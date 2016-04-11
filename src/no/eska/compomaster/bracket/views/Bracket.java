package no.eska.compomaster.bracket.views;


import javafx.geometry.Pos;
import no.eska.compomaster.Main;
import no.eska.compomaster.Resources;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import org.w3c.dom.css.Rect;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

/**
 * Created by Eska on 03.03.2016.
 * This class is a view bracket generator
 */
public class Bracket extends StackPane {
    private Font        font;

    private final int   offsetY          = 100;
    private final int   offsetX          = 300;

    private int         lineThinckness   = 4;
    private boolean     looserBracket;

    private int         bracketSize;

    public double      deltaX;
    public double      deltaY;

    private HashMap<String, MatchRectangle> matches;

    /**
     * constructor
     *
     * @param bracketSize number of 2^n matches in round 1
     */
    public Bracket(int bracketSize) {
        this.bracketSize    = bracketSize;
        this.looserBracket  = false;
        this.font           = Resources.getCsgoFont(30);
        this.matches        = new HashMap<>();
    }


    /**
     * optional constructor for bracket
     * @param bracketSize number of 2^n matches in round 1
     * @param looserBracket looserbracket
     */
    public Bracket(int bracketSize, boolean looserBracket) {
        this.bracketSize    = bracketSize;
        this.looserBracket  = looserBracket;
        this.font           = Resources.getCsgoFont(30);
        this.matches        = new HashMap<>();
    }


    /**
     * load method for Bracket
     */
    public void load() {
        loadBackground();
        this.setAlignment(Pos.TOP_LEFT);
        loadWBracket(75, 75);
        loadLBracket(offsetX+75, offsetY*(int)Math.pow(2, bracketSize) + offsetY+75);
        //loadZoom();
        //loadDrag();
    }


    /**
     * loads winners bracket
     * @param xOff x offset
     * @param yOff y offset
     */
    private void loadWBracket(int xOff, int yOff) {
        int bsize = bracketSize;
        int posX = xOff;
        int posY = yOff;
        int matchCnt = 0;

        for(int i = 0; i < (int)Math.pow(2, bsize); i++) {
            int hlen = !this.looserBracket || bsize == bracketSize ? 50 : offsetX/2+50;
            int vlen = offsetY*(int)Math.pow(2, bracketSize-bsize);
            if(bsize > 0 || bsize > -1 && this.looserBracket)
                addHLine(bsize == 0 ? hlen+MatchRectangle.WIDTH/2+50 : hlen, posX+MatchRectangle.WIDTH, posY+MatchRectangle.HEIGHT/2-lineThinckness/2);
            if(bsize > 0 && i % 2 == 0) {
                addVLine(vlen, posX+MatchRectangle.WIDTH+hlen, posY+MatchRectangle.HEIGHT/2-lineThinckness/2);
                addHLine(hlen-lineThinckness, posX+MatchRectangle.WIDTH+hlen, posY+MatchRectangle.HEIGHT/2+vlen/2-lineThinckness/2);
            }
            addMatchRectangle(posX, posY, "wb"+(++matchCnt));
            posY += offsetY*(int)Math.pow(2, bracketSize - bsize);
            if( (i + 1) == (int)Math.pow(2, bsize) ) {
                posY = (offsetY/2)+offsetY*((int)Math.pow(2, (bracketSize - bsize))-1)+yOff;
                posX += this.looserBracket && bsize != bracketSize ? offsetX*2 : offsetX;
                i = -1;
                bsize--;
            }
        }

        //fix grand final in hashmap
        if(!this.looserBracket) {
            matches.put("gf", matches.get("wb"+matchCnt));
            matches.remove("wb"+matchCnt);
        }
    }


    /**
     * loads looserbracket
     * @param xOff x offset
     * @param yOff y offset
     */
    private void loadLBracket(int xOff, int yOff) {
        if(!this.looserBracket) return;
        int bsize = bracketSize-1;
        int posX = xOff;
        int posY = yOff;
        int matchCnt = 0;

        for( int i = 0; i < (int)Math.pow(2, bsize); i++ ) {
            int hlen = 50;
            int vlen = offsetY*(int)Math.pow(2, bracketSize-bsize-1);

            if( bsize > -1 ) {
                addHLine(hlen*2-lineThinckness, posX+MatchRectangle.WIDTH, posY+MatchRectangle.HEIGHT/2-lineThinckness/2);
                addHLine(hlen, posX+MatchRectangle.WIDTH+offsetX, posY+MatchRectangle.HEIGHT/2-lineThinckness/2);
            }
            if( bsize > 0 && i % 2 == 0 ) {
                addVLine(vlen, posX+MatchRectangle.WIDTH+hlen+offsetX, posY+MatchRectangle.HEIGHT/2-lineThinckness/2);
                addHLine(hlen-lineThinckness, posX+MatchRectangle.WIDTH+hlen+offsetX, posY+MatchRectangle.HEIGHT/2+vlen/2-lineThinckness/2);
            }

            //looser in winnerbracket line
            addHLine(hlen-lineThinckness, posX+offsetX-hlen, posY+MatchRectangle.HEIGHT/4);
            addVLine(10, posX+offsetX-hlen, posY+MatchRectangle.HEIGHT/4-10);

            if( bsize+1 == bracketSize ) {
                //first round in looser bracket
                addHLine(hlen-lineThinckness, posX-hlen, posY+MatchRectangle.HEIGHT/4);
                addVLine(10, posX-hlen, posY+MatchRectangle.HEIGHT/4-10);
                addHLine(hlen-lineThinckness, posX-hlen, posY+MatchRectangle.HEIGHT/4+MatchRectangle.HEIGHT/2);
                addVLine(10, posX-hlen, posY+MatchRectangle.HEIGHT/4+MatchRectangle.HEIGHT/2-10);
            }

            addMatchRectangle(posX, posY, "lb"+(++matchCnt));
            addMatchRectangle(posX+offsetX, posY, "lb"+(matchCnt+(int)Math.pow(2, bsize)));
            posY += offsetY*(int)Math.pow(2, bracketSize - bsize - 1);
            if( (i + 1) == (int)Math.pow(2, bsize) ) {
                posY = (offsetY/2)+offsetY*((int)Math.pow(2, ((bracketSize-1) - bsize))-1)+yOff;
                posX += offsetX*2;
                i = -1;
                matchCnt += (int)Math.pow(2, bsize);
                bsize--;
            }
        }
        //add grand final lines
        addVLine(offsetY*(int)Math.pow(2, bracketSize-1)/2 + offsetY*(int)Math.pow(2, bracketSize) + offsetY - offsetY*(int)Math.pow(2, bracketSize)/2, posX-50, yOff-(offsetY/2)*(int)Math.pow(2, bracketSize)-offsetY-MatchRectangle.HEIGHT/4);
        addHLine(50-lineThinckness, posX-50, yOff-offsetY+MatchRectangle.HEIGHT/2-lineThinckness/2);

        //add grand final
        addMatchRectangle(posX, yOff-offsetY, "gf");
    }

