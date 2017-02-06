package com.cqliving.framework.utils.qrcode.imp;


import com.cqliving.framework.utils.qrcode.TextDrawing;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

/**
 * Created by Administrator on 2015/4/7.
 */
public class SimpleTextDrawingImpl implements TextDrawing {
    @Override
    public void drawing(String text, BufferedImage qrcodeImg) {
        int qrcodeWidth=qrcodeImg.getWidth();
        int qrcodeHeight=qrcodeImg.getHeight();
        //获取font的样式应用在str上的整个矩形
        Font font = new Font("华文行楷", Font.BOLD, 36);
        BufferedImage bufferedImage = new BufferedImage(qrcodeWidth, qrcodeHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = (Graphics2D) bufferedImage.getGraphics();
        FontRenderContext frc = graphics.getFontRenderContext();
        bufferedImage = graphics.getDeviceConfiguration().createCompatibleImage(qrcodeWidth, qrcodeHeight, Transparency.TRANSLUCENT);

        graphics.dispose();
        graphics = bufferedImage.createGraphics();
        TextLayout textLayout = new TextLayout(text, font, frc);
        graphics.setFont(font);
        FontMetrics fm = graphics.getFontMetrics();

        int ascent = fm.getAscent(); //取得Ascent
        int descent = fm.getDescent(); //取得Descent
        int width = fm.stringWidth(text); //取得字符串宽度

        //计算置中坐标
        int x = (qrcodeWidth - width) / 2;
        int y = (qrcodeHeight - (ascent + descent)) / 2 + ascent;

        Shape sha = textLayout.getOutline(AffineTransform.getTranslateInstance(x, y));
        //int rgb = Color.BLACK.getRGB();
        int w = width+20;
        int h = ascent+descent+20;
        graphics.setColor(Color.WHITE);
        graphics.fillRoundRect((qrcodeWidth - w) / 2, (qrcodeHeight - h) / 2, w, h, 20, 20);

        graphics.setColor(new Color(0x1C86EE));
        graphics.drawRoundRect((qrcodeWidth - w) / 2, (qrcodeHeight - h) / 2, w, h, 20, 20);
        graphics.setColor(new Color(0x0C004B));
        graphics.setStroke(new BasicStroke(10f));
        graphics.draw(sha);
        graphics.setColor(new Color(0x1C86EE));
        graphics.fill(sha);

        graphics.dispose();
        bufferedImage.flush();

        Graphics2D g2 = qrcodeImg.createGraphics();
        g2.drawImage(bufferedImage, 0, 0, qrcodeWidth, qrcodeHeight, null);
        g2.dispose();
        qrcodeImg.flush();
    }
}
