package by.bsu.app.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import by.bsu.app.model.Subject;
import by.bsu.app.repo.SubjectRepo;

@Controller
public class GreetingController {
	
	@Autowired
	private SubjectRepo subjectRepo;
	
    @GetMapping("/greeting")
    public String greeting(
            @RequestParam(name="name", required=false, defaultValue="World") String name,
            Map<String, Object> model
    ) {
        model.put("name", name);
        return "greeting";
    }
    
    @GetMapping
    public String main(Map<String,Object>model) {
    	Iterable<Subject>subjects=subjectRepo.findAll();
    	model.put("subjects",subjects);
    	return "main";
    }
    
    @PostMapping
    public String add(@RequestParam String name, @RequestParam String prefix, Map<String, Object> model) {
        Subject subject = new Subject(name, prefix);

        subjectRepo.save(subject);

        Iterable<Subject> subjects = subjectRepo.findAll();

        model.put("subjects", subjects);

        return "main";
    }
    @PostMapping("filter")
    public String filter(@RequestParam String filter, Map<String, Object> model) {
    	Iterable<Subject>subjects;
    	if(!filter.isEmpty() && filter!=null){
    		subjects=subjectRepo.findByPrefix(filter);
    	}
    	else {
    		subjects=subjectRepo.findAll();
    	}
    	model.put("subjects", subjects);

        return "main";
    	
    }

   
}