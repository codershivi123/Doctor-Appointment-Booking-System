package com.mediSlot.Controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

import com.mediSlot.dao.DoctorDao;
import com.mediSlot.dao.DoctorScheduleDao;
import com.mediSlot.model.Doctor;
import com.mediSlot.model.DoctorSchedule;
import com.mediSlot.service.DoctorScheduleService;
import com.mediSlot.service.DoctorService;
import com.mediSlot.util.DBConnection;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/addDoctorSchedule")
public class AddDoctorSchedule extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws IOException {
		try {
			// Retrieve the doctor's phone number from the session
			HttpSession session = req.getSession();
			String doctorPhoneNo = (String) session.getAttribute("phoneNo");

			// Get a database connection
			DBConnection connection = DBConnection.getDbConnection();

			// Initialize DoctorDao to find doctor by phone number
			DoctorDao doctorDao = new DoctorDao(connection);
			DoctorService doctorService = new DoctorService(doctorDao);
			
			// Find the doctor using the phone number
			Doctor doctor = doctorService.findByPhoneNo(doctorPhoneNo);

			// Retrieve parameters from the request
			String scheduleDateStr = req.getParameter("newScheduleDate");
			String scheduleTime = req.getParameter("newScheduleTime");
			String status = req.getParameter("newScheduleStatus");

			// Convert scheduleDateStr to Date object
			Date scheduleDate = Date.valueOf(scheduleDateStr);

			// Create a new DoctorSchedule object
			DoctorSchedule doctorSchedule = new DoctorSchedule();
			doctorSchedule.setDoctor(doctor); // Set the doctor
			doctorSchedule.setAvailableDate(scheduleDate);
			doctorSchedule.setAvailableTime(scheduleTime);
			doctorSchedule.setBlockedTime(status);

			// Initialize DAO and Service objects for DoctorSchedule
			DoctorScheduleDao doctorScheduleDao = new DoctorScheduleDao(connection);
			DoctorScheduleService doctorScheduleService = new DoctorScheduleService(doctorScheduleDao);

			// Call the service method to add the new schedule
			DoctorSchedule newDoctorSchedule = doctorScheduleService.create(doctorSchedule);

			if (newDoctorSchedule != null) {
				session.setAttribute("AddedSchedule", "Schedule has been Added Sucessfully");
				RequestDispatcher requestDispatcher = req.getRequestDispatcher("doctor_dashboard.jsp");
				requestDispatcher.forward(req, res);

			} else {
				RequestDispatcher requestDispatcher = req.getRequestDispatcher("doctor_schedule_update.jsp");
				requestDispatcher.forward(req, res);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			res.sendRedirect("error.jsp");
		} catch (IllegalArgumentException e) {
			// Handle invalid input data errors
			e.printStackTrace();
			res.sendRedirect("error.jsp");
		} catch (Exception e) {
			e.printStackTrace();
			res.sendRedirect("error.jsp");
		}
	}
}
