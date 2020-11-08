package Ellipse;

import java.awt.Color;
import java.util.Arrays;

import Graph.Graph;
import Lines.Line;


//First Rotate then Scale otherwise scaling will change the center of rotation
public class Ellipse {
	protected Graph graph;
	protected Color boundary;
	protected Color fill;
	protected Color centreColor;
	protected int x_centre,y_centre,a,b;
	protected int[][] colors;
	protected int borderThickness;
	protected int fillThickness;
	
	protected Ellipse() {}
	
	public Ellipse(Graph g,Color boundary,int x_centre,int y_centre,int a,int b) {
		this.graph = g;
		this.boundary = boundary;
		this.x_centre=x_centre;
		this.y_centre=y_centre;
		this.a = a;
		this.b= b;
		colors = new int[g.getX_size()*2][g.getY_size()*2];
		this.borderThickness=-1;
		this.fillThickness=-1;
	}
	
	public Ellipse(Graph g,Color boundary,Color fill,int x_centre,int y_centre,int a,int b) {
		this.graph = g;
		this.boundary = boundary;
		this.fill = fill;
		this.x_centre=x_centre;
		this.y_centre=y_centre;
		this.a = a;
		this.b = b;
		colors = new int[g.getX_size()*2][g.getY_size()*2];
		this.borderThickness=-1;
		this.fillThickness=-1;
	}
	
	public void setBorderThickness(int n) {
		this.borderThickness = n;
	}
	
	public void setFillThickenss(int n) {
		this.fillThickness=n;
	}

	public Color getFill() {
		return fill;
	}

	public void setFill(Color fill) {
		this.fill = fill;
	}

	public Color getCentreColor() {
		return centreColor;
	}

	public void setCentreColor(Color centreColor) {
		this.centreColor = centreColor;
	}

	public int getX_centre() {
		return x_centre;
	}

	public void setX_centre(int x_centre) {
		this.x_centre = x_centre;
	}

	public int getY_centre() {
		return y_centre;
	}

	public void setY_centre(int y_centre) {
		this.y_centre = y_centre;
	}


	public Graph getG() {
		return graph;
	}

	public void setG(Graph g) {
		this.graph = g;
	}

	public Color getBoundary() {
		return boundary;
	}

	public void setBoundary(Color boundary) {
		this.boundary = boundary;
	}

	public int getA() {
		return a;
	}

	public void setA(int a) {
		this.a = a;
	}

	public int getB() {
		return b;
	}

	public void setB(int b) {
		this.b = b;
	}

	public void getEllipse()  { 
		int n = graph.getX_size();
		int m = graph.getY_size();
		int bt = borderThickness==-1?10:borderThickness;
		float dx, dy, d1, d2, x, y; 
		x = 0; 
		float b2 = (float)b;
		y = b2;
		float a2 = (float)a;
		d1 = (b2 * b2) - (a2 * a2 * b2)+ (0.25f * a2 * a2); 
		dx = 2 * b2 * b2 * x; 
		dy = 2 * a2 * a2 * y; 
		while (dx < dy) { 
		    colors[(int)x+x_centre+n][(int)y+y_centre+m]=1;
		    colors[-(int)x+x_centre+n][(int)y+y_centre+m]=1;
		    colors[(int)x+x_centre+n][-(int)y+y_centre+m]=1;
		    colors[-(int)x+x_centre+n][-(int)y+y_centre+m]=1;
		  
		    if (d1 < 0)  
		    { 
		    	x++; 
		        dx = dx + (2 * b2 * b2); 
		        d1 = d1 + dx + (b2 * b2); 
		    } 
		    else
	        { 
		    	x++; 
		        y--; 
		        dx = dx + (2 * b2 * b2); 
	            dy = dy - (2 * a2 * a2); 
	            d1 = d1 + dx - dy + (b2 * b2); 
	        } 
	    } 
	    d2 = ((b2 * b2) * ((x + 0.5f) * (x + 0.5f))) + ((a2 * a2) * ((y - 1) * (y - 1))) 
		        - (a2 * a2 * b2 * b2); 
	    while (y >= 0) { 
	        colors[(int)x+x_centre+n][(int)y+y_centre+m]=1;
		    colors[-(int)x+x_centre+n][(int)y+y_centre+m]=1;
		    colors[(int)x+x_centre+n][-(int)y+y_centre+m]=1;
		    colors[-(int)x+x_centre+n][-(int)y+y_centre+m]=1;
		  
	        if (d2 > 0) { 
	            y--; 
	            dy = dy - (2 * a2 * a2); 
	            d2 = d2 + (a2 * a2) - dy; 
	        } 
	        else { 
	            y--; 
	            x++; 
	            dx = dx + (2 * b2 * b2); 
	            dy = dy - (2 * a2 * a2); 
	            d2 = d2 + dx - dy + (a2 * a2); 
	        } 
	    }
	}
	
