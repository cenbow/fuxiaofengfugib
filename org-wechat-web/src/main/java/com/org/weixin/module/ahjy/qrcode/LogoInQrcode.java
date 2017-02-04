/**
 * Copyright (c) 2009 FEINNO, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * FEINNO, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with FEINNO.
 */
package com.org.weixin.module.ahjy.qrcode;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) feinno 2013-2016
 * @author fuxiaofeng on 2016年3月28日
 */
public class LogoInQrcode {

	
	public static void drawing(BufferedImage logoImg, BufferedImage qrcodeImg) {
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
        logoImg.flush();
        qrcodeImg.flush();
        
    }
	
}
