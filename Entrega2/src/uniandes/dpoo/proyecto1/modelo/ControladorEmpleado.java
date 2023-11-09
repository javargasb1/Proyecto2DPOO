package uniandes.dpoo.proyecto1.modelo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import uniandes.dpoo.proyecto1.consola.ConsolaPrincipal;

public class ControladorEmpleado
{
	private static String placa;
	private static String marca;
	private static String modelo;
	private static String color;
	private static String tipoTransmision;
	private static String categoria;
	private static String precio;
	
	public static void mostrarConsolaEmpleado(Usuario elEmpleado) throws IOException
	{
		String trabajo = elEmpleado.getWork();
	
		if(trabajo.equals("AdministradorLocal")) 
		{
		mostrarMenuAdmin();
		}
		if(trabajo.equals("ActualizadorEstadoVehiculo")) 
		{
		mostrarMenuAct();
		}
	}
	
	public static void mostrarMenuAdmin() throws IOException
	{
		boolean seguir = true;
		while (seguir)
		{
		System.out.println("\n Bienvenido Empleado ");
		System.out.println("\nOpciones de la aplicación\n");
		System.out.println("1. Registrar la compra de nuevos vehículos");
		System.out.println("2. Dar de baja a un vehiculo");
		System.out.println("3. Salir");
		int opcion1 = Integer.parseInt(input("Por favor seleccione una opción:"));
		 if (opcion1 ==1 ) 
		 {
			 registrarCompra();
		 }
		 if (opcion1 ==2)
		 {
			 darBaja();
		 }
		 if (opcion1 ==3)
		 {
			 seguir = false; 
		 }
		}
	}
	
	
	public static void mostrarMenuAct()
	{
		boolean continuar = true;
		while (continuar)
		{
		System.out.println("\n Bienvenido Actualizador ");
		System.out.println("\nOpciones de la aplicación\n");
		System.out.println("1. Actualizar estado de vehiculo");
		System.out.println("2. Salir");
		int opcion1 = Integer.parseInt(input("Por favor seleccione una opción:"));
		 if (opcion1 ==1 ) 
		 {
			  String placa =input("Por favor seleccione el vehiculo del que quiere actualizar el estado:");
			  int i=0;
			  boolean encontrado =false;
			  Vehiculo elVehiculo = null;
			  while(encontrado==false && i<ConsolaPrincipal.listaVehiculos.size()) {
				  Vehiculo v = ConsolaPrincipal.listaVehiculos.get(i);
				  if((v.getPlaca()).equals(placa)) {
					  elVehiculo =v;
					  encontrado=true;
				  }
				  
			  }
			  if(elVehiculo != null) {
				  Sede sedeReal = elVehiculo.getUbi();
				  ArrayList<Usuario> listaempleados = sedeReal.getEmpleados();
				  boolean enc = false;
				  int j = 0;
				  Usuario empleadoreal = null;
				  Usuario empleado;
				  while(enc == false && j<listaempleados.size())
					{
						empleado = listaempleados.get(j);
						if (empleado.getWork().equals("ActualizadorEstadoVehiculo"))
						{
							enc = true;
							empleadoreal = empleado;
						}
						j +=1;
							
					}
					ActualizadorEstadoVehiculo actualizar = new ActualizadorEstadoVehiculo(empleadoreal.getLogin(), empleadoreal.getClave(), empleadoreal.getNombre(), empleadoreal.getUbi());
					boolean cont=true;
					while(cont==true) {
						System.out.println("\nOpciones de la actualizacion\n");
						System.out.println("1. Bloquear/Desbloquear disponibilidad");
						System.out.println("2. Salir");
						int opcion2 = Integer.parseInt(input("Por favor seleccione una opción:"));
						if(opcion2==1) {
							LocalDate fechaRecogida = LocalDate.parse(input("Por favor seleccione la fecha incial para bloquear la disponibilidad en formato AAAA-MM-DD:"));
							LocalDate fechaDevuelta = LocalDate.parse(input("Por favor seleccione la fecha final para bloquear la disponibilidad en formato AAAA-MM-DD:"));
							String estado = input("Por favor ingrese el estado del vehiculo para estas fechas (Limpieza/Disponible/Alquilado):");
							actualizar.actualizarEstado2(estado,sedeReal, categoria, fechaRecogida, fechaDevuelta);
							System.out.println("El estado del vehiculo a sido actualizado"); 
						}
						else  {
							cont=false;
						}
					}
			  }
			  else {
				  System.out.println("El vehiculo no esta registrado"); 
			  }
		 }
		 if (opcion1 ==2)
		 {
			 continuar=false;
		 }
		
		
		}
	}
	
	public static String input(String mensaje)
	{
		try
		{
			System.out.print(mensaje );
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			return reader.readLine();
		}
		catch (IOException e)
		{
			System.out.println("Error leyendo de la consola");
			e.printStackTrace();
		}
		return null;
	}
	
	public static void registrarCompra() throws IOException 
	{
		System.out.println("\n----------- COMENCEMOS A REGISTAR LA COMPRA ----------- ");
		placa = input("\nIngresa la placa del nuevo vehiculo:");
		marca = input("\nIngresa la marca del nuevo vehiculo:");
		modelo = input("\nIngresa el modelo del nuevo vehiculo:");
		color =  input("\nIngresa el color del nuevo vehiculo:");
		tipoTransmision = input("\nIngresa el tipo de transmicion del nuevo vehiculo (Hidraulico, Mecanico, Electrico):");
		categoria = input("\nIngresa la categoria a la que pertenece del nuevo vehiculo (Pequeno,Lujo,SUV,VAN):");
		String ubicacion1 =  input("\nIngresa la ubicacion del nuevo vehiculo:");
		boolean encontrado2 = false;
		int q = 0;
		Sede ubicacion = null;
		while(encontrado2 == false && q<ConsolaPrincipal.listaSedes.size())
		{
			ubicacion = ConsolaPrincipal.listaSedes.get(q);
			if (ubicacion.getnombre().equals(ubicacion1))
			{
				encontrado2 = true;
			}
			q +=1;
		}
		precio =  input("\nIngresa el precio de alquiler del nuevo vehiculo:");
		Double precioreal = Double.parseDouble(precio);
		AdministradorLocal.nuevoVehiculo(placa,marca,modelo, color, tipoTransmision, categoria, ubicacion, precioreal);
		System.out.println("\nEL VEHICULO HA SIDO REGISTRADO EXITOSAMENTE ");
	
	}
	
	
	public static void darBaja() throws IOException
	{
		System.out.println("\n----------- VAMOS A DAR DE BAJA A UN VEHICULO ----------- ");
		String vehiculobaja = input("\nIngresa la placa del vehiculo que vamos a dar de baja:");
		AdministradorLocal.darBajaVehiculo(vehiculobaja);
		
	}

}