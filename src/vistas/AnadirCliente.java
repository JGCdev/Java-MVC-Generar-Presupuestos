package vistas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.github.lgooddatepicker.components.DatePicker;

import clases.Gestion;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import java.awt.event.ActionEvent;

public class AnadirCliente extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textNombre;
	private JTextField textDNI;
	private JTextField textEmail;
	private JTextField textTelefono;
	private JButton cancelButton;
	private JButton okButton;
	private JButton datePickerButton;
	private JLabel lblFechaInscripcin;
	private static DatePicker dp;
	
	public AnadirCliente() {
		setTitle("A\u00F1adir un nuevo cliente");
		iniciarComponentes();
		iniciarManejadores();
		
		setLocationRelativeTo(null);
		
	}


	private void iniciarManejadores() {
		
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				VistaClientes frame1 = new VistaClientes();
				frame1.setVisible(true);
			}
		});
		
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String nombre = textNombre.getText();
				String DNI = textDNI.getText();
				String email = textEmail.getText();
				String telefono = textTelefono.getText();
				//Obtenemos fecha del datepicker
				LocalDate date = dp.getDate();
				
				if (nombre.length()>0) {
					try {
						Gestion.anadirCliente(nombre, DNI, email, telefono, date);
						Gestion.guardarDatos();
						JOptionPane.showMessageDialog(null, "Se añadió el cliente");
						setVisible(false);
						VistaClientes frame = new VistaClientes();
						frame.setVisible(true);
						
						
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, "Hubo algún error");
					}
				}else {
					JOptionPane.showMessageDialog(null, "Al menos debes introducir un nombre");
				}
				
				
			}
		});
		
	}


	private void iniciarComponentes() {
		setBounds(100, 100, 248, 424);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(10, 11, 56, 14);
		contentPanel.add(lblNombre);
		
		JLabel lblDni = new JLabel("DNI:");
		lblDni.setBounds(10, 75, 56, 14);
		contentPanel.add(lblDni);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(10, 141, 56, 14);
		contentPanel.add(lblEmail);
		
		JLabel lblTelfono = new JLabel("Tel\u00E9fono:");
		lblTelfono.setBounds(10, 204, 56, 14);
		contentPanel.add(lblTelfono);
		
		textNombre = new JTextField();
		textNombre.setBounds(10, 36, 193, 20);
		contentPanel.add(textNombre);
		textNombre.setColumns(10);
		
		textDNI = new JTextField();
		textDNI.setBounds(10, 100, 193, 20);
		contentPanel.add(textDNI);
		textDNI.setColumns(10);
		
		textEmail = new JTextField();
		textEmail.setBounds(10, 166, 193, 20);
		contentPanel.add(textEmail);
		textEmail.setColumns(10);
		
		textTelefono = new JTextField();
		textTelefono.setBounds(10, 229, 193, 20);
		contentPanel.add(textTelefono);
		textTelefono.setColumns(10);
		
		lblFechaInscripcin = new JLabel("Fecha inscripci\u00F3n:");
		lblFechaInscripcin.setBounds(10, 273, 110, 14);
		contentPanel.add(lblFechaInscripcin);
		
		//DatePicker
		dp = new DatePicker();
		dp.setBounds(10, 300, 193, 25);
		contentPanel.add(dp);
		dp.setDateToToday();
		datePickerButton = dp.getComponentToggleCalendarButton();
		datePickerButton.setText("...");
        
	
		
		//contentPanel.add(textFecha);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("A\u00F1adir Cliente");
				
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				cancelButton = new JButton("Volver");
				
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
