package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Task {

	@Id
	@GeneratedValue
	private Long id;
	
	private String title;
	private String description;
	private boolean completed;
	
	//多対一の関係を表す
	@ManyToOne
	private User user;
}