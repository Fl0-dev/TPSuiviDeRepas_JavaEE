package suiviDeRepas.dal;



import java.util.List;

import suiviDeRepas.BusinessException;
import suiviDeRepas.bo.Repas;

public interface SuiviDeRepasDAO {
	
	public void insert(Repas repas) throws BusinessException;
	public List<Repas> selectAll() throws BusinessException;

}