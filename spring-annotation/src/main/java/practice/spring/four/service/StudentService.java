/**
 * 
 */
package practice.spring.four.service;

import practice.spring.four.simple.Student;

/**
 * @author hemd
 *
 */
public interface StudentService {
   String getStudentInfo(String name);
   Student getStudentObj(String name);
}
