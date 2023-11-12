package uniandes.dpoo.proyecto1.consola;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;

import uniandes.dpoo.proyecto1.modelo.*;
import uniandes.dpoo.proyecto1.procesamiento.*;


public class ConsolaPrincipal {
	public Sistema sistema = new Sistema();
	public static HashMap<String,String> usuariosClientes;
	public static HashMap<String,String> usuariosEmpleados;
	public static ArrayList<Usuario> listaEmpleados;
	public static ArrayList<Cliente> listaClientes;
	public static ArrayList<Sede> listaSedes;
	public static ArrayList<Vehiculo> listaVehiculos;
	public static ArrayList<Seguro> listaSeguros;
	public static ArrayList<Reserva> listaReservas;
	
	public static void main(String[] args) throws IOException, ParseException {
		ConsolaPrincipal consola = new ConsolaPrincipal();
		consola.cargarDatos();
		System.out.println(usuariosClientes);
		consola.ejecutarConsolaPrincipal();
		
	}
	
	public void cargarDatos() throws IOException, ParseException {
		String archivoClientes = "data/archivoClientes.txt";
		String archivoEmpleados = "data/archivoEmpleados.txt";
		String archivoSedes = "data/archivoSedes.txt";
		String archivoVehiculos = "data/archivoInventario.txt";
		String archivoSeguros = "data/archivoSeguros.txt";
		String archivoReservas = "data/archivoReservas.txt";
		usuariosClientes = LoaderUsuarios.getUsuariosClientes(archivoClientes);
		usuariosEmpleados = LoaderUsuarios.getUsuariosEmpleados(archivoEmpleados);
		listaEmpleados = LoaderUsuarios.getListaEmpleados();
		listaClientes = LoaderUsuarios.getListaClientes();
		listaSedes = LoaderSedes.cargarSedes(archivoSedes,listaEmpleados);
		listaVehiculos = LoaderInventario.cargarInventario(archivoVehiculos,listaSedes);
		listaSeguros= LoaderSeguros.cargarSeguros(archivoSeguros);
		listaReservas= LoaderReservas.cargarReservas(archivoReservas);
	}
	
	public void ejecutarConsolaPrincipal() throws IOException {
		boolean continuar= true;
		 System.out.println("Bienvenido al Alquiler de Carros !\n");
		 while (continuar) {
			 mostrarMenuInicial();
			 int opcion = Integer.parseInt(input("Por favor seleccione una opción:"));
			 if (opcion ==1 ) {
				 sistema.crearCuenta();
			 }
			 else if (opcion ==2) {
				 sistema.iniciarSesion();
			 }
			 else if (opcion ==3 ) {
				 continuar=false;
			 }
		 }
	}

	public void mostrarMenuInicial() {
		System.out.println("Porfavor Seleccione: \n1. Crear Cuenta");
		System.out.println("2. Iniciar Sesion");
		System.out.println("3. Cerrar Aplicacion"); 
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


	
}