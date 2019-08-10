package vistas;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import clases.Gestion;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class VentanaModificarProgramador extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textTelefono;
	private JTextField textEmail;
	private JTextField textDNI;
	private JTextField texNombre;
	private JButton btnActualizar;
	private JButton btnVolver;
	private JSpinner spinner;
	private int id;


	/**
	 * Create the frame.
	 * @param lenguajes 
	 * @param telefono 
	 * @param email 
	 * @param dNI 
	 * @param nombre 
	 */
	public VentanaModificarProgramador(String nombre, String dNI, String email, String telefono, int lenguajes, int id) {
		setTitle("Modificar empleado >Programador");
		iniciarComponentes();
		iniciarManejadores();
		
		setLocationRelativeTo(null);
		
		this.id = id;
		texNombre.setText(nombre);
		textDNI.setText(dNI);
		textTelefono.setText(telefono);
		textEmail.setText(email);
		spinner.setValue(lenguajes);
	}


	private void iniciarManejadores() {
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Gestion.modificarEmpleado(texNombre.getText(), textDNI.getText(), textEmail.getText(), textTelefono.getText(), getId(), (int)spinner.getValue());
					Gestion.guardarDatos();
					JOptionPane.showMessageDialog(null, "Cliente modificado correctamente");
					setVisible(false);
					VistaEmpleados frame6 = new VistaEmpleados();
					frame6.setVisible(true);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				VistaEmpleados frame6 = new VistaEmpleados();
				frame6.setVisible(true);
			}
		});
		
	}


	private void iniciarComponentes() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 368);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(10, 11, 46, 14);
		contentPane.add(lblNombre);
		
		JLabel lblDni = new JLabel("DNI");
		lblDni.setBounds(10, 70, 46, 14);
		contentPane.add(lblDni);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(10, 133, 46, 14);
		contentPane.add(lblEmail);
		
		JLabel lblTelfono = new JLabel("Tel\u00E9fono");
		lblTelfono.setBounds(10, 196, 70, 14);
		contentPane.add(lblTelfono);
		
		JLabel lblLenguajesDominados = new JLabel("Lenguajes dominados");
		lblLenguajesDominados.setBounds(10, 257, 121, 14);
		contentPane.add(lblLenguajesDominados);
		
		spinner = new JSpinner();
		spinner.setBounds(150, 254, 29, 20);
		contentPane.add(spinner);
		
		textTelefono = new JTextField();
		textTelefono.setBounds(10, 226, 315, 20);
		contentPane.add(textTelefono);
		textTelefono.setColumns(10);
		
		textEmail = new JTextField();
		textEmail.setColumns(10);
		textEmail.setBounds(10, 158, 315, 20);
		contentPane.add(textEmail);
		
		textDNI = new JTextField();
		textDNI.setColumns(10);
		textDNI.setBounds(10, 95, 315, 20);
		contentPane.add(textDNI);
		
		texNombre = new JTextField();
		texNombre.setColumns(10);
		texNombre.setBounds(10, 36, 315, 20);
		contentPane.add(texNombre);
		
		btnActualizar = new JButton("Actualizar");
		
		btnActualizar.setBounds(10, 296, 103, 23);
		contentPane.add(btnActualizar);
		
		btnVolver = new JButton("Volver");
		btnVolver.setBounds(321, 296, 103, 23);
		contentPane.add(btnVolver);
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}
	
}
