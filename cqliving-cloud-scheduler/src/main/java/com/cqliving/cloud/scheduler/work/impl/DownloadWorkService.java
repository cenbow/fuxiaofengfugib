package com.cqliving.cloud.scheduler.work.impl;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.concurrent.CountDownLatch;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.cqliving.basic.facade.PropertiesConfig;
import com.cqliving.cloud.common.constant.PropertyKey;
import com.cqliving.cloud.online.info.domain.InfoFile;
import com.cqliving.cloud.online.info.manager.InfoFileManager;
import com.cqliving.cloud.scheduler.work.WorkService;
/****
 * 上传工作线程
 * @author YUWU
 * @date 2016-05-23
 *
 */
public class DownloadWorkService implements WorkService {

    private static Logger logger = Logger.getLogger(DownloadWorkService.class);

    @Autowired
    private InfoFileManager infoFileManager;
    private InfoFile infoFile;
    private String workName;
	private long offset = 0;//偏移量
	private long length = 0;//分配给本线程的下载字节数
	private CountDownLatch end;
	private HttpClient httpClient;
	//private CloseableHttpClient httpClient;
	private HttpContext context;
    
    @Override
	public String getWorkName() {
		return workName;
	}

    /**
	 * @param url 下载文件地址
	 * @param fileName 另存文件名
	 * @param offset 本线程下载偏移量
	 * @param length 本线程下载长度
	 */
	public DownloadWorkService(long offset, long length,CountDownLatch end, HttpClient httpClient,InfoFile infoFile,String workName) {
		this.offset = offset;
		this.length = length;
		this.end = end;
		this.httpClient = httpClient;
		this.context = new BasicHttpContext();
		this.infoFile = infoFile;
    	this.workName = workName;
		logger.debug("偏移量=" + offset + ";字节数=" + length);
	}
    
    @Override
    public boolean doWork() {
    	try {
			HttpGet httpGet = new HttpGet(infoFile.getCloudUrl());
			RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(3000).setConnectTimeout(3000).build();//设置请求和传输超时时间
			httpGet.setConfig(requestConfig);
			httpClient.execute(httpGet);//执行请求
			
			httpGet.addHeader("Range", "bytes=" + this.offset + "-" + (this.offset + this.length - 1));
			HttpResponse response = httpClient.execute(httpGet, context);
			BufferedInputStream bis = new BufferedInputStream(response.getEntity().getContent());
			byte[] buff = new byte[1024];
			int bytesRead;
			
			StringBuilder fileUrl = new StringBuilder(PropertiesConfig.getString(PropertyKey.DOWNLOAD_QINIU_FILE_PATH));
			Long appId = infoFile.getAppId() == null ? 0L : infoFile.getAppId();
			fileUrl.append(appId).append(File.separator).append(infoFile.getQiniuKey());
			
			File newFile = new File(fileUrl.toString());
			RandomAccessFile raf = new RandomAccessFile(newFile, "rw");
			while ((bytesRead = bis.read(buff, 0, buff.length)) != -1) {
				raf.seek(this.offset);
				raf.write(buff, 0, bytesRead);
				this.offset = this.offset + bytesRead;
			}
			raf.close();
			bis.close();
		} catch (IOException e) {
			logger.error("下载文件出现异常",e);
			return false;
		} finally {
			end.countDown();
			logger.info(workName + "第" + end.getCount() + "块已经下载完成");
		}
        return true;
    }
}
