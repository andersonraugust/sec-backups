package br.udesc.defineBackups.util;

import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class XlsGenerator {
	

	public HSSFWorkbook wb;
	public HSSFSheet sheet;
	public HSSFCell cell;
	public HSSFRow row;
	public String fullFilePath;
	public String fileName;
	
	/**
	 * 
	 * @param fullFilePath inform the path starting and ending with '/'
	 * @param fileName inform a name without extension
	 */
	public XlsGenerator(String fullFilePath, String fileName) {
		this.fullFilePath = fullFilePath;
		this.fileName = fileName;
	}
	
    public void addRow() {
    	if (row == null)
			row = sheet.createRow(0);
    	else
    		row = sheet.createRow(row.getRowNum() + 1);
    }
    
    
    public void addColumn() {
		if (cell == null || row.getFirstCellNum() == -1)
			cell = row.createCell(0);
		else
			cell = row.createCell(cell.getCellNum() + 1);
    }
    
    public void openXLSFile() {
		String sheetName = "Sheet1";//name of sheet

		wb = new HSSFWorkbook();
		sheet = wb.createSheet(sheetName) ;
    }
    
	public void closeXLSFile() throws IOException {
		String excelFileName = this.fullFilePath + this.fileName + ".xls";
		
		FileOutputStream fileOut = new FileOutputStream(excelFileName);
		
		//write this workbook to an Outputstream.
		wb.write(fileOut);
		fileOut.flush();
		fileOut.close();
	}

}
