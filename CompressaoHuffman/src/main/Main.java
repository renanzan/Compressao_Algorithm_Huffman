package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.Controller;

public class Main extends Application {

	private static Stage primaryStage;
	
	public static final String NOME_ARQUIVO_ORIGINAL = "arquivo_original.txt";
	public static final String NOME_DO_ARQUIVO_COMPRIMIDO = "arquivo_comprimido";
	
	public static final String textoPadrao = "Lorem ipsum phasellus consectetur amet consectetur porta tempus, vulputate est "
			+ "dictum sociosqu sed primis at ipsum, habitant donec ad adipiscing cursus gravida. in "
			+ "tortor blandit conubia suspendisse taciti pellentesque odio netus praesent nunc cursus, "
			+ "aliquam dapibus quisque laoreet at praesent porta curabitur mi. mollis dictumst morbi "
			+ "cubilia scelerisque rhoncus aenean dictumst at aliquam condimentum, purus "
			+ "suspendisse habitasse vehicula euismod varius praesent ut. blandit felis ipsum hac "
			+ "feugiat lobortis mi, tellus phasellus tempor massa orci ante, tellus eros velit ultricies "
			+ "sapien.";
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Compressão de arquivo");
		
		FXMLLoader loader = new FXMLLoader(Controller.class.getResource("\\FXML_view.fxml"));
		loader.setController(new Controller());
		Parent parent = (Parent) loader.load();
		Scene scene = new Scene(parent);
		
		this.primaryStage.setScene(scene);
		this.primaryStage.sizeToScene();
		this.primaryStage.show();
		
		this.primaryStage.setResizable(false);
	}
	
	public static Stage getPrimaryStage() {
		return primaryStage;
	}

}
