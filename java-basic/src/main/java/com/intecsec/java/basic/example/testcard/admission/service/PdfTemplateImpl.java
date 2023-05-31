package com.intecsec.java.basic.example.testcard.admission.service;

import com.intecsec.java.basic.example.testcard.admission.po.AdmissionTicket;
import com.intecsec.java.basic.example.testcard.admission.zxing.QRCodeUtils;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PushbuttonField;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;


public class PdfTemplateImpl {
    
	public static void generateAll(List<AdmissionTicket> tickets) throws Exception
	{
		for(AdmissionTicket ticket : tickets)
		{
			generatePdf(ticket);
		}
	}
	
	public static void generatePdf(AdmissionTicket ticket) throws Exception {
        String resource = "pdf_template.pdf";
        System.setProperty("javax.xml.parsers.DocumentBuilderFactory",
                "com.sun.org.apache.xerces.internal.jaxp.DocumentBuilderFactoryImpl");
        PdfReader reader = null;
        PdfStamper pdfStamper = null;
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        
        try (InputStream inputStream = PdfTemplateImpl.class.getClassLoader().getResourceAsStream(resource)) {
            reader = new PdfReader(inputStream);
            pdfStamper = new PdfStamper(reader, os);
            AcroFields form = pdfStamper.getAcroFields();

            String fontPath = "/System/Library/Fonts/PingFang.ttc,0";
            // String fontPath = "C:\\Program Files (x86)\\Microsoft Office\\root\\VFS\\Fonts\\private\\STSONG.TTF";

            /*
            BaseFont baseFont = BaseFont
                    .createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
            */

            BaseFont baseFont = BaseFont.createFont(
                    fontPath,
					BaseFont.IDENTITY_H, BaseFont.EMBEDDED);

			
            form.addSubstitutionFont(baseFont);
            form.setField("candidateNumber", ticket.getCandidateNumber());
            form.setField("address", ticket.getAddress());
            form.setField("name", ticket.getName());
            form.setField("gender", ticket.getGender());
            form.setField("subject", ticket.getSubject());
            form.setField("time", ticket.getTime());
            form.setField("seat", ticket.getSeat());

            pdfStamper.setFormFlattening(true);
            byte[] zxingqrCode = QRCodeUtils.createZxingqrCode(ticket.getIdNumber());
            PushbuttonField ad = form.getNewPushbuttonFromField("qrcode");
            if (ad != null && zxingqrCode != null) {
                ad.setLayout(PushbuttonField.LAYOUT_ICON_ONLY);
                ad.setProportionalIcon(true);
                ad.setImage(Image.getInstance(zxingqrCode));
                form.replacePushbuttonField("qrcode", ad.getField());
            }
            pdfStamper.setFormFlattening(true);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pdfStamper != null) {
                    pdfStamper.close();
                }
                if (reader != null) {
                    reader.close();
                }
                os.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        FileOutputStream  fos = new FileOutputStream(new File(ticket.getCandidateNumber() + "-2.pdf"));
        fos.write(os.toByteArray());
        fos.close();
    }
}
