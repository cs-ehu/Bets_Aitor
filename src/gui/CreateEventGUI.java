package gui;

import java.awt.EventQueue;
import dataAccess.*;

import javax.swing.JFrame;

import com.toedter.calendar.JCalendar;

import businessLogic.BLFacade;

import java.awt.BorderLayout;
import java.awt.Rectangle;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class CreateEventGUI extends JFrame {
	private JTextField textFieldEventDesc;
	private DataAccess bussinesLogic;
	private JCalendar calendar;
	private JLabel lblEventoCreado;

	/**
	 * Create the application.
	 */
	public CreateEventGUI() {
		super();
		setTitle("Crear Evento");
		getContentPane().setLayout(null);
		
		calendar = new JCalendar();
		calendar.setBounds(new Rectangle(25, 11, 424, 113));
		getContentPane().add(calendar);
		
		
		lblEventoCreado = new JLabel("Evento Creado");
		lblEventoCreado.setForeground(Color.RED);
		lblEventoCreado.setBounds(179, 135, 107, 20);
		getContentPane().add(lblEventoCreado);
		lblEventoCreado.setVisible(false);
		
		
		JLabel lblNewLabel = new JLabel("Descripcion evento:");
		lblNewLabel.setBounds(25, 159, 152, 20);
		getContentPane().add(lblNewLabel);
		
		textFieldEventDesc = new JTextField();
		textFieldEventDesc.setText("");
		textFieldEventDesc.setBounds(196, 159, 243, 20);
		getContentPane().add(textFieldEventDesc);
		textFieldEventDesc.setColumns(10);
		
		JButton btnNewButton = new JButton("Crear evento");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BLFacade facade=MainGUI.getBusinessLogic();
				String des = textFieldEventDesc.getText();
				Date d = trim(calendar.getDate());				
				facade.createEvent(des, d);				
				lblEventoCreado.setVisible(true);
			}
		});
		btnNewButton.setBounds(92, 201, 174, 28);
		getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Close");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jButton2_actionPerformed(arg0);
			}
			
		});
		btnNewButton_1.setBounds(293, 201, 152, 27);
		getContentPane().add(btnNewButton_1);
		

		initialize();
	}

	private void jButton2_actionPerformed(ActionEvent e) {
		this.setVisible(false);
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {		
		this.setSize(495, 290);
	}

	
	  private Date trim(Date date) {

	      Calendar calendar = Calendar.getInstance();
	      calendar.setTime(date);
	      calendar.set(Calendar.MILLISECOND, 0);
	      calendar.set(Calendar.SECOND, 0);
	      calendar.set(Calendar.MINUTE, 0);
	      calendar.set(Calendar.HOUR_OF_DAY, 0);
	      return calendar.getTime();
	  }
}
