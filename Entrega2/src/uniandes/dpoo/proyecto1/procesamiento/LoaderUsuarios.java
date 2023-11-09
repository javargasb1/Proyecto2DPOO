package uniandes.dpoo.proyecto1.procesamiento;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import uniandes.dpoo.proyecto1.modelo.*;

public class LoaderUsuarios
{
	public static HashMap<String,String> usuariosClientes;
	public static HashMap<String,String> usuariosEmpleados;
	public static ArrayList<Usuario> listaEmpleados = new ArrayList<Usuario>();
	public static ArrayList<Cliente> listaClientes = new ArrayList<Cliente>();
	
	
	private static HashMap<String,String> cargarClientes(String archivoClientes) throws IOException
	{
		 usuariosClientes = new HashMap<String,String>();
		 
		 BufferedReader br = new BufferedReader(new FileReader(archivoClientes));
		 String linea = br.readLine();
		 linea = br.readLine();
		 while (linea != null)
		 {
			 String[] partes = linea.split(":");
			 String usuarioCliente = partes[0];
			 String contraseñaCliente = partes[1];
			 String nombre = partes[2];
			 String[] contacto1 = partes[3].split(",");
			 ArrayList<String> contacto = new ArrayList<String>();
			 contacto.add(contacto1[0]);
			 contacto.add(contacto1[1]);
			 LocalDate nacimiento = LocalDate.parse(partes[4]);
			 String nacionalidad = partes[5];
			 String documento = partes[6];
			 String[] licencia1 = partes[7].split(",");
			 LicenciaConduccion licencia = new LicenciaConduccion(licencia1[0], licencia1[1],licencia1[2]);
			 String[] pago1 = partes[8].split(",");
			 ArrayList<String> pago = new ArrayList<String>();
			 pago.add(pago1[0]);
			 pago.add(pago1[1]);
			 
			 usuariosClientes.put(usuarioCliente, contraseñaCliente);
			 Cliente nuevo = new Cliente(nombre, usuarioCliente, contraseñaCliente, contacto, nacimiento, nacionalidad, documento, licencia, pago);
			 listaClientes.add(nuevo);
			 linea = br.readLine();
		 }
		 br.close();
		 return usuariosClientes;
	}
	
	private static HashMap<String,String> cargarEmpleados(String archivoEmpleados) throws IOException
	{
		usuariosEmpleados = new HashMap<String,String>();
		
		BufferedReader br = new BufferedReader(new FileReader(archivoEmpleados));
		String linea = br.readLine();
		linea = br.readLine();
		while (linea != null)
		{
			String[] partes = linea.split(":");
			String usuarioEmpleado = partes[0];
			String contraseñaEmpleado = partes[1];
			String tipoEmpleado = partes[2];
			String nombre = partes[3];
			String ubicacion = partes[4];
			Usuario nuevo = new AdministradorLocal("","","","");
			if (tipoEmpleado.equals("AdministradorLocal")) {
			nuevo = new AdministradorLocal(usuarioEmpleado,contraseñaEmpleado,nombre,ubicacion);
			}
			else if (tipoEmpleado.equals("ActualizadorEstadoVehiculo")) {
			nuevo = new ActualizadorEstadoVehiculo(usuarioEmpleado,contraseñaEmpleado,nombre,ubicacion);
			}
			listaEmpleados.add(nuevo);
			usuariosEmpleados.put(usuarioEmpleado, contraseñaEmpleado);
			linea = br.readLine();
		}
		br.close();
		
		return usuariosEmpleados;
	}
	
	public static HashMap<String,String> getUsuariosClientes(String archivoClientes) throws IOException
	{
		cargarClientes(archivoClientes);
		return usuariosClientes;
	}
	
	public static HashMap<String,String> getUsuariosEmpleados(String archivoEmpleados) throws IOException
	{
		cargarEmpleados(archivoEmpleados);
		return usuariosEmpleados;
	}
	
	public static ArrayList<Usuario> getListaEmpleados()
	{
		return listaEmpleados;
	}
	
	public static ArrayList<Cliente> getListaClientes()
	{
		return listaClientes;
	}
}