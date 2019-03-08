package gui;

import java.awt.EventQueue;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import businessLogic.BLFacade;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CreateUserGUI  extends JFrame{
	private JTextField textFieldDni;
	private JTextField textFieldUsuario;
	private JTextField textFieldContrasena;



	/**
	 * Create the application.
	 */
	public CreateUserGUI() {
		super();
		setTitle("Registrarse");
		getContentPane().setLayout(null);
		
		JLabel lblDni = new JLabel("DNI:");
		lblDni.setBounds(44, 34, 108, 21);
		getContentPane().add(lblDni);
		
		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setBounds(44, 71, 108, 21);
		getContentPane().add(lblUsuario);
		
		JLabel lblContrasena = new JLabel("Contrasena:");
		lblContrasena.setBounds(44, 120, 108, 21);
		getContentPane().add(lblContrasena);
		
		textFieldDni = new JTextField();
		textFieldDni.setBounds(194, 34, 215, 21);
		getContentPane().add(textFieldDni);
		textFieldDni.setColumns(10);
		
		textFieldUsuario = new JTextField();
		textFieldUsuario.setBounds(194, 71, 215, 21);
		getContentPane().add(textFieldUsuario);
		textFieldUsuario.setColumns(10);
		
		textFieldContrasena = new JTextField();
		textFieldContrasena.setBounds(194, 120, 215, 21);
		getContentPane().add(textFieldContrasena);
		textFieldContrasena.setColumns(10);
		
		JButton btnRegistrarse = new JButton("Registrarse");
		btnRegistrarse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BLFacade facade=MainGUI.getBusinessLogic();
				String dni = textFieldDni.getText();
				String name = textFieldUsuario.getText();
				String contra = textFieldContrasena.getText();
				if (name.length()==0 || contra.length()==0 || dni.length()==0) {
					System.out.println("Falta usuario o contraseña o dni");
				}else {
					if(facade.Esta(dni)) {
						System.out.println("El usuario existe");
					}else {
						facade.crearUsuario(dni, name, contra);
					}
				}
			}
		});
		btnRegistrarse.setBounds(114, 174, 233, 42);
		getContentPane().add(btnRegistrarse);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		// this.setSize(271, 295);
		this.setSize(495, 290);
	}
}
