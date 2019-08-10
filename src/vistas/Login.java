package vistas;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import clases.Db;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class Login extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textUsuario;
	private JPasswordField textPassword;
	private JButton btnConectar;
	private JButton btnSalir;

	
	public Login() {
		setTitle("Panel de acceso");
		iniciarComponentes();
		iniciarManejadores();	
		
		setLocationRelativeTo(null);
		//Permite acceder al pulsar la tecla INTRO
		getRootPane().setDefaultButton(btnConectar);
		
		
	}

	private void iniciarManejadores() {
		btnConectar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Si campo usuario y contraseña tienen algún carácter, comprobamos la conexión
				if(textUsuario.getText().length()>0 && String.valueOf(textPassword.getPassword()).length()>0){
			        try{
			            Connection con = Db.connect();
			            Statement s =  con.createStatement();
			            //Consultamos usuario y contraseña
			            ResultSet r = s.executeQuery("select * from administradores where username=\""+textUsuario.getText()+"\" and password=\""+String.valueOf(textPassword.getPassword())+"\" ");
			            boolean found = false;
			            //Si se ha encontrado la consulta se muestran las opciones
			            while(r.next()){ found=true;}
			            if(found){
			                JOptionPane.showMessageDialog(rootPane, "Acceso concedido ...");
			                setVisible(false);
			                //Lanzamos menú principal de aplicación
			                VentanaPrincipal frame = new VentanaPrincipal();
			        		frame.setVisible(true);
			            }else{
			                JOptionPane.showMessageDialog(rootPane, "Acceso Denegado!!");
			            }
			        }catch(SQLException f){
			        System.out.println(f.getMessage());
			        }
			        }else{
			            JOptionPane.showMessageDialog(rootPane, "No debes dejar campos vacios !!");        
			        }
			}
		});
		
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Cerramos el programa
				System.exit(0);
			}
		});
		
	}

	private void iniciarComponentes() {
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 363, 243);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setBounds(25, 53, 69, 17);
		contentPane.add(lblUsuario);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a");
		lblContrasea.setBounds(25, 117, 69, 14);
		contentPane.add(lblContrasea);
		
		textUsuario = new JTextField();
		textUsuario.setBounds(104, 47, 215, 28);
		contentPane.add(textUsuario);
		textUsuario.setColumns(10);
		
		textPassword = new JPasswordField();
		textPassword.setBounds(104, 110, 215, 28);
		contentPane.add(textPassword);
		
		btnConectar = new JButton("Conectar");
		
		btnConectar.setBounds(22, 165, 145, 23);
		contentPane.add(btnConectar);
		
		btnSalir = new JButton("Salir");

		btnSalir.setBounds(180, 165, 145, 23);
		contentPane.add(btnSalir);
		
	}
}
