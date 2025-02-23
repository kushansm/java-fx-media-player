package lk.ijse.dep13.fx.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class AppRouter {
    public enum Routes {
        MAIN;
    }

    public static AnchorPane getContainer(Routes route) throws IOException {
        AnchorPane container=null;
        if (route == Routes.MAIN) {
            try {
                container = FXMLLoader.load(AppRouter.class.getResource("/scene/MainScene.fxml"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return container;

    }
}
