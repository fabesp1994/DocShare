package servlets;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.ResultSetMetaData;
import com.mysql.jdbc.Statement;

import beans.Utente;
import connection.Connessione;
import connection.Indexer;
import connection.Query;

/**
 * Servlet implementation class FirstServlet
 */
@WebServlet("/FirstServlet")
public class FirstServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FirstServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
	Connection conn = null;
	java.sql.Statement stmt = null;
	ResultSet rs = null;
	Utente utente=null;
	HttpSession session = request.getSession();
	int id=0;
	String nick=null, email=null, pass=null;
	try {
	conn = Connessione.getConnection();
	System.out.println("<--- "+conn+" aperta"+" --->");
	stmt = conn.createStatement();
	String nome=request.getParameter("form-username");
	String password=request.getParameter("form-password");
	rs = stmt.executeQuery(Query.cercaUtente(nome, password));
	System.out.println("<--- statement "+rs.toString()+" creato --->");
	java.sql.ResultSetMetaData rsmd = rs.getMetaData();
	int columnsNumber = rsmd.getColumnCount();
	if(!rs.next()) {
		System.out.println("no results");
        session.setAttribute("flag", "no results");
		response.setStatus(response.SC_MOVED_TEMPORARILY);
		response.setHeader("Location", "../login.jsp");
	}else {
		System.out.println("");
	    for (int i = 1; i <= columnsNumber; i++) {
	        if(rsmd.getColumnName(i).equalsIgnoreCase("id")) {
	        	id=Integer.parseInt(rs.getString(i));
	        }else if(rsmd.getColumnName(i).equalsIgnoreCase("nickname")) {
	        	nick = rs.getString(i);
	        }else if(rsmd.getColumnName(i).equalsIgnoreCase("email")) {
	        	email =rs.getString(i);
	        }else {
	        	pass =rs.getString(i);
	        }
	    }
	    utente=new Utente(id,nick,email,pass);
	    
	    session.setAttribute("user", utente);
	    RequestDispatcher rd = request.getRequestDispatcher("RepositoryDocuments");
	    rd.forward(request,response);
	}
	} catch (ClassNotFoundException ex) {
	ex.getMessage();
	} catch (SQLException ex) {
	ex.getMessage();
	} finally {
	try {
	rs.close();
	stmt.close();
	System.out.println("<--- statement "+rs.toString()+" chiuso --->");
	} catch (Exception ex) {
	ex.getMessage();
	}
	}
	}

}
