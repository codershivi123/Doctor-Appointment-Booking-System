package com.mediSlot.model;

import java.util.Date;

public class DoctorSchedule {
	private int scheduleID;
    private Doctor doctor;
    private Date availableDate;
    private String availableTime;
    private String BlockedTime;
    
	public DoctorSchedule() {
		super();
	}

	public DoctorSchedule(int scheduleID, Doctor doctor, Date availableDate, String availableTime, String blockedTime) {
		super();
		this.scheduleID = scheduleID;
		this.doctor = doctor;
		this.availableDate = availableDate;
		this.availableTime = availableTime;
		BlockedTime = blockedTime;
	}

	public DoctorSchedule(Doctor doctor, Date availableDate, String availableTime, String blockedTime) {
		super();
		this.doctor = doctor;
		this.availableDate = availableDate;
		this.availableTime = availableTime;
		BlockedTime = blockedTime;
	}

	public int getScheduleID() {
		return scheduleID;
	}

	public void setScheduleID(int scheduleID) {
		this.scheduleID = scheduleID;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public Date getAvailableDate() {
		return availableDate;
	}

	public void setAvailableDate(Date availableDate) {
		this.availableDate = availableDate;
	}

	public String getAvailableTime() {
		return availableTime;
	}

	public void setAvailableTime(String availableTime) {
		this.availableTime = availableTime;
	}

	public String getBlockedTime() {
		return BlockedTime;
	}

	public void setBlockedTime(String blockedTime) {
		BlockedTime = blockedTime;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + scheduleID;
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
		DoctorSchedule other = (DoctorSchedule) obj;
		if (scheduleID != other.scheduleID) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "DoctorSchedule [scheduleID=" + scheduleID + ", doctor=" + doctor + ", availableDate=" + availableDate
				+ ", availableTime=" + availableTime + ", BlockedTime=" + BlockedTime + "]";
	}
    
	
    
}
