package com.mediSlot.service;

import java.util.List;

import com.mediSlot.dao.TreatmentPlanDao;
import com.mediSlot.model.TreatmentPlan;

public class TreatmentPlanService implements IService<TreatmentPlan> {
	
	private TreatmentPlanDao treatmentPlanDao;

	public TreatmentPlanService(TreatmentPlanDao treatmentPlanDao) {
		this.treatmentPlanDao = treatmentPlanDao;
	}

	@Override
	public TreatmentPlan create(TreatmentPlan treatmentPlan) throws Exception {
		return treatmentPlanDao.create(treatmentPlan);
	}

	@Override
	public boolean update(int id, TreatmentPlan treatmentPlan) throws Exception {
		return treatmentPlanDao.update(id, treatmentPlan);
	}

	@Override
	public boolean delete(int id) throws Exception {
		return treatmentPlanDao.delete(id);
	}

	@Override
	public List<TreatmentPlan> findAll() throws Exception {
		return treatmentPlanDao.findAll();
	}

	public TreatmentPlan findById(int id) throws Exception {
		return treatmentPlanDao.findById(id);
	}
	
	public TreatmentPlan findByAppointmentId(int id) throws Exception {
		return treatmentPlanDao.findByAppointmentId(id);
	}

	public List<TreatmentPlan>  findAllRecordByPatientId(int id) throws Exception {
		return treatmentPlanDao.findAllRecordByPatientId(id);
	}
	
	public List<TreatmentPlan>  findAllRecordByDoctorId(int id) throws Exception {
		return treatmentPlanDao.findAllRecordByDocotrId(id);
	}
}
