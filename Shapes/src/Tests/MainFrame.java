package Tests;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class MainFrame extends JFrame{
	
	public MainFrame() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);
		this.setSize(1000,1000);
		Link link = new Link("Hello Soumik");
		link.setBounds(500,500,100,20);
		this.add(link);
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		new MainFrame();
	}
}
