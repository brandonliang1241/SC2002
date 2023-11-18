package excel;
// Java Program to Extract Content from a Excel sheet

// As we are reading the excel file, java.io package is
// compulsorily required
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
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
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
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
            Student stu = new Student("userId","name", "password",Faculty.SCSE);

			while (cellIterator.hasNext()) {
				Cell nextCell = cellIterator.next();
				int columnIndex = nextCell.getColumnIndex();

				switch (columnIndex) {
				case 0:
					stu.setName((String)getCellValue(nextCell));
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
            Staff stu = new Staff("userId","name","password",Faculty.SCSE);

			while (cellIterator.hasNext()) {
				Cell nextCell = cellIterator.next();
				int columnIndex = nextCell.getColumnIndex();

				switch (columnIndex) {
				case 0:
					stu.setName((String)getCellValue(nextCell));
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
                    double cellValue = Double.parseDouble((String)getCellValue(nextCell));
                    int number = (int) cellValue;
                    camp.setTotalSlots(number);
                    break;
                case 4: //Slots left
                    cellValue = Double.parseDouble((String)getCellValue(nextCell));
                    number = (int) cellValue;
                    camp.setSlotsLeft(number);
                    break;
                case 5: //Date
                    // SerialDate sd = new SerialDate(Double.parseDouble((String)getCellValue(nextCell)));
                    // LocalDate ld = LocalDate.ofEpochDay(sd.toEpochDays());
					if(((String)getCellValue(nextCell)).equals("null")){break;}
                    camp.setCampDate(
						LocalDate.parse((String)getCellValue(nextCell)));
                    break;
                case 6: //Closing time
                    // sd = new SerialDate(Double.parseDouble((String)getCellValue(nextCell)));
                    // LocalDateTime ldt = LocalDateTime.of(
                    // LocalDate.ofEpochDay(sd.toEpochDays()),
                    // LocalTime.ofSecondOfDay(sd.toDaySeconds()));
					if(((String)getCellValue(nextCell)).equals("null")){break;}
                    camp.setClosingTime(
						LocalDateTime.parse((String)getCellValue(nextCell)));
                    break;
                case 7: //Location
                    camp.setLocation(
                        (String)getCellValue(nextCell));
                    break;
                case 8: //CampCom slots
                    cellValue = Double.parseDouble((String)getCellValue(nextCell));
                    number = (int) cellValue;
                    camp.setCampComSlots(number);
                    break;
                case 9: //Description
                    camp.setDescription(
                        (String)getCellValue(nextCell));
                    break;
                case 10:
                    camp.toggleVisibility(
                        Boolean.valueOf((String)getCellValue(nextCell)));
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
	
	public void storeWorkbook(Database database)
			throws IOException
	{

		XSSFWorkbook workbook = new XSSFWorkbook(); 
  
        // spreadsheet object 
        XSSFSheet spreadsheet1 
            = workbook.createSheet(" Student Data "); 
        XSSFSheet spreadsheet2 
            = workbook.createSheet(" Staff Data "); 
		XSSFSheet spreadsheet3 
            = workbook.createSheet(" Camp Data "); 
        // creating a row object 
        XSSFRow row; 
  
        // This data needs to be written (Object[]) 
        Map<String, Object[]> studentData 
            = new TreeMap<String, Object[]>(); 
		
		for(int i = 0; i < database.getStudents().size();i++){
			String userId = database.getStudents().get(i).getUserId();
			String name = database.getStudents().get(i).getName();
			String password = database.getStudents().get(i).getPassword();
			String faculty = database.getStudents().get(i).getFacultyInfo().toString();
			studentData.put(String.valueOf(i), new Object[]{name, userId+"@e.ntu.edu.sg", faculty,password});
		}

        Map<String, Object[]> staffData 
            = new TreeMap<String, Object[]>(); 
		
		for(int i = 0; i < database.getStaffs().size();i++){
			String userId = database.getStaffs().get(i).getUserId();
			String name = database.getStaffs().get(i).getName();
			String password = database.getStaffs().get(i).getPassword();
			String faculty = database.getStaffs().get(i).getFacultyInfo().toString();
			staffData.put(String.valueOf(i), new Object[]{name, userId+"@e.ntu.edu.sg", faculty,password});
		}

		Map<String, Object[]> campData 
            = new TreeMap<String, Object[]>(); 
		
		for(int i = 0; i < database.getCamps().size();i++){ //havent finished
			String campName = database.getCamps().get(i).getCampName();
			String creator = database.getCamps().get(i).getStaffId();
			String faculty = database.getCamps().get(i).getUserGroup().toString();
			String totalSlots = String.valueOf(database.getCamps().get(i).getTotalSlots());
			String slotsLeft = String.valueOf(database.getCamps().get(i).getSlotsLeft());
			String date = String.valueOf(database.getCamps().get(i).getCampDate());
			String endDate = String.valueOf(database.getCamps().get(i).getClosingTime());
			String location = database.getCamps().get(i).getLocation();
			String campComSlots = String.valueOf(database.getCamps().get(i).getCampComSlots());
			String description = database.getCamps().get(i).getDescription();
			String visibility = String.valueOf(database.getCamps().get(i).isVisible());
			campData.put(String.valueOf(i), new Object[]{campName, creator, faculty, totalSlots, 
				slotsLeft, date, endDate, location, campComSlots, description, visibility});
		}

        Set<String> keyid1 = studentData.keySet(); 
  
        int rowid = 0; 
  
        // writing the data into the sheets... 
  
        for (String key : keyid1) { 
  
            row = spreadsheet1.createRow(rowid++); 
            Object[] objectArr = studentData.get(key); 
            int cellid = 0; 
  
            for (Object obj : objectArr) { 
                Cell cell = row.createCell(cellid++); 
                cell.setCellValue((String)obj); 
            } 
        } 

		Set<String> keyid2 = staffData.keySet(); 

		rowid = 0; 

        for (String key : keyid2) { 
  
            row = spreadsheet2.createRow(rowid++); 
            Object[] objectArr = staffData.get(key); 
            int cellid = 0; 
  
            for (Object obj : objectArr) { 
                Cell cell = row.createCell(cellid++); 
                cell.setCellValue((String)obj); 
            } 
        } 

  		Set<String> keyid3 = campData.keySet(); 

		rowid = 0; 

        for (String key : keyid3) { 
  
            row = spreadsheet3.createRow(rowid++); 
            Object[] objectArr = campData.get(key); 
            int cellid = 0; 
  
            for (Object obj : objectArr) { 
                Cell cell = row.createCell(cellid++); 
                cell.setCellValue((String)obj); 
            } 
        } 
        // .xlsx is the format for Excel Sheets... 
        // writing the workbook into the file... 
        FileOutputStream out = new FileOutputStream( 
            new File("/Users/brandon1241/Downloads/staff_list.xlsx")); 
  
        workbook.write(out); 
        out.close(); 
		workbook.close();
    } 
}
