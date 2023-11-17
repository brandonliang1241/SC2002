package excel;
// Java Program to Extract Content from a Excel sheet

// As we are reading the excel file, java.io package is
// compulsorily required
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import java.time.*;
import java.util.Date;
import java.time.format.DateTimeFormatter;

// Below imports are required to access Apache POI
// The usermodel package maps HSSF low level structures to
// familiar workbook/sheet model
// org.apache.poi.hssf.usermodel
// But we are using higher excel formats hence,
// org.apache.poi.ss.usermodel is used To determine the type
// of cell content
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
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

import jxl.write.DateTime;

// POJO class having 3 fields matching with the given excel
// class to assign the cell value once it is getting done to
// read from excel sheet It can be String/Boolean/Numeric
public class GetStudent {
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
	public List<Student>
	readBooksFromExcelFileStudent(String excelFilePath)
		throws IOException
	{
		List<Student> listStudent
			= new ArrayList<Student>();
		FileInputStream inputStream
			= new FileInputStream(new File(excelFilePath));

		Workbook workbook = new XSSFWorkbook(inputStream);
		Sheet firstSheet = workbook.getSheetAt(0);
		Iterator<Row> iterator = firstSheet.iterator();
		while (iterator.hasNext()) {
			Row nextRow = iterator.next();
			Iterator<Cell> cellIterator
				= nextRow.cellIterator();
            Student stu = new Student("userId","password",Faculty.SCSE);

			while (cellIterator.hasNext()) {
				Cell nextCell = cellIterator.next();
				int columnIndex = nextCell.getColumnIndex();

				switch (columnIndex) {
				case 0:
					break;
				case 1:
                    String result = (String)getCellValue(nextCell);
					stu.setUserId(
						result.split("@",2)[0]);
					break;
				case 2:
					stu.setFacultyInfo
						(Faculty.valueOf((String)getCellValue(nextCell)));
					break;
                case 3:
                    //this would be the password
                    stu.setPassword(
                        (String)getCellValue(nextCell));
                    break;
				default: System.out.println("fail"); break;
				}
			}
			listStudent.add(stu);
		}
        workbook.close();
		inputStream.close();

		return listStudent;
	}

    public List<Staff>
	readBooksFromExcelFileStaff(String excelFilePath)
		throws IOException
	{
		List<Staff> listStaff
			= new ArrayList<Staff>();
		FileInputStream inputStream
			= new FileInputStream(new File(excelFilePath));

		Workbook workbook = new XSSFWorkbook(inputStream);
		Sheet firstSheet = workbook.getSheetAt(1);
		Iterator<Row> iterator = firstSheet.iterator();
		while (iterator.hasNext()) {
			Row nextRow = iterator.next();
			Iterator<Cell> cellIterator
				= nextRow.cellIterator();
            Staff stu = new Staff("userId","password",Faculty.SCSE);

			while (cellIterator.hasNext()) {
				Cell nextCell = cellIterator.next();
				int columnIndex = nextCell.getColumnIndex();

				switch (columnIndex) {
				case 0:
					break;
				case 1:
                    String result = (String)getCellValue(nextCell);
					stu.setUserId(
						result.split("@",2)[0]);
					break;
				case 2:
					stu.setFacultyInfo
						(Faculty.valueOf((String)getCellValue(nextCell)));
					break;
                case 3:
                    //this would be the password
                    stu.setPassword(
                        (String)getCellValue(nextCell));
                    break;
				default: System.out.println("fail"); break;
				}
			}
			listStaff.add(stu);
		}
        workbook.close();
		inputStream.close();

		return listStaff;
	}

    public List<Camp>
	readBooksFromExcelFileCamp(String excelFilePath)
		throws IOException
	{
		List<Camp> listCamp
			= new ArrayList<Camp>();
		FileInputStream inputStream
			= new FileInputStream(new File(excelFilePath));

		Workbook workbook = new XSSFWorkbook(inputStream);
		Sheet firstSheet = workbook.getSheetAt(2);
		Iterator<Row> iterator = firstSheet.iterator();
		while (iterator.hasNext()) {
			Row nextRow = iterator.next();
			Iterator<Cell> cellIterator
				= nextRow.cellIterator();
            Camp camp = new Camp("default", "userID");

			while (cellIterator.hasNext()) {
				Cell nextCell = cellIterator.next();
				int columnIndex = nextCell.getColumnIndex();

				switch (columnIndex) {
				case 0: //Camp name
                    camp.setCampName(
                        (String)getCellValue(nextCell));
					break;
				case 1: //StaffId
					camp.setStaffId(
                        (String)getCellValue(nextCell));
					break;
				case 2: //faculty
					camp.setUserGroup(
						Faculty.valueOf((String)getCellValue(nextCell)));
					break;
                case 3: //Total Slots
                    double cellValue = (double)getCellValue(nextCell);
                    int number = (int) cellValue;
                    camp.setTotalSlots(number);
                    break;
                case 4: //Slots left
                    cellValue = (double)getCellValue(nextCell);
                    number = (int) cellValue;
                    camp.setSlotsLeft(number);
                    break;
                case 5: //Date
                    SerialDate sd = new SerialDate((Double)getCellValue(nextCell));
                    LocalDate ld = LocalDate.ofEpochDay(sd.toEpochDays());
                    camp.setCampDate(
                        ld);
                    break;
                case 6: //Closing time
                    sd = new SerialDate((Double)getCellValue(nextCell));
                    LocalDateTime ldt = LocalDateTime.of(
                    LocalDate.ofEpochDay(sd.toEpochDays()),
                    LocalTime.ofSecondOfDay(sd.toDaySeconds()));
                    camp.setClosingTime(ldt);
                    break;
                case 7: //Location
                    camp.setLocation(
                        (String)getCellValue(nextCell));
                    break;
                case 8: //CampCom slots
                    cellValue = (double)getCellValue(nextCell);
                    number = (int) cellValue;
                    camp.setCampComSlots(number);
                    break;
                case 9: //Description
                    camp.setDescription(
                        (String)getCellValue(nextCell));
                    break;
                case 10:
                    camp.toggleVisibility(
                        (Boolean)getCellValue(nextCell));
                    break;
				default: System.out.println("fail"); break;
				}
			}
			listCamp.add(camp);
		}
        workbook.close();
		inputStream.close();

		return listCamp;
	}
}
