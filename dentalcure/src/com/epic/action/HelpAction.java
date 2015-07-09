package com.epic.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;

import com.epic.common.EpicCommonDialog;

public class HelpAction extends AbstractAction {


	private static final long serialVersionUID = 1L;
	public HelpAction(){
		super("Help");
		putValue(Action.SHORT_DESCRIPTION, "Help");
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		EpicCommonDialog.showMessage();	

	}

}
