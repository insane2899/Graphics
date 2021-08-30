package LineClipping;

import java.awt.Color;

import Graph.Graph;
import Lines.Line;
import Polygon.Polygon;

public class PolygonClipping {
	
	private Graph graph;
	private final Polygon border;
	
	private Color outsideColor,insideColor,rectangleBorder;
	
	public PolygonClipping(Graph graph,Polygon border) {
		this.graph = graph;
		this.border = border;
	}
	
	public PolygonClipping(Graph graph,Polygon border,Color o,Color i,Color b) {
		this.graph = graph;
		this.border = border;
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
	
	public void drawBorder() {
		border.setGraph(graph);
		border.setBoundary(rectangleBorder);
		border.drawPolygon();
	}
	
	public void drawPolygon(Polygon p) {
		border.getPolygon();
		p.getFillPolygon();
		int[][] colors = p.getColors();
		for(int i=0;i<colors.length;i++) {
			for(int j=0;j<colors[0].length;j++) {
				if(colors[i][j]==1||colors[i][j]==2) {
					if(border.inside(i-graph.getX_size(),j-graph.getY_size())) {
						graph.plotPoint(i-graph.getX_size(), j-graph.getY_size(), insideColor);
					}
					else {
						graph.plotPoint(i-graph.getX_size(), j-graph.getY_size(), outsideColor);
					}
				}
			}
		}
	}
	
	public void drawLine(int xi,int yi,int xf,int yf) {
		int[][] points = Line.getpointsDDA(xi, xf, yi, yf);
		int n = points.length;
		for(int i=0;i<n;i++) {
			if(border.inside(points[i][0],points[i][1])) {
				graph.plotPoint(points[i][0], points[i][1], insideColor);
			}
			else {
				graph.plotPoint(points[i][0], points[i][1], outsideColor);
			}
		}
	}
}
