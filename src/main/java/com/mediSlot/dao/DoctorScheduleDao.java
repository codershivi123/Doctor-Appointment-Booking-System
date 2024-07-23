package com.mediSlot.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mediSlot.model.Doctor;
import com.mediSlot.model.DoctorSchedule;
import com.mediSlot.util.DBConnection;

public class DoctorScheduleDao implements IDao<DoctorSchedule> {
	private DBConnection dbConnection;
	private List<DoctorSchedule> doctorSchedules = new ArrayList<>();

	public DoctorScheduleDao() {
		super();
	}

	public DoctorScheduleDao(DBConnection dbConnection) {
		super();
		this.dbConnection = dbConnection;
	}

	@Override
	public DoctorSchedule create(DoctorSchedule doctorSchedule) {

		try {
			Connection connection = dbConnection.getConnection();
			String sqlQuery = "insert into DoctorSchedules (DoctorID, AvailableDate, AvailableTime, BlockedTime) VALUES (?,?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
			preparedStatement.setInt(1, doctorSchedule.getDoctor().getDoctorId());
			preparedStatement.setDate(2, (Date) doctorSchedule.getAvailableDate());
			preparedStatement.setString(3, doctorSchedule.getAvailableTime());
			preparedStatement.setString(4, doctorSchedule.getBlockedTime());
			if (preparedStatement.executeUpdate() < 0) {
				doctorSchedule = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return doctorSchedule;
	}

	@Override
	public boolean update(int id, DoctorSchedule doctorSchedule) {
		boolean result = false;
		try {
			Connection connection = dbConnection.getConnection();
			String sqlQuery = "update DoctorSchedules set BlockedTime='Booked' where scheduleID=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
			preparedStatement.setInt(1, id);
			if (preparedStatement.executeUpdate() > 0) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (!result) {
			System.out.println("Update Unsuccefully");
		}
		return result;
	}
	
	public boolean updateScheduled(int id, DoctorSchedule doctorSchedule) {
		boolean result = false;
		try {
			Connection connection = dbConnection.getConnection();
			String sqlQuery = "update DoctorSchedules set BlockedTime='Blocked' where scheduleID=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
			preparedStatement.setInt(1, id);
			if (preparedStatement.executeUpdate() > 0) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (!result) {
			System.out.println("Update Unsuccefully");
		}
		return result;
	}

	@Override
	public boolean delete(int id) {
		boolean result = false;
		try {
			Connection connection = dbConnection.getConnection();
			String sqlQuery = "delete from DoctorSchedules where scheduleID=?";
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

	public DoctorSchedule findOneAccToDoctorDateTime(int doctorId, Date availableDate, String availableTime)
			throws SQLException {
		Connection connection = dbConnection.getConnection();
		final String sqlQuery = "select ScheduleID,DoctorID , AvailableDate, AvailableTime, BlockedTime from DoctorSchedules where DoctorID=? AND AvailableDate=? AND AvailableTime=? ";
		PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
		preparedStatement.setInt(1, doctorId);
		preparedStatement.setDate(2, availableDate);
		preparedStatement.setString(3, availableTime);
		ResultSet resultSet = preparedStatement.executeQuery();

		DoctorSchedule doctorSchedule = null;
		if (resultSet.next()) {
			final String sqlQuery1 = "select * from Doctors where doctorId=" + doctorId;
			Statement statement = connection.createStatement();
			ResultSet resultSet2 = statement.executeQuery(sqlQuery1);
			if (resultSet2.next()) {
				Doctor doctor = new Doctor(resultSet2.getInt(1), resultSet2.getString(2), resultSet2.getString(3),
						resultSet2.getString(4), resultSet2.getString(5), resultSet2.getString(6));

				doctorSchedule = new DoctorSchedule(resultSet.getInt(1), doctor, resultSet.getDate(3),
						resultSet.getString(4), resultSet.getString(5));
			}
		}
		return doctorSchedule;
	}

	public List<String> findOneDoctroAccToDate(int doctorID, Date availableDate) throws SQLException {
		Connection connection = dbConnection.getConnection();
		final String sqlQuery = "select AvailableTime from DoctorSchedules where DoctorID=? AND AvailableDate=?  AND BlockedTime='Available' ";
		PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
		preparedStatement.setInt(1, doctorID);
		preparedStatement.setDate(2, availableDate);
		ResultSet resultSet = preparedStatement.executeQuery();
		List<String> availableTimes = new ArrayList<>();
		while (resultSet.next()) {
			availableTimes.add(resultSet.getString("AvailableTime"));
		}

		return availableTimes;
	}

	@Override
	public List<DoctorSchedule> findAll() throws SQLException {

		Connection connection = dbConnection.getConnection();
		Statement selectStatement = connection.createStatement();

		final String sqlQuery = "select * from DoctorSchedules";
		ResultSet resultSet = selectStatement.executeQuery(sqlQuery);

		while (resultSet.next()) {

			int doctorId = resultSet.getInt("DoctorID");
			DoctorDao doctorDao = new DoctorDao(dbConnection);
			Doctor doctor = doctorDao.findById(doctorId);
			DoctorSchedule doctorSchedule = new DoctorSchedule(resultSet.getInt(1), doctor, resultSet.getDate(3),
					resultSet.getString(4), resultSet.getString(5));
			doctorSchedules.add(doctorSchedule);

		}
		if (doctorSchedules.isEmpty())
			return null;
		return doctorSchedules;
	}

	public DoctorSchedule findById(int id) {
		DoctorSchedule doctorSchedule = null;
		try {
			Connection connection = dbConnection.getConnection();
			String sqlQuery = "select * from DoctorSchedules where ScheduleID=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				int doctorId = resultSet.getInt("DoctorID");
				final String sqlQuery1 = "select * from Doctors where doctorId=" + doctorId;
				Statement statement = connection.createStatement();
				ResultSet resultSet2 = statement.executeQuery(sqlQuery1);
				if (resultSet2.next()) {
					Doctor doctor = new Doctor(resultSet2.getInt(1), resultSet2.getString(2), resultSet2.getString(3),
							resultSet2.getString(4), resultSet2.getString(5), resultSet2.getString(6));

					doctorSchedule = new DoctorSchedule(resultSet.getInt(1), doctor, resultSet.getDate(3),
							resultSet.getString(4), resultSet.getString(5));
					return doctorSchedule;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return doctorSchedule;
	}

	public List<String> findAllTime() throws Exception {
		Connection connection = dbConnection.getConnection();
		Statement selectStatement = connection.createStatement();

		List<String> availableTimes = new ArrayList<>();

		final String sqlQuery = "select distinct AvailableTime from Doctorschedules";
		ResultSet resultSet = selectStatement.executeQuery(sqlQuery);
		while (resultSet.next()) {
			availableTimes.add(resultSet.getString(1));
		}
		
		if(availableTimes.isEmpty())
			return null;
		
		return availableTimes;
	}
	
	public List<DoctorSchedule> findAllDoctorSchedulesById(int id) throws SQLException {

		Connection connection = dbConnection.getConnection();

		final String sqlQuery = "select * from DoctorSchedules where DoctorID=?";
		PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
		preparedStatement.setInt(1, id);
		ResultSet resultSet = preparedStatement.executeQuery();

		while (resultSet.next()) {

			int doctorId = resultSet.getInt("DoctorID");
			DoctorDao doctorDao = new DoctorDao(dbConnection);
			Doctor doctor = doctorDao.findById(doctorId);
			DoctorSchedule doctorSchedule = new DoctorSchedule(resultSet.getInt(1), doctor, resultSet.getDate(3),
					resultSet.getString(4), resultSet.getString(5));
			doctorSchedules.add(doctorSchedule);

		}
		if (doctorSchedules.isEmpty())
			return null;
		return doctorSchedules;
	}
}
