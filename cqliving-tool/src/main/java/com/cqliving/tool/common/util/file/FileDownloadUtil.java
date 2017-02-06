package com.cqliving.tool.common.util.file;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Autowired;

public class FileDownloadUtil {
	//private final static Logger LOGGER = Logger.getLogger(FileDownloadUtil.class);
	/**
	 * 每个线程下载的字节数
	 */
	private long unitSize = 1000 * 1024;
	@Autowired
	private ThreadPoolExecutor taskExecutor;
	private CloseableHttpClient httpClient;
	
	public static void main(String [] args){
		FileDownloadUtil util = new FileDownloadUtil();
		try {
			util.doDownload();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public FileDownloadUtil() {
		System.out.println("初始化测试类....");
		PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
		cm.setMaxTotal(100);
		httpClient = HttpClients.custom().setConnectionManager(cm).build();
		
		taskExecutor =  new ThreadPoolExecutor(
				2, //运行线程大小
				4, //线程池的最大运行线程数
				1000, //空闲线程清楚时间
				TimeUnit.SECONDS, //空闲线程清楚时间的单位
				new ArrayBlockingQueue<Runnable>(100), //运行线程满时，等待队列的大小
				new ThreadPoolExecutor.CallerRunsPolicy());//池和队列满的策略
	}
	
	public void doDownload() throws IOException {
		String remoteFileUrl="http://o7sbnepxw.bkt.clouddn.com/test.wmv";
		String localPath="/Users/yuwu/log/";
		String fileName = new URL(remoteFileUrl).getFile();

		System.out.println("远程文件名称："+fileName);
		fileName = fileName.substring(fileName.lastIndexOf("/") + 1,fileName.length()).replace("%20", " ");
		System.out.println("本地文件名称："+fileName);
		long fileSize = FileDownloadUtil.getRemoteFileSize(remoteFileUrl);
		//this.createFile(localPath+System.currentTimeMillis()+fileName, fileSize);
		Long threadCount = (fileSize/unitSize)+(fileSize % unitSize!=0?1:0);
		long offset = 0;
		CountDownLatch end = new CountDownLatch(threadCount.intValue());
		if (fileSize <= unitSize) {// 如果远程文件尺寸小于等于unitSize
			DownloadThreadTest downloadThread = new DownloadThreadTest(remoteFileUrl,localPath+fileName, offset, fileSize,end,httpClient);
			taskExecutor.execute(downloadThread);

		} else {// 如果远程文件尺寸大于unitSize
			for (int i = 1; i < threadCount; i++) {
				DownloadThreadTest downloadThread = new DownloadThreadTest(remoteFileUrl, localPath+fileName, offset, unitSize,end,httpClient);
				taskExecutor.execute(downloadThread);
				offset = offset + unitSize;
			}
			if (fileSize % unitSize != 0) {// 如果不能整除，则需要再创建一个线程下载剩余字节
				DownloadThreadTest downloadThread = new DownloadThreadTest(remoteFileUrl, localPath+fileName, offset, fileSize - unitSize * (threadCount-1),end,httpClient);
				taskExecutor.execute(downloadThread);
			}

		}
		try {
			end.await();
			taskExecutor.shutdown();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Title:获取远程文件尺寸
	 * @author yuwu on 2016年5月26日
	 * @param remoteFileUrl
	 * @return
	 * @throws IOException
	 */
	public static long getRemoteFileSize(String remoteFileUrl) throws IOException {
		long fileSize = 0;
		HttpURLConnection httpConnection = (HttpURLConnection) new URL(remoteFileUrl).openConnection();
		httpConnection.setRequestMethod("HEAD");
		int responseCode = httpConnection.getResponseCode();
		if (responseCode >= 400) {
			return 0;
		}

		String sHeader;
		for (int i = 1;; i++) {
			sHeader = httpConnection.getHeaderFieldKey(i);
			if (sHeader != null && sHeader.equals("Content-Length")) {
				System.out.println("文件大小ContentLength:"+ httpConnection.getContentLength());
				fileSize = Long.parseLong(httpConnection.getHeaderField(sHeader));
				break;
			}
		}
		return fileSize;

	}

	/**
	 * 
	 * 创建指定大小的文件
	 */

//	private void createFile(String fileName, long fileSize) throws IOException {
//		File newFile = new File(fileName);
//		RandomAccessFile raf = new RandomAccessFile(newFile, "rw");
//		raf.setLength(fileSize);
//		raf.close();
//	}

}
