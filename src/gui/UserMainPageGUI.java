package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JTextField;

import businessLogic.BLFacade;

import javax.swing.JLabel;

public class UserMainPageGUI extends JFrame{

	private static JLabel lblNewLabel;
	private JTextField textField;
	private JLabel lblSaldoAAadir;
	private JButton btnAadir;
	private JButton btnMisApuestas;
	private JButton btnNewButton;
	
	/**
	 * Create the application.
	 */
	public UserMainPageGUI(final String name) {
		setTitle("User Main Page");
		getContentPane().setLayout(null);
		
		
		lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(122, 212, 172, 20);
		getContentPane().add(lblNewLabel);
		initialize();
		lblNewLabel.setVisible(false);
		
		JButton btnApostar = new JButton("Apostar");
		btnApostar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblNewLabel.setVisible(false);
				ApuestaGUI a = new ApuestaGUI(name);
				a.setVisible(true);
				textField.setVisible(false);
				lblSaldoAAadir.setVisible(false);
				btnAadir.setVisible(false);
			}
		});
		btnApostar.setBounds(117, 11, 202, 23);
		getContentPane().add(btnApostar);
		
		JButton btnAadirSaldo = new JButton("Anadir saldo");
		btnAadirSaldo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblNewLabel.setVisible(false);
				textField.setVisible(true);
				lblSaldoAAadir.setVisible(true);
				btnAadir.setVisible(true);
			}
		});
		btnAadirSaldo.setBounds(117, 79, 202, 23);
		getContentPane().add(btnAadirSaldo);
		
		JButton btnVerSaldo = new JButton("Ver saldo");
		btnVerSaldo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BLFacade facade=MainGUI.getBusinessLogic();
				//se debe obtener el saldo para el usuario registrado mantener login
				String saldo = "Tu saldo es de "+Float.toString(facade.saldo(name)) + " €";
				lblNewLabel.setText(saldo);
				lblNewLabel.setVisible(true);
				textField.setVisible(false);
				lblSaldoAAadir.setVisible(false);
				btnAadir.setVisible(false);
				
			}
		});
		btnVerSaldo.setBounds(117, 45, 202, 23);
		getContentPane().add(btnVerSaldo);
		
		textField = new JTextField();
		textField.setBounds(117, 230, 198, 20);
		getContentPane().add(textField);
		textField.setColumns(10);
		textField.setVisible(false);
		
		lblSaldoAAadir = new JLabel("Saldo a anadir:");
		lblSaldoAAadir.setBounds(22, 230, 90, 20);
		getContentPane().add(lblSaldoAAadir);
		lblSaldoAAadir.setVisible(false);
		
		btnAadir = new JButton("Anadir");
		btnAadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				BLFacade facade=MainGUI.getBusinessLogic();
				//se debe añadir saldo para el usuario registrado, mantener login
				float sa = Integer.parseInt(textField.getText());
				facade.anadirSaldo(name, sa);
			}
		});
		btnAadir.setBounds(335, 229, 89, 23);
		getContentPane().add(btnAadir);
		
		btnMisApuestas = new JButton("Mis Apuestas");
		btnMisApuestas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblNewLabel.setVisible(false);
				MisApuestasGUI a = new MisApuestasGUI(name);
				a.setVisible(true);
				textField.setVisible(false);
				lblSaldoAAadir.setVisible(false);
				btnAadir.setVisible(false);
			}
		});
		btnMisApuestas.setBounds(117, 113, 202, 23);
		getContentPane().add(btnMisApuestas);
		
		JButton btnBalanceDeBeneficios = new JButton("Balance de beneficios");
		btnBalanceDeBeneficios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lblNewLabel.setVisible(false);
				BalanceGUI a = new BalanceGUI(name);
				a.setVisible(true);
				textField.setVisible(false);
				lblSaldoAAadir.setVisible(false);
				btnAadir.setVisible(false);
			}
		});
		btnBalanceDeBeneficios.setBounds(117, 147, 202, 23);
		getContentPane().add(btnBalanceDeBeneficios);
		
		btnNewButton = new JButton("Historial de Apuestas");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lblNewLabel.setVisible(false);
				HistorialDeApuestasGUI2 a = new HistorialDeApuestasGUI2(name);
				a.setVisible(true);
				textField.setVisible(false);
				lblSaldoAAadir.setVisible(false);
				btnAadir.setVisible(false);
			}
		});
		btnNewButton.setBounds(117, 181, 202, 20);
		getContentPane().add(btnNewButton);
		btnAadir.setVisible(false);

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		this.setBounds(100, 100, 450, 300);
	}
}
