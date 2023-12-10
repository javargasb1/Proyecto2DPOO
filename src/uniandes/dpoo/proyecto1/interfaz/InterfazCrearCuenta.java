package uniandes.dpoo.proyecto1.interfaz;

import javax.swing.*;

import uniandes.dpoo.proyecto1.modelo.ConductorAdicional;
import uniandes.dpoo.proyecto1.modelo.LicenciaConduccion;
import uniandes.dpoo.proyecto1.modelo.Sede;
import uniandes.dpoo.proyecto1.modelo.Seguro;
import uniandes.dpoo.proyecto1.modelo.Sistema;
import uniandes.dpoo.proyecto1.modelo.Usuario;
import uniandes.dpoo.proyecto1.modelo.Vehiculo;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Enumeration;

public class InterfazCrearCuenta extends JFrame 
{
	private String tipoUsuario;
	
	private JRadioButton radioCliente;
	private JRadioButton radioEmpleado;
	private JRadioButton radioAdmin;
	private JRadioButton radioActual;
	private JTextField txtNombre;
	private JTextField txtCelular;
	private JTextField txtEmail;
	private JTextField txtFechaNacimiento; 
	private JTextField txtNumeroDocumento;
	private JTextField txtNacionalidad;

	private JTextField txtNumLicencia;

	private JTextField txtPaisExpedicion;

	private JTextField txtFechaVencimiento;

	private JTextField txtNumTarjeta;

	private JTextField txtFechaVencimientoTarjeta;

	private JTextField txtLogin;

	private JTextField txtClave;
	
	private Sistema sistema;

	private JTextField txtUbicacion;

