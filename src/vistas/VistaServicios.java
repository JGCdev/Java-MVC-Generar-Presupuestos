package vistas;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import clases.Gestion;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class VistaServicios extends JFrame {


	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JButton btnVolver;
	private JButton btnAgregarNuevo;
	private JButton btnModificar;
	private JButton btnBorrar;


	/**
	 * Create the frame.
	 */
	public VistaServicios() {
		setTitle("Servicios");
		iniciarComponentes();
		iniciarManejadores();
		
		setLocationRelativeTo(null);
		
	}


	private void iniciarManejadores() {
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				VentanaPrincipal frame1 = new VentanaPrincipal();
				frame1.setVisible(true);
			}
		});
		
		btnAgregarNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				AgregarServicio frame1 = new AgregarServicio();
				frame1.setVisible(true);
			}
		});
		
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = table.getSelectedRow();
				 if (index != -1) {
	        		//Sacamos el ID de cliente 
		        		 int ref = Gestion.getListaServicios().get(index).getRef();
		        		 String descripcion = Gestion.getListaServicios().get(index).getDescripcion();
		        		 Double precioU = Gestion.getListaServicios().get(index).getpUnidad();
		        		 setVisible(false);
						 VentanaModifServicio frame = new VentanaModifServicio(ref, descripcion, precioU);
						 frame.setVisible(true);
	        	}
			}
		});
		
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = table.getSelectedRow();
				try {
					Gestion.eliminarServicio(index);
					JOptionPane.showMessageDialog(null, "Servicio eliminado");
					setVisible(false);
					VistaServicios vs = new VistaServicios();
					vs.setVisible(true);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
	}


	private void iniciarComponentes() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 379);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 57, 390, 173);
		contentPane.add(scrollPane);
		
		table = new JTable() {
			private static final long serialVersionUID = 1L;
			//Sobreescribimos el método isCellEditable de la propia clase JTable para que las celdas no sean editables
			public boolean isCellEditable(int rowIndex, int colIndex) {
		        return false; 
		    }
			
		};
		scrollPane.setViewportView(table);
		DefaultTableModel tableModel = new DefaultTableModel();
        String[]columnNames = {
        		"REF", "DESCRIPCIÓN", "P.UNIDAD"
        	};
        tableModel.setColumnIdentifiers(columnNames);
        Object[] fila = new Object[tableModel.getColumnCount()];
        for (int i = 0; i < Gestion.getListaServicios().size(); i++) {
        	fila[0] = Gestion.getListaServicios().get(i).getRef();
        	fila[1] = Gestion.getListaServicios().get(i).getDescripcion();
        	fila[2] = Gestion.getListaServicios().get(i).getpUnidad() + "€";
        	tableModel.addRow(fila);
        	}
        
        table.setModel(tableModel);
        table.setBounds(517, 29, 206, 221);
        table.getColumnModel().getColumn(0).setPreferredWidth(1);
		
		JLabel lblServiciosDisponibles = new JLabel("Servicios disponibles");
		lblServiciosDisponibles.setBounds(21, 32, 144, 14);
		contentPane.add(lblServiciosDisponibles);
		
		btnAgregarNuevo = new JButton("Agregar nuevo");
		
		btnAgregarNuevo.setBounds(21, 252, 123, 23);
		contentPane.add(btnAgregarNuevo);
		
		btnModificar = new JButton("Modificar");
		
		btnModificar.setBounds(173, 252, 123, 23);
		contentPane.add(btnModificar);
		
		btnBorrar = new JButton("Borrar");
		
		btnBorrar.setBounds(322, 252, 89, 23);
		contentPane.add(btnBorrar);
		
		btnVolver = new JButton("Volver");
		
		btnVolver.setBounds(322, 307, 89, 23);
		contentPane.add(btnVolver);
		
	}

}
