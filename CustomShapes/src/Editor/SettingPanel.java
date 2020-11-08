package Editor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

public class SettingPanel extends JPanel{
	
	private final JPanel northPanel;
	private final JPanel southPanel;
	private final JButton bird,tree;
	private final JSlider size,angle;
	private final JLabel sizeLabel,angleLabel;
	
	public SettingPanel() {
		super(new BorderLayout());
		setBackground(Color.WHITE);
		this.northPanel = new JPanel(new GridLayout(1,2,20,20));
		this.northPanel.setBorder(BorderFactory.createEmptyBorder(200,20,200,20));
		this.southPanel = new JPanel(new GridLayout(4,1,20,20));
		this.southPanel.setBorder(BorderFactory.createEmptyBorder(200,20,200,20));
		this.bird = new JButton("Bird");
		this.tree = new JButton("Tree");
		northPanel.add(bird);
		this.sizeLabel = new JLabel("Size");
		this.angleLabel=new JLabel("Angle");
		northPanel.add(tree);
		this.size = new JSlider();
		this.angle = new JSlider();
		southPanel.add(sizeLabel);
		southPanel.add(size);
		southPanel.add(angleLabel);
		southPanel.add(angle);
		this.add(this.northPanel,BorderLayout.NORTH);
		this.add(this.southPanel,BorderLayout.SOUTH);
		setPreferredSize(new Dimension(500,30));
	}
		

}
