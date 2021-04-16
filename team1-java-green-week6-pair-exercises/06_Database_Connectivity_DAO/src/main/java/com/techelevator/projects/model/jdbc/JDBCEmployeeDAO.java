package com.techelevator.projects.model.jdbc;

import java.util.ArrayList;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.projects.model.Employee;
import com.techelevator.projects.model.EmployeeDAO;

public class JDBCEmployeeDAO implements EmployeeDAO {

	private JdbcTemplate jdbcTemplate;

	public JDBCEmployeeDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public List<Employee> getAllEmployees() {
		ArrayList<Employee> employeeList = new ArrayList<Employee>();
		String selectSQL = "SELECT * FROM employee";
		SqlRowSet results = jdbcTemplate.queryForRowSet(selectSQL);
		while(results.next()) {
			Employee employee = mapRowToEmployee(results);
			employeeList.add(employee);
			
		}
		return employeeList;
		
	}
	
	@Override
	public List<Employee> searchEmployeesByName(String firstNameSearch, String lastNameSearch) {
		ArrayList<Employee> searchEmployees = new ArrayList<Employee>();
		String employeeNameSQL = "SELECT firstName AND lastName FROM employees WHERE firstName LIKE ? AND lastName LIKE ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(employeeNameSQL, "%" + firstNameSearch + "%", "%" + lastNameSearch + "%");
		while(results.next()) {
			Employee employee = mapRowToEmployee(results);
			searchEmployees.add(employee);
			
		}
		return searchEmployees;
	}

	@Override
	public List<Employee> getEmployeesByDepartmentId(long id) {
		ArrayList<Employee> employeesByDepartmentId = new ArrayList<Employee>();
		String employeeDepartmentIdSQL = "SELECT * FROM employees WHERE department_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(employeeDepartmentIdSQL);
		while(results.next()) {
			Employee employee = mapRowToEmployee(results);
			employeesByDepartmentId.add(employee);
		}
		
		return employeesByDepartmentId;
	}

	@Override
	public List<Employee> getEmployeesWithoutProjects() {
		ArrayList<Employee> employeesWithoutProjects = new ArrayList<Employee>();
		String employeesWithoutProjectsSQL = "SELECT * FROM employee AS e \n" + 
				"LEFT JOIN project_employee pe ON e.employee_id= pe.employee_id\n" + 
				"WHERE project_id IS null";
		SqlRowSet results = jdbcTemplate.queryForRowSet(employeesWithoutProjectsSQL);
		while(results.next()) {
			Employee employee = mapRowToEmployee(results);
			employeesWithoutProjects.add(employee);
		}
		
		return employeesWithoutProjects;
	}

	@Override

	public List<Employee> getEmployeesByProjectId(Long projectId) {
		ArrayList<Employee> employeesOnProject = new ArrayList<Employee>();
		String employeesOnTheProject = "SELECT * FROM employee AS e \n" + 
				"JOIN project_employee pe ON e.employee_id= pe.employee_id\n" + 
				"WHERE project_id IS NOT NULL";
		SqlRowSet results = jdbcTemplate.queryForRowSet(employeesOnTheProject);
		while(results.next()) {
			Employee employee = mapRowToEmployee(results);
			employeesOnProject.add(employee);
		}
		
		return employeesOnProject;
	}

	@Override
	public void changeEmployeeDepartment(Long employeeId, Long departmentId) {
		String insertSQL ="UPDATE employee SET department_id = ? WHERE employee_id = ?";
		jdbcTemplate.update(insertSQL, employeeId, departmentId);
	}

	private Employee mapRowToEmployee(SqlRowSet results) {
		Employee employee = new Employee();
		employee.setFirstName(results.getString("firstName"));
		employee.setLastName(results.getString("lastName"));
		employee.setDepartmentId((long)(results.getInt("department_id")));
		employee.setId(results.getLong("employee_id"));
		return employee;
		
		
	}
}
