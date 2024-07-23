package com.mediSlot.service;

import java.sql.SQLException;
import java.util.List;

import com.mediSlot.dao.AppointmentDao;
import com.mediSlot.model.Appointment;

public class AppointmentService implements IService<Appointment> {
	private AppointmentDao appointmentDao;

	public AppointmentService(AppointmentDao appointmentDao) {
		this.appointmentDao = appointmentDao;
	}

	@Override
	public Appointment create(Appointment appointment) throws Exception {
		return appointmentDao.create(appointment);
	}

	@Override
	public boolean update(int id, Appointment appointment) throws Exception {
		return appointmentDao.update(id, appointment);
	}

	@Override
	public boolean delete(int id) throws Exception {
		return appointmentDao.delete(id);
	}

	@Override
	public List<Appointment> findAll() throws Exception {
		return appointmentDao.findAll();
	}

	public List<Appointment> findByDoctorId(int doctorID) throws SQLException {
		return appointmentDao.findByDoctorId(doctorID);
	}

	public Appointment findById(int id) {
		return appointmentDao.findById(id);
	}

	public List<Appointment> findByPatinetId(int id) {
		return appointmentDao.findByPatinetId(id);
	}
}
