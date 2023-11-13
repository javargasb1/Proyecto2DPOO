package uniandes.dpoo.proyecto1.interfaz;

import javax.swing.JButton;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelAdministrador extends JPanel implements ActionListener
{
	private JButton botonRegistrar;
	private JButton botonBaja;
	private JButton botonSalir;    

	public PanelAdministrador()
	{
		botonRegistrar = new JButton("Registrar Compra Vehiculo");
		botonRegistrar.addActionListener(this);
		botonRegistrar.setPreferredSize(new Dimension(200,100));
		botonSalir = new JButton("Salir");
		botonBaja = new JButton("Dar de Baja Vehiculo");
		botonBaja.addActionListener(this);
		botonBaja.setPreferredSize(new Dimension(200,100));
		botonSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (botonSalir.isSelected()) {
                    System.exit(0);
                } 
            }
        });
		JLabel bienvenido = new JLabel("Bienvenido Administrador, que accion desea realizar?");
		
		GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Márgenes externos
        gbc.gridx = 2;
        gbc.gridy = 2;
        add(bienvenido, gbc);
        gbc.gridx = 3;
        gbc.gridy = 4;
        gbc.gridwidth = 2; 
        add(botonRegistrar, gbc);
        gbc.gridx = 3;
        gbc.gridy = 5;
        gbc.gridwidth = 2; 
        add(botonBaja, gbc);
        gbc.gridx = 7;
        gbc.gridy = 7;
        gbc.gridwidth = 1; 
        add(botonSalir, gbc);

		
	}
	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getActionCommand().equals(botonRegistrar))
			InterfazAdministrador.registrarcompra();
		if(e.getActionCommand().equals(botonBaja))
			InterfazAdministrador.dardebaja();		
	}
	
}
