package com.example.currencyconverter.controller;

import com.example.currencyconverter.service.CurrencyService;
import com.example.currencyconverter.service.ExcelExportService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller
public class CurrencyController {
    private final CurrencyService currencyService;

    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/convert")
    public String convert(@RequestParam("amount") double amount,
                          @RequestParam("currency") String currency,
                          org.springframework.ui.Model model) {
        double rate = currencyService.getExchangeRate(currency);
        double convertedAmount = amount * rate;

        model.addAttribute("amount", amount);
        model.addAttribute("currency", currency);
        model.addAttribute("convertedAmount", convertedAmount);
        model.addAttribute("rate", rate);

        return "result";
    }

    @GetMapping("/export")
    public void exportToExcel(@RequestParam("amount") double amount,
                              @RequestParam("currency") String currency,
                              HttpServletResponse response) throws IOException {

        double rate = currencyService.getExchangeRate(currency);
        double convertedAmount = amount * rate;

        ExcelExportService excelExportService = new ExcelExportService();
        excelExportService.export(response, amount, currency, rate, convertedAmount);
    }
}
