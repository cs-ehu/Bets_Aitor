package gui;

import businessLogic.BLFacade;
import com.toedter.calendar.JCalendar;
import domain.Question;
import domain.Apuesta;
import domain.Event;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import java.text.DateFormat;
import java.util.*;
import java.util.List;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;



public class HistorialDeApuestasGUI2 extends JFrame {
	private static final long serialVersionUID = 1L;
	private final JLabel jLabelQueries = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Apuestas")); 

	private JButton jButtonClose = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close"));
	private JScrollPane scrollPaneQueries = new JScrollPane();
	private JTable tableQueries= new JTable();


	private String[] columnNamesQueries = new String[] {
			"Evento",
			"Fecha evento",
			"Pregunta",
			"Cuota",
			"Dinero Apostado",
			"Resultado",
			"Beneficios",
			"Corregida",
			"Acertada"
	};
	
	private DefaultTableModel tableModelQueries = new DefaultTableModel(null,columnNamesQueries);
	
	public HistorialDeApuestasGUI2(String name)
	{
		setTitle(ResourceBundle.getBundle("Etiquetas").getString("MisApuestasGUI.this.title")); //$NON-NLS-1$ //$NON-NLS-2$
			
			this.getContentPane().setLayout(null);
			this.setSize(new Dimension(700, 350));
			jLabelQueries.setBounds(43, 24, 406, 14);
			this.getContentPane().add(jLabelQueries);

			scrollPaneQueries.setBounds(new Rectangle(23, 55, 640, 192));
			jButtonClose.setBounds(new Rectangle(220, 258, 130, 30));

			jButtonClose.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					jButton2_actionPerformed(e);
				}
			});
			
			this.getContentPane().add(jButtonClose, null);
			tableModelQueries.setColumnCount(9);
			tableQueries = new JTable(tableModelQueries);
			
			BLFacade facade=MainGUI.getBusinessLogic();		
			List<Apuesta> apues = facade.getApuestas(name);

			if(apues.isEmpty()) {
				System.out.println("no hay apuestas");
			}else {
				System.out.println("hay apuestas");
				for (domain.Apuesta ap:apues){
					Vector<Object> row = new Vector<Object>();
					System.out.println(ap.getApuesta().getEvent().getDescription());
					
					row.add(ap.getApuesta().getEvent().getDescription());
					row.add(ap.getApuesta().getEvent().getEventDate());
					row.add(ap.getApuesta().getQuestion());
					row.add(ap.getApuesta().getCuota());
					row.add(ap.getDineroApostado());
					row.add(ap.getApuesta().getResult().getResult());
					row.add(ap.getBeneficios());
					row.add(ap.getCobrada());
					row.add(ap.getAcertada());
					tableModelQueries.addRow(row);
					//tableModelQueries.insertRow(tableModelQueries.getRowCount(), row);
					
				
				}
				tableQueries.getColumnModel().getColumn(0).setPreferredWidth(25);
				tableQueries.getColumnModel().getColumn(1).setPreferredWidth(50);
				tableQueries.getColumnModel().getColumn(2).setPreferredWidth(25);
				tableQueries.getColumnModel().getColumn(3).setPreferredWidth(15);
				tableQueries.getColumnModel().getColumn(4).setPreferredWidth(15);
				tableQueries.getColumnModel().getColumn(5).setPreferredWidth(15);
				tableQueries.getColumnModel().getColumn(6).setPreferredWidth(10);
				tableQueries.getColumnModel().getColumn(7).setPreferredWidth(10);
				tableQueries.getColumnModel().getColumn(8).setPreferredWidth(10);
				
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
			tableQueries.getColumnModel().getColumn(8).setPreferredWidth(10);


			this.getContentPane().add(scrollPaneQueries, null);

	}

	private void jButton2_actionPerformed(ActionEvent e) {
		this.setVisible(false);
	}

}
