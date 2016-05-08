package no.eska.compomaster.Headline.view;

        import javafx.geometry.Pos;
        import javafx.scene.layout.StackPane;
        import javafx.scene.shape.Rectangle;
        import javafx.scene.text.Text;
        import no.eska.compomaster.Resources;
        import no.eska.compomaster.guicomponents.Button;
        import sun.security.tools.keytool.Resources_sv;

/**
 * Created by Eska on 12.04.2016.
 */
public class Headline extends StackPane {

    Text content;
    Rectangle bg;
    Button back;


    public Headline(double w) {
        this.setAlignment(Pos.TOP_LEFT);
        this.setOpacity(0.7);
        this.setHeight(73);

        this.back = new Button(150, 73, 1, 1, "Back", Resources.getCsgoFont(35), Resources.getGray(), Resources.getWhite(), Resources.getWhite());
        this.bg = new Rectangle(w, 73);
        this.bg.setFill(Resources.getGray());
        this.bg.setStroke(Resources.getWhite());
        this.bg.setStrokeWidth(1);
        this.bg.setTranslateY(1);
        this.bg.setTranslateX(1);
        this.content = new Text();
        this.content.setTranslateY(25);
        this.content.setFont(Resources.getCsgoFont(35));
        this.content.setFill(Resources.getWhite());
        this.getChildren().addAll(bg, content);
    }


    /**
     * changes header text and translates to middle
     * @param text content
     */
    public void setText(String text) {
        content.setText(text);
        content.setTranslateX(bg.getWidth()/2-(this.content.getText().length()*35)/4);
    }


    /**
     * enables or disables the back button
     * @param bool
     */
    public void enableBack(boolean bool) {
        if(bool)
            this.getChildren().add(back);
        else
            this.getChildren().remove(back);
    }

    public void stretch(double width) {
        this.bg.setWidth(width);
        content.setTranslateX(bg.getWidth()/2-(this.content.getText().length()*35)/4);
    }


    /**
     * returns the back button
     * @return
     */
    public Button getBack() {
        return this.back;
    }
}
