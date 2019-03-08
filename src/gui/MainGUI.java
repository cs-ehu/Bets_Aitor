package gui;

/**
 * @author Software Engineering teachers
 */


import javax.swing.*;

import domain.Event;
import domain.User;
import businessLogic.BLFacade;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Vector;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import dataAccess.DataAccess;


public class MainGUI extends JFrame {
	
	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;
	
    private static BLFacade appFacadeInterface;
	
	public static BLFacade getBusinessLogic(){
		return appFacadeInterface;
	}

	public static void setBussinessLogic (BLFacade afi){
		appFacadeInterface=afi;
	}
	protected JLabel jLabelIniciarSesion;
	private JTextField textFieldUsuario;
	private JPasswordField passwordField;
	
	/**
	 * This is the default constructor
	 */
	public MainGUI() {
		super();
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				try {
					//if (ConfigXML.getInstance().isBusinessLogicLocal()) facade.close();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					System.out.println("Error: "+e1.toString()+" , probably problems with Business Logic or Database");
				}
				System.exit(1);
			}
		});

		initialize();
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		// this.setSize(271, 295);
		this.setSize(495, 290);
		this.setContentPane(getJContentPane());
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("MainTitle"));
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getLblNewLabel());
			
			textFieldUsuario = new JTextField();
			textFieldUsuario.setBounds(154, 71, 202, 27);
			jContentPane.add(textFieldUsuario);
			textFieldUsuario.setColumns(10);
			
			JLabel lblUsuario = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("lblUsuario")); //$NON-NLS-1$ //$NON-NLS-2$
			lblUsuario.setBounds(52, 74, 92, 21);
			jContentPane.add(lblUsuario);
			
			JLabel lblContrasea = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("lblContrasea")); //$NON-NLS-1$ //$NON-NLS-2$
			lblContrasea.setBounds(52, 125, 92, 21);
			jContentPane.add(lblContrasea);
			
			JButton btnIniciarSesin = new JButton(ResourceBundle.getBundle("Etiquetas").getString("btnIniciarSesin")); //$NON-NLS-1$ //$NON-NLS-2$
			btnIniciarSesin.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					BLFacade facade=MainGUI.getBusinessLogic();
					String name = textFieldUsuario.getText();
					String contra = String.valueOf(passwordField.getPassword());
					if (name.length()==0 || contra.length()==0) {
						System.out.println("Falta usuario o contraseña");
					}else {						
						if (facade.Login(name, contra)) {
							if (facade.EsAdmin(name, contra)) {
								AdminMainPageGUI a = new AdminMainPageGUI();
								a.setVisible(true);
							}else {
								UserMainPageGUI a = new UserMainPageGUI(name);
								a.setVisible(true);
							}
						}else {
							System.out.println("usuario o contraseña mal");
						}
					}
				}
			});
			btnIniciarSesin.setBounds(55, 183, 166, 27);
			jContentPane.add(btnIniciarSesin);
			
			JButton btnRegistrarse = new JButton(ResourceBundle.getBundle("Etiquetas").getString("btnRegistrarse")); //$NON-NLS-1$ //$NON-NLS-2$
			btnRegistrarse.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					CreateUserGUI a = new CreateUserGUI();
					a.setVisible(true);
				}
			});
			btnRegistrarse.setBounds(254, 183, 166, 27);
			jContentPane.add(btnRegistrarse);
			
			passwordField = new JPasswordField();
			//passwordField.setText(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.passwordField.text")); //$NON-NLS-1$ //$NON-NLS-2$
			passwordField.setBounds(154, 125, 202, 27);
			jContentPane.add(passwordField);
		}
		return jContentPane;
	}
	

	private JLabel getLblNewLabel() {
		if (jLabelIniciarSesion == null) {
			jLabelIniciarSesion = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("SelectOption"));
			jLabelIniciarSesion.setBounds(0, 1, 479, 62);
			jLabelIniciarSesion.setFont(new Font("Tahoma", Font.BOLD, 13));
			jLabelIniciarSesion.setForeground(Color.BLACK);
			jLabelIniciarSesion.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return jLabelIniciarSesion;
	}
	
	
} // @jve:decl-index=0:visual-constraint="0,0"

