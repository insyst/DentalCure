package com.epic.widgets;


import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class Tester extends JPanel {
	
	private static final long serialVersionUID = 1L;
	GridBagConstraints gc = new GridBagConstraints();

	Tester(){
		setLayout(new FlowLayout());		
//		setNorthePanel();
		setCenterpanel();

	}
	
	private void setCenterpanel() {
		JPanel datepicker = new JPanel();
		
		final String calendarImgPath = "D:\\prog\\dentalcure\\epicimage\\form3.png";
//		final String calendarImgPath = ".calendar.png";
		ImagePanel imagePanel = new ImagePanel(calendarImgPath);
		Graphics g = datepicker.getGraphics();
//		System.out.println("Graphios::  "+g+"    image== "+imagePanel.image);
//		g.drawImage(imagePanel.image, 60, 0, null); 
//		imagePanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.CYAN, Color.CYAN));
		datepicker.add(imagePanel);
//		imagePanel.validate();
//		imagePanel.repaint();
//		datepicker.validate();
//		datepicker.repaint();
		validate();
		repaint();
		add(imagePanel,BorderLayout.CENTER);
		validate();
		repaint();

	}
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
		frame.getContentPane().add(new Tester());
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
