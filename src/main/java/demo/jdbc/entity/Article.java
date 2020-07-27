package demo.jdbc.entity;

public class Article {

	Integer id;
	String ref;
	String designation;
	Double prix;
	Integer id_fou;
	
	
	
	/**
	 * @param id
	 * @param ref
	 * @param designation
	 * @param prix
	 * @param id_fou
	 */
	public Article(Integer id, String ref, String designation, Double prix, Integer id_fou) {
		super();
		this.id = id;
		this.ref = ref;
		this.designation = designation;
		this.prix = prix;
		this.id_fou = id_fou;
	}
	
	
	@Override
	public String toString() {
		return "Article [id=" + id + ", ref=" + ref + ", designation=" + designation + ", prix=" + prix + ", id_fou="
				+ id_fou + "]";
	}


	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * @return the ref
	 */
	public String getRef() {
		return ref;
	}
	/**
	 * @param ref the ref to set
	 */
	public void setRef(String ref) {
		this.ref = ref;
	}
	/**
	 * @return the designation
	 */
	public String getDesignation() {
		return designation;
	}
	/**
	 * @param designation the designation to set
	 */
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	/**
	 * @return the prix
	 */
	public Double getPrix() {
		return prix;
	}
	/**
	 * @param prix the prix to set
	 */
	public void setPrix(Double prix) {
		this.prix = prix;
	}
	/**
	 * @return the id_fou
	 */
	public Integer getId_fou() {
		return id_fou;
	}
	/**
	 * @param id_fou the id_fou to set
	 */
	public void setId_fou(Integer id_fou) {
		this.id_fou = id_fou;
	}
	
	
	
}
