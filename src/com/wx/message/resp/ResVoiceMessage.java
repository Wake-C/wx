package com.wx.message.resp;

/**
 * 
 * @ClassName: VoiceMessage
 * @Description: 回复语音消息 公众号--普通用户
 * @author chim
 * @date 2016年11月16日
 *
 */
public class ResVoiceMessage extends ResBaseMessage {
	private Voice Voice; // 语音

	public Voice getVoice() {
		return Voice;
	}

	public void setVoice(Voice voice) {
		Voice = voice;
	}

	
}
