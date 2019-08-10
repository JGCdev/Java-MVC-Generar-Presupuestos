package clases;

import java.io.Serializable;

/**
 * 
 * @author Jesús Giménez
 * @since 1.0
 * @version 1.0
 * 
 * 
 *  Clase utilizada para llevar registro de los empleados
 *  
 */

public abstract class Empleado implements Serializable, Comparable<Empleado> {

	private static final long serialVersionUID = -1634000538945321599L;
	private static final double salarioBase = 1000;
	private String nombre;
	private String dni;
	private String email;
	private String telefono;
	private int id;
	private static int numEmpleados;
	
	/**
	 * Constructor para empleado
	 * @param nombre Nombre del empleado
	 * @param dni dni del empleado
	 * @param email Email del empleado
	 * @param telefono Teléfono del empleado
	 */
	public Empleado(String nombre, String dni, String telefono, String email) {
		super();
		this.id = numEmpleados;
		this.nombre = nombre;
		this.dni = dni;
		this.telefono = telefono;
		this.email = email;
		numEmpleados++;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public static double getSalarioBase() {
		return salarioBase;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public static int getNumEmpleados() {
		return numEmpleados;
	}

	public static void setNumEmpleados(int numEmpleados) {
		Empleado.numEmpleados = numEmpleados;
	}

	public String toString() {
		return nombre;
	}
	
	/**
	 * Método compareTo de la interface Comparable que ordena el empleado por su DNI
	 * @return String dni menor
	 */
	public int compareTo(Empleado emp) {
		return dni.compareTo(emp.getDni());
	}
	
	

}
