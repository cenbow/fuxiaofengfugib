/**
 * Copyright (c) 2016 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.cqliving.tool.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.Thumbnails.Builder;
import net.coobird.thumbnailator.geometry.Positions;

import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.tool.common.util.StringUtil;
import com.cqliving.tool.common.util.file.FileUtils;

/**
 * Title:
 * <p>
 * Description:
 * </p>
 * Copyright (c) CQLIVING 2016
 * 
 * @author fuxiaofeng on 2016年5月13日
 */
public class ImageUtil {

	public static final String H_REG="h_";
	/**
	 * Title:
	 * <p>Description:</p>
	 * @author fuxiaofeng on 2016年5月13日
	 * @param inPath
	 * @param outPath
	 * @param imageCoordinate
	 */
	public static String cutImage(String inPath, String outPath, ImageCoordinate imageCoordinate) {
		
		try {
			if(StringUtil.isEmpty(outPath)){
				outPath = ImageUtil.appendSuffixBySize(imageCoordinate.getWidth(),imageCoordinate.getHeight(),inPath);
			}
			
			if(null != imageCoordinate.getRatio() && imageCoordinate.getRatio()){
				
				if(imageCoordinate.getW()<=0 || imageCoordinate.getH() <=0){
					Thumbnails.of(inPath).width(imageCoordinate.getWidth()).toFile(outPath);
				}else{
					Thumbnails.of(inPath)
					.sourceRegion(imageCoordinate.getX(), imageCoordinate.getY(), imageCoordinate.getW(),
							imageCoordinate.getH())
							.width(imageCoordinate.getWidth()).toFile(outPath);
				}
				
			}else if(imageCoordinate.getW()<=0 || imageCoordinate.getH() <=0){
				Thumbnails.of(inPath).forceSize(imageCoordinate.getWidth(), imageCoordinate.getHeight()).toFile(outPath);
			}else{
				Thumbnails.of(inPath)
				.sourceRegion(imageCoordinate.getX(), imageCoordinate.getY(), imageCoordinate.getW(),
						imageCoordinate.getH())
						.forceSize(imageCoordinate.getWidth(), imageCoordinate.getHeight()).toFile(outPath);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return outPath;
	}

	/**
	 * Title:
	 * <p>Description:</p>
	 * @author fuxiaofeng on 2016年5月13日
	 * @param inFile
	 * @param maxWidth
	 */
	public static void makeThumb(File inFile,int maxWidth) {

		try {
			BufferedImage bi = ImageIO.read(inFile);
			int w = bi.getWidth();
			if (w > maxWidth) {
				Thumbnails.of(inFile).width(maxWidth).toFile(inFile);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Title:
	 * <p>Description:宽高不变，缩小图片质量(减小大小)</p>
	 * @author fuxiaofeng on 2016年10月26日
	 * @param file
	 * @param maxSize 允许图片的最大分辨率，单位(kb)
	 */
	public static String reQuality(String file,long maxSize,float quality){
		String desFile = file;
		try {
			File f = new File(file);
			if(!f.exists())return file;
			if(f.length() > maxSize*1024){
				String suffix = FileUtils.getExtensions(file);
				if("png".equals(suffix.toLowerCase())){
					String subFileName = file.substring(0, file.lastIndexOf("." + suffix));
					desFile = subFileName + ".jpg";
				}
				Thumbnails.of(file).scale(1f).outputQuality(quality).toFile(desFile);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return desFile;
	}
	
	/**
	 * Title:
	 * <p>Description: 0:不存在，1：横图，2：竖图，3：正方形图片，4：不是图片文件</p>
	 * @author fuxiaofeng on 2016年5月13日
	 * @param inFile
	 * @param maxWidth
	 */
	public static int getImgStyle(File inFile) {

		try {
			
			if(!inFile.exists())return 0;
			
			BufferedImage bi = ImageIO.read(inFile);
			int w = bi.getWidth();
			int h = bi.getHeight();
			
			if(w==h)return 3;
			if(w<h)return 2;
			if(w>h)return 1;
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 4;
	}
	
	/**
	 * Title:
	 * <p>
	 * Description:等比压缩，传递thumb：图片规格 格式：12x23,11x11
	 * 宽高，多组用逗号分隔,每组宽高用英文小写字母x链接(图片强制修改为指定宽高) 
	 * 如果只穿一个数字，则为宽，按照宽为维度等比压缩
	 * 如果要按照高为维度等比压缩，则传h_300
	 * 参数实例：?thumb=150x100,200,h_300,200x150
	 * </p>
	 * @author fuxiaofeng on 2016年5月4日
	 * @param request
	 * @param inFile
	 */
	public static void makeThumb(String thumb, File inFile) {

		if (StringUtil.isEmpty(thumb))
			return;

		String[] arrSize = thumb.split(",");

		if (StringUtil.isEmpty(arrSize))
			return;

		for (String str : arrSize) {
			String[] wh = str.split("x");
			String sin = wh[0];
			boolean isHeight = sin.contains(H_REG);
			int h = 0,w=0;
			if(isHeight){
				sin = sin.replace(H_REG,"");
				h = StringUtil.stringToInteger(sin);
			}else{
				 w = StringUtil.stringToInteger(sin);
			}
			String path = inFile.getPath();
			File outFile = new File(ImageUtil.appendSuffixBySize(w,wh.length <= 1 ? h : StringUtil.stringToInteger(wh[1]), path));
			FileUtils.createNewFile(outFile);
			try {
				if (wh.length <= 1) {
					if(isHeight){
						Thumbnails.of(inFile).height(h).toFile(outFile);
					}else{
						Thumbnails.of(inFile).width(w).toFile(outFile);
					}
				} else {
					Thumbnails.of(inFile).forceSize(w, StringUtil.stringToInteger(wh[1])).toFile(outFile);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	//根据宽高生成新的文件路径
	/**
	 * Title:
	 * <p>Description:</p>
	 * @author fuxiaofeng on 2016年5月13日
	 * @param w
	 * @param h
	 * @param filePath
	 * @return
	 */
	public static String appendSuffixBySize(int w,int h,String filePath){
		
		File file = new File(filePath);
		if(!file.exists()){
			throw new BusinessException(-11111,"文件不存在");
		}
		String fileName = file.getName();
		// 后缀
		String suffix = FileUtils.getExtensions(fileName);
		String subFileName = fileName.substring(0, fileName.lastIndexOf("." + suffix));
		StringBuilder newFileName = new StringBuilder(subFileName);
		newFileName.append("_").append(w).append("x").append(h).append(".").append(suffix);
		
		return filePath.replace(fileName, newFileName);
	}
	
	/**
	 * Title:压缩至指定大小，多余部分裁剪掉
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @author fuxiaofeng on 2016年5月4日
	 */
	public static void cutForceSize(int w, int h, File inFile, File outFile) {

		// 压缩至指定图片尺寸，保持图片不变形，多余部分裁剪掉
		try {
			BufferedImage image = ImageIO.read(inFile);
			Builder<BufferedImage> builder = null;

			int imageWidth = image.getWidth();
			int imageHeitht = image.getHeight();
			if ((float) h / w != (float) imageWidth / imageHeitht) {
				if (imageWidth > imageHeitht) {
					image = Thumbnails.of(inFile).height(h).asBufferedImage();
				} else {
					image = Thumbnails.of(inFile).width(w).asBufferedImage();
				}
				builder = Thumbnails.of(image).sourceRegion(Positions.CENTER, w, h).size(w, h);
			} else {
				builder = Thumbnails.of(image).size(w, h);
			}
			builder.toFile(outFile);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static class ImageCoordinate {

		// 剪切图片的起始位置
		private int x;
		// 剪切图片的起始位置
		private int y;
		// 裁剪宽度
		private int w;
		// 裁剪高度
		private int h;

		// 裁剪后压缩成最终图片的宽度
		private int width;
		// 裁剪后压缩成最终图片的高度
		private int height;

		// 是否等比(true:是)(如果等比则按照宽为维度等比压缩,否则强制按照最终给定的宽高生成图片)
		private Boolean ratio;

		@SuppressWarnings("unused")
		private ImageCoordinate() {
		}

		public int getX() {
			return x;
		}

		public void setX(int x) {
			this.x = x;
		}

		public int getY() {
			return y;
		}

		public void setY(int y) {
			this.y = y;
		}

		public int getW() {
			return w;
		}

		public void setW(int w) {
			this.w = w;
		}

		public int getH() {
			return h;
		}

		public void setH(int h) {
			this.h = h;
		}

		public int getWidth() {
			return width;
		}

		public void setWidth(int width) {
			this.width = width;
		}

		public int getHeight() {
			return height;
		}

		public void setHeight(int height) {
			this.height = height;
		}

		public Boolean getRatio() {
			return ratio;
		}

		public void setRatio(Boolean ratio) {
			this.ratio = ratio;
		}
		
		public ImageCoordinate(int x, int y, int w, int h, int width, int height, Boolean ratio) {
			super();
			this.x = x;
			this.y = y;
			this.w = w;
			this.h = h;
			this.width = width;
			this.height = height;
			this.ratio = ratio;
		}

		
	}

}
