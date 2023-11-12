package uniandes.dpoo.proyecto1.interfaz;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class LogoPanel extends JPanel
{
	private BufferedImage imagen;

    public LogoPanel() 
    {
    	String LogoEmpresaPNG = "imagenes/LogoMovilRide.png";
        try {
            imagen = ImageIO.read(new File(LogoEmpresaPNG));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
    	super.paintComponent(g);
    	// Establece el color de fondo
        g.setColor(Color.WHITE); // Puedes cambiar el color según tus preferencias
        g.fillRect(0, 0, getWidth(), getHeight());
        
        if (imagen != null) {
            // Calcula el ancho y alto máximo permitido
            int maxWidth = getWidth() / 2;
            int maxHeight = getHeight() / 2;

            // Calcula el ancho y alto reales de la imagen
            int imageWidth = imagen.getWidth();
            int imageHeight = imagen.getHeight();

            // Calcula las nuevas dimensiones para ajustar la imagen en la mitad
            int newWidth = Math.min(imageWidth, maxWidth);
            int newHeight = Math.min(imageHeight, maxHeight);

            // Calcula la posición para centrar la imagen
            int x = (getWidth() - newWidth) / 2;
            int y = (getHeight() - newHeight) / 2;

            // Dibuja la imagen ajustada en la mitad del panel
            g.drawImage(imagen, x, y, newWidth, newHeight, this);
        }
    }
}
