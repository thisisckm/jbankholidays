package com.ck.bankholidays.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BankHolidaysController {
    
    @GetMapping("/isholiday")
    public String isHoliday(@RequestParam String date) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d");
            LocalDate localDate = LocalDate.parse(date, formatter);
            return localDate.toString();
        }
        catch (Exception ex) {
            return "";
        }
    }
}
