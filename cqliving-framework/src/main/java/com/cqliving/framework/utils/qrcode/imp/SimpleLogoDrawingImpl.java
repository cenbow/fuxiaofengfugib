package com.cqliving.framework.utils.qrcode.imp;

import com.cqliving.framework.utils.qrcode.LogoDrawing;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Administrator on 2015/4/7.
 */
public class SimpleLogoDrawingImpl implements LogoDrawing {
    @Override
    public void drawing(BufferedImage logoImg, BufferedImage qrcodeImg) {
        int h = qrcodeImg.getHeight();
        int w = qrcodeImg.getWidth();

        int h1 = logoImg.getHeight();
        int w1 = logoImg.getWidth();

        //高度处理，同比例缩放
        if(h1>100){
            w1=100*h1/h;
            h1=100;
        }
        //宽度处理，同比例缩放
        if(w1>100){
            h1=h*100/w;
            w1=100;
        }

        //计算logo在二维码图的坐标
        int x = w/2-w1/2;
        int y = h/2-h1/2;


        BufferedImage buffImg = qrcodeImg.getSubimage(x, y, w1, h1);

        Graphics2D g2 = buffImg.createGraphics();

        g2.drawImage(logoImg, 0, 0, w1, h1, null);

        g2.dispose();
        qrcodeImg.flush();
    }
}
