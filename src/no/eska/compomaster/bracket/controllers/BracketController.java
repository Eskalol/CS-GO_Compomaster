package no.eska.compomaster.bracket.controllers;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.StackPane;
import no.eska.compomaster.MainWindow;
import no.eska.compomaster.bracket.models.BracketModel;
import no.eska.compomaster.bracket.models.Match;
import no.eska.compomaster.bracket.models.Team;
import no.eska.compomaster.bracket.views.Bracket;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

/**
 * Created by Eska on 09.04.2016.
 */
public class BracketController {

    private MainWindow main;

    private Bracket bracket;
    private BracketModel bracketModel;

    private boolean drag = false;

    public BracketController(ArrayList<Team> teams, boolean loosersBracket, MainWindow main) {
        this.main               = main;
        int startMatches        = teams.size() % 2 != 0 ? teams.size()/2+1 : teams.size()/2;
        int bracketSize         = (int)Math.ceil(Math.log(startMatches) / Math.log(2));
        this.bracket            = new Bracket(bracketSize, loosersBracket);
        this.bracketModel       = new BracketModel(teams);
        bracketModel.initMatchModel(bracketSize, loosersBracket);
        bracketModel.initTeamsInMatchModel();
        initBracketView();
        updateViewFromModel();
        loadMatchRectanglEvents();

    }


    private void loadMatchRectanglEvents() {
        for(String key : bracketModel.getMatches().keySet()) {
            bracket.getMatch(key).setOnMousePressed(event -> {
                bracket.getMatch(key).setGlow(true);
            });
            bracket.getMatch(key).setOnMouseReleased(event -> {
                bracket.getMatch(key).setGlow(false);
                if(!drag) {
                    //Do stuff
                    //System.out.println("fire event: " + key);
                }
                drag = false;
            });
        }
    }


    /**
     * loads drag and drop, and zoom events in bracket.
     */
    private void loadBracketEvents() {
        //DragNDrop
        bracket.setOnMousePressed(event ->  {
            bracket.deltaX = bracket.getTranslateX() - event.getSceneX();
            bracket.deltaY = bracket.getTranslateY() - event.getSceneY();
        });

        bracket.setOnMouseDragged(event ->  {
            bracket.setTranslateX(event.getSceneX() + bracket.deltaX);
            bracket.setTranslateY(event.getSceneY() + bracket.deltaY);
            drag = true;
        });

        //Zoom
        bracket.setOnScroll(event -> {
            if(bracket.getScaleX() > 0.15 || event.getDeltaY() > 0) {
                bracket.setScaleX(bracket.getScaleX()+(event.getDeltaY()/1000)*bracket.getScaleX());
                bracket.setScaleY(bracket.getScaleY()+(event.getDeltaY()/1000)*bracket.getScaleY());
            }
        });
    }

    /**
     * initialize bracket view
     */
    private void initBracketView() {
        this.bracket.load();
        main.addNodeToRootPane(this.bracket);
        loadBracketEvents();
    }

    /**
     * update bracket view from model
     */
    private void updateViewFromModel() {
        for(Match match : bracketModel.getMatches().values()) {
            bracket.getMatch(match.getMatchLabel()).update(match);
        }
    }


}
