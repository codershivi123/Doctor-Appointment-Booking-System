package com.mediSlot.service;

import java.util.List;

import com.mediSlot.dao.PatientDao;
import com.mediSlot.model.Patient;
import com.mediSlot.util.PasswordHashing;

public class PatientService implements IService<Patient> {

	private PatientDao patientDao;

	public PatientService(PatientDao patientDao) {
		this.patientDao = patientDao;
	}

	@Override
	public Patient create(Patient patient) throws Exception {
		// Hash the password before passing it to the DAO
		String hashedPassword = PasswordHashing.hashPassword(patient.getPatient_Password());
		patient.setPatient_Password(hashedPassword);
		return patientDao.create(patient);
	}

	@Override
	public boolean update(int id, Patient patient) throws Exception {
		return patientDao.update(id, patient);
	}

	@Override
	public boolean delete(int id) throws Exception {
		return patientDao.delete(id);
	}

	@Override
	public List<Patient> findAll() throws Exception {
		return patientDao.findAll();
	}
	
	public Patient findOne(String phoneNo, String password) throws Exception {
		return patientDao.findOne(phoneNo, password);
	}

	public Patient findByPhoneNo(String phoneNo) throws Exception {

		return patientDao.findByPhoneNo(phoneNo);
	}

	public Patient findById(int id) {
		return patientDao.findById(id);
	}
	
}
