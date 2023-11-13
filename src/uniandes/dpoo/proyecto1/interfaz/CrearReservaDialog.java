package uniandes.dpoo.proyecto1.interfaz;

import javax.swing.*;

import uniandes.dpoo.proyecto1.modelo.ConductorAdicional;
import uniandes.dpoo.proyecto1.modelo.Sede;
import uniandes.dpoo.proyecto1.modelo.Seguro;
import uniandes.dpoo.proyecto1.modelo.Sistema;
import uniandes.dpoo.proyecto1.modelo.Vehiculo;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class CrearReservaDialog extends JDialog 
{
	private JComboBox<String> comboCategoria;
	private JComboBox<String> comboRecogerSede;
	private JTextField txtFechaRecogida;
    private JTextField txtFechaDevolucion;
    private JTextField txtHoraRecogida;
    private JRadioButton radioSi;
    private JRadioButton radioNo;
    private JComboBox<String> comboEntregarSede;
    private JTextField txtHoraEntregainf;
    private JTextField txtHoraEntregasup;
   

    public CrearReservaDialog(JFrame parent) 
    {
    	super(parent, "Crear Reserva", true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(700, 500);
        setLocationRelativeTo(parent);
        
        mostrarHorarios(); // Mostramos los horarios de las sedes.
        
        // Panel principal con BorderLayout
        JPanel panelPrincipal = new JPanel(new BorderLayout());

        // Mensaje y Logo en un panel en la región NORTH
        JPanel panelNorte = new JPanel(new BorderLayout());

        // Mensaje "Comencemos tu reserva"
        JLabel lblMensaje = new JLabel("Comencemos tu reserva");
        lblMensaje.setHorizontalAlignment(JLabel.CENTER);

        // Logo de la empresa (ajustado al tamaño deseado)
        ImageIcon logo = new ImageIcon(new ImageIcon("imagenes/LogoMovilRide.png").getImage().getScaledInstance(200, 100, Image.SCALE_SMOOTH));
        JLabel lblLogo = new JLabel(logo);
        lblLogo.setHorizontalAlignment(JLabel.LEFT);

        // Agregar mensaje y logo al panelNorte
        panelNorte.add(lblMensaje, BorderLayout.CENTER);
        panelNorte.add(lblLogo, BorderLayout.WEST);

        // Panel interno con GridBagLayout para otras componentes
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(2, 2, 2, 2);

        // Componentes
        addToPanel(panel, new JLabel("Categoria:"), gbc, 0, 0);
        comboCategoria = new JComboBox<>(new String[]{"Pequeno", "SUV", "Lujo", "VAN"});
        addToPanel(panel, comboCategoria, gbc, 1, 0);
        
        addToPanel(panel, new JLabel("Recoger en sede:"), gbc, 0, 1);
        comboRecogerSede = new JComboBox<>(new String[]{"ChapiRenta", "ChapiAlquila", "UsaquenAlquila"});
        addToPanel(panel, comboRecogerSede, gbc, 1, 1);

        addToPanel(panel, new JLabel("Fecha de recogida (AA-MM-DD):"), gbc, 0, 2);
        txtFechaRecogida = new JTextField(7);
        addToPanel(panel, txtFechaRecogida, gbc, 1, 2);
        
        addToPanel(panel, new JLabel("Fecha de devolución (AA-MM-DD):"), gbc, 0, 3);
        txtFechaDevolucion = new JTextField(7);
        addToPanel(panel, txtFechaDevolucion, gbc, 1, 3);
        
        
        addToPanel(panel, new JLabel("Hora de recogida (hh:mm):"), gbc, 0, 4);
        txtHoraRecogida = new JTextField(15);
        addToPanel(panel, txtHoraRecogida, gbc, 1, 4);

        addToPanel(panel, new JLabel("¿Necesita seguro?"), gbc, 0, 5);
        ButtonGroup group = new ButtonGroup();
        radioSi = new JRadioButton("Sí");
        radioNo = new JRadioButton("No");
        group.add(radioSi);
        group.add(radioNo);
        JPanel radioPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        radioPanel.add(radioSi);
        radioPanel.add(radioNo);
        addToPanel(panel, radioPanel, gbc, 1, 5);

        addToPanel(panel, new JLabel("Entregar en sede:"), gbc, 0, 6);
        comboEntregarSede = new JComboBox<>(new String[]{"ChapiRenta", "ChapiAlquila", "UsaquenAlquila"});
        addToPanel(panel, comboEntregarSede, gbc, 1, 6);
        
        
        addToPanel(panel, new JLabel("Rango de horas de entrega (hh:mm):"), gbc, 0, 7);
        txtHoraEntregainf = new JTextField(7);
        txtHoraEntregasup = new JTextField(7);
        addToPanel(panel, txtHoraEntregainf, gbc, 1, 7);
        addToPanel(panel,txtHoraEntregasup,gbc,2,7);

        // Botones
        JButton btnAceptar = new JButton("Aceptar");
        btnAceptar.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) {
            	String categoria = (String) comboCategoria.getSelectedItem();
                String NombreSedeRecoger = (String) comboRecogerSede.getSelectedItem();
                Sede sedeRecoger = StringtoSede(NombreSedeRecoger);
                LocalDate fechaRecogida = LocalDate.parse(txtFechaRecogida.getText());
                LocalDate fechaDevolucion = LocalDate.parse(txtFechaDevolucion.getText());
                LocalTime horaRecogida = LocalTime.parse(txtHoraRecogida.getText());
                boolean especial = false;
                ArrayList<Seguro> costoSeguros = null;
                if (radioSi.isSelected())
                {
                	ArrayList<Sede> listaSedes = Sistema.listaSedes;
                	especial = true;
                	costoSeguros = mostrarSeguros(listaSedes);
                }
                String NombreSedeEntrega = (String) comboEntregarSede.getSelectedItem();
                Sede sedeEntrega = StringtoSede(NombreSedeEntrega);
                LocalTime horaEntregainf = LocalTime.parse(txtHoraEntregainf.getText());
                LocalTime horaEntregasup = LocalTime.parse(txtHoraEntregasup.getText());
                ArrayList<LocalTime> rangoDeHoras = new ArrayList<LocalTime>() ;
				rangoDeHoras.add(horaEntregainf);
				rangoDeHoras.add(horaEntregasup);
				String temporada = null;
				Double precio = 0.0;
				for (Vehiculo vehiculo: Sistema.listaVehiculos)
				{
					if (vehiculo.getCategoria().equals(categoria))
							{
								precio = vehiculo.getPrecio();
								break;
							}
				}
				Double costoConductorAdicional =0.0;
				ArrayList<ConductorAdicional> conductoresAdicionales= new ArrayList<ConductorAdicional>();
				try {
					InterfazCliente.crearReserva(categoria,sedeRecoger,fechaRecogida,horaRecogida,especial,	Sistema.listaVehiculos,fechaDevolucion,rangoDeHoras,temporada,sedeEntrega,precio,costoSeguros,costoConductorAdicional, conductoresAdicionales);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
                dispose();
            }
        });

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Si el usuario cancela, simplemente cierra el diálogo.
                dispose();
            }
        });
        
        JButton btnHorarios = new JButton("Ver Horarios");
        btnHorarios.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarHorarios();
            }
        });

        // Agregar componentes al panel principal
        panelPrincipal.add(panelNorte, BorderLayout.NORTH);
        panelPrincipal.add(panel, BorderLayout.CENTER);

        // Agregar el panel principal al diálogo
        add(panelPrincipal, BorderLayout.CENTER);

        JPanel btnPanel = new JPanel();
        btnPanel.add(btnAceptar);
        btnPanel.add(btnCancelar);	
        btnPanel.add(btnHorarios);
        add(btnPanel, BorderLayout.SOUTH);
        
        setVisible(true);
    }
    
    private void addToPanel(JPanel panel, JComponent component, GridBagConstraints gbc, int gridx, int gridy) {
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        panel.add(component, gbc);
    }
    
    private static void mostrarHorarios() 
    {
        StringBuilder mensajeHorarios = new StringBuilder("Horarios de las sedes disponibles:\n");
        ArrayList<Sede> listaSedes = Sistema.listaSedes;
        int i = 0;
        while (i <listaSedes.size())
        {
        	Sede sede = listaSedes.get(i);
        	ArrayList<LocalTime> horario1 = sede.gethorario();
        	String nombreSede = sede.getnombre();
			String mensaje = nombreSede + ":\n"+ horario1.get(0)+"AM -"+horario1.get(1)+" PM \n";
			mensajeHorarios.append(mensaje);
			i++;
        }
		 
        JOptionPane.showMessageDialog(null, mensajeHorarios.toString(), "Horarios de Sedes", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private ArrayList<Seguro> mostrarSeguros(ArrayList<Sede> listaSedes)
    {
		boolean enc = false;
		int a = 0;
		ArrayList<String> opciones = new ArrayList<>();
		while(enc == false && a<listaSedes.size())
		{	
			Seguro seguro = Sistema.listaSeguros.get(a);
			String mensaje = (a+1)+")"+seguro.getNombre()+":$"+seguro.getPrecio();
			opciones.add(mensaje);
			a +=1;
		}
		
		MostrarSegurosDialog SegurosDialog = new MostrarSegurosDialog(this,opciones);
		ArrayList<Integer> listaOpcionesSeguro = SegurosDialog.getListaOpciones();
		ArrayList<Seguro> costoSeguros = new ArrayList<Seguro>();
		
		for (int num : listaOpcionesSeguro)
		{
			costoSeguros.add(Sistema.listaSeguros.get(num));
		}
		
		return costoSeguros;
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
