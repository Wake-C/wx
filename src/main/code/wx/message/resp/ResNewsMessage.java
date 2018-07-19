package wx.message.resp;

import java.util.List;

/**
 * 
 * @ClassName: ResNewsMessage
 * @Description: 回复图文消息 公众号--普通用户
 * @author chim
 * @date 2016年11月16日
 *
 */
public class ResNewsMessage extends ResBaseMessage {
	private int ArticleCount; // 图文消息个数，限制为10条以内
	private List<Article> Articles; // 多条图文消息信息，默认第一个item为大图,注意，如果图文数超过10，则将会无响应

	public int getArticleCount() {
		return ArticleCount;
	}

	public void setArticleCount(int articleCount) {
		ArticleCount = articleCount;
	}

	public List<Article> getArticles() {
		return Articles;
	}

	public void setArticles(List<Article> articles) {
		Articles = articles;
	}

}
