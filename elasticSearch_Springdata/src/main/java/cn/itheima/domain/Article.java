package cn.itheima.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldIndex;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName="bog3",type="article")
public class Article {

	/**
	 * not_analyzed表示不需要分词
	 * store表示存储
	 * analyzer表示需要分词
	 * analyzer表示分词器
	 * searchAnalyzer表示搜索是用什么来进行的
	 */
	@Id
	@Field(index=FieldIndex.not_analyzed,store=true,type=FieldType.Integer)
	private Integer id;
	@Field(index=FieldIndex.analyzed,analyzer="ik",searchAnalyzer="ik",store=true,type=FieldType.String)
	private String title;
	@Field(index=FieldIndex.analyzed,analyzer="ik",searchAnalyzer="ik",store=true,type=FieldType.String)
	private String content;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Override
	public String toString() {
		return "Article [id=" + id + ", title=" + title + ", content=" + content + "]";
	}
}
