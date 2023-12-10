package uniandes.dpoo.proyecto1.interfaz;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import javax.swing.*;

import uniandes.dpoo.proyecto1.modelo.ConductorAdicional;
import uniandes.dpoo.proyecto1.modelo.Sede;
import uniandes.dpoo.proyecto1.modelo.Seguro;
import uniandes.dpoo.proyecto1.modelo.Sistema;
import uniandes.dpoo.proyecto1.modelo.Vehiculo;
public class RegistrarCompraDialog extends JDialog 
{
	private JComboBox<String> comboCategoria;
	private JComboBox<String> comboRecogerSede;
    
    public RegistrarCompraDialog(Frame panelAdministrador) 
    {
    	super(panelAdministrador, "Registrar Compra Vehiculo", true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(900, 700);
        setLocationRelativeTo(panelAdministrador);
        JPanel panelPrincipal = new JPanel(new BorderLayout());
        JPanel panelNorte = new JPanel(new BorderLayout());
        JLabel lblMensaje = new JLabel("Registrar Compra Vehiculo");
        lblMensaje.setHorizontalAlignment(JLabel.CENTER);
        ImageIcon logo = new ImageIcon(new ImageIcon("imagenes/LogoMovilRide.png").getImage().getScaledInstance(200, 100, Image.SCALE_SMOOTH));
        JLabel lblLogo = new JLabel(logo);
        lblLogo.setHorizontalAlignment(JLabel.LEFT);
        panelNorte.add(lblMensaje, BorderLayout.CENTER);
        panelNorte.add(lblLogo, BorderLayout.WEST);


        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(2, 2, 2, 2);
        addToPanel(panel, new JLabel("Placa:"), gbc, 0, 10);
        JTextField txtPlaca = new JTextField(20);
        addToPanel(panel,txtPlaca,gbc,1,10);
        addToPanel(panel, new JLabel("Marca:"), gbc, 0, 20);
        JTextField txtMarca = new JTextField(20);
        addToPanel(panel,txtMarca,gbc,1,20);
        addToPanel(panel, new JLabel("Modelo:"), gbc, 0, 30);
        JTextField txtModelo = new JTextField(20);
        addToPanel(panel,txtModelo,gbc,1,30);
        addToPanel(panel, new JLabel("Color:"), gbc, 0, 40);
        JTextField txtColor = new JTextField(20);
        addToPanel(panel,txtColor,gbc,1,40);
        addToPanel(panel, new JLabel("Tipo de transimicion:"), gbc, 0, 50);
        JTextField txtTrasmicion = new JTextField(20);
        addToPanel(panel,txtTrasmicion,gbc,1,50);
        addToPanel(panel, new JLabel("Categoria:"), gbc, 0, 60);
        comboCategoria = new JComboBox<>(new String[]{"Pequeno", "SUV", "Lujo", "VAN"});
        addToPanel(panel, comboCategoria, gbc,  1, 60);
        addToPanel(panel, new JLabel("Ubicacion:"), gbc, 0, 70);
        comboRecogerSede = new JComboBox<>(new String[]{"ChapiRenta", "ChapiAlquila", "UsaquenAlquila"});
        addToPanel(panel, comboRecogerSede, gbc, 1, 70);
        addToPanel(panel, new JLabel("Precio:"), gbc, 0, 80);
        JTextField txtPrecio = new JTextField(20);
        addToPanel(panel,txtPrecio,gbc,1,80);
        addToPanel(panel, new JLabel("Tipo de vehiculo:"), gbc, 0, 90);
        JTextField txttipo = new JTextField(20);
        addToPanel(panel,txttipo,gbc,1,90);
        addToPanel(panel, new JLabel("Porcentaje adicional de la prima del seguro:"), gbc, 0, 100);
        JTextField txtPorcentaje = new JTextField(20);
        addToPanel(panel,txtPorcentaje,gbc,1,100);
  
        JButton btnAceptar = new JButton("Aceptar");
        btnAceptar.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) {
            	String categoria = (String) comboCategoria.getSelectedItem();
                String NombreSedeRecoger = (String) comboRecogerSede.getSelectedItem();
                Sede sedeRecoger = StringtoSede(NombreSedeRecoger);
    
            }
        });

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        
        panelPrincipal.add(panelNorte, BorderLayout.NORTH);
        panelPrincipal.add(panel, BorderLayout.CENTER);

        add(panelPrincipal, BorderLayout.CENTER);

        JPanel btnPanel = new JPanel();
        btnPanel.add(btnAceptar);
        btnPanel.add(btnCancelar);	
        add(btnPanel, BorderLayout.SOUTH);
        
        setVisible(true);
    }
    
    private void addToPanel(JPanel panel, JComponent component, GridBagConstraints gbc, int gridx, int gridy) {
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        panel.add(component, gbc);
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

