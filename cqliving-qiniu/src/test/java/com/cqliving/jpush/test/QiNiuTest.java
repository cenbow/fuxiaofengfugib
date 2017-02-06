package com.cqliving.jpush.test;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import com.cqliving.qiniu.util.QiNiuUtil;

public class QiNiuTest {
    public static final Log logger = LogFactory.getLog(QiNiuUtil.class);
    
	@Test
	public void sendPush1() {
	  //要上传的文件路径
        //String filePath = "/Users/yuwu/Pictures/桌面背景图/Unknown.jpeg";
        /*String filePath = "/Users/yuwu/Pictures/桌面背景图/1.jpeg";
        // 要上传的空间
        String bucketname = "wanzhou-2";
        // 上传到七牛后保存的文件名
        String key = "test_fetchToBucket.jpeg";*/
        //1、
        //logger.info(QiNiuUtil.upload(filePath,bucketname));
        //2、
        //logger.info(QiNiuUtil.upload(filePath,bucketname,key));
        //3、
        //logger.info(QiNiuUtil.upload(filePath,bucketname,key,true));
        //4、
        /*String[] buckets = QiNiuUtil.listBucket();
        for(String buket : buckets){
            logger.info(buket);
        }*/
        //5、
        //QiNiuUtil.fetchToBucket("http://120.76.79.75:82/test/common/64/20160415/2ef5926973ea4035a0ce69eda54fe84f.jpg",bucketname,"test_fetchToBucket.jpeg");
        //6、
        //FileInfo fileInfo = QiNiuUtil.findOneFile(bucketname,"test_fetchToBucket.jpeg");
        //logger.info(fileInfo);
        
        //7、
        //logger.info(upload(filePath,bucketname,key,true));
        //QiNiuUtil.moveFile(bucketname,"test_fetchToBucket.jpeg","xiushan-xhl","test1234.jpeg");
        //QiNiuUtil.copyFile("xiushan-xhl","test1234.jpeg",bucketname,"test_fetchToBucket.jpeg");
        
        //8、
        //QiNiuUtil.deleteFile(bucketname,"test_fetchToBucket.jpeg");
        //9、
        //QiNiuUtil.renameFile("xiushan-xhl","test1234.jpeg","1231qqa");
        //10、
        logger.info(QiNiuUtil.download("7xohpi.com1.z0.glb.clouddn.com"));
        //QiNiuUtil.uploadChuck("/Users/yuwu/Downloads/罗技M558-Options_5.50.9.zip",bucketname,"M558-Options_5.50.9.zip");
        //QiNiuUtil.uploadAndSaveas(filePath,bucketname,key);
	}
}