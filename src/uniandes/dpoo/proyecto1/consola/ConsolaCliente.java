package uniandes.dpoo.proyecto1.consola;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import uniandes.dpoo.proyecto1.consola.ConsolaPrincipal;
import uniandes.dpoo.proyecto1.modelo.Cliente;
import uniandes.dpoo.proyecto1.modelo.ConductorAdicional;
import uniandes.dpoo.proyecto1.modelo.ControladorCliente;
import uniandes.dpoo.proyecto1.modelo.Reserva;
import uniandes.dpoo.proyecto1.modelo.Sede;
import uniandes.dpoo.proyecto1.modelo.Seguro;
import uniandes.dpoo.proyecto1.modelo.Vehiculo;

public class ConsolaCliente
{
	private static String categoria;
	private static String sede;
	private static LocalDate fechaRecogida;
	private static LocalTime horaRecogida;
	private static boolean especial;

	private static LocalDate fechaDevuelta;
	private static ArrayList<LocalTime> rangoDeHoras;
	private static String temporada;
	private static String sedeEntrega;
	private static Double precio;
	private static ArrayList<Seguro> costoSeguros;
	public static Double costoConductorAdicional;
	public static ArrayList<ConductorAdicional> conductoresAdicionales;
	public static Reserva reserva;	
	public static void mostrarConsolaCliente(Cliente elCliente) throws IOException
	{
		boolean continuar = true;
		while (continuar)
		{
		mostrarMenu();
		int opcion = Integer.parseInt(input("Por favor seleccione una opción:"));
		 if (opcion ==1 ) 
		 	{
			 reserva = comenzarReserva(elCliente,ConsolaPrincipal.listaSedes,ConsolaPrincipal.listaVehiculos);
		 }
		 if(opcion ==2) 
		 {
			 if (reserva != null)
			 {
				 ControladorCliente.recogerVehiculo(reserva);
			 }
			 else if(reserva==null) 
			 {
				 	Reserva reserva1 = ControladorCliente.buscarReservaRecoger();
					if(reserva1 ==null)
					 {
						 System.out.println("No haz creado tu reserva. Elige la opcion 1 para crearla");
					 }
			 }
			 
		 }
		 if(opcion ==3) 
		 {
			 if (reserva != null)
			 {
				 ControladorCliente.devolverVehiculo(reserva);
			 }
			 else if(reserva==null) 
			 {
				Reserva reserva2 = ControladorCliente.buscarReservaDevolver();
					if(reserva2 ==null)
					 {
						 System.out.println("No haz creado tu reserva. Elige la opcion 1 para crearla");
					 }
			 }
			 
		 }
		 if(opcion == 4)
		 {
			 continuar = false;
		 }
		}
		
	}
	

