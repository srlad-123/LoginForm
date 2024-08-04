package com.login;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


@WebServlet("/login1")
public class login extends HttpServlet {

	private static final long serialVersionUID = 1L;
	Connection con;
	public void init(ServletConfig config) {
		 
		try {			
			Class.forName("oracle.jdbc.driver.OracleDriver");

			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "tiger");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
            String s1 = request.getParameter("UserMail");

             String s2 = request.getParameter("upassword");
       System.out.println(s1);
       System.out.println(s2);
		PreparedStatement ps = con.prepareStatement("select * from userinfo where uemail=? and upassword=?");
		ps.setString(1, s1);
		ps.setString(2, s2);
		ResultSet r1=ps.executeQuery();
		
		PrintWriter pw = response.getWriter();
		pw.println("<html><body bgcolor =red text=black><center><h1>");

		if(r1.next()) {
			pw.println("Welcome to it course. your user name is "+s1);
		}
		else {
			pw.println(" Invalid email id and password ");
		}
		pw.println("</h1></center></body></html>");
	} catch (SQLException e) {
		e.printStackTrace();
	}
		catch(IOException e) {
			e.printStackTrace();
		} 
	}
	

}
