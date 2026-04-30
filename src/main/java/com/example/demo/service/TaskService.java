package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Task;
import com.example.demo.entity.User;
import com.example.demo.repository.TaskRepository;

@Service
public class TaskService {

	@Autowired
	TaskRepository taskRepository;
	
	public List<Task> getTasks(User user){
		return taskRepository.findByUser(user);
	}
	
	//タスク登録
	public void addTask(String title,String description,User user) {
		Task task = new Task();
		task.setTitle(title);
		task.setCompleted(false);
		task.setDescription(description);
		task.setUser(user);
		
		taskRepository.save(task);
		
	}
	
	//タスク完了時
	public void complete(Long id) {

			Task task = new Task();
			task =taskRepository.findById(id).orElseThrow();
			task.setCompleted(true);
			taskRepository.save(task);

		
	}
	
	public void delete(Long id) {
		taskRepository.deleteById(id);
	}
}
