package com.example.restservice;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();

	@GetMapping("/greeting")
	public List<Item> greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
	    List<Item> l = new LinkedList<>();
	    l.add(new Item("test1", 42));
		l.add(new Item("test2", 43));
		return l;
	}
}
