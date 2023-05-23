package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ParamServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String contextParam = getServletContext().getInitParameter("sheesh");
		String servletParam = getServletConfig().getInitParameter("copyrightYear");
		System.out.println("contextParam: " + contextParam);
		if (contextParam != null) {
			System.out.println("contextParam: " + contextParam);
		}
		
		if (servletParam != null) {
			System.out.println("servletParam: " + servletParam);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