    /**
     * returns a view of a match
     * @param key key for match
     * @return MatchRectangle
     */
    public MatchRectangle getMatch(String key) {
        return matches.get(key);
    }


    /**
     * adding a MatchRectangle to brakcet with given coords
     *
     * @param x x coord
     * @param y y coord
     */
    private void addMatchRectangle(int x, int y, String key) {
        matches.put(key, new MatchRectangle(x, y));
        this.getChildren().add(matches.get(key));
    }


    /**
     * adds a horizontal line
     * @param len length of line
     * @param x translate pos
     * @param y translate pos
     */
    private void addHLine(int len, int x, int y) {
        Line l = new Line();
        l.setStartX(0);
        l.setStartY(0);
        l.setEndX(len);
        l.setEndY(0);
        l.setStrokeWidth(lineThinckness);
        l.setStroke(Color.web("f8f8f8"));
        l.setOpacity(0.7);
        l.setTranslateX(x);
        l.setTranslateY(y);
        this.getChildren().add(l);
    }


    /**
     * adds a vertical line
     * @param len length of line
     * @param x translate pos
     * @param y translate pos
     */
    private void addVLine(int len, int x, int y) {
        Line l = new Line();
        l.setStartX(0);
        l.setStartY(0);
        l.setEndX(0);
        l.setEndY(len);
        l.setStrokeWidth(lineThinckness);
        l.setStroke(Color.web("f8f8f8"));
        l.setOpacity(0.7);
        l.setTranslateX(x);
        l.setTranslateY(y);
        this.getChildren().add(l);
    }


    /**
     * loads background
     */
    public void loadBackground() {
        int bsize = this.looserBracket ? bracketSize + 1 : bracketSize;
        int height = offsetY*(int)Math.pow(2, bracketSize)+offsetY;
        height += this.looserBracket ? offsetY*(int)Math.pow(2, bracketSize-1)+offsetY : 0;
        int width = offsetX;
        int xOff = 50;

        for(int i = 0; i <= bsize; i++) {
            width = !this.looserBracket || i == bsize || i == 0 ? offsetX : offsetX*2;

            Rectangle rect = new Rectangle(width, height);
            rect.setFill(Color.TRANSPARENT);
            rect.setStroke(Color.web("232323"));
            rect.setTranslateY(0);
            rect.setTranslateX(xOff);
            rect.setStrokeWidth(lineThinckness);
            rect.setOpacity(0.7);
            this.getChildren().add(rect);

            Rectangle labelBg = new Rectangle(i == bsize ? offsetX/2 + offsetX/4 : offsetX/2, offsetY/2);
            labelBg.setFill(Color.web("232323"));
            labelBg.setOpacity(0.7);
            labelBg.setTranslateX(xOff+lineThinckness);
            labelBg.setTranslateY(lineThinckness);
            this.getChildren().add(labelBg);

            Text label = new Text(i == bsize ? "Grand final" : "Round " + (i+1));
            label.setFill(Color.web("f8f8f8"));
            label.setFont(font);
            label.setTranslateX(xOff+lineThinckness+10);
            label.setTranslateY(20);
            this.getChildren().addAll(label);


            xOff += width;
        }

        Rectangle sideBar = new Rectangle(50, height+lineThinckness);
        sideBar.setFill(Color.web("232323"));
        setTranslateX(0);
        setTranslateY(0);
        setOpacity(0.7);
        this.getChildren().add(sideBar);

        //add Winner views label
        Text wb = new Text(bracketSize > 1 ? "Winners Bracket" : "Winners");
        wb.setFont(font);
        wb.setFill(Color.web("f8f8f8"));
        wb.setRotate(-90);
        wb.setTranslateY((!this.looserBracket ? height/4 : (height)/4-offsetY/2)+(bracketSize > 1 ? 100 : 50));
        wb.setTranslateX(bracketSize > 1 ? -100 : -25);
        this.getChildren().add(wb);

        if(!this.looserBracket) return;

        //add Looser views label
        Text lb = new Text(bracketSize > 1 ? "Losers Bracket" : "Loosers");
        lb.setFont(font);
        lb.setFill(Color.web("f8f8f8"));
        lb.setRotate(-90);
        lb.setTranslateY(height/2+(int)Math.pow(2, bracketSize-1)*offsetY-offsetY/2+(bracketSize > 1 ? 100 : 50));
        lb.setTranslateX(bracketSize > 1 ? -100 : -25);
        this.getChildren().add(lb);
    }

}
