package com.mediSlot.model;

import java.sql.Date;

public class TreatmentPlan {
	private int planId;
	private Appointment appointment;
	private String diagnosis;
	private String treatment;
	private String prescription;
	private Date nextAppointmentDate;
	private String nextAppointmentTime;

	// Constructors
	public TreatmentPlan() {
	}

	public TreatmentPlan(int planId, Appointment appointment, String diagnosis, String treatment, String prescription,
			Date nextAppointmentDate, String nextAppointmentTime) {
		super();
		this.planId = planId;
		this.appointment = appointment;
		this.diagnosis = diagnosis;
		this.treatment = treatment;
		this.prescription = prescription;
		this.nextAppointmentDate = nextAppointmentDate;
		this.nextAppointmentTime = nextAppointmentTime;
	}

	public TreatmentPlan(Appointment appointment, String diagnosis, String treatment, String prescription,
			Date nextAppointmentDate, String nextAppointmentTime) {
		super();
		this.appointment = appointment;
		this.diagnosis = diagnosis;
		this.treatment = treatment;
		this.prescription = prescription;
		this.nextAppointmentDate = nextAppointmentDate;
		this.nextAppointmentTime = nextAppointmentTime;
	}

	public int getPlanId() {
		return planId;
	}

	public void setPlanId(int planId) {
		this.planId = planId;
	}

	public Appointment getAppointment() {
		return appointment;
	}

	public void setAppointment(Appointment appointment) {
		this.appointment = appointment;
	}

	public String getDiagnosis() {
		return diagnosis;
	}

	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}

	public String getTreatment() {
		return treatment;
	}

	public void setTreatment(String treatment) {
		this.treatment = treatment;
	}

	public String getPrescription() {
		return prescription;
	}

	public void setPrescription(String prescription) {
		this.prescription = prescription;
	}

	public Date getNextAppointmentDate() {
		return nextAppointmentDate;
	}

	public void setNextAppointmentDate(Date nextAppointmentDate) {
		this.nextAppointmentDate = nextAppointmentDate;
	}

	public String getNextAppointmentTime() {
		return nextAppointmentTime;
	}

	public void setNextAppointmentTime(String nextAppointmentTime) {
		this.nextAppointmentTime = nextAppointmentTime;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + planId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		TreatmentPlan other = (TreatmentPlan) obj;
		if (planId != other.planId) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "TreatmentPlan [planId=" + planId + ", appointment=" + appointment + ", diagnosis=" + diagnosis
				+ ", treatment=" + treatment + ", prescription=" + prescription + ", nextAppointmentDate="
				+ nextAppointmentDate + ", nextAppointmentTime=" + nextAppointmentTime + "]";
	}

}