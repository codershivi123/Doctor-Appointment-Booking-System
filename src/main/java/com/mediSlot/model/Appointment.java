package com.mediSlot.model;

import java.sql.Date;

public class Appointment {
	private int appointmentID;
	private Patient patient;
	private DoctorSchedule doctorSchedule;
	private String gender;
	private int age;
	private String bloodGroup;
	private String address;
	private Date appointmentDate;
	private String appointmentTime;
	private String appointmentReason;

	public Appointment() {
		super();
	}

	public Appointment(int appointmentID, Patient patient, DoctorSchedule doctorSchedule, String gender, int age,
			String bloodGroup, String address, Date appointmentDate, String appointmentTime, String appointmentReason) {
		super();
		this.appointmentID = appointmentID;
		this.patient = patient;
		this.doctorSchedule = doctorSchedule;
		this.gender = gender;
		this.age = age;
		this.bloodGroup = bloodGroup;
		this.address = address;
		this.appointmentDate = appointmentDate;
		this.appointmentTime = appointmentTime;
		this.appointmentReason = appointmentReason;
	}
	
	public Appointment( Patient patient, DoctorSchedule doctorSchedule, String gender, int age,
			String bloodGroup, String address, Date appointmentDate, String appointmentTime, String appointmentReason) {
		super();
		this.patient = patient;
		this.doctorSchedule = doctorSchedule;
		this.gender = gender;
		this.age = age;
		this.bloodGroup = bloodGroup;
		this.address = address;
		this.appointmentDate = appointmentDate;
		this.appointmentTime = appointmentTime;
		this.appointmentReason = appointmentReason;
	}

	public int getAppointmentID() {
		return appointmentID;
	}

	public void setAppointmentID(int appointmentID) {
		this.appointmentID = appointmentID;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public DoctorSchedule getDoctorSchedule() {
		return doctorSchedule;
	}

	public void setDoctorSchedule(DoctorSchedule doctorSchedule) {
		this.doctorSchedule = doctorSchedule;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(Date appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public String getAppointmentTime() {
		return appointmentTime;
	}

	public void setAppointmentTime(String appointmentTime) {
		this.appointmentTime = appointmentTime;
	}

	public String getAppointmentReason() {
		return appointmentReason;
	}

	public void setAppointmentReason(String appointmentReason) {
		this.appointmentReason = appointmentReason;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + appointmentID;
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
		Appointment other = (Appointment) obj;
		if (appointmentID != other.appointmentID) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Appointment [appointmentID=" + appointmentID + ", patient=" + patient + ", doctorSchedule="
				+ doctorSchedule + ", gender=" + gender + ", age=" + age + ", bloodGroup=" + bloodGroup + ", address="
				+ address + ", appointmentDate=" + appointmentDate + ", appointmentTime=" + appointmentTime
				+ ", appointmentReason=" + appointmentReason + "]";
	}
	
	
}
