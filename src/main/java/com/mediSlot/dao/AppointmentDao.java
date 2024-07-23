package com.mediSlot.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mediSlot.model.Appointment;
import com.mediSlot.model.DoctorSchedule;
import com.mediSlot.model.Patient;
import com.mediSlot.util.DBConnection;

public class AppointmentDao implements IDao<Appointment> {
	
	private DBConnection dbConnection;
	private List<Appointment> appointments = new ArrayList<>();

	public AppointmentDao() {
		super();
	}

	public AppointmentDao(DBConnection dbConnection) {
		super();
		this.dbConnection = dbConnection;
	}

	@Override
	public Appointment create(Appointment appointment) {

		try {
			Connection connection = dbConnection.getConnection();
			String sqlQuery = "INSERT INTO Appointments (PatientID, ScheduleID, Gender, Age, BloodGroup, Address, appointmentDate, appointmentTime, AppointmentReason)\r\n"
					+ "VALUES (?,?,?,?,?,?,?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
			preparedStatement.setInt(1, appointment.getPatient().getPatientId());
			preparedStatement.setInt(2, appointment.getDoctorSchedule().getScheduleID());
			preparedStatement.setString(3, appointment.getGender());
			preparedStatement.setInt(4, appointment.getAge());
			preparedStatement.setString(5,appointment.getBloodGroup() );
			preparedStatement.setString(6, appointment.getAddress());
			preparedStatement.setDate(7, appointment.getAppointmentDate());
			preparedStatement.setString(8, appointment.getAppointmentTime());
			preparedStatement.setString(9, appointment.getAppointmentReason());
			if (preparedStatement.executeUpdate() < 0) {
				appointment = null;
				preparedStatement.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return appointment;
	}

//	IMPLEMENT ACCORDING TO USE
	@Override
	public boolean update(int id, Appointment appointment) {
		boolean result = false;
		try {
			Connection connection = dbConnection.getConnection();
			String sqlQuery = "update Appointments set fullName=?, Patient_Phone_no=? where patientId=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
			
			if (preparedStatement.executeUpdate() > 0) {
				result = true;
				System.out.println("Upadte Succefully");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (!result) {
			System.out.println("Upadte Unsuccefully");
		}
		return result;
	}

	@Override
	public boolean delete(int id) {
		boolean result = false;
		try {
			Connection connection = dbConnection.getConnection();
			String sqlQuery = "delete from Appointments where AppointmentID=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
			preparedStatement.setInt(1, id);
			if (preparedStatement.executeUpdate() > 0) {
				System.out.println("Delete Succefully");
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}
	
	@Override
	public List<Appointment> findAll() throws SQLException {

		Connection connection = dbConnection.getConnection();
		Statement selectStatement = connection.createStatement();

		final String sqlQuery = "select * from Appointments";
		ResultSet resultSet = selectStatement.executeQuery(sqlQuery);

		while (resultSet.next()) {
			int patientId = resultSet.getInt("PatientID");
			int ScheduleID = resultSet.getInt("ScheduleID");
			PatientDao patientDao = new PatientDao(dbConnection);
			Patient patient=patientDao.findById(patientId);
			DoctorScheduleDao doctorScheduleDao = new DoctorScheduleDao(dbConnection);
			DoctorSchedule doctorSchedule=doctorScheduleDao.findById(ScheduleID);
			
			Appointment appointment = new Appointment(resultSet.getInt("AppointmentID"), patient, doctorSchedule, resultSet.getString("Gender"), resultSet.getInt("Age"), resultSet.getString("BloodGroup"), resultSet.getString("Address"), resultSet.getDate("AppointmentDate"), resultSet.getString("AppointmentTime"), resultSet.getString("AppointmentReason"));
			appointments.add(appointment);

		}
		if (appointments.isEmpty())
			return null;
		
		return appointments;
	}
	
	public List<Appointment> findByDoctorId(int doctorID) throws SQLException {

		Connection connection = dbConnection.getConnection();

		final String sqlQuery = "select * from  appointments a join doctorschedules ds on a.scheduleid = ds.scheduleid where ds.doctorid=?";

		PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
		preparedStatement.setInt(1, doctorID);

		ResultSet resultSet = preparedStatement.executeQuery();
		
		while (resultSet.next()) {
			int patientId = resultSet.getInt("PatientID");
			int ScheduleID = resultSet.getInt("ScheduleID");
			PatientDao patientDao = new PatientDao(dbConnection);
			Patient patient=patientDao.findById(patientId);
			DoctorScheduleDao doctorScheduleDao = new DoctorScheduleDao(dbConnection);
			DoctorSchedule doctorSchedule=doctorScheduleDao.findById(ScheduleID);
			
			Appointment appointment = new Appointment(resultSet.getInt("AppointmentID"), patient, doctorSchedule, resultSet.getString("Gender"), resultSet.getInt("Age"), resultSet.getString("BloodGroup"), resultSet.getString("Address"), resultSet.getDate("AppointmentDate"), resultSet.getString("AppointmentTime"), resultSet.getString("AppointmentReason"));
			appointments.add(appointment);

		}
		if (appointments.isEmpty())
			return null;
		
		return appointments;
	}
	
	

	public Appointment findById(int id) {
		Appointment appointment = null;
		try {
			Connection connection = dbConnection.getConnection();
			String sqlQuery = "select * from Appointments where AppointmentID=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				int patientId = resultSet.getInt("PatientID");
				int ScheduleID = resultSet.getInt("ScheduleID");
				PatientDao patientDao = new PatientDao(dbConnection);
				Patient patient=patientDao.findById(patientId);
				DoctorScheduleDao doctorScheduleDao = new DoctorScheduleDao(dbConnection);
				DoctorSchedule doctorSchedule=doctorScheduleDao.findById(ScheduleID);
				appointment = new Appointment(resultSet.getInt("AppointmentID"), patient, doctorSchedule, resultSet.getString("Gender"), resultSet.getInt("Age"), resultSet.getString("BloodGroup"), resultSet.getString("Address"), resultSet.getDate("AppointmentDate"), resultSet.getString("AppointmentTime"), resultSet.getString("AppointmentReason"));
				return appointment;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return appointment;
	}
	
	public List<Appointment> findByPatinetId(int id) {
		try {
			Connection connection = dbConnection.getConnection();
			String sqlQuery = "select * from Appointments where patientID=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				int patientId = resultSet.getInt("PatientID");
				int ScheduleID = resultSet.getInt("ScheduleID");
				PatientDao patientDao = new PatientDao(dbConnection);
				Patient patient=patientDao.findById(patientId);
				DoctorScheduleDao doctorScheduleDao = new DoctorScheduleDao(dbConnection);
				DoctorSchedule doctorSchedule=doctorScheduleDao.findById(ScheduleID);
				Appointment appointment = new Appointment(resultSet.getInt("AppointmentID"), patient, doctorSchedule, resultSet.getString("Gender"), resultSet.getInt("Age"), resultSet.getString("BloodGroup"), resultSet.getString("Address"), resultSet.getDate("AppointmentDate"), resultSet.getString("AppointmentTime"), resultSet.getString("AppointmentReason"));
				appointments.add(appointment);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if (appointments.isEmpty())
			return null;
		return appointments;
	}
}
