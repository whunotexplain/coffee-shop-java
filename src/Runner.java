import javax.swing.SwingUtilities;
import app.views.LoginFrame;

public class Runner {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            LoginFrame frame =
                    new LoginFrame();

            frame.setVisible(true);
        });
    }
}