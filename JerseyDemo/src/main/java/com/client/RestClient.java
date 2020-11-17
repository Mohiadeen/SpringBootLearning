package com.client;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.model.Employee;

public class RestClient {

	public static void main(String[] args) {
		Client client = ClientBuilder.newClient();
		WebTarget baseTarget = client.target("http://localhost:8080/JerseyDemo/restapi");
		WebTarget empTarget = baseTarget.path("employee");

		getAllEmployees(empTarget);

		// Request to getEmpById`
		getEmployeeById(empTarget);

		// Request to create Employee
		createEmployee(empTarget);

		// Request to update the employee
		updateEmployee(empTarget);

		// Request to delete Employee by Id
		deleteEmployeeById(empTarget);

	}

	private static void getAllEmployees(WebTarget empTarget) {
		// Request to get all Employees
		Response response = empTarget.request(MediaType.APPLICATION_JSON).get();

		List<Employee> list = response.readEntity(new GenericType<List<Employee>>() {});
		System.out.println(response.getStatus());
		System.out.println(list.toString());

	}

	private static void getEmployeeById(WebTarget empTarget) {
		WebTarget singleEmpTarget = empTarget.path("{employeeId}");
		Response response = singleEmpTarget.resolveTemplate("employeeId", "1").request().get();

		Employee employeeResponse = response.readEntity(Employee.class);

		System.out.println(response.getStatus());
		System.out.println(employeeResponse);
	}

	private static void createEmployee(WebTarget empTarget) {
		Employee emp = new Employee();
		emp.setId(20);
		emp.setName("Steve Waugh");

		Response response = empTarget.request(MediaType.APPLICATION_JSON).post(Entity.json(emp));
		Employee employeeResponse = response.readEntity(Employee.class);
		System.out.println(response.getStatus());
		System.out.println(employeeResponse);
	}

	private static void updateEmployee(WebTarget empTarget) {
		Employee emp = new Employee();
		emp.setId(10);
		emp.setName("Mark Waugh");

		WebTarget singleEmpTarget = empTarget.path("{employeeId}");
		Response response = singleEmpTarget.resolveTemplate("employeeId", emp.getId()).request().put(Entity.json(emp));
		Employee employeeResponse = response.readEntity(Employee.class);
		System.out.println(response.getStatus());
		System.out.println(employeeResponse);
	}

	private static void deleteEmployeeById(WebTarget empTarget) {
		WebTarget singleEmpTarget = empTarget.path("{employeeId}");
		Response response = singleEmpTarget.resolveTemplate("employeeId", "1").request().delete();

		System.out.println(response.getStatus());
		System.out.println(response.readEntity(String.class));
	}

}
