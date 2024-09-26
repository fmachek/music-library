package UserInterface;

import UserInterface.Panels.PlaybackControlPanel;
import UtilityClasses.FileLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

/**
 * Represents the main application window.
 */
public class MainAppWindow extends JFrame {
    /**
     * Main constructor for the main application window.
     * @param frameSize the size of the window
     */
    public MainAppWindow(Dimension frameSize) {
        setTitle("MusicLibrary");
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        try {
            ImageIcon icon = FileLoader.loadImageFromFile("res\\icons\\disc.png");
            setIconImage(icon.getImage());
        } catch (Exception e) {
            e.printStackTrace();
        }

        getContentPane().setPreferredSize(frameSize);
        pack();
    }

    /**
     * Loads the main window keybindings.
     * @param playbackPanel playback panel
     */
    public void loadKeybindings(PlaybackControlPanel playbackPanel) {
        // Action for CTRL + left arrow key
        Action ctrlLeftAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playbackPanel.activateBackwardButton();
            }
        };

        // Action for CTRL + right arrow key
        Action ctrlRightAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playbackPanel.activateForwardButton();
            }
        };

        // Action for the spacebar key
        Action spacebarAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playbackPanel.activatePlayButton();
            }
        };

        // Get the input map and action map for the content pane
        InputMap inputMap = getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = getRootPane().getActionMap();

        // Control + left arrow bind => backward button activates
        inputMap.put(KeyStroke.getKeyStroke("control LEFT"), "ctrlLeftAction");
        actionMap.put("ctrlLeftAction", ctrlLeftAction);

        // Control + right arrow bind => forward button activates
        inputMap.put(KeyStroke.getKeyStroke("control RIGHT"), "ctrlRightAction");
        actionMap.put("ctrlRightAction", ctrlRightAction);

        // Spacebar bind => play button activates
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0), "spacebarAction");
        actionMap.put("spacebarAction", spacebarAction);
    }
}
