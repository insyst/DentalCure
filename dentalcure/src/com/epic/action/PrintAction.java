package com.epic.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;

import com.epic.common.EpicCommonDialog;

public class PrintAction extends AbstractAction {


	private static final long serialVersionUID = 1L;
	public PrintAction(){
		super("Print");
		putValue(Action.SHORT_DESCRIPTION, "Print");
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		EpicCommonDialog.showMessage();	

	}

}
