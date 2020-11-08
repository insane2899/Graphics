package Polygon;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.Random;

import Lines.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Graph.Graph;

public class Canvas extends JFrame implements MouseMotionListener, KeyListener {

	int X,Y;
	Graph graph;
	JOptionPane p;
	OldPolygon polygon;
	
	boolean graphCoordinates = false;
	
	public Canvas() {
		graph = null;
		addMouseMotionListener(this);
		addKeyListener(this);
		setSize(1000,1000);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public static void main(String[] args) {
		new Canvas();

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
			this.polygon = new OldPolygon(graph,Color.BLACK,Color.GREEN);
			int[] x2 = {-15,0,15};
			int[] y2 = {60,80,60};
			polygon.setPoints(x2, y2);
			polygon.fillPolygon();
			for(int i=0;i<3;i++) {
				y2[i]-=10;
			}
			polygon.setPoints(x2, y2);
			polygon.fillPolygon();
			for(int i=0;i<3;i++) {
				y2[i]-=10;
			}
			polygon.setPoints(x2, y2);
			polygon.fillPolygon();
			polygon.setFill(new Color(227,166,166));;
			int[] x1= {-5,-5,5,5};
			int[] y1= {0,40,40,0};
			polygon.setPoints(x1, y1);
			polygon.fillPolygon();
			int[] x= {-20,-20,20,20};
			int[] y = {-10,10,10,-10};
			polygon.setFill(new Color(148,25,25));
			polygon.setPoints(x, y);
			polygon.fillPolygon();
			
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
