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
@WebServlet("/del")
public class del  extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private Connection con;

    public void init(ServletConfig config)   {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "tiger");
        } catch (ClassNotFoundException | SQLException e) {
          e.printStackTrace();
        }
    }

    public void destroy() {
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	try {
    	String s1 = request.getParameter("UEMAIL");
        PreparedStatement ps =  con.prepareStatement("delete from userinfo where UEMAIL=?");  
        ps.setString(1, s1);
       int i= ps.executeUpdate();
       System.out.println(i);
        PrintWriter pw = response.getWriter();
        pw.println("<html><body bgcolor=pink text=black padding=30px margin=20px ><center><h1>");

        if (i > 0) {
           pw.println("<h1>Successful</h1>");
       } else {
          pw.println("<h1>User Data Not Delete.</h1>");
       }
       pw.println("</h1></center></body></html>");
    }
    	catch(SQLException e ) {
    		e.printStackTrace();
    	}
    	
    	
    }


}
