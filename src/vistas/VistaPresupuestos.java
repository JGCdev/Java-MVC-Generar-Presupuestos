package vistas;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.itextpdf.text.DocumentException;

import clases.GeneratePDFileIText;
import clases.Gestion;
import clases.Presupuesto;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class VistaPresupuestos extends JFrame {

	private static final long serialVersionUID = -93211200363964960L;
	private JPanel contentPane;
	private JTable table;
	private JButton btnVolver;
	private JButton btnAadirNuevo;
	private JButton btnGenerarPdf;
	private JButton btnEnviarPorEmail;
	private JButton btnEliminar;
	private JButton btnModificar;

	/**
	 * Create the frame.
	 */
	public VistaPresupuestos() {
		setTitle("Presupuestos");
		iniciarComponentes();
		iniciarManejadores();
		setLocationRelativeTo(null);
	}

	private void iniciarComponentes() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 731, 519);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnAadirNuevo = new JButton("A\u00F1adir nuevo");
		
		btnAadirNuevo.setBounds(21, 374, 113, 23);
		contentPane.add(btnAadirNuevo);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 21, 668, 342);
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
        		"NUM", "PARA", "FECHA", "PRECIO TOTAL", "FINALIZACIÓN"
        	};
        tableModel.setColumnIdentifiers(columnNames);
        Object[] fila = new Object[tableModel.getColumnCount()];
        for (int i = 0; i < Gestion.getListaPresupuestos().size(); i++) {
        	fila[0] = Gestion.getListaPresupuestos().get(i).getId();
        	fila[1] = Gestion.getListaPresupuestos().get(i).getPara();
        	fila[2] = Gestion.getListaPresupuestos().get(i).getFecha();
        	fila[3] = Gestion.getListaPresupuestos().get(i).getSubtotal()-Gestion.getListaPresupuestos().get(i).getDescuento()+"€";
        	fila[4] = Gestion.getListaPresupuestos().get(i).getFinalizacionEstimada()+ " días";
        	tableModel.addRow(fila);
        	}
        
        table.setModel(tableModel);
        tableModel.fireTableDataChanged();
        table.setBounds(517, 29, 206, 221);
        table.getColumnModel().getColumn(0).setPreferredWidth(20);
		
		btnModificar = new JButton("Modificar");
		
		btnModificar.setBounds(144, 374, 113, 23);
		contentPane.add(btnModificar);
		
		btnEliminar = new JButton("Eliminar");
		
		btnEliminar.setBounds(267, 374, 130, 23);
		contentPane.add(btnEliminar);
		
		btnVolver = new JButton("Volver");
		
		btnVolver.setBounds(605, 447, 89, 23);
		contentPane.add(btnVolver);
		
		btnGenerarPdf = new JButton("Generar PDF");
		
		btnGenerarPdf.setBounds(20, 447, 247, 23);
		contentPane.add(btnGenerarPdf);
		
		btnEnviarPorEmail = new JButton("Enviar por email");
		
		btnEnviarPorEmail.setBounds(283, 447, 247, 23);
		contentPane.add(btnEnviarPorEmail);
	}

	private void iniciarManejadores() {
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				VentanaPrincipal frame1 = new VentanaPrincipal();
				frame1.setVisible(true);
			}
		});
		
		btnAadirNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				CrearPresupuesto frame = new CrearPresupuesto();
				frame.setVisible(true);
			}
		});
		
		btnGenerarPdf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Presupuesto aux;
				int index = table.getSelectedRow();
				aux= Gestion.getListaPresupuestos().get(index);
				//Creamos nombre de archivo en funcion de su id
				String nombreArchivo = Gestion.getListaPresupuestos().get(index).getNombreArchivo();
				
				try {
					new GeneratePDFileIText().createPdf(nombreArchivo, aux);
					JOptionPane.showMessageDialog(null, "El presupuesto ha sido creado");
				} catch (DocumentException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Problema al generar el presupuesto");
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		});
		
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int index = table.getSelectedRow();
				try {
					Gestion.eliminarPresupuesto(index);
					setVisible(false);
		   		    //Volvemos a lanzar para actualizar la aplicación
		   			VistaPresupuestos frame = new VistaPresupuestos();
					frame.setVisible(true);
					JOptionPane.showMessageDialog(null, "Presupuesto borrado");
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Problema al borrar el presupuesto");
				}

			}
		});
		
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int indice = table.getSelectedRow();
				dispose();
				VentanaModifPresupuesto vmp = new VentanaModifPresupuesto(indice);
				vmp.setVisible(true);
			}
		});
		
		btnEnviarPorEmail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int indice = table.getSelectedRow();
				setVisible(false);
				EnviarEmail ee = new EnviarEmail(Gestion.getPresupuesto(indice));
				ee.setVisible(true);
			}
		});
		
		
	}

}
