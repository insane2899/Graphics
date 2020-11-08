package Editor;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JSplitPane;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Custom.Animable;
import Custom.BirdPanel;
import Custom.TreePanel;

public class Editor {
	
	private final JFrame frame;
	private JPanel drawPanel;
	private final JPanel northPanel,southPanel;
	private final JSplitPane settingPanel;
	private final JButton bird,tree,fly;
	private final JSlider size,angle,graphSize;
	private final JLabel sizeLabel,angleLabel;
	private Container contain;
	
	private Editor() {
		this.frame = new JFrame("Editor");
		this.drawPanel = new BirdPanel();
		this.frame.setLayout(new BorderLayout());
		this.frame.setSize(new Dimension(1500,1000));
		this.frame.add(this.drawPanel,BorderLayout.CENTER);
		this.northPanel = new JPanel(new GridLayout(2,2,20,20));
		this.northPanel.setPreferredSize(new Dimension(500,30));
		this.northPanel.setBorder(BorderFactory.createEmptyBorder(100,20,100,20));
		this.southPanel = new JPanel(new GridLayout(6,1,20,20));
		this.southPanel.setPreferredSize(new Dimension(500,30));
		this.southPanel.setBorder(BorderFactory.createEmptyBorder(50,20,50,20));
		this.bird = new JButton("Bird");
		this.bird.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().remove(drawPanel);
				drawPanel = new BirdPanel();
				frame.getContentPane().add(drawPanel,BorderLayout.CENTER);
				frame.validate();
			}
		});
		this.tree = new JButton("Tree");
		this.tree.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().remove(drawPanel);
				drawPanel = new TreePanel();
				frame.getContentPane().add(drawPanel,BorderLayout.CENTER);
				frame.validate();
			}
		});
		this.fly = new JButton("Animate");
		this.fly.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				((Animable) drawPanel).setAnimate();
				drawPanel.repaint();
			}
		});
		northPanel.add(bird);
		this.sizeLabel = new JLabel("Size");
		this.angleLabel=new JLabel("Angle");
		northPanel.add(tree);
		northPanel.add(fly);
		this.size = new JSlider(1,5,1);
		size.setPaintTrack(true);
		size.setPaintTicks(true);
		size.setPaintLabels(true);
		size.setMajorTickSpacing(1);
		this.size.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				int sleepSense = size.getValue();
				((Animable) drawPanel).setScale_x(sleepSense);
				((Animable) drawPanel).setScale_y(sleepSense);
				drawPanel.repaint();
				
			}
		});
		this.angle = new JSlider(-180,180,0);
		angle.setPaintTrack(true);
		angle.setPaintTicks(true);
		angle.setPaintLabels(true);
		angle.setMajorTickSpacing(60);
		this.angle.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				int sleepSense = angle.getValue();
				((Animable)drawPanel).setAngle(-sleepSense);
				drawPanel.repaint();
			}
		});
		this.graphSize = new JSlider(1,6,6);
		graphSize.setPaintTrack(true);
		graphSize.setPaintTicks(true);
		graphSize.setPaintLabels(true);
		graphSize.setMajorTickSpacing(1);
		graphSize.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				int sleepSense = graphSize.getValue();
				((Animable)drawPanel).setGraphSize(sleepSense);
				drawPanel.repaint();
			}
		});
		southPanel.add(sizeLabel);
		southPanel.add(size);
		southPanel.add(angleLabel);
		southPanel.add(angle);
		southPanel.add(new JLabel("Graph Size"));
		southPanel.add(graphSize);
		this.settingPanel = new JSplitPane(SwingConstants.HORIZONTAL,northPanel,southPanel);
		this.settingPanel.resetToPreferredSizes();
		this.frame.add(this.settingPanel,BorderLayout.EAST);
		this.frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		new Editor();
	}

}
