package servlets;

import java.io.File;
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
 * Servlet implementation class EliminaP
 */
@WebServlet("/EliminaP")
public class EliminaP extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EliminaP() {
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
		ResultSet rs=null;
		Documento doc=null;
		HttpSession session = request.getSession();
try {
			
			conn = Connessione.getConnection();
			conn.setAutoCommit(true);
			System.out.println("<--- "+conn+" aperta"+" --->");
			stmt = conn.createStatement();
			String sql="DELETE FROM Condivisione WHERE documento=?";
	    	PreparedStatement stmt2 = conn.prepareStatement(sql);
	    	String sql2="DELETE FROM Documento WHERE id=?";
	    	PreparedStatement stmt3 = conn.prepareStatement(sql2);
			String paramName = "document";
			String paramValue = request.getParameter(paramName);
			rs=stmt.executeQuery(Query.cercaDocumento(paramValue));
			
			while(rs.next()) {
				int id= rs.getInt(1);
				String nome=rs.getString(2);
				String data=rs.getString(3);
				String testo=rs.getString(4);
				String proprietario=rs.getString(5);
				doc=new Documento(id,nome,data,testo,proprietario);
			}
			
			File file=new File(doc.getTesto());
			file.delete();
			
			
			stmt2.setString(1, paramValue);
			stmt2.executeUpdate();
			
			stmt3.setString(1, paramValue);
			stmt3.executeUpdate();
			
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
