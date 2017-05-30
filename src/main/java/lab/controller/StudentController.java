package lab.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import entity.Student;
import repository.StudentRepository;

@CrossOrigin
@RestController
public class StudentController {
	
	@Autowired
	private StudentRepository studentRepository;

	@RequestMapping(value="/submitStudentDetails",
			consumes=MediaType.APPLICATION_JSON_VALUE,
			produces=MediaType.APPLICATION_JSON_VALUE,
			method=RequestMethod.POST
			)
	@ResponseBody
	public void submitStudentDetails(@RequestBody Student student) {
	
		studentRepository.save(student);
	}
	
	@RequestMapping(value="/findByEmail",
			produces=MediaType.APPLICATION_JSON_VALUE,
			method=RequestMethod.GET
			)
	@ResponseBody
	public ResponseEntity<Student> findByEmail(@RequestBody String email) {
		
		Student student = this.studentRepository.findOne(email);
		return new ResponseEntity<>(student,HttpStatus.OK);
	}
	
	@RequestMapping(value="/loginStudent",
			produces=MediaType.APPLICATION_JSON_VALUE,
			method=RequestMethod.POST
			)
	@ResponseBody
	public ResponseEntity<Student> loginStudent(@RequestBody Student student) {
		
		//login procedure goes here

		Student resultStudent = this.studentRepository.findOne(student.getEmail());
		
		if (resultStudent.getPassword().equals(student.getPassword())){
			return new ResponseEntity<>(student,HttpStatus.OK);
		}else{
			return new ResponseEntity<>(student,HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@RequestMapping(value="/listStudents",
			produces=MediaType.APPLICATION_JSON_VALUE,
			method=RequestMethod.GET
			)
	@ResponseBody
	public ResponseEntity<List<Student>> listStudents() {
		
		List<Student> students =this.studentRepository.findAll();
		return new ResponseEntity<>(students,HttpStatus.OK);
	}
}
