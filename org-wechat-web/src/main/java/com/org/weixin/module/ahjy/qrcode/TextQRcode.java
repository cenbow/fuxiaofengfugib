/**
 * Copyright (c) 2009 FEINNO, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * FEINNO, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with FEINNO.
 */
package com.org.weixin.module.ahjy.qrcode;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) feinno 2013-2016
 * @author fuxiaofeng on 2016年3月26日
 */
public class TextQRcode {

	    /**
	     * The default generated QR code image width.
	     */
	    private static final int QR_CODE_DEFAULT_WIDTH = 300;

	    /**
	     * The default generated QR code image height.
	     */
	    private static final int QR_CODE_DEFAULT_HEIGHT = 300;

	    /**
	     * The text to encode.
	     */
	    private String text;

	    /**
	     * The resulting bit matrix.
	     */
	    private BitMatrix matrix;

	    /**
	     * Encode the value of {@link #text} as a QR code.
	     *
	     * @throws Exception if the QR code could not be encoded for some reason.
	     */
	    public void executeConnector() throws Exception {
	        matrix = null;
	        try {
	            matrix = new QRCodeWriter().encode(text, BarcodeFormat.QR_CODE, QR_CODE_DEFAULT_WIDTH, QR_CODE_DEFAULT_HEIGHT); // TODO add width and height as parameters of the connector
	        } catch (WriterException e) {
	            throw new Exception("Failed generating QR code", e);
	        }
	    }

	    /**
	     * The value of the text to be encoded as a QR code.
	     *
	     * @return the value of {@link #text}.
	     */
	    public String getText() {
	        return text;
	    }

	    /**
	     * Set the text to be encoded as a QR code.
	     *
	     * @param text the value to use.
	     */
	    public void setText(String text) {
	        this.text = text;
	    }

	    /**
	     * Get the resulting QR code encoded {@link BitMatrix}.
	     * <p/>
	     * The matrix can then be used this way:
	     * <pre>
	     * MatrixToImageWriter.writeToFile(matrix,"PNG",new File("some/path.png")); // Write to a file
	     * MatrixToImageWriter.writeToStream(matrix, "PNG", outputStream); // Write to a stream
	     * BufferedImage image = MatrixToImageWriter.toBufferedImage(matrix); // Get a BufferedImage
	     * </pre>
	     *
	     * @return
	     */
	    public BitMatrix getMatrix() {
	        return matrix;
	    }
}
