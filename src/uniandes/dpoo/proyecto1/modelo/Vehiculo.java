package uniandes.dpoo.proyecto1.modelo;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Vehiculo
{
	private String placa;
	private String marca;
	private String modelo;
	private String color;
	private String tipoTransmision;
	private String categoria;
	private Sede ubicacion;
	private Map<LocalDate, String> estado = new HashMap<>();
	private Double precio;
	private Map<LocalDate, Boolean> disponibilidad = new HashMap<>();
	
	public Vehiculo(String placa,String marca,String modelo,String color,String tipoTransmision,
			String categoria,Sede ubicacion,Double precio)
	{
		this.placa = placa;
		this.marca = marca;
		this.modelo = modelo;
		this.color = color;
		this.tipoTransmision = tipoTransmision;
		this.categoria = categoria;
		this.ubicacion = ubicacion;
		this.precio = precio;
		LocalDate date = LocalDate.now();
		for (int i = 0; i < 730; i++) {
			disponibilidad.put(date.plusDays(i), true);
			estado.put(date.plusDays(i),"Disponible");
		}
	}
	
	public String getPlaca() {

		return placa;
	}
	
	public String getMarca() {

		return marca;
	}
	
	public String getModelo() {

		return modelo;
	}
	
	public String getTipoTransmision() {

		return tipoTransmision;
	}
	
	public String getCategoria() {

		return categoria;
	}
	
	public Sede getUbi() {

		return ubicacion;
	}
	
	public Map<LocalDate, String> getEstado() {

		return estado;
	}
	public Double getPrecio() {

		return precio;
	}
	

	
	public void bloquearDisponibilidad(String estado2,LocalDate fechaRecogida, LocalDate fechaDevuelta)
	{
		for (LocalDate date = fechaRecogida; date.isBefore(fechaDevuelta); date = date.plusDays(1)) 
		{
            disponibilidad.put(date, false);
            estado.put(date, estado2);
        }
		
	}
	
	public void desbloquearDisponibilidad(String estado2, LocalDate fechaRecogida, LocalDate fechaDevuelta)
	{
		for (LocalDate date = fechaRecogida; date.isBefore(fechaDevuelta); date = date.plusDays(1)) 
		{
            disponibilidad.put(date, true);
            estado.put(date, estado2);
        }
		
	}
	
	public boolean verificarDisponibilidad(LocalDate fechaRecogida, LocalDate fechaDevuelta)
	{
		for (LocalDate date = fechaRecogida; date.isBefore(fechaDevuelta); date = date.plusDays(1)) 
		{
            if (!disponibilidad.get(date)) 
            {
                return false;
            }
        }
        return true;
		
	}

	public String getColor() {
		return color;
	}


	
}