package com.app.todo.controller;

import com.app.todo.models.Task;
import com.app.todo.services.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
//@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public String createTask(@RequestParam String title){
        taskService.createTask(title);
        return "redirect:/";
    }

    @GetMapping
    public String getTasks(Model model) {
        // Controller → Model → HTML Template → Browser
        List<Task> tasks = taskService.getAllTasks();
        model.addAttribute("tasks", tasks); // sends the List<Task> to Thymeleaf
        return "tasks"; //tells Spring to open tasks.html
    }

    @GetMapping("/{taskId}/toggle")
    public String toggleTask(@PathVariable Long taskId){
        taskService.toggleTask(taskId);
        return "redirect:/";
    }

    @GetMapping("/{taskId}/delete")
    public String deleteTask(@PathVariable Long taskId){
        taskService.deleteTask(taskId);
        return "redirect:/";
    }
}
