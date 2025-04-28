package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {

	// ===============================
	// DataProvider 1 - For Login Tests
	// ===============================
	@DataProvider(name = "LoginData")
	public String[][] getData() throws IOException {

		// Define the path to the Excel file (relative to project root)
		String path = ".\\testData\\Opencart_LoginData.xlsx";

		// Create an object of ExcelUtility with the file path
		ExcelUtility xlutil = new ExcelUtility(path);

		// Read total number of rows in the specified sheet (excluding header)
		int totalRows = xlutil.getRowCount("Sheet1");

		// Read total number of columns in the first data row (row index 1)
		int totalCols = xlutil.getCellCount("Sheet1", 1);

		// Create a 2D array to hold the login data
		// Rows = total data rows, Columns = number of fields per row
		String[][] loginData = new String[totalRows][totalCols];

		// Loop through each row and column to read the cell data
		for (int i = 1; i <= totalRows; i++)  // i starts at 1 to skip header row
		{
			for (int j = 0; j < totalCols; j++)  // j starts at 0 for first column
			{
				// Store data in 2D array (i-1 because array starts at index 0)
				loginData[i - 1][j] = xlutil.getCellData("Sheet1", i, j);
			}
		}

		// Return the populated 2D array to the test method
		return loginData;
	}

	// ===============================
	// Additional DataProviders can go below this block
	// ===============================
	
	// Example:
	// @DataProvider(name = "RegisterData")
	// public String[][] getRegisterData() throws IOException {
	//
}
