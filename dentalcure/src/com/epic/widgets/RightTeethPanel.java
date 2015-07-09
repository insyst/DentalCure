package com.epic.widgets;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.font.TextAttribute;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

public class RightTeethPanel extends JPanel{
	
	private static final long serialVersionUID = 1L;
	GridBagConstraints gc = new GridBagConstraints();

	RightTeethPanel(){
		setLayout(new FlowLayout());		
//		setNorthePanel();
		setCenterpanel();

	}
	private void setCenterpanel() {
		JPanel centerPanel =  new JPanel(new GridBagLayout());	
		centerPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.CYAN, Color.CYAN));

		JLabel nameLabel =  new JLabel("Tooth#");
		addItem(centerPanel,nameLabel, 0, 0, 1, 1, GridBagConstraints.WEST);
		Font font = nameLabel.getFont();
		Map attributes = font.getAttributes();
		attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		nameLabel.setFont(font.deriveFont(attributes));
		
		JLabel lblWidth =  new JLabel("MD Width");
		addItem(centerPanel,lblWidth, 0, 1, 1, 1, GridBagConstraints.CENTER);
		Font font1 = lblWidth.getFont();
		Map attributes1 = font1.getAttributes();
		attributes1.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		lblWidth.setFont(font.deriveFont(attributes));
		
		JLabel lblThickness =  new JLabel("Depth");
		addItem(centerPanel,lblThickness, 0, 2, 1, 1, GridBagConstraints.EAST);
		Font font2 = lblThickness.getFont();
		Map attributes2 = font2.getAttributes();
		attributes2.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		lblThickness.setFont(font.deriveFont(attributes));
		
		//JCheckBox gapCheckBox =  new JCheckBox("IsSpace", false);
		//DentalCureCenterPanel obj = new DentalCureCenterPanel(); 
		
		JTextField tfEleven = new JTextField(4);
		addItem(centerPanel,tfEleven, 1, 0, 1, 1, GridBagConstraints.WEST);
		tfEleven.setText("21");
		tfEleven.setHorizontalAlignment(JTextField.CENTER);
		tfEleven.setBackground(Color.green);
		tfEleven.setEditable(false);
		JTextField tfEleven1 = new JTextField(5);
		addItem(centerPanel,tfEleven1, 1, 1, 1, 1, GridBagConstraints.CENTER);
		tfEleven1.setText("9");
		tfEleven1.setHorizontalAlignment(JTextField.CENTER);
		tfEleven1.setBackground(Color.magenta);
		JTextField tfEleven2 = new JTextField(5);
		addItem(centerPanel,tfEleven2, 1, 2, 1, 1, GridBagConstraints.EAST);
		tfEleven2.setText("7");
		tfEleven2.setBackground(Color.orange);
		tfEleven2.setHorizontalAlignment(JTextField.CENTER);
		
		JLabel lblSp1 =  new JLabel("Space:");
		lblSp1.setForeground(Color.GRAY);
		addItem(centerPanel,lblSp1, 2, 0, 1, 1, GridBagConstraints.CENTER);
		JTextField tpSp1 = new JTextField(5);
		addItem(centerPanel,tpSp1, 2, 2, 1, 1, GridBagConstraints.WEST);
		tpSp1.setBackground(Color.CYAN);
		tpSp1.setText("0");
		//if(obj.gapCheckBox.isSelected())
			//tpSp1.setVisible(true);
		//addItem(centerPanel,gapCheckBox,2,0,1,1,GridBagConstraints.EAST);
		
		JTextField tfTwelve = new JTextField(4);
		addItem(centerPanel,tfTwelve, 3, 0, 1, 1, GridBagConstraints.WEST);
		tfTwelve.setBackground(Color.green);
		tfTwelve.setText("22");
		tfTwelve.setHorizontalAlignment(JTextField.CENTER);
		JTextField tfTwelve1 = new JTextField(5);
		addItem(centerPanel,tfTwelve1,3, 1, 1, 1, GridBagConstraints.CENTER);
		tfTwelve1.setBackground(Color.magenta);
		tfTwelve1.setText("6");
		tfTwelve1.setHorizontalAlignment(JTextField.CENTER);
		JTextField tfTwelve2 = new JTextField(5);
		addItem(centerPanel,tfTwelve2, 3, 2, 1, 1, GridBagConstraints.EAST);
		tfTwelve2.setBackground(Color.orange);
		tfTwelve2.setText("7");
		tfTwelve2.setHorizontalAlignment(JTextField.CENTER);
		
		JLabel lblSp2 =  new JLabel("Space:");
		lblSp2.setForeground(Color.GRAY);
		addItem(centerPanel,lblSp2, 4, 0, 1, 1, GridBagConstraints.CENTER);
		JTextField tfSp2 = new JTextField(5);
		addItem(centerPanel,tfSp2, 4, 2, 1, 1, GridBagConstraints.WEST);
		tfSp2.setBackground(Color.CYAN);
		tfSp2.setText("0");
		lblSp2.setHorizontalAlignment(JTextField.CENTER);
		
		JTextField tfThirteen = new JTextField(4);
		addItem(centerPanel,tfThirteen, 5, 0, 1, 1, GridBagConstraints.WEST);
		tfThirteen.setBackground(Color.green);
		tfThirteen.setText("23");
		tfThirteen.setHorizontalAlignment(JTextField.CENTER);
		tfThirteen.setEditable(false);
		JTextField tfThirteen1 = new JTextField(5);
		addItem(centerPanel,tfThirteen1,5, 1, 1, 1, GridBagConstraints.CENTER);
		tfThirteen1.setBackground(Color.magenta);
		tfThirteen1.setText("7");
		tfThirteen1.setHorizontalAlignment(JTextField.CENTER);
		JTextField tfThirteen2 = new JTextField(5);
		addItem(centerPanel,tfThirteen2, 5, 2, 1, 1, GridBagConstraints.EAST);
		tfThirteen2.setBackground(Color.orange);
		tfThirteen2.setText("7");
		tfThirteen2.setHorizontalAlignment(JTextField.CENTER);
		
		JLabel lblSp3 =  new JLabel("Space:");
		lblSp3.setForeground(Color.GRAY);
		addItem(centerPanel,lblSp3, 6, 0, 1, 1, GridBagConstraints.CENTER);
		JTextField tfSp3 = new JTextField(5);
		addItem(centerPanel,tfSp3, 6, 2, 1, 1, GridBagConstraints.WEST);
		tfSp3.setBackground(Color.CYAN);
		tfSp3.setText("0");
		lblSp3.setHorizontalAlignment(JTextField.CENTER);
		
		JTextField tffourteen = new JTextField(4);
		addItem(centerPanel,tffourteen, 7, 0, 1, 1, GridBagConstraints.WEST);
		tffourteen.setBackground(Color.green);
		tffourteen.setText("24");
		tffourteen.setHorizontalAlignment(JTextField.CENTER);
		tffourteen.setEditable(false);
		JTextField tffourteen1 = new JTextField(5);
		addItem(centerPanel,tffourteen1,7, 1, 1, 1, GridBagConstraints.CENTER);
		tffourteen1.setBackground(Color.magenta);
		tffourteen1.setText("7");
		tffourteen1.setHorizontalAlignment(JTextField.CENTER);
		JTextField tffourteen2 = new JTextField(5);
		addItem(centerPanel,tffourteen2, 7, 2, 1, 1, GridBagConstraints.EAST);
		tffourteen2.setBackground(Color.orange);
		tffourteen2.setText("10.5");
		tffourteen2.setHorizontalAlignment(JTextField.CENTER);
		
		JLabel lblSp4 =  new JLabel("Space:");
		lblSp4.setForeground(Color.GRAY);
		addItem(centerPanel,lblSp4, 8, 0, 1, 1, GridBagConstraints.CENTER);
		JTextField tfSp4 = new JTextField(5);
		addItem(centerPanel,tfSp4, 8, 2, 1, 1, GridBagConstraints.WEST);
		tfSp4.setBackground(Color.CYAN);
		tfSp4.setText("0");
		lblSp4.setHorizontalAlignment(JTextField.CENTER);
		
		JTextField tffifteen = new JTextField(4);
		addItem(centerPanel,tffifteen, 9, 0, 1, 1, GridBagConstraints.WEST);
		tffifteen.setBackground(Color.green);
		tffifteen.setText("25");
		tffifteen.setHorizontalAlignment(JTextField.CENTER);
		tffifteen.setEditable(false);
		JTextField tffifteen1 = new JTextField(5);
		addItem(centerPanel,tffifteen1,9, 1, 1, 1, GridBagConstraints.CENTER);
		tffifteen1.setBackground(Color.magenta);
		tffifteen1.setText("5.5");
		tffifteen1.setHorizontalAlignment(JTextField.CENTER);
		JTextField tffifteen2 = new JTextField(5);
		addItem(centerPanel,tffifteen2, 9, 2, 1, 1, GridBagConstraints.EAST);
		tffifteen2.setBackground(Color.orange);
		tffifteen2.setText("10.5");
		tffifteen2.setHorizontalAlignment(JTextField.CENTER);
		
		JLabel lblSp5 =  new JLabel("Space:");
		lblSp5.setForeground(Color.GRAY);
		addItem(centerPanel,lblSp5, 10, 0, 1, 1, GridBagConstraints.CENTER);
		JTextField tfSp5 = new JTextField(5);
		addItem(centerPanel,tfSp5, 10, 2, 1, 1, GridBagConstraints.WEST);
		tfSp5.setBackground(Color.CYAN);
		tfSp5.setText("0");
		lblSp5.setHorizontalAlignment(JTextField.CENTER);
		
		JTextField tfsixteen = new JTextField(4);
		addItem(centerPanel,tfsixteen, 11, 0, 1, 1, GridBagConstraints.WEST);
		tfsixteen.setBackground(Color.green);
		tfsixteen.setText("26");
		tfsixteen.setHorizontalAlignment(JTextField.CENTER);
		tfsixteen.setEditable(false);
		JTextField tfsixteen1 = new JTextField(5);
		addItem(centerPanel,tfsixteen1,11, 1, 1, 1, GridBagConstraints.CENTER);
		tfsixteen1.setBackground(Color.magenta);
		tfsixteen1.setText("10");
		tfsixteen1.setHorizontalAlignment(JTextField.CENTER);
		JTextField tfsixteen2 = new JTextField(5);
		addItem(centerPanel,tfsixteen2, 11, 2, 1, 1, GridBagConstraints.EAST);
		tfsixteen2.setBackground(Color.orange);
		tfsixteen2.setText("13");
		tfsixteen2.setHorizontalAlignment(JTextField.CENTER);
		
		JLabel lblSp6 =  new JLabel("Space:");
		lblSp6.setForeground(Color.GRAY);
		addItem(centerPanel,lblSp6, 12, 0, 1, 1, GridBagConstraints.CENTER);
		JTextField tfSp6 = new JTextField(5);
		addItem(centerPanel,tfSp6, 12, 2, 1, 1, GridBagConstraints.WEST);
		tfSp6.setBackground(Color.CYAN);
		tfSp6.setText("0");
		lblSp6.setHorizontalAlignment(JTextField.CENTER);
		
		JTextField tfseventeen = new JTextField(4);
		addItem(centerPanel,tfseventeen, 13, 0, 1, 1, GridBagConstraints.WEST);
		tfseventeen.setBackground(Color.green);
		tfseventeen.setText("27");
		tfseventeen.setEditable(false);
		tfseventeen.setHorizontalAlignment(JTextField.CENTER);
		JTextField tfseventeen1 = new JTextField(5);
		addItem(centerPanel,tfseventeen1,13, 1, 1, 1, GridBagConstraints.CENTER);
		tfseventeen1.setBackground(Color.magenta);
		tfseventeen1.setText("9");
		tfseventeen1.setHorizontalAlignment(JTextField.CENTER);
		JTextField tfseventeen2 = new JTextField(5);
		addItem(centerPanel,tfseventeen2, 13, 2, 1, 1, GridBagConstraints.EAST);
		tfseventeen2.setBackground(Color.orange);
		tfseventeen2.setText("13");
		tfseventeen2.setHorizontalAlignment(JTextField.CENTER);

		add(centerPanel,BorderLayout.CENTER);

	}

