package clases;

import java.io.Serializable;

/**
 * 
 * @author Jesús Giménez
 * @since 1.0
 * @version 1.0
 * 
 * 
 *  Clase utilizada para llevar registro de los empleados programadores
 *  
 */

public class Programador extends Empleado implements Serializable{


	private static final long serialVersionUID = 1L;
	private double salarioTotal;
	private int lenguajesDominados;
	
	/**
	 * Constructor para programador
	 * @param nombre Nombre del programador
	 * @param dni dni del programador
	 * @param email Email del programador
	 * @param telefono Telefono del programador
	 * @param lenguajesDominados Lenguajes dominados por el programador
	 */
	
	public Programador(String nombre, String dni, String telefono, String email, int lenguajesDominados) {
		super(nombre, dni, telefono, email);
		this.setLenguajesDominados(lenguajesDominados);
		this.salarioTotal = Empleado.getSalarioBase() + 600;
	}

	public double getSalarioTotal() {
		return salarioTotal;
	}

	public int getLenguajesDominados() {
		return lenguajesDominados;
	}

	public void setLenguajesDominados(int lenguajesDominados) {
		this.lenguajesDominados = lenguajesDominados;
	}
	
	
	
	
}
