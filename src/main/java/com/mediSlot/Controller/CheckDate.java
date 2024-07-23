package com.mediSlot.Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

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
import com.mediSlot.util.DBConnection;
import com.mediSlot.util.EmailSender;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/checkDate")
public class CheckDate extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws IOException {
		try {
			res.setContentType("text/html");
			PrintWriter out = res.getWriter();
			HttpSession session = req.getSession();

			String gender = req.getParameter("gender");
			String age = req.getParameter("age");
			String bloodGroup = req.getParameter("bloodGroup");
			String address = req.getParameter("address");
			int doctorId = Integer.parseInt(req.getParameter("doctor"));
			String date = req.getParameter("preferredDate");
			Date preferredDate = Date.valueOf(date);
			String preferredTime = req.getParameter("preferredTime");
			String appointmentReason = req.getParameter("appointmentReason");

			DBConnection dbConnection = DBConnection.getDbConnection();
			DoctorScheduleDao doctorScheduleDao = new DoctorScheduleDao(dbConnection);
			DoctorScheduleService doctorScheduleService = new DoctorScheduleService(doctorScheduleDao);
			DoctorSchedule doctorSchedule = doctorScheduleService.findOneAccToDoctorDateTime(doctorId, preferredDate,
					preferredTime);

			if (doctorSchedule != null) {
				if (doctorSchedule.getBlockedTime().equals("Blocked")
						|| doctorSchedule.getBlockedTime().equals("Booked")) {
					List<String> availableTimes = doctorScheduleService.findOneDoctroAccToDate(doctorId, preferredDate);
					if (!availableTimes.isEmpty()) {
						String doctorName = doctorSchedule.getDoctor().getDoctorName();

						session.setAttribute("gender", gender);
						session.setAttribute("age", age);
						session.setAttribute("bloodGroup", bloodGroup);
						session.setAttribute("address", address);
						session.setAttribute("doctorId", doctorId);
						session.setAttribute("doctor", doctorName);
						session.setAttribute("appointmentReason", appointmentReason);
						session.setAttribute("preferredDate", preferredDate);

						session.setAttribute("availableTimes", availableTimes);
						res.sendRedirect("appointmentSuggestDate.jsp");
					}
					else {
						session.setAttribute("timeError", "Appointment full!!....Please select another doctor rather then "+doctorSchedule.getDoctor().getDoctorName());
						res.sendRedirect("appointment.jsp");
					}
				} else {
					String phoneNo = (String) session.getAttribute("phoneNo");
					DBConnection connection = DBConnection.getDbConnection();
					PatientDao patientDao = new PatientDao(connection);
					Patient patient = patientDao.findByPhoneNo(phoneNo);
					if (patient != null) {
						int pAge = Integer.parseInt(age);
						AppointmentDao appointmentDao = new AppointmentDao(connection);
						AppointmentService appointmentService = new AppointmentService(appointmentDao);
						Appointment appointment = new Appointment(patient, doctorSchedule, gender, pAge, bloodGroup,
								address, preferredDate, preferredTime, appointmentReason);
						appointment = appointmentService.create(appointment);

						doctorSchedule = doctorScheduleService.findOneAccToDoctorDateTime(doctorId, preferredDate,
								preferredTime);

						int scheduleId = doctorSchedule.getScheduleID();
						doctorScheduleService.update(scheduleId, doctorSchedule);

						DoctorDao doctorDao = new DoctorDao(dbConnection);
						DoctorService doctorService = new DoctorService(doctorDao);

//================================================================================================================================
						EmailSender EmailSender = new EmailSender();
						String Pto = patient.getPatient_Email();
						String from = "medislot03@gmail.com";
						String Psubject = "Your Appointment is Booked!";
						String Ptext = "Dear " + patient.getFullName() + " ," + "\r\n" + "\r\n"
								+ "We hope this message finds you well. This email serves as a confirmation of your upcoming appointment scheduled on "
								+ preferredDate + " at " + preferredTime + " with "
								+ doctorService.findById(doctorId).getDoctorName() + ".\r\n" + "\r\n"
								+ "Please ensure to arrive promptly for your appointment. If there are any changes to your availability or if you need to reschedule, kindly inform us as soon as possible.\r\n"
								+ "\r\n"
								+ "Should you have any questions or require further assistance, please don't hesitate to reach out to us.\r\n"
								+ "\\r\n" + "Best regards,\r\n" + "\r\n" + "MediSlot";
						boolean Pb = EmailSender.sendEmail(Pto, from, Psubject, Ptext);
						if (Pb) {
							System.out.println("Email is sent successfully");
						} else {
							System.out.println("There is problem in sending email");
						}

						String Dto = doctorService.findById(doctorId).getDoctor_Email();
						String Dsubject = "Appointment Confirmation";
						String Dtext = "Dear " + doctorService.findById(doctorId).getDoctorName() + " ," + "\r\n"
								+ "\r\n"
								+ "I hope this email finds you well. I am writing to confirm an upcoming appointment for "
								+ patient.getFullName() + " scheduled on " + preferredDate + " at " + preferredTime
								+ ".\r\n" + "\r\n" + "Your Please be advised that " + patient.getFullName()
								+ " will be visiting your office for " + appointmentReason
								+ ". If there are any specific preparations or requirements needed prior to the appointment, kindly let us know at your earliest convenience.\r\n"
								+ "\r\n"
								+ "Thank you for your attention to this matter. Should you require any further information, please feel free to contact us.\r\n"
								+ "\r\n" + "Best regards,\r\n" + "\r\n" + "MediSlot";
						boolean Db = EmailSender.sendEmail(Dto, from, Dsubject, Dtext);
						if (Db) {
							System.out.println("Email is sent successfully");
						} else {
							System.out.println("There is problem in sending email");
						}
//================================================================================================================================

						RequestDispatcher requestDispatcher = req.getRequestDispatcher("appointment_book.jsp");
						requestDispatcher.forward(req, res);
					} else {
						out.print("<h1>No patinet Found<h1>");
					}
				}
			} else {
				session.setAttribute("timeError", "Please select appropriate time for selected doctor");
				res.sendRedirect("appointment.jsp");
			}
		} catch (ServletException e) {
			e.printStackTrace();
			res.sendRedirect("error.jsp");
		} catch (IOException e) {
			e.printStackTrace();
			res.sendRedirect("error.jsp");
		} catch (SQLException e) {
			e.printStackTrace();
			res.sendRedirect("error.jsp");
		} catch (Exception e) {
			e.printStackTrace();
			res.sendRedirect("error.jsp");
		}

	}
}
