package vistas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import clases.Empleado;
import clases.Gestion;
import clases.Grafico;
import clases.Programador;

public class VistaProgramadores extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnVolver;
	private JButton btnNuevo;
	private JButton btnModificarEmpleado;
	private JButton btnEliminar;
	private JTable table;
	private JButton btnProyectoEmpleado;

	public VistaProgramadores() {
		setTitle("Empleados > Programadores");
		iniciarComponentes();
		iniciarManejadores();
		setLocationRelativeTo(null);
	}
	
	private void iniciarManejadores() {

		 btnVolver.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		setVisible(false);
					VistaEmpleados frame1 = new VistaEmpleados();
					frame1.setVisible(true);
	        	}
	     });
		 
		 btnModificarEmpleado.addActionListener(new ActionListener() {
		       	public void actionPerformed(ActionEvent e) {
		       		String ubicacion = null;
		       		int lenguajes = 0;
		       		int index = table.getSelectedRow();
	        		 //Si se ha seleccionado un index, lanzamos registro de parámetros
	        		 if (index != -1) {
	        			//Obtenemos el ID de cliente 
		        		 int id = Gestion.getListaEmpleados().get(index).getId();
		        		 String nombre = Gestion.getListaEmpleados().get(index).getNombre();
		        		 String DNI = Gestion.getListaEmpleados().get(index).getDni();
		        		 String email = Gestion.getListaEmpleados().get(index).getEmail();
		        		 String telefono = Gestion.getListaEmpleados().get(index).getTelefono();
		        		 
		        		 if (Gestion.getListaEmpleados().get(index).getClass().getSimpleName().equals("Programador")) {
		        			 Programador uno = (Programador)Gestion.getListaEmpleados().get(index);
			        		 lenguajes = uno.getLenguajesDominados();
			        		 setVisible(false);
			        		 VentanaModificarProgramador frame = new VentanaModificarProgramador(nombre, DNI, email, telefono, lenguajes, id);
							 frame.setVisible(true);
		        		 }else {
		        			 Grafico dos = (Grafico)Gestion.getListaEmpleados().get(index);
			        		 ubicacion = dos.getUbicacion();
			        		 setVisible(false);
			        		 VentanaModifGrafico frame = new VentanaModifGrafico(nombre, DNI, email, telefono, ubicacion, id);
							 frame.setVisible(true);
		        		 }
						 
	        		 }
		       		
		       	}
		 });
		 
		 btnEliminar.addActionListener(new ActionListener() {
		       	public void actionPerformed(ActionEvent e) {
		        		//Marcamos el index del jlist
		        		 int index = table.getSelectedRow();
		        		 if (index != -1) {
		        		 //Sacamos el ID de cliente 
			        		 int id = Gestion.getListaEmpleados().get(index).getId();
			        		 try {
			        			 Gestion.eliminarEmpleado(id);
			        			 setVisible(false);
			        			 //Volvemos a lanzar para actualizar la aplicación
			        			 VistaEmpleados frame = new VistaEmpleados();
			 					 frame.setVisible(true);
			 					 JOptionPane.showMessageDialog(null, "Programador eliminado");
			 					 Gestion.guardarDatos();
			 					 
							} catch (Exception e2) {
								JOptionPane.showMessageDialog(null, "No se pudo eliminar el programador");
							}
		        		}
		        		   	
		       	}
		 });
		 
		 btnNuevo.addActionListener(new ActionListener() {
		       	public void actionPerformed(ActionEvent e) {
		       		setVisible(false);
		       		AnadirGrafico frame = new AnadirGrafico();
					frame.setVisible(true);
		       	}
		 });
		 
		 btnProyectoEmpleado.addActionListener(new ActionListener() {
		       	public void actionPerformed(ActionEvent e) {
		       		int index = table.getSelectedRow();
	        		String dni = Gestion.getListaEmpleados().get(index).getDni();
	        		setVisible(false);
	        		VistaProyectos frame = new VistaProyectos(dni);
	        		frame.setVisible(true);
		       	}
		 });
		 
	}


	private void iniciarComponentes() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 644, 371);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPanee = new JScrollPane();
		scrollPanee.setBounds(10, 27, 608, 223);
       contentPane.add(scrollPanee);
       
       table = new JTable() {
    	   private static final long serialVersionUID = 1L;
		   //Sobreescribimos el método isCellEditable de la propia clase JTable para que las celdas no sean editables
		   public boolean isCellEditable(int rowIndex, int colIndex) {
		       return false; 
		   }
       };
       
	   scrollPanee.setViewportView(table);
		DefaultTableModel tableModel = new DefaultTableModel();
       String[]columnNames = {
       		"ID", "NOMBRE", "DNI", "EMAIL", "TELEFONO", "SALARIO", "LENGUAJES DOMINADOS"
       	};
       tableModel.setColumnIdentifiers(columnNames);
       Object[] fila = new Object[tableModel.getColumnCount()];
       
       for (int i = 0; i < Gestion.getListaEmpleados().size(); i++) {
    	if (Gestion.esProgramador(Gestion.getListaEmpleados().get(i))) {
    		fila[0] = Gestion.getListaEmpleados().get(i).getId();
           	fila[1] = Gestion.getListaEmpleados().get(i).getNombre();
           	fila[2] = Gestion.getListaEmpleados().get(i).getDni();
           	fila[3] = Gestion.getListaEmpleados().get(i).getEmail();
           	fila[4] = Gestion.getListaEmpleados().get(i).getTelefono();
           	Empleado aux = Gestion.getListaEmpleados().get(i);
           	Programador aux2 = (Programador)aux;
       		fila[5] = (int)aux2.getSalarioTotal() + "€";
       		fila[6] = aux2.getLenguajesDominados();
       		tableModel.addRow(fila);
    	}
       }

       table.setModel(tableModel);
       table.setBounds(517, 29, 206, 221);
       table.getColumnModel().getColumn(0).setPreferredWidth(10);
      
       
       JLabel lblClientesRegistrados = new JLabel("Programadores Contratados:");
       lblClientesRegistrados.setBounds(10, 11, 172, 14);
       contentPane.add(lblClientesRegistrados);
       
       btnVolver = new JButton("Volver");
      
       btnVolver.setBounds(529, 265, 89, 23);
       contentPane.add(btnVolver);
       
       btnModificarEmpleado = new JButton("Modificar");
      
       btnModificarEmpleado.setBounds(133, 265, 113, 23);
       contentPane.add(btnModificarEmpleado);
       
       btnEliminar = new JButton("Eliminar");
      
       btnEliminar.setBounds(256, 265, 102, 23);
       contentPane.add(btnEliminar);
       
       btnNuevo = new JButton("Nuevo");
      
       btnNuevo.setBounds(10, 265, 113, 23);
       contentPane.add(btnNuevo);
       
       btnProyectoEmpleado = new JButton("Ver proyectos del empleado");
      
       btnProyectoEmpleado.setBounds(10, 299, 236, 23);
       contentPane.add(btnProyectoEmpleado);
		
		
		
	}
}