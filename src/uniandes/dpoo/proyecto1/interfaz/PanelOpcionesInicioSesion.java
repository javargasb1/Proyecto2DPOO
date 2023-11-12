package uniandes.dpoo.proyecto1.interfaz;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class PanelOpcionesInicioSesion extends JPanel implements ActionListener
{
	private JTextField txtUsuario;
    private JPasswordField txtContraseña;
    private JButton btnIniciarSesion;
    private JCheckBox chkMostrarContraseña;
    
    private JRadioButton radioCliente;
    private JRadioButton radioEmpleado;
    

	public PanelOpcionesInicioSesion()
	{
		setLayout(new GridBagLayout());
		
		ButtonGroup buttonGroup = new ButtonGroup();
        // Creación de componentes
		radioCliente = new JRadioButton("Cliente");
        radioCliente.setSelected(true);
        radioEmpleado = new JRadioButton("Empleado");
        
        buttonGroup.add(radioCliente);
        buttonGroup.add(radioEmpleado);
        
        JLabel lblUsuario = new JLabel("Usuario:");
        txtUsuario = new JTextField(15);

        JLabel lblContraseña = new JLabel("Contraseña:");
        txtContraseña = new JPasswordField(15);

        chkMostrarContraseña = new JCheckBox("Mostrar Contraseña");
        chkMostrarContraseña.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Cambia el carácter de la contraseña según el estado del checkbox
                if (chkMostrarContraseña.isSelected()) {
                    txtContraseña.setEchoChar((char) 0);
                } else {
                    txtContraseña.setEchoChar('*');
                }
            }
        });
        

        btnIniciarSesion = new JButton("Iniciar Sesión");
        btnIniciarSesion.addActionListener(this);

        // Configuración del GridBagConstraints para el diseño GridBagLayout
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Márgenes externos
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(radioCliente, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        add(radioEmpleado, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(lblUsuario, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(txtUsuario, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(lblContraseña, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(txtContraseña, gbc);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2; // Ocupa dos columnas
        add(chkMostrarContraseña, gbc);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2; // Ocupa dos columnas
        add(btnIniciarSesion, gbc);
    }
	
	@Override
    public void actionPerformed(ActionEvent e) 
	{
        String usuario = txtUsuario.getText();
        char[] contraseña = txtContraseña.getPassword();
        String contraseñaString = new String(contraseña);
        int opcion = 1; //Cliente seleccionado por deafult.
        if (radioEmpleado.isSelected())
        {
        	opcion = 2;
        }
        try {
			InicioSesionInterfaz.iniciarSesion(usuario,contraseñaString, opcion);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
}
