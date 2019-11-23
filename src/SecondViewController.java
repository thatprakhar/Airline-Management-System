import javax.swing.*;

public class SecondViewController {
    JFrame jFrame;
    SecondView secondView;

    public SecondViewController(JFrame jFrame, SecondView secondView) {
        this.jFrame = jFrame;
        this.secondView = secondView;
        this.secondView.getYesIWantToButton().addActionListener(e -> bookFLightSemantics());

    }

    public void bookFLightSemantics() {

    }
}
