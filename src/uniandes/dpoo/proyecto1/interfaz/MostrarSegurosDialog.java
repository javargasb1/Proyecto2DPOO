package uniandes.dpoo.proyecto1.interfaz;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MostrarSegurosDialog extends JDialog
{
	private JCheckBox[] checkboxes;
	private ArrayList<Integer> listaOpciones;
	
	public MostrarSegurosDialog(JDialog parent,ArrayList<String> opciones) 
	{	
		super(parent, "Seleccionar Opciones", true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(parent);

        // Crear un panel con un BoxLayout vertical
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Crear un array de checkboxes
        checkboxes = new JCheckBox[opciones.size()];

        // Crear y agregar checkboxes al panel
        for (int i = 0; i < opciones.size(); i++) {
            checkboxes[i] = new JCheckBox(opciones.get(i));
            panel.add(checkboxes[i]);
        }

        // Crear botón "Aceptar"
        JButton btnAceptar = new JButton("Aceptar");
        btnAceptar.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
            	listaOpciones = new ArrayList<Integer>();
                // Obtener las opciones seleccionadas
            	for (int i = 0; i < checkboxes.length; i++) 
            	{
                    if (checkboxes[i].isSelected()) 
                    {      	
                    	String textOpcion = checkboxes[i].getText();
                        int posicionSeleccionada = Character.getNumericValue(textOpcion.charAt(0)) - 1;
                        listaOpciones.add(posicionSeleccionada);
                    }
            	}

                // Cerrar el diálogo
                dispose();
            }
        });

        // Crear botón "Cancelar"
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Cerrar el diálogo sin realizar ninguna acción
                dispose();
            }
        });

        // Crear un panel para los botones y agregarlo al diálogo
        JPanel btnPanel = new JPanel();
        btnPanel.add(btnAceptar);
        btnPanel.add(btnCancelar);

        // Agregar componentes al diálogo
        add(panel, BorderLayout.CENTER);
        add(btnPanel, BorderLayout.SOUTH);
        setVisible(true);
	}

	public JCheckBox[] getCheckboxes() {
		return checkboxes;
	}

	public ArrayList<Integer> getListaOpciones() 
	{
		return listaOpciones;
	}

}