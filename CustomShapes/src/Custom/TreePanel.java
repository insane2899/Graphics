package Custom;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import Polygon.Polygon;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import Graph.Graph;

public class TreePanel extends JPanel implements MouseListener,Animable{

	private static final long serialVersionUID = 1L;
	private int X,Y;
	private Graph graph;
	private int angle,scale_x,scale_y,xcoord,ycoord,graphSize;
	private int speed;
	private boolean multipleTree;
	private int xcentre,ycentre;
	
	private boolean hasChange;
	
	public TreePanel() {
		graph = null;
		angle=0;
		scale_x=1;
		scale_y=1;
		graphSize = 6;
		xcoord = 0;
		multipleTree = false;
		ycoord=0;
		hasChange=true;
		addMouseListener(this);
		setSize(1000,1000);
		xcentre = getWidth()/2;
		ycentre = getHeight()-150;
		setVisible(true);
	}
	
	@Override
	public void setGroup() {
		this.multipleTree=!this.multipleTree;
	}
	
	@Override
	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public static void main(String[] args) {
		new TreePanel();

	}
	
	public void paintComponent(Graphics g) {
		g.clearRect(0, 0, getWidth(), getHeight());
		if(this.graph==null||hasChange) {
			this.graph = new Graph(xcentre,ycentre,graphSize,1000,1000,Color.BLACK,
					new Color(132,187,235));
			hasChange=false;
		}
		graph.setG(g);
		graph.drawCoordinates();
		if(!multipleTree) {
			drawTree(xcoord,ycoord,angle);
		}
		else {
			drawTree(-40,-60,angle);
			drawTree(-40,60,angle);
			drawTree(40,-60,angle);
			drawTree(40,60,angle);
		}
		//g.setColor(Color.BLACK);
		//g.drawString("Mouse Location: X:"+X+" Y:"+Y, 10, getHeight()-10);
	}
	
	private void drawTree(int xc,int yc,int a) {

		//Base
		Polygon polygon5 = new Polygon(graph,Color.BLACK,new Color(148,25,25));
		int[] x= {-20+xc,-20+xc,20+xc,20+xc};
		int[] y = {-10+yc,10+yc,10+yc,-10+yc};
		polygon5.setPoints(x, y);
		//polygon5.rotateAngle(angle,0,0);
		polygon5.getFillPolygon();
		polygon5.scalePolygon(scale_x, scale_y);
		polygon5.drawModifiedPolygon();
		
		//Trunk
		Polygon polygon4 = new Polygon(graph,Color.BLACK,new Color(227,166,166));
		int[] x1= {-5+xc,-5+xc,5+xc,5+xc};
		int[] y1= {0+yc,40+yc,40+yc,0+yc};
		polygon4.setPoints(x1, y1);
		polygon4.getFillPolygon();
		polygon4.rotateAngle(a,xc,yc);
		polygon4.scalePolygon(scale_x, scale_y);
		polygon4.drawModifiedPolygon();
		
		int[] x2 = {-15+xc,0+xc,15+xc};
		int[] y2 = {40+yc,60+yc,40+yc};
		
		//Bottom
		Polygon polygon3 = new Polygon(graph,Color.BLACK,Color.GREEN);
		polygon3.setPoints(x2, y2);
		polygon3.getFillPolygon();
		polygon3.rotateAngle(a,xc,yc);
		polygon3.scalePolygon(scale_x, scale_y);
		polygon3.drawModifiedPolygon();
		
		//Middle
		Polygon polygon2 = new Polygon(graph,Color.BLACK,Color.GREEN);
		for(int i=0;i<3;i++) {
			y2[i]+=10;
		}
		polygon2.setPoints(x2, y2);
		polygon2.getFillPolygon();
		polygon2.rotateAngle(a,xc,yc);
		polygon2.scalePolygon(scale_x, scale_y);
		polygon2.drawModifiedPolygon();
		
		//Top
		for(int i=0;i<3;i++) {
			y2[i]+=10;
		}
		Polygon polygon = new Polygon(graph,Color.BLACK,Color.GREEN);
		polygon.setPoints(x2, y2);
		polygon.getFillPolygon();
		polygon.rotateAngle(a,xc,yc);
		polygon.scalePolygon(scale_x, scale_y);
		polygon.drawModifiedPolygon();
		
	}
	
	public void fall() {
		new Timer(600-this.speed*100,new ActionListener() {
			int count = 0;
			@Override
			public void actionPerformed(ActionEvent e) {
				if(count==-95) {
					System.out.println("Stopped");
					((Timer)e.getSource()).stop();
				}
				else {
					angle = count;
					repaint();
				}
				count-=5;
			}
		}).start();
	}
	//Inherited abstract methods

	@Override
	public void setAnimate() {
		fall();
	}

	@Override
	public void setXcoord(int Xcoord) {
		this.xcoord = Xcoord;
		
	}

	@Override
	public void setYcoord(int Ycoord) {
		this.ycoord = Ycoord;
		
	}

	@Override
	public void setAngle(int angle) {
		this.angle = angle;
		
	}

	@Override
	public void setScale_x(int scale) {
		this.scale_x = scale;
		
	}

	@Override
	public void setScale_y(int scale) {
		this.scale_y = scale;
		
	}
	
	@Override
	public void setGraphSize(int size) {
		this.graphSize=size;
		hasChange=true;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		X = arg0.getX();
		Y = arg0.getY();
		if(arg0.getButton()==MouseEvent.BUTTON1) {
			xcoord = (int) graph.getCartisanX(X);
			ycoord = (int) graph.getCartisanY(Y);
			repaint();
			
		}
		else if(arg0.getButton()==MouseEvent.BUTTON3) {
			//System.out.println("Here");
			//drawTree((int)graph.getCartisanX(X),(int)graph.getCartisanY(Y),this.angle);
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


}

