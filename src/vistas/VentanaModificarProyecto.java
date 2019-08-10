package vistas;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.github.lgooddatepicker.components.DatePicker;

import clases.Cliente;
import clases.Empleado;
import clases.Gestion;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.ScrollPaneConstants;

public class VentanaModificarProyecto extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JList<Object> jListClientes;
	private JList<Object> jListEmpleados;
	private JLabel lblSeleccionaElCliente;
	private JLabel lblAsignaEmpleados;
	private JLabel lblNombreDelProyecto;
	private JTextField textNombre;
	private JLabel lblDescripcinGeneral;
	private JButton datePickerButton;
	private static DatePicker dp;
	private JButton btnActualizar;
	private JButton btnVolver;
	private int id;
	private JScrollPane scrollPane;
	private JTextArea textArea;

	public VentanaModificarProyecto(String nombre, String descripcion, LocalDate fecha, ArrayList<Empleado> empleados, Cliente cliente, int id) {
		setTitle("Modificar Proyecto");
		iniciarComponentes();
		iniciarManejadores();
		
		this.id = id;
		textNombre.setText(nombre);
		dp.setDate(fecha);
		textArea.setText(descripcion);
	}



	private void iniciarManejadores() {
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				VistaProyectos frame1 = new VistaProyectos();
				frame1.setVisible(true);
			}
		});
		
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (jListEmpleados.getSelectedIndices().length>0 && jListClientes.getSelectedIndex() != -1) {
					int cliente = jListClientes.getSelectedIndex();
					int id = Gestion.getListaClientes().get(cliente).getId();
					
					int[] idEmpleados = jListEmpleados.getSelectedIndices();
					ArrayList<Empleado> empleados = Gestion.obtenerEmpleadosConArray(idEmpleados);
					
					Gestion.modificarProyecto(textNombre.getText(), Gestion.obtenerClienteConID(id), empleados, textArea.getText(), dp.getDate(), getId());
					try {
						Gestion.guardarDatos();
						JOptionPane.showMessageDialog(null, "Proyecto modificado correctamente");
						setVisible(false);
						VistaProyectos frame6 = new VistaProyectos();
						frame6.setVisible(true);
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(null, "Hubo algún error");
						e1.printStackTrace();
					}
					
				}else {
					JOptionPane.showMessageDialog(null, "Selecciona al menos un cliente y un empleado para asignar al proyecto");
				}
		
			}
		});
	}

	private void iniciarComponentes() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 624, 437);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPanee = new JScrollPane();
		scrollPanee.setBounds(397, 50, 170, 81);
        contentPane.add(scrollPanee);
        jListClientes = new JList<>(Gestion.getListaClientes().toArray());
        jListClientes.setName("jListSource");
        scrollPanee.setViewportView(jListClientes);
        
        JScrollPane scrollPanee2 = new JScrollPane();
		scrollPanee2.setBounds(397, 174, 170, 160);
        contentPane.add(scrollPanee2);
        jListEmpleados = new JList<>(Gestion.getListaEmpleados().toArray());
        jListEmpleados.setName("jListSource");
        scrollPanee2.setViewportView(jListEmpleados);
        
        lblSeleccionaElCliente = new JLabel("Selecciona el cliente");
        lblSeleccionaElCliente.setBounds(397, 25, 140, 14);
        contentPane.add(lblSeleccionaElCliente);
        
        lblAsignaEmpleados = new JLabel("Asigna empleados");
        lblAsignaEmpleados.setBounds(397, 155, 140, 14);
        contentPane.add(lblAsignaEmpleados);
        
        lblNombreDelProyecto = new JLabel("Nombre del proyecto");
        lblNombreDelProyecto.setBounds(10, 25, 155, 14);
        contentPane.add(lblNombreDelProyecto);
        
        textNombre = new JTextField();
        textNombre.setBounds(10, 49, 251, 20);
        contentPane.add(textNombre);
        textNombre.setColumns(10);
        
        lblDescripcinGeneral = new JLabel("Descripci\u00F3n general");
        lblDescripcinGeneral.setBounds(10, 155, 155, 14);
        contentPane.add(lblDescripcinGeneral);
        
        JLabel lblFechaDeComienzo = new JLabel("Fecha de comienzo");
        lblFechaDeComienzo.setBounds(10, 90, 140, 14);
        contentPane.add(lblFechaDeComienzo);
		
		//DatePicker
		dp = new DatePicker();
		dp.setBounds(10, 110, 193, 25);
		contentPane.add(dp);
		dp.setDateToToday();
		datePickerButton = dp.getComponentToggleCalendarButton();
		datePickerButton.setText("...");
		
		btnActualizar = new JButton("Actualizar");
		btnActualizar.setBounds(10, 365, 89, 23);
		contentPane.add(btnActualizar);
		
		btnVolver = new JButton("Volver");
		btnVolver.setBounds(109, 365, 89, 23);
		contentPane.add(btnVolver);
		
		scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(10, 176, 329, 158);
		contentPane.add(scrollPane);
		
		textArea = new JTextArea();
		//Deshabilitamos el desbordamiento lateral derecho (Habilitando wrap)
		textArea.setLineWrap(true);
		scrollPane.setViewportView(textArea);
		
	}

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}
}