	public void getEllipseBottom(int ar)  { 
		int n = graph.getX_size();
		int m = graph.getY_size();
		int bt = borderThickness==-1?10:borderThickness;
		float dx, dy, d1, d2, x, y; 
		x = 0; 
		float b2 = (float)b;
		y = b2;
		float a2 = (float)a;
		d1 = (b2 * b2) - (a2 * a2 * b2)+ (0.25f * a2 * a2); 
		dx = 2 * b2 * b2 * x; 
		dy = 2 * a2 * a2 * y; 
		while (dx < dy) {
			if((int)y+y_centre<ar) {
				colors[(int)x+x_centre+n][(int)y+y_centre+m]=1;
			    colors[-(int)x+x_centre+n][(int)y+y_centre+m]=1;
			}
			if(-(int)y+y_centre<ar) {
				 colors[(int)x+x_centre+n][-(int)y+y_centre+m]=1;
				 colors[-(int)x+x_centre+n][-(int)y+y_centre+m]=1;				
			}
		    if (d1 < 0)  
		    { 
		    	x++; 
		        dx = dx + (2 * b2 * b2); 
		        d1 = d1 + dx + (b2 * b2); 
		    } 
		    else
	        { 
		    	x++; 
		        y--; 
		        dx = dx + (2 * b2 * b2); 
	            dy = dy - (2 * a2 * a2); 
	            d1 = d1 + dx - dy + (b2 * b2); 
	        } 
	    } 
	    d2 = ((b2 * b2) * ((x + 0.5f) * (x + 0.5f))) + ((a2 * a2) * ((y - 1) * (y - 1))) 
		        - (a2 * a2 * b2 * b2); 
	    while (y >= 0) { 
	    	if((int)y+y_centre<ar) {
				colors[(int)x+x_centre+n][(int)y+y_centre+m]=1;
			    colors[-(int)x+x_centre+n][(int)y+y_centre+m]=1;
			}
			if(-(int)y+y_centre<ar) {
				 colors[(int)x+x_centre+n][-(int)y+y_centre+m]=1;
				 colors[-(int)x+x_centre+n][-(int)y+y_centre+m]=1;				
			}
	        if (d2 > 0) { 
	            y--; 
	            dy = dy - (2 * a2 * a2); 
	            d2 = d2 + (a2 * a2) - dy; 
	        } 
	        else { 
	            y--; 
	            x++; 
	            dx = dx + (2 * b2 * b2); 
	            dy = dy - (2 * a2 * a2); 
	            d2 = d2 + dx - dy + (a2 * a2); 
	        } 
	    }
	    for(int i=-(int)x+x_centre+1;i<(int)x+x_centre;i++) {
	    	colors[i+n][ar+m]=1;
	    }
	}
	
