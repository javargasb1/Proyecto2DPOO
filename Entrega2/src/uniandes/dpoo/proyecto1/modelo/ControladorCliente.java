package uniandes.dpoo.proyecto1.modelo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import uniandes.dpoo.proyecto1.consola.ConsolaPrincipal;

public class ControladorCliente
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
				 recogerVehiculo(reserva);
			 }
			 else if(reserva==null) 
			 {
				 	boolean buscar = false;
					int b = 0;
					boolean buscar1 = false;
					int b1 = 0;
					Cliente cliente1 =null;
					Reserva reserva1 = null;
					while(buscar == false && b<ConsolaPrincipal.listaClientes.size())
						
					{	
						Cliente cliente = ConsolaPrincipal.listaClientes.get(b);
						while(buscar1 == false && b1<ConsolaPrincipal.listaReservas.size())
						{	
							reserva1 = ConsolaPrincipal.listaReservas.get(b);
							cliente1 = reserva1.getcliente();
							System.out.println(reserva1.getCostoConductorAdicional());
							b1+=1;
							if(cliente1.equals(cliente))
							{
								recogerVehiculo(reserva1);
								buscar1=true;
								buscar=true;
							}
						}
					b +=1;
					}
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
				 devolverVehiculo(reserva);
			 }
			 else if(reserva==null) 
			 {
				 	boolean buscar = false;
					int b = 0;
					boolean buscar1 = false;
					int b1 = 0;
					Cliente cliente2 =null;
					Reserva reserva2 = null;
					while(buscar == false && b<ConsolaPrincipal.listaClientes.size())
						
					{	
						Cliente cliente = ConsolaPrincipal.listaClientes.get(b);
						while(buscar1 == false && b1<ConsolaPrincipal.listaReservas.size())
						{	
							reserva2 = ConsolaPrincipal.listaReservas.get(b);
							cliente2 = reserva2.getcliente();
							System.out.println(reserva2.getCostoConductorAdicional());
							b1+=1;
							if(cliente2.equals(cliente))
							{
								devolverVehiculo(reserva2);
								buscar1=true;
								buscar=true;
							}
						}
					b +=1;
					}
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
			reserva.actualizarTemporada(fechaRecogida);
			reserva.actualizarPrecio();
			ConsolaPrincipal.listaReservas.add(reserva);
			reEscribirReservas();
			temporada = reserva.getTemporada();
			precio = (Double) reserva.getPrecio();
			Categoria nuevacategoria = new Categoria(categoria,fechaRecogida,fechaDevuelta, temporada, sedeRecoger, sedeEntrega,precio,costoSeguros,costoConductorAdicional);
			Double precioparcial = nuevacategoria.pago30porciento(categoria,fechaRecogida,fechaDevuelta, temporada, sedeRecoger, sedeEntrega,precio,costoSeguros,costoConductorAdicional);
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

	public static void recogerVehiculo(Reserva reserva) throws IOException
	{
				
		LocalDate fecha = reserva.getfechaRecogida();
		LocalTime hora = LocalTime.now();
		LocalDate ahorafecha = LocalDate.now();
		Sede sedeRecoger = reserva.getsede();

		boolean encontrado = false;
		int i = 0;
		ArrayList<LocalTime> horario;
		LocalTime horainf = null;
		LocalTime horasup = null;
		while(encontrado == false && i<ConsolaPrincipal.listaSedes.size())
		{
			Sede sede1 = ConsolaPrincipal.listaSedes.get(i);
			String sede2 = sede1.getnombre();
			if (sedeRecoger.getnombre().equals(sede2))
			{
				encontrado = true;
				horario = sedeRecoger.gethorario();
				horainf = horario.get(0);
				horasup = horario.get(1);
			}
			i +=1;
				
		}

		if (ahorafecha.equals(fecha) && hora.isAfter(horainf)&& hora.isBefore(horasup))
		{
		System.out.println("\n----------- ES HORA DE RECOGER TU VEHICULO ----------- \n");
		boolean salir = true;
		
		while(salir ==true)
		{
		String conductor = input("\nHabrán otros conductores? (SI/NO): \n");
		costoConductorAdicional=0.0;
		while (conductor.equals("SI"))
		{
			String nombre = input ("\nIngrese su nombre: \n");
			String contacto1 = input ("\nIngrese su celular de contacto: \n");
			String contacto2 = input ("\nIngrese su email de contacto: \n");
			ArrayList<String> contacto = new ArrayList<String>();
			contacto.add(contacto1);
			contacto.add(contacto2);
			String nacimiento1 = input ("\nIngrese su fecha de nacimiento con el formato AA-MM-DD: \n");
			LocalDate nacimiento= LocalDate.parse(nacimiento1);
			String nacionalidad = input ("\nIngrese su nacionalidad: \n");
			String documento = input ("\nIngrese su numero de documento: \n");
			System.out.println("Porfavor Ingrese los datos de la licencia de conduccion del conductor: ");
			String licencia1 = input ("\nIngrese su numero de licencia: \n");
			String licencia2 = input ("\nIngrese el pais de expedicion: \n");
			String licencia3 = input ("\nIngrese la fecha de vencimiento con el formato AA-MM-DD: \n");
			LicenciaConduccion licencia = new LicenciaConduccion(licencia1, licencia2, licencia3);
			ConductorAdicional nuevoConductor = new ConductorAdicional(nombre,contacto,nacimiento,nacionalidad,documento,licencia);
			costoConductorAdicional+=60000.0;
			conductoresAdicionales= new ArrayList<ConductorAdicional>();
			conductoresAdicionales.add(nuevoConductor);
			conductor = input("\nDesea adicionar otro conductor? (SI/NO): \n");
		}
		ConsolaPrincipal.listaReservas.remove(reserva);
		String esp = reserva.getEspecial();
		boolean especial =false;
		if (esp.equals("1"))
		{
			especial=true;
		}
		Reserva actualizarreserva = new Reserva(reserva.getcliente(), reserva.getcategoria(), reserva.getsede(), reserva.getfechaRecogida(), reserva.gethoraRecogida(), especial,  reserva.getlistaVehiculos(), reserva.getfechaDevuelta(),  reserva.getrangoHoras(), reserva.getTemporada(), reserva.getsedeEntrega(), reserva.getPrecio(),reserva.getCostosSeguro(), costoConductorAdicional, conductoresAdicionales,  reserva.getVehiculo());
		ConsolaPrincipal.listaReservas.add(actualizarreserva);
		if (conductor.equals("NO"))
		{
			salir = false;
		}
		}
		reEscribirReservas();
		System.out.println("\n----------- ESTE ES TU VEHICULO: ENTREGA ----------- \n");
		System.out.println("Categoria: "+reserva.getVehiculo().getCategoria()+"\n");
		System.out.println("Placa: "+reserva.getVehiculo().getPlaca()+"\n");
		System.out.println("Color: "+reserva.getVehiculo().getColor()+"\n");
		System.out.println("Modelo: "+reserva.getVehiculo().getModelo()+"\n");
		System.out.println("Tipo de Transmision: "+reserva.getVehiculo().getTipoTransmision()+"\n");
		System.out.println("Ubicacion: "+reserva.getVehiculo().getUbi().getnombre()+"\n");
		Categoria nuevacategoria = new Categoria(reserva.getcategoria(),reserva.getfechaRecogida(),reserva.getfechaDevuelta(), reserva.getTemporada(), reserva.getsede(), reserva.getsedeEntrega(),reserva.getPrecio(),reserva.getCostosSeguro(),reserva.getCostoConductorAdicional());
		
		Double preciofinal = nuevacategoria.precioFinal(reserva.getcategoria(),reserva.getfechaRecogida(),reserva.getfechaDevuelta(), reserva.getTemporada(), reserva.getsede(), reserva.getsedeEntrega(),reserva.getPrecio(),reserva.getCostosSeguro(),reserva.getCostoConductorAdicional());
		
		System.out.println("Ahora debe pagar el restrante del costo del alquiler de su reserva. Este corresponde a $:" + preciofinal);
		MedioDePago.bloquearTarjeta();
		}
		else
		{
			System.out.println("No es hora de recoger tu vehiculo");
		}
	}
	
	private static void devolverVehiculo(Reserva reserva2) {
		LocalDate fecha = reserva2.getfechaDevuelta();
		ArrayList<LocalTime> rangoDeHoras =reserva2.gethorasDevuelta();
		LocalDate ahorafecha = LocalDate.now();
		LocalTime ahoraTime = LocalTime.now();
		Sede sedeDevolver = reserva2.getsedeEntrega();


		if (ahorafecha.equals(fecha)&&ahoraTime.isAfter(rangoDeHoras.get(0)) &&ahoraTime.isBefore(rangoDeHoras.get(1)))
				
		{
		System.out.println("\n----------- ES HORA DE DEVOLVER TU VEHICULO ----------- \n");
		System.out.println("\n----------- CONFIRMA QUE ES TU VEHICULO: DEVUELTA ----------- \n");
		System.out.println("Categoria: "+reserva2.getVehiculo().getCategoria()+"\n");
		System.out.println("Placa: "+reserva2.getVehiculo().getPlaca()+"\n");
		System.out.println("Color: "+reserva2.getVehiculo().getColor()+"\n");
		System.out.println("Modelo: "+reserva2.getVehiculo().getModelo()+"\n");
		System.out.println("Tipo de Transmision: "+reserva2.getVehiculo().getTipoTransmision()+"\n");
		System.out.println("Ubicacion: "+reserva2.getVehiculo().getUbi().getnombre()+"\n");
		Vehiculo elVehiculo = reserva2.getVehiculo();
		Sede sedeReal = elVehiculo.getUbi();
		  ArrayList<Usuario> listaempleados = sedeReal.getEmpleados();
		  boolean enc = false;
		  int j = 0;
		  Usuario empleadoreal = null;
		  Usuario empleado;
		  while(enc == false && j<listaempleados.size())
			{
				empleado = listaempleados.get(j);
				if (empleado.getWork().equals("ActualizadorEstadoVehiculo"))
				{
					enc = true;
					empleadoreal = empleado;
				}
				j +=1;
					
			}
			ActualizadorEstadoVehiculo actualizar = new ActualizadorEstadoVehiculo(empleadoreal.getLogin(), empleadoreal.getClave(), empleadoreal.getNombre(), empleadoreal.getUbi());
			actualizar.actualizarEstado2("Limpieza",sedeReal, categoria, fechaDevuelta, fechaDevuelta.plusDays(1));
		}
		
		else {
			System.out.println("No es el horario adecuado para devolver tu vehiculo, vulve despues.");
		}
		
	}

	public static void reEscribirReservas() throws IOException {
		String data="login:categoria:nombreSede:fechaRecogida:horaRecogida:especial:fechaDevuelta:rangoDeHoras:temporada:sedeEntrega:precio:Seguros:costoConductorAdicional:conductoresAdicionales\n";
		for (int a =0 ; a < ConsolaPrincipal.listaReservas.size(); a++) {
			Reserva laReserva = ConsolaPrincipal.listaReservas.get(a);
			data+= laReserva.getcliente().getLogin()+ ";" + laReserva.getcategoria()+ ";" + laReserva.getsede().getnombre()+ ";" + laReserva.getfechaRecogida().toString()+ ";" + laReserva.gethoraRecogida().toString()+ ";" + laReserva.getEspecial()+ ";" + laReserva.getfechaDevuelta().toString()+ ";" + laReserva.getrangoHoras().get(0)+ "," +laReserva.getrangoHoras().get(1)+ ";" + laReserva.getTemporada() + ";" + laReserva.getsedeEntrega().getnombre()+ ";" + laReserva.getPrecio().toString()+";";
			if(laReserva.getCostosSeguro() == null) {
				data+="*";
			}
			else {
			for(Seguro seguro: laReserva.getCostosSeguro()) {
				data+=seguro.getNombre()+",";
				
			}
			}
			data+= ";"+laReserva.getCostoConductorAdicional().toString()+";";
			if(laReserva.getConductores()==null) {
				data+="*";
			}
			else if(laReserva.getConductores().size()==0) {
				data+="*";
			}
			else {
			for(ConductorAdicional c: laReserva.getConductores()) {
				data+=c.getNombre()+"&"+c.getContacto().get(0)+"/"+c.getContacto().get(1)+"&"+c.getFechaNacimiento().toString()+"&"+c.getNacionalidad()+"&"+c.getDoc().toString()+"&"+c.getLicencia().getNumero()+"/"+c.getLicencia().getPais()+"/"+c.getLicencia().getFecha().toString()+",";
				
			}
			}
			data+="\n";
		}
			FileWriter file = new FileWriter("./data/archivoReservas.txt");
			BufferedWriter output = new BufferedWriter(file);
			output.write(data);
			output.close();
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