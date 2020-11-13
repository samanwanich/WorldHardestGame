import java.awt.*;
import java.awt.geom.*;

public class Enemy {

    private Point pos1;
    private Point pos2;
    private double x;
    private double y;
    private int snapX;
    private int snapY;
    private double speed;
    private boolean moveToPos1;
    private boolean vertMovement;

    public Enemy(int x, int y, Point pos1, Point pos2, double speed, boolean moveToPos1, boolean vertMovement) {
        this.x = x * 40;
        this.y = y * 40;
        this.snapX = x;
        this.snapY = y;
        this.pos1 = pos1;
        this.pos2 = pos2;
        this.speed = speed;
        this.moveToPos1 = moveToPos1;
        this.vertMovement = vertMovement;
    }

    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillOval((int) (this.x - 10) + 20, (int) (this.y - 10) + 20 + 22, 20, 20);
        g.setColor(Color.BLUE);
        g.fillOval((int) (this.x - 8) + 20, (int) (this.y - 8) + 20 + 22, 16, 16);
    }

    public void update() {
        this.snapX = (int) (this.x / 40);
        this.snapY = (int) (this.y / 40);
        if (moveToPos1) {
            if (!this.vertMovement) {
                this.x -= this.speed;
            } else {
                this.y -= this.speed;
            }
            if (this.snapX < this.pos1.x || this.snapY < this.pos1.y) {
                this.moveToPos1 = false;
            }
        } else {
            if (!this.vertMovement) {
                this.x += this.speed;
            } else {
                this.y += this.speed;
            }
            if (this.snapX > this.pos2.x || this.snapY > this.pos2.y) {
                this.moveToPos1 = true;
            }
        }
    }

    public Point getPos1() {
        return this.pos1;
    }

    public Point getPos2() {
        return this.pos2;
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public int getSnapX() {
        return this.snapX;
    }

    public int getSnapY() {
        return this.snapY;
    }

    public double getSpeed() {
        return this.speed;
    }

    public Ellipse2D getBounds() {
        return new Ellipse2D.Double((this.x - 10) + 20, (this.y - 10) + 20, 20, 20);
    }
}
