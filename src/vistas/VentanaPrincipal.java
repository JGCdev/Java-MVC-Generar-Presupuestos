package vistas;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.SwingConstants;
import java.awt.Insets;
import java.awt.Cursor;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;



public class VentanaPrincipal extends JFrame {


	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnClientes;
	private JButton btnProyectos;
	private JButton btnEmpleados;
	private JButton btnPresupuestos;
	private JButton btnServicios;
	private JMenuBar menuBar;
	private JMenu mnArchivo;
	private JMenuItem mntmGuardarComo;
	private JMenuItem mntmCargar;
	

	
	public VentanaPrincipal() {
		setTitle("Programa de gesti\u00F3n Jes\u00FAs Gim\u00E9nez");
		
		iniciarComponentes();
		iniciarManejadores();
		
		
		setLocationRelativeTo(null);
	}
	
	private void iniciarManejadores() {
		
		btnClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				VistaClientes frame1 = new VistaClientes();
				frame1.setVisible(true);
			}
		});
		
		btnProyectos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				VistaProyectos frame1 = new VistaProyectos();
				frame1.setVisible(true);
			}
		});
		
		btnEmpleados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				VistaEmpleados frame6 = new VistaEmpleados();
				frame6.setVisible(true);
			}
		});
		
		btnPresupuestos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				VistaPresupuestos frame7 = new VistaPresupuestos();
				frame7.setVisible(true);
			}
		});
		
		btnServicios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				VistaServicios frame8 = new VistaServicios();
				frame8.setVisible(true);
			}
		});
		
		mntmCargar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CargarArchivo frame = new CargarArchivo();
                frame.setVisible(true);
			}
		});
		
		mntmGuardarComo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ElegirRuta frame = new ElegirRuta();
                frame.setVisible(true);
			}
		});
		
		
	}

	private void iniciarComponentes() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 606, 465);
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		mnArchivo = new JMenu("Archivo");
		menuBar.add(mnArchivo);
		
		mntmGuardarComo = new JMenuItem("Guardar como...");
		
		mntmGuardarComo.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/imagenes/iconos/guardar.png")));
		mnArchivo.add(mntmGuardarComo);
		
		mntmCargar = new JMenuItem("Cargar");
		
		mntmCargar.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/imagenes/iconos/outbox.png")));
		mnArchivo.add(mntmCargar);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnClientes = new JButton("Clientes");
		btnClientes.setContentAreaFilled(false);
		btnClientes.setFocusable(false);
		btnClientes.setFocusTraversalPolicyProvider(true);
		btnClientes.setFocusCycleRoot(true);
		btnClientes.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		btnClientes.setMargin(new Insets(0, 14, 2, 14));
		btnClientes.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnClientes.setHorizontalTextPosition(SwingConstants.CENTER);
		btnClientes.setAutoscrolls(true);
		btnClientes.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/imagenes/usuarios.png")));
		
		btnClientes.setBounds(20, 21, 154, 162);
		contentPane.add(btnClientes);
		
		btnProyectos = new JButton("Proyectos");
		btnProyectos.setContentAreaFilled(false);
		btnProyectos.setAutoscrolls(true);
		btnProyectos.setFocusable(false);
		btnProyectos.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnProyectos.setHorizontalTextPosition(SwingConstants.CENTER);
		btnProyectos.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/imagenes/servicios.png")));
		
		btnProyectos.setBounds(396, 20, 148, 162);
		contentPane.add(btnProyectos);
		
		btnEmpleados = new JButton("Empleados");
		btnEmpleados.setContentAreaFilled(false);
		btnEmpleados.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnEmpleados.setHorizontalTextPosition(SwingConstants.CENTER);
		btnEmpleados.setFocusable(false);
		btnEmpleados.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/imagenes/web.png")));
		
		btnEmpleados.setBounds(209, 20, 148, 162);
		contentPane.add(btnEmpleados);
		
		btnPresupuestos = new JButton("Presupuestos");
		btnPresupuestos.setContentAreaFilled(false);
		btnPresupuestos.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnPresupuestos.setHorizontalTextPosition(SwingConstants.CENTER);
		btnPresupuestos.setFocusable(false);
		btnPresupuestos.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/imagenes/presup.png")));
		
		btnPresupuestos.setBounds(209, 214, 148, 162);
		contentPane.add(btnPresupuestos);
		
		btnServicios = new JButton("Servicios");
		btnServicios.setContentAreaFilled(false);
		btnServicios.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnServicios.setHorizontalTextPosition(SwingConstants.CENTER);
		btnServicios.setFocusable(false);
		btnServicios.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/imagenes/lapiz.png")));
		
		btnServicios.setBounds(26, 214, 148, 162);
		contentPane.add(btnServicios);
		
		
	}
}
