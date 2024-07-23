package com.mediSlot.Controller;

import java.io.IOException;
import java.sql.SQLException;

import com.mediSlot.dao.PatientDao;
import com.mediSlot.model.Patient;
import com.mediSlot.service.PatientService;
import com.mediSlot.util.DBConnection;
import com.mediSlot.util.PasswordHashing;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/patientLogin")
public class PatientLogin extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html");
		try {
			String phoneNo = req.getParameter("phone");
			String password = req.getParameter("password");

			DBConnection dbConnection = DBConnection.getDbConnection();
			PatientDao patientDao = new PatientDao(dbConnection);
			PatientService patientService = new PatientService(patientDao);

			Patient patient = new Patient();
			patient = patientService.findByPhoneNo(phoneNo);

			if (patient != null) {
				HttpSession session = req.getSession();
				session.setAttribute("phoneNo", phoneNo);

				String hashedPasswordFromDB = patient.getPatient_Password();
				// Verify the entered password against the hashed password from the database
				boolean passwordMatch = PasswordHashing.verifyPassword(password, hashedPasswordFromDB);
				if (passwordMatch) {
					RequestDispatcher requestDispatcher = req.getRequestDispatcher("patient_dashboard.jsp");
					requestDispatcher.forward(req, res);
				} else {
					session.setAttribute("loginError", "Enter Valid Username or Password");
					res.sendRedirect("patient_login.jsp");
				}
			} else {
				HttpSession session = req.getSession();
				session.setAttribute("loginError", "Enter Valid Username or Password");
				res.sendRedirect("patient_login.jsp");
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
