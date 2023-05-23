package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class register extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("GET request fired on /register");
		
		getServletContext().getRequestDispatcher("/register.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("POST request fired on /register");
		
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String age = request.getParameter("age");
		String nickname = request.getParameter("nickname");
		String email = request.getParameter("email");
		String telephone = request.getParameter("telephone");
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirmPassword");
		
		StringBuilder error = new StringBuilder();
		
		if (firstName == null || firstName.trim().isEmpty()) {
			error.append("firstName is not provided" + "<br>");
		} 
		if (lastName == null || lastName.trim().isEmpty()) {
			error.append("lastName is not provided" + "<br>");
		}
		if (age == null || age.trim().isEmpty()) {
			error.append("age is not provided" + "<br>");
		}
		if (nickname == null || nickname.trim().isEmpty()) {
			error.append("nickname is not provided" + "<br>");
		}
		if (email == null || email.trim().isEmpty()) {
			error.append("email is not provided" + "<br>");
		}
		if (telephone == null || telephone.trim().isEmpty()) {
			error.append("telephone is not provided" + "<br>");
		}
		if (password == null || password.trim().isEmpty()) {
			error.append("password is not provided" + "<br>");
		}
		if (confirmPassword == null || confirmPassword.trim().isEmpty()) {
			error.append("confirmPassword is not provided" + "<br>");
		}
		if (!password.equals(confirmPassword)) {
			error.append("passwords don't match" + "<br>");
		}
		
		if (error.length() > 0) {
			request.setAttribute("error", error);
			doGet(request, response);
		} else {
			System.out.println(firstName + " " + lastName + " " + age + " " + nickname + " " +
					 email +  " " + telephone + " " + password + " " + confirmPassword);
		}
	}

}
