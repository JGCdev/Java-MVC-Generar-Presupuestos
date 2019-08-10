package clases;

import java.io.Serializable;

public class Servicio implements Serializable{

	private static final long serialVersionUID = 1L;
	private int ref;
	private String descripcion;
	private int unidades = 1;
	private double pUnidad;
	private double total;
	private static int numeroServicios;
	
	public Servicio(String descripcion, double pUnidad) {
		super();
		this.ref = getNumeroServicios();
		this.descripcion = descripcion;
		this.pUnidad = pUnidad;
		this.total = calcularTotal();
		numeroServicios++;
	}

	private double calcularTotal() {
		// TODO Auto-generated method stub
		return pUnidad * getUnidades();
	}

	public int getRef() {
		return ref;
	}

	public void setRef(int ref) {
		this.ref = ref;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getUnidades() {
		return unidades;
	}

	public void setUnidades(int unidades) {
		this.unidades = unidades;
	}

	public double getpUnidad() {
		return pUnidad;
	}

	public void setpUnidad(double pUnidad) {
		this.pUnidad = pUnidad;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public static int getNumeroServicios() {
		return numeroServicios;
	}

	public static void setNumeroServicios(int numeroServicios) {
		Servicio.numeroServicios = numeroServicios;
	}

	@Override
	public String toString() {
		return  ref + ", " + descripcion + ", Uds: " + unidades + ", " + pUnidad + "€";
	}
	
}
