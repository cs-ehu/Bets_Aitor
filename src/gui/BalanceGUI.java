package gui;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

public class BalanceGUI extends JFrame {
	private static final long serialVersionUID = 1L;
	private final JLabel jLabelQueries = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Apuestas"));

	private JButton jButtonClose = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close"));
	private JScrollPane scrollPaneQueries = new JScrollPane();
	private JTable tableQueries = new JTable();

	private String[] columnNamesQueries = new String[] { "Evento", "Fecha evento", "Pregunta", "Cuota",
			"Dinero Apostado", "Resultado", "Beneficios", "Acertada" };

	private DefaultTableModel tableModelQueries = new DefaultTableModel(null, columnNamesQueries);
	private JLabel lblBeneficios;

	public BalanceGUI(String name) {
		setTitle(ResourceBundle.getBundle("Etiquetas").getString("BalanceGUI.this.title")); //$NON-NLS-1$ //$NON-NLS-2$

		this.getContentPane().setLayout(null);
		this.setSize(new Dimension(700, 350));
		jLabelQueries.setBounds(43, 24, 406, 14);
		this.getContentPane().add(jLabelQueries);

		scrollPaneQueries.setBounds(new Rectangle(23, 55, 640, 164));
		jButtonClose.setBounds(new Rectangle(245, 270, 130, 30));

		jButtonClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButton2_actionPerformed(e);
			}
		});

		this.getContentPane().add(jButtonClose, null);
		tableModelQueries.setColumnCount(8);
		tableQueries = new JTable(tableModelQueries);

		BLFacade facade = MainGUI.getBusinessLogic();
		List<Apuesta> apues = facade.getApuestasFinalizadas(name);
		
		Float CantApos=0f;
		Float CantGanad=0f;

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
				row.add(ap.getApuesta().getCuota());
				row.add(ap.getDineroApostado());
				row.add(ap.getApuesta().getResult().getResult());
				row.add(ap.getBeneficios());
				//row.add(ap.getCobrada());
				row.add(ap.getAcertada());
				tableModelQueries.addRow(row);

				CantApos= CantApos+ ap.getDineroApostado();
				if(ap.getAcertada()==true) {
					CantGanad = CantGanad +ap.getBeneficios();
				}
				
			}
			tableQueries.getColumnModel().getColumn(0).setPreferredWidth(25);
			tableQueries.getColumnModel().getColumn(1).setPreferredWidth(50);
			tableQueries.getColumnModel().getColumn(2).setPreferredWidth(25);
			tableQueries.getColumnModel().getColumn(3).setPreferredWidth(15);
			tableQueries.getColumnModel().getColumn(4).setPreferredWidth(15);
			tableQueries.getColumnModel().getColumn(5).setPreferredWidth(15);
			tableQueries.getColumnModel().getColumn(6).setPreferredWidth(10);
			tableQueries.getColumnModel().getColumn(7).setPreferredWidth(10);
			//tableQueries.getColumnModel().getColumn(8).setPreferredWidth(10);

		}

		scrollPaneQueries.setViewportView(tableQueries);

		tableQueries.setModel(tableModelQueries);
		tableQueries.getColumnModel().getColumn(0).setPreferredWidth(25);
		tableQueries.getColumnModel().getColumn(1).setPreferredWidth(50);
		tableQueries.getColumnModel().getColumn(2).setPreferredWidth(25);
		tableQueries.getColumnModel().getColumn(3).setPreferredWidth(15);
		tableQueries.getColumnModel().getColumn(4).setPreferredWidth(15);
		tableQueries.getColumnModel().getColumn(5).setPreferredWidth(15);
		tableQueries.getColumnModel().getColumn(6).setPreferredWidth(10);
		tableQueries.getColumnModel().getColumn(7).setPreferredWidth(10);
		//tableQueries.getColumnModel().getColumn(8).setPreferredWidth(10);

		this.getContentPane().add(scrollPaneQueries, null);
		
		lblBeneficios = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("BalanceGUI.lblBeneficios.text")); //$NON-NLS-1$ //$NON-NLS-2$
		lblBeneficios.setBounds(62, 230, 535, 30);
		getContentPane().add(lblBeneficios);
		lblBeneficios.setText("La cantidad total apostada es: "+String.valueOf(CantApos)+"€, las ganacias son de: "+ String.valueOf(CantGanad)+"€ y el balance es de: "+String.valueOf(CantGanad-CantApos)+"€.");

	}

	private void jButton2_actionPerformed(ActionEvent e) {
		this.setVisible(false);
	}
}
