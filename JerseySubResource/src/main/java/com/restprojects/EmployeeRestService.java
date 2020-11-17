package com.restprojects;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import com.model.Department;
import com.model.Employee;

@Path("{pathVariable}")
public class EmployeeRestService {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllEmployees(@PathParam("pathVariable") String path) {
		
		
		List<Employee> list = new ArrayList<Employee>();

		List<Department> list1 = new ArrayList<Department>();

		list1.add(new Department(1, "Developer"));
		list1.add(new Department(2, "Architect"));
		list1.add(new Department(3, "Quality Engineer"));

		list.add(new Employee(1, "Mohaideen", list1));
		list.add(new Employee(2, "Lisa"));
		list.add(new Employee(3, "Ken"));

		return Response.ok().entity(list).build();
	}

	@GET
	@Path("/{empId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEmployeeById(@PathParam("empId") Integer empId) {
		System.out.println("This method");
		if (empId < 0) {
			return Response.noContent().build();
		}
		Employee emp = new Employee();

		emp.setId(empId);
		emp.setName("Mohan");

		return Response.ok().entity(emp).build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addEmployee(Employee e, @Context UriInfo uriInfo) throws URISyntaxException {
		if (e == null) {
			return Response.status(Status.BAD_REQUEST).entity("Please add employee details !!").build();
		}

		if (e.getName() == null) {
			return Response.status(Status.BAD_REQUEST).entity("Please provide the employee name !!").build();
		}
		URI buildURI = uriInfo.getAbsolutePathBuilder().path(String.valueOf(e.getId())).build();

		return Response.created(buildURI).entity(e).build();
	}

	@PUT
	@Path("/{empId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateEmployeeById(@PathParam("empId") Integer empId, Employee e) {
		Employee updatedEmployee = new Employee();

		if (e.getName() == null) {
			return Response.status(Status.NOT_FOUND).entity("Please provide the employee name !!").build();
		}

		updatedEmployee.setId(empId);
		updatedEmployee.setName(e.getName());

		return Response.ok().entity(updatedEmployee).build();
	}

	@DELETE
	@Path("/{empId}")
	public Response deleteEmployeeById(@PathParam("empId") Integer empId) {
		return Response.status(Status.ACCEPTED).entity("Employee deleted successfully !!").build();
	}

	// For calling Sub resource
	@Path("/{empId}/departments")
	public DepartmentService getDepartmentService() {
		return new DepartmentService();
	}

}
