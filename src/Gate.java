import java.io.Serializable;


public class Gate implements Serializable {
    private char terminal;
    private int gate;

    public Gate(char terminal) {
        this.terminal = terminal;
        gate = (int) (Math.random() * 17) + 1;
    }

    public void setGate(int gate) {
        this.gate = gate;
    }

    public void setTerminal(char terminal) {
        this.terminal = terminal;
    }

    public char getTerminal() {
        return terminal;
    }

    public int getGate() {
        return gate;
    }
}
