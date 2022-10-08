package com.ck.bankholidays.controller.view;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ck.bankholidays.data.HolidayRepository;

@Controller
@RequestMapping("/")
public class HomeController {
    
    @Autowired
	private HolidayRepository repository;

    @GetMapping("/")
    public String template(Model model) {
        model.addAttribute("holidays", 
            repository.findAllUpComingHoliday(new Date(System.currentTimeMillis())).subList(0, 3));
        return "home";
    }
}
