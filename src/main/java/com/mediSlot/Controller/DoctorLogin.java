package com.mediSlot.Controller;

import java.io.IOException;
import java.sql.SQLException;

import com.mediSlot.dao.DoctorDao;
import com.mediSlot.model.Doctor;
import com.mediSlot.service.DoctorService;
import com.mediSlot.util.DBConnection;
import com.mediSlot.util.PasswordHashing;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/doctorLogin")
public class DoctorLogin extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html");
		try {
			String phoneNo = req.getParameter("phone");
			String password = req.getParameter("password");

			DBConnection dbConnection = DBConnection.getDbConnection();
			DoctorDao doctorDao = new DoctorDao(dbConnection);
			DoctorService doctorService = new DoctorService(doctorDao);

			Doctor doctor = new Doctor();
			doctor = doctorService.findByPhoneNo(phoneNo);

			if (doctor != null) {
				HttpSession session = req.getSession();
				session.setAttribute("phoneNo", phoneNo);
				String hashedPasswordFromDB = doctor.getDoctorPassword();
				// Verify the entered password against the hashed password from the database
				boolean passwordMatch = PasswordHashing.verifyPassword(password, hashedPasswordFromDB);
				if (passwordMatch) {
					RequestDispatcher requestDispatcher = req.getRequestDispatcher("doctor_dashboard.jsp");
					requestDispatcher.forward(req, res);
				} else {
					session.setAttribute("loginError", "Enter Valid Username or Password");
					res.sendRedirect("doctor_login.jsp");
				}
			} else {
				HttpSession session = req.getSession();
				session.setAttribute("loginError", "Enter Valid Username or Password");
				res.sendRedirect("doctor_login.jsp");
			}
		} catch (IOException e) {
			e.printStackTrace();
			res.sendRedirect("error.jsp");
		} catch (SQLException e) {
			e.printStackTrace();
			res.sendRedirect("error.jsp");
		} catch (ServletException e) {
			e.printStackTrace();
			res.sendRedirect("error.jsp");
		} catch (Exception e) {
			e.printStackTrace();
			res.sendRedirect("error.jsp");
		}

	}
}
