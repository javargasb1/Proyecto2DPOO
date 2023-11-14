package uniandes.dpoo.proyecto1.interfaz;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import uniandes.dpoo.proyecto1.modelo.Sede;

public class DarBajaDialog extends JDialog {
	 public DarBajaDialog(Frame panelAdministrador) 
	    {
	    	super(panelAdministrador, "Dar de Baja Vehiculo", true);
	        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	        setSize(700, 500);
	        setLocationRelativeTo(panelAdministrador);
	        JPanel panelPrincipal = new JPanel(new BorderLayout());
	        JPanel panelNorte = new JPanel(new BorderLayout());
	        JLabel lblMensaje = new JLabel("Dar de Baja Vehiculo");
	        lblMensaje.setHorizontalAlignment(JLabel.CENTER);
	        ImageIcon logo = new ImageIcon(new ImageIcon("imagenes/LogoMovilRide.png").getImage().getScaledInstance(200, 100, Image.SCALE_SMOOTH));
	        JLabel lblLogo = new JLabel(logo);
	        lblLogo.setHorizontalAlignment(JLabel.LEFT);
	        panelNorte.add(lblMensaje, BorderLayout.CENTER);
	        panelNorte.add(lblLogo, BorderLayout.WEST);


	        JPanel panel = new JPanel(new GridBagLayout());
	        GridBagConstraints gbc = new GridBagConstraints();
	        gbc.insets = new Insets(2, 2, 2, 2);
	        addToPanel(panel, new JLabel("Ingrese la Placa del Vehiculo que desea dar de baja:"), gbc, 0, 0);
	        JTextField txtNombre = new JTextField(20);
	        addToPanel(panel,txtNombre,gbc,1,0);

	        JButton btnAceptar = new JButton("Confirmar");
	        btnAceptar.addActionListener(new ActionListener() 
	        {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	//ayduda
	    
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
	    
	 
	 

}
