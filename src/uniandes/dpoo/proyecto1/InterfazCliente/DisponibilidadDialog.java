package uniandes.dpoo.proyecto1.InterfazCliente;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import uniandes.dpoo.proyecto1.modelo.Sede;
import uniandes.dpoo.proyecto1.modelo.Sistema;
import uniandes.dpoo.proyecto1.modelo.Vehiculo;

public class DisponibilidadDialog extends JDialog
{
	private JComboBox<String> comboSede;
	private JTextField txtFecha;
	
	public DisponibilidadDialog(JFrame parent)
	{
		super(parent, "Consultar Disponibilidad", true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(400, 400);
        setLocationRelativeTo(parent);
        
     // Panel principal con BorderLayout
        JPanel panelPrincipal = new JPanel(new BorderLayout());

        // Mensaje y Logo en un panel en la región NORTH
        JPanel panelNorte = new JPanel(new BorderLayout());

        // Mensaje "Comencemos tu reserva"
        JLabel lblMensaje = new JLabel("Realiza tu consulta");
        lblMensaje.setHorizontalAlignment(JLabel.CENTER);

        // Logo de la empresa (ajustado al tamaño deseado)
        ImageIcon logo = new ImageIcon(new ImageIcon("imagenes/LogoMovilRide.png").getImage().getScaledInstance(200, 100, Image.SCALE_SMOOTH));
        JLabel lblLogo = new JLabel(logo);
        lblLogo.setHorizontalAlignment(JLabel.LEFT);

        // Agregar mensaje y logo al panelNorte
        panelNorte.add(lblMensaje, BorderLayout.CENTER);
        panelNorte.add(lblLogo, BorderLayout.WEST);
        
        JPanel panel = new JPanel(new GridBagLayout());
        
        JLabel lblSede = new JLabel("Sede para consultar disponibilidad:");
        comboSede = new JComboBox<>(new String[]{"ChapiRenta", "ChapiAlquila", "UsaquenAlquila"});
        
        JLabel lblFecha = new JLabel("Fecha de desea consultar (AAAA-MM-DD):");
        txtFecha = new JTextField(7);
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(2, 2, 2, 2);
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(lblSede,gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(comboSede, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(lblFecha, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(txtFecha, gbc);
        
        //Botones
        
        JButton btnBuscar = new JButton("Buscar");
        JButton btnCancelar = new JButton("Cancelar");
        
        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
            	String NombreSede = (String) comboSede.getSelectedItem();
            	Sede sede = StringtoSede(NombreSede);
                LocalDate fecha = LocalDate.parse(txtFecha.getText());
                consultarDisponibilidad(sede,fecha);
            }
        });
        
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Si el usuario cancela, simplemente cierra el diálogo.
                dispose();
            }
        });
        
     // Agregar componentes al panel principal
        panelPrincipal.add(panelNorte, BorderLayout.NORTH);
        panelPrincipal.add(panel, BorderLayout.CENTER);

        // Agregar el panel principal al diálogo
        add(panelPrincipal, BorderLayout.CENTER);

        JPanel btnPanel = new JPanel();
        btnPanel.add(btnBuscar);
        btnPanel.add(btnCancelar);	
        add(btnPanel, BorderLayout.SOUTH);
        setVisible(true);
        
	}
	
	private void consultarDisponibilidad(Sede sede, LocalDate fecha)
	{
		ArrayList<Vehiculo> listaVehiculos = Sistema.listaVehiculos;
		ArrayList<Vehiculo> listaVehiculosSede = new ArrayList<Vehiculo>();
		ArrayList<String> listaCategorias = new ArrayList<String>();
		//Buscamos todos los vehiculos para la sede ingresada
		for(Vehiculo vehiculo: listaVehiculos)
		{
			Sede sedeVehiculo = vehiculo.getUbi();
			if (sedeVehiculo.equals(sede))
			{
				listaVehiculosSede.add(vehiculo);
				String categoria = vehiculo.getCategoria();
				if (!listaCategorias.contains(categoria))
				{
					listaCategorias.add(categoria);	
				}
			}
		}
		
		if (!listaCategorias.isEmpty())
		{
			
			StringBuilder mensajeDisponibilidad = new StringBuilder("Categorias disponibles para la fecha ");
			String fechaString = fecha.toString() + ": \n";
			mensajeDisponibilidad.append(fechaString);
	        int i = 0;
	        while (i < listaCategorias.size())
	        {
				String mensaje = listaCategorias.get(i) + "\n";
				mensajeDisponibilidad.append(mensaje);
				i++;
	        }
			 
	        JOptionPane.showMessageDialog(null, mensajeDisponibilidad.toString(), "Categorias Disponibles", JOptionPane.INFORMATION_MESSAGE);
			 
		}
		else
		{
			JOptionPane.showMessageDialog(null,"No hay vehiculos disponibles para esta fecha :(\n Intente con otra fecha",
					"Sin Disponibilidad", JOptionPane.INFORMATION_MESSAGE);
		}
    }
	
	
	private Sede StringtoSede(String nombreSede)
    {
    	Sede sedeEncontrada = null;
    	for (Sede sede : Sistema.listaSedes)
    	{
    		String nombreSedex = sede.getnombre();
    		if (nombreSede.equals(nombreSedex))
    		{
    			sedeEncontrada = sede;
    			break;
    		}
    	}
		return sedeEncontrada;
    }
	
}