package suiviDeRepas.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import suiviDeRepas.BusinessException;
import suiviDeRepas.bll.SuiviDeRepasManager;
import suiviDeRepas.bo.Repas;


@WebServlet("/ServletVisualisationRepas")
public class ServletVisualisationRepas extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	public void init() throws ServletException {
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		//Appel du manager
		
		//récupération des données de la DAL avec la méthose getHistorique()
		List<Repas> historique;
		try {
			//Appel du manager
			SuiviDeRepasManager manager = new SuiviDeRepasManager();
			historique = manager.getHistorique();
			//passage de la liste en attribut
			request.setAttribute("historique", historique);
		} catch (BusinessException e) {
			e.printStackTrace();
			request.setAttribute("listeCodesErreur", e.getListeCodesErreur());
		}
		//envoie vers la JSP historique
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/historique.jsp");
		rd.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
