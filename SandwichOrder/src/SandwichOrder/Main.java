package SandwichOrder;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;

/**
 * This is the main class that runs the project and sets the inital window size and title.
 * @author Sandeep Alankar, Graham Deubner
 *
 */
public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			primaryStage.setTitle("Order Manager");
			primaryStage.show();
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("OrderManager.fxml"));
			Scene scene = new Scene(root,550,550);
			primaryStage.setResizable(false);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
