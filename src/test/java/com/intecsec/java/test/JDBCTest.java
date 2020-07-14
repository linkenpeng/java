package com.intecsec.java.test;

import com.intecsec.java.jdbc.DepartmentDao;
import com.intecsec.java.jdbc.Employee;
import com.intecsec.java.jdbc.EmployeeDao;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JDBCTest {
	private ApplicationContext context = null;
	private JdbcTemplate jdbcTemplate = null;
	private EmployeeDao employeeDao = null;
	private DepartmentDao departmentDao = null;
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate = null;
	
	{
		context = new ClassPathXmlApplicationContext("db.xml");
		jdbcTemplate = (JdbcTemplate) context.getBean("jdbcTemplate");
		employeeDao = (EmployeeDao) context.getBean("employeeDao");
		departmentDao = context.getBean(DepartmentDao.class);
		namedParameterJdbcTemplate = context.getBean(NamedParameterJdbcTemplate.class);
	}
	
	@Test
	public void testNamedParameterJdbcTemplate2() {
		String sql = "insert into employees(last_name,email,dept_id)values(:lastName,:email,:deptId)";
		
		Employee employee = new Employee();
		employee.setLastName("WL");
		employee.setEmail("wl@qq.com");
		employee.setDeptId(2);
		
		SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(employee);
		
		namedParameterJdbcTemplate.update(sql, parameterSource);		
	}
	
	@Test
	public void testNamedParameterJdbcTemplate() {
		String sql = "insert into employees(last_name,email,dept_id)values(:ln,:em,:dpid)";
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("ln", "GG");
		paramMap.put("em", "gg@qq.com");
		paramMap.put("dpid", 3);
		
		namedParameterJdbcTemplate.update(sql, paramMap);
	}
	
	@Test
	public void testDemartmentDao() {
		System.out.println(departmentDao.get(1));
	}
	
	@Test
	public void testEmployeeDao() {
		System.out.println(employeeDao.get(1));
	}
	
	
	@Test
	public void testQueryForObject2() {
		String sql = "select count(id) from employees";
		long count = jdbcTemplate.queryForObject(sql, long.class);
		System.out.println(count);
	}
	
	
	@Test
	public void testQueryForlist() {
		String sql = "select id,last_name lastName,email from employees where id>?";
		RowMapper<Employee> rowMapper = new BeanPropertyRowMapper<>(Employee.class);
		List<Employee> employees = jdbcTemplate.query(sql, rowMapper, 1);
		
		System.out.println(employees);
	}
	
	@Test
	public void testBatchUpdate() {
		String sql= "insert into employees(last_name,email,dept_id) values"
				+ "(?,?,?)";
		
		List<Object[]> batch = new ArrayList<>();
		batch.add(new Object[]{"AA", "aa@qq.com", 1});
		batch.add(new Object[]{"BB", "bb@qq.com", 2});
		batch.add(new Object[]{"CC", "cc@qq.com", 2});
		batch.add(new Object[]{"DD", "dd@qq.com", 1});
		batch.add(new Object[]{"EE", "ee@qq.com", 1});
		
		jdbcTemplate.batchUpdate(sql,batch);
	}
	
	@Test
	public void testQueryForObject() {
		String sql = "select id,last_name lastName,email,dept_id as \"department.id\" from employees where id=?";
		RowMapper<Employee> rowMapper = new BeanPropertyRowMapper<>(Employee.class);
//		Employee employee = jdbcTemplate.queryForObject(sql, Employee.class, 1);
		Employee employee = jdbcTemplate.queryForObject(sql, rowMapper, 1);
		
		System.out.println(employee);
	}
	
	
	@Test	
	public void testUpdate() {
		String sql="update employees set last_name=? WHERE id=?";
		jdbcTemplate.update(sql,"wwu", 1);
	}
	
	@Test
	public void testDataSource() throws SQLException {
		DataSource dataSource = (DataSource) context.getBean("dataSource");
		System.out.println(dataSource.getConnection());
	}
}
