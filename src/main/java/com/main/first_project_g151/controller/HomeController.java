package com.main.first_project_g151.controller;


import com.main.first_project_g151.cb.CriteriaImpl;
import com.main.first_project_g151.model.Car;
import com.main.first_project_g151.model.Fighter;
import com.main.first_project_g151.repository.CarRepository;
import com.main.first_project_g151.repository.FighterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final FighterRepository fighterRepository;
    private final CarRepository carRepository;
    private final CriteriaImpl criteria;
    @GetMapping(value = "/") //http://localhost:8080/
    public String getHomePage(Model model){
        model.addAttribute("fighters", fighterRepository.findAll());
        return "index";
    }

    @GetMapping(value = "/cb-name-age")
    public String getResultByCriteria(Model model,
                                      @RequestParam(required = false) String fullName,
                                      @RequestParam(required = false) Integer age){

        model.addAttribute("fighters", criteria.findFightersByFullNameAndAge(fullName, age));

        return "index";
    }

    @GetMapping(value = "/cb-age")
    public String getResultByCriteria(Model model,
                                      @RequestParam(required = false) Integer age){

        model.addAttribute("fighters", criteria.findFightersByAge(age));

        return "index";
    }

    @GetMapping(value = "/search")
    public String getFighters(@RequestParam String word,
                              Model model){
        model.addAttribute("fighters", fighterRepository.findFightersByWord(word));

        return "index";
    }

    @GetMapping(value = "/details/{id}")
    public String getFighter(@PathVariable int id, Model model){

        Fighter fighter = fighterRepository.findById(id).get();

        model.addAttribute("fighter", fighter);

        model.addAttribute("cars", fighter.getCar());

        List<Car> cars = carRepository.findAll();

        cars.removeAll(fighter.getCar());

        model.addAttribute("carsFighter", cars);


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

    @PostMapping(value = "/add-car")
    public String addCarToFighter(@RequestParam int car_id,
                                  @RequestParam int fighter_id){
        Car car = carRepository.findById(car_id).get();
        Fighter fighter = fighterRepository.findById(fighter_id).get();

        List<Car> cars = fighter.getCar();
        cars.add(car);

        fighterRepository.save(fighter);

        return "redirect:/details/" + fighter_id;
    }

    @PostMapping(value = "/delete-car")
    public String deleteCarFighter(@RequestParam int car_id,
                                  @RequestParam int fighter_id){
        Car car = carRepository.findById(car_id).get();
        Fighter fighter = fighterRepository.findById(fighter_id).get();

        List<Car> cars = fighter.getCar();
        cars.remove(car);

        fighterRepository.save(fighter);

        return "redirect:/details/" + fighter_id;
    }
}
