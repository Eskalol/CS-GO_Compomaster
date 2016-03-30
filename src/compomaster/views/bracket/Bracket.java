package compomaster.views.bracket;


import compomaster.models.teams.Team;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

/**
 * Created by Eska on 03.03.2016.
 */
public class Bracket extends StackPane {
    private final int offsetY   = 100;
    private final int offsetX   = 300;

    private int     lineThinckness = 5;
    private boolean looserBracket;

    private int bracketSize;

    private double deltaX;
    private double deltaY;


    /**
     * constructor
     *
     * @param bracketSize number of 2^n matches in round 1
     */
    public Bracket(int bracketSize) {
        this.bracketSize = bracketSize;
        this.looserBracket = false;
    }

    /**
     * optional constructor for brakcet
     * @param bracketSize number of 2^n matches in round 1
     * @param looserBracket looserbracket
     */
    public Bracket(int bracketSize, boolean looserBracket) {
        this.bracketSize = bracketSize;
        this.looserBracket = looserBracket;
    }

    /**
     * load method for Bracket
     */
    public void load() {
        loadWinnersBracket();
        loadZoom();
        loadDrag();
        loadLoosersBracket();
    }


    /**
     * method loads zoom on scroll event
     */
    private void loadZoom() {
        this.setOnScroll(new EventHandler<ScrollEvent>() {
            @Override
            public void handle(ScrollEvent event) {
                StackPane senderObject = (StackPane)event.getSource();
                if(senderObject.getScaleX() > 0.15 || event.getDeltaY() > 0) {
                    senderObject.setScaleX(senderObject.getScaleX()+(event.getDeltaY()/1000)*senderObject.getScaleX());
                    senderObject.setScaleY(senderObject.getScaleY()+(event.getDeltaY()/1000)*senderObject.getScaleY());
                }
            }
        });
    }


    /**
     * method loads events for dragging bracket in window
     */
    private void loadDrag() {
        this.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                StackPane senderObject = (StackPane)event.getSource();
                deltaX = senderObject.getTranslateX() - event.getSceneX();
                deltaY = senderObject.getTranslateY() - event.getSceneY();
            }
        });

        this.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                StackPane senderObject = (StackPane)event.getSource();
                senderObject.setTranslateX(event.getSceneX() + deltaX);
                senderObject.setTranslateY(event.getSceneY() + deltaY);
            }
        });
    }

    /**
     * Method loads Winner bracket to stackPane
     */
    private void loadWinnersBracket() {
        int nextPosX = 0;
        int nextPosY = 0;
        int bsize = bracketSize;
        for(int i = 0; i < (int)Math.pow(2, bsize); i++) {
            if(bsize > 0)
                addHandVLinesWinner(nextPosX, nextPosY, i, (bracketSize - bsize));
            addMatchRectangle(nextPosX, nextPosY);
            nextPosY += ( bsize == bracketSize ? offsetY : offsetY*((int)Math.pow(2, (bracketSize - bsize))) );
            if( (i + 1) == (int)Math.pow(2, bsize) ) {
                i = -1;
                //calc next y positon for first match in round
                nextPosY =  (offsetY/2)+offsetY*((int)Math.pow(2, (bracketSize - bsize))-1);
                //calc next x position
                nextPosX += (this.looserBracket && bsize < bracketSize ? offsetX*2 : offsetX);
                bsize--;
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
     * method adds horizontal and vertical lines to winners bracket
     *
     * @param x xPosition
     * @param y yPosition
     * @param match match number in round
     * @param depth round/depth of tree
     */
    private void addHandVLinesWinner(int x, int y, int match, int depth) {
        //calc length of horizontal line
        int horLen = (this.looserBracket && depth > 0 ? offsetX/2+50 : 50);
        int xOff = (this.looserBracket && depth > 0 ? 220 : 145);
        Line l1 = getHorizontalLine(horLen);
        l1.setTranslateX(x+xOff);
        l1.setTranslateY(y+12+lineThinckness);
        this.getChildren().add(l1);

        if(match % 2 == 0){
            int vLineLen = ( depth == 0 ? offsetY : offsetY*((int)Math.pow(2, depth)) );
            int yPos = ( depth == 0 ? y+62 : y+(offsetY/2)+offsetY*((int)Math.pow(2, depth)-1)-vLineLen/2+62 )+lineThinckness;
            Line l2 = getVerticalLine(vLineLen);
            l2.setTranslateX(x+xOff+horLen/2);
            l2.setTranslateY(yPos);
            this.getChildren().add(l2);

            int horX = horLen-4;
            Line l3 = getHorizontalLine(horX);
            l3.setTranslateX(x+xOff+horLen/2+horX/2);
            l3.setTranslateY(yPos);
            this.getChildren().add(l3);
        }
    }


    /**
     * method loads loosersbracket
     */
    private void loadLoosersBracket() {
        if(!this.looserBracket) return;
        int yStep = offsetY*((int)Math.pow(2, bracketSize)) + offsetY;
        int nextPosX = offsetX;
        int nextPosY = yStep;
        int bsize = bracketSize-1;
        for(int i = 0; i < (int)Math.pow(2, bsize); i++) {
            addMatchRectangle(nextPosX, nextPosY);
            addMatchRectangle(nextPosX+offsetX, nextPosY);
            nextPosY += ( bsize == bracketSize-1 ? offsetY : offsetY*((int)Math.pow(2, (bracketSize - bsize-1))) );
            if( (i + 1) == (int)Math.pow(2, bsize) ) {
                i = -1;
                //calc next y positon for first match in round
                nextPosY =  (offsetY/2)+offsetY*((int)Math.pow(2, (bracketSize - bsize-1))-1)+yStep;
                bsize--;
                nextPosX += offsetX*2;
            }
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
