package uniandes.dpoo.proyecto1.interfaz;

import javax.imageio.ImageIO;
import javax.swing.*;

import uniandes.dpoo.proyecto1.consola.ConsolaPrincipal;
import uniandes.dpoo.proyecto1.modelo.Sistema;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;

public class InicioSesionInterfaz extends JFrame 
{
	private LogoPanel logoPanel;
	private PanelOpcionesInicioSesion panelOpcionesInicioSesion;
	private int anchoVentana = 800;
	private int largoVentana = 500;
	
	public InicioSesionInterfaz() 
	{
		setLayout(new BorderLayout());
		
		logoPanel = new LogoPanel();
		logoPanel.setPreferredSize(new Dimension(anchoVentana/2,largoVentana/2));
		add(logoPanel,BorderLayout.WEST);
		
		panelOpcionesInicioSesion = new PanelOpcionesInicioSesion();
		panelOpcionesInicioSesion.setPreferredSize(new Dimension(anchoVentana/2,largoVentana/2));
		add(panelOpcionesInicioSesion,BorderLayout.EAST);
		
		setTitle("Iniciar Sesión");
		setSize(anchoVentana,largoVentana);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);	
		setLocationRelativeTo(null);
	}
	
	public static void main(String[] args) throws IOException, ParseException
	{
		new InicioSesionInterfaz();
		Sistema.cargarDatos();
	}
	
	public static void iniciarSesion(String login,String clave, int opcion) throws IOException
	{
		Sistema.iniciarSesion(login,clave,opcion);
	}
	
}

	

