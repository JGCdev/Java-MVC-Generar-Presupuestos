package vistas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import clases.Cliente;
import clases.Empleado;
import clases.Gestion;
import clases.Proyecto;

public class VistaProyectos extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnVolver;
	private JButton btnNuevo;
	private JButton btnModificarProyecto;
	private JButton btnEliminar;
	private JTable table;

	//Constructor sin parámetros, lanzado desde la ventana de navegación principal
	public VistaProyectos() {
		setTitle("Proyectos");
		iniciarComponentes();
		iniciarTablaSencilla();
		iniciarManejadores();
		
		setLocationRelativeTo(null);
	}
	
	//Constructor que lista proyectos de un cliente
	public VistaProyectos(int id) {
		iniciarComponentes();
		iniciarTablaConIdCliente(id);
		iniciarManejadores();
		setLocationRelativeTo(null);
	}
	
	//Constructor que lista proyectos de un empleado
	public VistaProyectos(String dni) {
		iniciarComponentes();
		iniciarTablaConDniCliente(dni);
		iniciarManejadores();
		
		setLocationRelativeTo(null);
	}
	
	//Tabla inicial, lista de proyectos
	private void iniciarTablaSencilla() {
		JScrollPane scrollPanee = new JScrollPane();
		scrollPanee.setBounds(10, 27, 684, 223);
        contentPane.add(scrollPanee);
       
      
        table = new JTable() {
        	private static final long serialVersionUID = 1L;
			//Sobreescribimos el método isCellEditable de la propia clase JTable para que las celdas no sean editables
			public boolean isCellEditable(int rowIndex, int colIndex) {
		        return false; 
		    }
        };
	    scrollPanee.setViewportView(table);
	    //Creamos modelo para la tabla
		DefaultTableModel tableModel = new DefaultTableModel();
        String[]columnNames = {
      		"ID", "NOMBRE", "DESCRIPCIÓN", "CLIENTE", "FECHA COMIENZO", "EMPLEADOS"
      	};
        tableModel.setColumnIdentifiers(columnNames);
        //obtenemos número de columnas para rellenar un array de casillas por fila
        Object[] fila = new Object[tableModel.getColumnCount()];
      
        for (int i = 0; i < Gestion.getListaProyectos().size(); i++) {
      	  fila[0] = Gestion.getListaProyectos().get(i).getId();
      	  fila[1] = Gestion.getListaProyectos().get(i).getNombre();
      	  fila[2] = Gestion.getListaProyectos().get(i).getDescripcion();
      	  fila[3] = Gestion.getListaProyectos().get(i).getCliente().getNombre();
      	  fila[4] = Gestion.getListaProyectos().get(i).getFechaComienzo();
      	  fila[5] = Gestion.getListaProyectos().get(i).getEmpleados();
      	  //Añadimos la fila en cada iteración hasta que no haya proyectos
          tableModel.addRow(fila);
        }
        table.setModel(tableModel);
        table.setBounds(517, 29, 206, 221);
        table.getColumnModel().getColumn(0).setPreferredWidth(10);
        table.getColumnModel().getColumn(2).setPreferredWidth(200);
	}
	
	//Tabla de proyectos según cliente (ID)
	private void iniciarTablaConIdCliente(int id) {
		JScrollPane scrollPanee = new JScrollPane();
		scrollPanee.setBounds(10, 27, 684, 223);
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
      		"ID", "NOMBRE", "DESCRIPCIÓN", "CLIENTE", "FECHA COMIENZO", "EMPLEADOS"
      	};
        tableModel.setColumnIdentifiers(columnNames);
        Object[] fila = new Object[tableModel.getColumnCount()];
        
        ArrayList<Proyecto> listaFinal = new ArrayList<Proyecto>();
        Proyecto aux;
        for (int i=0; i<Gestion.getListaProyectos().size();i++) {
     	   aux = Gestion.getListaProyectos().get(i);
     	   if (aux.getCliente().getId()==id) {
     		   listaFinal.add(aux);
     	   }
        }

        for (int i = 0; i < listaFinal.size(); i++) {
            fila[0] = Gestion.getListaProyectos().get(i).getId();
            fila[1] = Gestion.getListaProyectos().get(i).getNombre();
            fila[2] = Gestion.getListaProyectos().get(i).getDescripcion();
            fila[3] = Gestion.getListaProyectos().get(i).getCliente().getNombre();
            fila[4] = Gestion.getListaProyectos().get(i).getFechaComienzo();
            fila[5] = Gestion.getListaProyectos().get(i).getEmpleados();
            tableModel.addRow(fila);
        }
        
        table.setModel(tableModel);
        table.setBounds(517, 29, 206, 221);
        table.getColumnModel().getColumn(0).setPreferredWidth(10);
        table.getColumnModel().getColumn(2).setPreferredWidth(200);
		
	}
	
	//Tabla de proyectos según empleados
	private void iniciarTablaConDniCliente(String dni) {
		JScrollPane scrollPanee = new JScrollPane();
		scrollPanee.setBounds(10, 27, 684, 223);
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
      		"ID", "NOMBRE", "DESCRIPCIÓN", "CLIENTE", "FECHA COMIENZO", "EMPLEADOS"
      	};
        tableModel.setColumnIdentifiers(columnNames);
        Object[] fila = new Object[tableModel.getColumnCount()];
        //Creamos arraylist temporal para alojar proyectos con un mismo dni
        ArrayList<Proyecto> listaFinal = new ArrayList<Proyecto>();
        ArrayList<Empleado> aux = new ArrayList<Empleado>();
        
        Empleado emp;
        Proyecto proyecto;
        
        for (int i=0; i<Gestion.getListaProyectos().size();i++) {
        	proyecto = Gestion.getListaProyectos().get(i);
        	aux = proyecto.getEmpleados();
        	for (int j=0; j<aux.size(); j++) {
        		emp = aux.get(j);
        		if (emp.getDni().equals(dni)) {
        			listaFinal.add(proyecto);
        		}
        	}	   
        }

        for (int i = 0; i < listaFinal.size(); i++) {
            fila[0] = listaFinal.get(i).getId();
            fila[1] = listaFinal.get(i).getNombre();
            fila[2] = listaFinal.get(i).getDescripcion();
            fila[3] = listaFinal.get(i).getCliente();
            fila[4] = listaFinal.get(i).getFechaComienzo();
            fila[5] = listaFinal.get(i).getEmpleados();
            tableModel.addRow(fila);
        }
        
        table.setModel(tableModel);
        table.setBounds(517, 29, 206, 221);
        table.getColumnModel().getColumn(0).setPreferredWidth(10);
        table.getColumnModel().getColumn(2).setPreferredWidth(200);
		
	}

	private void iniciarManejadores() {

		 btnVolver.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		setVisible(false);
					VentanaPrincipal frame1 = new VentanaPrincipal();
					frame1.setVisible(true);
	        	}
	     });
		 
		 btnModificarProyecto.addActionListener(new ActionListener() {
		       	public void actionPerformed(ActionEvent e) {
		       		int index = table.getSelectedRow();
	        		 //Si se ha seleccionado un index, lanzamos registro de parámetros
	        		 if (index != -1) {
	        			//Sacamos el ID de cliente 
		        		 int id = Gestion.getListaProyectos().get(index).getId();
		        		 
		        		 String nombre = Gestion.getListaProyectos().get(index).getNombre();
		        		 String descripcion = Gestion.getListaProyectos().get(index).getDescripcion();
		        		 Cliente cliente = Gestion.getListaProyectos().get(index).getCliente();
		        		 ArrayList<Empleado> listaEmpleados = Gestion.getListaProyectos().get(index).getEmpleados();
		        		 LocalDate fecha = Gestion.getListaProyectos().get(index).getFechaComienzo();

		        		 setVisible(false);
		        		 //Pasamos los parámetros al constructor de la ventana para poder setearlos
			        	 VentanaModificarProyecto frame = new VentanaModificarProyecto(nombre, descripcion, fecha, listaEmpleados, cliente, id);
						 frame.setVisible(true);
		        	}		 
	        	}  		
		 });
		 
		 btnEliminar.addActionListener(new ActionListener() {
		       	public void actionPerformed(ActionEvent e) {
		        		//Marcamos el index del jlist
		        		 int index = table.getSelectedRow();
		        		 if (index != -1) {
		        		 //Sacamos el ID de cliente 
			        		 int id = Gestion.getListaProyectos().get(index).getId();
			        		 try {
			        			 Gestion.eliminarProyecto(id);
			        			 setVisible(false);
			        			 //Volvemos a lanzar para actualizar la aplicación
			        			 VistaProyectos frame = new VistaProyectos();
			 					 frame.setVisible(true);
			 					 JOptionPane.showMessageDialog(null, "Proyecto eliminado");
			 					 Gestion.guardarDatos();
			 					 
							} catch (Exception e2) {
								JOptionPane.showMessageDialog(null, "No se pudo eliminar el proyecto");
							}
		        		}   	
		       	}
		 });
		 btnNuevo.addActionListener(new ActionListener() {
		       	public void actionPerformed(ActionEvent e) {
		       		setVisible(false);
		       		AnadirProyecto frame = new AnadirProyecto();
					frame.setVisible(true);
		       	}
		 });
		 
	}

	private void iniciarComponentes() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 720, 342);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		

       JLabel lblClientesRegistrados = new JLabel("Proyectos Registrados:");
       lblClientesRegistrados.setBounds(10, 11, 140, 14);
       contentPane.add(lblClientesRegistrados);
       
       btnVolver = new JButton("Volver");
      
       btnVolver.setBounds(405, 265, 89, 23);
       contentPane.add(btnVolver);
       
       btnModificarProyecto = new JButton("Modificar");
      
       btnModificarProyecto.setBounds(133, 265, 113, 23);
       contentPane.add(btnModificarProyecto);
       
       btnEliminar = new JButton("Eliminar");
      
       btnEliminar.setBounds(256, 265, 102, 23);
       contentPane.add(btnEliminar);
       
       btnNuevo = new JButton("Nuevo");
      
       btnNuevo.setBounds(10, 265, 113, 23);
       contentPane.add(btnNuevo);

	}
	
	

}