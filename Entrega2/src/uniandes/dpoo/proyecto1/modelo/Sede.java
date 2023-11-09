package uniandes.dpoo.proyecto1.modelo;

import java.time.LocalTime;
import java.util.ArrayList;

public class Sede
{
	private String nombre;
	private String ubicacion;
	private ArrayList<LocalTime> horario;
	private static ArrayList<Usuario> empleados;
	
	public Sede(String nombre,String ubicacion, ArrayList<LocalTime> horario,
			ArrayList<Usuario> empleados)
	{
		this.nombre = nombre;
		this.ubicacion = ubicacion;
		this.horario = horario;
		this.empleados = empleados;
	}
	
	public String getnombre() {

		return nombre;
	}
	
	public String getUbi() {

		return ubicacion;
	}
	
	public ArrayList<LocalTime> gethorario() {

		return horario;
	}
	public ArrayList<Usuario> getEmpleados() {

		return empleados;
	}
}