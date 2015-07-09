package com.epic.main;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import com.epic.common.EpicResourceBundle;
import com.epic.widgets.DentalCureMainFrame;


public class EpicMain {

	public static void main(String ...args)	{

		DentalCureMainFrame mainFrame = new DentalCureMainFrame();
		mainFrame.setTitle(EpicResourceBundle.getString("frameTitle"));
		BufferedImage image = null;
		Image img = null;
		/*
		try{
			File file = new File(EpicMain.class.getResource("../res/icon.ico").toURI());
			image = ImageIO.read(file);
		}catch(Exception e){
			System.out.println("EpicMain::main::  exception occured for favIcon");
			e.printStackTrace();}
			
		try{
			img = Toolkit.getDefaultToolkit().getImage(EpicMain.class.getResource("/icon.ico"));
			System.out.println("Image:: "+img);
			mainFrame.setIconImage(img);
		}catch(Exception e){
			e.printStackTrace();
		}*/
		try{
		File file = new File("icon.ico");
//  	  Image img = Toolkit.getDefaultToolkit().getImage(EpicMain.class.getResource("res/standard.jpeg"));
		image = ImageIO.read(file);
		}catch(Exception e){e.printStackTrace();}
		if (image!=null)
		mainFrame.setIconImage(image);
		mainFrame.setSize(800, 700);
		mainFrame.setVisible(true);
	}
}
