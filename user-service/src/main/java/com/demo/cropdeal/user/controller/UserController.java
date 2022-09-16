package com.demo.cropdeal.user.controller;

import com.demo.cropdeal.user.dto.UserDto;
import com.demo.cropdeal.user.model.User;
import com.demo.cropdeal.user.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/add-user")
	@ApiOperation(value = "Add user details",
		notes = "enter all the required and valid user details to add the user in database", response = String.class)
	public ResponseEntity<String> addUser(@RequestBody UserDto userDto) {
		
		return new ResponseEntity<>(userService.addUser(userDto), HttpStatus.OK);
	}
	
	@DeleteMapping("/delete-user/{userId}")
	@ApiOperation(value = "Delete user details by id", notes = "enter valid user id to be deleted from the  database",
		response = String.class)
	public ResponseEntity<String> deleteUser(@PathVariable ObjectId userId) {
		
		return new ResponseEntity<>(userService.deleteUser(userId), HttpStatus.OK);
		
	}
	
	@GetMapping("/get-user/{userId}")
	public ResponseEntity<User> getUser(@PathVariable ObjectId userId) {
		
		return new ResponseEntity<>(userService.getUser(userId), HttpStatus.OK);
	}
	
	//get all the users from database
	
	@GetMapping("/get-all-users")
	public ResponseEntity<List<User>> getAllUsers() {
		
		return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
	}
	
	@GetMapping("/get-user-by-email/{email}")
	public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
		
		return new ResponseEntity<>(userService.getUserByEmail(email), HttpStatus.OK);
	}
	
	@GetMapping("/get-user-username/{username}")
	public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
		
		return new ResponseEntity<>(userService.getUserByUsername(username), HttpStatus.OK);
	}
	
	
	@PutMapping("/update-user/{userId}")
	public ResponseEntity<User> updateUser(@PathVariable ObjectId userId, @RequestBody User user) {
		
		return new ResponseEntity<>(userService.updateUser(userId, user), HttpStatus.OK);
	}
	
	@PutMapping("/update-user-status/{userId}/{status}")
	public ResponseEntity<String> updateUserStatus(@PathVariable ObjectId userId, @PathVariable String status) {
		
		Boolean newStatus = status.equalsIgnoreCase("active") ? true : false;
		
		return new ResponseEntity<>(userService.markUserStatus(userId, newStatus), HttpStatus.OK);
	}
}
