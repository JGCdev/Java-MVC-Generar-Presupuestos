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
import java.awt.event.ActionEvent;

public class AgregarServicio extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtDescripcion;
	private JTextField txtPrecio;
	private JButton okButton;

	/**
	 * Create the dialog.
	 */
	public AgregarServicio() {
		setTitle("Agregar nuevo servicio");
		
		iniciarComponentes();
		iniciarManejadores();
		
		
		
	}

	private void iniciarManejadores() {
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String descripcion = txtDescripcion.getText();
				String precio = txtPrecio.getText();
				Double pUnidad = Double.parseDouble(precio);
				
				if (descripcion.length()>0 && precio.length()>0) {
					try {
						Gestion.anadirServicioLista(descripcion, pUnidad);
						Gestion.guardarDatos();
						JOptionPane.showMessageDialog(null, "Se añadió el servicio");
						setVisible(false);
						VistaServicios frame = new VistaServicios();
						frame.setVisible(true);
						
						
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, "Hubo algún error");
					}
				}else {
					JOptionPane.showMessageDialog(null, "Debes rellenar los campos");
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
			JLabel lblDescripcin = new JLabel("Descripci\u00F3n");
			lblDescripcin.setBounds(10, 26, 96, 14);
			contentPanel.add(lblDescripcin);
		}
		{
			txtDescripcion = new JTextField();
			txtDescripcion.setBounds(10, 49, 264, 20);
			contentPanel.add(txtDescripcion);
			txtDescripcion.setColumns(10);
		}
		{
			JLabel lblPrecio = new JLabel("Precio");
			lblPrecio.setBounds(10, 93, 46, 14);
			contentPanel.add(lblPrecio);
		}
		{
			txtPrecio = new JTextField();
			txtPrecio.setBounds(10, 118, 86, 20);
			contentPanel.add(txtPrecio);
			txtPrecio.setColumns(10);
		}
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
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
	}

}
