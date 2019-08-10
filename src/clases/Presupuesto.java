package clases;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Presupuesto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String para;
	private String email;
	private String telefono;
	private int id;
	private LocalDate fecha;
	private String fechaString;
	private LocalDate validez;
	private String validezString;
	private ArrayList<Servicio> listaServicios = new ArrayList<>();
	private double descuento;

	private int finalizacionEstimada;
	private Cliente cliente;
	private static int numPresupuestos;
	private double subtotal;
	private String nombreArchivo;
	
	public Presupuesto(ArrayList<Servicio> listaServicios, Cliente cliente, double descuento, int finalizacion, double subtotal) {
		super();
		this.para = cliente.getNombre();
		this.email = cliente.getEmail();
		this.telefono = cliente.getTelefono();
		this.id = getNumPresupuestos();
		this.fecha = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-LLLL-yyyy");
		this.fechaString = fecha.format(formatter);
		this.listaServicios = listaServicios;
		this.validez = fecha.plusDays(31);
		this.validezString = validez.format(formatter);
		this.descuento = descuento;
		this.finalizacionEstimada = finalizacion;
		this.cliente = cliente;
		this.nombreArchivo = "PRESUP-18" + getNumPresupuestos() + ".pdf";

		this.subtotal = subtotal;
		numPresupuestos++;
	}
	
	public double calcularTotal(ArrayList<Servicio> listaServicios){
		Servicio aux;
		Double total = 0.0;
		for (int i=0; i<listaServicios.size();i++) {
			aux = listaServicios.get(i);
			total = total + (aux.getpUnidad() * aux.getUnidades());
			total = total - descuento;
		}
		return total;
	}
	
	public String getNombreArchivo() {
		return nombreArchivo;
	}

	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}

	public double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}

	public String getValidezString() {
		return validezString;
	}

	public void setValidezString(String validezString) {
		this.validezString = validezString;
	}

	public String getFechaString() {
		return fechaString;
	}

	public void setFechaString(String fechaString) {
		this.fechaString = fechaString;
	}

	public String getPara() {
		return para;
	}

	public void setPara(String para) {
		this.para = para;
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public LocalDate getValidez() {
		return validez;
	}

	public void setValidez(LocalDate validez) {
		this.validez = validez;
	}

	public ArrayList<Servicio> getListaServicios() {
		return listaServicios;
	}

	public void setListaServicios(ArrayList<Servicio> listaServicios) {
		this.listaServicios = listaServicios;
	}

	public double getDescuento() {
		return descuento;
	}

	public void setDescuento(double descuento) {
		this.descuento = descuento;
	}

	public int getFinalizacionEstimada() {
		return finalizacionEstimada;
	}

	public void setFinalizacionEstimada(int finalizacionEstimada) {
		this.finalizacionEstimada = finalizacionEstimada;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public static int getNumPresupuestos() {
		return numPresupuestos;
	}

	public static void setNumPresupuestos(int numPresupuestos) {
		Presupuesto.numPresupuestos = numPresupuestos;
	}

	@Override
	public String toString() {
		return "Presupuesto [para=" + para + ", email=" + email + ", telefono=" + telefono + ", id=" + id + ", fecha="
				+ fecha + ", validez=" + validez + ", listaServicios=" + listaServicios + ", descuento=" + descuento
				+ ", finalizacionEstimada=" + finalizacionEstimada + ", cliente="
				+ cliente + "]";
	}
	
	
	
	
		
}