	public static void mostrarMenu()
	{
		System.out.println("\n Bienvenido Cliente ");
		System.out.println("\nOpciones de la aplicación\n");
		System.out.println("1. Reservar vehiculo");
		System.out.println("2. Recoger vehiculo");
		System.out.println("3. Devolver vehiculo");
		System.out.println("4. Salir");
	}
	public static Reserva comenzarReserva(Cliente elCliente,ArrayList<Sede> listaSedes,ArrayList<Vehiculo>  listaVehiculos) throws IOException
	{

		System.out.println("\n----------- COMENCEMOS CON TU RESERVA----------- \n");
		categoria = input ("\nElija la categoria de vehiculo desea reservar (Pequeno, SUV, Lujo o VAN): \n");
		System.out.println("\nEstas son las sedes donde puede recoger su vehiculo: \n");
		boolean encontrado1 = false;
		int j = 0;
		while(encontrado1 == false && j<listaSedes.size())
		{	
			Sede sedeReal = listaSedes.get(j);
			String sedes = sedeReal.getnombre();
			System.out.println(sedes);
					j +=1;
		}
					
		Sede sedeRecoger = encontrarsede(listaSedes);

		String respuesta = input("\nDesea ver los seguros adicionales? (SI/NO): \n");
		if (respuesta.equals("SI"))
		{
			especial = true;
			boolean enc = false;
			int a = 0;
			while(enc == false && a<listaSedes.size())
			{	
				Seguro seguro = ConsolaPrincipal.listaSeguros.get(a);
				System.out.println((a+1)+")"+seguro.getNombre()+":$"+seguro.getPrecio());
						a +=1;
			}
			

			String respuesta1 = input("\nDeseas anadir seguros adicionales? (SI/NO): \n");
			if (respuesta1.equals("SI"))
			{
				String seguros= "SI";
				int k=0;
				costoSeguros = new ArrayList<Seguro>() ;
				while(seguros.equals("SI") && k<ConsolaPrincipal.listaSeguros.size()) {
					String seguro = input("\nQue seguro deseas adicionar(1,2,3): \n");
					int num  =Integer.parseInt(seguro);
					Seguro sele = ConsolaPrincipal.listaSeguros.get(num-1);	
					costoSeguros.add(sele);
					seguros = input("\nDeseas anadir otro seguro? (SI/NO): \n");
					k+=1;
				}
				}
		}
		else
		{
			especial = false;
		}
		System.out.println("\nEstas son las sedes donde puede entregar su vehiculo: \n");
		boolean encontrado2 = false;
		int k = 0;
		while(encontrado2 == false && k<listaSedes.size())
		{
			Sede sedeReal = listaSedes.get(k);
			String sedes = sedeReal.getnombre();
			System.out.println(sedes);
			k +=1;
		}
		String sedeEntrega1 = input("\nElija la sede donde quiera entregar su vehiculo: \n");
		boolean encontrado3 = false;
		int l = 0;
		Sede sedeEntrega = null;
		ArrayList<LocalTime> horario1;
		while(encontrado3 == false && l<listaSedes.size())
		{
			sedeEntrega = listaSedes.get(l);
			if (sedeEntrega.getnombre().equals(sedeEntrega1))
			{
				encontrado3 = true;
				horario1 = sedeEntrega.gethorario();
				System.out.println("El horario de atencion de esta sede es:"+ horario1.get(0)+"AM -"+horario1.get(1)+" PM");
				String fechaDevuelta1 = input("\nIndique la fecha en el que se devolvera el vehiculo en el formato AA-MM-DD: \n");
				fechaDevuelta= LocalDate.parse(fechaDevuelta1);	
				boolean enRango = false;
				while(enRango==false) {
					String horainf1 = input("\nEs necesario saber el rango de horas en las que va a devolver el vehiculo, ingrese la hora inferior en formato HH:MM : \n");
					LocalTime horaInf= LocalTime.parse(horainf1);
					String horasup1 = input("\nAhora ingrese la hora superior en formato HH:MM : \n");
					LocalTime horaSup= LocalTime.parse(horasup1);
					if(horaInf.isAfter(horario1.get(0)) && horaSup.isBefore(horario1.get(1))) {
						rangoDeHoras = new ArrayList<LocalTime>() ;
						rangoDeHoras.add(horaInf);
						rangoDeHoras.add(horaSup);
						enRango = true;
					}
					else {
						System.out.println("El rango escogido no esta dentro del horario de atencion de la sede");
					}
					
				}
			}
			l +=1;
					
		}
		costoConductorAdicional =0.0;
		conductoresAdicionales= new ArrayList<ConductorAdicional>();
		reserva = elCliente.crearReserva(elCliente,categoria,sedeRecoger,fechaRecogida,horaRecogida,especial,listaVehiculos,fechaDevuelta,rangoDeHoras,temporada,sedeEntrega,precio,costoSeguros,costoConductorAdicional, conductoresAdicionales);
		if(reserva != null) {
			System.out.println("Su reserva a sido creada con exito, cuando sea la hora adecuada, recoja su vehiculo.");
			Double precioparcial = ControladorCliente.reservaExitosa(categoria,fechaRecogida,fechaDevuelta, temporada, sedeRecoger, sedeEntrega,precio,costoSeguros,costoConductorAdicional);
			System.out.println("Para confirmar su reserva debe pagar el 30% del costo del alquiler. Este corresponde a $:" + precioparcial);
		}
		else {
			System.out.println("No hay vehiculos disponibles con esa categoria, en esas fechas y en esa sede, vuelva a hacer una nueva reserva.");
		}
		return reserva;
	}


	private static Sede encontrarsede(ArrayList<Sede> listaSedes) {
		sede = input("\nElija la sede donde quiera recoger su vehiculo: \n");
		boolean encontrado = false;
		int i = 0;
		ArrayList<LocalTime> horario;
		Sede sedeRecoger = null;
		while(encontrado == false && i<listaSedes.size())
		{
			sedeRecoger = listaSedes.get(i);
			if (sedeRecoger.getnombre().equals(sede))
			{
				encontrado = true;
				horario = sedeRecoger.gethorario();
				System.out.println("El horario de atencion de esta sede es:"+ horario.get(0)+"AM -"+horario.get(1)+" PM");
				String fechaRecogida1 = input("\nIndique la fecha en la que se espera llegar a la agencia en formato AA-MM-DD: \n");
				fechaRecogida= LocalDate.parse(fechaRecogida1);	
				boolean enRango = false;
				while(enRango==false) {
					String horaRecogida1 = input("\nIndique la hora en la que se espera llegar a la agencia en formato HH:MM : \n");
					horaRecogida= LocalTime.parse(horaRecogida1);
					if(horaRecogida.isAfter(horario.get(0)) && horaRecogida.isBefore(horario.get(1))) {
						enRango = true;
					}
					else {
						System.out.println("La hora escogida no esta dentro del horario de atencion de la sede");
					}
					
				}
			}
			i +=1;
				
		}
		return sedeRecoger;
	}

	public static String input(String mensaje)
	{
		try
		{
			System.out.print(mensaje);
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			return reader.readLine();
		}
		catch (IOException e)
		{
			System.out.println("Error leyendo de la consola");
			e.printStackTrace();
		}
		return null;
	}
	
}