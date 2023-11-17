package excel;

// Java Program to Extract Content from a Excel sheet

// As we are reading the excel file, java.io package is
// compulsorily required

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


// POJO class having 3 fields matching with the given excel

/**
 * Unit test for simple App.
 */
public class AppTest 
{

	public static void main(String[] args)
	{
		// detecting the file type
		GetContentFromExcelSheets getContentFromExcelSheets
			= new GetContentFromExcelSheets();
		List<Employee> extractedEmployeeData
			= new ArrayList<Employee>();
		try {
			extractedEmployeeData
				= getContentFromExcelSheets
					.readBooksFromExcelFile(
						"/Users/brandon1241/Documents/testfile.xlsx");
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(extractedEmployeeData);
	}
}
