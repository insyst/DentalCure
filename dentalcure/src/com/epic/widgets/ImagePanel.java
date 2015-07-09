package com.epic.widgets;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ImagePanel extends JPanel{

	private static final long serialVersionUID = 1L;
	private BufferedImage image;
	String filename = "";

	public void setFileName(String fileName){
		this.filename = fileName;
	}
	/*
	public ImagePanel(String fileName) {
		try {         
	    	  File file = new File(getClass().getResource(fileName).toURI());
	    	  //System.out.println("File is:: "+file.getPath()+"   <<"+file.getAbsolutePath()+">>"+file.hashCode());
	      BufferedImage image = ImageIO.read(file);
	      g2.drawImage(image,0,0,null);
	      } catch (Exception ex) {
	    	  System.out.println("Error occured while opening the file... ");
	    	  ex.printStackTrace();
	    	  
	     }
	}*/
	
	public ImagePanel(String fileName) {
		try {
			setFileName(fileName);
			System.out.println("ImagePanel::filename:: "+fileName);
			
//			image = ImageIO.read(getClass().getResource(fileName));
			image = ImageIO.read(new File(fileName));
		} catch (IOException ex) {

		}
	}
	 
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (filename.endsWith("3.png"))
		g.drawImage(image, 60, 0, null);
		else
			g.drawImage(image, 60, 2, null);
	}

}