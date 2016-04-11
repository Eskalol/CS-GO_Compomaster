package no.eska.compomaster.start.controllers;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import no.eska.compomaster.MainWindow;
import no.eska.compomaster.Resources;
import no.eska.compomaster.start.views.ListElement;
import no.eska.compomaster.start.views.StartView;

import java.util.ArrayList;

/**
 * Created by Eska on 11.04.2016.
 */
public class StartController {
    private StartView view;

    private ArrayList<String> maps;
    private ArrayList<String> teams;
    private String compoName = "";

    public StartController() {
        view = new StartView();
        maps = new ArrayList<>();
        teams = new ArrayList<>();

        load();
    }

    private void load() {
        loadAddTeamEvent();
        loadAddMapEvent();
        loadCompNameEvent();
    }




    /**
     * loads componameEvent
     */
    private void loadCompNameEvent() {
        view.compoName.setOnKeyPressed(event -> {
            setCompoName(view.compoName.getText());
            if(event.getCode() == KeyCode.ENTER) {
                view.compoName.deselect();
            }
        });
    }

    /**
     * sets name of compo
     * @param name name of compo
     */
    private void setCompoName(String name) {
        this.compoName = name;
    }
    /**
     * loads add map events
     */
    private void loadAddMapEvent() {
        view.addMap.setOnMouseClicked(event -> {
            onAddMap();
        });

        view.mapName.setOnKeyPressed(event -> {
            if(event.getCode().equals(KeyCode.ENTER)) {
                onAddMap();
            }
        });
    }


    /**
     * fires when addmap clicked and mapName enter event
     */
    private void onAddMap() {
        if(view.mapName.getText().length() == 0) return;
        System.out.println("lol");
        ListElement map = new ListElement(498, 50, view.mapName.getText(), Resources.getCsgoFont(25));
        maps.add(view.mapName.getText());
        view.mapName.clear();
        view.maps.addListElement(map);
        map.setOnRemove(event -> {
            view.maps.removeListElement(map);
            maps.remove(map.getContent());
        });
    }


    /**
     * loads add team events
     */
    private void loadAddTeamEvent() {
        view.addTeam.setOnMouseClicked(event -> {
            onAddTeam();
        });
        view.teamName.setOnKeyPressed(event -> {
            if(event.getCode().equals(KeyCode.ENTER)) {
                onAddTeam();
            }
        });
    }


    /**
     * fires when add team clicked and teamName filed enter event
     */
    private void onAddTeam() {
        if(view.teamName.getText().length() == 0) return;
        System.out.println("lol");
        ListElement team = new ListElement(498, 50, view.teamName.getText(), Resources.getCsgoFont(25));
        teams.add(view.teamName.getText());
        view.teamName.clear();
        view.teams.addListElement(team);
        team.setOnRemove(event -> {
            view.teams.removeListElement(team);
            teams.remove(team.getContent());
        });
    }


    /**
     * returns the view
     * @return view
     */
    public StartView getView() {
        return this.view;
    }


    /**
     * set launch event
     * @param evt event to fire
     */
    public void setOnLaunch(EventHandler<? super MouseEvent> evt) {
        this.view.launch.setOnMouseClicked(evt);
    }

    /**
     * returns maps list
     * @return maps list
     */
    public ArrayList<String> getMaps() {
        return this.maps;
    }

    /**
     * returns teams list
     * @return teams list
     */
    public ArrayList<String> getTeams() {
        return this.teams;
    }

    /**
     * returns losersbracket bool value
     * @return losersbracket bool
     */
    public Boolean getLosersBracket() {
        return this.view.loserbracket.isSelected();
    }

    /**
     * returns name of compo
     * @return
     */
    public String getCompoName() {
        return this.compoName;
    }
}
