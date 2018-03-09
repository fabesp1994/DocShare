package servlets;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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
import connection.Indexer;
import connection.Query;

/**
 * Servlet implementation class CreaCondivisione
 */
@WebServlet("/CreaCondivisione")
public class CreaCondivisione extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreaCondivisione() {
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
		// TODO Auto-generated method stub
		Connection conn = null;
		java.sql.Statement stmt = null;
		ResultSet rs = null;
		Utente utente=null;
		HttpSession session = request.getSession();
		Documento doc=(Documento) session.getAttribute("documento");
try {
			
			conn = Connessione.getConnection();
			conn.setAutoCommit(true);
			System.out.println("<--- "+conn+" aperta"+" --->");
			stmt = conn.createStatement();
			String sql="INSERT INTO Condivisione VALUES ("+Indexer.getIndex()+",?,?,?)";
			PreparedStatement stmt2 = conn.prepareStatement(sql); 
			String user=request.getParameter("text");
			rs=stmt.executeQuery(Query.cercaUtente2(user));
			if(!rs.next()) {
				System.out.println("no results");
		        session.setAttribute("flag", "no results");
				response.setStatus(response.SC_MOVED_TEMPORARILY);
				response.setHeader("Location", "../documentP.jsp");
			}else {
				int id=rs.getInt(1);
				String nickname=rs.getString(2);
				String email=rs.getString(3);
				String password=rs.getString(4);
				utente=new Utente(id,nickname,email,password);
				stmt2.setInt(1, utente.getId());
				stmt2.setInt(2, doc.getId());
				DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
	            Date date = new Date();
	            String data=dateFormat.format(date);
	            stmt2.setString(3, data);
	            stmt2.executeUpdate();
	            
	            
	            
	            RequestDispatcher rd = request.getRequestDispatcher("DocumentController2?document="+doc.getId()+"");
			    rd.forward(request,response);
				
			}
			
		}catch(Exception e) {
			
			e.printStackTrace();
			
		}finally {
			
			try {
				rs.close();
				stmt.close();
				System.out.println("<--- statement "+rs.toString()+" chiuso --->");
				
				
			}catch(Exception ex) {
				ex.printStackTrace();
			}
			
		}
	}

}