	public void getEllipseTop(int ar)  { 
		int n = graph.getX_size();
		int m = graph.getY_size();
		int bt = borderThickness==-1?10:borderThickness;
		float dx, dy, d1, d2, x, y; 
		x = 0; 
		float b2 = (float)b;
		y = b2;
		float a2 = (float)a;
		d1 = (b2 * b2) - (a2 * a2 * b2)+ (0.25f * a2 * a2); 
		dx = 2 * b2 * b2 * x; 
		dy = 2 * a2 * a2 * y; 
		while (dx < dy) {
			if((int)y+y_centre>ar) {
				colors[(int)x+x_centre+n][(int)y+y_centre+m]=1;
			    colors[-(int)x+x_centre+n][(int)y+y_centre+m]=1;
			}
			if(-(int)y+y_centre>ar) {
				 colors[(int)x+x_centre+n][-(int)y+y_centre+m]=1;
				 colors[-(int)x+x_centre+n][-(int)y+y_centre+m]=1;				
			}
		    if (d1 < 0)  
		    { 
		    	x++; 
		        dx = dx + (2 * b2 * b2); 
		        d1 = d1 + dx + (b2 * b2); 
		    } 
		    else
	        { 
		    	x++; 
		        y--; 
		        dx = dx + (2 * b2 * b2); 
	            dy = dy - (2 * a2 * a2); 
	            d1 = d1 + dx - dy + (b2 * b2); 
	        } 
	    } 
	    d2 = ((b2 * b2) * ((x + 0.5f) * (x + 0.5f))) + ((a2 * a2) * ((y - 1) * (y - 1))) 
		        - (a2 * a2 * b2 * b2); 
	    while (y >= 0) { 
	    	if((int)y+y_centre>ar) {
				colors[(int)x+x_centre+n][(int)y+y_centre+m]=1;
			    colors[-(int)x+x_centre+n][(int)y+y_centre+m]=1;
			}
			if(-(int)y+y_centre>ar) {
				 colors[(int)x+x_centre+n][-(int)y+y_centre+m]=1;
				 colors[-(int)x+x_centre+n][-(int)y+y_centre+m]=1;				
			}
	        if (d2 > 0) { 
	            y--; 
	            dy = dy - (2 * a2 * a2); 
	            d2 = d2 + (a2 * a2) - dy; 
	        } 
	        else { 
	            y--; 
	            x++; 
	            dx = dx + (2 * b2 * b2); 
	            dy = dy - (2 * a2 * a2); 
	            d2 = d2 + dx - dy + (a2 * a2); 
	        } 
	    }
	    for(int i=-(int)x+x_centre+1;i<(int)x+x_centre;i++) {
	    	colors[i+n][ar+m]=1;
	    }
	}
	
	public void getFillEllipse() {
		getEllipse();
		fillInside(x_centre,y_centre);
		if(centreColor!=null) {
			colors[x_centre+graph.getX_size()][y_centre+graph.getY_size()]=3;
		}
	}
	
	public void getFillEllipseTop(int ar) {
		getEllipseTop(ar);
		fillinside(x_centre,ar+2);
		if(centreColor!=null) {
			colors[x_centre+graph.getX_size()][y_centre+graph.getY_size()]=3;
		}
	}
	
	public void getFillEllipseBottom(int ar) {
		getEllipseBottom(ar);
		fillinside(x_centre,ar-2);
		if(centreColor!=null) {
			colors[x_centre+graph.getX_size()][y_centre+graph.getY_size()]=3;
		}
	}
	
	public void fillEllipseCoordinates() {
		getEllipse();
		fillinside(x_centre,y_centre);
		if(centreColor!=null) {
			colors[x_centre+graph.getX_size()][y_centre+graph.getY_size()]=3;
		}
	}
	
	private void fillinside(int x,int y) {
		int n = graph.getX_size(),m = graph.getY_size();
		colors[x+graph.getX_size()][y+graph.getY_size()]=2;
		if(x==0 || y==0) {
			colors[x+graph.getX_size()][y+graph.getY_size()]=-1;
		}
		if(colors[x+1+n][y+m]==0) {
			fillinside(x+1,y);
		}
		if(colors[x-1+n][y+m]==0) {
			fillinside(x-1,y);
		}
		if(colors[x+n][y+1+m]==0) {
			fillinside(x,y+1);
		}
		if(colors[x+n][y-1+m]==0) {
			fillinside(x,y-1);
		}
	}
	
	private void fillInside(int x,int y) {
		int n = graph.getX_size(),m = graph.getY_size();
		colors[x+graph.getX_size()][y+graph.getY_size()]=2;
		if(colors[x+1+n][y+m]==0) {
			fillInside(x+1,y);
		}
		if(colors[x-1+n][y+m]==0) {
			fillInside(x-1,y);
		}
		if(colors[x+n][y+1+m]==0) {
			fillInside(x,y+1);
		}
		if(colors[x+n][y-1+m]==0) {
			fillInside(x,y-1);
		}
	}
	
	public void clearScreen() {
		for(int i=0;i<colors.length;i++) {
			Arrays.fill(colors[i], 0);
		}
	}
	
