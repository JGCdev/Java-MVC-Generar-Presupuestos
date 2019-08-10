package vistas;

import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.github.lgooddatepicker.components.DatePicker;

import clases.Gestion;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaModifCliente extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField texNombre;
	private JTextField textDni;
	private JTextField textTelef;
	private JTextField textCorreo;
	private JButton btnAct;
	private int id;
	private JButton datePickerButton;
	private static DatePicker dp;
	private JButton btnVolver;

	/**
	 * Create the frame.
	 * @param id 
	 * @param fechaRegistro 
	 * @param telefono 
	 * @param email 
	 * @param dNI 
	 * @param nombre 
	 */
	public VentanaModifCliente(String nombre, String dNI, String email, String telefono, LocalDate fechaRegistro, int id) {
		setTitle("Modificar Cliente");
		iniciarComponentes();
		
		iniciarManejadores();
		
		texNombre.setText(nombre);
		textDni.setText(dNI);
		textTelef.setText(telefono);
		textCorreo.setText(email);
		dp.setDate(fechaRegistro);
		
		this.id = id;
		
		
	}

	private void iniciarManejadores() {

		btnAct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Gestion.modificarCliente(texNombre.getText(), textDni.getText(), textCorreo.getText(), textTelef.getText(), dp.getDate(), getId());
					Gestion.guardarDatos();
					JOptionPane.showMessageDialog(null, "Cliente modificado correctamente");
					setVisible(false);
					VistaClientes frame6 = new VistaClientes();
					frame6.setVisible(true);
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Hubo algún error");
				}
				
			}
		});
		
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				VistaClientes frame6 = new VistaClientes();
				frame6.setVisible(true);
			}
		});
		
		
	}

	private void iniciarComponentes() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 446);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(10, 43, 46, 14);
		contentPane.add(lblNombre);
		
		JLabel lblNewLabel = new JLabel("DNI");
		lblNewLabel.setBounds(10, 102, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Telef");
		lblNewLabel_1.setBounds(10, 171, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Correo");
		lblNewLabel_2.setBounds(10, 235, 46, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblFechas = new JLabel("Fechas");
		lblFechas.setBounds(10, 296, 46, 14);
		contentPane.add(lblFechas);
		
		texNombre = new JTextField();
		texNombre.setBounds(10, 68, 389, 20);
		contentPane.add(texNombre);
		texNombre.setColumns(10);
		
		textDni = new JTextField();
		textDni.setBounds(10, 127, 389, 20);
		contentPane.add(textDni);
		textDni.setColumns(10);
		
		btnAct = new JButton("Actualizar");
		
		btnAct.setBounds(157, 374, 120, 23);
		contentPane.add(btnAct);
		
		textTelef = new JTextField();
		textTelef.setBounds(10, 196, 389, 20);
		contentPane.add(textTelef);
		textTelef.setColumns(10);
		
		textCorreo = new JTextField();
		textCorreo.setBounds(10, 260, 389, 20);
		contentPane.add(textCorreo);
		textCorreo.setColumns(10);
		
		//DatePicker
		dp = new DatePicker();
		dp.setBounds(10, 320, 193, 25);
		contentPane.add(dp);
		dp.setDateToToday();
		datePickerButton = dp.getComponentToggleCalendarButton();
		datePickerButton.setText("...");
		
		btnVolver = new JButton("Volver");
		btnVolver.setBounds(310, 374, 89, 23);
		contentPane.add(btnVolver);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
