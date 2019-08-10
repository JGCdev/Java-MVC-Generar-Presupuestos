package clases;

import java.io.File;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


/**
 * 
 * @author Jesús Giménez
 * @since 1.0
 * @version 1.0
 * 
 * 
 *  Esta clase se utiliza para interactuar entre las demás clases, contiene los métodos
 *  
 */

public class Gestion {
	
	private static String rutaGuardado = "datos.bin";
	private static String rutaCargado = "datos.bin";
	
	private static ArrayList<Cliente> listaClientes = new ArrayList<Cliente>();
	private static ArrayList<Empleado> listaEmpleados = new ArrayList<Empleado>();
	private static ArrayList<Proyecto> listaProyectos = new ArrayList<Proyecto>();
	private static ArrayList<Servicio> listaServicios = new ArrayList<Servicio>();
	private static ArrayList<Presupuesto> listaPresupuestos = new ArrayList<Presupuesto>();
	
	//CLIENTES
	/**
	 * Metodo que añade un cliente al arrayList
	 * @param nombre Nombre del cliente
	 * @param DNI String
	 * @param email String 
	 * @param telefono String 
	 * @param fechaRegistro LocalDate
	 */
	public static void anadirCliente(String nombre, String DNI, String email, String telefono, LocalDate fechaRegistro) {
		
		Cliente nuevo = new Cliente(nombre, DNI, email, telefono, fechaRegistro);
		
		listaClientes.add(nuevo);
	}
	
	/**
	 * Metodo que elimina un cliente a partir de su ID
	 * @param id int
	 */
	public static void eliminarCliente(int id) {
		
		Cliente aux;
		for(int i=0; i<listaClientes.size(); i++) {
			aux = listaClientes.get(i);
			if(aux.getId()==id) {
				listaClientes.remove(i);
				i--;
			}
		}
		
	}
	
	/**
	 * Metodo que modifica los datos de un cliente
	 * @param nombre String
	 * @param DNI String
	 * @param email String 
	 * @param telefono String 
	 * @param fechaRegistro LocalDate
	 * @param id int
	 */
	public static void modificarCliente(String nombre, String DNI, String email, String telefono, LocalDate fechaRegistro, int id) {	
		Cliente cliente;
		for (int i=0; i<listaClientes.size(); i++) {
			cliente = listaClientes.get(i);
			if (cliente.getId()==id) {
				cliente.setDNI(DNI);
				cliente.setEmail(email);
				cliente.setFechaRegistro(fechaRegistro);
				cliente.setNombre(nombre);
				cliente.setTelefono(telefono);
			}
		}
	}
	//EMPLEADOS
	/**
	 * Metodo que añade un empleado programador
	 * @param nombre String
	 * @param DNI String
	 * @param email String 
	 * @param telefono String 
	 * @param lenguajes int
	 */
	public static void anadirEmpleado(String nombre, String DNI, String email, String telefono, int lenguajes) {
		Programador nuevo = new Programador(nombre, DNI, email, telefono, lenguajes);
		listaEmpleados.add(nuevo);
	}
	
	/**
	 * Metodo que añade un empleado diseñador gráfico
	 * @param nombre String
	 * @param DNI String
	 * @param email String 
	 * @param telefono String 
	 * @param programaPrincipal String
	 */
	public static void anadirEmpleado(String nombre, String DNI, String email, String telefono, String programaPrincipal) {	
		Grafico nuevo = new Grafico(nombre, DNI, email, telefono, programaPrincipal);
		listaEmpleados.add(nuevo);
	}
	
	
	/**
	 * Metodo que elimina un empleado del arrayList a partir de su ID
	 * @param id int
	 */
	public static void eliminarEmpleado(int id) {
		Empleado aux;
		for(int i=0; i<listaEmpleados.size(); i++) {
			aux = listaEmpleados.get(i);
			if(aux.getId()==id) {
				listaEmpleados.remove(i);
				i--;
			}
		}
	}
	
