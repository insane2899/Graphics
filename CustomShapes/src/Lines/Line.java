package Lines;

import java.awt.Color;
import java.util.StringTokenizer;

import Graph.Graph;

public class Line {
	
	private Graph graph;
	private Color color;

	public Line(Graph g,Color color) {
		graph = g;
		this.color = color;
	}
	
	public Graph getGraph() {
		return graph;
	}

	public void setGraph(Graph graph) {
		this.graph = graph;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public void drawDDA(String s) {
		StringTokenizer st = new StringTokenizer(s);
		int x1 = Integer.parseInt(st.nextToken());
		int y1 = Integer.parseInt(st.nextToken());
		int x2 = Integer.parseInt(st.nextToken());
		int y2 = Integer.parseInt(st.nextToken());
		int dx = Math.abs(x1-x2);
		int dy = Math.abs(y1-y2);
		int n;
		if(dx>dy) {
			n = dx;
		}
		else {
			n = dy;
		}
		double a = (double)(x2-x1)/n;
		double b = (double)(y2-y1)/n;
		for(int i=0;i<=n;i++) {
			double x = x1+(double)i*a;
			double y = y1+(double)i*b;
			if(x - (int)x >0.5) {
				x++;
			}
			if(y-(int)y>0.5) {
				y++;
			}
			//System.out.println(x+" "+y);
			graph.plotPoint((int)x,(int)y,color);	
		}
	}
	
	public void drawDDA(int x1,int x2,int y1,int y2) {
		int dx = Math.abs(x1-x2);
		int dy = Math.abs(y1-y2);
		int n;
		if(dx>dy) {
			n = dx;
		}
		else {
			n = dy;
		}
		double a = (double)(x2-x1)/n;
		double b = (double)(y2-y1)/n;
		for(int i=0;i<=n;i++) {
			double x = x1+(double)i*a;
			double y = y1+(double)i*b;
			if(x - (int)x >0.5) {
				x++;
			}
			if(y-(int)y>0.5) {
				y++;
			}
			//System.out.println(x+" "+y);
			graph.plotPoint((int)x,(int)y,color);	
		}
	}
	
	public static int[][] getpointsDDA(int x1,int x2,int y1,int y2){
		int dx = Math.abs(x1-x2);
		int dy = Math.abs(y1-y2);
		int n;
		if(dx>dy) {
			n = dx;
		}
		else {
			n = dy;
		}
		int[][] ans = new int[n+1][2];
		double a = (double)(x2-x1)/n;
		double b = (double)(y2-y1)/n;
		for(int i=0;i<=n;i++) {
			double x = x1+(double)i*a;
			double y = y1+(double)i*b;
			if(x - (int)x >0.5) {
				x++;
			}
			if(y-(int)y>0.5) {
				y++;
			}
			ans[i][0]=(int)x;
			ans[i][1]=(int)y;
		}
		return ans;
	}
	
	public void drawDDA(int x1,int y1,int x2,int y2,int[][] colors,int c) {
		int dx = Math.abs(x1-x2);
		int dy = Math.abs(y1-y2);
		int n;
		if(dx>dy) {
			n = dx;
		}
		else {
			n = dy;
		}
		double a = (double)(x2-x1)/n;
		double b = (double)(y2-y1)/n;
		for(int i=0;i<=n;i++) {
			double x = x1+(double)i*a;
			double y = y1+(double)i*b;
			if(x - (int)x >0.5) {
				x++;
			}
			if(y-(int)y>0.5) {
				y++;
			}
			//System.out.println(x+" "+y);
			if(colors[(int)x+graph.getX_size()][(int)y+graph.getY_size()]==0) {
				graph.plotPoint((int)x,(int)y,color);
				colors[(int)x+graph.getX_size()][(int)y+graph.getY_size()]=c;
			}
		}
	}
	
	public void drawB(String s) {
		StringTokenizer st = new StringTokenizer(s);
		int x1 = Integer.parseInt(st.nextToken());
		int y1 = Integer.parseInt(st.nextToken());
		int x2 = Integer.parseInt(st.nextToken());
		int y2 = Integer.parseInt(st.nextToken());
		int dx = Math.abs(x1-x2);
		int dy = Math.abs(y1-y2);
		int sx = x1<x2?1:-1;
		int sy = y1<y2?1:-1;
		
		int err = dx - dy;
		int e2;
		
		while(true) {
			graph.plotPoint(x1,y1,color);
			if(x1==x2 && y1==y2) {
				break;
			}
			e2 = 2*err;
			if(e2> -dy) {
				err = err - dy;
				x1 = x1+sx;
			}
			if(e2<dx) {
				err = err+dx;
				y1 = y1+sy;
			}
		}
	}
	
	public void drawB(int x1,int x2,int y1,int y2) {
		int dx = Math.abs(x1-x2);
		int dy = Math.abs(y1-y2);
		int sx = x1<x2?1:-1;
		int sy = y1<y2?1:-1;
		
		int err = dx - dy;
		int e2;
		
		while(true) {
			graph.plotPoint(x1,y1,color);
			if(x1==x2 && y1==y2) {
				break;
			}
			e2 = 2*err;
			if(e2> -dy) {
				err = err - dy;
				x1 = x1+sx;
			}
			if(e2<dx) {
				err = err+dx;
				y1 = y1+sy;
			}
		}
	}
	
	public void drawB(int x1,int y1,int x2,int y2,int[][] colors,int c) {
		int dx = Math.abs(x1-x2);
		int dy = Math.abs(y1-y2);
		int sx = x1<x2?1:-1;
		int sy = y1<y2?1:-1;
		
		int err = dx - dy;
		int e2;
		
		while(true) {
			graph.plotPoint(x1,y1,color);
			colors[x1+graph.getX_size()][y1+graph.getY_size()]=c;
			if(x1==x2 && y1==y2) {
				break;
			}
			e2 = 2*err;
			if(e2> -dy) {
				err = err - dy;
				x1 = x1+sx;
			}
			if(e2<dx) {
				err = err+dx;
				y1 = y1+sy;
			}
		}
	}
	
	public void drawMP(String s) {
		StringTokenizer st = new StringTokenizer(s);
		int x1 = Integer.parseInt(st.nextToken());
		int y1 = Integer.parseInt(st.nextToken());
		int x2 = Integer.parseInt(st.nextToken());
		int y2 = Integer.parseInt(st.nextToken());
		int dy = y2-y1;
		int dx = x2-x1;
		int d = dy - (dx/2);
		int begx = x1,begy = y1;
		graph.plotPoint(begx, begy,color);
		while(begx<x2) {
			begx=begx+1;
			if(d<0) {
				d = d+dy;
			}
			else {
				d = d+dy-dx;
				begy=begy+1;
			}
			graph.plotPoint(begx,begy,color);
		}
	}
}
