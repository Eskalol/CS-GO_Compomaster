package no.eska.compomaster.guicomponents;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.effect.InnerShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import no.eska.compomaster.Resources;

/**
 * Created by Eska on 11.04.2016.
 */
public class Button extends StackPane {
    private Rectangle rect;
    private Text text;
    private InnerShadow ie;
    private Color fill;
    private Color border;

    /**
     * Button
     * @param w width
     * @param h height
     * @param x translate x
     * @param y translate y
     * @param text button text
     * @param font button font
     * @param fill button fill
     * @param textColor button text color
     * @param border button border color
     */
    public Button(int w, int h, int x, int y, String text, Font font, Color fill, Color textColor, Color border) {
        this.setWidth(w);
        this.setHeight(h);
        this.setTranslateX(x);
        this.setTranslateY(y);
        this.setAlignment(Pos.TOP_LEFT);
        this.fill = fill;
        this.border = border;

        rect = new Rectangle(w, h);
        rect.setFill(fill);
        rect.setStroke(border);
        rect.setStrokeWidth(1);
        this.text = new Text(text);
        this.text.setFont(font);
        this.text.setFill(textColor);
        this.text.setTranslateX(w/2-(text.length()*font.getSize())/3);
        this.text.setTranslateY(h/2-font.getSize()/4);
        ie = new InnerShadow();
        ie.setColor(Resources.getWhite());
        ie.setRadius(20);
        rect.setOnMouseEntered(event -> {
            rect.setEffect(ie);
        });
        rect.setOnMouseExited(event -> {
            rect.setEffect(null);
        });
        this.text.setOnMouseEntered(event -> {
            rect.setEffect(ie);
        });
        this.text.setOnMouseExited(event -> {
            rect.setEffect(null);
        });
        this.rect.setOnMousePressed(event -> {
            ie.setColor(Resources.getGray());
            this.text.setEffect(ie);
        });
        this.rect.setOnMouseReleased(event -> {
            ie.setColor(Resources.getWhite());
            this.text.setEffect(null);
        });
        this.text.setOnMousePressed(event -> {
            ie.setColor(Resources.getGray());
            this.text.setEffect(ie);
        });
        this.text.setOnMouseReleased(event -> {
            ie.setColor(Resources.getWhite());
            this.text.setEffect(null);
        });

        this.getChildren().addAll(this.rect, this.text);
    }

}
