package servlets;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.jdbc.Connection;

import beans.Documento;
import connection.Connessione;
import connection.Query;

/**
 * Servlet implementation class DocumentController
 */
@WebServlet("/DocumentController")
public class DocumentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DocumentController() {
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
		Documento doc=null;
		
		try {
			
			conn = Connessione.getConnection();
			System.out.println("<--- "+conn+" aperta"+" --->");
			stmt = conn.createStatement();
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
		    
		    session.setAttribute("documento", doc);
			
			response.setStatus(response.SC_MOVED_TEMPORARILY);
			response.setHeader("Location", "../documentC.jsp");
		    
			
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
