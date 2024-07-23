package com.mediSlot.model;

public class Patient {
	private int patientId;
	private String fullName;
	private String patient_Email;
	private String patient_Phone_no;
	private String patient_Password;

	public Patient() {
		super();
	}

	

	public Patient(int patientId, String fullName, String patient_Email, String patient_Phone_no,
			String patient_Password) {
		super();
		this.patientId = patientId;
		this.fullName = fullName;
		this.patient_Email = patient_Email;
		this.patient_Phone_no = patient_Phone_no;
		this.patient_Password = patient_Password;
	}

	public Patient(String fullName, String patient_Email, String patient_Phone_no, String patient_Password) {
		super();
		this.fullName = fullName;
		this.patient_Email = patient_Email;
		this.patient_Phone_no = patient_Phone_no;
		this.patient_Password = patient_Password;
	}



	public int getPatientId() {
		return patientId;
	}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
	public String getPatient_Email() {
		return patient_Email;
	}


	public void setPatient_Email(String patient_Email) {
		this.patient_Email = patient_Email;
	}

	public String getPatient_Phone_no() {
		return patient_Phone_no;
	}

	public void setPatient_Phone_no(String patient_Phone_no) {
		this.patient_Phone_no = patient_Phone_no;
	}

	public String getPatient_Password() {
		return patient_Password;
	}

	public void setPatient_Password(String patient_Password) {
		this.patient_Password = patient_Password;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + patientId;
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
		Patient other = (Patient) obj;
		if (patientId != other.patientId) {
			return false;
		}
		return true;
	}



	@Override
	public String toString() {
		return "Patient [patientId=" + patientId + ", fullName=" + fullName + ", patient_Email=" + patient_Email
				+ ", patient_Phone_no=" + patient_Phone_no + ", patient_Password=" + patient_Password + "]";
	}

	
}
