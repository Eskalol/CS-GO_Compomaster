package compomaster;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;

/**
 * Created by Eska on 03.03.2016.
 */
public class Resources {
    public static Font getCsgoFont(int size) {
        return Font.loadFont(Resources.class.getResource("/resources/font/cs_regular.ttf").toExternalForm(), size);
    }

    public static ImageView getImageView(String name) {
        return new ImageView(new Image(Resources.class.getResource("/resources/img/" + name).toExternalForm()));
    }
}
