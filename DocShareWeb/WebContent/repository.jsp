<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.ArrayList" %>
    <%@ page import="beans.Documento" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

  <head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>DocShare Repository</title>

    <!-- Bootstrap core CSS -->
    <link href="assets2/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="assets2/css/4-col-portfolio.css" rel="stylesheet">

  </head>

  <body onload="myFunction()">
  <% //QUI CODICE JAVA %>
    <!-- Navigation -->
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
      <div class="container">
        <a class="navbar-brand" href="#">DocShare</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
          <ul class="navbar-nav ml-auto">
            <li class="nav-item active">
              <a class="nav-link">Repository
                <span class="sr-only">(current)</span>
              </a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="./logout.jsp">Logout</a>
            </li>
          </ul>
        </div>
      </div>
    </nav>

    <!-- Page Content -->
    <div class="container">
    
    <h1 class="my-4"><b>Repository</b><br><br>
        <small><i>Upload a new file...</i></small>
      </h1>
      <br>
      
      <div id="upload">
      <form action = "servlets/UploadServlet" method = "post" onsubmit="return valida()" enctype = "multipart/form-data">
         <input type = "file" id="file" name = "file" size = "50" />
         <input type = "submit" value = "Upload File" />
      </form>
      </div>
      <br><br>
      <!-- Page Heading -->
      <h1 class="my-4">
        <small><i>Here are your files!</i></small>
      </h1>

      <div class="row">
        <!--  <div class="col-lg-3 col-md-4 col-sm-6 portfolio-item">
          <div class="card h-100">
            <a href="document.jsp"><img class="card-img-top" src="https://icon-icons.com/icons2/1274/PNG/512/1497558526-12_84950.png" alt=""></a>
            <div class="card-body">
              <h4 class="card-title">
                <a href="#">Nome primo file</a>
              </h4>
              <p class="card-text">01/01/01</p>
            </div>
            <button onclick="window.location.href='document.jsp'" class="visualizza"><b><i>Show</i></b></button><br><button onclick="window.location.href=''" class="elimina"><b><i>Delete</i></b></button>
          </div>
        </div>  -->
        <%
        ArrayList<Documento> docPropri=(ArrayList<Documento>) session.getAttribute("docPropri");
        ArrayList<Documento> docCondivisi=(ArrayList<Documento>) session.getAttribute("docCondivisi");
        if(docPropri.size()<1 && docCondivisi.size()<1){
        	out.print("&nbsp&nbsp&nbsp&nbsp There's no file in your repository!<br><br><br><br><br>");
        }else{
        for(int i=0; i<docPropri.size(); i++){
        	Documento x=docPropri.get(i);
			out.print("<div class=\"col-lg-3 col-md-4 col-sm-6 portfolio-item\">");
			out.print("<div class=\"card h-100\">");
			out.print("<a href=\"document.jsp\"><img class=\"card-img-top\" src=\"https://icon-icons.com/icons2/1274/PNG/512/1497558526-12_84950.png\" alt=\"\"></a>");
			out.print("<div class=\"card-body\">");
			out.print("<h4 class=\"card-title\">");
			out.print("<a>"+x.getNome()+"</a>");
			out.print("</h4>");
			out.print("<p class=\"card-text\">"+x.getData()+"</p>");
			out.print("</div>");
			out.print("<button onclick=\"window.location.href='servlets/DocumentController2?document="+x.getId()+"'\" class=\"visualizza\"><b><i>Show</i></b></button><br><button onclick=\"window.location.href='servlets/EliminaP?document="+x.getId()+"'\" class=\"elimina\"><b><i>Delete</i></b></button>");
			out.print("</div>");
			out.print("</div>");
        }
        
        for(int i=0; i<docCondivisi.size(); i++){
        	Documento x=docCondivisi.get(i);
			out.print("<div class=\"col-lg-3 col-md-4 col-sm-6 portfolio-item\">");
			out.print("<div class=\"card h-100\">");
			out.print("<a href=\"document.jsp\"><img class=\"card-img-top\" src=\"https://icon-icons.com/icons2/1274/PNG/512/1497558526-12_84950.png\" alt=\"\"></a>");
			out.print("<div class=\"card-body\">");
			out.print("<h4 class=\"card-title\">");
			out.print("<a>"+x.getNome()+"</a>");
			out.print("</h4>");
			out.print("<p class=\"card-text\">"+x.getData()+"&nbsp&nbsp&nbsp&nbsp<font color=\"red\"><b>CONDIVISO</b></font><br>");
			out.print("proprietario:&nbsp&nbsp&nbsp<font color=\"red\">"+x.getProprietario()+"</font></p>");
			out.print("</div>");
			out.print("<button onclick=\"window.location.href='servlets/DocumentController?document="+x.getId()+"'\" class=\"visualizza\"><b><i>Show</i></b></button><br><button onclick=\"window.location.href='servlets/EliminaC?document="+x.getId()+"'\" class=\"elimina\"><b><i>Delete</i></b></button>");
			out.print("</div>");
			out.print("</div>");
        }
        
        }
        
        %>
        
      </div>
      <!-- /.row -->

    </div>
    <!-- /.container -->

    <!-- Footer -->
    <footer class="py-5 bg-dark">
      <div class="container">
        <p class="m-0 text-center text-white">Copyright &copy; DocShare 2017</p>
      </div>
      <!-- /.container -->
    </footer>
    
    <script type="text/javascript">
        function myFunction(){
        	<%
        		if(session.getAttribute("uploaded")!=null){
        			out.print("alert(\"File caricato correttamente!\")");
        			session.removeAttribute("uploaded");
        	    }
        	
        	%>
        	
        }
    		
		</script>
		
	<script type="text/javascript">
        function valida(){
        	var file = document.getElementById("file");
        	var stringa = file.value;
        	if(file.value.length<1){
        		alert("Nessun file selezionato!");
			    file.focus;
			    return false;
        	}if(file.value.indexOf(".txt")<0){
        		alert("Selezionare un file di tipo .txt!");
			    file.focus;
			    return false;
        	}
        	return true;
        	
        }
    		
		</script>	

    <!-- Bootstrap core JavaScript -->
    <script src="assets2/vendor/jquery/jquery.min.js"></script>
    <script src="assets2/vendor/popper/popper.min.js"></script>
    <script src="assets2/vendor/bootstrap/js/bootstrap.min.js"></script>
   </body>

</html>
