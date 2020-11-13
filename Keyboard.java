import java.awt.event.*;

public class Keyboard {

    public static boolean UpIsPress = false;
    public static boolean DownIsPress = false;
    public static boolean RightIsPress = false;
    public static boolean LeftIsPress = false;
    public static boolean EnterIsPress = false;
    public static boolean ShiftIsPress = false;

    public static void init() {
        Game.frame.addKeyListener(new KeyListener() {

            public void keyTyped(KeyEvent e) {
            }

            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    LeftIsPress = true;
                }
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    RightIsPress = true;
                }
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    UpIsPress = true;
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    DownIsPress = true;
                }
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    EnterIsPress = true;
                }
                if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
                    ShiftIsPress = true;
                }
            }

            public void keyReleased(KeyEvent e) {

                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    LeftIsPress = false;
                }
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    RightIsPress = false;
                }
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    UpIsPress = false;
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    DownIsPress = false;
                }
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    EnterIsPress = false;
                }
                if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
                    ShiftIsPress = true;
                }
            }
        });
    }
}
