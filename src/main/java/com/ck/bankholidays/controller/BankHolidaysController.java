package com.ck.bankholidays.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ck.bankholidays.data.Holiday;
import com.ck.bankholidays.data.HolidayRepository;


@RestController
public class BankHolidaysController {

    @Autowired
	private HolidayRepository repository;

    @GetMapping("/holiday")
    public List<Holiday> isHoliday(@RequestParam Optional<String> date) {
        try {
            if (date.isPresent()) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d");
                LocalDate localDate = LocalDate.parse(date.get(), formatter);
                return List.of(repository.findByDate(Date.valueOf(localDate)));
            }
            else {
                return repository.findAllUpComingHoliday(new Date(System.currentTimeMillis()));
            }
        }
        catch (Exception ex) {
            return List.of();
        }
    }
}
