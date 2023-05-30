package com.intecsec.java.basic.example.testcard.admission;


import com.intecsec.java.basic.example.testcard.admission.data.AdmissionDao;
import com.intecsec.java.basic.example.testcard.admission.data.Importer;
import com.intecsec.java.basic.example.testcard.admission.mail.Postman;
import com.intecsec.java.basic.example.testcard.admission.po.AdmissionTicket;
import com.intecsec.java.basic.example.testcard.admission.service.DocxTemplateToPdfImpl;
import com.intecsec.java.basic.example.testcard.admission.service.PdfTemplateImpl;

import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {
    	//获取data.xlsx的所有数据
    	List<AdmissionTicket> tickets = Importer.importFromExcel("data.xlsx");
    	
    	//将tickets导入到数据库中
    	AdmissionDao.addAll(tickets);
    			
    	//从数据库中重新加载数据
    	List<AdmissionTicket> tickets2 = AdmissionDao.getAll();
    	
    	//生成所有的模板(两种方法)    	
    	DocxTemplateToPdfImpl.generateAll(tickets2); //write docx, then convert to pdf
    	PdfTemplateImpl.generateAll(tickets2); //read pdf template and fill with values
    	
    	//发送邮件
    	new Postman().sendAll(tickets2);
    }   
}
