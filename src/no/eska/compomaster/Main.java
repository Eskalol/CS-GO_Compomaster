package no.eska.compomaster;

import no.eska.compomaster.Start.View.StartView;
import no.eska.compomaster.bracket.models.Team;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * Main class
 */
public class Main extends Application implements MainWindow {

    private static final String windowTitle = "CS:GO compomaster v0.0.1";

    private int         height;
    private int         width;
    private Stage       primaryStage;
    private Scene       primaryScene;
    private StackPane   rootPane;


    /**
     * StartView method
     *
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage   = primaryStage;
        this.height         = 720;
        this.width          = 1280;

        initScene();

        this.primaryStage.setTitle(this.windowTitle);
        this.primaryStage.setScene(this.primaryScene);
        this.primaryStage.setResizable(true);
        this.primaryStage.show();

        ArrayList<Team> teams = new ArrayList<>();
        teams.add(new Team("Imba"));
        teams.add(new Team("Karasjok"));
        teams.add(new Team("noobs"));
        teams.add(new Team("NIP"));
        teams.add(new Team("Catch"));
        teams.add(new Team("NoA"));
        teams.add(new Team("LOL"));


        addNodeToRootPane(new StartView());
        //new BracketController(teams, true, this);
        //MatchRectangle rect = new MatchRectangle(400, 400);
        //rect.load();
        //this.addNodeToRootPane(rect);


        //Bracket b = new Bracket(3, true);
        //b.load();
        //this.addNodeToRootPane(b);
        //b.setTranslateY(100);
        //b.setTranslateX(100);

        //this.rootPane.setAlignment(b, Pos.CENTER_LEFT);
        //b.setTranslateX(0);
        //

    }


    /**
     * Init primary scene and adds rootPane to scene.
     * adding background image and fits with primaryStage property
     */
    private void initScene() {
        this.rootPane    = new StackPane();
        this.primaryScene   = new Scene(this.rootPane, this.width, this.height);
        this.rootPane.setStyle("-fx-background-color: #444;");

        //adding background image
        ImageView background = Resources.getImageView("csgoBackground.jpg");
        background.fitWidthProperty().bind(primaryStage.widthProperty());
        background.fitHeightProperty().bind(primaryStage.heightProperty());
        addNodeToRootPane(background);
    }


    /**
     * Add node to Root pane
     *
     * @param node node
     */
    public void addNodeToRootPane(Node node) {
        this.rootPane.getChildren().add(node);
    }


    /**
     * Get window height
     *
     * @return window height
     */
    public int getHeight() {
        return this.height;
    }


    /**
     * Get window width
     *
     * @return window width
     */
    public int getWidth() {
        return this.width;
    }


    public static void main(String[] args) {
        launch(args);
    }

}
