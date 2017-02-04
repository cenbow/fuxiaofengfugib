package com.org.weixin.client.bean.base.message.resp;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 视频
 */
public class RespVideoMessage extends RespMessage {

	public RespVideoMessage(String touser,Video video) {
		super(touser,"video");
		this.video = video;
	}

	public Video video;

	public Video getVideo() {
		return video;
	}

	public void setVideo(Video video) {
		this.video = video;
	}

	public static class Video {
		private String media_id;
		private String title;
		private String description;

		public Video(String media_id, String title, String description) {
			super();
			this.media_id = media_id;
			this.title = title;
			this.description = description;
		}

		public String getMedia_id() {
			return media_id;
		}

		public void setMedia_id(String mediaId) {
			media_id = mediaId;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

	}
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