    public InterfazCrearCuenta(String tipoUsuario) {
        super("Registro de Cuenta");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(700, 500);
        setLocationRelativeTo(null);
        
        if (tipoUsuario == null)
        {
        	InterfazSeleccionTipoUsuario();
        }
        else
        {
        	this.tipoUsuario = tipoUsuario;
        }
       
        
        // Panel del Empleado con BorderLayout
        JPanel PanelPrincipal = new JPanel(new BorderLayout());

        // Mensaje y Logo en un panel en la región NORTH
        JPanel panelNorte = new JPanel(new BorderLayout());

        // Mensaje "Comencemos tu reserva"
        JLabel lblMensaje = new JLabel("Vamos a Crear tu Cuenta!!!");
        lblMensaje.setHorizontalAlignment(JLabel.CENTER);

        // Logo de la empresa (ajustado al tamaño deseado)
        ImageIcon logo = new ImageIcon(new ImageIcon("imagenes/LogoMovilRide.png").getImage().getScaledInstance(300, 200, Image.SCALE_SMOOTH));
        JLabel lblLogo = new JLabel(logo);
        lblLogo.setHorizontalAlignment(JLabel.LEFT);

        // Agregar mensaje y logo al panelNorte
        panelNorte.add(lblMensaje, BorderLayout.CENTER);
        panelNorte.add(lblLogo, BorderLayout.WEST);
        
        if (tipoUsuario.equals("Cliente"))
        {	     	
	        // Panel interno con GridBagLayout para otras componentes
	        JPanel panel = new JPanel(new GridBagLayout());
	        GridBagConstraints gbc = new GridBagConstraints();
	        gbc.insets = new Insets(5, 5, 5, 5);
	
	        // Componentes      
	        addToPanel(panel,new JLabel("Nombre:"),gbc,0,0);
	        txtNombre = new JTextField(20);
	        addToPanel(panel,txtNombre,gbc,1,0);
	
	        addToPanel(panel,new JLabel("Celular de Contacto:"),gbc,0,1);
	        txtCelular = new JTextField(15);
	        addToPanel(panel,txtCelular,gbc,1,1);
	
	        addToPanel(panel,new JLabel("Email de Contacto:"),gbc,0,2);
	        txtEmail = new JTextField(20);
	        addToPanel(panel,txtEmail,gbc,1,2);
	
	        addToPanel(panel,new JLabel("Fecha de Nacimiento (AAAA-MM-DD):"),gbc,0,3);
	        txtFechaNacimiento = new JTextField(15);
	        addToPanel(panel,txtFechaNacimiento,gbc,1,3);
	
	        addToPanel(panel,new JLabel("Número de Documento:"),gbc,0,4);
	        txtNumeroDocumento = new JTextField(15);
	        addToPanel(panel,txtNumeroDocumento,gbc,1,4);
	        
	        addToPanel(panel,new JLabel("Nacionalidad:"),gbc,0,5);
	        txtNacionalidad = new JTextField(15);
	        addToPanel(panel,txtNacionalidad,gbc,1,5);
	        
	        addToPanel(panel,new JLabel("Porfavor Ingrese los datos de su licencia de conduccion: "),gbc,0,6);
	        addToPanel(panel,new JLabel("Número de Licencia"),gbc,0,7);
	        txtNumLicencia = new JTextField(15);
	        addToPanel(panel,txtNumLicencia,gbc,1,7);
	        
	        addToPanel(panel,new JLabel("Pais de Expedición"),gbc,0,8);
	        txtPaisExpedicion = new JTextField(15);
	        addToPanel(panel,txtPaisExpedicion,gbc,1,8);
	        
	        addToPanel(panel,new JLabel("Fecha de Vencimiento (AAAA-MM-DD):"),gbc,0,9);
	        txtFechaVencimiento = new JTextField(15);
	        addToPanel(panel,txtFechaVencimiento,gbc,1,9);
	        
	        addToPanel(panel,new JLabel("Porfavor Ingrese los datos de pago: "),gbc,0,10);
	        
	        addToPanel(panel,new JLabel("Número tajeta de Credito"),gbc,0,11);
	        txtNumTarjeta = new JTextField(15);
	        addToPanel(panel,txtNumTarjeta,gbc,1,11);
	        
	        addToPanel(panel,new JLabel("Fecha de Vencimiento (AAAA-MM-DD"),gbc,0,12);
	        txtFechaVencimientoTarjeta = new JTextField(15);
	        addToPanel(panel,txtFechaVencimientoTarjeta,gbc,1,12);
	        
	        addToPanel(panel,new JLabel("Nuevo Login"),gbc,0,13);
	        txtLogin = new JTextField(15);
	        addToPanel(panel,txtLogin,gbc,1,13);
	        
	        addToPanel(panel,new JLabel("Nueva clave"),gbc,0,14);
	        txtClave = new JTextField(15);
	        addToPanel(panel,txtClave,gbc,1,14);
	      
	        // Botones
	        
	        JButton btnRegistrar = new JButton("Registrar");
	        btnRegistrar.addActionListener(new ActionListener(){
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                String nombre = txtNombre.getText();
	                String celular = txtCelular.getText();
	                String email = txtEmail.getText();
	                ArrayList<String> datosContacto = new ArrayList<String>();
	    			datosContacto.add(celular);
	    			datosContacto.add(email);
	                LocalDate nacimiento = LocalDate.parse(txtFechaNacimiento.getText());
	                String numeroDocumento = txtNumeroDocumento.getText();
	                String nacionalidad = txtNacionalidad.getText();
	                String licencia1 = txtNumLicencia.getText();
	                String licencia2 = txtPaisExpedicion.getText();
	                String licencia3 = txtFechaVencimiento.getText();	                
	                LicenciaConduccion licencia = new LicenciaConduccion(licencia1, licencia2, licencia3);
	                
	                String pago1 = txtNumTarjeta.getText();
	                String pago2 = txtFechaVencimientoTarjeta.getText();
	    			ArrayList<String> pago = new ArrayList<String>();
	    			pago.add(pago1);
	    			pago.add(pago2);
	    			
	    			String login = txtLogin.getText();
	    			String clave = txtClave.getText();
	                
	    			try {
	    				sistema = new Sistema();
						sistema.crearCuentaCliente(nombre,login,clave,datosContacto,nacimiento,
								nacionalidad,numeroDocumento,licencia,pago);
						String mensaje = "Se ha creado su cuenta de Cliente con exito!\n";
						JOptionPane.showMessageDialog(null, mensaje, "Éxito", JOptionPane.INFORMATION_MESSAGE);
						dispose();
					} catch (IOException | ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
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
	        
	
	        // Agregar componentes al panel principal
	        PanelPrincipal.add(panelNorte, BorderLayout.NORTH);
	        PanelPrincipal.add(panel, BorderLayout.CENTER);
	
	        // Agregar el panel principal al diálogo
	        add(PanelPrincipal, BorderLayout.CENTER);
	
	        JPanel btnPanel = new JPanel();
	        btnPanel.add(btnRegistrar);
	        btnPanel.add(btnCancelar);	
	        add(btnPanel, BorderLayout.SOUTH);
	        setSize(700,800);
	        setLocationRelativeTo(null);
	        setVisible(true);
        }
        
        else if (tipoUsuario.equals("Empleado"))
        {
	        // Panel interno con GridBagLayout para otras componentes
	        JPanel panel = new JPanel(new GridBagLayout());
	        GridBagConstraints gbc = new GridBagConstraints();
	        gbc.insets = new Insets(5, 5, 5, 5);
	
	        // Componentes  
	        addToPanel(panel, new JLabel("Tipo Usuario: "), gbc, 0, 0);
	        ButtonGroup group = new ButtonGroup();
	        radioAdmin = new JRadioButton("AdministradoLocal");
	        radioActual = new JRadioButton("ActualizadorEstadoVehiculo");
	        group.add(radioAdmin);
	        group.add(radioActual);
	        JPanel radioPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	        radioPanel.add(radioAdmin);
	        radioPanel.add(radioActual);
	        addToPanel(panel, radioPanel, gbc, 1, 0);
	        
	        addToPanel(panel,new JLabel("Nombre:"),gbc,0,1);
	        txtNombre = new JTextField(20);
	        addToPanel(panel,txtNombre,gbc,1,1);
	
	        addToPanel(panel,new JLabel("Ubicación:"),gbc,0,2);
	        txtUbicacion = new JTextField(15);
	        addToPanel(panel,txtUbicacion,gbc,1,2);
	
	        addToPanel(panel,new JLabel("login:"),gbc,0,3);
	        txtLogin = new JTextField(20);
	        addToPanel(panel,txtLogin,gbc,1,3);
	
	        addToPanel(panel,new JLabel("clave:"),gbc,0,4);
	        txtClave = new JTextField(15);
	        addToPanel(panel,txtClave,gbc,1,4);
	       
	        // Botones
	        
	        JButton btnRegistrar = new JButton("Registrar");
	        btnRegistrar.addActionListener(new ActionListener(){
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	int opcion = 1;
	            	if (radioActual.isSelected())
	            	{
	            		opcion = 2;
	            	}
	            	
	            	String nombre = txtNombre.getText();
	            	String ubicacion = txtUbicacion.getText();
	            	String login = txtLogin.getText();
	            	String clave = txtClave.getText();
	            
					try {
						sistema = new Sistema();
						sistema.crearCuentaEmpleado(opcion, login, clave, nombre, ubicacion);
						String mensaje = "Se ha creado su cuenta de Empleado con exito!\n";
						JOptionPane.showMessageDialog(null, mensaje, "Éxito", JOptionPane.INFORMATION_MESSAGE);
						dispose();
					} catch (IOException | ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
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
	        // Agregar componentes al panel principal
	        PanelPrincipal.add(panelNorte, BorderLayout.NORTH);
	        PanelPrincipal.add(panel, BorderLayout.CENTER);
	
	        // Agregar el panel principal al diálogo
	        add(PanelPrincipal, BorderLayout.CENTER);
	
	        JPanel btnPanel = new JPanel();
	        btnPanel.add(btnRegistrar);
	        btnPanel.add(btnCancelar);	
	        add(btnPanel, BorderLayout.SOUTH);
	        setLocationRelativeTo(null);
	        setVisible(true);
        }
    }
    
    private void addToPanel(JPanel panel, JComponent component, GridBagConstraints gbc, int gridx, int gridy) {
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        panel.add(component, gbc);
    }
    
    
    public void InterfazSeleccionTipoUsuario() 
    {
    	String[] opciones = {"Cliente", "Empleado"};
	    int seleccion = JOptionPane.showOptionDialog(
	            null,
	            "Seleccione su tipo de usuario:",
	            "Selección de Usuario",
	            JOptionPane.DEFAULT_OPTION,
	            JOptionPane.QUESTION_MESSAGE,
	            null,
	            opciones,
	            opciones[0]
	    );
	
	    // Validar la selección del usuario
	    if (seleccion == JOptionPane.CLOSED_OPTION) 
	    {
	        // Si el usuario cierra la ventana, salir de la aplicación
	        System.exit(0);
	    } 
	    else 
	    {
	        // Llamar al método de registro con el tipo de usuario seleccionado
	        tipoUsuario = opciones[seleccion];  
	    }
    }
    

}