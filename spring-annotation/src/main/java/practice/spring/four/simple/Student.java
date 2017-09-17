/**
 * 
 */
package practice.spring.four.simple;

import java.io.Serializable;

/**
 * @author hemd
 *
 */
public class Student implements Serializable {
   private static final long serialVersionUID = 6685035426509943430L;
   private String name;
   private Integer age;
   private Boolean gender;
   public String getName() {
      return name;
   }
   public void setName(String name) {
      this.name = name;
   }
   public Integer getAge() {
      return age;
   }
   public void setAge(Integer age) {
      this.age = age;
   }
   public Boolean getGender() {
      return gender;
   }
   public void setGender(Boolean gender) {
      this.gender = gender;
   }
   public Student() {
      super();
   }
   public Student(String name, Integer age, Boolean gender) {
      super();
      this.name = name;
      this.age = age;
      this.gender = gender;
   }
   @Override
   public String toString() {
      return "Student [name=" + name + ", age=" + age + ", gender=" + gender + "]";
   }
   
}
