package vistas;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import com.github.lgooddatepicker.components.DatePicker;

import clases.Empleado;
import clases.Gestion;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class AnadirProyecto extends JFrame {


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
	private JButton btnAadir;
	private JButton btnVolver;
	private JTextArea textDescripcion;

	public AnadirProyecto() {
		setTitle("A\u00F1adir proyecto");
		iniciarComponentes();
		iniciarManejadores();
		
		setLocationRelativeTo(null);
	}

	private void iniciarManejadores() {
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				VistaProyectos frame1 = new VistaProyectos();
				frame1.setVisible(true);
			}
		});
		
		btnAadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Sacamos el número de indice del ArrayList para buscar después la ID
				int cliente = jListClientes.getSelectedIndex();
				
				int id = Gestion.getListaClientes().get(cliente).getId();
				
				//Sacamos array de índices 
				int[] idEmpleados = jListEmpleados.getSelectedIndices();
				ArrayList<Empleado> empleados = Gestion.obtenerEmpleadosConArray(idEmpleados);
				
				Gestion.anadirProyecto(textNombre.getText(), Gestion.obtenerClienteConID(id), empleados, textDescripcion.getText(), dp.getDate());
				try {
					
					Gestion.guardarDatos();
					JOptionPane.showMessageDialog(null, "Proyecto añadido correctamente");
					setVisible(false);
					VistaProyectos frame1 = new VistaProyectos();
					frame1.setVisible(true);
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		
		
	}

	private void iniciarComponentes() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 503, 354);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPanee = new JScrollPane();
		scrollPanee.setBounds(300, 50, 170, 100);
        contentPane.add(scrollPanee);
        jListClientes = new JList<>(Gestion.getListaClientes().toArray());
        jListClientes.setName("jListSource");
        scrollPanee.setViewportView(jListClientes);
        
        JScrollPane scrollPanee2 = new JScrollPane();
		scrollPanee2.setBounds(300, 200, 170, 100);
        contentPane.add(scrollPanee2);
        jListEmpleados = new JList<>(Gestion.getListaEmpleados().toArray());
        jListEmpleados.setName("jListSource");
        scrollPanee2.setViewportView(jListEmpleados);
        
        lblSeleccionaElCliente = new JLabel("Selecciona el cliente");
        lblSeleccionaElCliente.setBounds(300, 25, 170, 14);
        contentPane.add(lblSeleccionaElCliente);
        
        lblAsignaEmpleados = new JLabel("Asigna empleados");
        lblAsignaEmpleados.setBounds(300, 175, 159, 14);
        contentPane.add(lblAsignaEmpleados);
        
        lblNombreDelProyecto = new JLabel("Nombre del proyecto");
        lblNombreDelProyecto.setBounds(10, 25, 204, 14);
        contentPane.add(lblNombreDelProyecto);
        
        textNombre = new JTextField();
        textNombre.setBounds(10, 49, 251, 20);
        contentPane.add(textNombre);
        textNombre.setColumns(10);
        
        lblDescripcinGeneral = new JLabel("Descripci\u00F3n general");
        lblDescripcinGeneral.setBounds(10, 97, 193, 14);
        contentPane.add(lblDescripcinGeneral);
        
        textDescripcion = new JTextArea();
        textDescripcion.setLineWrap(true);
        textDescripcion.setBorder(new LineBorder(Color.GRAY));
        textDescripcion.setBounds(10, 122, 251, 64);
        contentPane.add(textDescripcion);
        
        JLabel lblFechaDeComienzo = new JLabel("Fecha de comienzo");
        lblFechaDeComienzo.setBounds(10, 217, 170, 14);
        contentPane.add(lblFechaDeComienzo);
		
		//DatePicker
		dp = new DatePicker();
		dp.setBounds(10, 240, 193, 25);
		contentPane.add(dp);
		dp.setDateToToday();
		datePickerButton = dp.getComponentToggleCalendarButton();
		datePickerButton.setText("...");
		
		btnAadir = new JButton("A\u00F1adir");
		
		btnAadir.setBounds(10, 282, 89, 23);
		contentPane.add(btnAadir);
		
		btnVolver = new JButton("Volver");
		
		btnVolver.setBounds(114, 282, 89, 23);
		contentPane.add(btnVolver);
	}
}
