package no.eska.compomaster.guicomponents;

import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

/**
 * Vbox inside a ScrollPane
 * Created by Eska on 11.04.2016.
 */
public class ScrollList extends ScrollPane {
    private VBox content;
    private int size = 0;

    public ScrollList(int w, int h, int x, int y) {
        this.setMaxSize(w, h);
        this.setMinSize(w, h);
        this.setTranslateX(x);
        this.setTranslateY(y);
        this.content = new VBox();
        this.setContent(content);
    }

    /**
     * add element to list
     * @param element element to add
     */
    public void addListElement(Node element) {
        this.content.getChildren().add(element);
        this.size++;
    }

    /**
     * remove element from list
     * @param element element to remove
     */
    public void removeListElement(Node element) {
        this.content.getChildren().remove(element);
        this.size--;
    }

    public int getSize() { return this.size; }

    /**
     * clears the scroll list.
     */
    public void clear() {
        this.getChildren().remove(content);
        this.content = new VBox();
        this.setContent(content);
    }
}
