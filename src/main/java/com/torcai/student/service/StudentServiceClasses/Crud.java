package com.torcai.student.service.StudentServiceClasses;

import java.io.IOException;


import com.torcai.student.service.Handlers.CRUD;
import com.torcai.student.service.StudentServiceInterfaces.StudentCrudService;

public class Crud implements StudentCrudService
{

	public void operate() 
	{
		try 
		  {
			CRUD.HSRes.getWriter().println("<h1>" + "Please Perform Your Query" + "</h1>");
		  } 
		catch (IOException e) 
		  {
			e.printStackTrace();
		  }
		
	}
  
}
