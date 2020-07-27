package demo.jdbc.dao;

import java.util.List;

import demo.jdbc.entity.Fournisseur;

public interface FournisseurDao {
	List<Fournisseur> extraire();
	void insert (Fournisseur fournisseur);
	int update (String ancienNom, String nuveauNom);
	boolean delete (Fournisseur fournisseur);
	

}
