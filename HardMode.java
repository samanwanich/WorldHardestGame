import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Area;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class HardMode extends GamePlay{
    
    @Override
    public void init(Player player, int levelNum) {
        this.map = new ArrayList<Board>();
        this.enemy = new ArrayList<Enemy>();
        this.coins = new ArrayList<Coin>();
        this.levelArea = new Area();

        try {
            this.spawnPoint = new Point(Integer.parseInt(PropLoader.loadProperty("spawn_point", "Hard_" + levelNum + ".properties")
                    .split(",")[0]) * 40 + 20, Integer.parseInt(PropLoader.loadProperty("spawn_point", "Hard_" + levelNum + ".properties")
                    .split(",")[1]) * 40 + 20);

            String coinData = null;
            if (PropLoader.loadProperty("coins", "Hard_" + levelNum + ".properties") != "null") {
                coinData = PropLoader.loadProperty("coins", "Hard_" + levelNum + ".properties");
            }
            if (coinData != null) {
                coinData = coinData.replaceAll("\\Z", "");

                if (coinData.contains("-")) {
                    String[] coins = coinData.split("-");
                    for (String s : coins) {
                        this.coins.add(new Coin((int) (Double.parseDouble(s.split(",")[0]) * 40), (int) (Double.parseDouble(s.split(",")[1]) * 40)));
                    }
                } else {
                    this.coins.add(new Coin((int) (Double.parseDouble(coinData.split(",")[0]) * 40), (int) (Double.parseDouble(coinData.split(",")[1]) * 40)));
                }
            }

            InputStreamReader isr = new InputStreamReader(ClassLoader.getSystemResource("Hard_" + levelNum + ".txt").openStream());
            String content = "";
            Scanner scanner = new Scanner(isr);
            content = scanner.useDelimiter("\\Z").next();
            scanner.close();
            content = content.replaceAll("\n", "");

            for (int i = 0; i < content.length(); i++) {
                if (i > 299) {
                    break;
                } else {
                    this.map.add(new Board((i % 20) * 40, (i / 20) * 40, Character.getNumericValue(content.charAt(i))));
                }
            }
            this.levelArea = new Area();
            for (Board board : this.map) {
                if (board.getType() != 0) {
                    this.levelArea.add(new Area(new Rectangle(board.getX() - 3, board.getY() - 3 + 22, 46, 46)));
                }
            }
        } catch (Exception e) {
        }

        try {
            InputStreamReader isr = new InputStreamReader(ClassLoader.getSystemResource("Hard_" + levelNum + ".txt").openStream());
            Scanner sc = new Scanner(isr);
            String content = sc.useDelimiter("\\Z").next();
            String[] lines = content.split("\n");
            sc.close();
            for (int i = 19; lines[i] != null; i++) {
                String line = lines[i];
                String[] enemyData = line.replaceAll(" ", "").split("-");
                this.enemy.add(new Enemy(Integer.parseInt(enemyData[0]), Integer.parseInt(enemyData[1]),
                        new Point(Integer.parseInt(enemyData[2].split(",")[0]), Integer.parseInt(enemyData[2].split(",")[1])),
                        new Point(Integer.parseInt(enemyData[3].split(",")[0]), Integer.parseInt(enemyData[3].split(",")[1])),
                        Double.parseDouble(enemyData[4]),
                        Boolean.parseBoolean(enemyData[5]),
                        Boolean.parseBoolean(enemyData[6])
                ));
            }
        } catch (Exception e) {
        }

        player.respawn(this);
    }
}
