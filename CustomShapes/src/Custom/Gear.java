package Custom;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Circle.Circle;
import Ellipse.OldEllipse;
import Graph.Graph;
import Polygon.OldPolygon;

public class Gear extends JFrame implements MouseMotionListener, KeyListener {

	int X,Y;
	Graph graph;
	JOptionPane p;
	
	boolean graphCoordinates = false;
	boolean fill = false;
	
	public Gear() {
		graph = null;
		addMouseMotionListener(this);
		addKeyListener(this);
		setSize(1000,1000);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public static void main(String[] args) {
		new Gear();

	}
	
	public void paint(Graphics g) {
		g.clearRect(0, 0, getWidth(), getHeight());
		if(this.graph==null) {
			this.graph = new Graph(getWidth()/2,getHeight()/2,6,1000,1000,Color.BLACK,
					Color.BLUE);
		}
		graph.setG(g);
		if(graphCoordinates) {
			graph.drawCoordinates();
			
			//gear extensions
			OldPolygon one = new OldPolygon(graph,Color.GRAY,Color.GRAY);
			int[] x1 = {3,-3,-3,3};
			int[] y1 = {20,20,-20,-20};
			one.setPoints(x1, y1);
			one.fillPolygon();
			
			OldPolygon two = new OldPolygon(graph,Color.GRAY,Color.GRAY);
			int[] x2 = {20,20,-20,-20};
			int[] y2 = {3,-3,-3,3};
			two.setPoints(x2, y2);
			two.fillPolygon();
			
			OldPolygon three = new OldPolygon(graph,Color.GRAY,Color.GRAY);
			int[] x3 = {12,16,-12,-16};
			int[] y3 = {16,12,-16,-12};
			three.setPoints(x3, y3);
			three.fillPolygon();
			
			OldPolygon four = new OldPolygon(graph,Color.GRAY,Color.GRAY);
			int[] x4 = {-12,-16,12,16};
			int[] y4 = {16,12,-16,-12};
			four.setPoints(x4, y4);
			four.fillPolygon();
			
			//rings
			Circle outer = new Circle(graph,Color.GRAY,Color.GRAY,0,0,15);
			outer.fillEllipse();
			
			Circle innter = new Circle(graph,Color.GRAY,Color.BLUE,0,0,8);
			innter.fillEllipse();
			
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
		else if(key == KeyEvent.VK_F) {
			fill=true;
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
