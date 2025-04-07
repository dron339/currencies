package com.example.currencyconverter.service;

import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;

public class ExcelExportService {

    public void export(HttpServletResponse response,
                       double amount,
                       String currency,
                       double rate,
                       double convertedAmount) throws IOException {

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=conversion.xlsx");

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Conversion");

        // Створення заголовків
        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("Amount");
        header.createCell(1).setCellValue("Currency");
        header.createCell(2).setCellValue("Rate");
        header.createCell(3).setCellValue("Converted Amount");

        // Данні
        Row dataRow = sheet.createRow(1);
        dataRow.createCell(0).setCellValue(amount);
        dataRow.createCell(1).setCellValue(currency);
        dataRow.createCell(2).setCellValue(rate);
        dataRow.createCell(3).setCellValue(convertedAmount);

        workbook.write(response.getOutputStream());
        workbook.close();
    }
}
