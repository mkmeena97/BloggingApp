package controllers;

import java.awt.Taskbar.State;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Topic;


@WebServlet("/getTopics")
public class GetTopicServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Connection con; 
	
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		con=(Connection)config.getServletContext().getAttribute("jdbccon");
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Statement st = null;
		ResultSet rs = null;
		
		try 
		{
			st=con.createStatement();
			rs=st.executeQuery("select * from topic");
			List<Topic>topics = new ArrayList<>();
			while(rs.next())
			{
				System.out.println(rs.getInt(1));
				Topic t = new Topic(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4));
				topics.add(t);
			}
			getServletContext().setAttribute("topics", topics);
			
			RequestDispatcher rd = request.getRequestDispatcher("/Topics.jsp");
			rd.forward(request, response);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally
		{
			try 
			{
				rs.close();
				st.close();
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
