package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.dao.UserDAO;
import com.model.entity.User;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {
	
	private UserDAO userDao = new UserDAO();
	
	public RegistrationServlet() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		getServletContext().getRequestDispatcher("/registration.jsp").forward(request, response);
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String nickname = request.getParameter("nickname");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirmPassword");
		
		StringBuilder error = new StringBuilder();
		StringBuilder noError = new StringBuilder();
		
		if (nickname == null || nickname.trim().isEmpty()) {
			error.append("Имя пользователя не введено <br />");
		}
		if (email == null || email.trim().isEmpty()) {
			error.append("Эл. почта не введена <br />");
		}
		if (password == null || password.trim().isEmpty()) {
			error.append("Пароль не введен <br />");
		}
		if (confirmPassword == null || confirmPassword.trim().isEmpty()) {
			error.append("Подтверждание пароля не введено <br />");
		}
		if (!password.equals(confirmPassword)) {
			error.append("Пароль и его подтверждение не совпадают <br />");
		}
		if (userDao.isUserEmailExist(email)) {
			error.append("Эл. почта уже зарегистрирована");
		}
		if (error.length() > 0) {
			request.setAttribute("error", error);
		
		} else {
			User user = new User(nickname, email, password);
			
			try {
				userDao.registerUser(user);
			}catch(Exception e) {
				e.printStackTrace();
			}
			noError.append("Аккаунт успешно зарегистрирован");
			request.setAttribute("noError", noError);
			
		}
		doGet(request, response);
	}
}
