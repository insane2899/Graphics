package Custom;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import Polygon.Polygon;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Graph.Graph;

public class Tree extends JFrame implements MouseMotionListener, KeyListener {

	private static final long serialVersionUID = 1L;
	int X,Y;
	Graph graph;
	JOptionPane p;
	int angle,scale_x,scale_y;
	
	boolean graphCoordinates = false;
	
	public Tree() {
		graph = null;
		angle=0;
		scale_x=1;
		scale_y=1;
		addMouseMotionListener(this);
		addKeyListener(this);
		setSize(1000,1000);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public static void main(String[] args) {
		new Tree();

	}
	
	public void paint(Graphics g) {
		g.clearRect(0, 0, getWidth(), getHeight());
		if(this.graph==null) {
			this.graph = new Graph(getWidth()/2,getHeight()-150,10,1000,1000,Color.BLACK,
					new Color(132,187,235));
		}
		graph.setG(g);
		if(graphCoordinates) {
			graph.drawCoordinates();
			
			//Base
			Polygon polygon5 = new Polygon(graph,Color.BLACK,new Color(148,25,25));
			int[] x= {-20,-20,20,20};
			int[] y = {-10,10,10,-10};
			polygon5.setPoints(x, y);
			//polygon5.rotateAngle(angle,0,0);
			polygon5.getFillPolygon();
			polygon5.scalePolygon(scale_x, scale_y);
			polygon5.drawModifiedPolygon();
			
			//Trunk
			Polygon polygon4 = new Polygon(graph,Color.BLACK,new Color(227,166,166));
			int[] x1= {-5,-5,5,5};
			int[] y1= {0,40,40,0};
			polygon4.setPoints(x1, y1);
			polygon4.getFillPolygon();
			polygon4.rotateAngle(angle,0,0);
			polygon4.scalePolygon(scale_x, scale_y);
			polygon4.drawModifiedPolygon();
			
			int[] x2 = {-15,0,15};
			int[] y2 = {40,60,40};
			
			//Bottom
			Polygon polygon3 = new Polygon(graph,Color.BLACK,Color.GREEN);
			polygon3.setPoints(x2, y2);
			polygon3.getFillPolygon();
			polygon3.rotateAngle(angle,0,0);
			polygon3.scalePolygon(scale_x, scale_y);
			polygon3.drawModifiedPolygon();
			
			//Middle
			Polygon polygon2 = new Polygon(graph,Color.BLACK,Color.GREEN);
			for(int i=0;i<3;i++) {
				y2[i]+=10;
			}
			polygon2.setPoints(x2, y2);
			polygon2.getFillPolygon();
			polygon2.rotateAngle(angle,0,0);
			polygon2.scalePolygon(scale_x, scale_y);
			polygon2.drawModifiedPolygon();
			
			//Top
			for(int i=0;i<3;i++) {
				y2[i]+=10;
			}
			Polygon polygon = new Polygon(graph,Color.BLACK,Color.GREEN);
			polygon.setPoints(x2, y2);
			polygon.getFillPolygon();
			polygon.rotateAngle(angle,0,0);
			polygon.scalePolygon(scale_x, scale_y);
			polygon.drawModifiedPolygon();
			
		}
		//g.setColor(Color.BLACK);
		//g.drawString("Mouse Location: X:"+X+" Y:"+Y, 10, getHeight()-10);
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
		//repaint();
		
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		int key = arg0.getExtendedKeyCode();
		//System.out.println(key);
		if(key == KeyEvent.VK_ADD) {
			graph.setScale(graph.getScale()+2);
		}
		else if(key == KeyEvent.VK_SUBTRACT){
			if(graph.getScale()>2) {
				graph.setScale(graph.getScale()-2);
			}
		}
		else if(key==KeyEvent.VK_DOWN) {
			graph.setOrigin_y(graph.getOrigin_y()+graph.getScale());
		}
		else if(key == KeyEvent.VK_UP) {
			graph.setOrigin_y(graph.getOrigin_y()-graph.getScale());
		}
		else if(key == KeyEvent.VK_LEFT) {
			graph.setOrigin_x(graph.getOrigin_x()-graph.getScale());
		}
		else if(key == KeyEvent.VK_RIGHT) {
			graph.setOrigin_x(graph.getOrigin_x()+graph.getScale());
		}
		else if(key == KeyEvent.VK_G) {
			graphCoordinates = true;
		}
		else if(key == KeyEvent.VK_N) {
			graphCoordinates = false;
		}
		else if(key == KeyEvent.VK_NUMPAD7) {
			angle-=30;
		}
		else if(key==KeyEvent.VK_NUMPAD1) {
			angle+=30;
		}
		else if(key==KeyEvent.VK_B) {
			scale_x+=1;
			scale_y+=1;
		}
		else if(key==KeyEvent.VK_S) {
			scale_x=scale_x==1?scale_x:scale_x-1;
			scale_y=scale_y==1?scale_y:scale_y-1;
		}
		else {
			return;
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

