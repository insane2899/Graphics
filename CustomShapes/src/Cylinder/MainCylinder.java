package Cylinder;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Ellipse.OldEllipse;
import Graph.Graph;

public class MainCylinder extends JFrame implements MouseMotionListener, KeyListener {

	int X,Y;
	Graph graph;
	JOptionPane p;
	Cylinder cylinder;
	
	boolean graphCoordinates = false;
	boolean fill = false;
	
	public MainCylinder() {
		graph = null;
		addMouseMotionListener(this);
		addKeyListener(this);
		setSize(1000,1000);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public static void main(String[] args) {
		new MainCylinder();

	}
	
	public void paint(Graphics g) {
		g.clearRect(0, 0, getWidth(), getHeight());
		if(this.graph==null) {
			this.graph = new Graph(getWidth()/2,getHeight()/2,8,1000,1000,Color.BLACK,
					Color.WHITE);
		}
		graph.setG(g);
		if(graphCoordinates) {
			graph.drawCoordinates();
			cylinder = new Cylinder(graph,20,20,40,20,10,Color.BLACK);
			cylinder.setG(graph);
			if(!fill) {
				cylinder.drawCylinder();
			}
			else {
				cylinder.fillCylinder(Color.CYAN);
			}
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