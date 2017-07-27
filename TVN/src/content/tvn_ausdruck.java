/*******************************************************************************
 * Copyright (c) 2001, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package content;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;


import allgemein.datumzeit;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.Barcode128;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;


public class tvn_ausdruck {
	
	public tvn_ausdruck(String mat_auswahl,ArrayList daten){
		
		int y_pos,zeilen_counter=1,seiten_counter=1,x_pos;
		
    	

		
		
		datumzeit dz = new datumzeit();
		try {
			File f = File.createTempFile("tvn", ".pdf");
			Document document = new Document();
			FileOutputStream fos = new FileOutputStream(f);
			PdfWriter writer = PdfWriter.getInstance(document, fos);
			document.setPageSize(PageSize.A4);//Querformat = .rotate()
			
			// step 3: we open the document
	        document.open();
	     // step 4: we add a paragraph to the document
            PdfContentByte cb = writer.getDirectContent();
            BaseFont bf = null;
            try {
				bf = BaseFont.createFont(BaseFont.HELVETICA,BaseFont.CP1250, BaseFont.EMBEDDED);
			} catch (IOException e) {

			}
			Image logo = Image.getInstance(getClass().getClassLoader().getResource("bilder/logo.jpg"));
			logo.scalePercent(25); //auf 40% verkleinern
            logo.setAbsolutePosition(30,803);
            document.add(logo);
			//Auswahl
            cb.setFontAndSize(bf,18);
            cb.beginText();
            cb.setTextMatrix(170,810);
            cb.showText("Teile-Verwendungsnachweis");
            //Rückverfolgung
            cb.setFontAndSize(bf,12);
            cb.setTextMatrix(50,785);
            cb.showText("Auswahl: "+mat_auswahl);
            
            //Seitenzahl
            cb.setFontAndSize(bf,8);
            cb.setTextMatrix(450,785);
            cb.showText(dz.datum_deutsch()+" "+dz.zeit_deutsch()+" Seite: "+seiten_counter);
            cb.endText();
              
            //oben
            PdfContentByte balken = writer.getDirectContentUnder();
            balken.setRGBColorFill(184,190,204);
            balken.rectangle(150,782,430,50);//Linke Ecke unten: x_pos   y_pos  Rechte Ecke oben: Länge höhe
            balken.fill();
            //Kopfzeile
            //unten
            balken = writer.getDirectContentUnder();
            balken.setRGBColorFill(206,209,220);
            balken.rectangle(10,782,570,20);
            balken.fill();
            //Fußzeile
            //links
            balken.setRGBColorFill(184,190,204);
            balken.rectangle(10,1,180,30);
            balken.fill();
            //Fußzeile
            //rechts
            balken.setRGBColorFill(206,209,220);
            balken.rectangle(180,1,400,30);//652
            balken.fill();
            //feste Texte
            //copyright
            cb.setFontAndSize(bf,6);
            cb.beginText();
            cb.setTextMatrix(190,20);//5
            cb.showText("Alle Rechte bei Nexans autoelectric GmbH, auch für Schutzrechtsanmeldungen");
            cb.setTextMatrix(190,25);//12
            cb.showText("Alle Verfügungsbefugnisse, wie Kopie und Weitergaberecht bei uns. Schutzvermerk nach DIN 34 beachten!"); 
            cb.setFontAndSize(bf,10);

            cb.setTextMatrix(20,750);
            cb.showText("PA-Nr.");
            cb.setTextMatrix(120,750);
            cb.showText("Materialnummer");
            cb.setTextMatrix(220,750);
            cb.showText("Menge");
            cb.setTextMatrix(320,750);
            cb.showText("Artikelnummer");
            cb.endText();
            
            y_pos = 730;
            x_pos = 20;
            Iterator it = daten.iterator();
            
            
            while(it.hasNext()){
            	cb.setFontAndSize(bf,7);
                cb.beginText();
            	cb.setTextMatrix(x_pos,y_pos);
            	cb.showText((String) it.next());
            	x_pos = x_pos + 100;
            	cb.setTextMatrix(x_pos,y_pos);
            	cb.showText((String) it.next());
            	x_pos = x_pos + 100;
            	cb.setTextMatrix(x_pos,y_pos);
            	cb.showText(it.next().toString());
            	x_pos = x_pos + 100;
            	cb.setTextMatrix(x_pos,y_pos);
            	cb.showText((String) it.next());
            	y_pos = y_pos - 10;
            	zeilen_counter++;
            	x_pos = 20;
            	cb.endText();
            	if(zeilen_counter > 68){
            		document.newPage();
                    seiten_counter++;
                    y_pos = 730;
                    zeilen_counter = 1;
        			logo = Image.getInstance(getClass().getClassLoader().getResource("bilder/logo.jpg"));
        			logo.scalePercent(25); //auf 40% verkleinern
                    logo.setAbsolutePosition(30,803);
                    document.add(logo);
        			//Auswahl
                    cb.setFontAndSize(bf,18);
                    cb.beginText();
                    cb.setTextMatrix(170,810);
                    cb.showText("Teile-Verwendungsnachweis");
                    //Rückverfolgung
                    cb.setFontAndSize(bf,12);
                    cb.setTextMatrix(50,785);
                    cb.showText("Auswahl: "+mat_auswahl);
                    
                    //Seitenzahl
                    cb.setFontAndSize(bf,8);
                    cb.setTextMatrix(450,785);
                    cb.showText(dz.datum_deutsch()+" "+dz.zeit_deutsch()+" Seite: "+seiten_counter);
                    cb.endText();
                      
                    //oben
                    balken = writer.getDirectContentUnder();
                    balken.setRGBColorFill(184,190,204);
                    balken.rectangle(150,782,430,50);//Linke Ecke unten: x_pos   y_pos  Rechte Ecke oben: Länge höhe
                    balken.fill();
                    //Kopfzeile
                    //unten
                    balken = writer.getDirectContentUnder();
                    balken.setRGBColorFill(206,209,220);
                    balken.rectangle(10,782,570,20);
                    balken.fill();
                    //Fußzeile
                    //links
                    balken.setRGBColorFill(184,190,204);
                    balken.rectangle(10,1,180,30);
                    balken.fill();
                    //Fußzeile
                    //rechts
                    balken.setRGBColorFill(206,209,220);
                    balken.rectangle(180,1,400,30);//652
                    balken.fill();
                    //feste Texte
                    //copyright
                    cb.setFontAndSize(bf,6);
                    cb.beginText();
                    cb.setTextMatrix(190,20);//5
                    cb.showText("Alle Rechte bei Nexans autoelectric GmbH, auch für Schutzrechtsanmeldungen");
                    cb.setTextMatrix(190,25);//12
                    cb.showText("Alle Verfügungsbefugnisse, wie Kopie und Weitergaberecht bei uns. Schutzvermerk nach DIN 34 beachten!"); 
                    cb.setFontAndSize(bf,10);

                    cb.setTextMatrix(20,750);
                    cb.showText("PA-Nr.");
                    cb.setTextMatrix(120,750);
                    cb.showText("Materialnummer");
                    cb.setTextMatrix(220,750);
                    cb.showText("Menge");
                    cb.setTextMatrix(320,750);
                    cb.showText("Artikelnummer");
                    cb.endText();
            	}
            }
            
            
            //step 5: we close the document
            document.close();
            Runtime.getRuntime().exec("rundll32 SHELL32.DLL,ShellExec_RunDLL " +f);
			
			
		} catch (IOException e) {
			
		} catch (DocumentException e) {
			
		}
	}
	
}