/*	private void setCenterpanel() {
		JPanel centerPanel =  new JPanel(new GridBagLayout());	
		centerPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.CYAN, Color.CYAN));

		JLabel nameLabel =  new JLabel("Tooth");
		addItem(centerPanel,nameLabel, 0, 0, 1, 1, GridBagConstraints.WEST);
		Font font = nameLabel.getFont();
		Map attributes = font.getAttributes();
		attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		nameLabel.setFont(font.deriveFont(attributes));
		
		JLabel lblWidth =  new JLabel("Width");
		addItem(centerPanel,lblWidth, 0, 1, 1, 1, GridBagConstraints.CENTER);
		Font font1 = lblWidth.getFont();
		Map attributes1 = font1.getAttributes();
		attributes1.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		lblWidth.setFont(font.deriveFont(attributes));
		
		JLabel lblThickness =  new JLabel("Depth");
		addItem(centerPanel,lblThickness, 0, 2, 1, 1, GridBagConstraints.EAST);
		Font font2 = lblThickness.getFont();
		Map attributes2 = font2.getAttributes();
		attributes2.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		lblThickness.setFont(font.deriveFont(attributes));
		
		JTextField tfEleven = new JTextField(3);
		addItem(centerPanel,tfEleven, 1, 0, 1, 1, GridBagConstraints.WEST);
		tfEleven.setBackground(Color.green);
		tfEleven.setText("21");
		JTextField tfEleven1 = new JTextField(3);
		addItem(centerPanel,tfEleven1, 1, 1, 1, 1, GridBagConstraints.CENTER);
		tfEleven1.setBackground(Color.magenta);
		tfEleven1.setText("9");
		JTextField tfEleven2 = new JTextField(3);
		addItem(centerPanel,tfEleven2, 1, 2, 1, 1, GridBagConstraints.EAST);
		tfEleven2.setBackground(Color.orange);
		tfEleven2.setText("7");
		
		JLabel lblSp =  new JLabel("Space:");
		lblSp.setForeground(Color.GRAY);
		addItem(centerPanel,lblSp, 2, 0, 1, 1, GridBagConstraints.CENTER);
		JTextField tfSp1 = new JTextField(3);
		addItem(centerPanel,tfSp1, 2, 2, 1, 1, GridBagConstraints.WEST);
		tfSp1.setBackground(Color.CYAN);
		tfSp1.setText("0");
		
		JTextField tfTweleve = new JTextField(3);
		addItem(centerPanel,tfTweleve, 3, 0, 1, 1, GridBagConstraints.WEST);
		tfTweleve.setBackground(Color.green);
		tfTweleve.setText("22");
		JTextField tfTweleve1 = new JTextField(3);
		addItem(centerPanel,tfTweleve1,3, 1, 1, 1, GridBagConstraints.CENTER);
		tfTweleve1.setBackground(Color.magenta);
		tfTweleve1.setText("6");
		JTextField tfTweleve2 = new JTextField(3);
		addItem(centerPanel,tfTweleve2, 3, 2, 1, 1, GridBagConstraints.EAST);
		tfTweleve2.setBackground(Color.orange);
		tfTweleve2.setText("7");
		
		JLabel lblSp1 =  new JLabel("Space:");
		lblSp1.setForeground(Color.GRAY);
		addItem(centerPanel,lblSp1, 4, 0, 1, 1, GridBagConstraints.CENTER);
		JTextField tfSp2 = new JTextField(3);
		addItem(centerPanel,tfSp2, 4, 2, 1, 1, GridBagConstraints.WEST);
		tfSp2.setBackground(Color.CYAN);
		tfSp2.setText("0");
		
		JTextField tfThirteen = new JTextField(3);
		addItem(centerPanel,tfThirteen, 5, 0, 1, 1, GridBagConstraints.WEST);
		tfThirteen.setBackground(Color.green);
		tfThirteen.setText("23");
		JTextField tfThirteen1 = new JTextField(3);
		addItem(centerPanel,tfThirteen1,5, 1, 1, 1, GridBagConstraints.CENTER);
		tfThirteen1.setBackground(Color.magenta);
		tfThirteen1.setText("7");
		JTextField tfThirteen2 = new JTextField(3);
		addItem(centerPanel,tfThirteen2, 5, 2, 1, 1, GridBagConstraints.EAST);
		tfThirteen2.setBackground(Color.CYAN);
		tfThirteen2.setText("7");
		
		JLabel lblSp2 =  new JLabel("Space:");
		lblSp2.setForeground(Color.GRAY);
		addItem(centerPanel,lblSp2, 6, 0, 1, 1, GridBagConstraints.CENTER);
		JTextField tfSp3 = new JTextField(3);
		addItem(centerPanel,tfSp3, 6, 2, 1, 1, GridBagConstraints.WEST);
		tfSp3.setBackground(Color.CYAN);
		tfSp3.setText("0");
		
		JTextField tffourteen = new JTextField(3);
		addItem(centerPanel,tffourteen, 7, 0, 1, 1, GridBagConstraints.WEST);
		tffourteen.setBackground(Color.green);
		tffourteen.setText("24");
		JTextField tffourteen1 = new JTextField(3);
		addItem(centerPanel,tffourteen1,7, 1, 1, 1, GridBagConstraints.CENTER);
		tffourteen1.setBackground(Color.magenta);
		tffourteen1.setText("7");
		JTextField tffourteen2 = new JTextField(3);
		addItem(centerPanel,tffourteen2, 7, 2, 1, 1, GridBagConstraints.EAST);
		tffourteen2.setBackground(Color.orange);
		tffourteen2.setText("10.5");
		
		JLabel lblSp3 =  new JLabel("Space:");
		lblSp3.setForeground(Color.GRAY);
		addItem(centerPanel,lblSp3, 8, 0, 1, 1, GridBagConstraints.CENTER);
		JTextField tfSp4 = new JTextField(3);
		addItem(centerPanel,tfSp4, 8, 2, 1, 1, GridBagConstraints.WEST);
		tfSp4.setBackground(Color.CYAN);
		tfSp4.setText("0");
		
		JTextField tffifteen = new JTextField(3);
		addItem(centerPanel,tffifteen, 9, 0, 1, 1, GridBagConstraints.WEST);
		tffifteen.setBackground(Color.green);
		tffifteen.setText("25");
		JTextField tffifteen1 = new JTextField(3);
		addItem(centerPanel,tffifteen1,9, 1, 1, 1, GridBagConstraints.CENTER);
		tffifteen1.setBackground(Color.magenta);
		tffifteen1.setText("5.5");
		JTextField tffifteen2 = new JTextField(3);
		addItem(centerPanel,tffifteen2, 9, 2, 1, 1, GridBagConstraints.EAST);
		tffifteen2.setBackground(Color.orange);
		tffifteen2.setText("10.5");
		
		JLabel lblSp4 =  new JLabel("Space:");
		lblSp4.setForeground(Color.GRAY);
		addItem(centerPanel,lblSp4, 10, 0, 1, 1, GridBagConstraints.CENTER);
		JTextField tfSp5 = new JTextField(3);
		addItem(centerPanel,tfSp5, 10, 2, 1, 1, GridBagConstraints.WEST);
		tfSp5.setBackground(Color.CYAN);
		tfSp5.setText("0");
		
		JTextField tfsixteen = new JTextField(3);
		addItem(centerPanel,tfsixteen, 11, 0, 1, 1, GridBagConstraints.WEST);
		tfsixteen.setBackground(Color.green);
		tfsixteen.setText("26");
		JTextField tfsixteen1 = new JTextField(3);
		addItem(centerPanel,tfsixteen1,11, 1, 1, 1, GridBagConstraints.CENTER);
		tfsixteen1.setBackground(Color.magenta);
		tfsixteen1.setText("10");
		JTextField tfsixteen2 = new JTextField(3);
		addItem(centerPanel,tfsixteen2, 11, 2, 1, 1, GridBagConstraints.EAST);
		tfsixteen2.setBackground(Color.orange);
		tfsixteen2.setText("13");
		
		JLabel lblSp5 =  new JLabel("Space:");
		lblSp5.setForeground(Color.GRAY);
		addItem(centerPanel,lblSp5, 12, 0, 1, 1, GridBagConstraints.CENTER);
		JTextField tfSp6 = new JTextField(3);
		addItem(centerPanel,tfSp6, 12, 2, 1, 1, GridBagConstraints.WEST);
		tfSp6.setBackground(Color.CYAN);
		tfSp6.setText("0");
		
		JTextField tfseventeen = new JTextField(3);
		addItem(centerPanel,tfseventeen, 13, 0, 1, 1, GridBagConstraints.WEST);
		tfseventeen.setBackground(Color.green);
		tfseventeen.setText("27");
		JTextField tfseventeen1 = new JTextField(3);
		addItem(centerPanel,tfseventeen1,13, 1, 1, 1, GridBagConstraints.CENTER);
		tfseventeen1.setBackground(Color.magenta);
		tfseventeen1.setText("9");
		JTextField tfseventeen2 = new JTextField(3);
		addItem(centerPanel,tfseventeen2, 13, 2, 1, 1, GridBagConstraints.EAST);
		tfseventeen2.setBackground(Color.orange);
		tfseventeen2.setText("13");
		

		add(centerPanel,BorderLayout.CENTER);

	}*/
	private void addItem(JPanel p, JComponent c, int y, int x, int width, int height, int align){
		gc.gridx = x;
		gc.gridy = y;
//		gc.gridwidth = width;
//		gc.gridheight = height;
//		gc.weightx = 20.0;
//		gc.weighty = 20.0;
		gc.insets = new Insets(1, 1, 1, 1);
		gc.anchor = align;
		gc.fill = GridBagConstraints.CENTER;
		p.add(c, gc);
	}
	public static void main(String[] LeftTeethSet)
	{
		JFrame frame = new JFrame();
		frame.setSize(500,500);
		frame.getContentPane().add(new RightTeethPanel());
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
