import javax.swing.JFrame;

public class runner {

    public static void main(String[] args) {
        JFrame app = new GUI();
        app.setTitle("Number System");
        app.setSize(500, 200);
        app.pack();
        app.setLocationRelativeTo(null);
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        app.setVisible(true);
    }
}