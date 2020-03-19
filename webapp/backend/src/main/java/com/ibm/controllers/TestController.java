package com.ibm.controllers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.ibm.model.Key;
import com.ibm.model.User;

import com.ibm.algorithms.aes;
import com.ibm.algorithms.des;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class TestController {

	private List<User> employees = createList();

	@RequestMapping(value = "/users", method = RequestMethod.GET, produces = "application/json")
	public List<User> firstPage() {
		return employees;
	}

	@PostMapping(path = { "/createkey" })
	public Key create(@RequestBody Key key) {
		
		System.out.println(key.getTypeOfKey()+" "+key.getAlgorithm()+" "+key.getKeySize()+" "+key.getUniqueIdentifier());

		//pass these parameters to the create key function
		//add unique identifier to the object
		//return the same object
		key.setUniqueIdentifier("ThisIsUniqueIdentifier");
		System.out.println(key.getTypeOfKey()+" "+key.getAlgorithm()+" "+key.getKeySize()+" "+key.getUniqueIdentifier());
		return key;
	}

	@PostMapping(path = { "/getkey" })
	public String get(@RequestBody Key key) {
		//get key by passing uniqueIdentifier
		System.out.println(key.getUniqueIdentifier());
		String keyString = "ThisIsKey";
		return keyString;
	}
	
	@PostMapping(path = { "/destroykey" })
	public String destroy(@RequestBody Key key) {
		//get result status by passing unique identifier
		System.out.println(key.getUniqueIdentifier());
		String ResultStatus = "ThisIsResultStatus";
		return ResultStatus;
	}

	@PostMapping(path = { "/encrypt" })
	public String encrypt(@RequestParam("plaintextFile") MultipartFile file, @RequestParam("uniqueIdentifier") String uniqueIdentifier) throws IOException {
		//String plaintext = file.getBytes().toString();
		//System.out.println(plaintext);
		//System.out.println(uniqueIdentifier);
		
		//convert multipart to file and read content
		String fileName = "D:/"+"encrypted-"+file.getOriginalFilename();
		File ciphertext =  new File(fileName);
		file.transferTo(ciphertext);
		BufferedReader bufferedReader = new BufferedReader(new FileReader(ciphertext));
		String fileContent="";
		String i; 
		while ((i = bufferedReader.readLine()) != null){
			fileContent+=i;
		} 
		System.out.println(fileContent);
		
		//consider value of key for now
		String secretKey = "wgfiwbeNCNKJWE;QGH";

		aes aesAlgo = new aes();
		String encryptedText = aesAlgo.encrypt(fileContent, secretKey);
		System.out.println(encryptedText);
		
		/*
		des desAlgo = new des();
		String encryptedText1 = desAlgo.encrypt(fileContent, secretKey);
		System.out.println(encryptedText1);
		*/

		//WRITE TO FILE NOT WORKING
		//FileWriter fileWriter = new FileWriter(ciphertext);
		//fileWriter.write(encryptedText);
		//BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
		//bufferedWriter.write(encryptedText);

		//return encryptedText;
		return encryptedText;
	}

	private static List<User> createList() {
		List<User> tempEmployees = new ArrayList<User>();

		User emp1 = new User();
		emp1.setUserName("user1");
		emp1.setPassword("password1");
		
		User emp2 = new User();
		emp2.setUserName("user2");
		emp2.setPassword("password2");
		
		tempEmployees.add(emp1);
		tempEmployees.add(emp2);

		//System.out.println(tempEmployees);
		return tempEmployees;
	}

}

