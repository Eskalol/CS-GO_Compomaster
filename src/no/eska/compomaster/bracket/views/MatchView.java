package no.eska.compomaster.bracket.views;

import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import no.eska.compomaster.Resources;
import no.eska.compomaster.guicomponents.Button;
import no.eska.compomaster.guicomponents.ScrollList;

import java.util.ArrayList;

/**
 * Created by Eska on 14.04.2016.
 */
public class MatchView extends StackPane {
    public Button add;
    public ScrollList mapList;
    public boolean active = false;

    public TextField team1Input;
    public TextField team2Input;

    private Text error;
    private Text team1Win;
    private Text team2Win;

    private Text team1Name;
    private Text team2Name;

    private Font font;

    public CheckBox t1WO;
    public CheckBox t2WO;

    public ComboBox<String> map;
    public ComboBox<String> bo;


    public MatchView(int width, ArrayList<String> maps) {
        this.setTranslateX(width);
        this.setWidth(600);
        this.setAlignment(Pos.TOP_LEFT);
        this.setOpacity(0.7);
        this.getStylesheets().add(Resources.getResource("css/matchStyle.css"));

        this.map = new ComboBox<>(FXCollections.observableList(maps));
        loadBackground();
        loadAdd();
        loadFooter();
        loadText();
        WOBox();
        loadMapsChoice();
    }

    /**
     * load background
     */
    private void loadBackground() {
        Rectangle r1 = new Rectangle(250, 50);
        r1.setFill(Resources.getOrange());
        r1.setStroke(Resources.getWhite());
        r1.setStrokeWidth(1);
        r1.setTranslateX(0);
        r1.setTranslateY(75);

        Rectangle r2 = new Rectangle(50, 50);
        r2.setFill(Resources.getGray());
        r2.setStroke(Resources.getWhite());
        r2.setStrokeWidth(1);
        r2.setTranslateX(250);
        r2.setTranslateY(75);

        Rectangle r3 = new Rectangle(50, 50);
        r3.setFill(Resources.getGray());
        r3.setStroke(Resources.getWhite());
        r3.setStrokeWidth(1);
        r3.setTranslateX(300);
        r3.setTranslateY(75);

        Rectangle r4 = new Rectangle(250, 50);
        r4.setFill(Resources.getWhite());
        r4.setStroke(Resources.getWhite());
        r4.setStrokeWidth(1);
        r4.setTranslateX(350);
        r4.setTranslateY(75);

        this.getChildren().addAll(r1, r2, r3, r4);
    }


    /**
     * loads add
     */
    public void loadAdd() {
        Rectangle r = new Rectangle(600, 50);
        r.setStrokeWidth(1);
        r.setStroke(Resources.getWhite());
        r.setFill(Resources.getGray());
        r.setTranslateY(125);
        add = new Button(80, 50, 520, 125, "Add", Resources.getCsgoFont(25), Resources.getGray(), Resources.getWhite(), Resources.getWhite());

        Rectangle r1 = new Rectangle(50, 50);
        r1.setStrokeWidth(1);
        r1.setStroke(Resources.getWhite());
        r1.setFill(Resources.getOrange());
        r1.setTranslateY(125);
        r1.setTranslateX(100);

        Rectangle r2 = new Rectangle(50, 50);
        r2.setStrokeWidth(1);
        r2.setStroke(Resources.getWhite());
        r2.setFill(Resources.getWhite());
        r2.setTranslateY(125);
        r2.setTranslateX(250);

        Text t1 = new Text("Orange wins");
        t1.setFill(Resources.getWhite());
        t1.setFont(Resources.getCsgoFont(16));
        t1.setTranslateX(5);
        t1.setTranslateY(145);

        Text t2 = new Text("White wins");
        t2.setFill(Resources.getWhite());
        t2.setFont(Resources.getCsgoFont(16));
        t2.setTranslateX(155);
        t2.setTranslateY(145);


        this.team1Input = new TextField("");
        this.team1Input.setFont(Resources.getCsgoFont(25));

        this.team1Input.setTranslateX(93);
        this.team1Input.setTranslateY(125);
        this.team1Input.setMinSize(70, 50);
        this.team1Input.setMaxSize(70, 50);


        this.team2Input = new TextField("");
        this.team2Input.setFont(Resources.getCsgoFont(25));

        this.team2Input.setTranslateX(243);
        this.team2Input.setTranslateY(125);
        this.team2Input.setMinSize(70, 50);
        this.team2Input.setMaxSize(70, 50);


        this.mapList = new ScrollList(601, 150, 0, 175);
        this.getChildren().addAll(r, r1, r2, t1, t2, team1Input, team2Input, add, mapList);
    }


