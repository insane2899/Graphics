package Polygon;

import java.awt.Color;

import Graph.Graph;
import Lines.Line;

public class OldPolygon {
	private Graph graph;
	private Color boundary;
	private Color fill;
	private int[] x;
	private int[] y;
	private Line lines;
	private String method;
	int[][] colors;
	
	public OldPolygon() {}
	
	public OldPolygon(Graph graph, Color boundary, Color fill) {
		this.graph = graph;
		this.boundary = boundary;
		this.fill = fill;
		colors = new int[graph.getX_size()*2][graph.getY_size()*2];
	}
	
	public void setMethod(String method) {
		this.method = method;
	}
	
	public void setPoints(int[] x,int[] y) {
		this.x = x;
		this.y = y;
	}

	public Graph getGraph() {
		return graph;
	}
	public void setGraph(Graph graph) {
		this.graph = graph;
	}
	public Color getBoundary() {
		return boundary;
	}
	public void setBoundary(Color boundary) {
		this.boundary = boundary;
	}
	public Color getFill() {
		return fill;
	}
	public void setFill(Color fill) {
		this.fill = fill;
	}
	
	public void drawPolygon() {
		lines = new Line(graph,boundary);
		int n = x.length;
		for(int i=0;i<n;i++) {
			if(method==null || method.contentEquals("DDA")) {
				lines.drawDDA(x[i%n],y[i%n],x[(i+1)%n],y[(i+1)%n],colors,1);
			}
			else if(method.equals("Bresensham")) {
				lines.drawB(x[i%n],y[i%n],x[(i+1)%n],y[(i+1)%n],colors,1);
			}
		}
	}
	
	public void fillPolygon() {
		drawPolygon();
		int minx=Integer.MAX_VALUE,mini=0;
		for(int i=0;i<x.length;i++) {
			if(x[i]<minx) {
				minx=x[i];
				mini=i;
			}
		}
		//System.out.println(x[mini]+" "+y[mini]);
		outer:for(int i=0;i<10;i++) {
			for(int j=-5;j<=5;j++) {
				if(inside(i+x[mini],j+y[mini])) {
					//System.out.println((i+x[mini])+" "+(j+y[mini]));
					fillinside(i+x[mini],j+y[mini]);
					break outer;
				}
			}
		}
	}
	
	private boolean inside(int x,int y) {
		if(colors[x+graph.getX_size()][y+graph.getY_size()]==1) {
			return false;
		}
		if(colors[x+graph.getX_size()][y+graph.getY_size()]==2) {
			return false;
		}
		int count = 0;
		for(int i=x-1+graph.getX_size();i>=0;i--) {
			if(colors[i][y+graph.getY_size()]==1) {
				count++;
			}
		}
		//System.out.println(count);
		if(count%2==0) {
			return false;
		}
		return true;
	}
	
	private void fillinside(int x,int y) {
		int n = graph.getX_size(),m = graph.getY_size();
		colors[x+graph.getX_size()][y+graph.getY_size()]=2;
		graph.plotPoint(x,y,fill,8);
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
