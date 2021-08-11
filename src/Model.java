import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import javafx.scene.paint.Color;



public class Model {
	
	private double width;
	private double height;
	private Color [][] painting;
	private ArrayList<Point> currentExpansion = new ArrayList <Point>();
	private ArrayList <Point> newExpansion = new ArrayList <Point>();
	private double colorChange;
	
	
	Model(double width,double height, double colorChange){
		
		this.width = width;
		this.height = height;
		this.colorChange = colorChange;
		this.painting = new Color [(int) this.width][ (int) this.height];
		setStartingPoint();
		
	}
	
	
	public void setStartingPoint() {
		this.painting[(int) width/2][(int) height/2] = new Color(0.5,0.5,0.5,1);
		Point p = new Point((int) width/2, (int) height/2);
		this.currentExpansion.add(p);

	}
	
	
	public void updateIteratively() {
		
		
		for (Point p : this.currentExpansion) {
			double rx = nextDouble (- this.colorChange, this.colorChange);
			double gx = nextDouble (- this.colorChange, this.colorChange);
			double bx  = nextDouble (- this.colorChange, this.colorChange);
			
			addNeighborIteratively((int) p.getX(),(int) p.getY() ,rx,gx,bx);
			
		}
		
		this.currentExpansion.addAll(newExpansion);
		this.newExpansion.clear();
	
	}
	
	public void updateConditionally() {
		
		int next = 0;
		
		while (next < this.currentExpansion.size()) {
			
			Point p = this.currentExpansion.get(next);
			double rx = nextDouble (- this.colorChange, this.colorChange);
			double gx = nextDouble (- this.colorChange, this.colorChange);
			double bx  = nextDouble (- this.colorChange, this.colorChange);
			
			addNeighborConditionally((int) p.getX(),(int) p.getY() ,rx,gx,bx);
			next ++;
			
		}
		
	}
	
	
	
	
	
