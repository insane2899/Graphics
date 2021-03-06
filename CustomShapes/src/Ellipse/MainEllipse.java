package Ellipse;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Circle.Circle;
import Graph.Graph;
import Lines.Line;

public class MainEllipse extends JFrame implements MouseMotionListener, KeyListener {

	int X,Y;
	Graph graph;
	JOptionPane p;
	Ellipse ellipse;
	
	boolean graphCoordinates = false;
	boolean move = false;
	
	public MainEllipse() {
		graph = null;
		addMouseMotionListener(this);
		addKeyListener(this);
		setSize(1000,1000);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public static void main(String[] args) {
		new MainEllipse();

	}
	
	public void paint(Graphics g) {
		g.clearRect(0, 0, getWidth(), getHeight());
		if(this.graph==null) {
			this.graph = new Graph(getWidth()/2,getHeight()/2,8,1000,1000,Color.BLACK,
					new Color(132,187,235));
		}
		graph.setG(g);
		if(graphCoordinates) {
			graph.drawCoordinates();
			ellipse = new Ellipse(graph,Color.BLACK,Color.RED,0,0,20,40);
			ellipse.setCentreColor(Color.BLACK);
			ellipse.getFillEllipse();
			ellipse.rotateAngle(30,0,0);
			ellipse.drawModifiedEllipse();
			//ellipse.fillEllipse();
			if(move) {
				ellipse.rollAction(20,0);
			}
		}
		//g.setColor(Color.BLACK);
		//g.drawString("Mouse Location: X:"+X+" Y:"+Y, 10, getHeight()-10);
	}
	/*	Doesn't get used as already made same method in Ellipse class

	public void rollAction(int xf,int yf,Ellipse ellipse) {
		int xi = ellipse.getX_centre();
		int yi = ellipse.getY_centre();
		int a = ellipse.getA();
		int b = ellipse.getB();
		Color centreColor = ellipse.getCentreColor();
		int[][] points = Line.getpointsDDA(xi, xf, yi, yf);
		int n = points.length;
		for(int i=0;i<n-1;i++) {
			try {
				Thread.sleep(1000);
				ellipse = new Ellipse(graph,Color.BLACK,Color.RED,points[i][0],points[i][1],a,b);
				ellipse.setCentreColor(centreColor);
				graph.drawCoordinates();
				ellipse.fillEllipse();
			}catch(Exception e) {}
		}
		try {
			Thread.sleep(1000);
			ellipse = new Ellipse(graph,Color.BLACK,Color.red,xf,yf,a,b);
			ellipse.setCentreColor(centreColor);
			graph.drawCoordinates();
			ellipse.fillEllipse();
		}catch(Exception e) {}
	}*/
	
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
		else if(key == KeyEvent.VK_R) {
			move=true;
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