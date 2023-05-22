package servlets;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;


import java.sql.*;
import java.util.HashMap;


@WebServlet("/uploadImage")
@MultipartConfig
public class uploadImage extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("uploadImage.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setCharacterEncoding("UTF-8");
		
		PrintWriter out = response.getWriter();
		
		System.out.println("post on uploadImage fired");
		
		Part part = request.getPart("input-images");
		
		if (part != null) {
			try {
				
				Class.forName("org.postgresql.Driver");
				
				String url = "jdbc:postgresql://localhost:5432/windows2";
				String user = "postgres";
				String pass = "admin123";
				
				Connection conn = DriverManager.getConnection(url, user, pass);
				
				PreparedStatement ps = conn.prepareStatement("insert into test_images(img) values(?)");
				
				InputStream is = part.getInputStream();
				
				ps.setBinaryStream(1, is, part.getSize());
				
				ps.executeUpdate();
				
				ps.close();
				conn.close();
				System.out.println("uploading done");
				
				request.setAttribute("isUpdload", "Загружено");
				
				doGet(request, response);
				
			}catch(Exception e) {
				request.setAttribute("isUpdload", "Не удалось загрузить картинку");
				out.println(e);
			}
		} else {
			request.setAttribute("isUpdload", "Не удалось загрузить картинку");
		}
		
	}

}