	/**
	 * Metodo que modifica los datos de un empleado programador
	 * @param nombre String
	 * @param DNI String
	 * @param email String 
	 * @param telefono String 
	 * @param id int
	 * @param lenguajes int
	 */
	public static void modificarEmpleado(String nombre, String DNI, String email, String telefono, int id, int lenguajes) {	
		Empleado empleado;
		Programador aux;
		for (int i=0; i<listaEmpleados.size(); i++) {
			empleado = listaEmpleados.get(i);
			if (empleado.getId()==id) {
				if (empleado.getClass().getSimpleName().equals("Programador")){
					aux = (Programador)empleado;
					aux.setDni(DNI);
					aux.setEmail(email);
					aux.setNombre(nombre);
					aux.setTelefono(telefono);
					aux.setLenguajesDominados(lenguajes);
				}
			}
		}
	}
	
	
	/**
	 * Metodo que modifica los datos de un empleado diseñador gráfico
	 * @param nombre String
	 * @param DNI String
	 * @param email String 
	 * @param telefono String 
	 * @param id int
	 * @param ubicacion String 
	 */
	public static void modificarEmpleado(String nombre, String DNI, String email, String telefono, int id, String ubicacion) {	
		Empleado empleado;
		Grafico aux;
		for (int i=0; i<listaEmpleados.size(); i++) {
			empleado = listaEmpleados.get(i);
			if (empleado.getId()==id) {
				if (empleado.getClass().getSimpleName().equals("Grafico")){
					aux = (Grafico)empleado;
					aux.setDni(DNI);
					aux.setEmail(email);
					aux.setNombre(nombre);
					aux.setTelefono(telefono);
					aux.setUbicacion(ubicacion);
				}	
			}
		}
	}
		
	//PROYECTOS
	/**
	 * Metodo que genera un nuevo proyecto
	 * @param nombre String
	 * @param cliente Cliente
	 * @param empleados ArrayList
	 * @param descripcion String 
	 * @param fechaComienzo LocalDate
	 */
	public static void anadirProyecto(String nombre, Cliente cliente, ArrayList<Empleado> empleados, String descripcion, LocalDate fechaComienzo) {
		Proyecto nuevo = new Proyecto(nombre, cliente, empleados, descripcion, fechaComienzo);
		listaProyectos.add(nuevo);
	}
		
	/**
	 * Metodo que elimina un proyecto a partir de su ID
	 * @param id int
	 */
	public static void eliminarProyecto(int id) {
		Proyecto aux;
		for(int i=0; i<listaProyectos.size(); i++) {
			aux = listaProyectos.get(i);
			if(aux.getId()==id) {
				listaProyectos.remove(i);
				i--;
			}
		}
	}
		
	/**
	 * Metodo que modifica un proyecto
	 * @param nombre String
	 * @param cliente Cliente
	 * @param empleados ArrayList
	 * @param descripcion String 
	 * @param fechaRegistro LocalDate
	 * @param id int
	 */
	public static void modificarProyecto(String nombre, Cliente cliente, ArrayList<Empleado> empleados, String descripcion, LocalDate fechaRegistro, int id) {	
		Proyecto proyecto;
		for (int i=0; i<listaProyectos.size(); i++) {
			proyecto = listaProyectos.get(i);
			if (proyecto.getId()==id) {
				proyecto.setNombre(nombre);
				proyecto.setCliente(cliente);
				proyecto.setDescripcion(descripcion);
				proyecto.setEmpleados(empleados);
				proyecto.setFechaComienzo(fechaRegistro);
			}
		}
	}
	
	
	
	//SERVICIOS
	public static void anadirServicioLista(String descripcion, double pUnidad) {
		
		Servicio aux = new Servicio(descripcion, pUnidad);
		listaServicios.add(aux);
	}
	
	public static void eliminarServicio(int ref) throws IOException {
		listaServicios.remove(ref);
		guardarDatos();
	}
	
	public static void modificarServicio(int ref, String desc, double precio) throws IOException {	
		Servicio aux;
		for (int i=0; i<listaServicios.size(); i++) {
			aux= listaServicios.get(i);
			if (aux.getRef()==ref) {
				aux.setDescripcion(desc);
				aux.setpUnidad(precio);
			}
		}
		guardarDatos();
	}
	
