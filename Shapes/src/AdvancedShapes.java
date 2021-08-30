import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;

public class AdvancedShapes extends JFrame implements MouseMotionListener{
	
	int X,Y;
	
	public AdvancedShapes() {
		setSize(800,800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		X=0;
		Y=0;
		addMouseMotionListener(this);
	}

	public static void main(String[] args) {
		new AdvancedShapes();
	}
	
	public void paint(Graphics g) {
		//g.drawOval(100,100,200,100);
		g.clearRect(0, 0, getWidth(), getHeight());
		
		//ground
		g.setColor(Color.GREEN);
		g.fillRect(0, 700, getWidth(),getHeight()-700);
		
		//sky
		g.setColor(new Color(189,186,243));
		g.fillRect(0, 0, getWidth(), 700);
		
		//tree
		int lshift = 20;
		g.setColor(new Color(142,74,6));
		g.fillRect(100+lshift-6,475,40,700-475);
		g.setColor(new Color(59,146,22));
		int radius = 70;
		g.fillOval(55-radius+lshift,460-radius,2*radius,2*radius);
		g.fillOval(190-radius, 460-radius, 2*radius, 2*radius);
		g.fillOval(134-radius, 370-radius, 2*radius, 2*radius);
		
		//house
		int[] x = {250,250,315,380,380};
		int[] y = {700,560,440,560,700};
		g.setColor(new Color(199,183,5));
		g.fillPolygon(x, y, 5);
		
		int[] xb = {380,380,700,700};
		int[] yb = {700,560,560,700};
		g.fillPolygon(xb,yb,4);
		
		int[] xa = {230,250,315};
		int[] ya = {560,560,440};
		g.setColor(new Color(50,48,21));
		g.fillPolygon(xa,ya,3);
		
		int[] xc = {380,315,315+700-380,700};
		int[] yc = {560,440,440,560};
		g.setColor(new Color(84,81,33));
		g.fillPolygon(xc,yc,4);
		
		g.setColor(Color.BLACK);
		g.drawLine(380, 560, 380, 700);
		
		g.setColor(Color.BLACK);
		g.fillRect(500, 600, 40, 700-600);
		
		g.fillRect(300,580,30,30);
		
		//Helicopter
		int[] xh = {250,250,420,750,420};
		int[] yh = {260,230,145,245,345};
		g.setColor(new Color(188,42,26));
		g.fillPolygon(xh,yh,5);
		int rad = 50;
		g.setColor(new Color(177,246,232));
		g.fillOval(750-rad, 245-rad, 2*rad, 2*rad);
		
		g.setColor(Color.WHITE);
		int[] xw = {265,420,420};
		int[] yw = {245,245,160};
		g.fillPolygon(xw,yw,3);
		
		g.setColor(Color.BLACK);
		g.fillRect(415, 130, 10,15 );
		g.fillRect(435, 135, 700-435, 5);
		g.fillRect(140, 135, 700-435, 5);
	
		g.fillRect(415, 345, 10, 10);
		g.fillRect(250, 355, 700-250, 5);
		
		g.drawArc(750-30, 245-30, 2*30,2*30, 90, 90);
		g.drawArc(750-30, 245-30, 2*30, 2*30, 270, 90);
		g.drawArc(750-15, 245-15, 2*15, 2*15, 0, 90);
		g.drawArc(750-15, 245-15, 2*15, 2*15, 180, 90);
		
		
		g.setColor(Color.BLACK);
		g.drawString("Mouse Position: X: "+X+" Y: "+Y, 10, 790);
		
	}

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

}
