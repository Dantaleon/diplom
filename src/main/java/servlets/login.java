package servlets;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.dao.UserDAO;
import com.model.entity.User;

@WebServlet("/login")
public class login extends HttpServlet {

	private UserDAO userDao = new UserDAO();
	
    public login() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		StringBuilder error = new StringBuilder();
		StringBuilder noError = new StringBuilder();
		
		if (email == null || email.trim().isEmpty()) {
			error.append("Эл. почта не введена <br />");
		}
		if (password == null || password.trim().isEmpty()) {
			error.append("Пароль не введен <br />");
		}
		if (!(userDao.isUserEmailExist(email)) ) {
			error.append("Эл. почта не зарегистрирована");
		}
		if (error.length() > 0) {
			request.setAttribute("error", error);
			doGet(request, response);
			return;
		}
			
		if(userDao.checkUserOnLogin(email, password)) {
			request.getSession().setAttribute("id", userDao.getUserIdByEmail(email));
			request.getSession().setAttribute("role", "admin");
			System.out.println(request.getSession().getAttribute("id"));
			System.out.println(request.getSession().getAttribute("role"));
			response.sendRedirect("/index.jsp");
		} else {
			error.append("Логин или пароль не верны");
			request.setAttribute("error", error);
			doGet(request, response);
			return;
		}
			
	}

}
