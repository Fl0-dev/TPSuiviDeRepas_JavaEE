package suiviDeRepas.bo;

public class Aliment {
	
	///////////////////////////////////////Attribut///////////////////////////////////

	private int idAliment;
	private String nomAliment;
	
	
	///////////////////////////////////////Getters Setters///////////////////////////////////
	
	public int getIdAliment() {
		return idAliment;
	}
	public void setIdAliment(int idAliment) {
		this.idAliment = idAliment;
	}
	public String getNomAliment() {
		return nomAliment;
	}
	public void setNomAliment(String nomAliment) {
		this.nomAliment = nomAliment;
	}
	
	///////////////////////////////////////Constructeurs//////////////////////////////////

	public Aliment(String nomAliment) {
		
		this.nomAliment = nomAliment;
	}
	public Aliment() {
	}
	@Override
	public String toString() {
		return "Aliment [idAliment=" + idAliment + ", nomAliment=" + nomAliment + "]";
	}

}
