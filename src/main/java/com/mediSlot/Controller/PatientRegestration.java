package com.mediSlot.Controller;

import java.io.IOException;
import java.sql.SQLException;

import com.mediSlot.dao.PatientDao;
import com.mediSlot.model.Patient;
import com.mediSlot.service.PatientService;
import com.mediSlot.util.DBConnection;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/patientRegestration")
public class PatientRegestration extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws IOException {
		try {
			res.setContentType("text/html");
			
			String fullName = req.getParameter("name");
			String email = req.getParameter("email");
			String phoneNo = req.getParameter("phone");
			String password = req.getParameter("password");
			DBConnection dbconnection = DBConnection.getDbConnection();
			PatientDao patientDao = new PatientDao(dbconnection);
			PatientService patientService = new PatientService(patientDao);
			
			Patient patientExist = new Patient(fullName, email, phoneNo, password);
			patientExist = patientService.findByPhoneNo(phoneNo);
			if (patientExist == null) {
				Patient patient = new Patient(fullName, email, phoneNo, password);
				patient = patientService.create(patient);
				RequestDispatcher requestDispatcher = req.getRequestDispatcher("patient_login.jsp");
				requestDispatcher.forward(req, res);

			} else {
				HttpSession session = req.getSession();
				session.setAttribute("regError", "Username Already Exist");
				res.sendRedirect("patient_regestration.jsp");
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
