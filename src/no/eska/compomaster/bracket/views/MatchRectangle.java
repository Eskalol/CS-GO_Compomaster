package no.eska.compomaster.bracket.views;

import com.sun.javafx.scene.control.skin.LabeledText;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.TextAlignment;
import no.eska.compomaster.Resources;
import no.eska.compomaster.models.teams.Team;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * Created by Eska on 03.03.2016.
 */
public class MatchRectangle extends StackPane {
    public static final int WIDTH = 200;
    public static final int HEIGHT = 70;

    private final int ARC = 10;
    private final double OPACITY = 0.7;

    private final int FONTSIZE = 18;


    private TeamInMatch team1;
    private TeamInMatch team2;

    private Font font;
    private Rectangle outerRect;

    public MatchRectangle(int x, int y) {
        this.setAlignment(Pos.TOP_LEFT);
        font = Resources.getCsgoFont(FONTSIZE);
        this.setTranslateX(x);
        this.setTranslateY(y);
        load();
    }

    public void load() {
        loadHoverGlow();
        this.team1 = new TeamInMatch(Color.web("fe721d"), font, ARC, OPACITY, WIDTH, HEIGHT / 2);
        this.team2 = new TeamInMatch(Color.web("f8f8f8"), font, ARC, OPACITY, WIDTH, HEIGHT / 2);
        team2.setTranslateY(HEIGHT / 2);
        this.getChildren().addAll(team1, team2);
        loadMouseHover();
        loadMouseClick();
    }


    /**
     * loads Hover glow
     * TODO: fix transparent background of rectangle
     */
    public void loadHoverGlow() {
        DropShadow outerGlow = new DropShadow();
        outerGlow.setColor(Color.web("f8f8f8"));
        outerGlow.setOffsetX(0);
        outerGlow.setOffsetY(0);
        outerGlow.setRadius(20);

        outerRect = new Rectangle(200, 70);
        outerRect.setArcHeight(10);
        outerRect.setArcWidth(10);
        outerRect.setEffect(outerGlow);
        outerRect.setOpacity(0);
        this.getChildren().add(outerRect);

    }


    /**
     * loads mouse hover glow
     */
    private void loadMouseHover() {
        this.setOnMouseEntered(event -> {
            this.setTranslateY(this.getTranslateY() + 2);
        });
        this.setOnMouseExited(event -> {
            this.setTranslateY(this.getTranslateY() - 2);
        });
    }


    /**
     * loads mouse click animation
     */
    private void loadMouseClick() {
        this.setOnMousePressed(event -> {
            outerRect.setOpacity(0.5);
        });

        this.setOnMouseReleased(event -> {

            outerRect.setOpacity(0);
        });
    }
}