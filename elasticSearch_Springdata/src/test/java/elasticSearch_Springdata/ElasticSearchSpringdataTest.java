package elasticSearch_Springdata;

import org.elasticsearch.client.Client;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.itheima.dao.ArticleResiposty;
import cn.itheima.domain.Article;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:applicationContext.xml")
public class ElasticSearchSpringdataTest {

	@Autowired
	private ArticleResiposty articleResiposty;
	
	@Autowired
	private Client client;
	
	@Autowired
	private ElasticsearchTemplate elasticsearchTemplate;
	
	@Test
	public void CreateIndex(){
		elasticsearchTemplate.createIndex(Article.class);
		elasticsearchTemplate.putMapping(Article.class);
	}
	
	@Test
	public void save(){
		Article article = new Article();
		article.setId(1);
		article.setTitle("����java���Կ����������������ķִʹ��߰�");
		article.setContent(
				"�Կ�Դ��ĿLuenceΪӦ������ģ���ϴʵ�ִʺ��ķ������㷨�����ķִ�������°汾��IKAnalyzer3.0��չΪ����Java�Ĺ��÷ִ������������Lucene��Ŀ��ͬʱ�ṩ�˶�Lucene��Ĭ���Ż�ʵ��");
		articleResiposty.save(article);
	
		
	}
}
