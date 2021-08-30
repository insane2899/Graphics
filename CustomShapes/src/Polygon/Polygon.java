package Polygon;

import java.awt.Color;

import Graph.Graph;
import Lines.Line;


//Always first rotate then scale. Otherwise scaling will change the center of the rotation

public class Polygon {
	private Graph graph;
	private Color boundary;
	private Color fill;
	private int[] x;
	private int[] y;
	private Line lines;
	private String method;
	private int[][] colors;
	
	public Polygon() {}
	
	public Polygon(Graph graph, Color boundary, Color fill) {
		this.graph = graph;
		this.boundary = boundary;
		this.fill = fill;
		this.colors = new int[2*graph.getX_size()][2*graph.getY_size()];
	}
	
	public void setMethod(String method) {
		this.method = method;
	}
	
	public int[][] getColors(){
		return this.colors;
	}
	
	public void setPoints(int[] x,int[] y) {
		this.x = x;
		this.y = y;
	}
	
	public int[] getXPoints() {
		return x;
	}
	
	public int[] getYPoints() {
		return y;
	}

	public Graph getGraph() {
		return graph;
	}
	public void setGraph(Graph graph) {
		this.graph = graph;
	}
	public void clearGraph() {
		this.colors = new int[2*graph.getX_size()][2*graph.getY_size()];
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
	
	public void getPolygon() {
		lines = new Line(graph,boundary);
		int n = x.length;
		for(int i=0;i<n;i++) {
			if(method==null || method.contentEquals("DDA")) {
				int[][] points = lines.getpointsDDA(x[i%n], x[(i+1)%n], y[i%n], y[(i+1)%n]);
				for(int j=0;j<points.length;j++) {
					colors[points[j][0]+graph.getX_size()][points[j][1]+graph.getY_size()]=1;
				}
			}
		}
	}
	
	public void getFillPolygon() {
		getPolygon();
		int minx=Integer.MAX_VALUE,mini=0;
		for(int i=0;i<x.length;i++) {
			if(x[i]<minx) {
				minx=x[i];
				mini=i;
			}
		}
		outer:for(int i=0;i<10;i++) {
			for(int j=-5;j<=5;j++) {
				if(inside(i+x[mini],j+y[mini])) {
					getinside(i+x[mini],j+y[mini]);
					break outer;
				}
			}
		}
	}
	
	public boolean inside(int x,int y) {
		if(colors[x+graph.getX_size()][y+graph.getY_size()]==1) {
			return false;
		}
		if(colors[x+graph.getX_size()][y+graph.getY_size()]==2) {
			return true;
		}
		int count = 0;
		for(int i=x+graph.getX_size();i>=0;i--) {
			if(colors[i][y+graph.getY_size()]==1) {
				count++;
			}
		}
		if(count%2==0) {
			return false;
		}
		else if(count%2!=0) {
			boolean flag1=true,flag2=true;
			int count2=0;
			if(y+graph.getY_size()-1>=0) {
				for(int i=x+graph.getX_size();i>=0;i--) {
					if(colors[i][y+graph.getY_size()-1]==1) {
						count2++;
					}
				}
				if(count2%2==0) {
					flag1=false;
				}
			}
			count2=0;
			if(y+graph.getY_size()+1<2*graph.getY_size()) {
				for(int i=x+graph.getX_size();i>=0;i--) {
					if(colors[i][y+graph.getY_size()+1]==1) {
						count2++;
					}
				}
				if(count2%2==0) {
					flag2=false;
				}
			}
			if(!flag1&&!flag2) {
				return false;
			}
		}
		return true;
	}
	
	private void getinside(int x,int y) {
		int n = graph.getX_size(),m = graph.getY_size();
		if(x+n<0 || x+n>=2*n || y+m<0 || y+n>=2*m) {
			return;
		}
		colors[x+graph.getX_size()][y+graph.getY_size()]=2;
		if(colors[x+1+n][y+m]==0) {
			getinside(x+1,y);
		}
		if(colors[x-1+n][y+m]==0) {
			getinside(x-1,y);
		}
		if(colors[x+n][y+1+m]==0) {
			getinside(x,y+1);
		}
		if(colors[x+n][y-1+m]==0) {
			getinside(x,y-1);
		}
	}
	
	private void fillOnGraph() {
		for(int i=0;i<colors.length;i++) {
			for(int j=0;j<colors[0].length;j++) {
				if(colors[i][j]==2) {
					graph.plotPoint(i-graph.getX_size(), j-graph.getY_size(), fill);
				}
			}
		}
	}
	
	private void drawOnGraph() {
		for(int i=0;i<colors.length;i++) {
			for(int j=0;j<colors[0].length;j++) {
				if(colors[i][j]==1) {
					graph.plotPoint(i-graph.getX_size(), j-graph.getY_size(), boundary);
				}
			}
		}
	}
	
	public void drawPolygon() {
		getPolygon();
		drawOnGraph();
	}
	
	public void fillPolygon() {
		getPolygon();
		getFillPolygon();
		drawOnGraph();
		fillOnGraph();
	}
	

	public void rotateAngle(int a,int x_pivot,int y_pivot) {
		double angle = (double)Math.toRadians(a);
		int[][] modifiedColors = new int[colors.length][colors[0].length];
		for(int i=0;i<colors.length;i++) {
			for(int j=0;j<colors[0].length;j++) {
				if(colors[i][j]==1||colors[i][j]==2) {
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
		//System.out.println("Here");
		this.colors = modifiedColors;
	}
	
	public void drawModifiedPolygon() {
		drawOnGraph();
		fillOnGraph();
	}
	
	public void scalePolygon(int scale_x,int scale_y) {
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

