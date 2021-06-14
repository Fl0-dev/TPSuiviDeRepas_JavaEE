package suiviDeRepas.bo;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Repas {
///////////////////////////////////////Attributs///////////////////////////////////
	private int idRepas;
	private LocalDate dateRepas;
	private LocalTime heureRepas;
	private List<Aliment> listaliments = new ArrayList<>();
	
	///////////////////////////////////////Getters Setters///////////////////////////////////
	public int getIdRepas() {
		return idRepas;
	}
	public void setIdRepas(int idRepas) {
		this.idRepas = idRepas;
	}
	public LocalTime getHeureRepas() {
		return heureRepas;
	}
	public void setHeureRepas(LocalTime heureRepas) {
		this.heureRepas = heureRepas;
	}
	public LocalDate getDateRepas() {
		return dateRepas;
	}
	public void setDateRepas(LocalDate dateRepas) {
		this.dateRepas = dateRepas;
	}
	
	public List<Aliment> getListaliments() {
		return listaliments;
	}
	public void setListaliments(List<Aliment> listaliments) {
		this.listaliments = listaliments;
	}
	
///////////////////////////////////////Constructeurs///////////////////////////////////
	public Repas(int idRepas, LocalDate dateRepas, LocalTime heureRepas, List<Aliment> listaliments) {
		super();
		this.idRepas = idRepas;
		this.dateRepas = dateRepas;
		this.heureRepas = heureRepas;
		this.listaliments = listaliments;
	}
	public Repas(LocalDate dateRepas, LocalTime heureRepas, List<Aliment> listaliments) {
		super();
		this.dateRepas = dateRepas;
		this.heureRepas = heureRepas;
		this.listaliments = listaliments;
	}
	public Repas() {
		listaliments = new ArrayList<>();
	}
	public Repas(int idRepas, LocalDate dateRepas, LocalTime heureRepas) {
		super();
		this.idRepas = idRepas;
		this.dateRepas = dateRepas;
		this.heureRepas = heureRepas;
	}
	
///////////////////////////////////////Méthodes///////////////////////////////////
	
	/**
	 * permet d'ajouter un aliment
	 * @param alimentAajouter
	 */
	public void ajouterAliment(Aliment alimentAajouter) {
		listaliments.add(alimentAajouter);
		
		
	}
	@Override
	public String toString() {
		return "Repas [id=" + this.idRepas + ", date=" + this.dateRepas + ", heure=" + this.heureRepas + ", aliments=" + this.listaliments + "]";
	}
}
