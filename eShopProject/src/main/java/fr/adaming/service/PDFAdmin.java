package fr.adaming.service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import fr.adaming.model.Produit;

public class PDFAdmin {
	
public static void Enregistrement (List<Produit> listeProduit) throws FileNotFoundException, DocumentException {
		
		// Création du document pdf
		Document doc = new Document(PageSize.A4, 50, 50, 50, 50);
		
		PdfWriter.getInstance(doc, new FileOutputStream("C:\\Users\\inti0426\\Desktop\\Produit.pdf"));
		
		doc.open();
		
		Paragraph titre = new Paragraph(new Paragraph("LISTE DES PRODUITS",FontFactory.getFont(FontFactory.TIMES, 30, Font.BOLD, new CMYKColor(45, 0, 85, 49))));
		titre.setSpacingAfter(20);
		doc.add(titre);
		
		// Création d'un tableau dans le pdf		
		PdfPTable table = new PdfPTable(5);
		
		
		// Contenu du tableau
		table.addCell("ID");
		table.addCell("Designation");
		table.addCell("Description");
		table.addCell("Quantité");
		table.addCell("Prix");
		
	
	   
	   for (Produit prd : listeProduit){
		   
		   table.addCell(String.valueOf(prd.getId()));
		   table.addCell(prd.getDesignation());
		   table.addCell(prd.getDescription());
		   table.addCell(String.valueOf(prd.getQuantite()));
		   table.addCell(String.valueOf(prd.getPrix()));
	   }
	   
	   doc.add(table);
	   
	   doc.close();
		
	}


}
