## Student Management System

The "Student Management System" is a software application designed to facilitate user registration, login, and role-based management. It also provides tools for managing student information, course data, a grading system, and search/sort functionality. Additionally, it ensures data persistence by allowing users to save and load data to/from a text file.

### Objectives

- **Apply Programming Fundamentals:**
    - Utilize variables and operators to manage data effectively.
    - Implement functions and control flow for structured code.

- **Utilize OOP Concepts:**
    - Create and work with classes and objects to model real-world entities.
    - Apply encapsulation to protect and control access to data.
    - Leverage inheritance and polymorphism for code reuse and extensibility.

- **Incorporate Java-Specific Features:**
    - Implement methods to encapsulate behavior.
    - Employ if-else statements and switch cases for conditional logic.
    - Utilize loops for repetitive tasks.
    - Work with arrays, strings, collections, streams, enums, and generics to handle data efficiently.
    - Implement error handling for robust code.

- **Introduce DevOps:**
    - Gain a foundational understanding of basic Git and GitHub concepts for version control and collaboration.

### Features

1. **User Management:**
    - **User Registration:** Users can register with the system and specify their roles as Admin, Teacher, or Student.
    - **User Login:** Registered users can securely log into the system with their credentials.
    - **Role-Based Management:** The system distinguishes between Admin, Teacher, and Student roles, each with specific privileges and access rights.

2. **Student Management:**
    - **Create, Read, Update, Delete (CRUD):** The system offers CRUD operations for managing student data, allowing users to add, view, update, and delete student information.
    - **Student Data:** Student records typically include details such as name, student ID, and enrolled courses.

3. **Course Management:**
    - **Create, Read, Update, Delete (CRUD):** Users can perform CRUD operations for managing course data, which includes course name, course ID, and enrolled students.
    - **Course Data:** Course records store information about the course itself and the students who are enrolled.

4. **Grading System:**
    - **Assign and Modify Grades:** The system includes a grading system where teachers or admins can assign and modify grades for students enrolled in specific courses.
    - **Grade Management:** This feature enables the tracking of students' academic performance, allowing users to access information regarding students who may have lower academic achievements.

5. **Search and Sort Functionality:**
    - **Search:** Users can search for students or courses based on various parameters, such as name or ID.
    - **Sort:** Students or courses can be sorted by name, making it easy to organize and locate information.

6. **Data Persistence:**
    - **Save and Load Data:** The system allows users to save data to a text file for backup or future reference. This data can also be loaded back into the system for continuity and data integrity.

### Dependencies

The project is built with Maven.
Test dependency: the project uses the junit-jupiter-engine version 5.2.0.

### Install

To get the source, clone the git repository.
```sh
git clone https://github.com/ElenaBezro/StudentManagementSystem
```
### Building

The Maven setup is fairly standard, the main tasks are:

- mvn clean: Clean the project by removing the target directory and any generated build artifacts.
- mvn compile: Compile the source code in your project.
- mvn test: Execute tests for your project.
- mvn package: Package your application into a JAR, WAR, or other desired artifact.
- mvn install: Install the project's artifacts in your local Maven repository.
- mvn deploy: Deploy your project's artifacts to a remote Maven repository.
- mvn clean install: Clean the project and then install it.
