package principal;

import java.io.IOException;

import clases.Gestion;
import vistas.VentanaPrincipal;

/**
 * 
 * @author Jes�s Gim�nez
 * @since 1.0
 * @version 1.0
 * 
 * 
 *  Clase principal con el �nico m�todo main del programa, llama a la ventana de login.
 *  El programa por defecto guarda los datos en la carpeta d�nde se ubica
 *  
 */

public class Principal {
	
	/**
	 * Metodo Main que lanza el login y carga los datos
	 * @throws ClassNotFoundException Excepci�n de clase
	 * @throws IOException Excepci�n de guardado
	 * @param args String[] Argumentos
	 */
	
	public static void main(String[] args) throws ClassNotFoundException, IOException {
		VentanaPrincipal frame = new VentanaPrincipal();
		frame.setVisible(true);
		Gestion.cargarDatos();
	}
}
