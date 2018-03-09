package servlets;

import java.io.*;
import java.sql.PreparedStatement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.output.*;

import com.mysql.jdbc.Connection;

import beans.Utente;
import connection.Connessione;
import connection.Indexer;

import javax.servlet.annotation.WebServlet;


/**
 * Servlet implementation class UploadServlet
 */
@WebServlet("/UploadServlet")
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private boolean isMultipart;
    private String filePath="C:\\Users\\Fabio\\Desktop\\Università\\IGES\\REPOSITORY\\";
    private int maxMemSize = 20 * 1024;
	private File file ;

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadServlet() {
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
		// Check that we have a file upload request
	      isMultipart = ServletFileUpload.isMultipartContent(request);
	      boolean caricato=false;
	      HttpSession session = request.getSession();
	      Connection conn = null;
	      Utente utente=(Utente) session.getAttribute("user");
	      int id=0;
	      
	      if( !isMultipart ) {
	         
	         System.out.println("No file uploaded!"); 
	         
	         return;
	      }
	  
	      DiskFileItemFactory factory = new DiskFileItemFactory();
	   
	      // maximum size that will be stored in memory
	      factory.setSizeThreshold(maxMemSize);
	   
	      // Location to save data that is larger than maxMemSize.
	      factory.setRepository(new File("C:\\Users\\Fabio\\Desktop\\Università\\IGES\\temp"));

	      // Create a new file upload handler
	      ServletFileUpload upload = new ServletFileUpload(factory);
	  

	      try { 
	    	  
	    	 conn = Connessione.getConnection();
	    	 id=Indexer.getIndex();
	    	 String sql="INSERT INTO Documento VALUES ("+id+",?,?,?,?)";
	    	 PreparedStatement stmt2 = conn.prepareStatement(sql);
	    	 
	         // Parse the request to get file items.
	         List fileItems = upload.parseRequest(request);
		
	         // Process the uploaded file items
	         Iterator i = fileItems.iterator();

	   
	         while ( i.hasNext () ) {
	            FileItem fi = (FileItem)i.next();
	            if ( !fi.isFormField () ) {
	               // Get the uploaded file parameters
	               String fieldName = fi.getFieldName();
	               String fileName = fi.getName();
	               String contentType = fi.getContentType();
	               boolean isInMemory = fi.isInMemory();
	               long sizeInBytes = fi.getSize();
	               String filepathcomplete=null;
	               
	            
	               // Write the file
	               if( fileName.lastIndexOf("\\") >= 0 ) {
	            	  filepathcomplete=filePath +id+ fileName.substring( fileName.lastIndexOf("\\")+1);
	                  file = new File( filepathcomplete) ;
	               } else {
	            	  filepathcomplete=filePath +"\\"+id+ fileName;
	                  file = new File(filepathcomplete) ;
	               }
	               fi.write( file ) ;
	               System.out.println("Uploaded Filename: " + fileName );
	               caricato=true;
	               //AL DATABASE  (DA AGGIUSTARE)
	               DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
	               Date date = new Date();
	               String data=dateFormat.format(date);
				   stmt2.setString(1, fileName);
				   stmt2.setString(2, data);
				   stmt2.setString(3, filepathcomplete);
				   stmt2.setInt(4, utente.getId());  //prendere id utente nella session
				   stmt2.executeUpdate();
	               
	            }
	         }
	         } catch(Exception ex) {
	            System.out.println(ex);
	         }finally {
	        	 
				if(caricato) {
	     			session.setAttribute("uploaded", "uploaded");
	     			}
				RequestDispatcher rd = request.getRequestDispatcher("RepositoryDocuments");
			    rd.forward(request,response);
	         }
	}

}
