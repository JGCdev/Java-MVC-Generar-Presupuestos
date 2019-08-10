package vistas;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import clases.Cliente;
import clases.Gestion;
import clases.Servicio;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JSpinner;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.SpinnerNumberModel;
import java.awt.Insets;

public class CrearPresupuesto extends JFrame {

	private static final long serialVersionUID = -3296953134758604859L;

	private JPanel contentPane;
	private JTextField txtId;
	private JTextField txtFin;
	private JList<Object> jListServicios;
	private JList<Object> jListPresup;
	private JScrollPane scrollPane_1;
	private JTextField txtSubTotal;
	private JButton btnAadir;
	private ArrayList<Servicio> listaPresupuestoFin = new ArrayList<Servicio>();
	private JButton btnCrear;
	private JTextField txtDesc;
	private JLabel lblDescuento;
	private JSpinner spinnerServi;
	private static double precioTemporal = 0.0;
	double descuento;
	private JTextField txtTotal;
	private JLabel lblTotal_1;
	private JButton btnAtrs;
	private JButton btnVistaclientes;
	private JButton btnEliminar;
	private JButton btnSubir;
	private JButton btnBajar;


	/**
	 * Create the frame.
	 * @param id 
	 */
	public CrearPresupuesto(int id) {
		setTitle("Crear presupuesto");
		iniciarComponentes();
		iniciarId(id);
		iniciarManejadores();

		setLocationRelativeTo(null);

	}
	
	public CrearPresupuesto() {
		setTitle("Crear presupuesto");
		iniciarComponentes();
		iniciarId2();
		iniciarManejadores();
		setLocationRelativeTo(null);

	}

