package com.example.kiber.Controller;

import com.example.kiber.DAO.CityDAO;
import com.example.kiber.Model.city;
import com.example.kiber.Repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class Vkladka1Controller {
    @Autowired
    CityDAO cityDAO;

    @Autowired
    CityRepository cityRepository;

    @GetMapping("/vkladka1")
    public String home(Model model) {
        return "/vkladka1/main";
    }

    @GetMapping("/cityupdate")
    public String cityupdate(Model model) {
        boolean b = cityDAO.updatecity();
        return "/vkladka1/main";
    }

    @PostMapping("/city")
    public String city(Model model, @RequestParam String name, @RequestParam int mounth, @RequestParam int day) {
        try{
            if(day == 0) {
                Iterable<city> cities = cityRepository.findAllByNameAndMounth(name,mounth);
                model.addAttribute("cities",cities);
                return "/vkladka1/table";
            }
            Iterable<city> cities = cityRepository.findAllByNameAndMounthAndDay(name,mounth,day);
            model.addAttribute("cities",cities);
            return "/vkladka1/table";
        } catch (Exception e) {
            return "/vkladka1/main";
        }
    }




}
