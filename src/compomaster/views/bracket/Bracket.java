package compomaster.views.bracket;


import compomaster.models.teams.Team;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

/**
 * Created by Eska on 03.03.2016.
 */
public class Bracket extends StackPane {
    private final int offsetY   = 100;
    private final int offsetX   = 300
            ;

    private int nextPosX = 0;
    private int nextPosY = 0;

    private int bracketSize;

    /**
     * constructor
     *
     * @param bracketSize number of teams in bracket
     */
    public Bracket(int bracketSize) {
        this.bracketSize = bracketSize;
    }

    public void load() {
        MatchRectangle match1 = new MatchRectangle(new Team("Steklovata"), new Team("Kraftlaget"));
        match1.load();
        match1.updateText();
        nextPosY += offsetY;
        Line l1 = getHorizontalLine();
        l1.setTranslateX(145);
        l1.setTranslateY(18);

        MatchRectangle match2 = new MatchRectangle(new Team("Team Nordlys"), new Team("Karasjok allstars"));
        match2.load();
        match2.updateText();
        match2.setTranslateX(nextPosX);
        match2.setTranslateY(nextPosY);
        Line l2 = getHorizontalLine();
        l2.setTranslateX(145);
        l2.setTranslateY(18+offsetY);

        Line l3 = getVerticalLine();
        l3.setTranslateY(68);
        l3.setTranslateX(170);

        Line l4 = getHorizontalLine();
        l4.setTranslateY(68);
        l4.setTranslateX(195);


        MatchRectangle match3 = new MatchRectangle();
        match3.load();
        match3.updateText();
        match3.setTranslateY(50);
        match3.setTranslateX(305);

        this.getChildren().addAll(match1, match2, l1, l2, l3, l4, match3);
    }


    private Line getHorizontalLine() {
        Line l = new Line();
        l.setStartX(0);
        l.setStartY(0);
        l.setEndX(50);
        l.setEndY(0);
        l.setStrokeWidth(3);
        l.setStroke(Color.web("f8f8f8"));
        l.setOpacity(0.7);
        return l;
    }

    private Line getVerticalLine() {
        Line l = new Line();
        l.setStartX(0);
        l.setStartY(0);
        l.setEndX(0);
        l.setEndY(100);
        l.setStrokeWidth(3);
        l.setStroke(Color.web("f8f8f8"));
        l.setOpacity(0.7);
        return l;
    }
}
