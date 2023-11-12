package uniandes.dpoo.proyecto1.procesamiento;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import uniandes.dpoo.proyecto1.modelo.Sede;
import uniandes.dpoo.proyecto1.modelo.Vehiculo;

public class LoaderInventario
{
	
public static ArrayList<Vehiculo> listaVehiculos = new ArrayList<Vehiculo>();
	
	public static ArrayList<Vehiculo> cargarInventario(String archivoInventario, ArrayList<Sede> listaSedes) throws IOException
	{
		
		BufferedReader br = new BufferedReader(new FileReader(archivoInventario));
		String linea = br.readLine();
		linea = br.readLine();
		while (linea != null)
		{
			String[] partes = linea.split(":");
			String placa = partes[0];
			String marca = partes[1];
			String modelo = partes[2];
			String color = partes[3];
			String tipoTransmision = partes[4];
			String categoria = partes[5];
			String ubicacion1 = partes[6];
			Sede sedeActual= listaSedes.get(0);
			for (Sede sede: listaSedes) {
				String ubicacionSede = sede.getnombre();
				if(ubicacionSede.equals(ubicacion1)) {
					sedeActual = sede;
				}
			}
			String precio = partes[7];
			Double precioreal = Double.parseDouble(precio);
			Vehiculo nuevo = new Vehiculo(placa, marca, modelo, color, tipoTransmision,categoria, sedeActual,precioreal);
			listaVehiculos.add(nuevo);
			linea = br.readLine();
			
			}
			
		 br.close();
		 
		 return listaVehiculos;
	}
	
}