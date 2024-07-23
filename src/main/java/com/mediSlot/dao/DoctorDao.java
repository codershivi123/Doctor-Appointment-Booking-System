package com.mediSlot.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mediSlot.model.Doctor;
import com.mediSlot.util.DBConnection;

public class DoctorDao implements IDao<Doctor> {
	private DBConnection dbConnection;
	private List<Doctor> doctors = new ArrayList<>();

	public DoctorDao() {
		super();
	}

	public DoctorDao(DBConnection dbConnection) {
		super();
		this.dbConnection = dbConnection;
	}

	@Override
	public Doctor create(Doctor doctor) {

		try {
			Connection connection = dbConnection.getConnection();
			String sqlQuery = "insert into Doctors (Doctor_Name, Specialization,Doctor_Email, Doctor_Phone_no,Doctor_Password) VALUES (?,?,?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
			preparedStatement.setString(1, doctor.getDoctorName());
			preparedStatement.setString(2, doctor.getSpecialization());
			preparedStatement.setString(3, doctor.getDoctor_Email());
			preparedStatement.setString(4, doctor.getDoctorPhoneNo());
			preparedStatement.setString(5, doctor.getDoctorPassword());
			if (preparedStatement.executeUpdate() < 0) {
				doctor = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return doctor;
	}

	@Override
	public boolean update(int id, Doctor doctor) {
		boolean result = false;
		try {
			Connection connection = dbConnection.getConnection();
			String sqlQuery = "update Doctors set Doctor_Name=?, Specialization=?,Doctor_Email=?, Doctor_Phone_no=?, Doctor_Password=? where DoctorID=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
			preparedStatement.setString(1, doctor.getDoctorName());
			preparedStatement.setString(2, doctor.getSpecialization());
			preparedStatement.setString(3, doctor.getDoctor_Email());
			preparedStatement.setString(4, doctor.getDoctorPhoneNo());
			preparedStatement.setString(5, doctor.getDoctorPassword());
			preparedStatement.setInt(6, id);
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
			String sqlQuery = "delete from Doctors where DoctorID=?";
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

	
	public Doctor findOne(String phoneNo, String password) throws SQLException {
		Connection connection = dbConnection.getConnection();
		final String sqlQuery = "select DoctorID, Doctor_Name, Specialization, Doctor_Email, Doctor_Phone_no,Doctor_Password from Doctors where Doctor_Phone_no=? AND Doctor_Password=? ";
		PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
		preparedStatement.setString(1, phoneNo);
		preparedStatement.setString(2, password);
		ResultSet resultSet = preparedStatement.executeQuery();
		Doctor doctor = null;
		if (resultSet.next()) {
			doctor = new Doctor(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4),resultSet.getString(5), resultSet.getString(6));
		}

		return doctor;
	}
	
	
	public Doctor findByPhoneNo(String phoneNo)throws SQLException {
		Connection connection = dbConnection.getConnection();
		final String sqlQuery = "select DoctorID, Doctor_Name, Specialization,Doctor_Email, Doctor_Phone_no,Doctor_Password from Doctors where Doctor_Phone_no=? ";
		PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
		preparedStatement.setString(1, phoneNo);
		ResultSet resultSet = preparedStatement.executeQuery();
		Doctor doctor = null;
		if (resultSet.next()) {
			doctor = new Doctor(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4),resultSet.getString(5), resultSet.getString(6));
		}

		return doctor;
	}
	
	public Doctor findById(int id)throws SQLException {
		Connection connection = dbConnection.getConnection();
		final String sqlQuery = "select * from Doctors where DoctorID=? ";
		PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
		preparedStatement.setInt(1, id);
		ResultSet resultSet = preparedStatement.executeQuery();
		Doctor doctor = null;
		if (resultSet.next()) {
			doctor = new Doctor(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4),resultSet.getString(5), resultSet.getString(6));
		}

		return doctor;
	}


	@Override
	public List<Doctor> findAll() throws SQLException {

		Connection connection = dbConnection.getConnection();
		Statement selectStatement = connection.createStatement();

		final String sqlQuery = "select * from Doctors";
		ResultSet resultSet = selectStatement.executeQuery(sqlQuery);

		while (resultSet.next()) {
			Doctor doctor = new Doctor(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4),resultSet.getString(5), resultSet.getString(6));
			doctors.add(doctor);

		}
		if (doctors.isEmpty())
			return null;
		return doctors;
	}

}
