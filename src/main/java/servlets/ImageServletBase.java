package servlets;

import java.io.IOException;
import java.io.PrintWriter;
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

@WebServlet("/ImagesBaseServlet")
public class ImageServletBase extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String imageId = request.getParameter("id");

		// Rest of your servlet code to retrieve the image using the imageId
		try {

			Class.forName("org.postgresql.Driver");

			String url = "jdbc:postgresql://localhost:5432/windows2";
			String user = "postgres";
			String pass = "admin123";

			Connection conn = DriverManager.getConnection(url, user, pass);

			PreparedStatement ps = conn.prepareStatement("select img from test_images where id = ?");

			Integer imageParsedId = Integer.parseInt(imageId);
			ps.setInt(1, imageParsedId);

			ResultSet rs = ps.executeQuery();

			// Retrieve the image content and convert it to base64
			if (rs.next()) {
				byte[] content = rs.getBytes("img");
				String base64Img = Base64.getEncoder().encodeToString(content);

				// Set the response content type
				response.setContentType("text/html;charset=UTF-8");

				// Create the img tag with the base64 image source
				
				String imgTag = "<img src=\"data:image/png;base64," + base64Img + "\" alt=\"image\">";
				//String imgTag = "data:image/png;base64," + base64Img;
				// Write the img tag to the response
				
				//PrintWriter out = response.getWriter();
				//out.print(imgTag);
				response.setContentType("image/png;charset=UTF-8");
		        response.getWriter().print(imgTag);
			} else {
				System.out.println("ImageServletBase else statement");
				response.sendError(HttpServletResponse.SC_NOT_FOUND);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
