package Cylinder;

import java.awt.Color;

import Ellipse.OldEllipse;
import Graph.Graph;
import Lines.Line;

public class Cylinder {
	Graph g;
	OldEllipse top;
	Line left;
	Line right;
	OldEllipse bottom;
	OldEllipse fill;
	int xc,yc,height,a,b;
	Color border;
	
	public Cylinder(Graph g,int xc,int yc,int height,int a,int b,Color border) {
		this.xc = xc;
		this.yc = yc;
		this.height = height;
		this.a =a;
		this.b = b;
		this.border = border;
	}	
	
	public Graph getG() {
		return g;
	}

	public void setG(Graph g) {
		this.g = g;
	}

	public int getXc() {
		return xc;
	}
	
	public void setXc(int xc) {
		this.xc = xc;
	}

	public int getYc() {
		return yc;
	}

	public void setYc(int yc) {
		this.yc = yc;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
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

	public void drawCylinder() {
		top = new OldEllipse(g,border,xc,yc+height,a,b);
		bottom = new OldEllipse(g,border,xc,yc,a,b);
		left = new Line(g,border);
		right = new Line(g,border);
		top.drawEllipse();
		bottom.drawEllipse();
		left.drawDDA(xc-a,xc-a,yc+height,yc);
		right.drawDDA(xc+a,xc+a,yc+height,yc);
	}
	
	public void fillCylinder(Color color) {
		drawCylinder();
		bottom.setFill(color);
		bottom.fillEllipse();
		for(int i=b;i<=height;i+=5) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			int hmax = i+yc;
			int hmin = yc;
			int xmax = xc+a;
			int xmin = xc-a;
			for(int l=i+yc;l>=yc;l--) {
				for(int m = xc-a+1;m<xc+a;m++) {
					g.plotPoint(m, l, color);
				}
			}
			bottom.setY_centre(i+yc);
			bottom.clearScreen();
			bottom.fillEllipse();
			top.drawEllipse();
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		int hmax = height+yc;
		int hmin = yc;
		int xmax = xc+a;
		int xmin = xc-a;
		for(int l=height+yc;l>=yc;l--) {
			for(int m = xc-a+1;m<xc+a;m++) {
				g.plotPoint(m, l, color);
			}
		}
		bottom.setY_centre(height+yc);
		bottom.clearScreen();
		bottom.fillEllipse();
		top.drawEllipse();	
	}
	
}
