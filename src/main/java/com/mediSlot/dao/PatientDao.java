package com.mediSlot.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mediSlot.model.Patient;
import com.mediSlot.util.DBConnection;

public class PatientDao implements IDao<Patient> {
	private DBConnection dbConnection;
	private List<Patient> patients = new ArrayList<>();

	public PatientDao() {
		super();
	}

	public PatientDao(DBConnection dbConnection) {
		super();
		this.dbConnection = dbConnection;
	}

	@Override
	public Patient create(Patient patient) {

		try {
			Connection connection = dbConnection.getConnection();
			String sqlQuery = "insert into patients (FullName, Patient_Email, Patient_Phone_no, Patient_Password) VALUES (?,?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
			preparedStatement.setString(1, patient.getFullName());
			preparedStatement.setString(2, patient.getPatient_Email());
			preparedStatement.setString(3, patient.getPatient_Phone_no());
			preparedStatement.setString(4, patient.getPatient_Password());
			if (preparedStatement.executeUpdate() < 0) {
				patient = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return patient;
	}

	@Override
	public boolean update(int id, Patient patient) {
		boolean result = false;
		try {
			Connection connection = dbConnection.getConnection();
			String sqlQuery = "update Patients set fullName=?, Patient_Email=?, Patient_Phone_no=? where patientId=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
			preparedStatement.setString(1, patient.getFullName());
			preparedStatement.setString(2, patient.getPatient_Email());
			preparedStatement.setString(3, patient.getPatient_Phone_no());
			preparedStatement.setInt(4, id);
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
			String sqlQuery = "delete from Patients where patientID=?";
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

	
	public Patient findOne(String phoneNo, String password) throws SQLException {
		Connection connection = dbConnection.getConnection();
		final String sqlQuery = "select patientID,FullName,Patient_Email,Patient_Phone_no,Patient_Password from Patients where Patient_Phone_no=? AND Patient_Password=? ";
		PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
		preparedStatement.setString(1, phoneNo);
		preparedStatement.setString(2, password);
		ResultSet resultSet = preparedStatement.executeQuery();
		Patient patient = null;
		if (resultSet.next()) {
			patient = new Patient(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5));
		}

		return patient;
	}
	
	
	public Patient findByPhoneNo(String phoneNo) throws SQLException {
		Connection connection = dbConnection.getConnection();
		final String sqlQuery = "select patientID,FullName,Patient_Email,Patient_Phone_no,Patient_Password from Patients where Patient_Phone_no=?";
		PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
		preparedStatement.setString(1, phoneNo);
		ResultSet resultSet = preparedStatement.executeQuery();
		Patient patient = null;
		if (resultSet.next()) {
			patient = new Patient(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5));
		}

		return patient;
	}
	
	@Override
	public List<Patient> findAll() throws SQLException {

		Connection connection = dbConnection.getConnection();
		Statement selectStatement = connection.createStatement();

		final String sqlQuery = "select * from Patients";
		ResultSet resultSet = selectStatement.executeQuery(sqlQuery);

		while (resultSet.next()) {
			Patient patient = new Patient(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5));
			patients.add(patient);

		}
		if (patients.isEmpty())
			return null;
		return patients;
	}
	
	public Patient findById(int id) {
		Patient patient = null;
		try {
			Connection connection = dbConnection.getConnection();
			String sqlQuery = "select * from Patients where PatientID=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				patient = new Patient(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5));
				return patient;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return patient;
	}

}
