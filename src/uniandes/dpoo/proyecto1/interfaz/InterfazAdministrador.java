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
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;


public class InterfazAdministrador extends JFrame 
{
	public static LogoPanel logoPanel;
	public static PanelAdministrador panelAdministrador;
	private PanelOpcionesInicioSesion panelOpcionesInicioSesion;
	private int anchoVentana = 800;
	private int largoVentana = 500;
	
	public InterfazAdministrador() 
	{
		setLayout(new FlowLayout());
		
		logoPanel = new LogoPanel();
		logoPanel.setPreferredSize(new Dimension(300, 250));
		setLayout(new FlowLayout(FlowLayout.LEADING));
        add(logoPanel);
		
		PanelAdministrador panelActualizar = new PanelAdministrador();
		panelActualizar.setPreferredSize(new Dimension(anchoVentana/2,largoVentana/2));
		setLayout(new FlowLayout(FlowLayout.RIGHT));
		add(panelActualizar);
		
		setTitle("Administrador");
		setSize(anchoVentana,largoVentana);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);	
		setLocationRelativeTo(null);

      
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
