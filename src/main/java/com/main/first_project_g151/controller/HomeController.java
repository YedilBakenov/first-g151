package com.main.first_project_g151.controller;


import com.main.first_project_g151.model.Fighter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @GetMapping(value = "/") //http://localhost:8080/
    public String getHomePage(Model model){
        model.addAttribute("fighters", Fighter.getFighters());
        return "index";
    }

    @GetMapping(value = "/details/{id}")
    public String getFighter(@PathVariable int id, Model model){
        model.addAttribute("fighter", Fighter.getFighterById(id));
        return "details";
    }

    @PostMapping(value = "/upd")
    public String updateFighter(Fighter fighter){
        Fighter.updateFighter(fighter);

        return "redirect:/";
    }

    @PostMapping(value = "/delete")
    public String deleteFighter(@RequestParam int id){

        Fighter.deleteFighter(id);

        return "redirect:/";
    }

    @GetMapping(value = "/add")
    public String addFighterPage(){
        return "add-page";
    }

    @PostMapping(value = "/add")
    public String addFighter(Fighter fighter){

        Fighter.add(fighter);

        return "redirect:/";
    }
}
