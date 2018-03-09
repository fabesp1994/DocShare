package servlets;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.jdbc.Connection;

import beans.Condivisione;
import beans.Documento;
import connection.Connessione;
import connection.Query;

/**
 * Servlet implementation class DocumentController2
 */
@WebServlet("/DocumentController2")
public class DocumentController2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DocumentController2() {
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
		java.sql.Statement stmt2 = null;
		java.sql.Statement stmt3 = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		ResultSet rs3 = null;
		Condivisione condiv=null;
		HttpSession session = request.getSession();
		Documento doc=null;
		ArrayList<Condivisione> condivisioni=new ArrayList<Condivisione>();
		
		try {
			
			conn = Connessione.getConnection();
			System.out.println("<--- "+conn+" aperta"+" --->");
			stmt = conn.createStatement();
			stmt2 = conn.createStatement();
			stmt3 = conn.createStatement();
			String paramName = "document";
			String paramValue = request.getParameter(paramName);
			rs = stmt.executeQuery(Query.cercaDocumento(paramValue));
			int id=0;
			String nome="";
			String data="";
			String path="";
			String proprietario="";
			
			while(rs.next()) {
			id=rs.getInt(1);
			nome=rs.getString(2);
			data=rs.getString(3);
			path=rs.getString(4);
			proprietario=rs.getString(5);
			}
			
			doc=new Documento(id,nome,data,path,proprietario);
			File file=new File(path);
			FileReader f;
		    f=new FileReader(file);

		    BufferedReader buffer;
		    buffer=new BufferedReader(f);
		    
		    String testo="";
		    String s;
		    while(true) {
		        s=buffer.readLine();
		        if(s==null)
		          break;
		        testo=testo+s+"\n";
		      }
		    doc.setTesto(testo);
		    
		    rs3=stmt3.executeQuery(Query.condivisioniDelProprietario(paramValue));
		    while(rs3.next()) {
				int identificativo=rs3.getInt(1);
				String utenteCondivisione=rs3.getString(2);
				int docum=rs3.getInt(3);
				String dataCond=rs3.getString(4);
				condiv=new Condivisione(identificativo,utenteCondivisione,docum,dataCond);
				rs2=stmt2.executeQuery(Query.cercaUtente3(utenteCondivisione));
				while(rs2.next()) {
					condiv.setNomeU(rs2.getString(2));
					condiv.setEmailU(rs2.getString(3));
				}
				condivisioni.add(condiv);
			}
		    
		    session.setAttribute("condivisioni", condivisioni);
		    session.setAttribute("documento", doc);
			
			response.setStatus(response.SC_MOVED_TEMPORARILY);
			response.setHeader("Location", "../documentP.jsp");
		    
			
		} catch (Exception e) {
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
