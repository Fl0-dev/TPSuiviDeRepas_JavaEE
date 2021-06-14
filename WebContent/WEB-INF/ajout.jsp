<%@page import="java.util.List"%>
<%@page import="suiviDeRepas.messages.LecteurMessage"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Ajout</title>
</head>
<body>
	<h1>Ajout</h1>
	
	<%
		List<Integer> erreurs = (List<Integer>)request.getAttribute("listeCodesErreur");
		if(erreurs != null) {
	%>
	<h2 style="color:red;">Une erreur est survenue !</h2>
	<%
			for(int code : erreurs) {
	%>
		<p><%=LecteurMessage.getMessageErreur(code) %>
	<%
			}
		}
	%>
	
	<form action="<%= request.getContextPath()%>/ServletAjoutRepas" method="post">
	
		<label>Date : </label>
		<input type="date" id="input_date" name="date"/>
		<br>
		<label>Heure : </label>
		<input type="time" id="input_hour" name="heure"/>
		<br>
		<label>Repas : </label>
		<textarea id="textarea_repas" rows="5" cols="30" name="textAliments" placeholder="aliments qui composent le repas"></textarea>
		<br>
		<input type="submit" value="Valider">
		
	</form>
	<br>	
	<form action="<%= request.getContextPath()%>/ServletAccueil">
		<button>Annuler</button>
	</form>
</body>
</html> 