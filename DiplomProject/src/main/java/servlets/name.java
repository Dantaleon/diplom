package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/name")
public class name extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String age = request.getParameter("age");
		
		StringBuilder error = new StringBuilder();
		System.out.println("errorBegining: " + "\"" + error + "\"");
		if (firstName == null) {
			error.append("firstName is not provided.");
		}
		if (lastName == null) {
			error.append("lastName is not provided");
		}
		if (age == null) {
			error.append("age is not provided");
		}
		
		if (error.length() > 0) {
			System.out.println("errorEnding: " + "\"" + error + "\"");
			request.setAttribute("error", error);
		} else {
			System.out.println(firstName + " " + lastName + " " + age + "years old.");
			request.setAttribute("firstName", firstName);
			request.setAttribute("lastName", lastName);
			request.setAttribute("age", age);
		}
		
		getServletContext().getRequestDispatcher("/name.jsp").forward(request, response);
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
