package uniandes.dpoo.proyecto1.InterfazCliente;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.io.IOException;
import java.text.ParseException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import uniandes.dpoo.proyecto1.interfaz.InterfazCrearCuenta;
import uniandes.dpoo.proyecto1.interfaz.LogoPanel;
import uniandes.dpoo.proyecto1.modelo.Sistema;
import uniandes.dpoo.proyecto1.modelo.Usuario;


public class NuevaInterfazCliente extends JFrame
{
	private LogoPanel logoPanel;
	private OpcionesAutentificacion panelOpcionesAutentificacion;
	private int anchoVentana = 800;
	private int largoVentana = 500;
	private Usuario usuario;
	
	public NuevaInterfazCliente()
	{
		setLayout(new BorderLayout());
		
		logoPanel = new LogoPanel();
		logoPanel.setPreferredSize(new Dimension(anchoVentana/2,largoVentana/2));
		add(logoPanel,BorderLayout.WEST);
		
		panelOpcionesAutentificacion = new OpcionesAutentificacion(this);
		panelOpcionesAutentificacion.setPreferredSize(new Dimension(anchoVentana/2,largoVentana/2));
		panelOpcionesAutentificacion.setBackground(Color.MAGENTA);
		add(panelOpcionesAutentificacion,BorderLayout.EAST);
		
		setTitle("Bienvenido Cliente");
		setSize(anchoVentana,largoVentana);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}
	
	public void IniciarSesion(String login,String clave, int opcion)throws IOException, ParseException
	{
		usuario = Sistema.iniciarSesion(login,clave,opcion);
		if (usuario != null)
		{
			cerrarVentana();
			OpcionesCliente frame = new OpcionesCliente(usuario);
		}
		else 
		{
			String mensaje = "Credenciales incorrectas o si no estas registrado, crea una cuenta!\"";
			JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void CrearCuenta() 
	{
		new InterfazCrearCuenta("Cliente");
	}
	
	private void cerrarVentana() 
	{
        this.dispose();
    }
	
	
	public static void main(String[] args)
	{
		new NuevaInterfazCliente();
	}

	
}