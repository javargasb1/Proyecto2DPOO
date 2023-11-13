package uniandes.dpoo.proyecto1.interfaz;

import javax.imageio.ImageIO;
import javax.swing.*;

import uniandes.dpoo.proyecto1.consola.ConsolaPrincipal;
import uniandes.dpoo.proyecto1.modelo.ActualizadorEstadoVehiculo;
import uniandes.dpoo.proyecto1.modelo.Sede;
import uniandes.dpoo.proyecto1.modelo.Sistema;
import uniandes.dpoo.proyecto1.modelo.Usuario;
import uniandes.dpoo.proyecto1.modelo.Vehiculo;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;

public class InterfazActualizador extends JFrame 
{
	public static LogoPanel logoPanel;
	public static PanelActualizador panelActualizar;
	private PanelOpcionesInicioSesion panelOpcionesInicioSesion;
	private int anchoVentana = 800;
	private int largoVentana = 500;
	
	public InterfazActualizador() 
	{
		setLayout(new FlowLayout());
		
		logoPanel = new LogoPanel();
		logoPanel.setPreferredSize(new Dimension(400, 250));
		setLayout(new FlowLayout(FlowLayout.LEADING));
        add(logoPanel);
		
		panelActualizar = new PanelActualizador();
		panelActualizar.setPreferredSize(new Dimension(anchoVentana/2,largoVentana/2));
		setLayout(new FlowLayout(FlowLayout.CENTER));
		add(panelActualizar);
		
		setTitle("Actualizador");
		setSize(anchoVentana,largoVentana);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);	
		setLocationRelativeTo(null);
	}
	public static void actualizar() {
		String placa =input("Por favor seleccione el vehiculo del que quiere actualizar el estado:");
		  int i=0;
		  boolean encontrado =false;
		  Vehiculo elVehiculo = null;
		  while(encontrado==false && i<Sistema.listaVehiculos.size()) {
			  Vehiculo v = Sistema.listaVehiculos.get(i);
			  if((v.getPlaca()).equals(placa)) {
				  elVehiculo =v;
				  encontrado=true;
			  }
			  
		  }
		  if(elVehiculo != null) {
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
				boolean cont=true;
				while(cont==true) {
					System.out.println("\nOpciones de la actualizacion\n");
					System.out.println("1. Bloquear/Desbloquear disponibilidad");
					System.out.println("2. Salir");
					int opcion2 = Integer.parseInt(input("Por favor seleccione una opción:"));
					if(opcion2==1) {
						LocalDate fechaRecogida = LocalDate.parse(input("Por favor seleccione la fecha incial para bloquear la disponibilidad en formato AAAA-MM-DD:"));
						LocalDate fechaDevuelta = LocalDate.parse(input("Por favor seleccione la fecha final para bloquear la disponibilidad en formato AAAA-MM-DD:"));
						String estado = input("Por favor ingrese el estado del vehiculo para estas fechas (Limpieza/Disponible/Alquilado):");
						String categoria= elVehiculo.getCategoria();
						actualizar.actualizarEstado2(estado,sedeReal, categoria, fechaRecogida, fechaDevuelta);
						System.out.println("El estado del vehiculo a sido actualizado"); 
					}
					else  {
						cont=false;
					}
				}
		  }
		  else {
			  System.out.println("El vehiculo no esta registrado"); 
		  }
	}
	public static String input(String mensaje)
	{
		try
		{
			System.out.print(mensaje );
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


