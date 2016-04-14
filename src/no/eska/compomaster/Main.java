package no.eska.compomaster;

import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.util.Duration;
import no.eska.compomaster.Headline.view.Headline;
import no.eska.compomaster.bracket.controllers.BracketController;
import no.eska.compomaster.start.views.StartView;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import no.eska.compomaster.start.controllers.StartController;

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

    private StartController sc;
    private StartView sw;
    private BracketController bc;
    private Headline hl;

    /**
     * StartView method
     *
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage   = primaryStage;
        this.height         = 720;
        this.width          = 1280;

        initScene();

        this.primaryStage.setTitle(this.windowTitle);
        this.primaryStage.setScene(this.primaryScene);
        this.primaryStage.setResizable(true);
        this.primaryStage.show();

        loadHeader();
        this.sc = new StartController();
        this.sw = sc.getView();
        sw.setTranslateX(primaryStage.getWidth()/2-500);
        addNodeToRootPane(sw);

        primaryStage.widthProperty().addListener((obs, old, newValue) -> {
            System.out.println(sw.getWidth());
            sw.setTranslateX((newValue.doubleValue()/2-500-sw.translateOffset));
            hl.stretch(newValue.doubleValue());
        });
        loadLaunch();
    }

    private void loadHeader() {
        this.hl = new Headline(primaryStage.getWidth());
        this.hl.setText("CS:GO Compomaster");
        addNodeToRootPane(hl);
    }

    private void loadLaunch() {
        bc = new BracketController(this);
        sc.setOnLaunch(event -> {
            if(!sw.isValid())
                return;
            TranslateTransition tt = new TranslateTransition(Duration.seconds(0.5), sw);
            sw.translateOffset = -primaryStage.getWidth()-500;
            tt.setToX(-primaryStage.getWidth()-1000);

            bc.launch(sc.getTeams(), sc.getLosersBracket());
            TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), bc.getBracket());
            bc.getBracket().setTranslateX(primaryStage.getWidth());
            bc.getBracket().setTranslateY(75);
            tt1.setToX(20);

            tt.play();
            tt1.play();
            hl.setText(sc.getCompoName());
        });
    }

    /**
     * Init primary scene and adds rootPane to scene.
     * adding background image and fits with primaryStage property
     */
    private void initScene() {
        this.rootPane    = new StackPane();
        this.primaryScene   = new Scene(this.rootPane, this.width, this.height);
        this.rootPane.setStyle("-fx-background-color: #444;");

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
