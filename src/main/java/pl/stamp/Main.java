package pl.stamp;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * @author Krzysztof && Adamczyk Bartłomiej Pyk
 * Managing start of application
 */
public class Main extends Application {

    /**
     * Application title
     */
    private final static String TITLE = "Stempel";

    /**
     * Path to FXML with root template
     */
    private final static String rootFXMLPath = "/views/root.fxml";

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource(rootFXMLPath));
        final Controller controller = new Controller();
        loader.setController(controller);
        Parent root = loader.load();
        primaryStage.setTitle(TITLE);
        primaryStage.setScene(new Scene(root));
        primaryStage.addEventHandler(WindowEvent.WINDOW_SHOWN, (e) -> Platform.runLater(controller::handleWindowShownEvent));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    public static String getTITLE() {
        return TITLE;
    }
}
