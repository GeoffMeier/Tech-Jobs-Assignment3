package org.launchcode.techjobs.mvc.controllers;

import org.launchcode.techjobs.mvc.models.Job;
import org.launchcode.techjobs.mvc.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;

import static org.launchcode.techjobs.mvc.controllers.ListController.columnChoices;
import static org.launchcode.techjobs.mvc.controllers.ListController.tableChoices;


/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {



    @GetMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", columnChoices);
//        model.addAttribute("all",JobData.findAll());
//        model.addAttribute("employers", JobData.getAllEmployers());
//        model.addAttribute("locations", JobData.getAllLocations());
//        model.addAttribute("positionType", JobData.getAllPositionTypes());
//        model.addAttribute("skills", JobData.getAllCoreCompetency());
        return "search";
    }

    // TODO #3 - Create a handler to process a search request and render the updated search view.
@PostMapping(value = "results")
    public String displaySearchResults(Model model,@RequestParam  String  searchType,@RequestParam String searchTerm){
model.addAttribute("columns",columnChoices);
//    model.addAttribute("tableChoices",tableChoices);
    ArrayList<Job>jobs;
    if (searchTerm.equals("all")||searchType.equals(null)){
        jobs=JobData.findAll();
        model.addAttribute("title","all");
    }else {
        jobs=JobData.findByColumnAndValue(searchType,searchTerm);
        model.addAttribute("title", "Here are the results of the "+ searchType +"value of "+ searchTerm);

    }
model.addAttribute("jobs", jobs);
          return "search";
        }

}
