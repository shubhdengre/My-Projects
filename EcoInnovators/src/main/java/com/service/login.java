package com.service;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.*;

import java.sql.*;

public class login extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request,HttpServletResponse  response) throws IOException, ServletException {
		
		HttpSession session = request.getSession();
	    String expectedCaptchaCode = (String) session.getAttribute("captchaCode");
	    String userCaptchaCode = request.getParameter("captcha_input");
		String utype;
		String userid;
		String passwd;
		
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		utype=request.getParameter("user");
		userid = request.getParameter("email");
		passwd = request.getParameter("pwd");
		if(expectedCaptchaCode != null && expectedCaptchaCode.equals(userCaptchaCode)) {
		try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/EcoInnovators", "root", "server@123");            
            Statement pst = conn.createStatement();
            if(utype.equals("Admin")) {
            	ResultSet rs = pst.executeQuery("select * from admin;");
            	while(rs.next()) {
                  	if(rs.getString(1).equals(userid) && rs.getString(2).equals(passwd)) {
                  		Cookie ck=new Cookie("un",userid);
                		response.addCookie(ck);
                		response.sendRedirect("./Dashboard.html");
                		break;
                	} 
                	else {
                		out.print("<h1>Invalid Credentials!!!</h1>");
                		RequestDispatcher rd=request.getRequestDispatcher("/login.html");
                		rd.include(request,response);
                	}
                }
            }
            else if(utype.equals("Business")) {
            	ResultSet rs = pst.executeQuery("select * from bus;");
            	while(rs.next()) {
                  	if(rs.getString(1).equals(userid) && rs.getString(2).equals(passwd)) {
                  		Cookie ck=new Cookie("un",userid);
                		response.addCookie(ck);
                		response.sendRedirect("./Dashboard.html");
                		break;
                	} 
                	else {
                		out.print("<h1>Invalid Credentials!!!</h1>");
                		RequestDispatcher rd=request.getRequestDispatcher("/login.html");
                		rd.include(request,response);
                	}
                }
            }
            else if(utype.equals("Government")) {
            	ResultSet rs = pst.executeQuery("select * from gov;");
            	while(rs.next()) {
                  	if(rs.getString(1).equals(userid) && rs.getString(2).equals(passwd)) {
                  		Cookie ck=new Cookie("un",userid);
                		response.addCookie(ck);
                		response.sendRedirect("./Dashboard.html");
                		break;
                	} 
                	else {
                		out.print("<h1>Invalid Credentials!!!</h1>");
                		RequestDispatcher rd=request.getRequestDispatcher("/login.html");
                		rd.include(request,response);
                	}
                }
            }
            else if(utype.equals("Consumer")) {
            	ResultSet rs = pst.executeQuery("select * from con;");
            	while(rs.next()) {
                  	if(rs.getString(1).equals(userid) && rs.getString(2).equals(passwd)) {
                  		Cookie ck=new Cookie("un",userid);
                		response.addCookie(ck);
                		response.sendRedirect("./Dashboard.html");
                		break;
                	} 
                	else {
                		out.print("<h1>Invalid Credentials!!!</h1>");
                		RequestDispatcher rd=request.getRequestDispatcher("/login.html");
                		rd.include(request,response);
                	}
                }
            }
        } 
        catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
	}
	}
		else {
	        request.setAttribute("error", "Invalid CAPTCHA code.");
	        request.getRequestDispatcher("login.html").forward(request, response);
	    }
}}