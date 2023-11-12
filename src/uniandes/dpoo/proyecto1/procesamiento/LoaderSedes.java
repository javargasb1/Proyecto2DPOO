package uniandes.dpoo.proyecto1.procesamiento;

import java.io.BufferedReader;
import java.text.ParseException;
import java.time.LocalTime;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import uniandes.dpoo.proyecto1.modelo.*;

public class LoaderSedes
{
	public static ArrayList<Sede> listaSedes = new ArrayList<Sede>();
	
	public static ArrayList<Sede> cargarSedes(String archivoSedes, ArrayList<Usuario> listaEmpleados) throws IOException, ParseException
	{
		
		BufferedReader br = new BufferedReader(new FileReader(archivoSedes));
		String linea = br.readLine();
		linea = br.readLine();
		while (linea != null)
		{
			String[] partes = linea.split(";");
			String nombre = partes[0];
			String ubicacion = partes[1];
			String[] rangoHoras = partes[2].split("-");
			LocalTime horario1 = LocalTime.parse(rangoHoras[0]);
			//System.out.println(horario1);
			LocalTime horario2 = LocalTime.parse(rangoHoras[1]);
			ArrayList<LocalTime> horario= new ArrayList<LocalTime>();
			horario.add(horario1);
			horario.add(horario2);
			
			ArrayList<Usuario> empleadosSede= new ArrayList<Usuario>();
			for (Usuario empleado: listaEmpleados) {
				String ubicacionEmpleado = empleado.getUbi();
				if(ubicacionEmpleado.equals(ubicacion)) {
					empleadosSede.add(empleado);
				}
			}
			Sede nueva = new Sede(nombre, ubicacion, horario, empleadosSede);
			listaSedes.add(nueva);
			linea = br.readLine();
		 }
		 br.close();
		 
		 return listaSedes;
	}
	
	
	
}