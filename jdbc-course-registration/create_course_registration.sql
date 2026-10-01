CREATE DATABASE IF NOT EXISTS course_registration_db;
USE course_registration_db;

CREATE TABLE IF NOT EXISTS CourseRegistration (
    StudentID VARCHAR(30) NOT NULL,
    StudentName VARCHAR(150) NOT NULL,
    CourseCode VARCHAR(30) NOT NULL,
    CourseName VARCHAR(150) NOT NULL,
    Semester VARCHAR(30) NOT NULL,
    PRIMARY KEY (StudentID, CourseCode, Semester)
);
