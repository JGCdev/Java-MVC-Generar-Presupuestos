package clases;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.*;

/**
 * Example of using the iText library to work with PDF documents on Java, 
 * lets you create, analyze, modify and maintain documents in this format.
 * Ejemplo de uso de la librería iText para trabajar con documentos PDF en Java, 
 * nos permite crear, analizar, modificar y mantener documentos en este formato.
 *
 * @author xules You can follow me on my website http://www.codigoxules.org/en
 * Puedes seguirme en mi web http://www.codigoxules.org
 */
public class GeneratePDFileIText {

	 /** Path to the resulting PDF file. */
    public static final String RESULT
        = "fichero.pdf";
 
    /**
     * Crea el documento PDF.
     * @param recibe nombre de archivo y presupuesto para obtener datos
     * @throws    DocumentException 
     * @throws    IOException 
     */
    public void createPdf(String filename, Presupuesto pres)
	throws DocumentException, IOException {
    	
    	Font boldFont = FontFactory.getFont("arial", 11,Font.BOLD);
    	Font normalFont = FontFactory.getFont("arial", 10);
    	
    	
        // Se crea el documento
        Document documento = new Document();

        // Se crea el OutputStream para el fichero donde queremos dejar el pdf.
        FileOutputStream ficheroPdf = new FileOutputStream(filename);

        // Se asocia el documento al OutputStream y se indica que el espaciado entre
        // lineas sera de 20. Esta llamada debe hacerse antes de abrir el documento
        PdfWriter.getInstance(documento,ficheroPdf).setInitialLeading(20);

        documento.open();

    	
    	Paragraph n = new Paragraph( "PRESUPUESTO",
				FontFactory.getFont("arial",   // fuente
				24,                            // tamaño
				Font.BOLD,                   // estilo
				BaseColor.DARK_GRAY));
    	n.setAlignment(Element.ALIGN_RIGHT);
    	documento.add(n);
    	
    	Image foto3 = Image.getInstance(getClass().getResource("/imagenes/logonuevogrande.bmp"));
    	foto3.setAbsolutePosition(50, 700);
     	//foto3.scaleToFit(130, 130);
     	documento.add(foto3);

        
        //Tabla para
        PdfPTable tablaPara = new PdfPTable(4);
        tablaPara.setWidths(new float[] { 1,2,1,1 });
        tablaPara.setWidthPercentage(65);
        tablaPara.completeRow();
        tablaPara.addCell(new Phrase("Para:", boldFont  ));
        tablaPara.addCell(new Phrase(pres.getPara(), normalFont  ));
        tablaPara.addCell(new Phrase("Nº Presup:", boldFont  ));
        tablaPara.addCell(new Phrase("018-"+Integer.toString(pres.getId()), normalFont  ));
        tablaPara.addCell(new Phrase("Email:", boldFont  ));
        tablaPara.addCell(new Phrase(pres.getEmail(), normalFont  ));
        tablaPara.addCell(new Phrase("Fecha:", boldFont  ));
        tablaPara.addCell(new Phrase(pres.getFechaString(), normalFont  ));
        tablaPara.addCell(new Phrase("Teléfono:", boldFont  ));
        tablaPara.addCell(new Phrase(pres.getTelefono(), normalFont  ));
        tablaPara.addCell(new Phrase("Validez:", boldFont  ));
        tablaPara.addCell(new Phrase(pres.getValidezString(), normalFont  ));
        
        tablaPara.setSpacingBefore(20);
        tablaPara.setHorizontalAlignment(Chunk.ALIGN_RIGHT);

        documento.add(tablaPara);
        
        //Añadir items
        PdfPTable tabla = new PdfPTable(5);
        tabla.setWidthPercentage(100);
        tabla.setWidths(new float[] { 10,45,15,15,15 });
        tabla.completeRow();
        tabla.addCell(new Phrase("REF", boldFont  ));
        tabla.addCell(new Phrase("DESCRIPCIÓN", boldFont  ));
        tabla.addCell(new Phrase("PRECIO UD", boldFont  ));
        tabla.addCell(new Phrase("UDS", boldFont  ));
        tabla.addCell(new Phrase("TOTAL", boldFont  ));
        
        int size = pres.getListaServicios().size();
        
        for (int i = 0; i < size; i++)
        {
        	tabla.addCell(Integer.toString(pres.getListaServicios().get(i).getRef()));
        	tabla.addCell(pres.getListaServicios().get(i).getDescripcion());
        	tabla.addCell(String.valueOf(pres.getListaServicios().get(i).getpUnidad()));
        	tabla.addCell(Integer.toString(pres.getListaServicios().get(i).getUnidades()));
        	tabla.addCell(String.valueOf(pres.getListaServicios().get(i).getpUnidad()*pres.getListaServicios().get(i).getUnidades()));
        }
        
        tabla.setSpacingBefore(25);
        
        
        tabla.setHorizontalAlignment(Chunk.ALIGN_LEFT);
        documento.add(tabla);
        
        //Añadir tabla con condiciones y firma
        PdfPTable tablaPrecio = new PdfPTable(2);
        tablaPrecio.setWidthPercentage(30);
        tablaPrecio.completeRow();
        tablaPrecio.addCell(new Phrase("Subtotal", boldFont  ));
        tablaPrecio.addCell(new Phrase(String.valueOf(pres.getSubtotal())+"€", boldFont  ));
        tablaPrecio.addCell(new Phrase("Descuento", boldFont  ));
        tablaPrecio.addCell(new Phrase(String.valueOf(pres.getDescuento()), boldFont  ));
        tablaPrecio.addCell(new Phrase("Total SIN IVA", boldFont  ));
        tablaPrecio.addCell(new Phrase(String.valueOf(pres.getSubtotal()-pres.getDescuento())+ "€", boldFont  ));
        
        tablaPrecio.setHorizontalAlignment(Chunk.ALIGN_RIGHT);
        documento.add(tablaPrecio);
        
        
        PdfPTable tablaFinal = new PdfPTable(2);
        tablaFinal.setWidthPercentage(100);
        tablaFinal.setWidths(new float[] {6,4 });
        tablaFinal.completeRow();
        PdfPCell celdaTexto2 = new PdfPCell(new Paragraph( "Condiciones",
				FontFactory.getFont("arial",   // fuente
				12,                            // tamaño
				Font.BOLD,                   // estilo
				BaseColor.DARK_GRAY)));
        celdaTexto2.setBorderColor(BaseColor.WHITE);
        tablaFinal.addCell(celdaTexto2);
        
        PdfPCell celdaTexto3 = new PdfPCell(new Paragraph( "Firma",
				FontFactory.getFont("arial",   // fuente
				12,                            // tamaño
				Font.BOLD,                   // estilo
				BaseColor.DARK_GRAY)));
        celdaTexto3.setBorderColor(BaseColor.WHITE);
        celdaTexto3.setHorizontalAlignment(Chunk.ALIGN_RIGHT);
        celdaTexto3.setPaddingRight(28);
        tablaFinal.addCell(celdaTexto3);
        
        PdfPCell celdaTexto4 = new PdfPCell(new Paragraph( "50% antes de comenzar, 50% al finalizar\n\nFinalización estimada: "+ pres.getFinalizacionEstimada() +" días",
				FontFactory.getFont("arial",   // fuente
				10,                            // tamaño
				Font.NORMAL,                   // estilo
				BaseColor.DARK_GRAY)));
        celdaTexto4.setBorderColor(BaseColor.WHITE);
        celdaTexto4.setPaddingTop(5);
        tablaFinal.addCell(celdaTexto4);
        
        Image foto2 = Image.getInstance(getClass().getResource("/imagenes/firma2.jpg"));
    	foto2.scaleToFit(75, 75);
    	PdfPCell celdaFoto2 = new PdfPCell(foto2);
    	celdaFoto2.setHorizontalAlignment(Image.ALIGN_RIGHT);
        celdaFoto2.setBorderColor(BaseColor.WHITE);
        celdaFoto2.setPaddingTop(5);
        tablaFinal.addCell(celdaFoto2);
        
        tablaFinal.setSpacingBefore(15);
        documento.add(tablaFinal);
        
        //Cerramos el documento
        documento.close();
        
        
    }

	
}