    /**
     * loads footer
     */
    public void loadFooter() {
        Rectangle r = new Rectangle(600, 50);
        r.setFill(Resources.getGray());
        r.setStrokeWidth(1);
        r.setStroke(Resources.getWhite());
        r.setTranslateY(325);
        this.bo = new ComboBox<>(FXCollections.observableArrayList("BO1", "BO3", "BO5"));
        this.bo.setTranslateY(325);
        this.bo.setTranslateX(450);
        this.bo.setPrefSize(150, 51);

        error = new Text();
        error.setFill(Color.RED);
        error.setFont(Resources.getCsgoFont(16));
        error.setTranslateY(345);
        error.setTranslateX(280);
        error.maxWidth(90);

        this.getChildren().addAll(r, this.bo, error);
    }


    /**
     * load text
     */
    public void loadText() {
        font = Resources.getCsgoFont(25);
        team1Name = new Text();
        team1Name.setFont(font);
        team1Name.setFill(Resources.getGray());
        team1Name.setTranslateY(25-font.getSize()/3+75);

        team2Name = new Text();
        team2Name.setFont(font);
        team2Name.setFill(Resources.getGray());
        team2Name.setTranslateY(25-font.getSize()/3+75);

        team1Win = new Text();
        team1Win.setFont(font);
        team1Win.setFill(Resources.getWhite());
        team1Win.setTranslateY(25-font.getSize()/3+75);

        team2Win = new Text();
        team2Win.setFont(font);
        team2Win.setFill(Resources.getWhite());
        team2Win.setTranslateY(25-font.getSize()/3+75);

        this.getChildren().addAll(team1Name, team2Name, team1Win, team2Win);
    }

    /**
     * load walk over box
     */
    public void WOBox() {
        t1WO = new CheckBox();
        t1WO.setFont(Resources.getCsgoFont(18));
        t1WO.setText("WO orange");
        t1WO.setTextFill(Resources.getWhite());
        t1WO.setTranslateY(337);
        t1WO.setTranslateX(10);


        t2WO = new CheckBox();
        t2WO.setFont(Resources.getCsgoFont(18));
        t2WO.setText("WO white");
        t2WO.setTextFill(Resources.getWhite());
        t2WO.setTranslateY(337);
        t2WO.setTranslateX(150);

        this.getChildren().addAll(t1WO, t2WO);
    }

    /**
     * load map choice
     */
    public void loadMapsChoice() {
        map.setTranslateY(125);
        map.setTranslateX(300);
        map.setPrefSize(220, 51);

        this.getChildren().add(map);
    }

    /**
     *
     * @param team1Name team1 name
     * @param team2Name team2 name
     * @param team1Wins team1 wins
     * @param team2Wins team2 wins
     */
    public void update(String team1Name, String team2Name, int team1Wins, int team2Wins) {
        this.mapList.clear();
        this.team1Name.setText(team1Name);
        this.team1Name.setTranslateX(250/2-(team1Name.length()*font.getSize())/4);

        this.team2Name.setText(team2Name);
        this.team2Name.setTranslateX(250/2-(team2Name.length()*font.getSize())/4+350);

        this.team1Win.setText(team1Wins < 0 ? "WO" : team1Wins+"");
        this.team1Win.setTranslateX(50/2- ((team1Wins < 0 ? "WO" : team1Wins+"").length()*font.getSize())/3+250);
        if(team1Wins < 0)
            t1WO.setSelected(true);

        this.team2Win.setText(team2Wins < 0 ? "WO" : team2Wins+"");
        this.team2Win.setTranslateX(50/2- ((team2Wins < 0 ? "WO" : team2Wins+"").length()*font.getSize())/3+300);
        if(team2Wins < 0)
            t2WO.setSelected(true);

    }



    /**
     * translates to center if active
     * @param width
     */
    public void translateCenter(double width) {
        this.setTranslateX(width/2-300+(active ? 0 : width));
    }

    /**
     * check if add map is valid
     * @return
     */
    public boolean isValid() {
        error.setText("");
        if(team1Input.getText().length() < 1 || team2Input.getText().length() < 1 || map.getValue() == null || map.getValue().length() < 1) {
            error.setText("Enter wins and map!");
            return false;
        }
        return  true;
    }
}
