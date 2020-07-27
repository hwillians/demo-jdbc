package demo.jdbc.dao;

import java.util.List;

import demo.jdbc.entity.Article;

public interface ArticleDao {
	List<Article> extraire();
	void insert (Article article);
	int update(String ancienNom,String nouveauNom);
	boolean delete (Article article);

}
