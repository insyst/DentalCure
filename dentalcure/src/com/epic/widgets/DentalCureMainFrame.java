package com.epic.widgets;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;

import javax.swing.*;

import com.epic.action.HelpAction;
import com.epic.action.OpenAction;
import com.epic.action.SaveAction;

/**
 * This frame is the main frame of GUI. It will contains the following section.
 * 1) MenuBar on top
 * 2) NorthPanel: contains the clinic name with photo
 *    Patient details and Doctors Name
 *    Below that it will contain a panel consisting of Generate button,
 *    Save, Close and Print and with a ComboBox panel
 *  3) Center Panel: contains a panel which is divided by two panel
 *     i.e. Left Panel which is a Palette for drawing perpose
 *      and the right panel for loading the teeth image upon combo type selection.
 * @author ananda
 *
 */
public class DentalCureMainFrame extends JFrame {


	private static final long serialVersionUID = 1L;

	public DentalCureMainFrame(){
		setLayout(new BorderLayout());
		JPanel northPanel =  new DentalCureNorthPanel();
		JPanel centerPanel =  new DentalCureCenterPanel();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setJMenuBar(getJMenuBar());
		getContentPane().add(northPanel, BorderLayout.NORTH);
		getContentPane().add(centerPanel, BorderLayout.CENTER);
	}


	@Override
	public JMenuBar getJMenuBar() {

		JMenuBar bar = new JMenuBar();

		JMenu menu = new JMenu("File");
		menu.setMnemonic(KeyEvent.VK_F);
		JMenuItem menuItem = new JMenuItem(new OpenAction());
		menu.add(menuItem);		
		menuItem = new JMenuItem(new SaveAction());
		menu.add(menuItem);
		bar.add(menu);

		menu = new JMenu("Help");
		menu.setMnemonic(KeyEvent.VK_H);
		menuItem = new JMenuItem(new HelpAction());
		menu.add(menuItem);		
		bar.add(menu);
		return bar;
	}
}
