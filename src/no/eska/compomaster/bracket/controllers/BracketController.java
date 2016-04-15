package no.eska.compomaster.bracket.controllers;

import javafx.animation.TranslateTransition;
import javafx.util.Duration;
import no.eska.compomaster.Main;
import no.eska.compomaster.bracket.models.BracketModel;
import no.eska.compomaster.bracket.models.Match;
import no.eska.compomaster.bracket.models.Team;
import no.eska.compomaster.bracket.views.Bracket;
import no.eska.compomaster.bracket.views.MatchView;


import java.util.ArrayList;

/**
 * Created by Eska on 09.04.2016.
 */
public class BracketController {

    private Main main;

    private Bracket bracket;
    private BracketModel bracketModel;

    private boolean drag = false;

    private MatchController mc;

    public BracketController(Main main) {
        this.main               = main;
    }

    public void launch(ArrayList<String> t, ArrayList<String> maps, boolean losersbracket) {
        ArrayList<Team> teams = new ArrayList<>();
        for(String s : t)
            teams.add(new Team(s));

        int startMatches        = teams.size() % 2 != 0 ? teams.size()/2+1 : teams.size()/2;
        int bracketSize         = (int)Math.ceil(Math.log(startMatches) / Math.log(2));
        this.bracket            = new Bracket(bracketSize, losersbracket);
        this.bracketModel       = new BracketModel(teams, maps);
        this.mc                 = new MatchController(main.getWidth(), maps);
        bracketModel.initMatchModel(bracketSize, losersbracket);
        bracketModel.initTeamsInMatchModel();
        initBracketView();
        updateViewFromModel();
        loadMatchRectanglEvents();
        main.addNodeToRootPane(this.mc.getView());
        loadBackEvent();
    }


    private void loadMatchRectanglEvents() {
        for(String key : bracketModel.getMatches().keySet()) {
            bracket.getMatch(key).setOnMousePressed(event -> {
                bracket.getMatch(key).setGlow(true);
            });
            bracket.getMatch(key).setOnMouseReleased(event -> {
                bracket.getMatch(key).setGlow(false);
                if(!drag) {
                    mc.setMatch(bracketModel.getMatches().get(key));
                    mc.updateView();
                    main.hl.setText(key.substring(0, 2).equalsIgnoreCase("gf") ? "Grand final" : (key.substring(0, 2).equalsIgnoreCase("wb") ? "Winners bracket match " : "Losers bracket match ")+key.substring(2));
                    main.hl.enableBack(true);
                    TranslateTransition tt = new TranslateTransition(Duration.seconds(0.5), mc.getView());
                    tt.setToX(main.getWidth()/2-300);
                    TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), bracket);
                    tt1.setToX(-bracket.getWidth()-main.getWidth());
                    tt1.play();
                    tt.play();

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

    public Bracket getBracket() {
        return this.bracket;
    }


    /**
     * returns matchView.
     * @return
     */
    public MatchView getMatchView() {
        return this.mc.getView();
    }


    private void loadBackEvent() {
        this.main.hl.getBack().setOnMouseClicked(event -> {
            TranslateTransition tt = new TranslateTransition(Duration.seconds(0.5), mc.getView());
            tt.setToX(this.main.getWidth());
            TranslateTransition t1 = new TranslateTransition(Duration.seconds(0.5), bracket);
            t1.setToX(5);

            tt.play();
            t1.play();
            mc.getView().active = false;
            this.main.hl.enableBack(false);
            this.main.hl.setText(this.main.sc.getCompoName());
        });
    }
}
