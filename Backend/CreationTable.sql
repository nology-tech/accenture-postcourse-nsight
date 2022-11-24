CREATE TABLE students (
id INT NOT NULL,
FOREIGN KEY (course_id) REFERENCES courses(id),
consumer_or_consultant VARCHAR(255) NOT NULL,
FOREIGN KEY (employer) REFERENCES employers(id) ,
employed BOOLEAN NOT NULL,
date_of_birth DATE NOT NULL,
email VARCHAR(255),
name VARCHAR(255) NOT NULL,
PRIMARY KEY(id)
);

CREATE TABLE employers (
id INT NOT NULL,
employer_name VARCHAR(255) NOT NULL,
PRIMARY KEY(id)
);

CREATE TABLE employer_student_list (
FOREIGN KEY (employer_id) REFERENCES employers(id),
FOREIGN KEY (student_id) REFERENCES students(id) PRIMARY KEY
);

CREATE TABLE courses_student_list (
FOREIGN KEY (course_id) REFERENCES courses(id),
FOREIGN KEY (student_id) REFERENCES students(id) PRIMARY KEY
);

CREATE TABLE instructors_course_list (
FOREIGN KEY (instructors_id) REFERENCES instructors(id),
FOREIGN KEY (course_id) REFERENCES courses(id) PRIMARY KEY
);

CREATE TABLE instructors (
FOREIGN KEY
);

CREATE TABLE courses (

);