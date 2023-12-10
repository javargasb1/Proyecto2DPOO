package uniandes.dpoo.proyecto1.InterfazCliente;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import uniandes.dpoo.proyecto1.interfaz.CrearReservaDialog;
import uniandes.dpoo.proyecto1.modelo.Cliente;
import uniandes.dpoo.proyecto1.modelo.Sistema;
import uniandes.dpoo.proyecto1.modelo.Usuario;

public class OpcionesCliente extends JFrame
{
	private Cliente usuario;
	
	public OpcionesCliente(Usuario usuario)
	{
		this.usuario = (Cliente) usuario;
		// Crear un JPanel para contener los botones
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1, 10, 10)); // GridLayout para organizar los botones

        // Crear los botones
        JButton consultarButton = new JButton("Consultar disponibilidad");
        JButton reservaButton = new JButton("Reserva Vehículo");
        JButton salirButton = new JButton("Salir");

        // Agregar los botones al panel
        panel.add(consultarButton);
        panel.add(reservaButton);
        panel.add(salirButton);

        // Agregar el panel al JFrame
        add(panel, BorderLayout.CENTER);
        
        
        consultarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) 
            {
            	CrearDisponibilidadDialog();
            }
        });
        
        
        reservaButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) 
            {
            	CrearReservaDialog();
            }
        });
        

        // Acción para el botón "Salir"
        salirButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // Salir del programa al hacer clic en "Salir"
            }
        });

        setTitle("Opciones del Cliente");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
	}
	
	private void CrearReservaDialog()
    {
    	new CrearReservaDialog(this);
    }
	
	private void CrearDisponibilidadDialog()
	{
		new DisponibilidadDialog(this);
	}
	
}