	public void addNeighborIteratively(int x, int y, double rx, double gx , double bx) {
		
		if (this.painting[x][y] != null) {
		double red = Math.abs(this.painting[x][y].getRed() + rx);
		double r =  red > 1 ? 1 : red < 0 ? 0 : red;
		double green = Math.abs(this.painting[x][y].getGreen() + gx);
		double g =  green > 1 ? 1 : green < 0 ? 0 : green;
		double blue = Math.abs(this.painting[x][y].getBlue() + bx);
		double b =  blue > 1 ? 1 : blue < 0 ? 0 : blue;
		
		Color color = new Color (r,g,b,1);
	
			if ( y-1 > 0 && this.painting[x][y-1] == null  && getRandomBoolean() ) {
				this.painting[x][y-1] = color; 
				this.newExpansion.add(new Point(x,y-1));
				
				
			}
			if ( x+1 < this.width && this.painting[x+1][y] == null && getRandomBoolean() ) {
				this.painting[x+1][y] = color; 
				this.newExpansion.add(new Point(x+1,y));
				
			}
			if ( y+1 < this.height && this.painting[x][y+1] == null && getRandomBoolean() ) {
				this.painting[x][y+1] = color; 
				this.newExpansion.add(new Point(x,y+1));
	
			}
			if ( x-1 > 0 && painting[x-1][y] == null && getRandomBoolean() ) {
				painting[x-1][y] = color; 
				this.newExpansion.add(new Point(x-1,y));
			}
		}
	}
	
	
	public void addNeighborConditionally(int x, int y, double rx, double gx , double bx) {
		
		if (this.painting[x][y] != null) {
		double red = Math.abs(this.painting[x][y].getRed() + rx);
		double r =  red > 1 ? 1 : red < 0 ? 0 : red;
		double green = Math.abs(this.painting[x][y].getGreen() + gx);
		double g =  green > 1 ? 1 : green < 0 ? 0 : green;
		double blue = Math.abs(this.painting[x][y].getBlue() + bx);
		double b =  blue > 1 ? 1 : blue < 0 ? 0 : blue;
		
		Color color = new Color (r,g,b,1);
	
			if ( y-1 > 0 && this.painting[x][y-1] == null  && getRandomBoolean() ) {
				this.painting[x][y-1] = color; 
				this.currentExpansion.add(new Point(x,y-1));
				
			}
			if ( x+1 < this.width && this.painting[x+1][y] == null && getRandomBoolean() ) {
				this.painting[x+1][y] = color; 
				this.currentExpansion.add(new Point(x+1,y));
			}
			if ( y+1 < this.height && this.painting[x][y+1] == null && getRandomBoolean() ) {
				this.painting[x][y+1] = color; 
				this.currentExpansion.add(new Point(x,y+1));
			}
			if ( x-1 > 0 && painting[x-1][y] == null && getRandomBoolean() ) {
				painting[x-1][y] = color; 
				this.currentExpansion.add(new Point(x-1,y));
			}
			
		}
		
		
	}
	
	
	public void addNeighborByOne(int x, int y, double rx, double gx , double bx) {
		
		if (this.painting[x][y] != null) {
		double red = Math.abs(this.painting[x][y].getRed() + rx);
		double r =  red > 1 ? 1 : red < 0 ? 0 : red;
		double green = Math.abs(this.painting[x][y].getGreen() + gx);
		double g =  green > 1 ? 1 : green < 0 ? 0 : green;
		double blue = Math.abs(this.painting[x][y].getBlue() + bx);
		double b =  blue > 1 ? 1 : blue < 0 ? 0 : blue;
		int path = (int) (Math.random() * 4);		
		
		Color color = new Color (r,g,b,1);
	
			if ( y-1 > 0 && this.painting[x][y-1] == null  && path == 0 ) {
				this.painting[x][y-1] = color; 
//				this.newExpansion.add(new Point(x,y-1));
				this.currentExpansion.add(new Point(x,y-1));
			
			}
			if ( x+1 < this.width && this.painting[x+1][y] == null && path == 1 ) {
				this.painting[x+1][y] = color; 
//				this.newExpansion.add(new Point(x+1,y));
				this.currentExpansion.add(new Point(x+1,y));
			}
			if ( y+1 < this.height && this.painting[x][y+1] == null && path == 2 ) {
				this.painting[x][y+1] = color; 
//				this.newExpansion.add(new Point(x,y+1));
				this.currentExpansion.add(new Point(x,y+1));
			}
			if ( x-1 > 0 && painting[x-1][y] == null && path == 3 ) {
				painting[x-1][y] = color; 
//				this.newExpansion.add(new Point(x-1,y));
				this.currentExpansion.add(new Point(x-1,y));
			}
			
		}
	}
	
	
	public void setWhiteCircle(double x, double y, int radius) {
		
		int minWidth = (int) ((x - radius -1 ) < 0 ? 0 : (x - radius -1 )) ;
		int maxWidth = (int) ((x + radius +1 ) > this.height ? this.height : (x + radius + 1 )) ;
		int minHeight = (int) ((y - radius -1 ) < 0 ? 0 : (y - radius -1 )) ;
		int maxHeight = (int) ((y +  radius + 1 ) > this.width ? this.width : (y + radius + 1 )) ;
		
		for (int i = minHeight ; i < maxHeight  ;i++) {
			for (int j = minWidth; j < maxWidth ; j++ ) {
				
				int distance = (int) euclDistance(x,y,j,i);
				
				if ( distance < radius) {
					this.painting[j][i] = null;
					
				}else {
					this.newExpansion.add(new Point(i,j));
				}
			}
		}
	}
	
	
	
	public double euclDistance (double x1,double y1 , double x2, double y2) {
		
		double difX = x1 - x2;
		double difY = y1 - y2;
		return Math.sqrt( (difX * difX) + (difY * difY));
	}
	
	
	public static double nextDouble (double min, double max) {
		 
	        return (ThreadLocalRandom.current().nextDouble() * (max - min)) + min;
	 }
	
	
	public boolean getRandomBoolean() {
		 
		    Random random = new Random();
		    return random.nextBoolean();
	}
	
	 
	public Color getPixel(int x, int y) {
		return painting[x][y];
	}
	
	
	public double getWidth() {
		return width;
	}
	
	
	public double getHeight() {
		return height;
	}
}