package Twig;

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

public class Twig extends JFrame implements MouseMotionListener, KeyListener {

	int X,Y;
	Graph graph;
	Line lines;
	JOptionPane p;
	
	boolean graphCoordinates = false;
	
	public Twig() {
		graph = null;
		lines = null;
		addMouseMotionListener(this);
		addKeyListener(this);
		setSize(1000,1000);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public static void main(String[] args) {
		new Twig();

	}
	
	public void paint(Graphics g) {
		g.clearRect(0, 0, getWidth(), getHeight());
		if(this.graph==null) {
			this.graph = new Graph(getWidth()/2,getHeight()-50,5,1000,1000,Color.BLACK,
					new Color(132,187,235));
		}
		graph.setG(g);
		if(graphCoordinates) {
			graph.drawCoordinates();
			lines = new Line(graph,Color.RED);
			//drawTwig(lines,60,90,0,0);
			drawTwigRandom(lines,50,90,0,0,0);
		}
		else {
			graph.drawCoordinates();
			lines = new Line(graph,Color.RED);
			drawTwig(lines,50,90,0,0);
		}
	}
	
	static void drawTwig(Line lines,int length,int angle,int x,int y) {
		int change = 45;
		if(angle==90) {
			String s = Integer.toString(x)+" "+Integer.toString(y)+" "+Integer.toString(x)
			+" "+Integer.toString(y+length);
			lines.drawDDA(s);
			if(length/2>0) {
				drawTwig(lines,length/2,angle+change,x,y+length);
				drawTwig(lines,length/2,angle-change,x,y+length);
			}
		}
		else if(angle==270) {
			String s = Integer.toString(x)+" "+Integer.toString(y)+" "+Integer.toString(x)
			+" "+Integer.toString(y-length);
			lines.drawDDA(s);
			if(length/2>0) {
				drawTwig(lines,length/2,angle+change,x,y-length);
				drawTwig(lines,length/2,angle-change,x,y-length);
			}
		}
		else {
			int y2 = (int) (Math.sin(Math.toRadians(angle))*length) +y;
			int x2 = (int) (Math.cos(Math.toRadians(angle))*length) + x;
			//System.out.println(x2+" "+y2);
			String s = Integer.toString(x)+" "+Integer.toString(y)+" "+Integer.toString(x2)
			+" "+Integer.toString(y2);
			lines.drawDDA(s);
			if(length/2>0) {
				drawTwig(lines,length/2,angle+change,x2,y2);
				drawTwig(lines,length/2,angle-change,x2,y2);
			}
		}
	}
	
	static void drawTwigRandom(Line lines,int length,int angle,int x,int y,int call) {
		if(call == 6) {
			return;
		}
		Random rand = new Random();
		int change = rand.nextInt(30)+30;
		int a = length - rand.nextInt(length/2);
		int b = length - rand.nextInt(length/2);
		int x2=0,y2=0;
		if(angle==90) {
			String s = Integer.toString(x)+" "+Integer.toString(y)+" "+Integer.toString(x)
			+" "+Integer.toString(y+length);
			x2=x;
			y2=y+length;
			lines.drawDDA(s);
		}
		else if(angle==270) {
			String s = Integer.toString(x)+" "+Integer.toString(y)+" "+Integer.toString(x)
			+" "+Integer.toString(y-length);
			lines.drawDDA(s);
			x2=x;
			y2=y-length;
		}
		else {
			y2 = (int) (Math.sin(Math.toRadians(angle))*length) +y;
			x2 = (int) (Math.cos(Math.toRadians(angle))*length) + x;
			//System.out.println(x2+" "+y2);
			String s = Integer.toString(x)+" "+Integer.toString(y)+" "+Integer.toString(x2)
			+" "+Integer.toString(y2);
			lines.drawDDA(s);
		}
		drawTwigRandom(lines,a,angle+change,x2,y2,call+1);
		drawTwigRandom(lines,b,angle-change,x2,y2,call+1);
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
			graph.setScale(graph.getScale()+5);
		}
		else if(key == KeyEvent.VK_SUBTRACT){
			if(graph.getScale()>5) {
				graph.setScale(graph.getScale()-5);
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
