package uniandes.dpoo.proyecto1.interfaz;

import javax.swing.JButton;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class PanelActualizador extends JPanel implements ActionListener {
	private JButton botonActualizar;
	private JButton botonSalir;

	
	public PanelActualizador() {
		botonActualizar = new JButton("Actualizar el estado de un vehiculo");
		botonActualizar.addActionListener(this);
		botonActualizar.setPreferredSize(new Dimension(300,100));
		botonSalir = new JButton("Salir");
		botonSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (botonSalir.isSelected()) {
                    System.exit(0);
                } 
            }
        });
		JLabel bienvenido = new JLabel("Bienvenido Actualizador, que accion desea realizar?");
		GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Márgenes externos
        gbc.gridx = 2;
        gbc.gridy = 2;
        add(bienvenido, gbc);
        gbc.gridx = 3;
        gbc.gridy = 4;
        gbc.gridwidth = 2; 
        add(botonActualizar, gbc);
        gbc.gridx = 5;
        gbc.gridy = 5;
        gbc.gridwidth = 1; 
        add(botonSalir, gbc);
	}
	@Override
	public void actionPerformed(ActionEvent e) {

		InterfazActualizador.actualizar();
		
	}
	
}

