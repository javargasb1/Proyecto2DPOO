package uniandes.dpoo.proyecto1.modelo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;

public class Reserva
{
	private String categoria;
	private Sede sede;
	private LocalDate fechaRecogida;
	private LocalTime horaRecogida;
	private boolean especial;
	private ArrayList<Vehiculo>  listaVehiculos;
	private ArrayList<LocalTime>  rangoDeHoras;
	private LocalDate fechaDevuelta;
	private String temporada;
	private Sede sedeEntrega;
	private Double precio;
	private ArrayList<Seguro> costoSeguros;
	private Double costoConductorAdicional;
	private ArrayList<ConductorAdicional> conductoresAdicionales;
	private Cliente elCliente;
	private Vehiculo elVehiculo;
	
	
	public Reserva(Cliente elCliente,String categoria,Sede sede,LocalDate fechaRecogida,LocalTime horaRecogida,boolean especial,ArrayList<Vehiculo>  listaVehiculos,LocalDate fechaDevuelta, ArrayList<LocalTime>  rangoDeHoras,String temporada,Sede sedeEntrega,Double precio,ArrayList<Seguro> costoSeguros,Double costoConductorAdicional, ArrayList<ConductorAdicional> conductoresAdicionales, Vehiculo vehiculoReservado)
	{
		this.elCliente = elCliente;
		this.categoria = categoria;
		this.sede = sede;
		this.fechaRecogida = fechaRecogida;
		this.horaRecogida = horaRecogida;
		this.especial = especial;
		this.listaVehiculos = listaVehiculos;
		this.rangoDeHoras = rangoDeHoras;
		this.fechaDevuelta = fechaDevuelta;
		this.temporada = temporada;
		this.sedeEntrega = sedeEntrega;
		this.precio = precio;
		this.costoSeguros = costoSeguros;
		this.costoConductorAdicional = costoConductorAdicional;
		this.conductoresAdicionales = conductoresAdicionales;
		this.elVehiculo= vehiculoReservado;
		
	}

	public  Cliente getcliente()
	{
		return elCliente;
	}
	
	public String getcategoria()
	{
		return categoria;
	}
	
	public Sede getsede()
	{
		return sede;
	}
	
	public LocalDate getfechaRecogida()
	{
		return fechaRecogida;
	}
	
	public LocalTime gethoraRecogida()
	{
		return horaRecogida;
	}
	
	public ArrayList<LocalTime> getrangoHoras()
	{
		return rangoDeHoras;
	}
	
	public LocalDate getfechaDevuelta()
	{
		return fechaDevuelta;
	}
	public String getTemporada()
	{
		return temporada;
	}
	public ArrayList<Vehiculo>  getlistaVehiculos()
	{
		return listaVehiculos;
	}
	
	
	public void actualizarTemporada(LocalDate fechaRecogida) {
		Month mes = fechaRecogida.getMonth();
		int mesletra = mes.getValue();
		if (mesletra == 6 || mesletra == 7|| mesletra == 12 || mesletra == 1)
		{
		//Se encuentra si el mes para hacer la reserva es junio, julio, diciembre o enero para saber si es temporada alta
			temporada = "Alta";
		}
		else
		{
			temporada = "Baja";
		}
		
	}

	public void actualizarConductores(Double costoConductorAdicional2,
			ArrayList<ConductorAdicional> conductoresAdicionales2) {
		costoConductorAdicional=costoConductorAdicional2;
		conductoresAdicionales=conductoresAdicionales2;
		
	}

	public Vehiculo getVehiculo() {
		return elVehiculo;
		
	}

	public ArrayList<LocalTime> gethorasDevuelta() {
		return rangoDeHoras;
	}
	public Sede getsedeEntrega()
	{
		return sedeEntrega;
	}

	public String getEspecial() {
		String esp="0";
		if (especial==true) {
			esp="1";
		}
		return esp;
	}

	public Double getPrecio() {
		return precio;
	}

	public ArrayList<Seguro> getCostosSeguro() {
		// TODO Auto-generated method stub
		return costoSeguros;
	}

	public Double getCostoConductorAdicional() {
		// TODO Auto-generated method stub
		return costoConductorAdicional;
	}

	public ArrayList<ConductorAdicional> getConductores() {
		// TODO Auto-generated method stub
		return conductoresAdicionales;
	}

	public void actualizarPrecio() {
		precio = elVehiculo.getPrecio();
		
	}
	
}