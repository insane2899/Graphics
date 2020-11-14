package LineClipping;

import java.awt.Color;

import Graph.Graph;
import Polygon.Polygon;

public class SutherlandHodgeman {
	
	private Graph graph;
	private Polygon border;
	
	private Color outsideColor,insideColor,rectangleBorder;
	
	public SutherlandHodgeman(Graph graph,Polygon border) {
		this.graph = graph;
		this.border = border;
	}
	
	public SutherlandHodgeman(Graph graph,Polygon border,Color o,Color i,Color b) {
		this.graph = graph;
		outsideColor = o;
		insideColor = i;
		rectangleBorder = b;
		this.border = border;
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
	
	private int x_intersect(int x1,int y1,int x2,int y2,int x3,int y3,int x4,int y4) {
		int num = (x1*y2 - y1*x2)*(x3-x4) - (x1-x2)*(x3*y4 - y3*x4);
		int den = (x1-x2)*(y3-y4) - (y1-y2)*(x3-x4);
		return num/den;
	}
	
	private int y_intersect(int x1,int y1,int x2,int y2,int x3,int y3,int x4,int y4) {
		int num = (x1*y2 - y1*x2) * (y3-y4) -  (y1-y2) * (x3*y4 - y3*x4); 
	    int den = (x1-x2) * (y3-y4) - (y1-y2) * (x3-x4); 
	    return num/den; 
	}
		
	private void clip(Polygon p,int x1,int y1,int x2,int y2) {
		int[] x = new int[40];
		int[] y = new int[40];
		int z = 0;
		int[] ox = p.getXPoints();
		int[] oy = p.getYPoints();
		for(int i=0;i<ox.length;i++) {
			int k = (i+1)%ox.length;
			int ix = ox[i],iy = oy[i],kx = ox[k],ky=oy[k];
			
			int i_pos = (x2-x1)*(iy-y1) - (y2-y1)*(ix-x1);
			int k_pos = (x2-x1)*(ky-y1) - (y2-y1)*(kx-x1);
			if(i_pos<0 && k_pos<0) {
				x[z]=kx;
				y[z]=ky;
				z++;
			}
			else if(i_pos>=0 && k_pos<0) {
				x[z]=x_intersect(x1,y1,x2,y2,ix,iy,kx,ky);
				y[z]=y_intersect(x1,y1,x2,y2,ix,iy,kx,ky);
				z++;
				x[z]=kx;
				y[z]=ky;
				z++;
			}
			else if(i_pos<0 && k_pos>=0) {
				x[z]=x_intersect(x1,y1,x2,y2,ix,iy,kx,ky);
				y[z]=y_intersect(x1,y1,x2,y2,ix,iy,kx,ky);
				z++;
			}
			else {}
		}
		int[] newx = new int[z];
		int[] newy = new int[z];
		for(int i=0;i<z;i++) {
			newx[i]=x[i];
			newy[i]=y[i];
		}
		p.setPoints(newx, newy);
	}
	
	public void drawPolygon(Polygon p) {
		p.setGraph(graph);
		p.setBoundary(rectangleBorder);
		p.setFill(outsideColor);
		p.fillPolygon();
		int[] x = border.getXPoints();
		int[] y = border.getYPoints();
		for(int i=0;i<border.getXPoints().length;i++) {
			int k = (i+1)%border.getXPoints().length;
			clip(p,x[i],y[i],x[k],y[k]);
		}
		p.clearGraph();	//The graph instance of this object changes in last setgraph
						//called in this method. But the graph of the p instance is 
						//already colored in its color[][] array. so wrong output will come without
						//clearing the color[][] array
		//p.setBoundary(insideColor);
		p.setFill(insideColor);
		p.fillPolygon();
	}
	
	public void drawBorder() {
		border.setGraph(graph);
		border.setBoundary(rectangleBorder);
		border.drawPolygon();
	}
}
