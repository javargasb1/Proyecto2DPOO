package uniandes.dpoo.proyecto1.modelo;

import java.time.LocalDate;

import uniandes.dpoo.proyecto1.consola.ConsolaPrincipal;

public class ActualizadorEstadoVehiculo implements Usuario{

	private String nombre;
	private String login;
	private String clave;
	private String ubicacion;

	
	public ActualizadorEstadoVehiculo(String login, String clave, String nombre, String ubicacion)
	{
		this.login = login;
		this.clave = clave;
		this.nombre = nombre;
		this.ubicacion= ubicacion;
	}


	public String getNombre() {
		return nombre;
	}	
	
	public String getClave() {
		return clave;
	}	
	
	public String getLogin() {
		return login;
	}	
	
	public String getUbi() {
		return ubicacion;
	}	
	
	public String getWork() {
		return "ActualizadorEstadoVehiculo";
	}

	public Vehiculo actualizarEstado(Sede sede1,String categoria, LocalDate fechaRecogida, LocalDate fechaDevuelta)
	{
		boolean encontrado=false;
		Vehiculo vehiculoReservado= null;
		int i=0;
		String estado = "Alquilado";
		while(encontrado==false && i< ConsolaPrincipal.listaVehiculos.size()) {
			vehiculoReservado= ConsolaPrincipal.listaVehiculos.get(i);
			String cat=vehiculoReservado.getCategoria();
			Sede sedev = vehiculoReservado.getUbi();
			boolean disp= vehiculoReservado.verificarDisponibilidad(fechaRecogida, fechaDevuelta);
			if(cat.equals(categoria) && sede1.getnombre().equals(sedev.getnombre()) && disp==true) {
				encontrado=true;
				vehiculoReservado.bloquearDisponibilidad(estado,fechaRecogida,fechaDevuelta);
			}
			else {
				vehiculoReservado= null;
			}
			i+=1;
		}
		return vehiculoReservado;
	}
	public void actualizarEstado2(String estado, Sede sede1,String categoria, LocalDate fechaRecogida, LocalDate fechaDevuelta)
	{
		boolean encontrado=false;
		Vehiculo vehiculoReservado= null;
		int i=0;
		while(encontrado==false && i< ConsolaPrincipal.listaVehiculos.size()) {
			vehiculoReservado= ConsolaPrincipal.listaVehiculos.get(i);
			String cat=vehiculoReservado.getCategoria();
			Sede sedev = vehiculoReservado.getUbi();
			boolean disp= vehiculoReservado.verificarDisponibilidad(fechaRecogida, fechaDevuelta);			
			if(cat.equals(categoria) && sede1.getnombre().equals(sedev.getnombre()) && disp==true) {
				encontrado=true;
				if(estado.equals("Disponible")) {
					vehiculoReservado.desbloquearDisponibilidad(estado,fechaRecogida, fechaDevuelta);
				}
				else {
					vehiculoReservado.bloquearDisponibilidad(estado,fechaRecogida,fechaDevuelta);
				}
			}
			else {
				vehiculoReservado= null;
			}
			i+=1;
		}

	}
}
