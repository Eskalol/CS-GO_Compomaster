package no.eska.compomaster.bracket.views;

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
 * Created by Eska on 14.04.2016.
 */
public class MapListElement extends StackPane {

    private Button remove;
    private Font font;

    public MapListElement(String mapName, String orangeWins, String whiteWins) {
        this.setAlignment(Pos.TOP_LEFT);
        this.font = Resources.getCsgoFont(25);
        loadBackground();
        loadText(mapName, orangeWins, whiteWins);
    }


    private void loadBackground() {
        Rectangle r = new Rectangle(50, 50);
        r.setStrokeWidth(1);
        r.setStroke(Resources.getWhite());
        r.setFill(Resources.getOrange());

        Rectangle r1 = new Rectangle(50, 50);
        r1.setStrokeWidth(1);
        r1.setStroke(Resources.getWhite());
        r1.setFill(Resources.getWhite());
        r1.setTranslateX(50);

        Rectangle r2 = new Rectangle(450, 50);
        r2.setStrokeWidth(1);
        r2.setStroke(Resources.getWhite());
        r2.setFill(Resources.getGray());
        r2.setTranslateX(100);

        remove = new Button(50, 50, 550, 0, "X", font, Resources.getOrange(), Color.BLACK, Resources.getGray());

        this.getChildren().addAll(r, r1, r2, remove);
    }

    private void loadText(String map, String orangeWins, String whiteWins) {
        Text t1 = new Text(map);
        t1.setFont(Resources.getCsgoFont(30));
        t1.setFill(Resources.getWhite());
        t1.setTranslateX(100+450/2-(map.length()*30)/3);
        t1.setTranslateY(25-30/4);

        Text t2 = new Text(orangeWins);
        t2.setFont(Resources.getCsgoFont(30));
        t2.setFill(Resources.getGray());
        t2.setTranslateY(25-30/4);
        t2.setTranslateX(50/2-(orangeWins.length()*30)/3);

        Text t3 = new Text(whiteWins);
        t3.setFont(Resources.getCsgoFont(30));
        t3.setFill(Resources.getGray());
        t3.setTranslateY(25-30/4);
        t3.setTranslateX(50+50/2-(whiteWins.length()*30)/3);

        this.getChildren().addAll(t1, t2, t3);
    }

    /**
     * set on remove event
     * @param evt event
     */
    public void setOnRemove(EventHandler<? super MouseEvent> evt) {
        remove.setOnMouseClicked(evt);
    }
}
