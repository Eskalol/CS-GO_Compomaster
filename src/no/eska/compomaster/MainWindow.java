package no.eska.compomaster;

import javafx.scene.Node;

/**
 * Created by Eska on 02.03.2016.
 */
public interface MainWindow {
    public void addNodeToRootPane(Node node);
    public int  getHeight();
    public int  getWidth();
}
