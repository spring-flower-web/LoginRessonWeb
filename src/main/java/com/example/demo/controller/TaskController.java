package com.example.demo.controller;

import java.util.List;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Task;
import com.example.demo.entity.User;
import com.example.demo.service.TaskService;

@Controller
@RequestMapping("/task")
public class TaskController {

	@Autowired
	private TaskService taskService;
	
	//タスク一覧表示
	@GetMapping
	public String showTasks(Model model, HttpSession session) {
		
		User user = (User) session.getAttribute("user");
		
		//未ログイン対策
		if (user == null) {
			return "redirect:.login";
		}
		
		List<Task> tasks = 	taskService.getTasks(user);
		model.addAttribute("tasks",tasks);
		
		return "tasks";
	}
	
    // ➕ タスク追加
    @PostMapping("/add")
    public String addTask(@RequestParam String title,
                          @RequestParam String description,
                          HttpSession session) {

        User user = (User) session.getAttribute("user");

        if (user == null) {
            return "redirect:/login";
        }

        taskService.addTask(title, description, user);

        return "redirect:/tasks";
    }

    // ✅ タスク完了
    @PostMapping("/complete")
    public String completeTask(@RequestParam Long id) {

        taskService.complete(id);
        return "redirect:/tasks";
    }

    // 🗑️ タスク削除
    @PostMapping("/delete")
    public String deleteTask(@RequestParam Long id) {

        taskService.delete(id);
        return "redirect:/tasks";
    }
}
	
