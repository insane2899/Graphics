package LineClipping;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Graph.Graph;
import Polygon.Polygon;

public class MainCanvas extends JFrame implements MouseMotionListener, KeyListener {

	int X,Y;
	Graph graph;
	JOptionPane p;
	int xs,ys;
	
	boolean graphCoordinates = false;
	
	public MainCanvas() {
		graph = null;
		addMouseMotionListener(this);
		addKeyListener(this);
		setSize(1000,1000);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public static void main(String[] args) {
		new MainCanvas();

	}
	
	public void paint(Graphics g) {
		g.clearRect(0, 0, getWidth(), getHeight());
		if(this.graph==null) {
			this.graph = new Graph(getWidth()/2,getHeight()/2,8,1000,1000,Color.BLUE,
					Color.CYAN);
		}
		graph.setG(g);
		if(graphCoordinates) {
			graph.drawCoordinates();
			Polygon border = new Polygon(graph,null,null);
			int[] x = {0+xs,-20+xs,20+xs};
			int[] y = {0+ys,20+ys,20+ys};
			border.setPoints(x,y);
			Polygon clipped = new Polygon(graph,null,null);
			int[] x1 = {0,-10,10};
			int[] y1 = {30,5,5};
			clipped.setPoints(x1, y1);
			SutherlandHodgeman sh = new SutherlandHodgeman(graph,border,Color.RED,Color.YELLOW,
					Color.BLACK);
			sh.drawPolygon(clipped);
			sh.drawBorder();
			/*
			PolygonClipping pc = new PolygonClipping(graph,border,Color.RED,Color.YELLOW,Color.BLACK);
			//pc.drawLine(-100, 10, 100, 10);
			pc.drawPolygon(clipped);
			pc.drawBorder();
			*/
			/*
			CohenSutherland cs = new CohenSutherland(graph,10+xs,15+ys,50+xs,55+ys,Color.RED,Color.YELLOW,Color.BLACK);
			int[] x = {0,30,60};
			int[] y = {25,70,25};
			Polygon triangle = new Polygon(graph,null,null);
			triangle.setPoints(x, y);
			cs.drawRectangle();
			cs.drawPolygon(triangle);
			*/
			/*
			 * cs.drawLine(0, 25, 30, 70); cs.drawLine(30, 70, 60, 25); cs.drawLine(60, 25,
			 * 0, 25); cs.drawRectangle();
			 */
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
			repaint();
		}
		else if(key == KeyEvent.VK_SUBTRACT){
			if(graph.getScale()>2) {
				graph.setScale(graph.getScale()-2);
				repaint();
			}
		}
		else if(key==KeyEvent.VK_DOWN) {
			graph.setOrigin_y(graph.getOrigin_y()+graph.getScale());
			repaint();
		}
		else if(key == KeyEvent.VK_UP) {
			graph.setOrigin_y(graph.getOrigin_y()-graph.getScale());
			repaint();
		}
		else if(key == KeyEvent.VK_LEFT) {
			graph.setOrigin_x(graph.getOrigin_x()-graph.getScale());
			repaint();
		}
		else if(key == KeyEvent.VK_RIGHT) {
			graph.setOrigin_x(graph.getOrigin_x()+graph.getScale());
			repaint();
		}
		else if(key == KeyEvent.VK_G) {
			graphCoordinates = true;
			repaint();
		}
		else if(key == KeyEvent.VK_N) {
			graphCoordinates = false;
			repaint();
		}
		else if(key==KeyEvent.VK_NUMPAD2) {
			ys-=5;
			repaint();
		}
		else if(key==KeyEvent.VK_NUMPAD4) {
			xs-=5;
			repaint();
		}
		else if(key==KeyEvent.VK_NUMPAD6) {
			xs+=5;
			repaint();
		}
		else if(key == KeyEvent.VK_NUMPAD8) {
			ys+=5;
			repaint();
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		
	}

}