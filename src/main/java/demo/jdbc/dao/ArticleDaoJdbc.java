package demo.jdbc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import demo.jdbc.entity.Article;

public class ArticleDaoJdbc implements ArticleDao {
	public static void main(String[] args) {

		ArticleDaoJdbc ofo = new ArticleDaoJdbc();
		List<Article> listeArt = ofo.extraire();
		for (Article fo : listeArt) {
			System.out.println(fo);
		}
		
		ofo.insert(new Article(101, "A10", "new article", 200.0, 1));
		listeArt = ofo.extraire();
		for (Article fo : listeArt) {
			System.out.println(fo);
		}

	}

	@Override
	public List<Article> extraire() {
		Connection connection = null;
		List<Article> listeArt = new ArrayList<Article>();
		try {
			connection = getConnection();

			Statement monCanal = connection.createStatement();
			ResultSet monResultat = monCanal.executeQuery("select * from Article where article.id_fou =1;");

			while (monResultat.next()) {
				listeArt.add(new Article(monResultat.getInt("id"), monResultat.getString("ref"),
						monResultat.getString("designation"), monResultat.getDouble("prix"),
						monResultat.getInt("id_fou")));
			}
			monResultat.close();
			monCanal.close();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				System.err.println(e.getMessage());
			}

		}
		return listeArt;
	}

	private Connection getConnection() {
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

	@Override
	public void insert(Article article) {
		Connection connection = null;

		try {
			connection = getConnection();
			Statement monCanal = connection.createStatement();
			int nb = monCanal.executeUpdate("INSERT INTO Article(id,ref,designation,prix,id_fou) VALUES ("
					+article.getId()+",'"
					+article.getRef()+"','"
					+article.getDesignation()+"',"
					+article.getPrix()+","
					+article.getId_fou()+");"
					);
			if(nb==1) {
				System.out.println("Article ajouté !");
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				System.err.println("Probleme de connection close : " + e.getMessage());
			}
		}

	}

	@Override
	public int update(String ancienNom, String nouveauNom) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean delete(Article article) {
		// TODO Auto-generated method stub
		return false;
	}

}
