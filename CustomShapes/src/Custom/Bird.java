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
import Ellipse.Ellipse;
import Graph.Graph;
import Polygon.Polygon;


public class Bird extends JFrame implements MouseMotionListener, KeyListener {

	int X,Y;
	Graph graph;
	JOptionPane p;
	
	boolean graphCoordinates = false;
	boolean fill = false;
	boolean fly = false;
	
	int xcoord,ycoord;
	int angle;
	int scale_x,scale_y;
	
	public Bird() {
		graph = null;
		xcoord=0;
		ycoord=0;
		scale_x=1;
		scale_y=1;
		angle = 0;
		addMouseMotionListener(this);
		addKeyListener(this);
		setSize(1000,1000);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public static void main(String[] args) {
		new Bird();

	}
	
	public void paint(Graphics g) {
		g.clearRect(0, 0, getWidth(), getHeight());
		if(this.graph==null) {
			this.graph = new Graph(getWidth()/2,getHeight()/2,6,1000,1000,Color.BLACK,
					Color.CYAN);
		}
		graph.setG(g);
		if(graphCoordinates) {
			if(!fly) {
				graph.drawCoordinates();
				drawBird(xcoord,ycoord,3,angle,scale_x,scale_y);
			}
			else if(fly){
				flyBird(graph);
			}
		}
	}
	
	public void flyBird(Graph graph) {
		for(int i=0;i>=-130;i-=10) {
			graph.drawCoordinates();
			//System.out.println((i/10)%6);
			drawBird(i+xcoord,-i+ycoord,(i/10)%6,0,1,1);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void drawBird(int w,int h,int stage,int angle,int scale_x,int scale_y) {
		stage = Math.abs(stage);
		//head
		Circle head = new Circle(graph,Color.BLACK,Color.YELLOW,-19+w,8+h,8);
		head.getFillEllipse();
		head.scaleEllipse(scale_x, scale_y);
		head.rotateAngle(angle, 8+w, 1+h);
		head.drawModifiedEllipse();
		//head.fillEllipse();
		
		//top body
		Ellipse body = new Ellipse(graph,Color.BLACK,Color.YELLOW,8+w,0+h,20,10);
		body.getFillEllipse();
		body.scaleEllipse(scale_x, scale_y);
		body.rotateAngle(angle, 8+w, 1+h);
		body.drawModifiedEllipse();
		//body.fillEllipse();
		
		//wing
		if(stage==2||stage==4) {
			Circle wing = new Circle(graph,Color.BLACK,Color.PINK,8+w,-4+h,8);
			wing.getFillEllipseTop(1+h);
			wing.scaleEllipseTop(scale_x, scale_y,1+h);
			wing.rotateAngleTop(angle, 8+w, 1+h,1+h);
			wing.drawModifiedEllipse();
			//wing.fillEllipseTop(1+h);
			
		}
		else if(stage==0){
			Circle wing = new Circle(graph,Color.BLACK,Color.PINK,8+w,-4+h,8);
			wing.getFillEllipseBottom(1+h);
			wing.scaleEllipseBottom(scale_x, scale_y,1+h);
			wing.rotateAngleBottom(angle,8+w,1+h,1+h);
			wing.drawModifiedEllipse();
			//wing.fillEllipseBottom(1+h);
		}
		else if(stage==1||stage==5) {
			Circle wing = new Circle(graph,Color.BLACK,Color.PINK,8+w,6+h,8);
			wing.getFillEllipseBottom(1+h);
			wing.scaleEllipseBottom(scale_x, scale_y,1+h);
			wing.rotateAngleBottom(angle, 8+w, 1+h, 1+h);
			wing.drawModifiedEllipse();
			//wing.fillEllipseBottom(1+h);
		}
		else if(stage==3){
			Circle wing = new Circle(graph,Color.BLACK,Color.PINK,8+w,6+h,8);
			wing.getFillEllipseTop(1+h);
			wing.scaleEllipseTop(scale_x, scale_y,1+h);
			wing.rotateAngleTop(angle,8+w, 1+h, 1+h);
			wing.drawModifiedEllipse();
			//wing.fillEllipseTop(1+h);
		}
		
		//eye
		Circle eye = new Circle(graph,Color.BLACK,Color.BLACK,-19+w,12+h,1);
		eye.setBorderThickness(5);
		eye.getFillEllipse();
		eye.scaleEllipse(scale_x, scale_y);
		eye.rotateAngle(angle,8+w, 1+h);
		eye.drawModifiedEllipse();
		
		//beak
		Polygon beak = new Polygon(graph,Color.BLACK,Color.RED);
		int a=-27+w,b=-27+w,c=-32+w;
		int[] x = new int[3];
		x[0]=a;
		x[1]=b;
		x[2]=c;
		int[] y = new int[3];
		y[0]=11+h;
		y[1]=5+h;
		y[2]=8+h;
		beak.setPoints(x,y);
		beak.getFillPolygon();
		beak.rotateAngle(angle, 8+w, 1+h);
		beak.scalePolygon(scale_x,scale_y);
		beak.drawModifiedPolygon();
		//beak.fillPolygon();
		
		//tail
		Polygon tail = new Polygon(graph,Color.BLACK,Color.PINK);
		int p=28+w,q=35+w,r=33+w;
		int[] x1 = new int[3];
		x1[0]=p;
		x1[1]=q;
		x1[2]=r;
		int[] y1 = new int[3];
		y1[0]=0+h;
		y1[1]=0+h;
		y1[2]=8+h;
		tail.setPoints(x1, y1);
		tail.getFillPolygon();
		tail.rotateAngle(angle, 8+w, 1+h);
		tail.scalePolygon(scale_x, scale_y);
		tail.drawModifiedPolygon();
		//tail.fillPolygon();
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
		else if(key==KeyEvent.VK_A) {
			fly=true;
		}
		else if(key==KeyEvent.VK_NUMPAD4) {
			xcoord-=5;
			System.out.println(xcoord);
		}
		else if(key==KeyEvent.VK_NUMPAD6) {
			xcoord+=5;
		}
		else if(key==KeyEvent.VK_NUMPAD8) {
			ycoord+=5;
		}
		else if(key==KeyEvent.VK_NUMPAD2) {
			ycoord-=5;
		}
		else if(key==KeyEvent.VK_NUMPAD7) {
			angle-=30;
		}
		else if(key==KeyEvent.VK_NUMPAD1) {
			angle+=30;
		}
		else if(key == KeyEvent.VK_B) {
			scale_x+=1;
			scale_y+=1;
		}
		else if(key == KeyEvent.VK_S) {
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
