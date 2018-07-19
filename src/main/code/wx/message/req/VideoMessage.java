package wx.message.req;

/**
 * @ClassName: VideoMessage
 * @Description: 请求消息 用户-公众号 视频消息
 * @author chim
 * @date 2016年11月16日
 *
 */
public class VideoMessage extends BaseMessage {
	private String MediaId; // 视频消息媒体id，可以调用多媒体文件下载接口拉取数据。
	private String ThumbMediaId; // 视频消息缩略图的媒体id，可以调用多媒体文件下载接口拉取数据。

	public String getMediaId() {
		return MediaId;
	}

	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}

	public String getThumbMediaId() {
		return ThumbMediaId;
	}

	public void setThumbMediaId(String thumbMediaId) {
		ThumbMediaId = thumbMediaId;
	}

}