	/*		Under Construction Error Exists!!!!!! Use at own risk */
	
	public void rollAction(int xf,int yf) {
		int xi = getX_centre();
		int yi = getY_centre();
		int a =  getA();
		int b =  getB();
		Color centreColor = getCentreColor();
		int[][] points = Line.getpointsDDA(xi, xf, yi, yf);
		int n = points.length;
		for(int i=0;i<n-1;i++) {
			try {
				Thread.sleep(1000);
				this.x_centre = points[i][0];
				this.y_centre=points[i][1];
				for(int j=0;j<colors.length;j++) {
					Arrays.fill(colors[j],0);
				}
				setCentreColor(centreColor);
				graph.drawCoordinates();
				fillEllipse();
			}catch(Exception e) {}
		}
		try {
			Thread.sleep(1000);
			this.x_centre = points[n-1][0];
			this.y_centre = points[n-1][1];
			for(int j=0;j<colors.length;j++) {
				Arrays.fill(colors[j],0);
			}
			setCentreColor(centreColor);
			graph.drawCoordinates();
			fillEllipse();
		}catch(Exception e) {}
	}
	
	private void fillOnGraph() {
		int n = graph.getX_size(),m=graph.getY_size();
		for(int i=0;i<colors.length;i++) {
			for(int j=0;j<colors[0].length;j++) {
				if(colors[i][j]==2) {
					graph.plotPoint(i-n,j-m,this.fill,this.fillThickness==-1?10:fillThickness);
				}
			}
		}
	}
	
	private void drawOnGraph() {
		int n = graph.getX_size(),m=graph.getY_size();
		for(int i=0;i<colors.length;i++) {
			for(int j=0;j<colors[0].length;j++) {
				if(colors[i][j]==1) {
					graph.plotPoint(i-n,j-m,this.boundary,this.borderThickness==-1?10:borderThickness);
				}
			}
		}
	}
	
	public void rotateAngle(int a,int x_pivot,int y_pivot) {
		double angle = (double)Math.toRadians(a);
		int[][] modifiedColors = new int[colors.length][colors[0].length];
		for(int i=0;i<colors.length;i++) {
			for(int j=0;j<colors[0].length;j++) {
				if(colors[i][j]==1||colors[i][j]==2||colors[i][j]==3) {
					double mod_x = i - graph.getX_size() - x_pivot;
					double mod_y = j -graph.getY_size()- y_pivot;
					int new_x = x_pivot + (int)((mod_x*Math.cos(angle))- (mod_y*Math.sin(angle)));
					int new_y = y_pivot + (int)((mod_x*Math.sin(angle))+(mod_y*Math.cos(angle)));
					new_x+=graph.getX_size();
					new_y+=graph.getY_size();
					if(new_x<2*graph.getX_size()&&new_y<2*graph.getY_size()) {
						modifiedColors[new_x][new_y]=colors[i][j];
					}
				}
			}
		}
		this.colors = modifiedColors;
	}
	
	public void rotateAngleTop(int a,int x_pivot,int y_pivot,int ar) {
		double angle = (double)Math.toRadians(a);
		int[][] modifiedColors = new int[colors.length][colors[0].length];
		for(int i=0;i<colors.length;i++) {
			for(int j=0;j<colors[0].length;j++) {
				if(colors[i][j]==1||colors[i][j]==2||colors[i][j]==3) {
					double mod_x = i - graph.getX_size() - x_pivot;
					double mod_y = j -graph.getY_size()- y_pivot;
					int new_x = x_pivot + (int)((mod_x*Math.cos(angle))- (mod_y*Math.sin(angle)));
					int new_y = y_pivot + (int)((mod_x*Math.sin(angle))+(mod_y*Math.cos(angle)));
					new_x+=graph.getX_size();
					new_y+=graph.getY_size();
					if(new_x<2*graph.getX_size()&&new_y<2*graph.getY_size()) {
						modifiedColors[new_x][new_y]=colors[i][j];
					}
				}
			}
		}
		this.colors = modifiedColors;
	}
	
