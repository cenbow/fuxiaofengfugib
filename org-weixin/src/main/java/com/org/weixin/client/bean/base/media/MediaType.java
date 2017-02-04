package com.org.weixin.client.bean.base.media;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public enum MediaType{
	image {
		@Override
		public String mimeType() {
			return "image/jpeg";
		}

		@Override
		public String fileSuffix() {
			return "jpg";
		}

		@Override
		public String uploadType() {
			return "image";
		}
	},voice_mp3 {
		@Override
		public String mimeType() {
			return "audio/mpeg";
		}

		@Override
		public String fileSuffix() {
			return "mp3";
		}

		@Override
		public String uploadType() {
			return "voice";
		}
	},voice_arm {
		@Override
		public String mimeType() {
			return "audio/amr";
		}

		@Override
		public String fileSuffix() {
			return "amr";
		}

		@Override
		public String uploadType() {
			return "voice";
		}
	},video {
		@Override
		public String mimeType() {
			return "video/mp4";
		}

		@Override
		public String fileSuffix() {
			return "mp4";
		}

		@Override
		public String uploadType() {
			return "video";
		}
	},thumb {
		@Override
		public String mimeType() {
			return "image/jpeg";
		}

		@Override
		public String fileSuffix() {
			return "jpg";
		}

		@Override
		public String uploadType() {
			return "thumb";
		}
	};

	public abstract String mimeType();

	public abstract String fileSuffix();

	/**
	 * 上传类型
	 * @return
	 */
	public abstract String uploadType();

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
	
}
