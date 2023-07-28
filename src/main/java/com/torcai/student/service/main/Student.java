package com.torcai.student.service.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;

import com.torcai.student.service.Handlers.CRUD;

public class Student {

	public static String url = "jdbc:mysql://localhost:3306/Employees";
    static String user = "root";
    static String password = "Hinata@999";
    public static Connection connection;
	static Request request;
    public static void main(String[] args) throws Exception {
    	
    	try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);
            System.out.print("Success");
    	    }
    	catch (ClassNotFoundException e) {
    		System.out.print("Failed");
    		e.printStackTrace();
            
        } catch (SQLException throwables) {
        	System.out.print("Failed2");
            throwables.printStackTrace();
        } 
    	
        Server server = new Server(9007);
        ContextHandler contextCrud = new ContextHandler("/Crud/*");
        contextCrud.setHandler(new CRUD());
        ContextHandlerCollection contexts = new ContextHandlerCollection();
        contexts.setHandlers(new Handler[] { contextCrud });
        server.setHandler(contexts);
        server.start();
        server.join();
        }
    
}	