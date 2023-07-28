package com.torcai.student.service.StudentServiceClasses;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Pattern;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.torcai.student.service.Handlers.CRUD;
import com.torcai.student.service.StudentServiceInterfaces.StudentCrudService;
import com.torcai.student.service.main.Student;
import com.torcai.student.service.modelClass.StudentModel;

public class StudentSelect implements StudentCrudService 
{
    Connection ConnSelect = Student.connection;
    int id;
    Statement statement;
    boolean IdCount;
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
	public void operate() 
	{
		id=Integer.parseInt(CRUD.HSReq.getParameter("id"));
        ResultSet result;
        StudentModel Output = new StudentModel();
        	
        String regex = "(.)*(\\d)(.)*";      
        Pattern pattern = Pattern.compile(regex);
        String msg =CRUD.HSReq.getParameter("id");
        
        boolean containsNumber = pattern.matcher(msg).matches();
        try {
            statement= ConnSelect.createStatement();
            IdCount=statement.execute("SELECT * FROM Student WHERE id = " + CRUD.HSReq.getParameter("id"));
            } 
        catch (SQLException throwables)
    	    {
              throwables.printStackTrace();
            }  
        if((containsNumber == true)&&(IdCount==true))
        { 
        
            try {
                String query = "SELECT * FROM Student WHERE id = "+CRUD.HSReq.getParameter("id");
                Statement statement = ConnSelect.createStatement();
                result=statement.executeQuery(query);
                while (result.next()) 
                {
 				Output.setId(Integer.valueOf(result.getString("id")));
 				Output.setFname(result.getString("fname"));
 				Output.setLname(result.getString("lname"));
 				Output.setAge(Integer.valueOf(result.getString("age")));
                }
 				String json = gson.toJson(Output);
 				try {
					   CRUD.HSRes.getWriter().print(json);
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
        else
        {
        	try {
			        CRUD.HSRes.getWriter().print("Id Does Not exist Or invalid Id type Please Enter Valid Id ");
		        }    
        	catch (IOException e)
                {
			        e.printStackTrace();
		        }
        }
		
		
	}

}
