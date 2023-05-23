package servlets;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "HelloServlet", urlPatterns = {"/hello/*", "/hello-world"})
public class HelloServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("requested GET on /hello");
		
		String role = request.getSession().getAttribute("role").toString();
		
		if (role != null) 
		{
			System.out.println(" has been entered with role " + role);
			response.setContentType("text/plain");
			response.getWriter().write(" has been entered with role " + role);
		} else {
			System.out.println("unknown user has been entered");
			response.setContentType("text/plain");
			response.getWriter().write("ERROR");
		}
		
		
		
		
		//getServletContext().getRequestDispatcher("/cool.html").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		
	}

}
