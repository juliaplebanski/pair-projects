package com.techelevator.projects.model.jdbc;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.projects.model.Department;
import com.techelevator.projects.model.DepartmentDAO;

public class JDBCDepartmentDAO implements DepartmentDAO {
	
	private JdbcTemplate jdbcTemplate;

	public JDBCDepartmentDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Department> getAllDepartments() {
		ArrayList<Department> departmentList = new ArrayList<Department>();
		String selectSQL = "SELECT * FROM department";
		SqlRowSet results = jdbcTemplate.queryForRowSet(selectSQL);
		while(results.next()) {
			Department department = mapRowToDepartment(results);
			departmentList.add(department);
			
		}
		return departmentList;
	}

	@Override
	public List<Department> searchDepartmentsByName(String nameSearch) {
		ArrayList<Department>departmentList = new ArrayList<Department>();
		Department department = null;
		String selectSQL = "SELECT * FROM department WHERE name LIKE (?)";
		SqlRowSet results = jdbcTemplate.queryForRowSet(selectSQL, "%" + nameSearch + "%");
		while(results.next()) {
			department = mapRowToDepartment(results);
			departmentList.add(department);
		}
		return departmentList;
	}

	@Override
	public void saveDepartment(Department updatedDepartment) {
		String insertSQL ="UPDATE department SET name = ? WHERE department_id = ?";
		jdbcTemplate.update(insertSQL, updatedDepartment.getId(), updatedDepartment.getName());
	
	}

	@Override
	public Department createDepartment(Department newDepartment) {
		String insertSQL = "UPDATE department SET name = ?";
		jdbcTemplate.update(insertSQL, newDepartment.getId(), newDepartment.getName());
		return null;
	}

	@Override
	public Department getDepartmentById(Long id) {
		Department department = null;
		String sqlGetDepartmentById = "SELECT * FROM department WHERE department_id = ?";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sqlGetDepartmentById, id);
		if(result.next()) {
			department = mapRowToDepartment(result);
		}
		return department;
	}
	
	private Department mapRowToDepartment(SqlRowSet results) {
		Department department = new Department();
		department.setId((long) results.getInt("department_id"));
		department.setName(results.getString("name"));
		return department;
		
		
	}
	

}
