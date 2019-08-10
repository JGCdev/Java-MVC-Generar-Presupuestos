package vistas;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import clases.Presupuesto;

import javax.swing.JTextPane;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Properties;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class EnviarEmail extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnEnviar;
	private JTextPane textPane;
	private JTextField textDestinatario;
	private JLabel lblAsunto;
	private JTextField textAsunto;


	/**
	 * @wbp.parser.constructor
	 */
	public EnviarEmail(Presupuesto presupuesto) {
		setTitle("Enviar mensaje");
		iniciarComponentes();
		iniciarManejadores(presupuesto);	
		textDestinatario.setText(presupuesto.getEmail());
		textPane.setText("Hola " + presupuesto.getCliente().getNombre().substring(0,presupuesto.getCliente().getNombre().indexOf(" ")) + ",\n"
				+ "Adjunto presupuesto como hemos acordado previamente. \nUn saludo, ");
		textAsunto.setText("Presupuesto Web");
		
		setLocationRelativeTo(null);
	}
	
	public EnviarEmail(String email) {
		iniciarComponentes();
		iniciarManejadores2(email);	
		textDestinatario.setText(email);
		
		setLocationRelativeTo(null);
	}

	private void iniciarManejadores(Presupuesto presupuesto) {
		
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					EnviarMensaje(textDestinatario.getText(), textAsunto.getText(), textPane.getText(), presupuesto.getNombreArchivo());
					JOptionPane.showMessageDialog(null, "Mensaje enviado correctamente");
					setVisible(false);
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Error al enviar el mensaje");
				}
				
			}
		});
	}
	
	private void iniciarManejadores2(String email) {
		
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					EnviarMensaje(textDestinatario.getText(), textAsunto.getText(), textPane.getText());
					JOptionPane.showMessageDialog(null, "Mensaje enviado correctamente");
					setVisible(false);
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Error al enviar el mensaje");
				}
				
			}
		});
	}

	private void iniciarComponentes() {
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(58, 63, 190, 164);
		contentPane.add(scrollPane);
		
		textPane = new JTextPane();
		scrollPane.setViewportView(textPane);
		
		JLabel lblEscribeTuMensaje = new JLabel("Escribe tu mensaje");
		lblEscribeTuMensaje.setBounds(58, 39, 140, 14);
		contentPane.add(lblEscribeTuMensaje);
		
		btnEnviar = new JButton("Enviar");
	
		btnEnviar.setBounds(275, 204, 89, 23);
		contentPane.add(btnEnviar);
		
		textDestinatario = new JTextField();
		textDestinatario.setBounds(278, 63, 140, 20);
		contentPane.add(textDestinatario);
		textDestinatario.setColumns(10);
		
		JLabel lblDestinatario = new JLabel("Destinatario");
		lblDestinatario.setBounds(278, 39, 140, 14);
		contentPane.add(lblDestinatario);
		
		lblAsunto = new JLabel("Asunto");
		lblAsunto.setBounds(278, 105, 140, 14);
		contentPane.add(lblAsunto);
		
		textAsunto = new JTextField();
		textAsunto.setColumns(10);
		textAsunto.setBounds(275, 130, 140, 20);
		contentPane.add(textAsunto);
	}
	
	public static void EnviarMensaje(String destinatario, String asunto, String cuerpo, String ruta) throws MessagingException {
		
		 String remitente = "contacto@jesusgimenez.com";  //Para la dirección remitente@gmail.com

		Properties props = new Properties();
		props.put("mail.smtp.host", "mail.jesusgimenez.com");
		props.setProperty("mail.smtp.starttls.enable", "true");
		props.setProperty("mail.smtp.port","587");
		props.setProperty("mail.smtp.user", "contacto@jesusgimenez.com");
		props.setProperty("mail.smtp.auth", "true");
		Session session = Session.getDefaultInstance(props, null);
		session.setDebug(true);
		    
		MimeMessage message = new MimeMessage(session);
		
	    //Añadimos el adjunto (modificar para que identifique el presupuesto) 
	    BodyPart texto = new MimeBodyPart();
	    texto.setText(cuerpo);
	    
	    	BodyPart adjunto = new MimeBodyPart();
			adjunto.setDataHandler(new DataHandler(new FileDataSource(ruta)));
			adjunto.setFileName(ruta);
			MimeMultipart multiParte = new MimeMultipart();
		    multiParte.addBodyPart(adjunto);
		    multiParte.addBodyPart(texto);

	    

	    try {
	        message.setFrom(new InternetAddress(remitente));
	        message.addRecipients(Message.RecipientType.TO, destinatario);   //Se podrían añadir varios de la misma manera
	        message.setSubject(asunto);
	        message.setContent(multiParte);
	        Transport transport = session.getTransport("smtp");
	        transport.connect("mail.jesusgimenez.com", remitente, "yourpassword");
	        transport.sendMessage(message, message.getAllRecipients());
	        transport.close();
	    }
	    catch (MessagingException me) {
	        me.printStackTrace();   //Si se produce un error
	    }
	}
	
	public static void EnviarMensaje(String destinatario, String asunto, String cuerpo) throws MessagingException {
		
	    String remitente = "contacto@jesusgimenez.com";  //Para la dirección remitente@gmail.com

	    Properties props = new Properties();
	    props.put("mail.smtp.host", "mail.jesusgimenez.com");
	    props.setProperty("mail.smtp.starttls.enable", "true");
	    props.setProperty("mail.smtp.port","587");
	    props.setProperty("mail.smtp.user", "contacto@jesusgimenez.com");
	    props.setProperty("mail.smtp.auth", "true");
	    Session session = Session.getDefaultInstance(props, null);
	    session.setDebug(true);
	    
	    MimeMessage message = new MimeMessage(session);

	    try {
	        message.setFrom(new InternetAddress(remitente));
	        message.addRecipients(Message.RecipientType.TO, destinatario);   //Se podrían añadir varios de la misma manera
	        message.setSubject(asunto);
	        message.setText(cuerpo);
	        Transport transport = session.getTransport("smtp");
	        transport.connect("mail.jesusgimenez.com", remitente, "yourpassword");
	        transport.sendMessage(message, message.getAllRecipients());
	        transport.close();
	    }
	    catch (MessagingException me) {
	        me.printStackTrace();   //Si se produce un error
	    }
	}


}
