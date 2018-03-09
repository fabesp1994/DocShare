<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.ArrayList" %>
    <%@ page import="beans.Documento" %>
    <%@ page import="beans.Condivisione" %>
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

  <body onload="myFunction()">
  <%  
  			Documento doc=(Documento) session.getAttribute("documento");
    		ArrayList<Condivisione> condivisioni =(ArrayList<Condivisione>) session.getAttribute("condivisioni");
    		
  
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
    
    <!-- OPZIONALE -->
      <h1 class="my-4">
        <small><i>Share your file...</i></small>
      </h1>
      <br>
      
      <div id="upload">
        <form action = "servlets/CreaCondivisione" onsubmit="return valida()" method = "post">
          <h6>Insert the nickname of the utent for the sharing...</h6>
          <input type = "text" id="text" name = "text" size = "50" />
          <input type = "submit" value = "Share" />
        </form>
      </div>
      <br><br>
      <!-- Page Heading -->
     
		
      <h1 class="my-4">
        <small><i>Read your document...</i></small>
      </h1><br>
       <div class="row">
       &nbsp;&nbsp;&nbsp;<textarea style="position:absolute ;width: 80%;height: 30%;border: 3px solid #cccccc;font-family: Tahoma, sans-serif;resize:none;" name="styled-textarea" id="styled" setbg('#e5fff3');" onblur="setbg('white')" readonly="yes"><% out.print(doc.getTesto()); %></textarea>
	   </div>
	   
	   <br><br><br><br><br><br><br><br>
	   <h1 class="my-4">
        <small><i>Sharings...</i></small>
      </h1>
      
      <!-- OPZIONALE -->
		<link rel="stylesheet" type="text/css" href="//netdna.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css">
		<hr>
		<!--  <div class="container bootstrap snippet">
		    <div class="row">
		        <div class="col-lg-12">
		            <div class="main-box no-header clearfix">
		                <div class="main-box-body clearfix">
		                    <div class="table-responsive">
		                        <table class="table user-list">
		                            <thead>
		                                <tr>
		                                <th><span>User</span></th>
		                                <th><span>Shared</span></th>
		                                <th><span>Email</span></th>
		                                <th>&nbsp;</th>
		                                </tr>
		                            </thead>
		                            <tbody>
		                                <tr>
		                                    <td>
		                                        <a class="user-link">Utente 1</a>
		                                    </td>
		                                    <td>2013/08/12</td>
		                                    <td>
		                                        <a>marlon@brando.com</a>
		                                    </td>
		                                    <td style="width: 20%;">
		                                        <a href="" class="table-link danger">
		                                            <span class="fa-stack">
		                                                <i class="fa fa-square fa-stack-2x"></i>
		                                                <i class="fa fa-trash-o fa-stack-1x fa-inverse"></i>
		                                            </span>
		                                        </a>
		                                    </td>
		                                </tr>
		                            </tbody>
		                        </table>
		                    </div>
		                </div>
		            </div>
		        </div>
		    </div>
		</div>
		-->
		<%
		if(condivisioni.size()<1){
        	out.print("&nbsp&nbsp&nbsp&nbsp There's no sharings of the document!<br><br><br><br><br>");
        }else{
        	out.print("<div class=\"container bootstrap snippet\">");
    		out.print("<div class=\"row\">");
    		out.print("<div class=\"col-lg-12\">");
    		out.print("<div class=\"main-box no-header clearfix\">");
    		out.print("<div class=\"main-box-body clearfix\">");
    		out.print("<div class=\"table-responsive\">");
    		out.print("<table class=\"table user-list\">");
    		out.print("<thead>");
    		out.print("<tr>");
    		out.print("<th><span>User</span></th>");
    		out.print("<th><span>Shared</span></th>");
    		out.print("<th><span>Email</span></th>");
    		out.print("<th>&nbsp;</th>");
    		out.print("</tr>");
    		out.print("</thead>");
    		out.print("<tbody>");
    		
    		
        	for(int i=0;i<condivisioni.size();i++){
        		Condivisione cond=condivisioni.get(i);
        		
        		out.print("<tr>");
        		out.print("<td>");
        		out.print("<a class=\"user-link\">"+cond.getNomeU()+"</a>");
        		out.print("</td>");
        		out.print("<td>"+cond.getData()+"</td>");
        		out.print("<td>");
        		out.print("<a>"+cond.getEmailU()+"</a>");
        		out.print("</td>");
        		out.print("<td style=\"width: 20%;\">");
        		out.print("<a href=\"servlets/EliminaCondivisione?utenteCond="+cond.getUtenteCondivisione()+"\" class=\"table-link danger\">");
        		out.print("<span class=\"fa-stack\">");
        		out.print("<i class=\"fa fa-square fa-stack-2x\"></i>");
        		out.print("<i class=\"fa fa-trash-o fa-stack-1x fa-inverse\"></i>");
        		out.print("</span>");
        		out.print("</a>");
        		out.print("</td>");
        		out.print("</tr>");
        		
        		
        	}
        	
        	
        	out.print("</tbody>");
    		out.print("</table>");
    		out.print("</div>");
    		out.print("</div>");
    		out.print("</div>");
    		out.print("</div>");
    		out.print("</div>");
    		out.print("</div>");
        }
		
		
		%>
		
		<script type="text/javascript">
        function valida(){
        	var file = document.getElementById("text");
        	var stringa = file.value;
        	if(file.value.length<1){
        		alert("Indicare un utente valido!");
			    file.focus;
			    return false;
        	}
        	return true;
        	
        }
    		
		</script>
		
		<script type="text/javascript">
        function myFunction(){
        	<%
        		if(session.getAttribute("flag")!=null){
        			out.print("alert(\"Utente non trovato!\")");
        			session.removeAttribute("flag");
        	    }
        	
        	%>
        	
        }
    		
		</script>
      
      

    <!-- Bootstrap core JavaScript -->
    <script src="assets2/vendor/jquery/jquery.min.js"></script>
    <script src="assets2/vendor/popper/popper.min.js"></script>
    <script src="assets2/vendor/bootstrap/js/bootstrap.min.js"></script>
    </div>
    <br><br><br>
   </body>

</html>