/**
 * 
 */
package practice.spring.four.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import practice.spring.four.service.StudentService;

/**
 * @author hemd
 *
 */
@Controller
@RequestMapping("/students")
public class StudentController {
   @Autowired
   private StudentService studentService;
   @RequestMapping(value = "/info")
   @ResponseBody
   public String getStudentInfo(@RequestParam(value = "name", required = false, defaultValue = "Ai Iijiima") String name){
      return studentService.getStudentInfo(name);
   }
   @RequestMapping(value = "/obj")
   @ResponseBody
   public Object getStudentObj(@RequestParam(value = "name", required = false, defaultValue = "Ai Iijiima") String name){
      return studentService.getStudentObj(name);
   }
}
