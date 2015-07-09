package com.epic.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;

import com.epic.common.EpicCommonDialog;

public class OpenAction extends AbstractAction {

	private static final long serialVersionUID = 1L;
	public OpenAction(){
		super("Open Profile");
		putValue(Action.SHORT_DESCRIPTION, "Open a Patient Profile");
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		EpicCommonDialog.showMessage();		
	}

}
