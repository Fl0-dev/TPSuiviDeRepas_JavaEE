package suiviDeRepas.servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import suiviDeRepas.BusinessException;
import suiviDeRepas.bll.SuiviDeRepasManager;

@WebServlet("/ServletAjoutRepas")
public class ServletAjoutRepas extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void init() throws ServletException {

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/ajout.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		// Déclaration des variables

		LocalDate dateFormate = null;
		LocalTime heureFormate = null;
		String textAliments = "";
		BusinessException be = new BusinessException();

		// recupération du choix de l'user
		try {
			String dateRepas = request.getParameter("date");
			// formatage de la date :
			dateFormate = LocalDate.parse(dateRepas);

		} catch (DateTimeParseException e) {
			e.printStackTrace();
			be.ajouterErreur(CodesErreurServlet.FORMAT_DATE_INCORRECT);
		}
		try {
			String heure = request.getParameter("heure");
			// formatage de l'heure :
			heureFormate = LocalTime.parse(heure);

		} catch (DateTimeParseException e) {
			e.printStackTrace();
			be.ajouterErreur(CodesErreurServlet.FORMAT_HEURE_INCORRECT);
		}

		// s'il y a eu un problème, on retourne vers le formulaire en envoyant la liste
		// des erreurs
		if (be.hasErreurs()) {
			request.setAttribute("listeCodesErreur", be.getListeCodesErreur());

			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/ajoutRepas.jsp");
			rd.forward(request, response);
		} else { // si pas de problème, on envoie à la BLL

			textAliments = request.getParameter("textAliments");

			try {
				// Appel du manager
				SuiviDeRepasManager manager = new SuiviDeRepasManager();
				// ajout du repas 
				manager.addRepas(dateFormate, heureFormate, textAliments);

				// passage sur l'historique
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/ServletVisualisationRepas");
				rd.forward(request, response);
				// Si souci
			} catch (BusinessException e) {
				e.printStackTrace();
				request.setAttribute("listeCodesErreur", e.getListeCodesErreur());
				// retour sur la page ajout
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/ajout.jsp");
				rd.forward(request, response);
			}

		}

	}
}