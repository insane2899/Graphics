package Coordinates;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Line2D;

import javax.swing.JFrame;

public class ChangeCoordinates extends JFrame implements MouseMotionListener, KeyListener {
	
	int X,Y;
	int scale; //40 pixels = 1 unit of cartisan coordinate
	final int x_size;
	final int y_size;
	int origin_x;
	int origin_y;
	
	boolean coordinates;
	
	public ChangeCoordinates(int scale,int x_size,int y_size) {
		this.x_size = x_size;
		this.y_size = y_size;
		this.scale = scale;
		this.coordinates = true;
		addMouseMotionListener(this);
		addKeyListener(this);
		setSize(1000,1000);
		this.origin_x = getWidth()/2;
		this.origin_y = getHeight()/2;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public static void main(String[] args) {
		new ChangeCoordinates(40,1000,1000);

	}
	
	public void paint(Graphics g) {
		g.clearRect(0, 0, getWidth(), getHeight());
		g.setColor(new Color(132,187,235));
		g.fillRect(0,0,getWidth(),getHeight());
		//Cartisan Coordinates
		if(coordinates) {
			drawCoordinates(g);
		}
		drawSquare(g,1,1,10,10);
		plotPoint(g,-5,-4);
		plotPoint(g,-5,4);
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
			int xmin = getProgramY(-x_size,scale);
			int xmax = getProgramY(x_size,scale);
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
		int r = 5;
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
			scale-=5;
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
