package no.eska.compomaster.Start.View;

import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import no.eska.compomaster.Resources;
import no.eska.compomaster.guicomponents.Button;
import no.eska.compomaster.guicomponents.ScrollList;

/**
 * Created by Eska on 10.04.2016.
 */
public class StartView extends StackPane{

    private Font h1;
    private int yOffset = 75;
    private int borderThickness = 1;

    public ScrollList teams;
    public ScrollList maps;
    public TextField compoName;
    public TextField mapName;
    public TextField teamName;
    public Button addTeam;
    public Button addMap;
    public Button launch;
    public CheckBox loserbracket;

    public StartView() {
        this.setAlignment(Pos.TOP_LEFT);
        this.h1 = Resources.getCsgoFont(25);
        this.setOpacity(0.7);
        this.getStylesheets().add("resources/css/startStyle.css");
        load();
    }

    /**
     * loads headline, teams- and maps pane and footer.
     */
    public void load() {

        loadheadLine();
        loadTeamsScrollPane();
        loadMapsScrollPane();
        loadFooter();

        //tests
        teams.addListElement(new ListElement(497, 50, "NiP", h1));
        teams.addListElement(new ListElement(497, 50, "NoA", h1));
        teams.addListElement(new ListElement(497, 50, "Catch", h1));
        teams.addListElement(new ListElement(497, 50, "Fnatic", h1));
        teams.addListElement(new ListElement(497, 50, "Envy", h1));
        teams.addListElement(new ListElement(497, 50, "Karasjok", h1));
        teams.addListElement(new ListElement(497, 50, "Kraftlaget", h1));
        teams.addListElement(new ListElement(497, 50, "Team Eska", h1));

        maps.addListElement(new ListElement(497, 50, "Dust2", h1));
        maps.addListElement(new ListElement(497, 50, "Cache", h1));
        maps.addListElement(new ListElement(497, 50, "Inferno", h1));
        maps.addListElement(new ListElement(497, 50, "Mirage", h1));
        maps.addListElement(new ListElement(497, 50, "Overpass", h1));
        maps.addListElement(new ListElement(497, 50, "Cobblestone", h1));
        maps.addListElement(new ListElement(497, 50, "Nuke", h1));

    }

    /**
     * loads Headline
     */
    public void loadheadLine() {
        Rectangle r = new Rectangle(999, 50);
        r.setTranslateY(yOffset);
        r.setFill(Resources.getGray());
        r.setStrokeWidth(borderThickness);
        r.setStroke(Resources.getWhite());
        Text t = new Text("Compo name: ");
        t.setFill(Resources.getWhite());
        t.setFont(h1);
        t.setTranslateX(150);
        t.setTranslateY(20+yOffset);
        compoName = new TextField();
        compoName.setTranslateY(yOffset+5);
        compoName.setTranslateX(287);
        compoName.setMaxSize(400, 30);
        compoName.setFont(h1);
        this.getChildren().addAll(r, t, compoName);
    }

    /**
     * loads teams scroll pane
     */
    public void loadTeamsScrollPane() {
        Rectangle r = new Rectangle(499, 50);
        r.setFill(Resources.getGray());
        r.setStrokeWidth(borderThickness);
        r.setStroke(Resources.getWhite());
        r.setTranslateY(125);
        Text t = new Text("Team: ");
        t.setFont(h1);
        t.setTranslateX(50);
        t.setTranslateY(145);
        t.setFill(Resources.getWhite());
        teamName = new TextField();
        teamName.setMaxSize(300, 30);
        teamName.setTranslateX(110);
        teamName.setTranslateY(130);
        teamName.setFont(h1);
        addTeam = new Button(80, 50, 419, 125, "Add", h1, Resources.getGray(), Resources.getWhite(), Resources.getWhite());

        teams = new ScrollList(500, 500, 0, 175);
        this.getChildren().addAll(r, t, teamName, addTeam, teams);

    }

    /**
     * loads maps scroll pane
     */
    public void loadMapsScrollPane() {
        //add map
        Rectangle r = new Rectangle(499, 50);
        r.setFill(Resources.getGray());
        r.setStrokeWidth(borderThickness);
        r.setStroke(Resources.getWhite());
        r.setTranslateY(125);
        r.setTranslateX(500);
        Text t = new Text("Map: ");
        t.setFont(h1);
        t.setTranslateX(550);
        t.setTranslateY(145);
        t.setFill(Resources.getWhite());
        mapName = new TextField();
        mapName.setMaxSize(300, 30);
        mapName.setTranslateX(600);
        mapName.setTranslateY(130);
        mapName.setFont(h1);
        addMap = new Button(80, 50, 919, 125, "Add", h1, Resources.getGray(), Resources.getWhite(), Resources.getWhite());
        maps = new ScrollList(500, 500, 500, 175);
        this.getChildren().addAll(r, t, mapName, addMap, maps);
    }

    /**
     * loads footer
     */
    public void loadFooter() {
        Rectangle r = new Rectangle(999, 50);
        r.setTranslateY(645);
        r.setFill(Resources.getGray());
        r.setStrokeWidth(borderThickness);
        r.setStroke(Resources.getWhite());
        launch = new Button(120, 50, 879, 645, "Launch", h1, Resources.getGray(), Resources.getWhite(), Resources.getWhite());
        loserbracket = new CheckBox("Losers bracket?");
        loserbracket.setFont(h1);
        loserbracket.setTextFill(Resources.getWhite());
        loserbracket.setTranslateY(653);
        loserbracket.setTranslateX(20);
        this.getChildren().addAll(r, launch, loserbracket);
    }
}
