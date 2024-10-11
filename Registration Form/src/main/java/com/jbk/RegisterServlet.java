package com.jbk;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        
        // Fetch form data
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String mobile = req.getParameter("mobile");
        String dob = req.getParameter("dob");
        String passingYear = req.getParameter("passingYear");
        String specialization = req.getParameter("specialization");
        String percent10 = req.getParameter("percent10");
        String percent12 = req.getParameter("percent12");
        String graduationPercent = req.getParameter("graduationPercent");
        String experience = req.getParameter("experience");
        String gender = req.getParameter("gender");

        try {
            // Connect to MySQL Database
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo1184", "root", "suraj");

            // Prepare SQL query
            String query = "insert into users (first_name, last_name, email, password, mobile, dob, passing_year, specialization, percent_10, percent_12, graduation_percent, experience, gender) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, firstName);
            ps.setString(2, lastName);
            ps.setString(3, email);
            ps.setString(4, password);
            ps.setString(5, mobile);
            ps.setString(6, dob);
            ps.setString(7, passingYear);
            ps.setString(8, specialization);
            ps.setDouble(9, Double.parseDouble(percent10));
            ps.setDouble(10, Double.parseDouble(percent12));
            ps.setDouble(11, Double.parseDouble(graduationPercent));
            ps.setString(12, experience);
            ps.setString(13, gender);

            // Execute the query
            int result = ps.executeUpdate();
            
            // Response based on result
            if (result > 0) {
                out.println("<h2>Registration successful!</h2>");
            } else {
                out.println("<h2>Registration failed. Please try again!</h2>");
            }

            // Close connections
            ps.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
            out.println("<h2>Error occurred: " + e.getMessage() + "</h2>");
        }
    }
}