	public void rotateAngleBottom(int a,int x_pivot,int y_pivot,int ar) {
		double angle = (double)Math.toRadians(a);
		int[][] modifiedColors = new int[colors.length][colors[0].length];
		for(int i=0;i<colors.length;i++) {
			for(int j=0;j<colors[0].length;j++) {
				if(colors[i][j]==1||colors[i][j]==2||colors[i][j]==3) {
					double mod_x = i - graph.getX_size() - x_pivot;
					double mod_y = j -graph.getY_size()- y_pivot;
					int new_x = x_pivot + (int)((mod_x*Math.cos(angle))- (mod_y*Math.sin(angle)));
					int new_y = y_pivot + (int)((mod_x*Math.sin(angle))+(mod_y*Math.cos(angle)));
					new_x+=graph.getX_size();
					new_y+=graph.getY_size();
					if(new_x<2*graph.getX_size()&&new_y<2*graph.getY_size()) {
						modifiedColors[new_x][new_y]=colors[i][j];
					}
				}
			}
		}
		this.colors = modifiedColors;
	}
	
	public void drawEllipse() {
		getEllipse();
		drawOnGraph();
	}
	
	public void fillEllipse() {
		getEllipse();
		getFillEllipse();
		drawOnGraph();
		fillOnGraph();
	}
	
	public void drawEllipseTop(int ar) {
		getEllipseTop(ar);
		drawOnGraph();
	}
	
	public void drawEllipseBottom(int ar) {
		getEllipseBottom(ar);
		drawOnGraph();
	}
	
	public void fillEllipseTop(int ar) {
		getEllipseTop(ar);
		getFillEllipseTop(ar);
		drawOnGraph();
		fillOnGraph();
	}
	
	public void fillEllipseBottom(int ar) {
		getEllipseBottom(ar);
		getFillEllipseBottom(ar);
		drawOnGraph();
		fillOnGraph();
	}
	
	public void drawModifiedEllipse() {
		drawOnGraph();
		fillOnGraph();
	}
	
	public void scaleEllipse(int scale_x,int scale_y) {
		int[][] modifiedColors = new int[colors.length][colors[0].length];
		for(int i=0;i<colors.length;i++) {
			for(int j=0;j<colors[0].length;j++) {
				if(colors[i][j]==1||colors[i][j]==2||colors[i][j]==3) {
					int xcoord = i-graph.getX_size();
					int ycoord = j-graph.getY_size();
					int newx = xcoord * scale_x;
					int newy = ycoord*scale_y;
					newx+=graph.getX_size();
					newy+=graph.getY_size();
					if(newx<2*graph.getX_size()&&newy<2*graph.getY_size()) {
						//System.out.println(newx+" "+newy);
						modifiedColors[newx][newy]=colors[i][j];
					}
				}
			}
		}
		this.colors = modifiedColors;
	}
	
	public void scaleEllipseTop(int scale_x,int scale_y,int a) {
		int[][] modifiedColors = new int[colors.length][colors[0].length];
		for(int i=0;i<colors.length;i++) {
			for(int j=0;j<colors[0].length;j++) {
				if(colors[i][j]==1||colors[i][j]==2||colors[i][j]==3) {
					int xcoord = i-graph.getX_size();
					int ycoord = j-graph.getY_size();
					int newx = xcoord * scale_x;
					int newy = ycoord*scale_y;
					newx+=graph.getX_size();
					newy+=graph.getY_size();
					if(newx<2*graph.getX_size()&&newy<2*graph.getY_size()) {
						//System.out.println(newx+" "+newy);
						modifiedColors[newx][newy]=colors[i][j];
					}
				}
			}
		}
		this.colors = modifiedColors;
	}
	
	public void scaleEllipseBottom(int scale_x,int scale_y,int b) {
		int[][] modifiedColors = new int[colors.length][colors[0].length];
		for(int i=0;i<colors.length;i++) {
			for(int j=0;j<colors[0].length;j++) {
				if(colors[i][j]==1||colors[i][j]==2||colors[i][j]==3) {
					int xcoord = i-graph.getX_size();
					int ycoord = j-graph.getY_size();
					int newx = xcoord * scale_x;
					int newy = ycoord*scale_y;
					newx+=graph.getX_size();
					newy+=graph.getY_size();
					if(newx<2*graph.getX_size()&&newy<2*graph.getY_size()) {
						//System.out.println(newx+" "+newy);
						modifiedColors[newx][newy]=colors[i][j];
					}
				}
			}
		}
		this.colors = modifiedColors;
	}
}
