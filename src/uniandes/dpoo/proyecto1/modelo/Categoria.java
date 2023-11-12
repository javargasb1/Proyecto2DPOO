package uniandes.dpoo.proyecto1.modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import static java.time.temporal.ChronoUnit.DAYS;


public class Categoria
{
	private String categoria;
	private static LocalDate fechaRecogida;
	private static LocalDate fechaDevuelta;
	private String temporada;
	private Sede sedeRecoger;
	private Sede sedeEntrega;
	private static Double precio;
	private static ArrayList<Seguro> costoSeguros;
	private static Double costoConductorAdicional;
	
	public Categoria (String categoria,LocalDate fechaRecogida,LocalDate fechaDevuelta,String temporada,
			Sede sedeRecoger, Sede sedeEntrega,Double precio,ArrayList<Seguro> costoSeguros,
			Double costoConductorAdicional)
	{
		this.categoria = categoria;
		this.fechaRecogida = fechaRecogida;
		this.fechaDevuelta = fechaDevuelta;
		this.temporada = temporada;
		this.sedeRecoger = sedeRecoger;
		this.sedeEntrega = sedeEntrega;
		this.precio = precio;
		this.costoSeguros = costoSeguros;
		this.costoConductorAdicional = costoConductorAdicional;
		
	}
	
	public String getCategoria()
	{
		return categoria;
	}
	
	public LocalDate getfechaRecogida()
	{
		return fechaRecogida;
	}
	
	public LocalDate getfechaDevuelta()
	{
		return fechaDevuelta;
	}
	
	public Sede getsedeRecoger()
	{
		return sedeRecoger;
	}
	
	public Sede getsedeEntrega()
	{
		return sedeEntrega;
	}
	
	public Double getprecio()
	{
		return precio;
	}
	
	public ArrayList<Seguro> getcostoSeguros()
	{
		return costoSeguros;
	}
	
	public Double getcostoConductorAdicional()
	{
		return costoConductorAdicional;
	}
	
	public Double pago30porciento(String categoria,LocalDate fechaRecogida,LocalDate fechaDevuelta,String temporada,
			Sede sedeRecoger, Sede sedeEntrega,Double precio,ArrayList<Seguro> costoSeguros,
			Double costoConductorAdicional)
	{
		Double precioparcial = 0.0;
		if(temporada.equals("Baja"))
		{
			precioparcial+=precio;
		}
		else
		{
			precioparcial+=10%precioparcial;
		}
		
		Double excedente = 20%precio;
		if(sedeRecoger.equals(sedeEntrega))
		{
			precioparcial=precioparcial;
		}
		else
		{
			precioparcial+=excedente;
		}

		if(costoSeguros!=null)
		{
			boolean encontrado = false;
			int i = 0;
			Seguro costo=null;
			while(encontrado == false && i<costoSeguros.size())
			{
				costo = costoSeguros.get(i);
				Double costo1 = costo.getPrecio();
				precioparcial+=costo1;
				i +=1;
					
			}
		}
		
		long numdias = DAYS.between(fechaDevuelta, fechaRecogida);
		precioparcial+=30%precioparcial*numdias;
		return precioparcial;
	}
	public Double precioFinal(String categoria,LocalDate fechaRecogida,LocalDate fechaDevuelta,String temporada,
			Sede sedeRecoger, Sede sedeEntrega,Double precio,ArrayList<Seguro> costoSeguros,
			Double costoConductorAdicional)
	{
		Double precioparcial = pago30porciento(categoria, fechaRecogida, fechaDevuelta, temporada,
				 sedeRecoger,  sedeEntrega, precio, costoSeguros,costoConductorAdicional);
		Double precioFinal = (precioparcial*70)/30;
		precioFinal+=costoConductorAdicional;
		
		return precioFinal;
	}
	
}