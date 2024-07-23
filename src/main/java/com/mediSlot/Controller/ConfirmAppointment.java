package com.mediSlot.Controller;

import java.io.IOException;
//import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;

import com.mediSlot.dao.AppointmentDao;
import com.mediSlot.dao.DoctorDao;
import com.mediSlot.dao.DoctorScheduleDao;
import com.mediSlot.dao.PatientDao;
import com.mediSlot.model.Appointment;
import com.mediSlot.model.DoctorSchedule;
import com.mediSlot.model.Patient;
import com.mediSlot.service.AppointmentService;
import com.mediSlot.service.DoctorScheduleService;
import com.mediSlot.service.DoctorService;
import com.mediSlot.service.PatientService;
import com.mediSlot.util.DBConnection;
import com.mediSlot.util.EmailSender;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/confirmAppointment")
public class ConfirmAppointment extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest req, HttpServletResponse res) throws IOException {
		try {
			res.setContentType("text/html");

			HttpSession session = req.getSession();

			String phoneNo = (String) session.getAttribute("phoneNo");
			;
			DBConnection dbConnection = DBConnection.getDbConnection();

			PatientDao patientDao = new PatientDao(dbConnection);
			PatientService patientService = new PatientService(patientDao);
			Patient patient = patientService.findByPhoneNo(phoneNo);

			String gender = (String) session.getAttribute("gender");
			String getAge = (String) session.getAttribute("age");
			int age = Integer.parseInt(getAge);
			String bloodGroup = (String) session.getAttribute("bloodGroup");
			String address = (String) session.getAttribute("address");
			String appointmentReason = (String) session.getAttribute("appointmentReason");
			Date appoinmentDate = (Date) session.getAttribute("preferredDate");
			String appoinmentTime = req.getParameter("suggestedTime");


			int doctorId = (int) session.getAttribute("doctorId");
			DoctorScheduleDao doctorScheduleDao = new DoctorScheduleDao(dbConnection);
			DoctorScheduleService doctorScheduleService = new DoctorScheduleService(doctorScheduleDao);

			DoctorSchedule doctorSchedule = doctorScheduleService.findOneAccToDoctorDateTime(doctorId, appoinmentDate,
					appoinmentTime);
			
			DoctorDao doctorDao = new DoctorDao(dbConnection);
			DoctorService doctorService = new DoctorService(doctorDao);

			int scheduleId = doctorSchedule.getScheduleID();
			doctorScheduleService.update(scheduleId, doctorSchedule);
//================================================================================================================================
			EmailSender EmailSender = new EmailSender();
	        String Pto = patient.getPatient_Email();
	        String from = "medislot03@gmail.com";
	        String Psubject = "Your Appointment is Booked!";
	        String Ptext = "Dear "+patient.getFullName()+" ,"+"\r\n"
	        		+ "\r\n"
	        		+ "We hope this message finds you well. This email serves as a confirmation of your upcoming appointment scheduled on "+ appoinmentDate + " at " + appoinmentTime+" with "+doctorService.findById(doctorId).getDoctorName()+".\r\n"
	        		+ "\r\n"
	        		+ "Please ensure to arrive promptly for your appointment. If there are any changes to your availability or if you need to reschedule, kindly inform us as soon as possible.\r\n"
	        		+ "\r\n"
	        		+"Should you have any questions or require further assistance, please don't hesitate to reach out to us.\r\n"
	        		+ "\r\n"
	        		+ "Best regards,\r\n"
	        		+ "\r\n"
	        		+ "MediSlot";
	        boolean Pb = EmailSender.sendEmail(Pto, from, Psubject, Ptext);
	        if (Pb) {
	            System.out.println("Email is sent successfully");
	        } else {
	            System.out.println("There is problem in sending email");
	        }
	        
	        
	        String Dto = doctorService.findById(doctorId).getDoctor_Email();
	        String Dsubject = "Appointment Confirmation";
	        String Dtext = "Dear "+doctorService.findById(doctorId).getDoctorName()+" ,"+"\r\n"
	        		+ "\r\n"
	        		+ "I hope this email finds you well. I am writing to confirm an upcoming appointment for "+patient.getFullName()+" scheduled on "+ appoinmentDate + " at " + appoinmentTime+".\r\n"
	        		+ "\r\n"
	        		+ "Your Please be advised that "+patient.getFullName()+" will be visiting your office for "+appointmentReason+". If there are any specific preparations or requirements needed prior to the appointment, kindly let us know at your earliest convenience.\r\n"
	        		+ "\r\n"
	        		+ "Thank you for your attention to this matter. Should you require any further information, please feel free to contact us.\r\n"
	        		+ "\r\n"
	        		+ "Best regards,\r\n"
	        		+ "\r\n"
	        		+ "MediSlot";
	        boolean Db = EmailSender.sendEmail(Dto, from, Dsubject, Dtext);
	        if (Db) {
	            System.out.println("Email is sent successfully");
	        } else {
	            System.out.println("There is problem in sending email");
	        }

//================================================================================================================================

			AppointmentDao appointmentDao = new AppointmentDao(dbConnection);
			AppointmentService appointmentService = new AppointmentService(appointmentDao);
			Appointment appointment = new Appointment(patient, doctorSchedule, gender, age, bloodGroup, address,
					appoinmentDate, appoinmentTime, appointmentReason);
			appointment = appointmentService.create(appointment);
			if (appointment != null) {
				RequestDispatcher requestDispatcher = req.getRequestDispatcher("appointment_book.jsp");
				requestDispatcher.forward(req, res);
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
