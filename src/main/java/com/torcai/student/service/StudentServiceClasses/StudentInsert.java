package com.torcai.student.service.StudentServiceClasses;

import java.io.BufferedReader;
import java.io.IOException;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.sql.Statement;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.torcai.student.service.Handlers.CRUD;
import com.torcai.student.service.StudentServiceInterfaces.StudentCrudService;
import com.torcai.student.service.main.Student;
import com.torcai.student.service.modelClass.StudentModel;

public class StudentInsert implements StudentCrudService 
{
    String fname;
    String lname;
    int age;
	public void operate() 
	{
		  InputStream inputStream = null;
		  try {
		  	inputStream = CRUD.HSReq.getInputStream();
		  } catch (IOException e1)
		  {
		  	e1.printStackTrace();
		  };
        InputStreamReader isr = new InputStreamReader(inputStream);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(isr);
        System.out.print(json);
        StudentModel insrt = new Gson().fromJson(json,StudentModel.class);
        System.out.println(insrt.getAge());
        
        
        fname=insrt.getFname();
        lname=insrt.getLname();
        age = insrt.getAge();
        
        if ((fname!= null)&& (lname !=null))
        {
        	try {
                String query = "Insert into Student(fname,lname,age)"+" values("+"'"+fname+"'"+","+"'"+lname+"'"+","+ Integer.toString(age)+")";
                Statement statement = Student.connection.createStatement();
                statement.executeUpdate(query);
                try {
					CRUD.HSRes.getWriter().print("{\"status\":\"Success\"}");
				  } 
		      catch (IOException e) 
		          {
					e.printStackTrace();
				  }
        	  } 
         catch (SQLException throwables) {
            throwables.printStackTrace();
        } 
       }
		
	}


}
