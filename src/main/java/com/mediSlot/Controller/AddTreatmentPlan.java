package com.mediSlot.Controller;

import java.io.IOException;
import java.sql.Date;

import com.mediSlot.dao.AppointmentDao;
import com.mediSlot.dao.TreatmentPlanDao;
import com.mediSlot.model.Appointment;
import com.mediSlot.model.TreatmentPlan;
import com.mediSlot.service.AppointmentService;
import com.mediSlot.service.TreatmentPlanService;
import com.mediSlot.util.DBConnection;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/addTreatmentPlan")
public class AddTreatmentPlan extends HttpServlet{
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		try {
			HttpSession session = req.getSession();
			// Get a DB connection
			DBConnection dbConnection = DBConnection.getDbConnection();

			// Retrieve data from the req parameters
			int appointmentID = Integer.parseInt(req.getParameter("appointmentId"));
			String diagnosis = req.getParameter("diagnosis");
			String treatment = req.getParameter("treatment");
			String prescription = req.getParameter("prescription");
			String nextAppointmentDateStr = req.getParameter("nextAppointmentDate");
			String nextAppointmentTime = req.getParameter("nextAppointmentTime");


			// Parse the date string to java.sql.Date
			Date nextAppointmentDate = Date.valueOf(nextAppointmentDateStr);

			AppointmentDao AppointmentDao = new AppointmentDao(dbConnection);

			AppointmentService appointmentService = new AppointmentService(AppointmentDao);

			Appointment appointment = appointmentService.findById(appointmentID);


//
//			// Create a new TreatmentPlan object
			TreatmentPlan treatmentPlan = new TreatmentPlan();
			treatmentPlan.setAppointment(appointment);
			treatmentPlan.setDiagnosis(diagnosis);
			treatmentPlan.setTreatment(treatment);
			treatmentPlan.setPrescription(prescription);
			treatmentPlan.setNextAppointmentDate(nextAppointmentDate);
			treatmentPlan.setNextAppointmentTime(nextAppointmentTime);

//			// Initialize TreatmentPlanDao
			TreatmentPlanDao treatmentPlanDao = new TreatmentPlanDao(dbConnection);
//
			TreatmentPlanService treatmentPlanService = new TreatmentPlanService(treatmentPlanDao);
//
//			// Add the treatment plan to the database
			TreatmentPlan createdTreatment = treatmentPlanService.create(treatmentPlan);
//
//			
			if (createdTreatment != null) {

				session.setAttribute("treatmentAdded", "Treatment Plan added successfully");
				res.sendRedirect("doctor_dashboard.jsp");
			}
		} catch (Exception e) {
			// Handle any database errors
			e.printStackTrace();
			res.sendRedirect("error.jsp");
			
		}
	}
}
