package Lines;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Line2D;
import java.util.StringTokenizer;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Lines extends JFrame implements MouseMotionListener, KeyListener {
	
	int X,Y;
	int scale; //40 pixels = 1 unit of cartisan coordinate
	final int x_size;
	final int y_size;
	int origin_x;
	int origin_y;
	JOptionPane p;
	
	boolean coordinates,DDA,B,MP;
	String dda,b,midpoint;
	
	public Lines(int scale,int x_size,int y_size) {
		this.x_size = x_size;
		this.y_size = y_size;
		this.scale = scale;
		this.coordinates = true;
		this.DDA = false;
		this.B =false;
		addMouseMotionListener(this);
		addKeyListener(this);
		setSize(1000,1000);
		this.origin_x = getWidth()/2;
		this.origin_y = getHeight()/2;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public static void main(String[] args) {
		new Lines(40,1000,1000);

	}
	
	public void paint(Graphics g) {
		g.clearRect(0, 0, getWidth(), getHeight());
		g.setColor(new Color(132,187,235));
		g.fillRect(0,0,getWidth(),getHeight());
		//Cartisan Coordinates
		if(coordinates) {
			drawCoordinates(g);
		}
		if(DDA) {
			drawDDA(dda,g);
		}
		if(B) {
			drawB(b,g);
		}
		if(MP) {
			drawMP(midpoint,g);
		}
		g.setColor(Color.BLACK);
		g.drawString("Mouse Location: X:"+X+" Y:"+Y, 10, getHeight()-10);
		
	}
	
	int getProgramX(double x,int factor) {
		double w = origin_x;
		w += x*factor;
		return (int)w;
	}
	
	int getProgramY(double y,int factor) {
		double w = origin_y;
		w -= y*factor;
		return (int)w;
	}
	
	double getCartisanX(int x,int factor) {
		double w = origin_x;
		w = (double)x - w;
		w = w/(double)factor;
		return w;
	}
	
	double getCartisanY(int y,int factor) {
		double w = origin_y;
		w = w - (double)y;
		w = w/(double)factor;
		return w;
	}
	
	void drawCoordinates(Graphics g) {
		//Coordinate Axes
		Graphics2D g2 = (Graphics2D)g;
		g2.setColor(Color.BLACK);
		for(int i=-x_size;i<=x_size;i++) {
			int x = getProgramX(i,scale);
			int ymin = getProgramY(-y_size,scale);
			int ymax = getProgramY(y_size,scale);
			if(i!=0) {
				g2.setStroke(new BasicStroke(1));
			}
			else {
				g2.setStroke(new BasicStroke(3));
			}
			g2.draw(new Line2D.Float(x,ymin,x,ymax));
		}
		for(int i=-y_size;i<=y_size;i++) {
			int y = getProgramY(i,scale);
			int xmin = getProgramX(-x_size,scale);
			int xmax = getProgramX(x_size,scale);
			if(i!=0) {
				g2.setStroke(new BasicStroke(1));
			}
			else {
				g2.setStroke(new BasicStroke(3));
			}
			g2.draw(new Line2D.Float(xmin,y,xmax,y));
		}
		
		
	}
	
	void drawCircle(Graphics g,int x,int y,int r) {
		int radius = scale*r;
		g.drawOval(getProgramX(x,scale)-radius,getProgramY(y,scale)-radius,2*radius,2*radius);
	}
	
	void plotPoint(Graphics g,int x,int y) {
		int r = 20;
		g.fillOval(getProgramX(x,scale)-r, getProgramY(y,scale)-r, 2*r, 2*r);
	}
	
	void drawSquare(Graphics g,int x1,int y1,int x2,int y2) {
		for(int i=x1;i<=x2;i++) {
			for(int j=y1;j<=y2;j++) {
				if(i==x1||i==x2||j==y1||j==y2) {
					plotPoint(g,i,j);
				}
			}
		}
	}
	
	void drawDDA(String s,Graphics g) {
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
			g.setColor(Color.WHITE);
			//System.out.println(x+" "+y);
			plotPoint(g,(int)x,(int)y);	
		}
	}
	
	void drawB(String s,Graphics g) {
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
			g.setColor(Color.RED);
			plotPoint(g,x1,y1);
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
	
	void drawMP(String s,Graphics g) {
		StringTokenizer st = new StringTokenizer(s);
		int x1 = Integer.parseInt(st.nextToken());
		int y1 = Integer.parseInt(st.nextToken());
		int x2 = Integer.parseInt(st.nextToken());
		int y2 = Integer.parseInt(st.nextToken());
		int dy = y2-y1;
		int dx = x2-x1;
		int d = dy - (dx/2);
		int begx = x1,begy = y1;
		g.setColor(Color.YELLOW);
		plotPoint(g, begx, begy);
		while(begx<x2) {
			begx=begx+1;
			if(d<0) {
				d = d+dy;
			}
			else {
				d = d+dy-dx;
				begy=begy+1;
			}
			plotPoint(g,begx,begy);
		}
	}
	
	//Inherited abstract methods

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		X = arg0.getX();
		Y = arg0.getY();
		repaint();
		
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		int key = arg0.getExtendedKeyCode();
		//System.out.println(key);
		if(key == KeyEvent.VK_ADD) {
			scale+=5;
		}
		else if(key == KeyEvent.VK_SUBTRACT){
			if(scale!=0) {
				scale-=5;
			}
		}
		else if(key==KeyEvent.VK_DOWN) {
			origin_y+=scale;
		}
		else if(key == KeyEvent.VK_UP) {
			origin_y-=scale;
		}
		else if(key == KeyEvent.VK_LEFT) {
			origin_x-=scale;
		}
		else if(key == KeyEvent.VK_RIGHT) {
			origin_x+=scale;
		}
		else if(key == KeyEvent.VK_B) {
			b = JOptionPane.showInputDialog(this,"Enter Coordinates(x1,y1,x2,y2):");
			B = true;
		}
		else if(key == KeyEvent.VK_D) {
			dda = JOptionPane.showInputDialog(this,"Enter Coordinates(x1,y1,x2,y2):");
			DDA = true;
		}
		else if(key == KeyEvent.VK_M) {
			MP = true;
			midpoint = JOptionPane.showInputDialog(this,"Enter Coordinates(x1,y1,x2,y2):");
		}
		repaint();
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		
	}

}
