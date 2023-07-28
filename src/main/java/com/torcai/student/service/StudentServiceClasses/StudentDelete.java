package com.torcai.student.service.StudentServiceClasses;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Pattern;

import com.torcai.student.service.Handlers.CRUD;
import com.torcai.student.service.StudentServiceInterfaces.StudentCrudService;
import com.torcai.student.service.main.Student;
public class StudentDelete implements StudentCrudService 
{
    Connection ConnDelete = Student.connection;
    Statement statement; 
    boolean IdCount;
    int id;
	public void operate() 
	{
		String query = "DELETE FROM Student " + "WHERE id = " +CRUD.HSReq.getParameter("id");
        String regex = "(.)*(\\d)(.)*";      
        Pattern pattern = Pattern.compile(regex);
        String msg =CRUD.HSReq.getParameter("id");
        boolean containsNumber = pattern.matcher(msg).matches();
        try {
            statement= ConnDelete.createStatement();
            IdCount=statement.execute("SELECT * FROM Student WHERE id = " + CRUD.HSReq.getParameter("id"));
            } 
        catch (SQLException throwables)
    	    {
              throwables.printStackTrace();
            }  
        if((containsNumber == true)&&(IdCount==true))
        {
        
        	try {
                Statement statement = ConnDelete.createStatement();
                statement.executeUpdate(query);
                try {
					CRUD.HSRes.getWriter().print("{\"status\":\"Success\"}");
				} catch (IOException e) {
					// TODO Auto-generated catch block
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
