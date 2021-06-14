<%@page import="suiviDeRepas.messages.LecteurMessage"%>
<%@page import="suiviDeRepas.bo.Aliment"%>
<%@page import="java.util.List"%>
<%@page import="suiviDeRepas.bo.Repas"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Historique</title>
</head>
<body>
	<h1>Historique</h1>
	
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
		} else {
	%>
	
	<!-- Affichage de l'historique des repas -->
	
	<table align="center">
		<thead>
			<tr>
				<td>Date</td>
				<td>Heure</td>
				<td>Aliments</td>
			</tr>
		</thead>
		
		<%
			List<Repas> listeRepas = (List<Repas>)request.getAttribute("historique");
			if(listeRepas == null || listeRepas.isEmpty()) {
		%>
		<p>Il n'y a pas de repas</p>
		<%
			} else {
		%>
		<tbody>
			<%
				for(Repas r : listeRepas) {
			%>
			<tr>
				<td><%=r.getDateRepas() %></td>
				<td><%=r.getHeureRepas() %></td>
				<td>
					<ul>
					<%
						for(Aliment a : r.getListaliments()) {
					%>
						<li><%=a.getNomAliment() %></li>
					<%
						}
					%>
					</ul>
				</td>
			</tr>
			<%
				}
			%>
		</tbody>
		<%
			}
		%>
	</table>
	
	<%
		}
	%>
	<br>
	 
	 <form action="<%= request.getContextPath()%>/ServletAjoutRepas">
		<button>Ajouter un nouveau repas</button>
	</form>
	<br>
	<br>
	<form action="<%= request.getContextPath()%>/ServletAccueil">
		<button>Retour à l'accueil</button>
	</form>
</body>
</html>