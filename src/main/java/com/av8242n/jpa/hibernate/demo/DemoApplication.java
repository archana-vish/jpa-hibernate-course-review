package com.av8242n.jpa.hibernate.demo;

import com.av8242n.jpa.hibernate.demo.entity.Course;
import com.av8242n.jpa.hibernate.demo.entity.Student;
import com.av8242n.jpa.hibernate.demo.repository.CourseRepository;
import com.av8242n.jpa.hibernate.demo.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	@Autowired
	CourseRepository courseRepository;

	@Autowired
	StudentRepository studentRepository;

	private static final Logger logger = LoggerFactory.getLogger(DemoApplication.class.getName());

	@Override
	public void run(String... args) throws Exception {
		/** FOR COURSE **/
//		Course course = courseRepository.findById(10001);
//		logger.info("Course returned is {} ", course);
//		//courseRepository.deleteById(10001);
//		courseRepository.ifFoundUpdateElseSave(new Course("Microservices in 100 steps"));
//
//		courseRepository.playWithEntityManager();
//		courseRepository.testTimestamps();


		/** FOR STUDENT **/

		// Save a student with passport
		studentRepository.saveStudentWithPassport();
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
