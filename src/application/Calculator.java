package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Calculator extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("Calculator.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("calculator.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Calculator");
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
