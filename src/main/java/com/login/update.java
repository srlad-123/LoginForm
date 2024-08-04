package com.login;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/upd")
public class update extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Connection con;
	public void init(ServletConfig config) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "tiger");
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void destory() {
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	protected void doPost(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException{
		try {
		String s1 = req.getParameter("UEMAIL");
		String s2 = req.getParameter("UPASSWORD");

		PreparedStatement ps=con.prepareStatement("update userinfo set UPASSWORD=? where UEMAIL=? ");
		ps.setString(1, s2);
		ps.setString(2, s1);
		int i = ps.executeUpdate();
		System.out.println(i);
		PrintWriter pw = res.getWriter();
		pw.println(s1 + s2);
		pw.println("<html><body bgcolor =red text=black><center><h1>");

		if(i>0) {
			pw.println("Update your email address and password in succesfully.  ");
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


