package com.mediSlot.Controller;

import java.io.IOException;
import java.sql.SQLException;

import com.mediSlot.dao.DoctorDao;
import com.mediSlot.model.Doctor;
import com.mediSlot.service.DoctorService;
import com.mediSlot.util.DBConnection;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/doctorRegestration")
public class DoctorRegestration extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws IOException {
		try {
			res.setContentType("text/html");
			
			String doctorName = req.getParameter("name");
			String specialization = req.getParameter("specialization");
			String email = req.getParameter("email");
			String phoneNo = req.getParameter("phone");
			String password = req.getParameter("password");
			DBConnection dbconnection = DBConnection.getDbConnection();
			DoctorDao doctorDao = new DoctorDao(dbconnection);
			DoctorService doctorService = new DoctorService(doctorDao);
			Doctor doctorExist = new Doctor(doctorName, specialization, email, phoneNo, password);
			doctorExist = doctorService.findByPhoneNo(phoneNo);
			if (doctorExist == null) {
				Doctor doctor = new Doctor(doctorName, specialization, email, phoneNo, password);
				doctor = doctorService.create(doctor);

				RequestDispatcher requestDispatcher = req.getRequestDispatcher("doctor_login.jsp");
				requestDispatcher.forward(req, res);

			} else {
				HttpSession session = req.getSession();
				session.setAttribute("regError", "User Already Exist");
				res.sendRedirect("doctor_regestration.jsp");
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
