package fr.adaming.service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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

import fr.adaming.model.Commande;
import fr.adaming.model.LigneCommande;

public class PdfClient {
	
public static void Facture(Commande com) throws FileNotFoundException, DocumentException {
		
		// Mise en forme du document
		Document document = new Document(PageSize.A4, 75, 75,75, 75);
		
		// Writer pour ecrire sur document et choix du chemin d'accès+nom du document
		PdfWriter writer=PdfWriter.getInstance(document, new FileOutputStream("C:\\Users\\int0348\\Desktop\\FactureEshop.pdf"));
		
		// Ouverture du pdf
		document.open();
		
		// Création du contenu du document : methode Paragraphe
		Paragraph titre = new Paragraph("Facture", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, Font.UNDERLINE, new CMYKColor(74,255,178,0)));
		titre.setSpacingAfter(20);
		
		// Ajout du titre au document
		document.add(titre);

		// Ajout de la date de création du document
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

		Paragraph dateCom=new Paragraph("Commande du "+format.format(com.getDateCommande()), FontFactory.getFont(FontFactory.HELVETICA));
		dateCom.setSpacingAfter(5);
		document.add(dateCom);
		
		PdfPTable Fac= new PdfPTable(5);
		Fac.setSpacingAfter(10);
		Fac.addCell("Produit");
		Fac.addCell("Description");
		Fac.addCell("Prix à l'unité");
		Fac.addCell("Quantité");
		Fac.addCell("Prix");
		List<LigneCommande> listeCommande = com.getListeLignesCommandes();
		for (LigneCommande ligne:listeCommande){
			Fac.addCell(ligne.getProduit().getDesignation());
			Fac.addCell(ligne.getProduit().getDescription());
			Fac.addCell(String.valueOf(ligne.getProduit().getPrix()));
			Fac.addCell(String.valueOf(ligne.getQuantite()));
			Fac.addCell(String.valueOf(ligne.getPrix()));
		}
		Fac.addCell(" ");
		Fac.addCell(" ");
		Fac.addCell(" ");
		Fac.addCell("Prix total ");
		Fac.addCell(String.valueOf(com.getDateCommande()));
		document.add(Fac);
		
		Calendar cd=Calendar.getInstance();
		cd.setTime(com.getDateCommande());
		cd.add(Calendar.DAY_OF_YEAR, 14);
		Date dateRecup =cd.getTime();
		
		Paragraph recup=new Paragraph("Nous vous remercions de votre commande. Vous pourrez venir chercher vos produits à partir du "+format.format(dateRecup)+".", FontFactory.getFont(FontFactory.HELVETICA));
		recup.setSpacingAfter(5);
		document.add(recup);
		
		Paragraph fin=new Paragraph("A bientôt", FontFactory.getFont(FontFactory.HELVETICA));
		fin.getExtraParagraphSpace();
		fin.setSpacingAfter(5);
		document.add(fin);
		

		Paragraph signature=new Paragraph("Le service client", FontFactory.getFont(FontFactory.HELVETICA));
		signature.setAlignment(signature.ALIGN_CENTER);
		signature.setSpacingAfter(5);
		document.add(signature);
		
		// Fermeture du pdf
		document.close();
				
				
	}
	

}
