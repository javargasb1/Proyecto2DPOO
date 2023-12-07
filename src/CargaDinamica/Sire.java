package CargaDinamica;

public class Sire implements InterfaceTarjetas
{

	private String nombre;


	public Sire(String nombre) {
		this.nombre=nombre;
	}
	


	public String darNombre() {
		
		return (nombre);
	}

}
