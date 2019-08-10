package clases;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * 
 * @author Jesús Giménez
 * @since 1.0
 * @version 1.0
 * 
 * 
 *  Clase utilizada para llevar registro de los proyectos
 *  
 */

public class Proyecto implements Serializable, Comparable<Proyecto>{
	
	private static final long serialVersionUID = 1081683639558510302L;
	private String nombre;
	private Cliente cliente;
	private ArrayList<Empleado> empleados;
	private String descripcion;
	private LocalDate fechaComienzo;
	private int id;
	private static int numProyectos;
	
	/**
	 * Constructor proyectos
	 * @param nombre Nombre del programador
	 * @param cliente Cliente que contrata el proyecto
	 * @param empleados Array de empleados disponibles para asignar al proyecto
	 * @param descripcion Descripción del proyecto
	 * @param fechaComienzo Fecha de comienzo
	 */
	
	public Proyecto(String nombre, Cliente cliente, ArrayList<Empleado> empleados, String descripcion, LocalDate fechaComienzo) {
		super();
		this.nombre = nombre;
		this.cliente = cliente;
		this.setEmpleados(empleados);
		this.descripcion = descripcion;
		this.fechaComienzo = fechaComienzo;
		this.id = getNumProyectos();
		numProyectos++;
	}

	public int getNumProyectos() {
		return numProyectos;
	}

	public static void setNumProyectos(int numProyectos) {
		Proyecto.numProyectos = numProyectos;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public LocalDate getFechaComienzo() {
		return fechaComienzo;
	}

	public void setFechaComienzo(LocalDate fechaComienzo) {
		this.fechaComienzo = fechaComienzo;
	}

	public ArrayList<Empleado> getEmpleados() {
		return empleados;
	}

	public void setEmpleados(ArrayList<Empleado> empleados) {
		this.empleados = empleados;
	}

	/**
	 * Método toString sobreescrito, devuelve datos del proyecto
	 * @return String Resumen del proyecto
	 */
	@Override
	public String toString() {
		return "Proyecto [nombre=" + nombre + ", cliente=" + cliente + ", empleados=" + empleados + ", descripcion="
				+ descripcion + ", fechaComienzo=" + fechaComienzo + ", id=" + id + "]";
	}
	
	/**
	 * Método compareTo de la interface Comparable que ordena el proyecto por su fecha de comienzo
	 * @return LocalDate fecha menor
	 */
	@Override
	public int compareTo(Proyecto proy) {
		return fechaComienzo.compareTo(proy.fechaComienzo);
	}

}
