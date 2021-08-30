import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;

public class AdvancedShapes1 extends JFrame implements MouseMotionListener{
	
	int X,Y;
	
	public AdvancedShapes1() {
		setSize(800,800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		X=0;
		Y=0;
		addMouseMotionListener(this);
	}

	public static void main(String[] args) {
		new AdvancedShapes1();
	}
	
	public void paint(Graphics g) {
		//g.drawOval(100,100,200,100);
		g.clearRect(0, 0, getWidth(), getHeight());
		g.setColor(Color.GREEN);
		g.fillOval(110, 110, 110,110 );
		g.fillOval(210, 110, 110, 110);
		g.fillOval(160, 40, 110, 110);
		g.setColor(Color.BLACK);
		g.fillRect(190,190,43,150);
		g.drawString("A Tree",190,400);
		
		g.setColor(Color.RED);
		g.fillRect(500,150,200,200);
		int x[] = {500,600,700};
		int y[] = {150,40,150};
		g.setColor(Color.GREEN);
		g.fillPolygon(x,y,3);
		g.setColor(Color.BLACK);
		g.fillRect(520,200,80,150);
		g.fillRect(630,200,50,50);
		g.drawString("A Hut", 580, 400);
		
		g.setColor(Color.BLACK);
		g.drawOval(300,450,200,100);
		g.drawRect(385, 550, 10, 40);
		int xa[] = {280,280,390,700,700,390};
		int ya[] = {680,630,590,630,680,720};
		g.drawPolygon(xa,ya,6);
		g.drawString("A Helicopter", 630, 750);
		g.drawOval(700,620,80,80);
		g.drawRect(280,730,300,10);
		g.drawLine(390, 720,390, 730);
		g.drawLine(280, 630, 390, 630);
		g.drawLine(390, 590, 390, 630);
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