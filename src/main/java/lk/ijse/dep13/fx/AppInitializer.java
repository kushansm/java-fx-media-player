package lk.ijse.dep13.fx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lk.ijse.dep13.fx.util.AppRouter;

public class AppInitializer extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene mainscene = new Scene(AppRouter.getContainer(AppRouter.Routes.MAIN));
        primaryStage.setScene(mainscene);
        primaryStage.setTitle("JavaFX-Media-Player");
        primaryStage.show();
    }
}

