package vistas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import clases.Gestion;

public class CargarArchivo extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	 private JTextField textField;
	 private JButton btnSeleccionar;
	 private JButton btnOk;


	public CargarArchivo() {
		setTitle("Elegir archivo de carga");
		iniciarComponentes();
		iniciarManejadores();
	}

	private void iniciarManejadores() {
		btnSeleccionar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		//Creamos el objeto JFileChooser
        		JFileChooser fc=new JFileChooser();
        		
        		fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        		int seleccion=fc.showOpenDialog(contentPane);
        		 
        		//Si el usuario pincha en aceptar
        		if(seleccion==JFileChooser.APPROVE_OPTION){
        		 
        		    //Seleccionamos el fichero
        		    File fichero=fc.getSelectedFile();
        		 
        		    //Ecribe la ruta del fichero seleccionado en el campo de texto
        		    textField.setText(fichero.getAbsolutePath());
        		    
        		}
        	}
        });
		
		btnOk.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		try {
        			//Tomamos la ruta seleccionada y le añadimos un nombre fijo de archivo
        			String ruta = textField.getText();
            		Gestion.setRutaCargado(ruta);
            		Gestion.cargarDatos();
            		JOptionPane.showMessageDialog(null, "Archivo cargado correctamente");
            		setVisible(false);
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Problema al cargar el archivo");
				}
        		
        	}
        });
		
	}


	private void iniciarComponentes() {

        setBounds(100, 100, 450, 136);
        contentPane = new JPanel();
        contentPane.setLayout(null);
        setContentPane(contentPane);
 
        textField = new JTextField();
        textField.setToolTipText("Inserta la ruta del fichero de audio");
        textField.setBounds(52, 26, 209, 20);
        contentPane.add(textField);
        textField.setColumns(10);
 
        btnSeleccionar = new JButton("Seleccionar...");
        
        btnSeleccionar.setBounds(288, 25, 109, 23);
        contentPane.add(btnSeleccionar);
        
        btnOk = new JButton("OK");
        
        btnOk.setBounds(52, 57, 89, 23);
        contentPane.add(btnOk);
	}
}
