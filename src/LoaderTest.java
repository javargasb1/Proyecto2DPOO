import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.proyecto1.modelo.Sede;
import uniandes.dpoo.proyecto1.procesamiento.LoaderInventario;
import uniandes.dpoo.proyecto1.procesamiento.LoaderReservas;
import uniandes.dpoo.proyecto1.procesamiento.LoaderSedes;
import uniandes.dpoo.proyecto1.procesamiento.LoaderUsuarios;

class LoaderTest {
	
	private final LoaderInventario loaderInventario = new LoaderInventario();
	private final LoaderReservas loaderReservas = new LoaderReservas();
	private final LoaderSedes loaderSedes = new LoaderSedes();
	private final LoaderUsuarios loaderUsuarios = new LoaderUsuarios();

	
	@Test
	void testLoaderInventarioException()
	{
		// Probamos que se genere una excpeción cuando no encuentre el archivo
		Exception exception1 = assertThrows(IOException.class, () -> loaderInventario.cargarInventario("ArchivoFalso",null));
		// Probamos se genere una excepción cuando la lista de sedes esta vacia
		Exception exception2 = assertThrows(RuntimeException.class, () -> loaderInventario.cargarInventario("data/archivoInventario.txt",new ArrayList<Sede>()));
		
		
	}
	
	@Test
	void testLoaderReservasException()
	{
		// Probamos que se genere una excpeción cuando no encuentre el archivo
		Exception exception1 = assertThrows(IOException.class, () -> loaderReservas.cargarReservas("ArchivoFalso"));
	}
	
	@Test
	void testLoaderSedesException()
	{
		// Probamos que se genere una excpeción cuando no encuentre el archivo
		Exception exception1 = assertThrows(IOException.class, () -> loaderSedes.cargarSedes("ArchivoFalso", null));
		Exception exception2 = assertThrows(RuntimeException.class, () -> loaderInventario.cargarInventario("data/archivoInventario.txt",new ArrayList<Sede>()));
	}
	
	@Test
	void testLoaderSegurosException()
	{
		// Probamos que se genere una excpeción cuando no encuentre el archivo
		Exception exception1 = assertThrows(IOException.class, () -> loaderUsuarios.getUsuariosClientes("ArchivoFalso"));
		Exception exception2 = assertThrows(IOException.class, () -> loaderUsuarios.getUsuariosEmpleados("ArchivoFalso"));
	}
	
	
}
