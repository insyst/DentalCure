package com.epic.widgets;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

import com.epic.action.GenerateReportAction;
import com.epic.action.PrintAction;
import com.epic.common.EpicResourceBundle;

public class DentalCureCenterPanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private static final int MAX_SCORE = 20;
	   private static final int PREF_W = 250;
	   private static final int PREF_H = 300;
	   private static final int BORDER_GAP = 30;
	   private static final Color GRAPH_COLOR = Color.green;
	   private static final Color GRAPH_POINT_COLOR = new Color(150, 50, 50, 180);
	   private static final Stroke GRAPH_STROKE = new BasicStroke(3f);
	   private static final int GRAPH_POINT_WIDTH = 12;
	   private static final int Y_HATCH_CNT = 10;
	   private List<Integer> scores;
	   List<Point> graphPoints = new ArrayList<Point>();
	
	JCheckBox gapCheckBox;
	JComboBox<String> typeComboBox;
	JTextField statusMessage;
	DrawGraphNext imagePanel;
	LeftTeethPanel westPanel;
	GridBagConstraints gc=  new GridBagConstraints();
	final String imageFileForm1 = "D:\\prog\\dentalcure\\epicimage\\standard.jpg";
	final String imageFile = "/standard.jpg";
	final String imageFileForm2 = "D:\\prog\\dentalcure\\epicimage\\2.png";
	final String imageFileForm3 = "D:\\prog\\dentalcure\\epicimage\\3.png";
	Graphics2D g2 = null;

	DentalCureCenterPanel(){
		setLayout(new BorderLayout(5,5));
		setNorthPanel();
		setWestPanel();
		setCenterPanel();
		setSouthPanel();
		setEastPanel();
		imagePanel.validate();
		imagePanel.repaint();
//		imagePanel.drawTriangle();
	}
	public static Insets insets = new Insets(0,0,0,0);
	private static void addComponent(Container container, Component component, int gridx, int gridy,
		      int gridwidth, int gridheight, int anchor, int fill) {
		    GridBagConstraints gbc = new GridBagConstraints(gridx, gridy, gridwidth, gridheight, 1.0, 1.0,
		        anchor, fill, insets, 0, 0);
		    container.add(component, gbc);
		  }
	private void setNorthPanel() {
		GridBagLayout gblayout = new GridBagLayout();
		JPanel panel =  new JPanel(gblayout);
		panel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.CYAN, Color.CYAN));
		//panel.setBorder(BorderFactory.createLineBorder(Color.CYAN));
		String[] items =  new String[]{"Standard","Narrow","Broad"};
		DefaultComboBoxModel<String> aModel =  new DefaultComboBoxModel<String>(items);
		typeComboBox =  new JComboBox<String>(aModel);
//		typeComboBox.addActionListener(this);
		//gapCheckBox =  new JCheckBox("IsSpace", false);

		JPanel panel1 =new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel1.add(typeComboBox);
		//panel1.add(gapCheckBox);
//		gc.weighty = 100.0;
		addComponent(panel, panel1, 0, 0, 1, 1, GridBagConstraints.WEST, GridBagConstraints.BOTH);
//		addItem1(gblayout,panel,panel1,0,0,1,1);
		
		
		JButton clearBtn = new JButton("Clear");
		clearBtn.setActionCommand("Clear");
		clearBtn.addActionListener(this);
		//addItem(panel,clearBtn,3,0,1,1);
		JButton plotBtn = new JButton("Plot Graph");
		plotBtn.setActionCommand("Plot Graph");
		plotBtn.addActionListener(this);
		//addItem(panel,plotBtn,5,0,1,1);
		JButton generateBtn = new JButton(new GenerateReportAction());
		JButton printBtn = new JButton(new PrintAction());
		JPanel panel2 =new JPanel(new FlowLayout(FlowLayout.RIGHT));
		panel2.add(plotBtn);
		panel2.add(printBtn);
		panel2.add(clearBtn);
