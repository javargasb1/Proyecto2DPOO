package uniandes.dpoo.proyecto1.InterfazCliente;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class OpcionesAutentificacion extends JPanel implements ActionListener
{
	private NuevaInterfazCliente padre;
	private JTextField txtUsuario;
    private JPasswordField txtContraseña;
    private JButton btnIniciarSesion;
    private JButton btnCrearCuenta;
    private JCheckBox chkMostrarContraseña;
    
    

	public OpcionesAutentificacion(NuevaInterfazCliente padre)
	{
		this.padre = padre;
		
		setLayout(new GridBagLayout());
		
		JLabel lblTitulo = new JLabel("Bienvenido Cliente :)");
		lblTitulo.setFont(new Font("Arial", Font.BOLD, 25));
        
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
        
        btnCrearCuenta = new JButton("Crear Cuenta");
        btnCrearCuenta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                padre.CrearCuenta();
            }
        });

        // Configuración del GridBagConstraints para el diseño GridBagLayout
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Márgenes externos
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2; // Ocupa dos columnas
        add(lblTitulo,gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
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
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2; // Ocupa dos columnas
        add(btnCrearCuenta,gbc);
    }
	
	@Override
    public void actionPerformed(ActionEvent e) 
	{
        String usuario = txtUsuario.getText();
        char[] contraseña = txtContraseña.getPassword();
        String contraseñaString = new String(contraseña);
        int opcion = 1; // 1 significa Cliente
		try {
			padre.IniciarSesion(usuario,contraseñaString,opcion);
		} catch (IOException | ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	
		
	}
	
}

