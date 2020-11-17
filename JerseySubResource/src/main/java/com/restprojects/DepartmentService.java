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
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import com.model.Department;

@Path("/")
public class DepartmentService {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Department> getAllEmployees() {
		List<Department> list = new ArrayList<Department>();

		list.add(new Department(1, "Developer"));
		list.add(new Department(2, "Architect"));
		list.add(new Department(3, "Quality Engineer"));

		return list;
	}

	@GET
	@Path("/{messageId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEmployeeById(@PathParam("messageId") Integer messageId) {
		if (messageId < 0) {
			return Response.noContent().build();
		}
		Department emp = new Department();

		emp.setId(messageId);
		emp.setName("Mechanic");

		GenericEntity<Department> entity = new GenericEntity<Department>(emp, Department.class);
		return Response.ok().entity(entity).build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addEmployee(Department e, @Context UriInfo uriInfo) throws URISyntaxException {
		if (e == null) {
			return Response.status(Status.BAD_REQUEST).entity("Please add department details !!").build();
		}

		if (e.getName() == null) {
			return Response.status(Status.BAD_REQUEST).entity("Please provide the department name !!").build();
		}
		URI buildURI = uriInfo.getAbsolutePathBuilder().path(String.valueOf(e.getId())).build();

		return Response.created(buildURI).entity(e).build();
	}

	@PUT
	@Path("/{messageId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateEmployeeById(@PathParam("messageId") Integer messageId, Department e, @Context UriInfo uriInfo) {
		Department msg = new Department();

		if (e.getName() == null) {
			return Response.status(Status.NOT_FOUND).entity("Please provide the department name !!").build();
		}

		msg.setId(messageId);
		msg.setName(e.getName());
		
		
		
		msg.addLink("self", getUriforSelf(uriInfo, msg));
		msg.addLink("rel", getUriforRel(uriInfo, msg));
		
		return Response.ok().entity(msg).build();
	}

	private String getUriforSelf(UriInfo uriInfo, Department msg) {
		String uri = uriInfo.getBaseUriBuilder()
			.path(EmployeeRestService.class)
			.path(EmployeeRestService.class, "getDepartmentService")
			.resolveTemplate("empId", 10000)
			.path(Integer.toString(msg.getId()))
			.build().toString();
		System.out.println(uri);
		return uri;
	}

	
	private String getUriforRel(UriInfo uriInfo, Department msg) {
		String uri = uriInfo.getBaseUriBuilder()
			.path(EmployeeRestService.class)
			.path(Integer.toString(msg.getId()))
			.build().toString();
		System.out.println(uri);
		return uri;
	}
	
	@DELETE
	@Path("/{messageId}")
	public Response deleteEmployeeById(@PathParam("messageId") Integer messageId) {
		return Response.status(Status.ACCEPTED).entity("Department deleted successfully !!").build();
	}

}