//		gc.weightx = 0;
//		gc.weighty = 0;
//		addItem1(gblayout,panel,panel2,1,0,5,1);
		addComponent(panel, panel2, 1, 0, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
		add(panel, BorderLayout.NORTH);

	}

	private void setWestPanel() {
		westPanel =  new LeftTeethPanel();
		westPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.CYAN, Color.CYAN));
		//westPanel.add(new JLabel("Palette"));
		add(westPanel,BorderLayout.WEST);
	}
	private void setEastPanel() {
		JPanel eastPanel =  new RightTeethPanel();
		eastPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.CYAN, Color.CYAN));
		//eastPanel.add(new JLabel("Dalette"));
		add(eastPanel,BorderLayout.EAST);		
	}

	/**
	 * It will load the image into the panel depends upon
	 * form type selected.
	 */
	private void setCenterPanel() {
		List<Integer> scores = new ArrayList<Integer>();
		imagePanel = new DrawGraphNext(scores);
		imagePanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.CYAN, Color.CYAN));
		add(imagePanel, BorderLayout.CENTER);
//		imagePanel.addMouseMotionListener(this);
//	    imagePanel.addMouseListener(this);
		validate();
		repaint();	

	}

	private void setSouthPanel() {
		statusMessage =  new JTextField();
		statusMessage.setEditable(false);
		statusMessage.setText(EpicResourceBundle.getString("SuccessfullyLoaded"));
		statusMessage.setForeground(Color.BLUE);
		add(statusMessage,BorderLayout.SOUTH);
	}

	boolean isTeethGapSelected(){
		return gapCheckBox.isSelected();
	}

	private void addItem(JPanel p, JComponent c, int x, int y, int width, int height){		
		gc.gridx = x;
		gc.gridy = y;
		gc.weightx = 100;
//		gc.gridwidth = width;
//		gc.gridheight = height;   
		gc.insets = new Insets(1, 1, 1, 1);
		gc.fill = GridBagConstraints.NONE;
		p.add(c, gc);
	}
	private void addItem1(GridBagLayout layout,JPanel p, JComponent c, int x, int y, int width, int height){		
		gc.gridx = x;
		gc.gridy = y;
//		gc.weightx = 300;
		gc.gridwidth = width;
		layout.setConstraints(c, gc);
//		gc.gridheight = height;   
//		gc.insets = new Insets(1, 1, 1, 1);
//		gc.fill = GridBagConstraints.NONE;
		p.add(c);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int option = 3;
		if (e.getActionCommand().equals("Plot Graph"))
		{
			if (westPanel.tfEleven.getText().equals(8))
			{
				option = 1;
				imagePanel.plotGraph(option);
			}
			else if (westPanel.tfEleven.getText().equals(10))
			{
				option = 2;
				imagePanel.plotGraph(option);
			}
			else
			{
					imagePanel.plotGraph(option);//anything except 1 or 2
					imagePanel.validate();
					imagePanel.repaint();
			}
			System.out.println("Plot Graph button pressed:: ");
		}
		if (e.getActionCommand().equals("Clear"))
		{
			imagePanel.clear();
			System.out.println("Clear button pressed:: ");
		}
		else{
			String type = (String) typeComboBox.getSelectedItem();
			if("Standard".equalsIgnoreCase(type))	{
				insetImageIntoPanel(imageFile);
			}
			else if("Narrow".equalsIgnoreCase(type))		{
				insetImageIntoPanel(imageFileForm2);
	
			}
			else if("Broad".equalsIgnoreCase(type))		{
				insetImageIntoPanel(imageFileForm3);
			}
		}
	}



	private void insetImageIntoPanel(String imageFile) {
		/*
		remove(imagePanel);
		//		validate();
		//		repaint();	
		imagePanel =  getImagePanel(imageFile);
		imagePanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.CYAN, Color.CYAN));
		add(imagePanel,BorderLayout.CENTER);
		validate();
		repaint();
		*/		
	}


	private JPanel getImagePanel(String file) {
		return new ImagePanel(file);
	}
	}
