# Doctor Appointment Booking System

## About
The Doctor Appointment Booking System is a Java Web Development project designed to streamline the process of booking appointments with doctors. Users can register or log in, view available doctors, select an appointment slot, and confirm their booking. Administrators can manage doctor information, appointment slots, and view booking history.



### Features
This website is built for the following purposes:

- Booking doctor appointments online.
- Maintaining appointment booking history.
- Adding and managing doctors and their availability.
- User-friendly interface.
- Implementation of Http Servlets in Java.

### Users
#### Doctor Access:
- Add New Doctors.
- View Available Doctors.
- Manage Doctor Schedules.
- View Booking History.

#### patient Access:
- Create New Account or Register.
- Login.
- View Available Doctors.
- Book Appointments.
- Confirm Booking.

### Technologies Used
#### Front-End Development:
- HTML5
- CSS
- JavaScript

#### Back-End Development:
- Java (JDK 8+)
- JDBC
- Servlet
- JSP
#### Database:
- Oracle SQL

### Software and Tools Required
- Eclipse (Enterprise Edition)
- Java (JDK 8+)
- Tomcat v8.0+
- Apache Maven
- orcle sql developer tool

### orcle sql Database Queries - 
 
--========================================================================================Patients Table====================================
CREATE TABLE Patients (
    patientID INT GENERATED ALWAYS AS IDENTITY CONSTRAINT pk_patient PRIMARY KEY, -- Unique identifier for each patient
    FullName VARCHAR2(60) NOT NULL, -- Name of the patient
    Patient_Email Varchar2(50) UNIQUE NOT NULL,
    Patient_Phone_no VARCHAR2(15) UNIQUE NOT NULL, -- Contact phone number of the patient
    Patient_Password VARCHAR2(255) NOT NULL
);

commit ; 

select * from patients;  




--=========================================================Doctors Table=============================================================================================

 CREATE TABLE Doctors (
    DoctorID INT GENERATED ALWAYS AS IDENTITY CONSTRAINT pk_doctor PRIMARY KEY, -- Unique identifier for each doctor
    Doctor_Name VARCHAR2(50) NOT NULL, -- Name of the doctor
    Specialization VARCHAR2(30) NOT NULL, -- Specialization of the doctor
    Doctor_Email VARCHAR2(30) UNIQUE NOT NULL,
    Doctor_Phone_no VARCHAR2(15) UNIQUE NOT NULL, -- Contact phone number of the doctor
    Doctor_Password VARCHAR2(255) NOT NULL
);

commit; 

select * from doctors ; 


 --===============================================================================DoctorSchedules---====================================================================
 
 
 
 CREATE TABLE DoctorSchedules (
    ScheduleID INT GENERATED ALWAYS AS IDENTITY CONSTRAINT pk_DoctorSchedule PRIMARY KEY,
    DoctorID INT NOT NULL,
    AvailableDate DATE NOT NULL,
    AvailableTime VARCHAR2(10) NOT NULL,
    BlockedTime VARCHAR2(15) NOT NULL,
    CONSTRAINT FK_DoctorSchedule_Doctor FOREIGN KEY (DoctorID) REFERENCES Doctors(DoctorID)
);

commit;

select * from doctorschedules;



--=============================================================================Appointments===========================================================================


 CREATE TABLE Appointments (
    AppointmentID INT GENERATED ALWAYS AS IDENTITY CONSTRAINT pk_appointements PRIMARY KEY, -- Unique identifier for each appointment
    PatientID INT NOT NULL, -- Foreign key referencing patientID in Patients table
    ScheduleID INT NOT NULL,
    Gender VARCHAR2(10) NOT NULL, -- Gender of the patient
    Age NUMBER(3) NOT NULL, --Age of the patient
    BloodGroup VARCHAR2(5) NOT NULL, -- Blood group of the patient
    Address VARCHAR2(255) NOT NULL, -- Address of the patient
    AppointmentDate date NOT NULL,
    AppointmentTime VARCHAR(10) NOT NULL, -- Preferred date and time for the appointment
    AppointmentReason VARCHAR2(255) NOT NULL, -- Reason for the appointment
    CONSTRAINT FK_Appointments_Patients FOREIGN KEY (PatientID) REFERENCES Patients(PatientID),
    CONSTRAINT FK_Appointments_Schedule FOREIGN KEY (ScheduleID) REFERENCES DoctorSchedules(ScheduleID)
);

commit;

select * from Appointments;


--======================================================TREATEMENTPLANS==================================================================================================



CREATE TABLE TreatmentPlans (
    PlanID INT GENERATED ALWAYS AS IDENTITY CONSTRAINT pk_TreatmentPlans PRIMARY KEY, -- Unique identifier for each treatment plan
    AppointmentID INT NOT NULL, -- Foreign key referencing AppointmentID in Appointments table
    Diagnosis VARCHAR2(255) NOT NULL, -- Diagnosis made by the doctor
    Treatment VARCHAR2(255) NOT NULL, -- Treatment plan prescribed by the doctor
    Prescription VARCHAR2(255) NOT NULL, -- Medication prescribed as part of the treatment plan
    NextAppointmentDate Date NOT NULL, -- Date and time of the next appointment scheduled for the patient
    NextAppointmentTime VARCHAR2(20) NOT NULL,
    CONSTRAINT FK_TreatmentPlans_Appointments FOREIGN KEY (AppointmentID) REFERENCES Appointments(AppointmentID)
);

select * from TreatmentPlans;

commit ; 

--


============================================================================================================================================================================

# Screenshots

## Front Page
![Front Page](https://github.com/codershivi123/my-medislot-website/blob/main/front_page.jpeg)

## Patient Login
![Patient Login](https://github.com/codershivi123/my-medislot-website/blob/main/patient_login.jpeg)

## Doctor Module
![Doctor Module](https://github.com/codershivi123/my-medislot-website/blob/main/doctor_module.jpeg)

## Doctor Login
![Doctor Login](https://github.com/codershivi123/my-medislot-website/blob/main/doctor_login.jpeg)

## Appointment Form
![Appointment Form](https://github.com/codershivi123/my-medislot-website/blob/main/appointment_form.jpeg)

## Add Prescription
![Add Prescription](https://github.com/codershivi123/my-medislot-website/blob/main/add_prescription.jpeg)

