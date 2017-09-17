/**
 * 
 */
package practice.spring.four.service.impl;

import org.springframework.stereotype.Service;

import practice.spring.four.service.StudentService;
import practice.spring.four.simple.Student;

/**
 * @author hemd
 *
 */
@Service
public class StudentServiceImpl implements StudentService {

   private Student supplyEscortStu(String name){
      return new Student(name,17,false);
   }
   public String getStudentInfo(String name) {
      Student stu=supplyEscortStu(name);
      return stu.toString();
   }

   public Student getStudentObj(String name) {
      return supplyEscortStu(name);
   }
   
}
