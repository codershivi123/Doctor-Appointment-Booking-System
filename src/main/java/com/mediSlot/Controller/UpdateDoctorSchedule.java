package com.mediSlot.Controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

import com.mediSlot.dao.DoctorScheduleDao;
import com.mediSlot.model.DoctorSchedule;
import com.mediSlot.service.DoctorScheduleService;
import com.mediSlot.util.DBConnection;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/updateDoctorSchedule")
public class UpdateDoctorSchedule extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws IOException {
		try {
			res.setContentType("text/html");
			HttpSession session = req.getSession();
			int doctorId = (Integer) session.getAttribute("scheduledDoctorId");
			String date = req.getParameter("scheduleDate");
			Date scheduleDate = Date.valueOf(date);
			String scheduleTime = req.getParameter("scheduleTime");

			DBConnection dcConnection = DBConnection.getDbConnection();
			DoctorScheduleDao doctorScheduleDao = new DoctorScheduleDao(dcConnection);
			DoctorScheduleService doctorScheduleService = new DoctorScheduleService(doctorScheduleDao);
			DoctorSchedule doctorSchedule = doctorScheduleService.findOneAccToDoctorDateTime(doctorId, scheduleDate,
					scheduleTime);
			int scheduleId = doctorSchedule.getScheduleID();
			boolean flag = doctorScheduleDao.updateScheduled(scheduleId, doctorSchedule);
			if (flag) {
				res.sendRedirect("doctor_dashboard.jsp");
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			res.sendRedirect("error.jsp");
		} catch (SQLException e) {
			e.printStackTrace();
			res.sendRedirect("error.jsp");
		} catch (IOException e) {
			e.printStackTrace();
			res.sendRedirect("error.jsp");
		}
	}
}
