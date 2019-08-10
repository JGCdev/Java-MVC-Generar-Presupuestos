package vistas;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AnadirEmpleadoTipo extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnProgramador;
	private JButton btnDiseadorGrfico;
	private JButton btnVolver;


	/**
	 * Create the frame.
	 */
	public AnadirEmpleadoTipo() {
		setTitle("A\u00F1adir empleado > Tipo");
		
		iniciarComponentes();
		iniciarManejadores();
		
		setLocationRelativeTo(null);
		
	}

	private void iniciarManejadores() {
		btnProgramador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				AnadirProgramador frame1 = new AnadirProgramador();
				frame1.setVisible(true);
			}
		});
		
		btnDiseadorGrfico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				AnadirGrafico frame2 = new AnadirGrafico();
				frame2.setVisible(true);
			}
		});
		
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				VistaEmpleados frame3 = new VistaEmpleados();
				frame3.setVisible(true);
			}
		});
	}

	private void iniciarComponentes() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 240);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblSeleccionaElTipo = new JLabel("Selecciona el tipo de empleado");
		lblSeleccionaElTipo.setBounds(144, 54, 229, 14);
		contentPane.add(lblSeleccionaElTipo);
		
		btnProgramador = new JButton("Programador");
		
		btnProgramador.setBounds(41, 109, 162, 23);
		contentPane.add(btnProgramador);
		
		btnDiseadorGrfico = new JButton("Dise\u00F1ador Gr\u00E1fico");
		
		btnDiseadorGrfico.setBounds(242, 109, 152, 23);
		contentPane.add(btnDiseadorGrfico);
		
		btnVolver = new JButton("Volver");
		
		btnVolver.setBounds(172, 168, 89, 23);
		contentPane.add(btnVolver);
	}

}
