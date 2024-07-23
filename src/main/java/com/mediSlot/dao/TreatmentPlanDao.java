package com.mediSlot.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mediSlot.model.Appointment;
import com.mediSlot.model.TreatmentPlan;
import com.mediSlot.util.DBConnection;

public class TreatmentPlanDao implements IDao<TreatmentPlan> {

	private DBConnection dbConnection;
	private List<TreatmentPlan> treatmentPlans = new ArrayList<>();

	public TreatmentPlanDao() {
		super();
	}

	public TreatmentPlanDao(DBConnection dbConnection) {
		super();
		this.dbConnection = dbConnection;
	}

	@Override
	public TreatmentPlan create(TreatmentPlan treatmentPlan) {

		try {
			Connection connection = dbConnection.getConnection();
			String sqlQuery = "INSERT INTO TreatmentPlans (AppointmentID, Diagnosis, Treatment, Prescription, NextAppointmentDate, NextAppointmentTime) VALUES (?,?, ?, ?, ?, ?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
			preparedStatement.setInt(1, treatmentPlan.getAppointment().getAppointmentID());
			preparedStatement.setString(2, treatmentPlan.getDiagnosis());
			preparedStatement.setString(3, treatmentPlan.getTreatment());
			preparedStatement.setString(4, treatmentPlan.getPrescription());
			preparedStatement.setDate(5, (Date) treatmentPlan.getNextAppointmentDate());
			preparedStatement.setString(6, treatmentPlan.getNextAppointmentTime());
			if (preparedStatement.executeUpdate() < 0) {
				treatmentPlan = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return treatmentPlan;
	}

	@Override
	public boolean update(int id, TreatmentPlan t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<TreatmentPlan> findAll() throws Exception {
		Connection connection = dbConnection.getConnection();
		Statement selectStatement = connection.createStatement();

		final String sqlQuery = "select * from TreatmentPlans";
		ResultSet resultSet = selectStatement.executeQuery(sqlQuery);
		while (resultSet.next()) {
			int appointmentID = resultSet.getInt("AppointmentID");
			AppointmentDao appointmentDao = new AppointmentDao(dbConnection);
			Appointment appointment = appointmentDao.findById(appointmentID);
			TreatmentPlan treatmentPlan = new TreatmentPlan(resultSet.getInt(1), appointment, resultSet.getString(3),
					resultSet.getString(4), resultSet.getString(5), resultSet.getDate(6), resultSet.getString(7));
			treatmentPlans.add(treatmentPlan);
		}

		if (treatmentPlans.isEmpty())
			return null;

		return treatmentPlans;
	}

	public TreatmentPlan findById(int id) {
		TreatmentPlan treatmentPlan = null;
		try {
			Connection connection = dbConnection.getConnection();
			String sqlQuery = "select * from TreatmentPlans where PlanID=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {

				int appointmentID = resultSet.getInt("AppointmentID");
				AppointmentDao appointmentDao = new AppointmentDao(dbConnection);
				Appointment appointment = appointmentDao.findById(appointmentID);
				treatmentPlan = new TreatmentPlan(resultSet.getInt(1), appointment, resultSet.getString(3),
						resultSet.getString(4), resultSet.getString(5), resultSet.getDate(6), resultSet.getString(7));
				treatmentPlans.add(treatmentPlan);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return treatmentPlan;
	}

	public TreatmentPlan findByAppointmentId(int id) {
		TreatmentPlan treatmentPlan = null;
		try {
			Connection connection = dbConnection.getConnection();
			String sqlQuery = "select * from TreatmentPlans where AppointmentID=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {

				int appointmentID = resultSet.getInt("AppointmentID");
				AppointmentDao appointmentDao = new AppointmentDao(dbConnection);
				Appointment appointment = appointmentDao.findById(appointmentID);
				treatmentPlan = new TreatmentPlan(resultSet.getInt(1), appointment, resultSet.getString(3),
						resultSet.getString(4), resultSet.getString(5), resultSet.getDate(6), resultSet.getString(7));
				treatmentPlans.add(treatmentPlan);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return treatmentPlan;
	}

	public List<TreatmentPlan> findAllRecordByPatientId(int id) throws Exception {

		Connection connection = dbConnection.getConnection();

		final String sqlQuery = "select * from TreatmentPlans TP join appointments a on tp.appointmentID=a.appointmentID join Patients p on a.patientID=p.patientID where p.patientID=?";
		PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
		preparedStatement.setInt(1, id);

		ResultSet resultSet = preparedStatement.executeQuery();

		while (resultSet.next()) {

			int appointmentID = resultSet.getInt("AppointmentID");
			AppointmentDao appointmentDao = new AppointmentDao(dbConnection);
			Appointment appointment = appointmentDao.findById(appointmentID);

			TreatmentPlan treatmentPlan = new TreatmentPlan(resultSet.getInt(1), appointment, resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getDate(6), resultSet.getString(7));
			treatmentPlans.add(treatmentPlan);

		}
		if (treatmentPlans.isEmpty())
			return null;

		return treatmentPlans;
	}

	public List<TreatmentPlan> findAllRecordByDocotrId(int id) throws Exception {

		Connection connection = dbConnection.getConnection();

		final String sqlQuery ="select * from TreatmentPlans TP join appointments a on tp.appointmentID=a.appointmentID join doctorschedules ds on a.scheduleid=ds.scheduleid where ds.doctorid=?";
		PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
		preparedStatement.setInt(1, id);

		ResultSet resultSet = preparedStatement.executeQuery();

		while (resultSet.next()) {

			int appointmentID = resultSet.getInt("AppointmentID");
			AppointmentDao appointmentDao = new AppointmentDao(dbConnection);
			Appointment appointment = appointmentDao.findById(appointmentID);

			TreatmentPlan treatmentPlan = new TreatmentPlan(resultSet.getInt(1), appointment, resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getDate(6), resultSet.getString(7));
			treatmentPlans.add(treatmentPlan);

		}
		if (treatmentPlans.isEmpty())
			return null;

		return treatmentPlans;
	}
}
