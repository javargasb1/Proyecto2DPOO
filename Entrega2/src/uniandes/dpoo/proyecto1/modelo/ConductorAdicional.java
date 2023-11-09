package uniandes.dpoo.proyecto1.modelo;
import java.time.LocalDate;
import java.util.ArrayList;

public class ConductorAdicional 
{
	private String nombre;
	private ArrayList<String> datosContacto;
	private LocalDate fechaNacimiento;
	private String nacionalidad;
	private String imagenDocumento;
	private LicenciaConduccion licencia;
	
	public ConductorAdicional(String nombre, ArrayList<String> datosContacto, LocalDate nacimiento, 
			String nacionalidad, String imagenDocumento,LicenciaConduccion licencia)
	{
		this.nombre = nombre;
		this.datosContacto = datosContacto;
		this.fechaNacimiento = nacimiento;
		this.nacionalidad = nacionalidad;
		this.imagenDocumento = imagenDocumento;
		this.licencia = licencia;
		
	}

	public String getNombre() {
		return nombre;
	}
	public ArrayList<String> getContacto() {
		return datosContacto;
	}
	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}
	public String getNacionalidad() {
		return nacionalidad;
	}
	public String getDoc() {
		return imagenDocumento;
	}
	public LicenciaConduccion getLicencia() {
		return licencia;
	}
	
}