	//Presupuestos
	public static void anadirPresupuestoLista(ArrayList<Servicio> listaServicios, Cliente cliente, double descuento, int finalizacion, double subtotal) {
		Presupuesto aux = new Presupuesto(listaServicios, cliente, descuento, finalizacion, subtotal);
		listaPresupuestos.add(aux);
	}
	
	public static void eliminarPresupuesto(int index) {
		Presupuesto aux;
		aux= listaPresupuestos.get(index);
		listaPresupuestos.remove(aux);
		try {
			guardarDatos();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void modificarPresupuesto(ArrayList<Servicio> listaServicios, int id, int finalizacion, double subtotal, double desc) throws IOException {	
		Presupuesto aux;
		for (int i=0; i<listaPresupuestos.size(); i++) {
			aux= listaPresupuestos.get(i);
			if (aux.getId()==id) {
				//Listaservicios viene nulll
				aux.setListaServicios(listaServicios);
				
				aux.setDescuento(desc);
				aux.setFinalizacionEstimada(finalizacion);
				aux.setSubtotal(subtotal);
				listaPresupuestos.set(i, aux);
			}
			
		}
		guardarDatos();
	}
	
	/**
	 * Metodo que guarda los datos en una ruta preestablecida
	 * @throws IOException Excepción de guardado
	 */
	public static void guardarDatos() throws IOException {
		File fichero = new File(rutaGuardado);
		if (!fichero.exists()) {
			fichero.createNewFile();
		}
		FileOutputStream flujoFichero = new FileOutputStream(fichero);
		ObjectOutputStream flujoObjetos = new ObjectOutputStream(flujoFichero);
		
		// Escribir objetos en el fichero ordenándolos previamente con interface Comparable
		Collections.sort(listaClientes);
		flujoObjetos.writeObject(listaClientes);
		Collections.sort(listaEmpleados);
		flujoObjetos.writeObject(listaEmpleados);
		Collections.sort(listaProyectos);
		flujoObjetos.writeObject(listaProyectos);
		flujoObjetos.writeObject(listaServicios);
		flujoObjetos.writeObject(listaPresupuestos);
		flujoObjetos.close();
	}
	

	/**
	 * Metodo que carga los datos para trabajar en el programa
	 * @throws IOException Excepción de guardado
	 * @throws ClassNotFoundException Excepción de clase no encontrada
	 */
	@SuppressWarnings("unchecked")
	public static void cargarDatos() throws IOException, ClassNotFoundException {
		File fichero = new File(rutaCargado);
		if (fichero.exists()) {
			FileInputStream flujoFichero= new FileInputStream(fichero);
			ObjectInputStream flujoObjetos = new ObjectInputStream(flujoFichero);
			listaClientes = (ArrayList<Cliente>)flujoObjetos.readObject();
			listaEmpleados = (ArrayList<Empleado>)flujoObjetos.readObject();
			listaProyectos = (ArrayList<Proyecto>)flujoObjetos.readObject();
			listaServicios = (ArrayList<Servicio>)flujoObjetos.readObject();
			listaPresupuestos = (ArrayList<Presupuesto>)flujoObjetos.readObject();
			flujoObjetos.close();
			
			//Obtener ID + alto para poder cargarlos de nuevo
			int tamanoClientes = obtenerIdAltoCliente();
			Cliente.setNumClientes(tamanoClientes+1);
			
			int tamanoEmpleados = obtenerIdAltoEmpleado();
			Empleado.setNumEmpleados(tamanoEmpleados);
			
			int tamanoProyectos = obtenerIdAltoProyecto();
			Proyecto.setNumProyectos(tamanoProyectos);
			
			int tamanoServicios = obtenerIdAltoServicio();
			Servicio.setNumeroServicios(tamanoServicios);
			
			int tamanoPresupuestos = obtenerIdAltoPresupuesto();
			Presupuesto.setNumPresupuestos(tamanoPresupuestos);
		}	
		
		
	}
	
	private static int obtenerIdAltoPresupuesto() {
		Presupuesto aux;
		int mayor = 0;
		for (int i=0; i<listaPresupuestos.size(); i++) {
			aux = listaPresupuestos.get(i);
			if (aux.getId()>mayor) {
				mayor = aux.getId();
			}
		}
		return mayor+1;
	}

	private static int obtenerIdAltoServicio() {
		Servicio aux;
		int mayor = 0;
		for (int i=0; i<listaServicios.size(); i++) {
			aux = listaServicios.get(i);
			if (aux.getRef()>mayor) {
				mayor = aux.getRef();
			}
		}
		return mayor+1;
	}

	/**
	 * Metodo que obtiene el ID más alto de empleados dentro del ArrayList. Se utiliza para crear una nueva ID sin repetir.
	 * * @return mayor id mayor
	 */
	private static int obtenerIdAltoEmpleado() {
		Empleado aux;
		int mayor = 0;
		for (int i=0; i<listaEmpleados.size(); i++) {
			aux = listaEmpleados.get(i);
			if (aux.getId()>mayor) {
				mayor = aux.getId();
			}
		}
		return mayor+1;
	}

	/**
	 * Metodo que obtiene el ID más alto de cliente dentro del ArrayList. Se utiliza para crear una nueva ID sin repetir.
	 * @return mayor ID mayor
	 */
	private static int obtenerIdAltoCliente() {
		Cliente aux;
		int mayor = 0;
		for (int i=0; i<listaClientes.size(); i++) {
			aux = listaClientes.get(i);
			if (aux.getId()>mayor) {
				mayor = aux.getId();
			}
		}
		return mayor;
	}

	/**
	 * Metodo que obtiene el ID más alto de proyecto dentro del ArrayList. Se utiliza para crear una nueva ID sin repetir.
	 * @return mayor ID mayor
	 */
	private static int obtenerIdAltoProyecto() {
		Proyecto aux;
		int mayor = 0;
		for (int i=0; i<listaProyectos.size(); i++) {
			aux = listaProyectos.get(i);
			if (aux.getId()>mayor) {
				mayor = aux.getId();
			}
		}
		return mayor+1;
	}
	
	/**
	 * Metodo que comprueba si existe un ID de cliente a partir de un id en forma de String
	 * @param id String
	 * @return boolean
	 */
	public static boolean existeID(String id) {
		int ID = Integer.parseInt(id);
		Cliente aux;
		for (int i=0; i<listaClientes.size();i++) {
			aux = listaClientes.get(i);
			if (aux.getId()==ID) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Metodo que permite enviar un mensaje a un cliente y configurar los detalles del servidor de correo con Google
	 * @param destinatario String
	 * @param asunto String
	 * @param cuerpo String
	 * @throws MessagingException 
	 */
	public static void EnviarMensaje(String destinatario, String asunto, String cuerpo, String ruta) throws MessagingException {
	    String remitente = "contacto@jesusgimenez.com";  //Para la dirección remitente@gmail.com
	    
	    Properties props = new Properties();
	    props.put("mail.smtp.host", "mail.jesusgimenez.com");
	    props.setProperty("mail.smtp.starttls.enable", "true");
	    props.setProperty("mail.smtp.port","587");
	    props.setProperty("mail.smtp.user", "contacto@jesusgimenez.com");
	    props.setProperty("mail.smtp.auth", "true");

	    Session session = Session.getDefaultInstance(props, null);
	    session.setDebug(true);
	    
	    MimeMessage message = new MimeMessage(session);
	    
	    //Añadimos el adjunto (modificar para que identifique el presupuesto)
	    
	    
	    BodyPart texto = new MimeBodyPart();
	    texto.setText(cuerpo);
	    
	    	BodyPart adjunto = new MimeBodyPart();
			adjunto.setDataHandler(new DataHandler(new FileDataSource(ruta)));
			adjunto.setFileName(ruta);
			MimeMultipart multiParte = new MimeMultipart();
		    multiParte.addBodyPart(adjunto);
		    multiParte.addBodyPart(texto);

	    

	    try {
	        message.setFrom(new InternetAddress(remitente));
	        message.addRecipients(Message.RecipientType.TO, destinatario);   //Se podrían añadir varios de la misma manera
	        message.setSubject(asunto);
	        message.setContent(multiParte);
	        Transport transport = session.getTransport("smtp");
	        transport.connect("mail.jesusgimenez.com", remitente, "xxxxxx");
	        transport.sendMessage(message, message.getAllRecipients());
	        transport.close();
	    }
	    catch (MessagingException me) {
	        me.printStackTrace();   //Si se produce un error
	    }
	}
	
	/**
	 * Metodo que devuelve el cliente con el ID especificado
	 * @param id int
	 * @return aux Cliente
	 */
	public static Cliente obtenerClienteConID(int id) {
		Cliente aux;
		for (int i=0; i<listaClientes.size(); i++) {
			aux = listaClientes.get(i);
			if (id == aux.getId()) {
				return aux;
			}
		}
		return null;
	}
	
	/**
	 * Metodo que devuelve el empleado con el ID especificado
	 * @param id int
	 * @return aux Empleado
	 */
	private static Empleado obtenerEmpleadoConID(int id) {
		Empleado aux;
		for (int i=0; i<listaEmpleados.size(); i++) {
			aux = listaEmpleados.get(i);
			if (id == aux.getId()) {
				return aux;
			}
		}
		return null;
	}
	
	/**
	 * Metodo que devuelve un ArrayList de empleados a partir de un Array con sus IDs
	 * @param idEmpleados int[]
	 * @return lista lista de empleados
	 */
	public static ArrayList<Empleado> obtenerEmpleadosConArray(int[] idEmpleados) {
		ArrayList<Empleado> lista = new ArrayList<Empleado>(); 
		Empleado aux;
		for (int i=0; i < idEmpleados.length; i++) {
			int id = Gestion.getListaEmpleados().get(idEmpleados[i]).getId();
			aux = obtenerEmpleadoConID(id);
			lista.add(aux);
		}
		return lista;
	}
	
	public static boolean esProgramador(Empleado empleado) {
		if (empleado.getClass().getSimpleName().equals("Programador")) {
			return true;
		}
		return false;
		
	}
	
	public static Presupuesto getPresupuesto(int indice) {
		Presupuesto aux = listaPresupuestos.get(indice);
		return aux;
	}
	
	//Getters y setters
	public static ArrayList<Cliente> getListaClientes() {
		return listaClientes;
	}
	
	public static void setListaClientes(ArrayList<Cliente> listaClientes) {
		Gestion.listaClientes = listaClientes;
	}
	
	public static void setListaPresupuestos(ArrayList<Presupuesto> listaPresupuestos) {
		Gestion.listaPresupuestos = listaPresupuestos;
	}
	
	public static ArrayList<Presupuesto> getListaPresupuestos() {
		return listaPresupuestos;
	}

	public static ArrayList<Servicio> getListaServicios() {
		return listaServicios;
	}

	public static void setListaServicios(ArrayList<Servicio> listaServicios) {
		Gestion.listaServicios = listaServicios;
	}
	
	public static ArrayList<Empleado> getListaEmpleados() {
		return listaEmpleados;
	}

	public static void setListaEmpleados(ArrayList<Empleado> listaEmpleados) {
		Gestion.listaEmpleados = listaEmpleados;
	}
	
	public static ArrayList<Proyecto> getListaProyectos() {
		return listaProyectos;
	}

	public static void setListaProyectos(ArrayList<Proyecto> listaProyectos) {
		Gestion.listaProyectos = listaProyectos;
	}
	
	public static void setRutaGuardado(String rutaGuardado) {
		Gestion.rutaGuardado = rutaGuardado;
	}
	
	public static String getRutaCargado() {
		return rutaCargado;
	}

	public static void setRutaCargado(String rutaCargado) {
		Gestion.rutaCargado = rutaCargado;
	}

	public static int getIdPresupuesto(int indice) {
		
		return listaPresupuestos.get(indice).getId();
	}
	


}
