package Custom;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import Circle.Circle;
import Ellipse.Ellipse;
import Graph.Graph;
import Polygon.Polygon;

public class BirdPanel extends JPanel implements MouseListener,Animable {

	private int X,Y;
	private Graph graph;
	
	private boolean graphCoordinates ;
	private boolean graphChanged;
	private boolean multipleBird;
	private int xcoord,ycoord;
	private int angle;
	private int graphSize;
	private int scale_x,scale_y,stage,speed,xcentre,ycentre;
	
	public BirdPanel() {
		super();
		this.graphSize = 6;
		graph = null;
		xcoord=0;
		ycoord=0;
		multipleBird = false;
		graphCoordinates = true;
		scale_x=1;
		stage = 3;
		graphChanged = true;
		scale_y=1;
		angle = 0;
		addMouseListener(this);
		setSize(1000,1000);
		xcentre = getWidth()/2;
		ycentre = getHeight()/2;
		setVisible(true);
	}
	
	@Override
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	public int getXcoord() {
		return xcoord;
	}

	public void setXcoord(int xcoord) {
		this.xcoord = xcoord;
	}
	
	@Override
	public void setAnimate() {
		flyBird();
	}

	public int getYcoord() {
		return ycoord;
	}

	@Override
	public void setYcoord(int ycoord) {
		this.ycoord = ycoord;
	}

	public int getAngle() {
		return angle;
	}

	@Override
	public void setAngle(int angle) {
		this.angle = angle;
	}

	public int getScale_x() {
		return scale_x;
	}

	@Override
	public void setScale_x(int scale_x) {
		this.scale_x = scale_x;
	}

	public int getScale_y() {
		return scale_y;
	}

	@Override
	public void setScale_y(int scale_y) {
		this.scale_y = scale_y;
	}
	
	@Override
	public void setGroup() {
		this.multipleBird = !this.multipleBird;
	}
	
	@Override
	public void setGraphSize(int size) {
		this.graphChanged = true;
		this.graphSize = size;
	}

	public void paintComponent(Graphics g) {
		g.clearRect(0, 0, getWidth(), getHeight());
		if(this.graph==null||this.graphChanged) {
			this.graph = new Graph(xcentre,ycentre,graphSize,1000,1000,Color.BLACK,
					Color.CYAN);
			this.graphChanged = false;
		}
		graph.setG(g);
		graph.drawCoordinates();
		if(!multipleBird) {
			drawBird(xcoord,ycoord,stage,angle,scale_x,scale_y);
		}
		else {
			drawBird(40,40,stage,angle,scale_x,scale_y);
			drawBird(40,-40,stage,angle,scale_x,scale_y);
			drawBird(-40,-40,stage,angle,scale_x,scale_y);
			drawBird(-40,40,stage,angle,scale_x,scale_y);
			//multipleBird=false;
		}
	}
	
	private void flyBird() {
		new Timer(1100-this.speed*100,new ActionListener() {
			int i = 0;
			int initialx=xcoord;
			int initialy=ycoord;
			@Override
			public void actionPerformed(ActionEvent e) {
				i-=10;
				if(i==-130) {
					System.out.println("Stopped");
					((Timer)e.getSource()).stop();
				}
				else {
					xcoord = i+initialx;
					ycoord = -i+initialy;
					stage = (i/10)%6;
					repaint();
				}
			}
		}).start();
	}
	
	public void drawBird(int w,int h,int stage,int angle,int scale_x,int scale_y) {
		stage = Math.abs(stage);
		//head
		Circle head = new Circle(graph,Color.BLACK,Color.YELLOW,-19+w,8+h,8);
		head.getFillEllipse();
		head.rotateAngle(angle, 8+w, 1+h);
		head.scaleEllipse(scale_x, scale_y);
		head.drawModifiedEllipse();
		//head.fillEllipse();
		
		//top body
		Ellipse body = new Ellipse(graph,Color.BLACK,Color.YELLOW,8+w,0+h,20,10);
		body.getFillEllipse();
		body.rotateAngle(angle, 8+w, 1+h);
		body.scaleEllipse(scale_x, scale_y);
		body.drawModifiedEllipse();
		//body.fillEllipse();
		
		//wing
		if(stage==2||stage==4) {
			Circle wing = new Circle(graph,Color.BLACK,Color.PINK,8+w,-4+h,8);
			wing.getFillEllipseTop(1+h);
			wing.rotateAngleTop(angle, 8+w, 1+h,1+h);
			wing.scaleEllipseTop(scale_x, scale_y,1+h);
			wing.drawModifiedEllipse();
			//wing.fillEllipseTop(1+h);
			
		}
		else if(stage==0){
			Circle wing = new Circle(graph,Color.BLACK,Color.PINK,8+w,-4+h,8);
			wing.getFillEllipseBottom(1+h);
			wing.rotateAngleBottom(angle,8+w,1+h,1+h);
			wing.scaleEllipseBottom(scale_x, scale_y,1+h);
			wing.drawModifiedEllipse();
			//wing.fillEllipseBottom(1+h);
		}
		else if(stage==1||stage==5) {
			Circle wing = new Circle(graph,Color.BLACK,Color.PINK,8+w,6+h,8);
			wing.getFillEllipseBottom(1+h);
			wing.rotateAngleBottom(angle, 8+w, 1+h, 1+h);
			wing.scaleEllipseBottom(scale_x, scale_y,1+h);
			wing.drawModifiedEllipse();
			//wing.fillEllipseBottom(1+h);
		}
		else if(stage==3){
			Circle wing = new Circle(graph,Color.BLACK,Color.PINK,8+w,6+h,8);
			wing.getFillEllipseTop(1+h);
			wing.rotateAngleTop(angle,8+w, 1+h, 1+h);
			wing.scaleEllipseTop(scale_x, scale_y,1+h);
			wing.drawModifiedEllipse();
			//wing.fillEllipseTop(1+h);
		}
		
		//eye
		Circle eye = new Circle(graph,Color.BLACK,Color.BLACK,-19+w,12+h,1);
		eye.setBorderThickness(5);
		eye.getFillEllipse();
		eye.rotateAngle(angle,8+w, 1+h);
		eye.scaleEllipse(scale_x, scale_y);
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
	public void mouseClicked(MouseEvent arg0) {
		X = arg0.getX();
		Y = arg0.getY();
		xcoord = (int) graph.getCartisanX(X);
		ycoord = (int) graph.getCartisanY(Y);
		//System.out.println(xcoord+" "+ycoord);
		repaint();
		
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
		//System.out.println("Pressed");
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}

