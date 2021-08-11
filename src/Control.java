import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class Control {
	
	private Model model;
	private View view;
	private Pane root; 
	private int radius = 100;
	
	public Control(Model model, View view) {
		
		this.model = model;
		this.view = view;
		this.root = view.getRoot();
		
		setMouseLeftClick(root,view);
		//setMouseLeftClick(this.root,this.model);
		setScreenshot (this.root, this.view);
		
	}
	
	
	
	public void setScreenshot (Pane root, View view) {
		
		view.getScene().setOnKeyPressed(e -> {
		    if (e.getCode() == KeyCode.SPACE) {
		        view.captureAndSaveDisplay(root);
		    }
		});
		
	}
	
	public void setMouseLeftClick(Pane root , View view) {
		
		view.getScene().setOnMousePressed(( e -> {
			if (e.isPrimaryButtonDown()) {
				   model.setWhiteCircle(e.getSceneX(), e.getSceneY(), radius);
			}
		}));
		
	}
	
	public void setMouseLeftClick(Pane root, Model model) {
		
		root.setOnMouseClicked(new EventHandler<MouseEvent>() {
		    @Override
		    public void handle(MouseEvent event) {
		        model.setWhiteCircle(event.getSceneX(), event.getSceneY(), radius);
		    }
		});	
	}
	
}