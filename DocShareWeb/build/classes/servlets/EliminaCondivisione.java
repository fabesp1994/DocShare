package servlets;

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

/**
 * Servlet implementation class EliminaCondivisione
 */
@WebServlet("/EliminaCondivisione")
public class EliminaCondivisione extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EliminaCondivisione() {
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
			String paramName = "utenteCond";
			String paramValue = request.getParameter(paramName);
			Documento documento=(Documento) session.getAttribute("documento");
			
			
			stmt2.setInt(1, documento.getId());
			stmt2.setString(2, paramValue);
			stmt2.executeUpdate();
			
			RequestDispatcher rd = request.getRequestDispatcher("DocumentController2?document="+documento.getId());
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
