package br.senac.sp.epictask.controller;

import br.senac.sp.epictask.model.Task;
import br.senac.sp.epictask.repository.TaskRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("task")
public class TaskController {

    @Autowired // Injeção de dependências - se trocar banco de dados ou alguma info, não muda nada
    TaskRepository repository;
    @GetMapping
    public String index(Model model){

        var lista = repository.findAll();
        model.addAttribute("tasks",lista);
        return "task/index";

    }

    @GetMapping("new") //localhost:8080/task/new
    public String form(Task task){

        return "task/form";

    }

    @PostMapping("new")
    public String save(@Valid Task task, BindingResult result){

        //receber valores do form
        //salvar no BD
        //redirecionar para a lista /task
        if(result.hasErrors()) return "task/form";
        repository.save(task);
        return "redirect:/task";
    }


}
