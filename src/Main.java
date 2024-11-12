import javax.swing.JFrame;

public class Main {
    public Main() {
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        GamePanel gamePanel = new GamePanel();
        frame.setDefaultCloseOperation(3);
        frame.add(gamePanel);
        frame.setSize(310, 300);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}