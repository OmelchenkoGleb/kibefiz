package com.example.kiber.Controller;

import com.example.kiber.DAO.CityDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.util.Date;

@Controller
public class Vkladka2Controller {
    @Autowired
    CityDAO cityDAO;


    @GetMapping("/vkladka2")
    public String home(){
        return "/vkladka2/form";
    }


    @PostMapping("/lab2")
    public String lab2(Model model, @RequestParam String name, @RequestParam String date1, @RequestParam String date2) throws ParseException {
        return cityDAO.extractData(name,date1,date2,model);
    }
}
