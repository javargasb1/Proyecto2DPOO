package uniandes.dpoo.proyecto1.interfaz;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.PDPageContentStream.AppendMode;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import java.io.IOException;
import java.util.Date;


import java.io.IOException;

public class Factura {
	public static void generarFactura(String nombreCliente, String placaVehiculo) {
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);
            PDPageContentStream contentStream = new PDPageContentStream(document, page, AppendMode.APPEND, true);
            contentStream.setLeading(14.5f);
            contentStream.beginText();
            contentStream.newLineAtOffset(25, 700);
            contentStream.showText("Factura");
            contentStream.newLine();
            contentStream.showText("Cliente: " + nombreCliente);
            contentStream.newLine();
            contentStream.showText("Vehículo: " + placaVehiculo);
            contentStream.newLine();
            contentStream.showText("Fecha: " + new Date());
            contentStream.newLine();
            contentStream.endText();
            // Agregar firma del administrador como imagen
            PDImageXObject pdImage = PDImageXObject.createFromFile("data/firmaAdmin.png", document);
            contentStream.drawImage(pdImage, 25, 500, pdImage.getWidth() / 4, pdImage.getHeight() / 4);
            contentStream.close();
            // Guardar el documento como archivo PDF
            document.save("data/Factura");
            System.out.println("Factura generada correctamente en: " + "data/Factura");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
