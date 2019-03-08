package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;

import businessLogic.BLFacade;
import domain.Event;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.awt.event.ActionEvent;

public class AdminMainPageGUI extends JFrame{


	/**
	 * Create the application.
	 */
	public AdminMainPageGUI() {
		setTitle("Admin Page");
		getContentPane().setLayout(null);
		
		JButton btnCrearPregunta = new JButton("Crear pregunta");
		btnCrearPregunta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BLFacade facade=MainGUI.getBusinessLogic();
				//Vector<Event> events=facade.getAllEvents();
				JFrame a = new CreateQuestionGUI(new Vector<Event>());
				a.setVisible(true);
			}
		});
		btnCrearPregunta.setBounds(127, 74, 209, 39);
		getContentPane().add(btnCrearPregunta);
		
		JButton btnCrearEvento = new JButton("Crear evento");
		btnCrearEvento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CreateEventGUI a = new CreateEventGUI();
				a.setVisible(true);
			}
		});
		btnCrearEvento.setBounds(127, 24, 209, 39);
		getContentPane().add(btnCrearEvento);
		
		JButton btnConsultarPreguntas = new JButton("Consultar preguntas");
		btnConsultarPreguntas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FindQuestionsGUI a = new FindQuestionsGUI();
				a.setVisible(true);
			}
		});
		btnConsultarPreguntas.setBounds(127, 124, 209, 39);
		getContentPane().add(btnConsultarPreguntas);
		
		JButton btnMeterResultados = new JButton("Meter Resultados");
		btnMeterResultados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MeterResultadosGUI a = new MeterResultadosGUI();
				a.setVisible(true);
			}
		});
		btnMeterResultados.setBounds(127, 174, 209, 39);
		getContentPane().add(btnMeterResultados);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		this.setSize(495, 290);
	}
}
