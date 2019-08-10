package clases;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * 
 * @author Jesús Giménez
 * @since 1.0
 * @version 1.0
 * 
 * 
 *  Clase utilizada para llevar registro de clientes
 *  
 */

public class Cliente implements Serializable, Comparable<Cliente>{

	private static final long serialVersionUID = 1L;
	private String nombre;
	private String DNI;
	private String email;
	private String telefono;
	private LocalDate fechaRegistro;
	private int id;
	private static int numClientes;
	
	/**
	 * Constructor para cliente 
	 * @param nombre Nombre del cliente
	 * @param DNI dni del cliente
	 * @param email Email del cliente
	 * @param telefono Teléfono del cliente
	 * @param fechaRegistro Fecha de registro del cliente
	 */
	public Cliente(String nombre, String DNI, String email, String telefono, LocalDate fechaRegistro) {
		super();
		this.nombre = nombre;
		this.DNI = DNI;
		this.email = email;
		this.telefono = telefono;
		this.id = getNumClientes();
		this.fechaRegistro = fechaRegistro;
		numClientes++;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDNI() {
		return DNI;
	}

	public void setDNI(String dNI) {
		DNI = dNI;
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
	
	public static int getNumClientes() {
		return numClientes;
	}

	public static void setNumClientes(int numClientes) {
		Cliente.numClientes = numClientes;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(LocalDate fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	
	/**
	 * Método toString sobreescrito, devuelve DNI para usar en JList
	 * @return String DNI
	 */
	public String toString() {
		return "DNI: " + DNI;
	}

	/**
	 * Método compareTo de la interface Comparable que ordena el cliente por su fecha de registro
	 * @return LocalDate fecha menor de registro
	 */
	@Override
	public int compareTo(Cliente otro) {
		return fechaRegistro.compareTo(otro.fechaRegistro);
	}
	
	
}
