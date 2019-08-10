package principal;

import java.io.IOException;

import clases.Gestion;
import vistas.VentanaPrincipal;

/**
 * 
 * @author Jesús Giménez
 * @since 1.0
 * @version 1.0
 * 
 * 
 *  Clase principal con el único método main del programa, llama a la ventana de login.
 *  El programa por defecto guarda los datos en la carpeta dónde se ubica
 *  
 */

public class Principal {
	
	/**
	 * Metodo Main que lanza el login y carga los datos
	 * @throws ClassNotFoundException Excepción de clase
	 * @throws IOException Excepción de guardado
	 * @param args String[] Argumentos
	 */
	
	public static void main(String[] args) throws ClassNotFoundException, IOException {
		VentanaPrincipal frame = new VentanaPrincipal();
		frame.setVisible(true);
		Gestion.cargarDatos();
	}
}
