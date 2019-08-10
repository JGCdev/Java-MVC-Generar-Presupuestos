package vistas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import clases.Gestion;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Insets;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.border.MatteBorder;

public class VistaClientes extends JFrame {

	private JTable table;
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnVolver;
	private JButton btnEliminarCliente;
	private JButton btnModificarCliente;
	private JButton btnAadirCliente;
	private JButton btnMail;
	private JButton btnVerProyectosDel;
	private JButton btnPresup;

	public VistaClientes() {
		setTitle("Clientes");
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
		 
		 btnEliminarCliente.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		//Marcamos el index del jlist
	        		 int index = table.getSelectedRow();
	        		 
	        		 if (index != -1) {
	        		 //Sacamos el ID de cliente 
	        		 int id = Gestion.getListaClientes().get(index).getId();
	        		 try {
	        			 Gestion.eliminarCliente(id);
	        			 setVisible(false);
	        			 //Volvemos a lanzar para actualizar la aplicación
	        			 VistaClientes frame = new VistaClientes();
	 					 frame.setVisible(true);
	 					 JOptionPane.showMessageDialog(null, "Cliente eliminado");
	 					 Gestion.guardarDatos();
	 					 
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, "No se pudo eliminar el cliente");
					}
	        		} 
	        	
	        	}
	      });
		 
		 
		 btnModificarCliente.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		 int index = table.getSelectedRow();
	        		 //Si se ha seleccionado un index, lanzamos registro de parámetros
	        		 if (index != -1) {
	        			//Sacamos el ID de cliente 
		        		 int id = Gestion.getListaClientes().get(index).getId();
		        		 String nombre = Gestion.getListaClientes().get(index).getNombre();
		        		 String DNI = Gestion.getListaClientes().get(index).getDNI();
		        		 String email = Gestion.getListaClientes().get(index).getEmail();
		        		 String telefono = Gestion.getListaClientes().get(index).getTelefono();
		        		 LocalDate fechaRegistro = Gestion.getListaClientes().get(index).getFechaRegistro();
		        		 setVisible(false);
						 VentanaModifCliente frame = new VentanaModifCliente(nombre, DNI, email, telefono, fechaRegistro, id);
						 frame.setVisible(true);
	        		 }
	        		 
	        	}
	     });
		 
		 btnAadirCliente.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		setVisible(false);
	        		AnadirCliente frame = new AnadirCliente();
					frame.setVisible(true);
	        	}
	     });
		 
		 btnMail.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		
	        		int index = table.getSelectedRow();
	        		String email = Gestion.getListaClientes().get(index).getEmail();
	        		EnviarEmail frame = new EnviarEmail(email);
					frame.setVisible(true);
	        	}
	     });
		 
		 btnVerProyectosDel.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		int index = table.getSelectedRow();
	        		int id = Gestion.getListaClientes().get(index).getId();
	        		setVisible(false);
	        		VistaProyectos frame = new VistaProyectos(id);
	        		frame.setVisible(true);
	        		
	        	}
	     });
		 
		 btnPresup.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		int index = table.getSelectedRow();
	        		int id = Gestion.getListaClientes().get(index).getId();
	        		setVisible(false);
	        		CrearPresupuesto frame = new CrearPresupuesto(id);
	        		frame.setVisible(true);
	        	}
	     });
	}
	
	private void iniciarComponentes() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 779, 488);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 48, 627, 359);
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
        		"ID", "NOMBRE", "DNI", "TELEFONO", "MAIL", "FECHA REGISTRO"
        	};
        tableModel.setColumnIdentifiers(columnNames);
        Object[] fila = new Object[tableModel.getColumnCount()];
        for (int i = 0; i < Gestion.getListaClientes().size(); i++) {
        	fila[0] = Gestion.getListaClientes().get(i).getId();
        	fila[1] = Gestion.getListaClientes().get(i).getNombre();
        	fila[2] = Gestion.getListaClientes().get(i).getDNI();
        	fila[3] = Gestion.getListaClientes().get(i).getTelefono();
        	fila[4] = Gestion.getListaClientes().get(i).getEmail();
        	fila[5] = Gestion.getListaClientes().get(i).getFechaRegistro();
        	tableModel.addRow(fila);
        	}
        
        table.setModel(tableModel);
        table.setBounds(517, 29, 206, 221);
        table.getColumnModel().getColumn(0).setPreferredWidth(1);
        
        
        JLabel lblClientesRegistrados = new JLabel("Clientes Registrados");
        lblClientesRegistrados.setBounds(10, 23, 128, 14);
        contentPane.add(lblClientesRegistrados);
        
        btnVolver = new JButton("Volver");
        btnVolver.setBorderPainted(false);
        btnVolver.setMargin(new Insets(0, 5, 0, 5));
        btnVolver.setContentAreaFilled(false);
        btnVolver.setIcon(new ImageIcon(VistaClientes.class.getResource("/imagenes/iconos/volvermin.png")));
        
        btnVolver.setBounds(664, 414, 89, 30);
        contentPane.add(btnVolver);
        
        btnEliminarCliente = new JButton("Eliminar");
        btnEliminarCliente.setFocusPainted(false);
        btnEliminarCliente.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
        btnEliminarCliente.setBackground(Color.LIGHT_GRAY);
        btnEliminarCliente.setIcon(new ImageIcon(VistaClientes.class.getResource("/imagenes/iconos/eliminar_min.png")));
        btnEliminarCliente.setMargin(new Insets(0, 5, 0, 5));
       
        btnEliminarCliente.setBounds(647, 138, 106, 30);
        contentPane.add(btnEliminarCliente);
        
        btnModificarCliente = new JButton("Modificar");
        btnModificarCliente.setFocusPainted(false);
        btnModificarCliente.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
        btnModificarCliente.setBackground(Color.LIGHT_GRAY);
        btnModificarCliente.setIcon(new ImageIcon(VistaClientes.class.getResource("/imagenes/iconos/editar_min.png")));
        btnModificarCliente.setMargin(new Insets(0, 5, 0, 5));
       
        btnModificarCliente.setBounds(647, 92, 106, 30);
        contentPane.add(btnModificarCliente);
        
        btnAadirCliente = new JButton("A\u00F1adir");
        btnAadirCliente.setFocusPainted(false);
        btnAadirCliente.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
        btnAadirCliente.setBackground(Color.LIGHT_GRAY);
        btnAadirCliente.setIcon(new ImageIcon(VistaClientes.class.getResource("/imagenes/iconos/anadir_min.png")));
        btnAadirCliente.setMargin(new Insets(0, 5, 0, 5));
        
        btnAadirCliente.setBounds(647, 45, 106, 30);
        contentPane.add(btnAadirCliente);
        
        btnMail = new JButton("Enviar mail");
        btnMail.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
        btnMail.setBackground(Color.LIGHT_GRAY);
       
        btnMail.setBounds(404, 418, 109, 23);
        contentPane.add(btnMail);
        
        btnVerProyectosDel = new JButton("Ver Proyectos del Cliente");
        btnVerProyectosDel.setBackground(Color.LIGHT_GRAY);
        btnVerProyectosDel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
       
        btnVerProyectosDel.setBounds(197, 418, 191, 23);
        contentPane.add(btnVerProyectosDel);
        
        btnPresup = new JButton("Realizar Presupuesto");
        btnPresup.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
        btnPresup.setBackground(new Color(192, 192, 192));
        
        btnPresup.setBounds(10, 418, 170, 23);
        contentPane.add(btnPresup);
	}
}
