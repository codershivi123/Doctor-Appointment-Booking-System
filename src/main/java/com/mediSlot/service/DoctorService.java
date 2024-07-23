package com.mediSlot.service;

import java.util.List;

import com.mediSlot.dao.DoctorDao;
import com.mediSlot.model.Doctor;
import com.mediSlot.util.PasswordHashing;

public class DoctorService implements IService<Doctor> {
	private DoctorDao doctorDao;

	public DoctorService(DoctorDao doctorDao) {
		this.doctorDao = doctorDao;
	}

	@Override
	public Doctor create(Doctor doctor) throws Exception {
		String hashedPassword = PasswordHashing.hashPassword(doctor.getDoctorPassword());
        doctor.setDoctorPassword(hashedPassword);
		return doctorDao.create(doctor);
	}

	@Override
	public boolean update(int id, Doctor doctor) throws Exception {
		return doctorDao.update(id, doctor);
	}

	@Override
	public boolean delete(int id) throws Exception {
		return doctorDao.delete(id);
	}

	@Override
	public List<Doctor> findAll() throws Exception {
		return doctorDao.findAll();
	}
	
	public Doctor findOne(String phoneNo, String password) throws Exception {
		return doctorDao.findOne(phoneNo, password);
	}
	
 
	public Doctor findByPhoneNo(String phoneNo) throws Exception{ 
		return doctorDao.findByPhoneNo(phoneNo); 
	}

	public Doctor findById(int id) throws Exception{ 
		return doctorDao.findById(id); 
	}
}
