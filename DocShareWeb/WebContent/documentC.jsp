<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="beans.Documento" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

  <head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>DocShare Document</title>

    <!-- Bootstrap core CSS -->
    <link href="assets2/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="assets2/css/4-col-portfolio.css" rel="stylesheet">
    

  </head>

  <body>
  <%  
  			Documento doc=(Documento) session.getAttribute("documento");
    		
  
  %>
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
              <a class="nav-link">Document
                <span class="sr-only">(current)</span>
              </a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="repository.jsp">Repository</a>
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
    <h1 class="my-4"><font color="#1E90FF"><b><% out.print(doc.getNome()); %></b></font><br><br>
      </h1>
      <!-- Page Heading -->
     
		
      <h1 class="my-4">
        <small><i>Read your document...</i></small>
      </h1><br>
       <div class="row">
       &nbsp;&nbsp;&nbsp;<textarea style="position:absolute ;width: 80%; height: 50%; heborder: 3px solid #cccccc;font-family: Tahoma, sans-serif;resize:none;" name="styled-textarea" id="styled" setbg('#e5fff3');" onblur="setbg('white')" readonly="yes"><% out.print(doc.getTesto()); %></textarea>
	   </div>
	   
	   <br><br>
      
      <!-- OPZIONALE -->
		<link rel="stylesheet" type="text/css" href="//netdna.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css">
		<hr>
      
      

    <!-- Bootstrap core JavaScript -->
    <script src="assets2/vendor/jquery/jquery.min.js"></script>
    <script src="assets2/vendor/popper/popper.min.js"></script>
    <script src="assets2/vendor/bootstrap/js/bootstrap.min.js"></script>
    </div>
    <br><br><br>
   </body>

</html>