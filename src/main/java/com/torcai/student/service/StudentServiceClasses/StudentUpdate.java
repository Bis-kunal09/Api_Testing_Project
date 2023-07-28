package com.torcai.student.service.StudentServiceClasses;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import com.google.gson.Gson;
import com.torcai.student.service.Handlers.CRUD;
import com.torcai.student.service.StudentServiceInterfaces.StudentCrudService;
import com.torcai.student.service.main.Student;
import com.torcai.student.service.modelClass.StudentModel;
public class StudentUpdate implements StudentCrudService
{

   public void operate() 
  {
   String fname,lname;
   Connection ConnUpdate = Student.connection;
   int age,id;
   Statement statement;

   InputStream inputStream = null;
try {
	inputStream = CRUD.HSReq.getInputStream();
} catch (IOException e1)
{
	e1.printStackTrace();
}
   InputStreamReader isr = new InputStreamReader(inputStream);
   StudentModel insrt = new Gson().fromJson(isr, StudentModel.class);
   fname=insrt.getFname();
   lname=insrt.getLname();
   age = insrt.getAge();
   id  = insrt.getId();
		   
     try {
		    String query = "UPDATE Student " +"SET fname='"+fname+"', "+" lname = '"+lname+"', "+  "age ="+Integer.toString(age)+" WHERE id=" + Integer.toString(id);
		    statement = ConnUpdate.createStatement();
		    statement.executeUpdate(query);
		      try {
					CRUD.HSRes.getWriter().print("{\"status\":\"Success\"}");
				  } 
		      catch (IOException e) 
		          {
					e.printStackTrace();
				  }
		  } 
	 catch (SQLException throwables) 
		  {
		     throwables.printStackTrace();
		  } 
		      
  }
		  
	

}
