package test;
import static org.junit.Assert.assertEquals;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.junit.*;
import org.springframework.mock.*;
import org.springframework.mock.web.MockHttpServletRequest;

import com.mysql.jdbc.Connection;

import connection.*;
import servlets.Register;

public class MyTests {
	
	@Test
	public void testaConnessione() {
		try {
			Connection c=null;
			c=Connessione.getConnection();
			if(c!=null) {
			assertEquals("Errore di connessione!",Connection.class, c.getClass());
			}else {
			assertEquals("Errore di connessione!",Connection.class, c);	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testRegister() {
		HttpServletRequest request =(HttpServletRequest)new HttpServletRequestImpl();
		System.out.println("--->"+request.getClass());
		request.setAttribute("form-first-name", "provaTest");
		request.setAttribute("form-email", "test@test.it");
		request.setAttribute("password1", "88888888");
		request.setAttribute("password2", "88888888");
		Register register=new Register();
		register.faccioIlPost(request, null);
	}
	

}
