package suiviDeRepas.dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


import suiviDeRepas.BusinessException;
import suiviDeRepas.bo.Aliment;
import suiviDeRepas.bo.Repas;

public class SuiviDeRepasDAOJdbcImpl implements SuiviDeRepasDAO{

	
	
	//méthode insert
	@Override
	public void insert(Repas repas) throws BusinessException{
	
		//requête sql pour ajouter un repas
		String sqlInsertRepas = "INSERT INTO REPAS (dateRepas, heureRepas)" +
	            "VALUES (?,?);";
		//requête sql pour ajouter un aliment
		String sqlInsertAliment = "INSERT INTO ALIMENTS (repas, nomAliment)" +
	            "VALUES (?,?);";
		
		if(repas == null) {
			BusinessException be = new BusinessException();
			be.ajouterErreur(CodesErreurDAL.INSERT_OBJECT_NULL);
			throw be;
		}
		
		
		//ouverture de la connection à la DB
		try (Connection connection = ConnectionProvider.getConnection()){
			
			try {
				//arrêt de l'autocommit dans SQL pour annuler si erreur
				connection.setAutoCommit(false);
				
				PreparedStatement requeteRepas = connection.prepareStatement(sqlInsertRepas,PreparedStatement.RETURN_GENERATED_KEYS);
				//initialisation de sqlInsertRepas
				requeteRepas.setDate(1, Date.valueOf(repas.getDateRepas()));
				requeteRepas.setTime(2, Time.valueOf(repas.getHeureRepas()));
				// exécution
				requeteRepas.executeUpdate();
			
	            // pour récupérer l'id auto incrémenté du repas
	            ResultSet rs = requeteRepas.getGeneratedKeys();
	            if(rs.next())
	    			{
	    				repas.setIdRepas(rs.getInt(1));
	    			}   
	                
	            //initialisation de sqlInsertAliment
	    			//pour chaque aliment de la liste d'aliments du repas
	    			for (Aliment aliment : repas.getListaliments()) {
	    				PreparedStatement requeteAliment = connection.prepareStatement(sqlInsertAliment);
	    				//récupère l'id du repas
	    				requeteAliment.setInt(1, repas.getIdRepas());
	    				//récupère le nom de l'aliment
	    				requeteAliment.setString(2, aliment.getNomAliment());
	    				//exécution
	    				requeteAliment.executeUpdate();
					}
	    			//accepte la transaction SQL
	    			connection.commit();
			} catch (Exception e) {
				//si soucis annulation de la transaction
				e.printStackTrace();
				connection.rollback();	
				BusinessException be = new BusinessException();
				be.ajouterErreur(CodesErreurDAL.INSERT_REPAS_ECHEC);
				throw be;
			}
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException be = new BusinessException();
			be.ajouterErreur(CodesErreurDAL.INSERT_REPAS_ECHEC);
			throw be;
		}
	}
	
	//méthode selecAll
	public List<Repas> selectAll() throws BusinessException {
		List<Repas> listRepas = new ArrayList<>();

		//requête SQL
		String SqlSelect = "SELECT * FROM REPAS LEFT JOIN ALIMENTS ON idRepas= repas ORDER BY dateRepas DESC, heureRepas DESC;";
		
		//ouverture de la connection à la DB
		try (Connection connection = ConnectionProvider.getConnection();
        Statement requete = connection.createStatement()){
			//exécution de la requête
			ResultSet rs = requete.executeQuery(SqlSelect);
			
			//Création d'une variable idPrécedent qui comparé à l'idRepas
			//permet de mettre les aliments dans le même repas
			int idPrecedent = 0;
			Repas repas = null;
			
			//tant qu'il y a des lignes
			while (rs.next()){
				//récupération de l'idRepas
				int id = rs.getInt("idRepas");
				
				//si idPrecedent est différent de la nouvelle ligne
				if (idPrecedent!=id) {
					//récupération de la date et passage de Date à LocalDate
					LocalDate dateRequete = rs.getDate("dateRepas").toLocalDate();
					//récupération de l'heure
					LocalTime heureRequete = rs.getTime("heureRepas").toLocalTime();
					//création de repas avec les données collectées en amont
					repas = new Repas(id,dateRequete,heureRequete);
					listRepas.add(repas);
					//changement de l'idPrecedent
					idPrecedent = id;
				}
					
				//récupération du nom de l'aliment de la ligne
				String aliment = rs.getString("nomAliment");
				//création de l'aliment
				Aliment alimentAajouter = new Aliment(aliment);
				//ajout dans la liste d'aliments du repas
				repas.ajouterAliment(alimentAajouter);
			}			
			
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException be = new BusinessException();
			be.ajouterErreur(CodesErreurDAL.SELECTALL_REPAS_ECHEC);
			throw be;
		}
		return listRepas;
		
		
		
	}
	
	
	
	
	
}
