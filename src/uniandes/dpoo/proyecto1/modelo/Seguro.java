package uniandes.dpoo.proyecto1.modelo;


public class Seguro
{
	private String nombre;
	private Double precio;

	public Seguro(String nombre,Double precio)
	{
		this.nombre = nombre;
		this.precio = precio;
	}
	
	public String getNombre() {
		return nombre;
		
	}
	
	public Double getPrecio() {
		return precio;
	}
}
