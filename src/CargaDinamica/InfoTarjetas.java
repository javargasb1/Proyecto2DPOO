package CargaDinamica;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import uniandes.dpoo.proyecto1.interfaz.LogoPanel;

public class InfoTarjetas extends JFrame{
	
	private ArrayList<InterfaceTarjetas> tarjets;
	private JPanel optionsPanel;
	private LogoPanel logoPanel;
	private int anchoVentana = 800;
	private int largoVentana = 500;

	public static void main(String[] args)
	{
		InfoTarjetas tj = new InfoTarjetas();

	}
	
	public InfoTarjetas()
	{
		setLayout(new BorderLayout());
		logoPanel = new LogoPanel();
		logoPanel.setPreferredSize(new Dimension(anchoVentana/2,largoVentana/2));
		add(logoPanel,BorderLayout.WEST);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        add(optionsPanel, BorderLayout.CENTER);
        cargarTarjetasDeTextFile("./data/archivoTarjetas.txt");
        setVisible(true);
	}

	private void cargarTarjetasDeTextFile(String nombreFile) {
		try
		{
			
			FileReader fileReader = new FileReader(nombreFile);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String nombreTarjeta;
            List<String> nombreTarjetas = new ArrayList<>();

            while ((nombreTarjeta = bufferedReader.readLine()) != null) 
            {
            	nombreTarjetas.add(nombreTarjeta);
            }

            bufferedReader.close();
            
			BufferedReader br = new BufferedReader(new FileReader(nombreFile));
			tarjets = new ArrayList<InterfaceTarjetas>();
			String linea = br.readLine();
			
			
			for (String name : nombreTarjetas) 
			{
                Class clase = Class.forName("CargaDinamica."+name);
                InfoTarjeta objeto = (InfoTarjeta) clase.getDeclaredConstructor().newInstance();
                
                JButton btn = new JButton(name);
                optionsPanel.add(btn);
                
			}
				
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
			String mensaje = "Error al leer el archivo!\"";
			JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
		}
	}		
}
