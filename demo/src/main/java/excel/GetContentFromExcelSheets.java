package excel;
// Java Program to Extract Content from a Excel sheet

// As we are reading the excel file, java.io package is
// compulsorily required
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// Below imports are required to access Apache POI
// The usermodel package maps HSSF low level structures to
// familiar workbook/sheet model
// org.apache.poi.hssf.usermodel
// But we are using higher excel formats hence,
// org.apache.poi.ss.usermodel is used To determine the type
// of cell content
import org.apache.poi.ss.usermodel.Cell;

// each and every row of excel is taken and stored in this
// row format
import org.apache.poi.ss.usermodel.Row;

// excel sheet is read in this sheet format
import org.apache.poi.ss.usermodel.Sheet;

// excel Workbook is read in this Workbook format
import org.apache.poi.ss.usermodel.Workbook;

// XSSFWorkbook denotes the API is for working with Excel
// 2007 and later.
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

// POJO class having 3 fields matching with the given excel
// class to assign the cell value once it is getting done to
// read from excel sheet It can be String/Boolean/Numeric
public class GetContentFromExcelSheets {
	private Object getCellValue(Cell cell)
	{
		switch (cell.getCellType()) {
		case STRING:
			return cell.getStringCellValue();

		case BOOLEAN:
			return cell.getBooleanCellValue();

		case NUMERIC:
			return cell.getNumericCellValue();
			
		default: break;
		}


		return null;
	}
	// Read the excel sheet contents and get the contents in
	// a list
	public List<Employee>
	readBooksFromExcelFile(String excelFilePath)
		throws IOException
	{
		List<Employee> listEmployees
			= new ArrayList<Employee>();
		FileInputStream inputStream
			= new FileInputStream(new File(excelFilePath));

		Workbook workbook = new XSSFWorkbook(inputStream);
		Sheet firstSheet = workbook.getSheetAt(0);
		Iterator<Row> iterator = firstSheet.iterator();

		while (iterator.hasNext()) {
			Row nextRow = iterator.next();
			Iterator<Cell> cellIterator
				= nextRow.cellIterator();
			Employee emp = new Employee();

			while (cellIterator.hasNext()) {
				Cell nextCell = cellIterator.next();
				int columnIndex = nextCell.getColumnIndex();

				switch (columnIndex) {
				case 0:
					emp.setEmployeeName(
						(String)getCellValue(nextCell));
					break;
				case 1:
					emp.setEmployeeDesignation(
						(String)getCellValue(nextCell));
					break;
				case 2:
					emp.setSalary(
						(Double)getCellValue(nextCell));
					break;
				default: System.out.println("fail"); break;
				}
			}
			listEmployees.add(emp);
		}
		inputStream.close();

		return listEmployees;
	}
}
