import java.awt.*;
import java.awt.event.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.text.DecimalFormat;
import javax.swing.*;

public class Game extends JPanel implements ActionListener {

    private static Game game;
    private Timer timer = new Timer(5, this);
    static JFrame frame = new JFrame();
    static final int MAIN_MENU = 1, LEVEL = 2, Ending = 3;
    static int gameState = MAIN_MENU;
    static double time = 0;
    public boolean timecount = false;
    static int levelNum = 0;
    private Player player = new Player();
    static GamePlay level = new GamePlay() {};
    static HardMode hard = new HardMode() {};
    private int totalLevel = 2;
    private DecimalFormat df2 = new DecimalFormat("#.##");
    private boolean HardMode = false;

    public void paintComponent(final Graphics g) {
        super.paintComponent(g);
        update(g);
        render(g);
        timer.start();
        if (timecount) {
            time += 0.005;
        }
    }

    public void update(Graphics g) {
        if (gameState == MAIN_MENU) {
            if (Keyboard.EnterIsPress && !Keyboard.ShiftIsPress) {
                player.reset();
                levelNum = 1;
                level.init(player, levelNum);
                new Thread() {
                    public void run() {
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                        }
                        gameState = LEVEL;
                    }
                }.start();
            }
            if (Keyboard.EnterIsPress && Keyboard.ShiftIsPress) {
                player.reset();
                levelNum = 1;
                HardMode = true;
                hard.init(player, levelNum);
                new Thread() {
                    public void run() {
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                        }
                        gameState = LEVEL;
                    }
                }.start();
            }
        }
    }

    private void render(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        if (gameState == MAIN_MENU) {
            g2.setPaint(new GradientPaint(0, 0, new Color(213, 213, 255), 0, 600, Color.WHITE));
            g2.fillRect(0, 0, 800, 600);

            g.setColor(Color.BLACK);
            g.setFont(new Font("SansSerif", Font.BOLD, 30));
            g.drawString("THE WORLD'S", 40, 65);
            g.setColor(Color.CYAN);
            g.setFont(new Font("SansSerif", Font.BOLD, 80));
            g.drawString("HARDEST GAME", 40, 145);
            g.setColor(Color.BLACK);
            drawTextOutline("HARDEST GAME", 40, 145, 3, g2);

            g.setFont(new Font("SansSerif", Font.BOLD, 40));
            g.drawString("PRESS ENTER", 50, 360);
            g.setColor(Color.BLACK);
            g.drawString("TO PLAY NORMAL MODE !", 50, 400);
            g.setColor(Color.GREEN);
            drawTextOutline("TO PLAY NORMAL MODE !", 50, 400, 2, g2);
            g.setColor(Color.BLACK);
            g.drawString("HOLD SHIFT + ENTER", 50, 480);
            g.setColor(Color.BLACK);
            g.drawString("TO PLAY HARD MODE !", 50, 520);
            g.setColor(Color.RED);
            drawTextOutline("TO PLAY HARD MODE !", 50, 520, 2, g2);
        } else if (gameState == LEVEL) {
            if (levelNum != 0 && !HardMode) {
                timecount = true;
                level.drawMaps(g);
                level.drawCoins(g);
                level.drawEnemy(g);
                level.updateEnemy();

                player.draw(g);
                player.update(level);

                g.setColor(Color.BLACK);
                g.fillRect(0, 0, 800, 22);
                g.setColor(Color.WHITE);
                g.setFont(new Font("Tahoma", Font.BOLD, 18));
                g.drawString("Deaths: " + player.getDeaths(), 670, 17);
                g.drawString(levelNum + "/" + totalLevel, 380, 17);

                g.drawString("Time: " + df2.format(time), 30, 17);
            } else if (levelNum != 0 && HardMode) {
                timecount = true;
                hard.drawMaps(g);
                hard.drawCoins(g);
                hard.drawEnemy(g);
                hard.updateEnemy();

                player.draw(g);
                player.update(hard);

                g.setColor(Color.BLACK);
                g.fillRect(0, 0, 800, 22);
                g.setColor(Color.WHITE);
                g.setFont(new Font("Tahoma", Font.BOLD, 18));
                g.drawString("Deaths: " + player.getDeaths(), 670, 17);
                g.drawString(levelNum + "/" + totalLevel, 380, 17);

                g.drawString("Time: " + df2.format(time), 30, 17);
            }
            if (levelNum == totalLevel + 1) {
                timecount = false;
                gameState = Ending;
            }
        } else if (gameState == Ending) {
            g2.setPaint(new GradientPaint(0, 0, new Color(213, 213, 255), 0, 600, Color.WHITE));
            g2.fillRect(0, 0, 800, 600);
            g.setFont(new Font("Tahoma", Font.BOLD, 32));
            g.setColor(Color.BLACK);
            g.drawString("Your Score", 310, 150);
            g.drawString("Deaths: " + player.getDeaths() + "\n", 320, 225);
            g.drawString("Time: " + df2.format(time) + "\n", 300, 290);
            g.setFont(new Font("Tahoma", Font.BOLD, 48));
            g.drawString("Thanks for playing !", 160, 375);
        }
        g.dispose();
    }

    private void drawTextOutline(String text, int x, int y, int thickness, Graphics2D g2) {
        TextLayout tl = new TextLayout(text, g2.getFont(), new FontRenderContext(null, false, false));
        AffineTransform textAt = new AffineTransform();
        textAt.translate(x, y);
        g2.setStroke(new BasicStroke(thickness));
        g2.draw(tl.getOutline(textAt));
        g2.setStroke(new BasicStroke());
    }

    public void actionPerformed(ActionEvent arg0) {
        repaint();
    }

    public static void main(String[] args) {
        playMusic ms = new playMusic();
        ms.playBackground("music.wav");
        frame.setTitle("World's Hardest Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(800, 622));
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        Keyboard.init();
        game = new Game();
        frame.add(game);
        frame.setVisible(true);
    }

}
