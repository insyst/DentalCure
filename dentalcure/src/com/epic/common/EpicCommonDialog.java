package com.epic.common;

import javax.swing.JOptionPane;

public class EpicCommonDialog {

	
	static public void showMessage(){
		JOptionPane.showMessageDialog(null, EpicResourceBundle.getString("dialogMessage"));
	}
}
