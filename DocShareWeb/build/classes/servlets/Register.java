package servlets;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.jdbc.Connection;

import beans.Utente;
import connection.Connessione;
import connection.Indexer;
import connection.Query;

/**
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
		java.sql.Statement stmt = null;
		int controllo=0;
		ResultSet rs = null;
		Utente utente=null;
		boolean caricato=false;
		HttpSession session = request.getSession();
		try {
			conn = Connessione.getConnection();
			conn.setAutoCommit(true);
			System.out.println("<--- "+conn+" aperta"+" --->");
			stmt = conn.createStatement();
			String sql="INSERT INTO Utente VALUES ("+Indexer.getIndex()+",?,?,?)";
			PreparedStatement stmt2 = conn.prepareStatement(sql);
			String nome=request.getParameter("form-first-name");
			String email=request.getParameter("form-email");
			String pass1=request.getParameter("password1");
			String pass2=request.getParameter("password2");
			rs = stmt.executeQuery(Query.cercaUtente2(nome));
			if(rs.next()) {
				System.out.println("QUIII");
				if (session!=null) {
		        session.setAttribute("userNameUsato", "errore");
				}
				response.setStatus(response.SC_MOVED_TEMPORARILY);
				response.setHeader("Location", "../login.jsp");
			}else {
				System.out.println("Provo a creare!");
				caricato=true;
				stmt2.setString(1, nome);
				stmt2.setString(2, email);
				stmt2.setString(3, pass1);
				controllo=stmt2.executeUpdate();
				
			}
			} catch (ClassNotFoundException ex) {
			ex.getMessage();
			} catch (SQLException ex) {
			ex.getMessage();
			} finally {
			try {
				if(controllo>0) {
					System.out.println("Fatto!!");
				}else {
					System.out.println("Errore!!");
				}
			rs.close();
			stmt.close();
			System.out.println("<--- statement "+rs.toString()+" chiuso --->");
			if(caricato) {
			caricato=false;	
			if(session!=null) {
			session.setAttribute("creato", "creato");
			}
			}
			response.setStatus(response.SC_MOVED_TEMPORARILY);
			response.setHeader("Location", "../login.jsp");
			} catch (Exception ex) {
			ex.getMessage();
			}
			}
		
	}
	
	public void faccioIlPost(HttpServletRequest request, HttpServletResponse response) {
		try {
			doPost(request,response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
