import java.awt.*;

public class Board {

    private int x = 0;
    private int y = 0;
    private int snapX = 0;
    private int snapY = 0;
    private int type = 0;

    public Board() {
        this.x = 0;
        this.y = 0;
        this.snapX = x / 40;
        this.snapY = y / 40;
        this.type = 0;
    }

    public Board(int x, int y, int t) {
        this.x = x;
        this.y = y;
        this.snapX = x / 40;
        this.snapY = y / 40;
        this.type = t;
    }

    public void draw(GamePlay map, Graphics g) {
        if (this.getType() != 0) {
            if (this.getType() == 1) {
                if (this.getSnapX() % 2 == 0) {
                    if (this.getSnapY() % 2 == 0) {
                        g.setColor(new Color(230, 230, 255));
                    } else {
                        g.setColor(Color.WHITE);
                    }
                } else if (this.getSnapX() % 2 == 1) {
                    if (this.getSnapY() % 2 == 0) {
                        g.setColor(Color.WHITE);
                    } else {
                        g.setColor(new Color(230, 230, 255));
                    }
                }
                g.fillRect(this.getX(), this.getY() + 22, 40, 40);

            } else if (this.getType() == 2) {
                g.setColor(new Color(181, 254, 180));
                g.fillRect(this.getX(), this.getY() + 22, 40, 40);

            } else if (this.getType() == 3) {
                g.setColor(new Color(181, 254, 180));
                g.fillRect(this.getX(), this.getY() + 22, 40, 40);
            }
        }
    }

    public Rectangle getBounds() {
        return new Rectangle(this.x, this.y, 39, 39);
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getSnapX() {
        return this.snapX;
    }

    public int getSnapY() {
        return this.snapY;
    }

    public int getType() {
        return this.type;
    }
}
