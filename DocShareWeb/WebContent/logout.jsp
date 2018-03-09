<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    
    <%
    
    
    session.removeAttribute("user");
    session.removeAttribute("docPropri");
    session.removeAttribute("docCondivisi");
    session.removeAttribute("documento");
    session.removeAttribute("condivisioni");
    
    response.setStatus(response.SC_MOVED_TEMPORARILY);
	response.setHeader("Location", "./index.html");
    
    
    
    
    
    %>
