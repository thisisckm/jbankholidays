package com.ck.bankholidays.controller.view;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ck.bankholidays.data.Holiday;
import com.ck.bankholidays.data.HolidayRepository;

@Controller
@RequestMapping("/")
public class HomeController {
    
    @Autowired
	private HolidayRepository repository;

    @GetMapping("/")
    public String template(Model model) {

        List<Holiday> upcomingHoliday = repository.findAllUpComingHoliday(new Date(System.currentTimeMillis())); 

        int endIndex = 5;
        if (upcomingHoliday.size() < 5) {
            endIndex = upcomingHoliday.size() - 1;
        }

        upcomingHoliday = upcomingHoliday.subList(0, endIndex);

        model.addAttribute("holidays", upcomingHoliday);
        return "home";
        
    }
}
