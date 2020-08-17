package com.rlsp.springboot.fundamentals.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rlsp.springboot.fundamentals.error.CustomErrorType;
import com.rlsp.springboot.fundamentals.model.Student;
import com.rlsp.springboot.fundamentals.util.DateUtil;

/**
 * E um RECURSO para API
 * 
 * Quando se adiciona @RestContoller é como se configurarmos a aplicacao com @ResponseBody (adicionado para todos os metodos da classe)
 * 
 * @ResponseBody => anotacao que diz que o resultado retornara um JSON, sem trabalhar com VIEW
 * 
 * OBS: se configurarmos com  @Contoller deve-se incluir @ResponseBody, pois caso contrario retornara uma VIEW
 * 
 * @RequestMapping => apresenta a URL para acesso (no caso, ex: localhost/student
 * 
 * @author rlatorraca
 *
 */


@RestController
@RequestMapping("/students") //sempre no PLURAL o requestMapping , e o padrao e pegar o unico metodo GET
public class StudentEndpoint {

	@Autowired // Faz a injecao de DEPENDENCIA, instanciando o Objeto
	private DateUtil dateUtil;
	
	/**
	 * Usado para retorna seu STATUS CODE (um codigo de resposta da requisicao)
	 * @return
	 */
	@GetMapping("/list")
	private ResponseEntity<?> listAll(){
		//System.out.println("-------------------------------- ===> " + dateUtil.formatLocalDateTimeToDBStyle(LocalDateTime.now()));
		return new ResponseEntity<>(Student.studentList, HttpStatus.OK); // Retorna a lista de Student e o Status da resposta HTTP
	}
	
	/**
	 * Metodo para buscar um valor da lista de Students
	 * 
	 * @PathVariable => usado para pegar o ID da assinatura da requisicao
	 */
	@RequestMapping(method = RequestMethod.GET, path="/{id}")
	private ResponseEntity<?> getStudentById(@PathVariable("id") int id){
		Student student = new Student();
		student.setId(id);
		int index = Student.studentList.indexOf(student);
		if(index == -1) {
			return new ResponseEntity<>(new CustomErrorType("Student not found"), HttpStatus.NOT_FOUND);
		}
		
		return  new ResponseEntity<>(Student.studentList.get(index), HttpStatus.OK) ;
		
	}
}
