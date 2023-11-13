package uniandes.dpoo.proyecto1.interfaz;

import javax.swing.*;

import uniandes.dpoo.proyecto1.modelo.Sistema;
import uniandes.dpoo.proyecto1.modelo.*;

import java.awt.*;
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
		
		panelOpcionesInicioSesion = new PanelOpcionesInicioSesion(this);
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
	} 
	
	private void cerrarVentana() 
	{
        this.dispose();
    }
	
	public void iniciarSesion(String login,String clave, int opcion) throws IOException, ParseException
	{
		Usuario usuario = Sistema.iniciarSesion(login,clave,opcion);
		if (usuario != null)
		{
			cerrarVentana();
			if (opcion == 1)
			{
				InterfazCliente frame = new InterfazCliente(usuario);
			}
			else if (opcion == 2)
			{
				
				String trabajo = usuario.getWork();
				
				if(trabajo.equals("AdministradorLocal")) 
				{
				InterfazAdministrador frame = new InterfazAdministrador();
				}
				if(trabajo.equals("ActualizadorEstadoVehiculo")) 
				{
				InterfazActualizador frame = new InterfazActualizador();
				}
			}
		}
		else 
		{
			String mensaje = "Credenciales incorrectas o si no estas registrado, crea una cuenta!\"";
			JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void CrearCuenta() 
	{
		new InterfazCrearCuenta();
	}
	
}

	

