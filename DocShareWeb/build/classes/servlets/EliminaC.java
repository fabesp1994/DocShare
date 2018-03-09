package servlets;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.jdbc.Connection;

import beans.Documento;
import beans.Utente;
import connection.Connessione;
import connection.Query;

/**
 * Servlet implementation class EliminaC
 */
@WebServlet("/EliminaC")
public class EliminaC extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EliminaC() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection conn = null;
		java.sql.Statement stmt = null;
		ResultSet rs = null;
		HttpSession session = request.getSession();
try {
			
			conn = Connessione.getConnection();
			conn.setAutoCommit(true);
			System.out.println("<--- "+conn+" aperta"+" --->");
			stmt = conn.createStatement();
			String sql="DELETE FROM Condivisione WHERE documento=? AND utenteCondivisione=?";
	    	PreparedStatement stmt2 = conn.prepareStatement(sql);
			String paramName = "document";
			String paramValue = request.getParameter(paramName);
			Utente utente=(Utente) session.getAttribute("user");
			
			stmt2.setString(1, paramValue);
			stmt2.setInt(2, utente.getId());
			stmt2.executeUpdate();
			
			RequestDispatcher rd = request.getRequestDispatcher("RepositoryDocuments");
		    rd.forward(request,response);
		    
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			
			try {
				
				stmt.close();
				
				
			}catch(Exception ex) {
				ex.printStackTrace();
			}
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
