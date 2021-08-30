package Circle;

import java.awt.Color;

import Graph.Graph;

public class SeparateCircle {
	private Graph g;
	private Color boundary;
	private Color fill;
	private Color centreColor;
	int x_centre,y_centre,r;
	int[][] colors;
	
	public SeparateCircle(Graph g,Color boundary,int x_centre,int y_centre,int r) {
		this.g = g;
		this.boundary = boundary;
		this.x_centre=x_centre;
		this.y_centre=y_centre;
		this.r = r;
		colors = new int[g.getX_size()*2][g.getY_size()*2];
	}
	
	public SeparateCircle(Graph g,Color boundary,Color fill,int x_centre,int y_centre,int r) {
		this.g = g;
		this.boundary = boundary;
		this.fill = fill;
		this.x_centre=x_centre;
		this.y_centre=y_centre;
		this.r = r;
		colors = new int[g.getX_size()*2][g.getY_size()*2];
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

	public int getR() {
		return r;
	}

	public void setR(int r) {
		this.r = r;
	}

	public Graph getG() {
		return g;
	}

	public void setG(Graph g) {
		this.g = g;
	}

	public Color getBoundary() {
		return boundary;
	}

	public void setBoundary(Color boundary) {
		this.boundary = boundary;
	}

	public void drawCircle()  { 
		int n = g.getX_size();
		int m = g.getY_size();
		int x = r, y = 0;  
		g.plotPoint(x+x_centre,y+y_centre,boundary);
		colors[x+x_centre+n][y+y_centre+m]=1;
		if (r > 0) { 
			g.plotPoint(x_centre, r+y_centre, boundary);
			g.plotPoint(-r+x_centre, y_centre, boundary);
			g.plotPoint(x_centre, -r+y_centre, boundary);
			colors[x_centre+n][r+y_centre+m]=1;
			colors[-r+x_centre+n][y_centre+m]=1;
			colors[x_centre+n][-r+y_centre+m]=1;
		} 
		int P = 1 - r; 
		while (x > y) { 
			y++; 
			if (P <= 0) {
				P = P + 2 * y + 1; 
			}
			else { 
				x--; 
				P = P + 2 * y - 2 * x + 1; 
			} 
			if (x < y) { 
				break; 
			}
			g.plotPoint(x+x_centre,y+y_centre,boundary);
			g.plotPoint(-x+x_centre, y+y_centre, boundary);
			g.plotPoint(x+x_centre, -y+y_centre, boundary);
			g.plotPoint(-x+x_centre, -y+y_centre, boundary);
			colors[x+x_centre+n][y+y_centre+m]=1;
			colors[-x+x_centre+n][y+y_centre+m]=1;
			colors[x+x_centre+n][-y+y_centre+m]=1;
			colors[-x+x_centre+n][-y+y_centre+m]=1;
			if (x != y) { 
				g.plotPoint(y+x_centre, x+y_centre, boundary);
				g.plotPoint(-y+x_centre, x+y_centre, boundary);
				g.plotPoint(y+x_centre, -x+y_centre, boundary);
				g.plotPoint(-y+x_centre, -x+y_centre, boundary);
				colors[y+x_centre+n][x+y_centre+m]=1;
				colors[-y+x_centre+n][x+y_centre+m]=1;
				colors[y+x_centre+n][-x+y_centre+m]=1;
				colors[-y+x_centre+n][-x+y_centre+m]=1;
			} 
		} 
	}
	
	public void fillCircle() {
		drawCircle();
		fillinside(x_centre,y_centre);
		if(centreColor!=null) {
			g.plotPoint(x_centre,y_centre,centreColor);
		}
		
	}
	
	private void fillinside(int x,int y) {
		int n = g.getX_size(),m = g.getY_size();
		colors[x+g.getX_size()][y+g.getY_size()]=2;
		if(x==0 || y==0) {
			//g.plotPoint(x, y, Color.BLACK);
		}
		else {
			g.plotPoint(x,y,fill,8);
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
}
