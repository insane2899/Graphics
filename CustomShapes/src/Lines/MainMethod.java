package Lines;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Graph.Graph;

public class MainMethod extends JFrame implements MouseMotionListener, KeyListener {

	int X,Y;
	Graph graph;
	Line lines;
	JOptionPane p;
	
	boolean coordinates,DDA,B,MP;
	String dda,b,midpoint;
	
	public MainMethod() {
		graph = null;
		lines = null;
		this.coordinates = true;
		this.DDA = false;
		this.B =false;
		addMouseMotionListener(this);
		addKeyListener(this);
		setSize(1000,1000);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public static void main(String[] args) {
		new MainMethod();

	}
	
	public void paint(Graphics g) {
		g.clearRect(0, 0, getWidth(), getHeight());
		//Cartisan Coordinates
		if(this.graph==null) {
			this.graph = new Graph(getWidth()/2,getHeight()/2,40,1000,1000,Color.BLACK,
					new Color(132,187,235));
		}
		graph.setG(g);
		if(coordinates) {
			graph.drawCoordinates();
		}
		if(lines==null) {
			lines = new Line(graph,Color.BLACK);
		}
		if(DDA) {
			lines.setColor(Color.WHITE);
			lines.drawDDA(dda);
		}
		if(B) {
			lines.setColor(Color.RED);
			lines.drawB(b);
		}
		if(MP) {
			lines.setColor(Color.YELLOW);
			lines.drawMP(midpoint);
		}
		g.setColor(Color.BLACK);
		g.drawString("Mouse Location: X:"+X+" Y:"+Y, 10, getHeight()-10);
		
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
