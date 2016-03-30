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
        joinWinnerAndLooserBracket();
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

        int yStep = offsetY*(int)Math.pow(2, bracketSize) + offsetY;
        int nextPosX = offsetX;
        int nextPosY = yStep;
        int bsize = bracketSize-1;

        for(int i = 0; i < (int)Math.pow(2, bsize); i++) {
            addHandVLinesLooser(nextPosX, nextPosY, 0, i, bracketSize - bsize - 1);
            addHandVLinesLooser(nextPosX+offsetX, nextPosY, 1, i, bracketSize - bsize - 1);
            addMatchRectangle(nextPosX, nextPosY);
            addMatchRectangle(nextPosX+offsetX, nextPosY);
            nextPosY += ( bsize == bracketSize-1 ? offsetY : offsetY*((int)Math.pow(2, (bracketSize - bsize - 1))) );
            if( (i + 1) == (int)Math.pow(2, bsize) ) {
                i = -1;
                //calc next y positon for first match in round
                nextPosY =  (offsetY/2)+offsetY*((int)Math.pow(2, (bracketSize - bsize-1))-1)+yStep;
                bsize--;
                nextPosX += offsetX*2;
            }
        }

        //Add grand final
        addMatchRectangle(nextPosX, yStep-offsetY);
    }


    /**
     * method adds horizontal and vertical lines to Looser bracket
     *
     * @param x xPosition
     * @param y yPosition
     * @param matchType match type 0 is first column in looser bracket round 1 is second column
     * @param matchRow match row in looserbracket round
     * @param depth round/depth of tree
     */
    private void addHandVLinesLooser(int x, int y, int matchType, int matchRow, int depth) {
        //calc length of horizontal line
        int horLen = ( matchType == 0 ? 96 : 50);
        int xOff = (matchType == 0 ? 168 : 145);

        Line l1 = getHorizontalLine(horLen);
        l1.setTranslateX(x+xOff);
        l1.setTranslateY(y+12+lineThinckness);
        this.getChildren().add(l1);

        //first add lines for looser in winnersbracket round
        if(matchType == 1 || depth == 0) {
            Line l2 = getHorizontalLine(45);
            l2.setTranslateX(x - 107);
            l2.setTranslateY(y);
            this.getChildren().add(l2);
            Line l3 = getVerticalLine(10);
            l3.setTranslateX(x - 130);
            l3.setTranslateY(y - 5);
            this.getChildren().add(l3);
        }

        //first round in looserbracket
        if(depth == 0 && matchType == 0) {
            Line l4 = getHorizontalLine(45);
            l4.setTranslateX(x - 107);
            l4.setTranslateY(y + 35);
            this.getChildren().add(l4);
            Line l5 = getVerticalLine(10);
            l5.setTranslateX(x - 130);
            l5.setTranslateY(y + 30);
            this.getChildren().add(l5);
        }

        if(matchRow % 2 == 0 && matchType == 1 && depth+1 != bracketSize) {
            int vLineLen = offsetY*((int)Math.pow(2, depth));
            int yPos = y+(offsetY/2)+offsetY*((int)Math.pow(2, depth)-1)-vLineLen/2+62+lineThinckness;
            Line l6 = getVerticalLine(vLineLen);
            l6.setTranslateX(x+170);
            l6.setTranslateY(yPos);
            this.getChildren().add(l6);

            Line l7 = getHorizontalLine(46);
            l7.setTranslateX(x+170+23);
            l7.setTranslateY(yPos);
            this.getChildren().add(l7);
        }

    }

    /**
     * method joins winner and looser bracket together for grand final.
     */
    private void joinWinnerAndLooserBracket() {
        if(!this.looserBracket) return;

        //adding horisontal line for winner of winner bracket
        int yPos = offsetY*(int)Math.pow(2, bracketSize)/2;
        int xPos = offsetX*bracketSize*2-lineThinckness;
        Line l1 = getHorizontalLine(offsetX+50);
        l1.setTranslateX(xPos);
        l1.setTranslateY(yPos-33);
        this.getChildren().add(l1);
        
        //adding vertical line to join winner and looser bracket
        xPos = offsetX*bracketSize*2+offsetX/2+20;
        int w2Llen = offsetY*(int)Math.pow(2, bracketSize-1)/2 + offsetY*(int)Math.pow(2, bracketSize) + offsetY - yPos;
        Line l2 = getVerticalLine(w2Llen);
        l2.setTranslateX(xPos);
        l2.setTranslateY(w2Llen/2+yPos-33);
        this.getChildren().add(l2);

        //line to grand final
        Line l3 = getHorizontalLine(46);
        l3.setTranslateX(xPos+23);
        l3.setTranslateY(yPos*2+17);
        this.getChildren().add(l3);
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
