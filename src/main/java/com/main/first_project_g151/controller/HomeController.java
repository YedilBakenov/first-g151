package com.main.first_project_g151.controller;


import com.main.first_project_g151.db.DBConnector;
import com.main.first_project_g151.model.Fighter;
import com.main.first_project_g151.repository.FighterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final FighterRepository fighterRepository;
    @GetMapping(value = "/") //http://localhost:8080/
    public String getHomePage(Model model){
        model.addAttribute("fighters", fighterRepository.findAll());
        return "index";
    }

    @GetMapping(value = "/details/{id}")
    public String getFighter(@PathVariable int id, Model model){
        model.addAttribute("fighter", fighterRepository.findById(id).get());
//        model.addAttribute("cities", DBConnector.getAllCities());
        return "details";
    }

    @PostMapping(value = "/upd")
    public String updateFighter(Fighter fighter){
//        DBConnector.updateFighter(fighter);

        fighterRepository.save(fighter);

        return "redirect:/";
    }

    @PostMapping(value = "/delete")
    public String deleteFighter(@RequestParam int id){

//        DBConnector.deleteFighter(id);
        fighterRepository.deleteById(id);

        return "redirect:/";
    }

    @GetMapping(value = "/add")
    public String addFighterPage(Model model){
//        model.addAttribute("cities", DBConnector.getAllCities());
        return "add-page";
    }

    @PostMapping(value = "/add")
    public String addFighter(Fighter fighter){

//        DBConnector.addFighter(fighter);
        fighterRepository.save(fighter);

        return "redirect:/";
    }
}
