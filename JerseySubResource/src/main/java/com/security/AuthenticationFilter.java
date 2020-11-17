package com.security;

import java.util.List;
import java.util.StringTokenizer;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import org.glassfish.jersey.internal.util.Base64;

import com.model.Employee;

/**
 * This filter verify the access permissions for a user based on UserName and
 * passowrd provided in request
 */
@Provider
public class AuthenticationFilter implements javax.ws.rs.container.ContainerRequestFilter {

	@Context
	private ResourceInfo resourceInfo;

	private static final String AUTHORIZATION_PROPERTY = "Authorization";
	private static final String AUTHENTICATION_SCHEME = "Basic ";
	private static final Response ACCESS_DENIED = Response.status(Response.Status.UNAUTHORIZED)
			.entity("You cannot access this resource one").build();
	private static final Response INVALID_CREDENTIALS = Response.status(Response.Status.UNAUTHORIZED).entity("invalid user")
			.build();

	// private static final Response INVALID_CREDENTIALS =
	// Response.ok(employee()).type(MediaType.APPLICATION_JSON).build();

	@Override
	public void filter(ContainerRequestContext requestContext) {

		// Fetch authorization header
		final List<String> authorization = requestContext.getHeaders().get(AUTHORIZATION_PROPERTY);
		System.out.println(requestContext.getHeaders().toString());

		// If no authorization information present; block access
		if (authorization == null || authorization.isEmpty()) {
			requestContext.abortWith(ACCESS_DENIED);
			return;
		}

		// Get encoded UserName and password
		final String encodedUserPassword = authorization.get(0).replaceFirst(AUTHENTICATION_SCHEME, "");

		// Decode UserName and password
		String usernameAndPassword = new String(Base64.decode(encodedUserPassword.getBytes()));

		// Split UserName and password tokens
		final StringTokenizer tokenizer = new StringTokenizer(usernameAndPassword, ":");
		final String userName = tokenizer.nextToken();
		final String password = tokenizer.nextToken();

		// Verifying Username and password
		System.out.println(userName);
		System.out.println(password);

		// Is user valid?
		if (!isUserAllowed(userName, password)) {
			requestContext.abortWith(INVALID_CREDENTIALS);
			return;
		}

	}

	private boolean isUserAllowed(final String userName, final String password) {
		boolean isAllowed = false;

		if (userName.equals("user") && password.equals("password")) {
			isAllowed = true;
		}
		return isAllowed;
	}

	public static Employee employee() {
		Employee emp = new Employee();
		emp.setId(1);
		emp.setName("test");
		return emp;
	}
}