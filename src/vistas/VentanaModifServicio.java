package vistas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import clases.Gestion;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class VentanaModifServicio extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JButton okButton;
	private JButton cancelButton;
	private JTextField txtDesc;
	private JTextField txtPrecio;
	private int ref;

	/**
	 * Create the dialog.
	 */
	public VentanaModifServicio(int ref, String desc, double pUnidad ) {
		setTitle("Modificar Servicio");
		iniciarComponentes();
		iniciarManejadores();
		this.ref = ref;
		txtDesc.setText(desc);
		txtPrecio.setText(Double.toString(pUnidad));
		setLocationRelativeTo(null);
		
	}

	private void iniciarManejadores() {
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String desc = txtDesc.getText();
				Double precio = Double.valueOf(txtPrecio.getText());
				
				try {
					Gestion.modificarServicio(getRef(), desc, precio);
					JOptionPane.showMessageDialog(null, "Servicio modificado correctamente");
					setVisible(false);
					VistaServicios vs = new VistaServicios();
					vs.setVisible(true);
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null, "Hubo algún error");
					e1.printStackTrace();
				}
				
			}
		});
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				VistaServicios vs = new VistaServicios();
				vs.setVisible(true);
			}
		});
		
	}

	private void iniciarComponentes() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		txtDesc = new JTextField();
		txtDesc.setBounds(10, 50, 414, 20);
		contentPanel.add(txtDesc);
		txtDesc.setColumns(10);
		
		JLabel lblDescripcin = new JLabel("Descripci\u00F3n");
		lblDescripcin.setBounds(10, 25, 97, 14);
		contentPanel.add(lblDescripcin);
		
		JLabel lblPrecioUnidad = new JLabel("Precio unidad");
		lblPrecioUnidad.setBounds(10, 107, 97, 14);
		contentPanel.add(lblPrecioUnidad);
		
		txtPrecio = new JTextField();
		txtPrecio.setBounds(10, 132, 86, 20);
		contentPanel.add(txtPrecio);
		txtPrecio.setColumns(10);
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
				cancelButton = new JButton("Cancelar");
				
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
	}

	public int getRef() {
		return ref;
	}

	public void setRef(int ref) {
		this.ref = ref;
	}
	
}
