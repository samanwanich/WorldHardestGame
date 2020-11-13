import java.awt.*;
import java.util.*;

public class Player {

    private int x;
    private int y;
    private boolean collidingUp;
    private boolean collidingDown;
    private boolean collidingLeft;
    private boolean collidingRight;
    private int deaths;
    private boolean dead;

    public Player() {
        this.x = 400;
        this.y = 300;
        this.deaths = 0;
        this.dead = false;
        this.collidingUp = false;
        this.collidingDown = false;
        this.collidingLeft = false;
        this.collidingRight = false;
    }

    public Player(int x, int y) {
        this.x = x;
        this.y = y;
        this.deaths = 0;
        this.dead = false;
        this.collidingUp = false;
        this.collidingDown = false;
        this.collidingLeft = false;
        this.collidingRight = false;
    }

    public void reset() {
        this.x = 400;
        this.y = 300;
        this.deaths = 0;
        this.dead = false;
        this.collidingUp = false;
        this.collidingDown = false;
        this.collidingLeft = false;
        this.collidingRight = false;
    }

    public void draw(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(x - 15, y + 7, 31, 31);
        g.setColor(Color.red);
        g.fillRect(x - 12, y + 10, 25, 25);
    }

    public void update(GamePlay GP) {
        if (GP.coins != null) {
            for (Coin coin : GP.coins) {
                if (this.collidesWith(coin.getBounds()) && !coin.collected) {
                    coin.collected = true;
                }
            }
        }
        if (GP.getMap() != new ArrayList<Board>()) {
            if (GP.allCoinsCollected()) {
                for (Board board : GP.getMap()) {
                    if (board.getType() == 3 && this.collidesWith(board.getBounds())) {

                        Game.levelNum++;
                        GP.init(this, Game.levelNum);
                        Game.gameState = Game.LEVEL;
                    }
                }
            }
        }

        checkCollisionUp(GP);
        checkCollisionDown(GP);
        checkCollisionLeft(GP);
        checkCollisionRight(GP);

        if (this.dead) {
            this.respawn(GP);
            this.dead = false;
        } else {
            if (Keyboard.UpIsPress && !this.collidingUp) {
                this.y--;
            }
            if (Keyboard.DownIsPress && !this.collidingDown) {
                this.y++;
            }
            if (Keyboard.LeftIsPress && !this.collidingLeft) {
                this.x--;
            }
            if (Keyboard.RightIsPress && !this.collidingRight) {
                this.x++;
            }
        }

        if (this.x > 800) {
            this.x = 0;
        }
        if (this.x < 0) {
            this.x = 800;
        }
        if (this.y > 600) {
            this.y = 0;
        }
        if (this.y < 0) {
            this.y = 600;
        }

        if (!this.dead) {
            for (Enemy enemy : GP.enemy) {
                if (this.collidesWith(enemy.getBounds())) {
                    this.deaths++;
                    this.dead = true;
                }
            }
        }
    }

    public int getDeaths() {
        return this.deaths;
    }

    void respawn(GamePlay level) {
        this.x = level.getSpawnPoint().x;
        this.y = level.getSpawnPoint().y;
        if (level.coins != null) {
            for (Coin coin : level.coins) {
                coin.collected = false;
            }
        }
    }

    public Rectangle getBounds() {
        return new Rectangle(x - 15, y - 15, 31, 31);
    }

    boolean collidesWith(Shape other) {
        return this.getBounds().getBounds2D().intersects(other.getBounds2D());
    }

    Board getRelativeTile(GamePlay gp, int x1, int y1, int xOff, int yOff) {
        for (Board t : gp.getMap()) {
            if (x1 / 40 + xOff == t.getSnapX() && y1 / 40 + yOff == t.getSnapY()) {
                return t;
            }
        }
        return null;
    }

    void checkCollisionUp(GamePlay gp) {
        if (getRelativeTile(gp, this.x - 14, this.y + 24, 0, -1) != null
                && getRelativeTile(gp, this.x - 14, this.y + 24, 0, -1).getType() == 0
                || getRelativeTile(gp, this.x + 15, this.y + 24, 0, -1) != null
                && getRelativeTile(gp, this.x + 15, this.y + 24, 0, -1).getType() == 0) {
            this.collidingUp = true;
            return;
        }
        this.collidingUp = false;
    }

    void checkCollisionDown(GamePlay gp) {
        if (getRelativeTile(gp, this.x - 14, this.y - 24, 0, 1) != null
                && getRelativeTile(gp, this.x - 14, this.y - 24, 0, 1).getType() == 0
                || getRelativeTile(gp, this.x + 15, this.y - 24, 0, 1) != null
                && getRelativeTile(gp, this.x + 15, this.y - 24, 0, 1).getType() == 0) {
            this.collidingDown = true;
            return;
        }
        this.collidingDown = false;
    }

    void checkCollisionLeft(GamePlay gp) {
        if (getRelativeTile(gp, this.x + 24, this.y - 15, -1, 0) != null
                && getRelativeTile(gp, this.x + 24, this.y - 15, -1, 0).getType() == 0
                || getRelativeTile(gp, this.x + 24, this.y + 14, -1, 0) != null
                && getRelativeTile(gp, this.x + 24, this.y + 14, -1, 0).getType() == 0) {
            this.collidingLeft = true;
            return;
        }
        this.collidingLeft = false;
    }

    void checkCollisionRight(GamePlay gp) {
        if (getRelativeTile(gp, this.x - 24, this.y - 15, 1, 0) != null
                && getRelativeTile(gp, this.x - 24, this.y - 15, 1, 0).getType() == 0
                || getRelativeTile(gp, this.x - 24, this.y + 15, 1, 0) != null
                && getRelativeTile(gp, this.x - 24, this.y + 15, 1, 0).getType() == 0) {
            this.collidingRight = true;
            return;
        }
        this.collidingRight = false;
    }
}
