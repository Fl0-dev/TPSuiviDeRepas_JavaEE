package suiviDeRepas.dal;



public abstract class DAOFactory {
	
	public static SuiviDeRepasDAO getSuiviDeRepas()
	{
		SuiviDeRepasDAO suiviDeRepasDAO = new SuiviDeRepasDAOJdbcImpl();
		return suiviDeRepasDAO; 
	}
}
