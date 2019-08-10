package vistas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import clases.Gestion;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class AnadirProgramador extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textNombre;
	private JTextField textEmail;
	private JTextField textTelefono;
	private JTextField textDNI;
	private JButton okButton;
	private JButton cancelButton;
	private JSpinner spinner;

	
	public AnadirProgramador() {
		setTitle("A\u00F1adir empleado > Programador");
		
		iniciarComponentes();
		iniciarManejadores();
		
		setLocationRelativeTo(null);
		
	}


	private void iniciarManejadores() {
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				AnadirEmpleadoTipo frame6 = new AnadirEmpleadoTipo();
				frame6.setVisible(true);
				
				setLocationRelativeTo(null);
			}
		});
		
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String nombre = textNombre.getText();
				String dni = textDNI.getText();
				int lenguajes = (int) spinner.getValue();
				
				String telefono = textTelefono.getText();
				String email = textEmail.getText();
				
				try {
					Gestion.anadirEmpleado(nombre, dni, email, telefono, lenguajes);
					JOptionPane.showMessageDialog(null, "Empleado añadido");
					Gestion.guardarDatos();
					
					setVisible(false);
					VistaEmpleados frame6 = new VistaEmpleados();
					frame6.setVisible(true);
					
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			
				
			}
		});
		
	}


	private void iniciarComponentes() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNombre = new JLabel("Nombre");
			lblNombre.setBounds(10, 11, 46, 14);
			contentPanel.add(lblNombre);
		}
		{
			JLabel lblEmail = new JLabel("Email");
			lblEmail.setBounds(10, 49, 46, 14);
			contentPanel.add(lblEmail);
		}
		{
			JLabel lblTelefono = new JLabel("Telefono");
			lblTelefono.setBounds(10, 94, 65, 14);
			contentPanel.add(lblTelefono);
		}
		{
			JLabel lblDni = new JLabel("Dni");
			lblDni.setBounds(10, 133, 65, 14);
			contentPanel.add(lblDni);
		}
		{
			JLabel lblEspecialidad = new JLabel("Lenguajes dominados");
			lblEspecialidad.setBounds(10, 175, 135, 14);
			contentPanel.add(lblEspecialidad);
		}
		{
			textNombre = new JTextField();
			textNombre.setBounds(85, 8, 192, 27);
			contentPanel.add(textNombre);
			textNombre.setColumns(10);
		}
		{
			textEmail = new JTextField();
			textEmail.setColumns(10);
			textEmail.setBounds(85, 46, 192, 27);
			contentPanel.add(textEmail);
		}
		{
			textTelefono = new JTextField();
			textTelefono.setColumns(10);
			textTelefono.setBounds(85, 91, 192, 27);
			contentPanel.add(textTelefono);
		}
		{
			textDNI = new JTextField();
			textDNI.setColumns(10);
			textDNI.setBounds(85, 130, 192, 27);
			contentPanel.add(textDNI);
		}
		
		spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(new Integer(1), null, null, new Integer(1)));
		spinner.setBounds(134, 172, 29, 20);
		contentPanel.add(spinner);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("OK");
				
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
