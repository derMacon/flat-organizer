package com.example.restservice;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();

	@GetMapping("/greeting")
	public List<Item> greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
	    List<Item> l = new LinkedList<>();
	    l.add(new Item("test1", 42, "requester1"));
		l.add(new Item("test2", 43, "requester1"));
		return l;
	}

	@PostMapping(path = "/items")
	public void addMember(@RequestBody Item item) {
		System.out.println(item);
	}


	@PostMapping(path = "/post", consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<String> post(@RequestBody Item item) {
		System.out.println(item);
		return new ResponseEntity<String>("POST Response", HttpStatus.OK);
	}

}
