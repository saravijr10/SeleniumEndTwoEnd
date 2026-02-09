package utils;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class ExcelUtils {

    public static Object[][] readLoginDataByType(
            String filePath,
            String sheetName,
            String typeFilter) {

        List<Object[]> filteredData = new ArrayList<>();

        try {
            FileInputStream fis = new FileInputStream(filePath);
            Workbook workbook = WorkbookFactory.create(fis);
            Sheet sheet = workbook.getSheet(sheetName);

            int rows = sheet.getPhysicalNumberOfRows();

            for (int i = 1; i < rows; i++) {
                Row row = sheet.getRow(i);

                String email = row.getCell(0).toString();
                String password = row.getCell(1).toString();
                String type = row.getCell(2).toString();

                if (type.equalsIgnoreCase(typeFilter)) {
                    filteredData.add(new Object[]{email, password});
                }
            }

            workbook.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return filteredData.toArray(new Object[0][]);
    }

}
