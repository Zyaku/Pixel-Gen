import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class View {

	private Model model;
	private Stage stage;
	private double width;
	private double height;
	private PixelWriter writer;
	private Pane root;
	private Scene scene;
	
	public View(Model model, Stage stage) {
		
		this.model = model;
		this.stage = stage;
		this.width = model.getWidth();
		this.height = model.getHeight();
		
		Canvas canvas = new Canvas(width,height);
		canvas.setWidth(width);
		canvas.setHeight(height);
		
		GraphicsContext gc = canvas.getGraphicsContext2D();
		root = new Pane();
		root.getChildren().add(canvas);
		scene = new Scene(root);
		this.stage.setScene(scene);
		this.writer = gc.getPixelWriter();
		
	}
	
	public void drawCanvas() {
		for ( int y = 0; y < this.height; y ++) {
			for (int x = 0; x < this.width; x++) {
				Color color = (model.getPixel(x, y) != null) ? model.getPixel(x, y) : Color.WHITE;
				this.writer.setColor(x, y, color);
			}
		}
	}
	
	public Pane getRoot() {
		return this.root;
	}
	
	public Scene getScene() {
		return this.scene;
	}
	

	public void captureAndSaveDisplay(Pane root){
	    FileChooser fileChooser = new FileChooser();

	    //Set extension filter
	    fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("png files (*.png)", "*.png"));

	    //Prompt user to select a file
	    File file = fileChooser.showSaveDialog(null);
	    
	    if(file != null){
	        try {
	            //Pad the capture area
	            WritableImage writableImage = new WritableImage((int) model.getWidth(), (int) model.getHeight());
	            root.snapshot(null, writableImage);
	            RenderedImage renderedImage = SwingFXUtils.fromFXImage(writableImage, null);
	            //Write the snapshot to the chosen file
	            ImageIO.write(renderedImage, "png", file);
	        } catch (IOException ex) { ex.printStackTrace(); }
	    }
	}
	
}