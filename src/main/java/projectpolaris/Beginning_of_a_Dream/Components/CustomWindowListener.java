package projectpolaris.Beginning_of_a_Dream.Components;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class CustomWindowListener implements WindowListener {
    @Override
    public void windowOpened(WindowEvent e) {
        System.out.println("windowOpened: " + e.toString());
    }

    @Override
    public void windowClosing(WindowEvent e) {
        System.out.println("windowClosing: " + e.toString());
        System.out.println("Closing...");

        System.exit(1);

    }

    @Override
    public void windowClosed(WindowEvent e) {
        System.out.println("windowClosed: " + e.toString());
    }

    @Override
    public void windowIconified(WindowEvent e) {
        System.out.println("windowIconified: " + e.toString());

    }

    @Override
    public void windowDeiconified(WindowEvent e) {
        System.out.println("windowDeiconified: " + e.toString());

    }

    @Override
    public void windowActivated(WindowEvent e) {
        System.out.println("windowActivated: " + e.toString());

    }

    @Override
    public void windowDeactivated(WindowEvent e) {
        System.out.println("windowDeactivated: " + e.toString());

    }
}
