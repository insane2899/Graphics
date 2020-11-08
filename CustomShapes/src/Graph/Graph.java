package Graph;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

public class Graph {
	
	private int origin_x;
	private int origin_y;
	private int scale;
	private int x_size;
	private int y_size;
	private Color linecolor;
	private Color graphcolor;
	private Graphics g;
	
	public Graph(int origin_x, int origin_y, int scale, int x_size, 
			int y_size,Color linecolor,Color graphcolor,Graphics g) {
		this.origin_x = origin_x;
		this.origin_y = origin_y;
		this.scale = scale;
		this.x_size = x_size;
		this.y_size = y_size;
		this.linecolor = linecolor;
		this.graphcolor = graphcolor;
		this.g = g;
	}
	
	public Graph(int origin_x, int origin_y, int scale, int x_size, 
			int y_size,Color linecolor,Color graphcolor) {
		this.origin_x = origin_x;
		this.origin_y = origin_y;
		this.scale = scale;
		this.x_size = x_size;
		this.y_size = y_size;
		this.linecolor = linecolor;
		this.graphcolor = graphcolor;
	}
	
	
	
	public Color getLinecolor() {
		return linecolor;
	}

	public void setLinecolor(Color linecolor) {
		this.linecolor = linecolor;
	}

	public Color getGraphcolor() {
		return graphcolor;
	}

	public void setGraphcolor(Color graphcolor) {
		this.graphcolor = graphcolor;
	}

	public Graphics getG() {
		return g;
	}

	public void setG(Graphics g) {
		this.g = g;
	}

	public Color getColor() {
		return this.linecolor;
	}
	
	public void setColor(Color color) {
		this.linecolor = color;
	}

	public int getOrigin_x() {
		return origin_x;
	}

	public void setOrigin_x(int origin_x) {
		this.origin_x = origin_x;
	}

	public int getOrigin_y() {
		return origin_y;
	}

	public void setOrigin_y(int origin_y) {
		this.origin_y = origin_y;
	}

	public int getScale() {
		return scale;
	}

	public void setScale(int scale) {
		this.scale = scale;
	}

	public int getX_size() {
		return x_size;
	}

	public void setX_size(int x_size) {
		this.x_size = x_size;
	}

	public int getY_size() {
		return y_size;
	}

	public void setY_size(int y_size) {
		this.y_size = y_size;
	}

	public int getProgramX(double x) {
		double w = origin_x;
		w += x*scale;
		return (int)w;
	}
	
	public int getProgramY(double y) {
		double w = origin_y;
		w -= y*scale;
		return (int)w;
	}
	
	public double getCartisanX(int x) {
		double w = origin_x;
		w = (double)x - w;
		w = w/(double)scale;
		return w;
	}
	
	public double getCartisanY(int y) {
		double w = origin_y;
		w = w - (double)y;
		w = w/(double)scale;
		return w;
	}
	
	public void drawCoordinates() {
		//Coordinate Axes
		g.setColor(graphcolor);
		g.fillRect(-x_size*scale,-y_size*scale,2*x_size*scale,2*y_size*scale);
		Graphics2D g2 = (Graphics2D)g;
		g2.setColor(linecolor);
		for(int i=-x_size;i<=x_size;i++) {
			int x = getProgramX(i);
			int ymin = getProgramY(-y_size);
			int ymax = getProgramY(y_size);
			if(i!=0) {
				g2.setStroke(new BasicStroke(1));
			}
			else {
				g2.setStroke(new BasicStroke(3));
			}
			g2.draw(new Line2D.Float(x,ymin,x,ymax));
		}
		for(int i=-y_size;i<=y_size;i++) {
			int y = getProgramY(i);
			int xmin = getProgramY(-x_size);
			int xmax = getProgramY(x_size);
			if(i!=0) {
				g2.setStroke(new BasicStroke(1));
			}
			else {
				g2.setStroke(new BasicStroke(3));
			}
			g2.draw(new Line2D.Float(xmin,y,xmax,y));
		}
	}
	

	void drawCircle(int x,int y,int r) {
		int radius = scale*r;
		g.setColor(linecolor);
		g.drawOval(getProgramX(x)-radius,getProgramY(y)-radius,2*radius,2*radius);
	}
	
	void plotPoint(int x,int y) {
		int r = 10;
		g.setColor(linecolor);
		g.fillOval(getProgramX(x)-r, getProgramY(y)-r, 2*r, 2*r);
	}
	
	public void plotPoint(int x,int y,Color color) {
		int r = 10;
		g.setColor(color);
		g.fillOval(getProgramX(x)-r, getProgramY(y)-r, 2*r, 2*r);
	}
	
	public void plotPoint(int x,int y,Color color,int r) {
		g.setColor(color);
		g.fillOval(getProgramX(x)-r, getProgramY(y)-r, 2*r, 2*r);
	}
}
