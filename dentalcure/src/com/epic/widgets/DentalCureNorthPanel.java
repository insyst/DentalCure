package com.epic.widgets;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

import com.epic.common.EpicResourceBundle;

public class DentalCureNorthPanel extends JPanel implements ActionListener  {


	private static final long serialVersionUID = 1L;
	JComboBox<String> typeComboBox;
	GridBagConstraints gc = new GridBagConstraints();

	DentalCureNorthPanel(){

		setLayout(new BorderLayout(5,5));		
		setNorthePanel();
		setCenterpanel();

	}

	private void setNorthePanel() {
		JPanel northPanel =  new JPanel(new FlowLayout(FlowLayout.CENTER));
		northPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.CYAN, Color.CYAN));
		northPanel.setBackground(Color.GREEN);
		JLabel label = new JLabel("LINGUALOYO");
		label.setFont(new Font(Font.SANS_SERIF,Font.BOLD,14));		
		label.setForeground(Color.BLUE);
		northPanel.add(label);
		add(northPanel,BorderLayout.NORTH);
	}
	private void setCenterpanel() {
		JPanel centerPanel =  new JPanel(new GridBagLayout());	
		centerPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.CYAN, Color.CYAN));

		JLabel nameLabel =  new JLabel(EpicResourceBundle.getString("PatientLabel"));
		addItem(centerPanel,nameLabel, 0, 0, 1, 1, GridBagConstraints.WEST);
		JTextField patientName = new JTextField(15);
		addItem(centerPanel,patientName, 1, 0, 1, 1, GridBagConstraints.WEST);
		
		JLabel ageLabel =  new JLabel("Age:");
		addItem(centerPanel,ageLabel, 0, 1, 1, 1, GridBagConstraints.WEST);
		JTextField age1 = new JTextField(15);
		addItem(centerPanel,age1,1,1,1,1,GridBagConstraints.WEST);
		
		JLabel sexLabel =  new JLabel("Sex:");
		addItem(centerPanel,sexLabel, 0, 2, 1, 1, GridBagConstraints.WEST);
		String[] items =  new String[]{"M","F"};
		DefaultComboBoxModel<String> aModel =  new DefaultComboBoxModel<String>(items);
		//typeComboBox.setPreferredSize( new Dimension(1,8));
		typeComboBox =  new JComboBox<String>(aModel);
		typeComboBox.setPreferredSize( new Dimension(168,20));
		typeComboBox.addActionListener(this);
		addItem(centerPanel,typeComboBox,1,2,1,1,GridBagConstraints.WEST);
		
		JLabel diagnosisLabel =  new JLabel(EpicResourceBundle.getString("Diagnosis"));
		addItem(centerPanel,diagnosisLabel, 0, 3, 1, 1, GridBagConstraints.WEST);
		JTextArea diagnosysArea = new JTextArea(2, 15);
		diagnosysArea.setLineWrap(true);
		addItem(centerPanel,new JScrollPane(diagnosysArea), 1, 3, 1, 1, GridBagConstraints.WEST);
		
		JLabel dateOfDelivery = new JLabel("Date Of Delivery:");
		addItem(centerPanel,dateOfDelivery, 0, 4, 1, 1, GridBagConstraints.WEST);
		
		JPanel datepicker = new JPanel();
//		datepicker.setPreferredSize( new Dimension(200,25));
		DateFormat format =  new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		JFormattedTextField dateField = new JFormattedTextField(format);
		dateField.setValue(new Date());
		dateField.setColumns(15);
//		datepicker.add(dateField);
		
		final String calendarImgPath = "D:\\prog\\dentalcure\\epicimage\\form1.png";
		ImagePanel imagePanel = new ImagePanel("form1.png");
//		imagePanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.CYAN, Color.CYAN));
		datepicker.add(imagePanel);
		imagePanel.validate();
		imagePanel.repaint();
		datepicker.validate();
		datepicker.repaint();
		addItem(centerPanel,dateField, 1, 4, 1, 1, GridBagConstraints.WEST);

		JLabel doctorNameLabel =  new JLabel(EpicResourceBundle.getString("DoctorLabel"));
		addItem(centerPanel,doctorNameLabel, 4, 0, 1, 1, GridBagConstraints.EAST);
		JTextField doctorName = new JTextField(13);
		doctorName.setText("Dr.Suryakanta Das");
		//doctorName.setBorder(BorderFactory.createEmptyBorder());
		doctorName.setEditable(true);
		addItem(centerPanel,doctorName, 5, 0, 1, 1, GridBagConstraints.WEST);	

		add(centerPanel,BorderLayout.CENTER);

	}

	private void addItem(JPanel p, JComponent c, int x, int y, int width, int height, int align){
		gc.gridx = x;
		gc.gridy = y;
		gc.gridwidth = width;
		gc.gridheight = height;
		gc.weightx = 100.0;
		gc.weighty = 100.0;
		gc.insets = new Insets(1, 1, 1, 1);
		gc.anchor = align;
		gc.fill = GridBagConstraints.NONE;
		p.add(c, gc);
	}
	public void actionPerformed(ActionEvent e) {

		/*String type = (String) typeComboBox.getSelectedItem();
		if("M".equalsIgnoreCase(type))	{
			insetImageIntoPanel(imageFileForm1);
		}
		else if("Form2".equalsIgnoreCase(type))		{
			insetImageIntoPanel(imageFileForm2);

		}
		else if("Form3".equalsIgnoreCase(type))		{
			insetImageIntoPanel(imageFileForm3);
		}*/
	}

}