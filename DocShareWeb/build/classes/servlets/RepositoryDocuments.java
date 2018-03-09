package servlets;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
 * Servlet implementation class RepositoryDocuments
 */
@WebServlet("/RepositoryDocuments")
public class RepositoryDocuments extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RepositoryDocuments() {
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
		java.sql.Statement stmt2 = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		Utente utente=null;
		HttpSession session = request.getSession();
		Documento doc=null;
		ArrayList<Documento> docPropri = new ArrayList<Documento>();
		ArrayList<Documento> docCondivisi = new ArrayList<Documento>();
		try {
			
			conn = Connessione.getConnection();
			System.out.println("<--- "+conn+" aperta"+" --->");
			stmt = conn.createStatement();
			stmt2 = conn.createStatement();
			utente=(Utente) session.getAttribute("user");
			rs = stmt.executeQuery(Query.documentiUtente(utente.getId()));
			while(rs.next()) {
				int id= rs.getInt(1);
				String nome=rs.getString(2);
				String data=rs.getString(3);
				String testo=rs.getString(4);
				String proprietario=rs.getString(5);
				doc=new Documento(id,nome,data,testo,proprietario);
				docPropri.add(doc);
			}
			session.setAttribute("docPropri", docPropri);
			rs = stmt.executeQuery(Query.documentiUtenteCond(utente.getId()));
			while(rs.next()) {
				int id= rs.getInt(1);
				String nome=rs.getString(2);
				String data=rs.getString(3);
				String testo=rs.getString(4);
				String proprietario=rs.getString(5);
				//Prendo il nome proprietario
				rs2=stmt2.executeQuery(Query.cercaUtente3(proprietario));
				while(rs2.next()) {
					proprietario=rs2.getString(2);
				}
				
				//Fine nome proprietario
				doc=new Documento(id,nome,data,testo,proprietario);
				docCondivisi.add(doc);
			}
			session.setAttribute("docCondivisi", docCondivisi);
			
			response.setStatus(response.SC_MOVED_TEMPORARILY);
			response.setHeader("Location", "../repository.jsp");
			
			
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
