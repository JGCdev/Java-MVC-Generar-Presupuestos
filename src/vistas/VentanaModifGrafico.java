package vistas;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import clases.Gestion;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class VentanaModifGrafico extends JFrame {


	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private int id;
	private JTextField textNombre;
	private JTextField textTelef;
	private JTextField texEmail;
	private JTextField textDni;
	private JTextField textUbic;
	private JButton btnVolver;
	private JButton btnActualizar;


	/**
	 * Create the frame.
	 * @param ubicacion 
	 * @param lenguajes 
	 * @param telefono 
	 * @param email 
	 * @param dNI 
	 * @param nombre 
	 */
	public VentanaModifGrafico(String nombre, String dNI, String email, String telefono, String ubicacion, int id) {
		setTitle("Modificar Empleado > Dise\u00F1ador");
		iniciarComponentes();
		
		iniciarManejadores();
		
		setLocationRelativeTo(null);
		
		this.id = id;
		textNombre.setText(nombre);
		textDni.setText(dNI);
		textTelef.setText(telefono);
		texEmail.setText(email);
		textUbic.setText(ubicacion);
		
	}

	private void iniciarManejadores() {

		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Gestion.modificarEmpleado(textNombre.getText(), textDni.getText(), texEmail.getText(), textTelef.getText(), getId(), textUbic.getText());
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
		setBounds(100, 100, 402, 444);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(10, 43, 46, 14);
		contentPane.add(lblNombre);
		
		JLabel lblTelefono = new JLabel("Telefono");
		lblTelefono.setBounds(10, 117, 89, 14);
		contentPane.add(lblTelefono);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(10, 186, 46, 14);
		contentPane.add(lblEmail);
		
		JLabel lblDni = new JLabel("DNI");
		lblDni.setBounds(10, 248, 46, 14);
		contentPane.add(lblDni);
		
		JLabel lblUbicacin = new JLabel("Ubicaci\u00F3n");
		lblUbicacin.setBounds(10, 314, 89, 14);
		contentPane.add(lblUbicacin);
		
		textNombre = new JTextField();
		textNombre.setBounds(10, 68, 356, 20);
		contentPane.add(textNombre);
		textNombre.setColumns(10);
		
		textTelef = new JTextField();
		textTelef.setColumns(10);
		textTelef.setBounds(10, 142, 356, 20);
		contentPane.add(textTelef);
		
		texEmail = new JTextField();
		texEmail.setColumns(10);
		texEmail.setBounds(10, 211, 356, 20);
		contentPane.add(texEmail);
		
		textDni = new JTextField();
		textDni.setColumns(10);
		textDni.setBounds(10, 273, 356, 20);
		contentPane.add(textDni);
		
		textUbic = new JTextField();
		textUbic.setColumns(10);
		textUbic.setBounds(10, 339, 356, 20);
		contentPane.add(textUbic);
		
		btnVolver = new JButton("Volver");
		
		btnVolver.setBounds(277, 370, 89, 23);
		contentPane.add(btnVolver);
		
		btnActualizar = new JButton("Actualizar");
		
		btnActualizar.setBounds(10, 370, 89, 23);
		contentPane.add(btnActualizar);
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
