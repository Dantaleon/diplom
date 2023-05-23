package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Base64;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/viewImages")
public class viewImages extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setCharacterEncoding("UTF-8");
		
		System.out.println("get on viewImages fired");
		
		PrintWriter out = response.getWriter();
		
		try {
			
			Class.forName("org.postgresql.Driver");
			
			String url = "jdbc:postgresql://localhost:5432/windows2";
			String user = "postgres";
			String pass = "admin123";
			
			Connection conn = DriverManager.getConnection(url, user, pass);
			
			PreparedStatement ps = conn.prepareStatement("select * from test_images");
			
			ResultSet rs = ps.executeQuery(); 
			
			HashMap<Integer, byte[]> map = new HashMap<Integer, byte[]>();
			
			ArrayList<Integer> array = new ArrayList<Integer>();
			
			if (rs != null) {
				
				while(rs.next()) 
				{
					int id = rs.getInt(1);
					array.add(id);
					System.out.println(id);
					byte[] img = rs.getBytes(2);
					System.out.println(img);
					map.put(id, img);
				}
				
				ps.close();
				conn.close();
				request.setAttribute("resultMap", map);
				request.setAttribute("array", array);
			} else {
				System.out.println("rs is null");
			}
			
		}catch(Exception e) {
			out.println(e);
		}finally {
			request.getRequestDispatcher("show-images.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
