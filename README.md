# Doctor Appointment Booking System

## About
The Doctor Appointment Booking System is a Java Web Development project designed to streamline the process of booking appointments with doctors. Users can register or log in, view available doctors, select an appointment slot, and confirm their booking. Administrators can manage doctor information, appointment slots, and view booking history.

![DoctorAppointmentBooking](https://your-image-url)

### Features
This website is built for the following purposes:

- Booking doctor appointments online.
- Maintaining appointment booking history.
- Adding and managing doctors and their availability.
- User-friendly interface.
- Implementation of Http Servlets in Java.

### Users
#### Admin Access:
- Add New Doctors.
- View Available Doctors.
- Manage Doctor Schedules.
- View Booking History.

#### User Access:
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

### Database Query
Setting up Oracle SQL Developer to create and manage a project database involves several steps. Here's a detailed guide to help you through the process:

Step 1: Download and Install Oracle SQL Developer
Download Oracle SQL Developer:

Visit the Oracle SQL Developer download page.
Choose the appropriate version for your operating system (Windows, macOS, or Linux).
Download the zip file or the installer.
Install Oracle SQL Developer:

If you downloaded the zip file, extract it to a desired location.
If you downloaded the installer, run it and follow the on-screen instructions.
Step 2: Install Oracle Database (if not already installed)
Download Oracle Database:

Visit the Oracle Database download page.
Choose the version you need and download it.
Install Oracle Database:

Run the installer and follow the on-screen instructions.
Set up the necessary configurations like creating a new database, setting the password for the SYS and SYSTEM users, etc.
Step 3: Start Oracle Database
Start Oracle Services:
Ensure that the Oracle database services are running. You can check this through the Services management console on Windows or through command line tools on Linux and macOS.
Step 4: Configure Oracle SQL Developer
Open Oracle SQL Developer:

Navigate to the directory where you extracted SQL Developer and run the sqldeveloper executable.
Create a New Connection:

Click on the green + icon or navigate to File > New > Database Connection.
Fill in the connection details:
Connection Name: Choose a name for your connection.
Username: Enter your database username (e.g., SYSTEM or a user you created).
Password: Enter your database password.
Hostname: Enter localhost if the database is on your local machine, or the IP address if it’s on a remote server.
Port: Default is 1521.
SID/Service Name: Enter the SID or Service Name of your database (default is usually orcl).
Test the Connection:

Click on the Test button to check if the connection details are correct. If the test is successful, click Connect.
Step 5: Create and Manage Your Project Database
Create a New Schema:

Open the connection you created.
Navigate to the SQL Worksheet by clicking on the worksheet icon.

   

 
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
======================================================================================================================================================================

commit ; 

--============================================================================================================================================================================

