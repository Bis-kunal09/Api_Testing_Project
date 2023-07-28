package com.torcai.student.service.Handlers;
import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

import com.torcai.student.service.StudentServiceClasses.Crud;
import com.torcai.student.service.StudentServiceClasses.StudentDelete;
import com.torcai.student.service.StudentServiceClasses.StudentInsert;
import com.torcai.student.service.StudentServiceClasses.StudentSelect;
import com.torcai.student.service.StudentServiceClasses.StudentUpdate;
import com.torcai.student.service.StudentServiceInterfaces.StudentCrudService;


public class CRUD extends AbstractHandler 
{
      public static Request Breq;
      public static HttpServletRequest HSReq;
      public static HttpServletResponse HSRes;
      
	public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException 
  {
		Breq   = baseRequest;
		HSReq = request;
		HSRes = response;
		HSRes.setContentType("application/json;charset=utf-8");
		HSRes.setStatus(HttpServletResponse.SC_OK);
		Breq.setHandled(true);
			System.out.println(request.getRequestURI());
			if(request.getRequestURI().contains("Insert")) 
			{
				StudentCrudService  StInsert = new StudentInsert();
		        StInsert.operate();
			}
			else if(request.getRequestURI().contains("Update")) 
			{
				StudentCrudService  StUpdate = new StudentUpdate();
		        StUpdate.operate();
			}
			else if(request.getRequestURI().contains("Delete")) 
			{
				StudentCrudService StDelete = new StudentDelete();
				StDelete.operate();
	
			}
			else if(request.getRequestURI().contains("Select"))
			{
				StudentCrudService StSelect = new StudentSelect();
				StSelect.operate();
			}
			else
			{
				StudentCrudService crud = new Crud();
				crud.operate();
			}
			
  }

}
