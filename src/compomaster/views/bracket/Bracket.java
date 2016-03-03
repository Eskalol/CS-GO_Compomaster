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
    private final int offsetX   = 300;

    private int lineThinckness = 3;


    private int bracketSize;


    /**
     * constructor
     *
     * @param bracketSize number of 2^n teams in bracket
     */
    public Bracket(int bracketSize) {
        this.bracketSize = bracketSize;
    }


    /**
     * load method for Bracket
     */
    public void load() {
        loadWinnersBracket();
    }

    
    /**
     * Method loads Winner bracket to stackPane
     */
    private void loadWinnersBracket() {
        int nextPosX = 0;
        int nextPosY = 0;
        int bsize = bracketSize;
        for(int i = 0; i < (int)Math.pow(2, bsize); i++) {
            addMatchRectangle(nextPosX, nextPosY);
            if(bsize > 0)
                addHandVLines(nextPosX, nextPosY, i, (bracketSize - bsize));
            nextPosY += ( bsize == bracketSize ? offsetY : offsetY*((int)Math.pow(2, (bracketSize - bsize))) );
            if( (i + 1) == (int)Math.pow(2, bsize) ) {
                i = -1;
                //calc next y positon for first match in round
                nextPosY =  (offsetY/2)+offsetY*((int)Math.pow(2, (bracketSize - bsize))-1);
                bsize--;
                nextPosX += offsetX;
            }
        }
    }


    /**
     * adding a MatchRectangle to brakcet with given coords
     *
     * @param x x coord
     * @param y y coord
     */
    private void addMatchRectangle(int x, int y) {
        MatchRectangle match = new MatchRectangle();
        match.load();
        match.updateText();
        match.setTranslateX(x);
        match.setTranslateY(y);
        this.getChildren().add(match);

    }


    /**
     * method adds horizontal and vertical lines to bracket
     *
     * @param x xPosition
     * @param y yPosition
     * @param match match number in round
     * @param depth round/depth of tree
     */
    private void addHandVLines(int x, int y, int match, int depth) {
        Line l1 = getHorizontalLine(50);
        l1.setTranslateX(x+145);
        l1.setTranslateY(y+15+lineThinckness);
        this.getChildren().add(l1);

        int vLineLen = ( depth == 0 ? offsetY : offsetY*((int)Math.pow(2, depth)) );
        int yPos = ( depth == 0 ? y+65 : y+(offsetY/2)+offsetY*((int)Math.pow(2, depth)-1)-vLineLen/2+65 )+lineThinckness;
        if(match % 2 == 0){
            Line l2 = getVerticalLine(vLineLen);
            l2.setTranslateX(x+170);
            l2.setTranslateY(yPos);
            this.getChildren().add(l2);

            Line l3 = getHorizontalLine(46);
            l3.setTranslateX(x+193);
            l3.setTranslateY(yPos);
            this.getChildren().add(l3);
        }
    }


    /**
     * method return horizontal line with given length
     *
     * @param len length
     * @return
     */
    private Line getHorizontalLine(int len) {
        Line l = new Line();
        l.setStartX(0);
        l.setStartY(0);
        l.setEndX(len);
        l.setEndY(0);
        l.setStrokeWidth(lineThinckness);
        l.setStroke(Color.web("f8f8f8"));
        l.setOpacity(0.7);
        return l;
    }


    /**
     * method returns vertical line with given length
     *
     * @param len length
     * @return
     */
    private Line getVerticalLine(int len) {
        Line l = new Line();
        l.setStartX(0);
        l.setStartY(0);
        l.setEndX(0);
        l.setEndY(len);
        l.setStrokeWidth(lineThinckness);
        l.setStroke(Color.web("f8f8f8"));
        l.setOpacity(0.7);
        return l;
    }
}
