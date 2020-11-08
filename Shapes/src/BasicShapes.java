import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class BasicShapes extends JFrame implements MouseMotionListener{
	
	int X=0,Y=0;
	
	public BasicShapes() {
		setSize(800,800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		addMouseMotionListener(this);
	}
	
	public static void main(String[] args) {
		new BasicShapes();
	}
	
	public void paint(Graphics g) {
		g.clearRect(0, 0, getWidth(), getHeight());
		g.setColor(Color.BLUE);
		g.fillRect(50,80,200,200);
		g.setColor(Color.BLACK);
		g.drawString("A Square", 110, 180);
		g.setColor(Color.RED);
		g.fillOval(500,80,200,200);
		g.setColor(Color.BLACK);
		g.drawString("A Circle", 575, 180);
		g.setColor(Color.YELLOW);
		g.fillRect(50,500,200,100);
		g.setColor(Color.BLACK);
		g.drawString("A Rectangle", 110, 550);
		g.setColor(Color.GREEN);
		g.fillOval(500,500,200,100);
		g.setColor(Color.BLACK);
		g.drawString("An Ellipse", 580, 550);
		g.drawString("Mouse Position: X: "+X+" Y: "+Y, 10, 790);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		X = e.getX();
		Y = e.getY();
		revalidate();
		repaint();
	}
}
