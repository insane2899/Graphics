package LineClipping;

import java.awt.Color;

import Graph.Graph;
import Lines.Line;
import Polygon.Polygon;

public class CohenSutherland {
	
	private Graph graph;
	private final int x_min,y_min,x_max,y_max;
	private static final int INSIDE = 0;
	private static final int LEFT = 1;
	private static final int RIGHT = 2;
	private static final int BOTTOM = 4;
	private static final int TOP = 8;
	
	private Color outsideColor,insideColor,rectangleBorder;
	
	public CohenSutherland(Graph graph,int xmin,int ymin,int xmax,int ymax) {
		this.graph = graph;
		x_min = xmin;
		y_min = ymin;
		x_max = xmax;
		y_max = ymax;
	}
	
	public CohenSutherland(Graph graph,int x1,int y1,int x2,int y2,Color o,Color i,Color b) {
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
	
	private int[][] getPointsInside(int x1,int y1,int x2,int y2){
		int code1 = computeCode(x1,y1);
		int code2 = computeCode(x2,y2);
		boolean accept=false;
		while(true) {
			if((code1==0)&&(code2==0)) {
				accept=true;
				break;
			}
			else if((code1&code2)!=0) {
				break;
			}
			else {
				int code_out;
				double x=0,y=0;
				
				if(code1!=0) {
					code_out=code1;
				}
				else {
					code_out = code2;
				}
				if ((code_out & TOP)!=0) { 
	                x = (double)x1 + ((double)x2 - x1) * (y_max - y1) / (y2 - y1); 
	                y = y_max; 
	            } 
	            else if ((code_out & BOTTOM)!=0) { 
	                x = (double)x1 + ((double)x2 - x1) * (y_min - y1) / (y2 - y1); 
	                y = y_min; 
	            } 
	            else if ((code_out & RIGHT)!=0) { 
	                y = (double)y1 + ((double)y2 - y1) * (x_max - x1) / (x2 - x1); 
	                x = x_max; 
	            } 
	            else if ((code_out & LEFT)!=0) {
	            	y = (double)y1 + ((double)y2 - y1) * (x_min - x1) / (x2 - x1); 
	                x = x_min; 
	            }
				if(code_out == code1) {
					x1=x-(int)x>0.5?(int)x+1:(int)x;
					y1=y-(int)y>0.5?(int)y+1:(int)y;
					code1=computeCode(x1,y1);
				}
				else {
					x2=x-(int)x>0.5?(int)x+1:(int)x;
					y2=y-(int)y>0.5?(int)y+1:(int)y;
					code2=computeCode(x2,y2);
				}
			}
		}
		if(accept) {
			int[][] ans = new int[2][2];
			ans[0][0]=x1;
			ans[0][1]=y1;
			ans[1][0]=x2;
			ans[1][1]=y2;
			return ans;
		}
		else {
			return null;
		}
	}
	
	public void drawRectangle() {
		Polygon p = new Polygon(graph,rectangleBorder,null);
		int[] x_points = {x_min,x_min,x_max,x_max};
		int[] y_points = {y_min,y_max,y_max,y_min};
		p.setPoints(x_points, y_points);
		p.drawPolygon();
	}
	
	public void drawLine(int xi,int yi,int xf,int yf) {
		Line line = new Line(graph,outsideColor);
		line.drawDDA(xi,xf,yi,yf);
		int[][] inside = getPointsInside(xi,yi,xf,yf);
		if(inside==null) {
			
		}
		else {
			xi = inside[0][0];
			yi=inside[0][1];
			xf=inside[1][0];
			yf=inside[1][1];
			//System.out.println(xi+" "+yi+" "+xf+" "+yf);
			Line line1 = new Line(graph,insideColor);
			line1.drawDDA(xi,xf,yi,yf);
		}
	}
	
	public void drawPolygon(Polygon p) {
		p.setBoundary(outsideColor);
		p.drawPolygon();
		int[] x = p.getXPoints();
		int[] y = p.getYPoints();
		int n = x.length;
		for(int i=0;i<n;i++) {
			int xbeg = x[i];
			int ybeg = y[i];
			int xend = x[(i+1)%n];
			int yend = y[(i+1)%n];
			drawLine(xbeg,ybeg,xend,yend);
		}
	}
}
