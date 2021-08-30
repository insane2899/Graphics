package BlackHoleAlgo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashSet;
import java.util.StringTokenizer;

import Circle.Circle;
import Graph.Graph;
import Lines.Line;

public class Animation extends JFrame implements MouseMotionListener, KeyListener {

	int X,Y;
	Graph graph;
	
	boolean graphCoordinates = false;
	
	public Animation() {
		graph = null;
		addMouseMotionListener(this);
		addKeyListener(this);
		setSize(1000,1000);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public static void main(String[] args) {
		new Animation();

	}
	
	public void paint(Graphics g) {
		g.clearRect(0, 0, getWidth(), getHeight());
		if(this.graph==null) {
			this.graph = new Graph(40,getHeight()-40,80,100,100,Color.WHITE,
					Color.BLACK);
		}
		graph.setG(g);
		if(graphCoordinates) {
			double[][] oldCoordinates = new double[10][2];
			double[][] newCoordinates = new double[10][2];
			HashSet<Integer> newStars = new HashSet<Integer>();
			double[] BH = new double[2];
			double rad = 0.0;
			try {
				BufferedReader br = new BufferedReader(new FileReader("src/BlackHoleAlgo/Input.txt"));
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int i=0;i<10;i++) {
					oldCoordinates[i][0]=Double.parseDouble(st.nextToken());
					oldCoordinates[i][1]=Double.parseDouble(st.nextToken());
				}
				BH[0]=Double.parseDouble(st.nextToken());
				BH[1]=Double.parseDouble(st.nextToken());
				rad = Double.parseDouble(st.nextToken());
				drawSpace(oldCoordinates,BH,newStars,(int)rad);
				for(int i=1;i<20;i++) {
					newStars.clear();
					StringTokenizer st1 = new StringTokenizer(br.readLine());
					for(int j=0;j<10;j++) {
						newCoordinates[j][0]=Double.parseDouble(st1.nextToken());
						newCoordinates[j][1]=Double.parseDouble(st1.nextToken());
					}
					BH[0]=Double.parseDouble(st1.nextToken());
					BH[1]=Double.parseDouble(st1.nextToken());
					rad = Double.parseDouble(st1.nextToken());
					while(st1.hasMoreTokens()) {
						newStars.add(Integer.parseInt(st1.nextToken()));
					}
					System.out.println(newStars);
					shiftStars(oldCoordinates,newCoordinates,newStars,BH);
					Thread.sleep(5000);
				}
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			
		}
	}
	
	void shiftStars(double[][] oldCoordinates,double[][] newCoordinates,HashSet<Integer> newStars,double[] BH) {
		int countSteps = 5;
		double[][] ddiff = new double[10][2];
		for(int i=0;i<10;i++) {
			if(!newStars.contains(i)) {
				ddiff[i][0]=newCoordinates[i][0]-oldCoordinates[i][0];
				ddiff[i][1]=newCoordinates[i][1]-oldCoordinates[i][1];
				ddiff[i][0]/=5;
				ddiff[i][1]/=5;
			}
			else {
				oldCoordinates[i][0]=newCoordinates[i][0];
				oldCoordinates[i][1]=newCoordinates[i][1];
			}
		}
		for(int i=0;i<countSteps;i++) {
			drawSpace(oldCoordinates,BH,newStars,5);
			for(int j=0;j<10;j++) {
				oldCoordinates[j][0]+=ddiff[j][0];
				oldCoordinates[j][1]+=ddiff[j][1];
			}
			try {
				Thread.sleep(500);
			}catch(Exception e) {}
		}
		drawSpace(oldCoordinates,BH,newStars,5);
		
	}
	
	void drawSpace(double[][] oldCoordinates,double[] BH,HashSet<Integer> idx,int rad) {
		graph.drawCoordinates();
		for(int i=0;i<10;i++) {
			if(idx.contains(i)) {
				newStar(oldCoordinates[i][0],oldCoordinates[i][1]);
			}
			else {
				plotStar(oldCoordinates[i][0],oldCoordinates[i][1]);
			}
		}
		plotBH(BH[0],BH[1],rad);
	}
	
	void movePoint(double[][] oldCoordinates,double[][] newCoordinates,int idx) {
		
	}
	
	void plotStar(double x,double y){
		this.graph.plotPoint(x,y,Color.YELLOW);
	}
	
	void plotBH(double x,double y,int r) {
		this.graph.plotPoint(x,y,new Color(255,128,0),r);
		this.graph.plotPoint(x,y,Color.RED);
	}
	
	void newStar(double x,double y) {
		this.graph.plotPoint(x,y,Color.BLUE);
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
