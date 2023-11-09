package uniandes.dpoo.proyecto1.procesamiento;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import uniandes.dpoo.proyecto1.consola.ConsolaPrincipal;
import uniandes.dpoo.proyecto1.modelo.Cliente;
import uniandes.dpoo.proyecto1.modelo.ConductorAdicional;
import uniandes.dpoo.proyecto1.modelo.LicenciaConduccion;
import uniandes.dpoo.proyecto1.modelo.Reserva;
import uniandes.dpoo.proyecto1.modelo.Sede;
import uniandes.dpoo.proyecto1.modelo.Seguro;
import uniandes.dpoo.proyecto1.modelo.Vehiculo;

public class LoaderReservas {
	
public static ArrayList<Reserva> listaReservas = new ArrayList<Reserva>();
	
	public static ArrayList<Reserva> cargarReservas(String archivoReservas) throws IOException, ParseException
	{
		
		BufferedReader br = new BufferedReader(new FileReader(archivoReservas));
		String linea = br.readLine();
		linea = br.readLine();
		while (linea != null)
		{
			String[] partes = linea.split(";");
			String login = partes[0];
			Cliente elCliente = null;
			for (Cliente c: ConsolaPrincipal.listaClientes) {
				String l= c.getLogin();
				if(l.equals(login)) {
					elCliente = c;
				}
			}
			String categoria = partes[1];
			String ubi = partes[2];
			Sede laSede = null;
			for (Sede s: ConsolaPrincipal.listaSedes) {
				String l= s.getnombre();
				if(l.equals(ubi)) {
					laSede = s;
				}
			}
			LocalDate fechaRecogida = LocalDate.parse(partes[3]);
			LocalTime horaRecogida = LocalTime.parse(partes[4]);
			String especial1 = partes[5];
			boolean especial=false;
			if(especial1.equals("1")){
				especial= true;
			}
			LocalDate fechaDevuelta=LocalDate.parse(partes[6]);
			String[] rangoDeHoras1= partes[7].split(",");
			LocalTime horainf=LocalTime.parse(rangoDeHoras1[0]);
			LocalTime horasup=LocalTime.parse(rangoDeHoras1[1]);
			ArrayList<LocalTime> rangoDeHoras = new ArrayList<LocalTime>();
			rangoDeHoras.add(horainf);
			rangoDeHoras.add(horasup);
			String temporada = partes[8];
			String sedeEntrega1 = partes[9];
			Sede laSede2 = null;
			for (Sede s1: ConsolaPrincipal.listaSedes) {
				String l= s1.getnombre();
				if(l.equals(sedeEntrega1)) {
					laSede2 = s1;
				}
			}
			Double precio = Double.parseDouble(partes[10]);
			ArrayList<Seguro> costoSeguros = new ArrayList<Seguro>();
			if(partes[11].equals("*")) {
				
			}
			else {
				String[] seguros =partes[11].split(",");
				int i=0;
				while(i<seguros.length) {
					Seguro elSeguro1;
					for (Seguro s: ConsolaPrincipal.listaSeguros) {
						String l= s.getNombre();
						if(l.equals(seguros[0])) {
							elSeguro1 = s;
							costoSeguros.add(elSeguro1);
						}
					}
				i+=1;
				}
			}
			
			Double costoConductor = Double.parseDouble(partes[12]);
			ArrayList<ConductorAdicional> listaconductores = new ArrayList<ConductorAdicional>();
			
			if(partes[13].equals("*")) {
				
			}
			else {
				String[] conductores =partes[13].split(",");
				int i=0;
				while(i<conductores.length) {
					String[] info= conductores[i].split("&");
					String nombre = info[0];
					String[] contacto1= info[1].split("/");
					ArrayList<String> contacto = new ArrayList<String>();
					contacto.add(contacto1[0]);
					contacto.add(contacto1[1]);
					LocalDate nacimiento=LocalDate.parse(info[2]);
					String nacionalidad = info[3];
					String documento = info[4];
					String[] licencia1= info[5].split("/");
					LicenciaConduccion lic = new LicenciaConduccion(licencia1[0],licencia1[1],licencia1[2]);
					ConductorAdicional elConductor= new ConductorAdicional(nombre,contacto,nacimiento,nacionalidad,documento,lic);
					listaconductores.add(elConductor);
				i+=1;
				}
			}
			Reserva nueva = elCliente.crearReserva(elCliente, categoria,laSede,fechaRecogida,horaRecogida,especial,ConsolaPrincipal.listaVehiculos,fechaDevuelta,rangoDeHoras,temporada,laSede2,precio,costoSeguros,costoConductor,listaconductores);
			listaReservas.add(nueva);
			linea = br.readLine();
		 }
		 br.close();
		 
		 return listaReservas;
	}
	
}
