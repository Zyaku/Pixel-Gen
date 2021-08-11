import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application{
	
	private static String title = "Painting";
	private static double width = 400;
	private static double height = 400;
	private double colorChange = 0.03;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		Model model = new Model(width,height,colorChange);
		View view = new View(model,primaryStage);
		@SuppressWarnings("unused")
		Control control = new Control(model,view);
		
		primaryStage.setTitle(title);
		primaryStage.show();
		
		AnimationTimer loop = new AnimationTimer(){																				// Animation Timer
			
            public void handle(long currentNanoTime){
     
            	model.updateIteratively();
//            	model.updateConditionally();
            	view.drawCanvas();    
            	
            	
            	
            }
		};
		
		loop.start();
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}