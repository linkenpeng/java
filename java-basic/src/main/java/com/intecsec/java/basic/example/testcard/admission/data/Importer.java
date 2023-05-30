package com.intecsec.java.basic.example.testcard.admission.data;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.intecsec.java.basic.example.testcard.admission.po.AdmissionTicket;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class Importer {
	public static List<AdmissionTicket> importFromExcel(String fileName) throws IOException {
		
		InputStream in = Importer.class.getClassLoader().getResourceAsStream(fileName);
		XSSFWorkbook workbook = new XSSFWorkbook(in);
		XSSFSheet sheet = workbook.getSheetAt(0);
		
        List<AdmissionTicket> admissionTickets = new ArrayList<>();
        
        int lastRowNum = sheet.getLastRowNum();
        for (int i = 1; i <= lastRowNum; i++) {
            XSSFRow row = sheet.getRow(i);
            AdmissionTicket admissionTicket = new AdmissionTicket();
            int colIndex = 0;
            XSSFCell cell = row.getCell(colIndex++);
            admissionTicket.setName(getStringValue(cell));

            cell = row.getCell(colIndex++);
            admissionTicket.setCandidateNumber(getStringValue(cell));

            cell = row.getCell(colIndex++);
            admissionTicket.setIdNumber(getStringValue(cell));

            cell = row.getCell(colIndex++);
            admissionTicket.setGender(getStringValue(cell));

            cell = row.getCell(colIndex++);
            admissionTicket.setSubject(getStringValue(cell));

            cell = row.getCell(colIndex++);
            admissionTicket.setAddress(getStringValue(cell));

            cell = row.getCell(colIndex++);
            admissionTicket.setTime(getStringValue(cell));

            cell = row.getCell(colIndex++);
            admissionTicket.setSeat(getStringValue(cell));

            cell = row.getCell(colIndex++);
            admissionTicket.setEmail(getStringValue(cell));

            admissionTickets.add(admissionTicket);
        }
        workbook.close();
        
        return admissionTickets;
    }

    private static String getStringValue(Cell cell) {

    	if (cell == null) {
			return "";
		}
		String cellValue = "";
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_NUMERIC:
			if(HSSFDateUtil.isCellDateFormatted(cell)){
				Date d = HSSFDateUtil.getJavaDate(cell.getNumericCellValue());
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				return df.format(d);
			}
			else{
				DecimalFormat format = new DecimalFormat("####");
				cellValue = format.format(cell.getNumericCellValue());
			}
			break;
		case Cell.CELL_TYPE_STRING:
			cellValue = cell.getStringCellValue().trim().replaceAll("\\s{1,}", "");
			break;
		case Cell.CELL_TYPE_FORMULA:
			cellValue = String.valueOf(cell.getNumericCellValue());
		default:
			break;
		}
		return cellValue;
    }
}
