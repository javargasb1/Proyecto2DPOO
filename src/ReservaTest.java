import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.proyecto1.interfaz.InterfazCliente;
import uniandes.dpoo.proyecto1.modelo.ConductorAdicional;
import uniandes.dpoo.proyecto1.modelo.ControladorCliente;
import uniandes.dpoo.proyecto1.modelo.Reserva;
import uniandes.dpoo.proyecto1.modelo.Sede;
import uniandes.dpoo.proyecto1.modelo.Seguro;
import uniandes.dpoo.proyecto1.modelo.Sistema;
import uniandes.dpoo.proyecto1.modelo.Usuario;
import uniandes.dpoo.proyecto1.modelo.Vehiculo;
import uniandes.dpoo.proyecto1.modelo.Cliente;

class ReservaTest 
{
	/* Casos de error:
	1. No hay vehiculos
	2. Fecha sin sentido
	3. Sede inexistente
	4. Hora Fuera Horario
	*/
	
	private Cliente usuario;
	private String categoria;
	private Sede sedeRecoger;
	private LocalDate fechaRecogida;
	private LocalTime horaRecogida;
	private boolean especial;
	private ArrayList<Vehiculo> listaVehiculos;
	private LocalDate fechaDevolucion;
	private ArrayList<LocalTime> rangoDeHoras;
	private String temporada;
	private Sede sedeEntrega;
	private Double precio;
	private ArrayList<Seguro> costoSeguros;
	private Double costoConductorAdicional;
	private ArrayList<ConductorAdicional> conductoresAdicionales;
	
	
	public void setUp() throws IOException, ParseException
	{
		usuario = (Cliente) Sistema.iniciarSesion("cliente1", "claveCliente1", 1);
		categoria = "SUV";
		sedeRecoger = Sistema.listaSedes.get(0);
		fechaRecogida = LocalDate.parse("2023-12-24");
		horaRecogida = LocalTime.parse("13:00");
		especial = false;
		listaVehiculos = Sistema.listaVehiculos;
		fechaDevolucion = LocalDate.parse("2023-12-26");
		LocalTime horaEntregainf = LocalTime.parse("12:00");
        LocalTime horaEntregasup = LocalTime.parse("15:00");
        ArrayList<LocalTime> rangoDeHoras = new ArrayList<LocalTime>() ;
		rangoDeHoras.add(horaEntregainf);
		rangoDeHoras.add(horaEntregasup);
		this.rangoDeHoras = rangoDeHoras;
		temporada = null;
		sedeEntrega = Sistema.listaSedes.get(0);
		precio = 985431.0;
		costoSeguros = null;
		costoConductorAdicional =0.0;
		conductoresAdicionales= new ArrayList<ConductorAdicional>();
	}
	
	public void crearReserva() throws IOException
	{
		InterfazCliente.setUsuario(usuario);
		InterfazCliente.crearReserva(categoria,sedeRecoger,fechaRecogida,
				horaRecogida,especial,Sistema.listaVehiculos,fechaDevolucion,
				rangoDeHoras,temporada,sedeEntrega,precio,costoSeguros,costoConductorAdicional,
				conductoresAdicionales);
	}
	@Test
	void testFaltaVehiculo() throws IOException, ParseException 
	{
		setUp();
		crearReserva();
		Reserva reserva = usuario.crearReserva(usuario, categoria, sedeRecoger,
				fechaRecogida, horaRecogida, especial, listaVehiculos,
				fechaDevolucion, rangoDeHoras, temporada, sedeEntrega,
				precio, costoSeguros, costoConductorAdicional, conductoresAdicionales);
		assertNull(reserva);
	}
	
	@Test 
	void testFechaMala() throws IOException, ParseException
	{
		setUp();
		fechaRecogida = LocalDate.parse("2022-12-24");
		Exception exception = assertThrows(NullPointerException.class,() -> usuario.crearReserva(usuario, categoria, sedeRecoger,
				fechaRecogida, horaRecogida, especial, listaVehiculos,
				fechaDevolucion, rangoDeHoras, temporada, sedeEntrega,
				precio, costoSeguros, costoConductorAdicional, conductoresAdicionales));
	}
	
	@Test 
	void testSedeInexistente() throws IOException, ParseException
	{
		setUp();
		sedeRecoger = null;
		Exception exception = assertThrows(NullPointerException.class,() -> usuario.crearReserva(usuario, categoria, sedeRecoger,
				fechaRecogida, horaRecogida, especial, listaVehiculos,
				fechaDevolucion, rangoDeHoras, temporada, sedeEntrega,
				precio, costoSeguros, costoConductorAdicional, conductoresAdicionales));
	}
	
	@Test
	void testHoraFueraHorario() throws IOException, ParseException
	{
		setUp();
		horaRecogida = LocalTime.parse("05:00");
		Exception exception = assertThrows(NullPointerException.class,() -> usuario.crearReserva(usuario, categoria, sedeRecoger,
				fechaRecogida, horaRecogida, especial, listaVehiculos,
				fechaDevolucion, rangoDeHoras, temporada, sedeEntrega,
				precio, costoSeguros, costoConductorAdicional, conductoresAdicionales));
		// Este error pasa sin problema por lo que se debe corregir.
		// Aunque en la interfaz se le aclara al Cliente los horarios.
	}
}
