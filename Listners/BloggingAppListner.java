package listeners;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;


@WebListener
public class BloggingAppListner implements ServletContextListener {
		Connection con;
    
   

	
    public void contextDestroyed(ServletContextEvent sce)  { 
    	try 
    	{
			con.close();
		} 
    	catch (SQLException e) {
			
			e.printStackTrace();
		}
    	
    }

    public void contextInitialized(ServletContextEvent sce)  { 
    	String driver = sce.getServletContext().getInitParameter("driverclass");
    	String url = sce.getServletContext().getInitParameter("jdbcurl");
    	String user = sce.getServletContext().getInitParameter("user");
    	String pwd= sce.getServletContext().getInitParameter("password");
    	
    		try 
    		{
				Class.forName(driver);
				con=DriverManager.getConnection(url,user,pwd);
				sce.getServletContext().setAttribute("jdbccon", con);
				System.out.println("Connnection is set as Context level Attribute");
			} 
    		catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
    	
    	
    	
    }
	
}
