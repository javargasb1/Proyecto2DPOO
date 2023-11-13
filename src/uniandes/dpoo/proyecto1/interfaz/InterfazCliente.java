package uniandes.dpoo.proyecto1.interfaz;

import javax.swing.*;

import uniandes.dpoo.proyecto1.consola.ConsolaCliente;
import uniandes.dpoo.proyecto1.modelo.Cliente;
import uniandes.dpoo.proyecto1.modelo.ConductorAdicional;
import uniandes.dpoo.proyecto1.modelo.ControladorCliente;
import uniandes.dpoo.proyecto1.modelo.Reserva;
import uniandes.dpoo.proyecto1.modelo.Sede;
import uniandes.dpoo.proyecto1.modelo.Seguro;
import uniandes.dpoo.proyecto1.modelo.Usuario;
import uniandes.dpoo.proyecto1.modelo.Vehiculo;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class InterfazCliente extends JFrame 
{

	private int opcion;
	private static Cliente usuario;
	
    public InterfazCliente(Usuario usuario) 
    {
    	this.usuario = (Cliente) usuario;
        // Configuración del JFrame
        setTitle("Opciones del Cliente");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        // Crear un panel principal con BorderLayout
        JPanel panelPrincipal = new JPanel(new BorderLayout());

        // Crear un mensaje de bienvenida en la parte superior
        String nombreCliente = usuario.getNombre();
        JLabel lblBienvenida = new JLabel("Bienvenid@" + nombreCliente);
        lblBienvenida.setHorizontalAlignment(JLabel.CENTER);

        // Crear un panel para los botones con GridLayout
        JPanel panelBotones = new JPanel(new GridLayout(4, 1, 10, 10));

        // Crear botones para las opciones
        JButton btnReservar = new JButton("Reservar Vehículo");
        JButton btnRecoger = new JButton("Recoger Vehículo");
        JButton btnDevolver = new JButton("Devolver Vehículo");
        JButton btnSalir = new JButton("Salir");

        // Configurar ActionListener para los botones
        btnReservar.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                CrearReservaDialog();
            }
        });

        btnRecoger.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
            	opcion = 2;
            }
        });

        btnDevolver.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
            	opcion = 3;
            }
        });

        btnSalir.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                // Lógica para la opción de salir
                System.exit(0);
            }
        });

        // Agregar botones al panel
        panelBotones.add(btnReservar);
        panelBotones.add(btnRecoger);
        panelBotones.add(btnDevolver);
        panelBotones.add(btnSalir);

        // Agregar componentes al panel principal
        panelPrincipal.add(lblBienvenida, BorderLayout.NORTH);
        panelPrincipal.add(panelBotones, BorderLayout.CENTER);

        // Agregar el panel principal al JFrame
        add(panelPrincipal);
    }
    
    private void CrearReservaDialog()
    {
    	new CrearReservaDialog(this);
    }

	public static void crearReserva(String categoria, Sede sedeRecoger, LocalDate fechaRecogida, LocalTime horaRecogida,
			boolean especial, ArrayList<Vehiculo> listaVehiculos, LocalDate fechaDevolucion,
			ArrayList<LocalTime> rangoDeHoras, String temporada, Sede sedeEntrega, Double precio,
			ArrayList<Seguro> costoSeguros, Double costoConductorAdicional,
			ArrayList<ConductorAdicional> conductoresAdicionales) throws IOException 
	{
		Reserva reserva = usuario.crearReserva(usuario,categoria,sedeRecoger,
				fechaRecogida,horaRecogida,especial,listaVehiculos,
				fechaDevolucion,rangoDeHoras,temporada,sedeEntrega,
				precio,costoSeguros,costoConductorAdicional,  conductoresAdicionales);
		
		ControladorCliente.setReserva(reserva);
		
		if(reserva != null) 
		{
			Double precioParcial = ControladorCliente.reservaExitosa(categoria,fechaRecogida,fechaDevolucion, temporada, sedeRecoger, sedeEntrega,precio,costoSeguros,costoConductorAdicional);
			String mensaje = "Su reserva a sido creada con exito, cuando sea la hora adecuada, recoja su vehiculo.\n"
					+ "Para confirmar su reserva debe pagar el 30% del costo del alquiler. Este corresponde a $:\n" + Double.toString(precioParcial);
			JOptionPane.showMessageDialog(null, mensaje, "Éxito", JOptionPane.INFORMATION_MESSAGE);
		}
		else 
		{
			String mensaje = "No hay vehiculos disponibles con esa categoria, en esas fechas y en esa sede, vuelva a hacer una nueva reserva.";
			JOptionPane.showMessageDialog(null, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
		}	
	}
	
  
}