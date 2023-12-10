package uniandes.dpoo.proyecto1.interfaz;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import uniandes.dpoo.proyecto1.modelo.AdministradorLocal;
import CargaDinamica.InfoTarjetas;

public class RealizarPagoTarjeta extends JDialog implements ActionListener
{

	private AdministradorLocal adminlocal;
	
	
	public RealizarPagoTarjeta(Frame panelAdministrador){
		String login = JOptionPane.showInputDialog("Ingrese su login: ");
		String clave = JOptionPane.showInputDialog("Ingrese su clave:  ");
		String nombre = JOptionPane.showInputDialog("Ingrese su nombre:  ");
		String ubicacion = JOptionPane.showInputDialog("Ingrese su ubicacion:  ");
		adminlocal = new AdministradorLocal(login,clave,nombre,ubicacion);
		int precio =Integer.parseInt( JOptionPane.showInputDialog("Digite el precio que debe pagar el cliente:  "));
		infoTarjetas = new InfoTarjetas();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
