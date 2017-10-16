package cn.itheima.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.itheima.dao.ArticleResiposty;
import cn.itheima.domain.Article;

@Service
public class ArticleServiceImp implements ArticleService{

	@Autowired
	private ArticleResiposty articleResiposty;
	
	/**
	 * �����ɾ���ҳ�����һ����
	 * ���з�ҳ��ѯ
	 */
	public void save(Article article) {
		articleResiposty.save(article);
	} 

}
