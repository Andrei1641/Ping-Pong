import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener {
    private static final int GAME_WIDTH = 300;
    private static final int GAME_HEIGHT = 300;
    private static final int TILE_SIZE = 15;
    private static final int TILE_SIZE_BALL = 10;
    private ArrayList<Integer> x = new ArrayList();
    private ArrayList<Integer> y = new ArrayList();
    private int playerxL;
    private int playeryL;
    private int playerxR;
    private int playeryR;
    private int ballx;
    private int bally;
    private int rangeP;
    private boolean up = false;
    private boolean left = true;
    private boolean doun = true;
    private boolean right = false;
    private boolean isBrickToch = false;
    private static boolean leftP = true;
    private static boolean rightP = false;
    private boolean inGame = true;
    private Timer timer;

    public GamePanel() {
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        this.setPreferredSize(new Dimension(300, 300));
        this.addKeyListener(new TAdapter(this));
        this.initGame();
    }

    public void initGame() {
        for(int j = 0; j < 3; ++j) {
            for(int i = 0; i < 20; ++i) {
                this.x.add(i * 15);
                this.y.add(15 * j);
            }
        }

        this.playerxL = 150;
        this.playeryL = 250;
        this.playerxR = 195;
        this.playeryR = 250;
        this.ballx = 300;
        this.bally = 100;
        this.requestFocusInWindow();
        this.timer = new Timer(50, this);
        this.timer.start();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.inGame) {
            g.setColor(Color.BLUE);
            g.fillRect(this.playerxL, this.playeryL, 45, 15);
            g.fillRect(this.playerxR, this.playeryR, 45, 15);
            g.setColor(Color.YELLOW);

            for(int i = 0; i < this.x.size(); ++i) {
                g.fillRect((Integer)this.x.get(i), (Integer)this.y.get(i), 15, 15);
            }

            g.setColor(Color.WHITE);
            g.fillRect(this.ballx, this.bally, 10, 10);
        }

    }

    void Move() {
        if (rightP) {
            this.playerxL += 10;
            this.playerxR += 10;
        }

        if (leftP) {
            this.playerxL -= 10;
            this.playerxR -= 10;
        }

        if (this.playerxR >= 255) {
            leftP = true;
            rightP = false;
        }

        if (this.playerxL <= 0) {
            leftP = false;
            rightP = true;
        }

        if (this.up) {
            this.bally -= 5;
        }

        if (this.right) {
            this.ballx += 5 + this.rangeP;
        }

        if (this.left) {
            this.ballx -= 5 + this.rangeP;
        }

        if (this.doun) {
            this.bally += 5;
        }

    }

    void ChecColl() {
        if (this.bally + 15 >= this.playeryR && this.bally <= this.playeryR + 15 && this.ballx + 15 >= this.playerxR && this.ballx <= this.playerxR + 45) {
            this.right = true;
            this.left = false;
            this.up = true;
            this.doun = false;
            this.rangeP = 12;
            this.isBrickToch = false;
        }

        if (this.bally + 15 >= this.playeryR && this.bally <= this.playeryR + 15 && this.ballx + 15 >= this.playerxR && this.ballx <= this.playerxR + 30) {
            this.right = true;
            this.left = false;
            this.up = true;
            this.doun = false;
            this.rangeP = 7;
            this.isBrickToch = false;
        }

        if (this.bally + 15 >= this.playeryR && this.bally <= this.playeryR + 15 && this.ballx + 15 >= this.playerxR && this.ballx <= this.playerxR + 15) {
            this.right = true;
            this.left = false;
            this.up = true;
            this.doun = false;
            this.rangeP = 5;
            this.isBrickToch = false;
        }

        if (this.bally + 15 >= this.playeryL && this.bally <= this.playeryL + 15 && this.ballx + 15 >= this.playerxL && this.ballx <= this.playerxL + 45) {
            this.right = false;
            this.left = true;
            this.up = true;
            this.doun = false;
            this.rangeP = 5;
            this.isBrickToch = false;
        }

        if (this.bally + 15 >= this.playeryL && this.bally <= this.playeryL + 15 && this.ballx + 15 >= this.playerxL && this.ballx <= this.playerxL + 30) {
            this.right = false;
            this.left = true;
            this.up = true;
            this.doun = false;
            this.rangeP = 7;
            this.isBrickToch = false;
        }

        if (this.bally + 15 >= this.playeryL && this.bally <= this.playeryL + 15 && this.ballx + 15 >= this.playerxL && this.ballx <= this.playerxL + 15) {
            this.right = false;
            this.left = true;
            this.up = true;
            this.doun = false;
            this.rangeP = 9;
            this.isBrickToch = false;
        }

        if (this.ballx + 10 >= 300) {
            this.right = false;
            this.left = true;
        }

        if (this.ballx <= 0) {
            this.right = true;
            this.left = false;
        }

        for(int i = 0; i < this.x.size(); ++i) {
            if ((Integer)this.x.get(i) <= this.ballx + 10 && (Integer)this.x.get(i) + 15 >= this.ballx && this.bally <= (Integer)this.y.get(i) + 15 && this.bally + 10 >= (Integer)this.y.get(i)) {
                if (this.bally + 10 - 5 <= (Integer)this.y.get(i)) {
                    this.doun = false;
                    this.up = true;
                } else if (this.bally >= (Integer)this.y.get(i) + 15 - 5) {
                    this.up = false;
                    this.doun = true;
                } else if (this.ballx + 10 - 5 <= (Integer)this.x.get(i)) {
                    this.right = false;
                    this.left = true;
                } else if (this.ballx >= (Integer)this.x.get(i) + 15 - 5) {
                    this.left = false;
                    this.right = true;
                }

                this.x.remove(i);
                this.y.remove(i);
                break;
            }
        }

        if (this.bally <= 0) {
            this.up = false;
            this.doun = true;
        }

    }

    public void actionPerformed(ActionEvent e) {
        if (this.inGame) {
            this.Move();
            this.ChecColl();
        }

        this.repaint();
    }

    class TAdapter extends KeyAdapter {
        TAdapter(final GamePanel this$0) {
        }

        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);
            int key = e.getKeyCode();
            if (GamePanel.leftP && !GamePanel.rightP) {
                if (key == 32) {
                    GamePanel.leftP = false;
                    GamePanel.rightP = true;
                }
            } else if (key == 32) {
                GamePanel.leftP = true;
                GamePanel.rightP = false;
            }

        }
    }
}