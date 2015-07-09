package com.epic.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;

import com.epic.common.EpicCommonDialog;

public class GenerateReportAction extends AbstractAction {


	private static final long serialVersionUID = 1L;
	public GenerateReportAction(){
		super("Generate");
		putValue(Action.SHORT_DESCRIPTION, "GenerateReport");
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		EpicCommonDialog.showMessage();	
	}

}
