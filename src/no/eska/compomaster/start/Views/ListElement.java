package no.eska.compomaster.start.views;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import no.eska.compomaster.Resources;
import no.eska.compomaster.guicomponents.Button;

/**
 * Created by Eska on 11.04.2016.
 */
public class ListElement extends StackPane {
    private Rectangle rect;
    private Button remove;
    private String content;

    /**
     * a list element in maps og teams
     * @param w width
     * @param h height
     * @param content text
     * @param font font
     */
    public ListElement(int w, int h, String content, Font font) {
        this.setAlignment(Pos.TOP_LEFT);
        this.setWidth(w);
        this.setHeight(h);
        this.content = content;
        rect = new Rectangle(w-h, h);
        rect.setFill(Resources.getWhite());
        rect.setStroke(Resources.getGray());
        rect.setStrokeWidth(1);
        remove = new Button(h, h, w-h, 0, "X", font, Resources.getOrange(), Color.BLACK, Resources.getGray());
        remove.setTranslateX(w-h);
        Text t1 = new Text(content);
        t1.setTranslateX((w-h)/2-content.length()*font.getSize()/4);
        t1.setTranslateY(h/2-font.getSize()/4);
        t1.setFont(font);
        this.getChildren().addAll(rect, t1, remove);
    }

    /**
     * set on remove event
     * @param evt event
     */
    public void setOnRemove(EventHandler<? super MouseEvent> evt) {
        remove.setOnMouseClicked(evt);
    }

    /**
     * returns text content
     * @return text content
     */
    public String getContent() {
        return this.content;
    }
}
