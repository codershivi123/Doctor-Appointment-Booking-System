package com.mediSlot.model;

public class Doctor {

	private int doctorId;
	private String doctorName;
	private String specialization;
	private String doctor_Email;
	private String doctorPhoneNo;
	private String doctorPassword;

	public Doctor() {
		super();
	}

	public Doctor(int doctorId, String doctorName, String specialization, String doctor_Email, String doctorPhoneNo,
			String doctorPassword) {
		super();
		this.doctorId = doctorId;
		this.doctorName = doctorName;
		this.specialization = specialization;
		this.doctor_Email = doctor_Email;
		this.doctorPhoneNo = doctorPhoneNo;
		this.doctorPassword = doctorPassword;
	}

	public Doctor(String doctorName, String specialization, String doctor_Email, String doctorPhoneNo,
			String doctorPassword) {
		super();
		this.doctorName = doctorName;
		this.specialization = specialization;
		this.doctor_Email = doctor_Email;
		this.doctorPhoneNo = doctorPhoneNo;
		this.doctorPassword = doctorPassword;
	}

	public int getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}

	public String getDoctor_Email() {
		return doctor_Email;
	}

	public void setDoctor_Email(String doctor_Email) {
		this.doctor_Email = doctor_Email;
	}

	public String getDoctorPhoneNo() {
		return doctorPhoneNo;
	}

	public void setDoctorPhoneNo(String doctorPhoneNo) {
		this.doctorPhoneNo = doctorPhoneNo;
	}

	public String getDoctorPassword() {
		return doctorPassword;
	}

	public void setDoctorPassword(String doctorPassword) {
		this.doctorPassword = doctorPassword;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + doctorId;
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
		Doctor other = (Doctor) obj;
		if (doctorId != other.doctorId) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Doctor [doctorId=" + doctorId + ", doctorName=" + doctorName + ", specialization=" + specialization
				+ ", doctor_Email=" + doctor_Email + ", doctorPhoneNo=" + doctorPhoneNo + ", doctorPassword="
				+ doctorPassword + "]";
	}

}