	private void iniciarManejadores() {
		btnAadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				double total = 0;
				int[] indice = jListServicios.getSelectedIndices();
				for (int i=0; i<indice.length; i++) {
					int pos = indice[i];
					int numero = (int) spinnerServi.getValue();
					Gestion.getListaServicios().get(pos).setUnidades(numero);
					precioTemporal = precioTemporal + (numero*Gestion.getListaServicios().get(pos).getpUnidad());
					listaPresupuestoFin.add(Gestion.getListaServicios().get(pos));
					total = total + (Gestion.getListaServicios().get(pos).getpUnidad()*numero);

				}
				
				descuento = Double.parseDouble(txtDesc.getText());
				
				
				
				txtSubTotal.setText(String.valueOf(precioTemporal));
				
				txtTotal.setText(String.valueOf(precioTemporal-descuento));
				
				jListPresup.setListData(listaPresupuestoFin.toArray());
				scrollPane_1.setViewportView(jListPresup);

			}

			
		});
		
		btnAtrs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				precioTemporal = 0;
				setVisible(false);
				VistaPresupuestos frame = new VistaPresupuestos();
				frame.setVisible(true);
			}
		});
		
		btnCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Cliente aux;
				Cliente clienteId = null;
				int id = Integer.parseInt(txtId.getText());
				for (int i=0; i<Gestion.getListaClientes().size();i++) {
					aux = Gestion.getListaClientes().get(i);
					if (aux.getId()==id) {
						clienteId = aux;
					}
				}
				if (clienteId!=null) {
					String desc = txtDesc.getText();
					Double descuento = Double.parseDouble(desc);
					
					String subtotal = txtSubTotal.getText();
					Double dSubtotal = Double.parseDouble(subtotal);
					
					String fin = txtFin.getText();
					int finalizacion = Integer.parseInt(fin);
					
					try {
						Gestion.anadirPresupuestoLista(listaPresupuestoFin, clienteId, descuento, finalizacion, dSubtotal);
						Gestion.guardarDatos();
						JOptionPane.showMessageDialog(null, "Se añadió el presupuesto");
						precioTemporal = 0;
						setVisible(false);
						VistaPresupuestos frame = new VistaPresupuestos();
						frame.setVisible(true);
						
						
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, "Hubo algún error");
					}
					
					
				}else {
					JOptionPane.showMessageDialog(null, "El cliente no existe");
				}
				
				
				
			}

		});
		
	
		
		btnVistaclientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				VistaClientes frame = new VistaClientes();
				frame.setVisible(true);
			}
		});
		
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = jListPresup.getSelectedIndex();
				double aRestar = listaPresupuestoFin.get(index).getpUnidad()*listaPresupuestoFin.get(index).getUnidades();
				listaPresupuestoFin.remove(index);
				
				jListPresup.setListData(listaPresupuestoFin.toArray());
				
				precioTemporal = precioTemporal - aRestar;
				descuento = Double.parseDouble(txtDesc.getText());
				
				txtSubTotal.setText(String.valueOf(precioTemporal-0));
				
				txtTotal.setText(String.valueOf(precioTemporal-descuento));
				
				
				
			}
		});
		
		btnSubir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int indice = jListPresup.getSelectedIndex();
				//Si no es el primer índice, lo intercambiamos por el anterior
				if (indice>=1) {
				Servicio posicionVieja = listaPresupuestoFin.get(indice-1);
				listaPresupuestoFin.set(indice-1, listaPresupuestoFin.get(indice));
				listaPresupuestoFin.set(indice, posicionVieja);
				
				jListPresup.setListData(listaPresupuestoFin.toArray());
				}
			}
		});
		
		btnBajar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int indice = jListPresup.getSelectedIndex();
				//Si no es el último índice, lo intercambiamos por el siguiente
				if (indice<listaPresupuestoFin.size()-1) {
				Servicio posicionVieja = listaPresupuestoFin.get(indice+1);
				listaPresupuestoFin.set(indice+1, listaPresupuestoFin.get(indice));
				listaPresupuestoFin.set(indice, posicionVieja);
				
				jListPresup.setListData(listaPresupuestoFin.toArray());
				}
			}
		});
	}


	private void iniciarComponentes() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 672, 510);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		JLabel lblIdCliente = new JLabel("ID Cliente:");
		lblIdCliente.setBounds(10, 18, 86, 14);
		contentPane.add(lblIdCliente);
		
		JLabel lblFinalizacinEstimada = new JLabel("Finalizaci\u00F3n estimada (d\u00EDas):");
		lblFinalizacinEstimada.setBounds(157, 18, 167, 14);
		contentPane.add(lblFinalizacinEstimada);
		
		txtFin = new JTextField();
		txtFin.setBounds(157, 43, 86, 20);
		contentPane.add(txtFin);
		txtFin.setColumns(10);
		
		JLabel lblServicios = new JLabel("Servicios disponibles");
		lblServicios.setBounds(10, 101, 167, 14);
		contentPane.add(lblServicios);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 129, 273, 216);
		contentPane.add(scrollPane);
		
		jListServicios = new JList<>(Gestion.getListaServicios().toArray());
		jListServicios.setName("jListSource");
		scrollPane.setViewportView(jListServicios);
		
		JLabel lblPresupuesto = new JLabel("Presupuesto Final");
		lblPresupuesto.setBounds(308, 101, 118, 14);
		contentPane.add(lblPresupuesto);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(308, 129, 273, 216);
		contentPane.add(scrollPane_1);
		
		jListPresup = new JList<>(listaPresupuestoFin.toArray());
		jListPresup.setName("jListSource2");
		scrollPane_1.setViewportView(jListPresup);

		btnAadir = new JButton("A\u00F1adir");
		
		btnAadir.setBounds(106, 356, 89, 23);
		contentPane.add(btnAadir);
		
		btnEliminar = new JButton("Eliminar");
		
		btnEliminar.setBounds(308, 356, 89, 23);
		contentPane.add(btnEliminar);
		
		spinnerServi = new JSpinner();
		spinnerServi.setModel(new SpinnerNumberModel(new Integer(1), null, null, new Integer(1)));
		spinnerServi.setBounds(67, 357, 29, 20);
		contentPane.add(spinnerServi);
		
		JLabel lblTotal = new JLabel("Subtotal");
		lblTotal.setBounds(457, 360, 62, 14);
		contentPane.add(lblTotal);
		
		txtSubTotal = new JTextField();
		txtSubTotal.setBounds(519, 356, 62, 20);
		contentPane.add(txtSubTotal);
		txtSubTotal.setColumns(10);
		
		btnCrear = new JButton("Crear Presupuesto");
		
		btnCrear.setBounds(202, 438, 185, 23);
		contentPane.add(btnCrear);
		
		txtDesc = new JTextField();
		txtDesc.setBounds(519, 390, 62, 20);
		contentPane.add(txtDesc);
		txtDesc.setColumns(10);
		txtDesc.setText("0");
		
		lblDescuento = new JLabel("Descuento");
		lblDescuento.setBounds(457, 393, 72, 14);
		contentPane.add(lblDescuento);
		
		txtTotal = new JTextField();
		txtTotal.setBounds(519, 425, 62, 20);
		contentPane.add(txtTotal);
		txtTotal.setColumns(10);
		
		lblTotal_1 = new JLabel("Total");
		lblTotal_1.setBounds(457, 428, 62, 14);
		contentPane.add(lblTotal_1);
		
		btnAtrs = new JButton("Volver");
		btnAtrs.setBounds(10, 438, 147, 23);
		contentPane.add(btnAtrs);
		
		btnVistaclientes = new JButton("Ver Clientes");
		btnVistaclientes.setBounds(10, 67, 118, 23);
		contentPane.add(btnVistaclientes);
		
		btnSubir = new JButton("\u25B2");
		btnSubir.setMargin(new Insets(2, 5, 2, 5));
		
		btnSubir.setBounds(587, 127, 36, 23);
		contentPane.add(btnSubir);
		
		btnBajar = new JButton("\u25BC");
		btnBajar.setMargin(new Insets(2, 5, 2, 5));
		
		btnBajar.setBounds(587, 161, 36, 23);
		contentPane.add(btnBajar);
	}
	
	private void iniciarId(int id) {
		txtId = new JTextField();
		txtId.setBounds(10, 43, 86, 20);
		txtId.setText(String.valueOf(id));
		contentPane.add(txtId);
		txtId.setColumns(10);
		
	}


	private void iniciarId2() {
		txtId = new JTextField();
		txtId.setBounds(10, 43, 118, 20);
		contentPane.add(txtId);
		txtId.setColumns(10);

	}
	
	public static double getPreciotemporal() {
		return precioTemporal;
	}
}
