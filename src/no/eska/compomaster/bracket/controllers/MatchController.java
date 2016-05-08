package no.eska.compomaster.bracket.controllers;

import no.eska.compomaster.Main;
import no.eska.compomaster.bracket.models.Map;
import no.eska.compomaster.bracket.models.Match;
import no.eska.compomaster.bracket.views.MapListElement;
import no.eska.compomaster.bracket.views.MatchView;

import java.util.ArrayList;

/**
 * Created by Eska on 14.04.2016.
 */
public class MatchController {

    private MatchView matchView;
    private Match match;


    public MatchController(int width, ArrayList<String> maps) {
        this.matchView  = new MatchView(width, maps);

        loadAddEvent();
        loadBestOfEvent();
    }


    /**
     * sets match model
     * @param match
     */
    public void setMatch(Match match) {
        this.match = match;
    }


    /**
     * updates the view
     */
    public void updateView() {
        this.matchView.active = true;
        this.matchView.update(match.getTeam1() == null ? "TBA" : match.getTeam1().getName(), match.getTeam2() == null ? "TBA" : match.getTeam2().getName(), match.getTeam1Wins(), match.getTeam2Wins());
        for(Map m : match.maps) {
            MapListElement ml = new MapListElement(m.getMapName(), m.getTeam1Wins()+"", m.getTeam2Wins()+"");
            matchView.mapList.addListElement(ml);
            ml.setOnRemove(event -> {
                matchView.mapList.removeListElement(ml);
                match.maps.remove(m);
            });
        }
        switch (match.getBestOf()) {
            case 1: matchView.bo.setValue("BO1"); break;
            case 3: matchView.bo.setValue("BO3"); break;
            case 5: matchView.bo.setValue("BO5"); break;
            default: break;
        }
    }


    /**
     * returns the match view
     * @return
     */
    public MatchView getView() {
        return this.matchView;
    }


    /**
     * loads add event.
     */
    private void loadAddEvent() {
        matchView.add.setOnMouseClicked(event -> {
            if(matchView.isValid()) {
                MapListElement ml = new MapListElement(matchView.map.getValue(), matchView.team1Input.getText(), matchView.team2Input.getText());
                matchView.mapList.addListElement(ml);
                Map m = new Map(matchView.map.getValue(), Integer.parseInt(matchView.team1Input.getText()), Integer.parseInt(matchView.team2Input.getText()));
                match.maps.add(m);
                ml.setOnRemove(event1 -> {
                    matchView.mapList.removeListElement(ml);
                    match.maps.remove(m);
                });

                matchView.map.setValue("");
                matchView.team1Input.setText("");
                matchView.team2Input.setText("");
            }
        });
    }

    private void loadBestOfEvent() {
        matchView.bo.valueProperty().addListener((obs, old, newValue) -> {
            switch (newValue) {
                case "BO1" : match.setBestOf(1); break;
                case "BO3" : match.setBestOf(3); break;
                case "BO5" : match.setBestOf(5); break;
                default: break;
            }
        });
    }


}
