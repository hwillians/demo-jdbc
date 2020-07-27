package demo.jdbc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import demo.jdbc.entity.Fournisseur;

public class FournisseurDaoJdbc implements FournisseurDao {

	public static void main(String[] args) {

		FournisseurDaoJdbc ofo = new FournisseurDaoJdbc();
		List<Fournisseur> listeFour = ofo.extraire();
		for (Fournisseur fo : listeFour) {
			System.out.println(fo);
		}
		
		//ofo.insert(new Fournisseur(9, "Lesieur"));
		ofo.update("Lesieur", "lecler");
		listeFour = ofo.extraire();
		for (Fournisseur fo : listeFour) {
			System.out.println(fo);
		}
		
		if(ofo.delete(new Fournisseur(9, "lecler")))System.out.println("Fourniseur supprimé !");
		listeFour = ofo.extraire();
		for (Fournisseur fo : listeFour) {
			System.out.println(fo);
		}
		
		
		
	}

	public List<Fournisseur> extraire() {
		Connection connection = null;
		List<Fournisseur> listeFour = new ArrayList<Fournisseur>();
		try {
			connection = getConnection();

			Statement monCanal = connection.createStatement();
			ResultSet monResultat = monCanal.executeQuery("select * from Fournisseur;");

			while (monResultat.next()) {
				listeFour.add(new Fournisseur(monResultat.getInt("id"), monResultat.getString("nom")));
			}

			monResultat.close();
			monCanal.close();

		} catch (Exception e) {
			System.err.println(e.getMessage());
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				System.err.println(e.getMessage());
			}
		}

		return listeFour;
	}

	@Override
	public void insert(Fournisseur fournisseur) {
		Connection connection = null;

		try {
			connection = getConnection();
			Statement monCanal = connection.createStatement();
			int nb = monCanal.executeUpdate("insert into fournisseur (id,nom) values (" + fournisseur.getId() + ",'"
					+ fournisseur.getNom() + "');");
			
			if (nb == 1) {
				System.out.println("Fourniseur ajouté !");
			}

		} catch (Exception e) {
			System.err.println(e.getMessage());
		} finally {
			try {
				if(connection != null) connection.close();
			}
			catch(SQLException e) {
				System.err.println("Probleme de connection close : "+e.getMessage());
			}
		}
	}

	@Override
	public int update(String ancienNom, String nouveauNom) {
		Connection connection = null;
		int nb =0;
		try {
			connection = getConnection();
			Statement monCanal = connection.createStatement();
			
			nb = monCanal.executeUpdate("update fournisseur SET nom = '"+nouveauNom+"' where nom='"+ancienNom+"';");
			
			monCanal.close();
		
		}catch(Exception e){
			System.out.println("Erreur d'éxecution : "+e.getMessage());
		}
		finally {
			try {
				if(connection != null) connection.close();
			}
			catch(SQLException e) {
				System.err.println("Probleme de connection close : "+e.getMessage());
			}
		}
		
		return nb;
	}

	@Override
	public boolean delete(Fournisseur fournisseur) {
		Connection connection = null;
		boolean nb = false;
		try {
			connection = getConnection();
			Statement monCanal = connection.createStatement();
			nb = monCanal.executeUpdate("delete from fournisseur where id ="+fournisseur.getId()+";")==1;
	
		monCanal.close();
		} catch (Exception e) {
			
			System.out.println("Erreur d'éxecution : "+e.getMessage());
		}finally {
			try {
				if(connection != null) connection.close();
			}
			catch(SQLException e) {
				System.err.println("Probleme de connection close : "+e.getMessage());
			}
		}
		return nb;
	}

	public Connection getConnection() {
		ResourceBundle db = ResourceBundle.getBundle("database");

		try {
			// enregistre le pilote
			Class.forName(db.getString("db.driver"));

			return DriverManager.getConnection(db.getString("db.url"), db.getString("db.user"),
					db.getString("db.pass"));
		} catch (ClassNotFoundException | SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
