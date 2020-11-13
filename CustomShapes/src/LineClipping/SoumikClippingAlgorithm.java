package LineClipping;

import java.awt.Color;

import Graph.Graph;
import Lines.Line;
import Polygon.Polygon;

public class SoumikClippingAlgorithm {
	
	private Graph graph;
	private final int x_min,y_min,x_max,y_max;
	private static final int INSIDE = 0;
	private static final int LEFT = 1;
	private static final int RIGHT = 2;
	private static final int BOTTOM = 4;
	private static final int TOP = 8;
	
	private Color outsideColor,insideColor,rectangleBorder;
	
	public SoumikClippingAlgorithm(Graph graph,int xmin,int ymin,int xmax,int ymax) {
		this.graph = graph;
		x_min = xmin;
		y_min = ymin;
		x_max = xmax;
		y_max = ymax;
	}
	
	public SoumikClippingAlgorithm(Graph graph,int x1,int y1,int x2,int y2,Color o,Color i,Color b) {
		this.graph = graph;
		x_min = x1;
		y_min = y1;
		x_max = x2;
		y_max = y2;
		outsideColor = o;
		insideColor = i;
		rectangleBorder = b;
	}
	
	public Color getOutsideColor() {
		return outsideColor;
	}

	public void setOutsideColor(Color outsideColor) {
		this.outsideColor = outsideColor;
	}

	public Color getInsideColor() {
		return insideColor;
	}

	public void setInsideColor(Color insideColor) {
		this.insideColor = insideColor;
	}

	public Color getRectangleBorder() {
		return rectangleBorder;
	}

	public void setRectangleBorder(Color rectangleBorder) {
		this.rectangleBorder = rectangleBorder;
	}

	private int computeCode(int x,int y) {
		int code = INSIDE;
		if(x<x_min) {
			code|=LEFT;
		}
		else if(x>x_max) {
			code|=RIGHT;
		}
		if(y<y_min) {
			code|=BOTTOM;
		}
		else if(y>y_max) {
			code|=TOP;
		}
		return code;
	}
	
	public void drawRectangle() {
		Polygon p = new Polygon(graph,rectangleBorder,null);
		int[] x_points = {x_min,x_min,x_max,x_max};
		int[] y_points = {y_min,y_max,y_max,y_min};
		p.setPoints(x_points, y_points);
		p.drawPolygon();
	}
	
	public void drawLine(int xi,int yi,int xf,int yf) {
		int[][] points = Line.getpointsDDA(xi, xf, yi, yf);
		int n = points.length;
		for(int i=0;i<n;i++) {
			if(computeCode(points[i][0],points[i][1])==0) {
				graph.plotPoint(points[i][0], points[i][1], insideColor);
			}
			else {
				graph.plotPoint(points[i][0], points[i][1], outsideColor);
			}
		}
	}
}
