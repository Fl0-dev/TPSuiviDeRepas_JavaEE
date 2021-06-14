package suiviDeRepas.bll;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import suiviDeRepas.BusinessException;
import suiviDeRepas.bo.Aliment;
import suiviDeRepas.bo.Repas;
import suiviDeRepas.dal.DAOFactory;
import suiviDeRepas.dal.SuiviDeRepasDAO;

public class SuiviDeRepasManager {
	
	private SuiviDeRepasDAO suiviDeRepasDAO = DAOFactory.getSuiviDeRepas();
	
	/**
	 * récupère la liste des repas de la D
	 * @return la liste des repas
	 */
	public List<Repas> getHistorique() throws BusinessException {
		List<Repas> list;
		SuiviDeRepasDAO suiviDeRepasDAO = DAOFactory.getSuiviDeRepas();
		list = suiviDeRepasDAO.selectAll();
		
		return list;
	}
	/**
	 * Permet de créer un repas et de l'ajouter en DB
	 * @param date
	 * @param heure
	 * @param aliments
	 */
	public void addRepas(LocalDate date, LocalTime heure, String aliments) throws BusinessException {
//		List<String> listSaisie = new ArrayList<>();
//		List<Aliment> listAliments = new ArrayList<>();
//		//transfomation de aliments en list<String>
//		listSaisie = Arrays.asList(aliments.trim().split(", "));
//		//formatage de la liste de String en Aliment
//		for (String string : listSaisie) {
//			Aliment aliment = new Aliment(string);
//			listAliments.add(aliment);
//		}
		
		//Validation des données
				BusinessException be = new BusinessException();
				validationDate(date, be);
				validationHeure(heure, be);
				validationAliments(aliments, be);
				
				if(be.hasErreurs()) {
					throw be;
				}
		//création d'une liste d'aliments
		List<Aliment> listAliments = new ArrayList<>();
		//découpage de la String
		String[] tabAliments = aliments.split(",");
		//remplissage de la liste
		for(String a : tabAliments) {
			listAliments.add(new Aliment(a.trim()));
		}
		//construction d'un repas si pas de souci
		Repas repas = new Repas(date,heure,listAliments);
		
		suiviDeRepasDAO.insert(repas);
	}
	private void validationDate(LocalDate date, BusinessException be) {
		if(date == null) {
			be.ajouterErreur(CodesErreurBLL.REPAS_DATE_OBLIGATOIRE);
		}
	}

	private void validationHeure(LocalTime heure, BusinessException be) {
		if(heure == null) {
			be.ajouterErreur(CodesErreurBLL.REPAS_HEURE_OBLIGATOIRE);
		}
	}

	private void validationAliments(String aliments, BusinessException be) {
		//si la chaine de caractères est null ou si (en enlevant les espaces) elle est vide
		if(aliments == null || aliments.trim().isEmpty()) {
			be.ajouterErreur(CodesErreurBLL.REPAS_ALIMENTS_OBLIGATOIRE);
		}
	}
	
	
}
