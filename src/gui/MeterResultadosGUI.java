package gui;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import businessLogic.BLFacade;
import domain.Apuesta;
import javax.swing.JTextField;
import java.awt.Color;

public class MeterResultadosGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private final JLabel jLabelQueries = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Apuestas"));

	private JButton jButtonClose = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close"));
	private JScrollPane scrollPaneQueries = new JScrollPane();
	private JTable tableQueries = new JTable();

	private String[] columnNamesQueries = new String[] { "Evento","Fecha evento", "Pregunta", "Resultado","Corregida",};

	private DefaultTableModel tableModelQueries = new DefaultTableModel(null, columnNamesQueries);
	private JTextField textFieldResultado;
	private domain.Apuesta apu;
	
	

	public MeterResultadosGUI() {
		setTitle(ResourceBundle.getBundle("Etiquetas").getString("MisApuestasGUI.this.title")); //$NON-NLS-1$ //$NON-NLS-2$

		this.getContentPane().setLayout(null);
		this.setSize(new Dimension(700, 350));
		jLabelQueries.setBounds(43, 24, 130, 14);
		this.getContentPane().add(jLabelQueries);

		scrollPaneQueries.setBounds(new Rectangle(23, 55, 640, 192));
		jButtonClose.setBounds(new Rectangle(533, 258, 130, 30));

		jButtonClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButton2_actionPerformed(e);
			}
		});

		this.getContentPane().add(jButtonClose, null);
		tableModelQueries.setColumnCount(6);
		tableQueries = new JTable(tableModelQueries);

		BLFacade facade = MainGUI.getBusinessLogic();
		List<Apuesta> apues = facade.getApuestasACoregir();

		if (apues.isEmpty()) {
			System.out.println("no hay apuestas");
		} else {
			System.out.println("hay apuestas");
			for (domain.Apuesta ap : apues) {
				Vector<Object> row = new Vector<Object>();
				System.out.println(ap.getApuesta().getEvent().getDescription());
				
				row.add(ap.getApuesta().getEvent().getDescription());
				row.add(ap.getApuesta().getEvent().getEventDate());
				row.add(ap.getApuesta().getQuestion());
				row.add(ap.getApuesta().getResult().getResult());
				row.add(ap.getCobrada());
				row.add(ap);
				tableModelQueries.addRow(row);
				// tableModelQueries.insertRow(tableModelQueries.getRowCount(), row);

			}
			tableQueries.getColumnModel().getColumn(0).setPreferredWidth(25);
			tableQueries.getColumnModel().getColumn(1).setPreferredWidth(50);
			tableQueries.getColumnModel().getColumn(2).setPreferredWidth(25);
			tableQueries.getColumnModel().getColumn(3).setPreferredWidth(15);
			tableQueries.getColumnModel().getColumn(4).setPreferredWidth(15);
			tableQueries.getColumnModel().removeColumn(tableQueries.getColumnModel().getColumn(5));

		}

		scrollPaneQueries.setViewportView(tableQueries);

		tableQueries.setModel(tableModelQueries);
		tableQueries.getColumnModel().getColumn(0).setPreferredWidth(25);
		tableQueries.getColumnModel().getColumn(1).setPreferredWidth(50);
		tableQueries.getColumnModel().getColumn(2).setPreferredWidth(25);
		tableQueries.getColumnModel().getColumn(3).setPreferredWidth(15);
		tableQueries.getColumnModel().getColumn(4).setPreferredWidth(15);


		tableQueries.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i=tableQueries.getSelectedRow();
				apu=(domain.Apuesta)tableModelQueries.getValueAt(i,5); // obtain qu object		
			}
		});
		
		

		this.getContentPane().add(scrollPaneQueries, null);
		
		JLabel lblMeterResultado = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("MeterResultadosGUI.lblMeterResultado.text")); //$NON-NLS-1$ //$NON-NLS-2$
		lblMeterResultado.setBounds(23, 263, 130, 20);
		getContentPane().add(lblMeterResultado);
		
		textFieldResultado = new JTextField();
		//textField.setText(ResourceBundle.getBundle("Etiquetas").getString("MeterResultadosGUI.textField.text")); //$NON-NLS-1$ //$NON-NLS-2$
		textFieldResultado.setBounds(128, 261, 185, 25);
		getContentPane().add(textFieldResultado);
		textFieldResultado.setColumns(10);
		
		JButton btnMeterResultado = new JButton(ResourceBundle.getBundle("Etiquetas").getString("MeterResultadosGUI.btnMeterResultado.text")); //$NON-NLS-1$ //$NON-NLS-2$
		btnMeterResultado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				BLFacade facade=MainGUI.getBusinessLogic();
				String res = textFieldResultado.getText();
				if (res== null || apu == null) {
					System.out.println("Mete resultado o selecciona");
				}else {
					facade.corregirResultado(apu, res);
					
					jLabelQueries.setVisible(false);
					jButton2_actionPerformed(arg0);
					MeterResultadosGUI m= new MeterResultadosGUI();
					m.setVisible(true);				
				}
			}
		});
		btnMeterResultado.setBounds(344, 258, 130, 30);
		getContentPane().add(btnMeterResultado);


	}

	private void jButton2_actionPerformed(ActionEvent e) {
		this.setVisible(false);
	}
}
