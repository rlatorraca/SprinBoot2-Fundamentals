package com.rlsp.springboot.fundamentals.endpoint;

import static java.util.Arrays.asList;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rlsp.springboot.fundamentals.model.Student;
import com.rlsp.springboot.fundamentals.util.DateUtil;

@RestController
@RequestMapping("/student")
public class StudentEndpoint {

	@Autowired // Faz a injecao de DEPENDENCIA, instanciando o Objeto
	private DateUtil dateUtil;
	
	@GetMapping("/list")
	private List<Student> listAll(){
		//System.out.println("-------------------------------- ===> " + dateUtil.formatLocalDateTimeToDBStyle(LocalDateTime.now()));
		return asList(new Student("Rodrigo"), new Student("Ronaldo"));
	}
}
