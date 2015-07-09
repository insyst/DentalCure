package com.epic.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;

import com.epic.common.EpicCommonDialog;

public class SaveAction extends AbstractAction {

	private static final long serialVersionUID = 1L;
	public SaveAction(){
		super("Save Profile");
		putValue(Action.SHORT_DESCRIPTION,"Save the Patient profile");
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		EpicCommonDialog.showMessage();	
	}

}
