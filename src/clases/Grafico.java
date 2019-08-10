package clases;

import java.io.Serializable;

/**
 * 
 * @author Jes�s Gim�nez
 * @since 1.0
 * @version 1.0
 * 
 * 
 *  Clase utilizada para llevar registro de los empleados dise�adores gr�ficos
 *  
 */

public class Grafico extends Empleado implements Serializable{

	private static final long serialVersionUID = 1L;
	private final double salarioTotal;
	private String ubicacion;
	
	/**
	 * Constructor para dise�ador gr�fico
	 * @param nombre Nombre del empleado
	 * @param dni dni del empleado
	 * @param email Email del empleado
	 * @param telefono Tel�fono del empleado
	 * @param ubicacion Ubicaci�n de trabajo
	 */
	
	public Grafico(String nombre, String dni, String telefono, String email, String ubicacion) {
		super(nombre, dni, telefono, email);
		// TODO Auto-generated constructor stub
		this.salarioTotal = Empleado.getSalarioBase() + 300;
		this.setUbicacion(ubicacion);
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public double getSalarioTotal() {
		return salarioTotal;
	}
		

}
