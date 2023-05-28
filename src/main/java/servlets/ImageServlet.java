package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Base64;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/images/*")
public class ImageServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String imageName = request.getPathInfo().substring(1); // shoud return foo.png
		System.out.println(imageName);
		
		try {
			
			Class.forName("org.postgresql.Driver");
			
			String url = "jdbc:postgresql://localhost:5432/windows2";
			String user = "postgres";
			String pass = "admin123";
			
			Connection conn = DriverManager.getConnection(url, user, pass);
			
			PreparedStatement ps = conn.prepareStatement("select img from test_images where id = ?");
			
			String imageNameSub = imageName.replaceAll(".png", "");
			Integer imageNameSubInt = Integer.parseInt(imageNameSub);
			System.out.println(imageNameSub);
			
			ps.setInt(1, imageNameSubInt);
			
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				System.out.println("hasNext");
				byte[] content = rs.getBytes("img");
				
				response.setContentType(getServletContext().getMimeType(imageName));
				
				response.setContentLength(content.length);
				
				response.getOutputStream().write(content);
				
			} else {
				response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
