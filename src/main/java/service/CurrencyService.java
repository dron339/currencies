package com.example.currencyconverter.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Map;

@Service
public class CurrencyService {
    private static final String API_URL = "https://api.privatbank.ua/p24api/pubinfo?exchange&json&coursid=5";

    public double getExchangeRate(String currency) {
        RestTemplate restTemplate = new RestTemplate();
        Map[] response = restTemplate.getForObject(API_URL, Map[].class);

        if (response != null) {
            for (Map rate : response) {
                if (rate.get("ccy").equals(currency)) {
                    return Double.parseDouble(rate.get("sale").toString());
                }
            }
        }
        return 0.0;
    }
}
