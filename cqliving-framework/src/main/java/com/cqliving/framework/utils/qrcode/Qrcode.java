package com.cqliving.framework.utils.qrcode;

import com.cqliving.framework.utils.qrcode.imp.SimpleLogoDrawingImpl;
import com.cqliving.framework.utils.qrcode.imp.SimpleTextDrawingImpl;
import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import org.apache.commons.lang3.StringUtils;

import javax.imageio.ImageIO;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Hashtable;

/**
 * Created by Administrator on 2015/4/7.
 *
 * 二维码工具类
 */
public final class Qrcode {

    //二维码宽度
    public static final int WIDTH=430;

    //二维码高度
    public static final int HEIGHT=430;

    //内容编码
    public static final String CHARACTER_SET="utf-8";

    //纠错级别
    public static final ErrorCorrectionLevel ERROR_LEVEL=ErrorCorrectionLevel.H;

    //边缘空白
    public static final int MARGIN = 2;

    //文件格式
    public static final String FORMAT="png";


    //生成二维码
    public static Builder encode(String content) {
        return encode(content, HEIGHT, WIDTH);
    }

    //生成二维码
    public static Builder encode(String content, int height, int width) {
        try {
            Hashtable<EncodeHintType,Object> hints = new Hashtable<EncodeHintType,Object>();
			/*设置纠错级别(L 7%~M 15%~Q 25%~H 30%),纠错级别越高存储的信息越少*/
            hints.put(EncodeHintType.ERROR_CORRECTION, ERROR_LEVEL);
			/*设置编码格式*/
            hints.put(EncodeHintType.CHARACTER_SET, CHARACTER_SET);
			/*设置边缘空白*/
            hints.put(EncodeHintType.MARGIN, MARGIN);

            BitMatrix bitMatrix = new MultiFormatWriter().encode(
                    content, BarcodeFormat.QR_CODE, width, height, hints);

            BufferedImage qrcodeImage = new BufferedImage(bitMatrix.getWidth(), bitMatrix.getHeight(), BufferedImage.TYPE_INT_RGB);
            for(int x=0;x<width;x++){
                for(int y=0;y<height;y++){
                    qrcodeImage.setRGB(x, y, bitMatrix.get(x, y) == true ? Color.BLACK.getRGB() : Color.WHITE.getRGB());
                }
            }
            return new Builder(qrcodeImage);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    //读取二维码
    public static String decode(InputStream input){

        try{
            BufferedImage image = ImageIO.read(input);
		    /*判断是否是图片*/
            if (image == null) {
                throw new RuntimeException("Input Stream is null");
            }
		    /*解析二维码用到的辅助类*/
            LuminanceSource source = new BufferedImageLuminanceSource(image);
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
            Hashtable<DecodeHintType,Object> hints= new Hashtable<DecodeHintType,Object>();
		    /*解码设置编码方式为：UTF-8*/
            hints.put(DecodeHintType.CHARACTER_SET, CHARACTER_SET);

            Result result = new MultiFormatReader().decode(bitmap,hints);
            String resultStr = result.getText();
            return resultStr;
        }catch(Exception ex){
            throw new RuntimeException(ex);
        }

    }

    public static class Builder{

        private BufferedImage qrcodeImage;

        private String text;

        private TextDrawing textDrawing;

        private InputStream logo;

        private LogoDrawing logoDrawing;

        public Builder(BufferedImage qrcodeImage) {
            this.qrcodeImage = qrcodeImage;
        }

        public Builder text(String text, TextDrawing textDrawing) {
            this.text = text;
            this.textDrawing = textDrawing;
            return this;
        }

        public Builder text(String text) {
            this.text(text, new SimpleTextDrawingImpl());
            return this;
        }


        public Builder logo(InputStream logo, LogoDrawing logoDrawing) {
            this.logo = logo;
            this.logoDrawing = logoDrawing;
            return this;
        }

        public Builder logo(InputStream logo) {
            this.logo(logo, new SimpleLogoDrawingImpl());
            return this;
        }



        public void write(OutputStream output){
            if(output==null) {
                throw new RuntimeException("Output Stream is null");
            }
             //采用的图层覆盖，所以要文字显示在logo上，必须先生成logo 再生成文字
            //生成图片
            if(logo!=null) {
                try {
                    logoDrawing.drawing(ImageIO.read(logo), qrcodeImage);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }


            //生成文字
            if(StringUtils.isNotBlank(text)){
                textDrawing.drawing(text, qrcodeImage);
            }

            //输出
            try {
                ImageIO.write(qrcodeImage, FORMAT, output);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }

}
