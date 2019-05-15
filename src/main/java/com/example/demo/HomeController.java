package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class HomeController {

    @Autowired
    ListRepository listRepository;

    @RequestMapping("/")
    public String listTodo(Model model){
        model.addAttribute("todos", listRepository.findAll());
        return "list";
    }
    @GetMapping("/add")
    public String todoForm(Model model){
        model.addAttribute("todo", new TodoList());
        return "todoform";
    }
    @PostMapping("/process")
    public String processForm(@Valid TodoList todo, BindingResult result){
        if(result.hasErrors()){
            return "todoform";
        }
        listRepository.save(todo);
        return "redirect:/";
    }
}
