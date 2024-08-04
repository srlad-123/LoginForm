package com.login;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Enumeration;

import jakarta.servlet.Servlet;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/res")
public class res extends HttpServlet {
	private Connection con;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "tiger");
			Long id=Long.parseLong(request.getParameter("USERID"));
			String s2 = request.getParameter("UFNAME");
			String s3 = request.getParameter("ULNAME");
			String s4 = request.getParameter("UEMAIL");
			//String s5 = request.getParameter("UPHNNO");
			Long phone = Long.parseLong(request.getParameter("UPHNNO"));
			String s6 = request.getParameter("UPASSWORD");

			PreparedStatement ps = con.prepareStatement("insert into userinfo values(?,?,?,?,?,?)");
			ps.setLong(1, id);
			ps.setString(2, s2);
			ps.setString(3, s3);
			ps.setString(4, s4);
			ps.setLong(5, phone);
			ps.setString(6, s6);
		    ps.executeUpdate();
		    
			PrintWriter pw = response.getWriter();
			pw.println("<html><body bgcolor=red><center><h1>");
			pw.println("Succesfully Registration......");
			pw.println("</h1><h2><a href=\"Login.html\">LoginPage</a></h2></center></body></html> ");
			
		 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		catch(IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
