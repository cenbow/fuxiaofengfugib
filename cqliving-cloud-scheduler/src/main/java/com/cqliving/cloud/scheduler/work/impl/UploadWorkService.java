package com.cqliving.cloud.scheduler.work.impl;

import java.net.URL;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.log4j.Logger;

import com.cqliving.cloud.online.info.domain.InfoFile;
import com.cqliving.cloud.online.info.manager.InfoFileManager;
import com.cqliving.cloud.scheduler.thread.ThreadPool;
import com.cqliving.cloud.scheduler.thread.WorkerThead;
import com.cqliving.cloud.scheduler.work.WorkService;
import com.cqliving.tool.common.util.file.FileDownloadUtil;
/****
 * 上传工作线程
 * @author YUWU
 * @date 2016-05-23
 */
public class UploadWorkService implements WorkService {
    private static Logger logger = Logger.getLogger(UploadWorkService.class);

	private long unitSize = 2000 * 1024;//每个线程下载的字节数
    private InfoFileManager infoFileManager;
    private InfoFile infoFile;
    private ThreadPool threadPool2;//用于单个文件分多线程处理
    private String workName;
    
    @Override
	public String getWorkName() {
		return workName;
	}

    public UploadWorkService(InfoFileManager infoFileManager,InfoFile infoFile,String workName,ThreadPool threadPool2) {
    	this.infoFileManager = infoFileManager;
    	this.infoFile = infoFile;
    	this.workName = workName;
    	this.threadPool2 = threadPool2;
	}
    
    @Override
    public boolean doWork() {
    	boolean flag = true;
		try {
			PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
			cm.setMaxTotal(100);
			CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(cm).build();
			
	    	//String remoteFileUrl="http://o7sbnepxw.bkt.clouddn.com/test.wmv";
			String remoteFileUrl = this.infoFile.getCloudUrl();
			//String localPath="/Users/yuwu/log/";
			String fileName = new URL(remoteFileUrl).getFile();
			//String filePath = localPath + fileName;
			
			fileName = fileName.substring(fileName.lastIndexOf("/") + 1,fileName.length()).replace("%20", " ");
			long fileSize = FileDownloadUtil.getRemoteFileSize(remoteFileUrl);
			//this.createFile(localPath+System.currentTimeMillis()+fileName, fileSize);
			Long threadCount = (fileSize/unitSize)+(fileSize % unitSize!=0?1:0);
			long offset = 0;
			CountDownLatch end = new CountDownLatch(threadCount.intValue());
			if (fileSize <= unitSize) {// 如果远程文件尺寸小于等于unitSize
				DownloadWorkService downloadThread = new DownloadWorkService(offset, fileSize,end,httpClient,infoFile,workName);
	    		this.threadPool2.execute(new WorkerThead(downloadThread));
			} else {// 如果远程文件尺寸大于unitSize
				for (int i = 1; i < threadCount; i++) {
					DownloadWorkService downloadThread = new DownloadWorkService(offset, unitSize,end,httpClient,infoFile,workName);
					this.threadPool2.execute(new WorkerThead(downloadThread));
					offset = offset + unitSize;
				}
				if (fileSize % unitSize != 0) {// 如果不能整除，则需要再创建一个线程下载剩余字节
					DownloadWorkService downloadThread = new DownloadWorkService(offset, fileSize - unitSize * (threadCount-1),end,httpClient,infoFile,workName);
					this.threadPool2.execute(new WorkerThead(downloadThread));
				}
			}
			end.await();
			logger.info(workName + "已经全部下载完成！");
			//this.threadPool2.shutdown();
		} catch (InterruptedException e) {
			logger.error(workName + "下载失败！",e);
			flag = false;
		} catch (Exception e) {
			logger.error(workName + "下载失败！",e);
			flag = false;
		}
		if(flag){
			//下载成功
			infoFile.setDownloadStatus(InfoFile.DOWNLOADSTATUS3);
			infoFile.setDownloadTime(new Date());
		}else{
			//设置为未下载
			infoFile.setDownloadStatus(InfoFile.DOWNLOADSTATUS1);
		}
		infoFileManager.save(infoFile);
        return flag;
    }
    
    /*@Override
    public boolean doWork() {
        try {
        	String url = "http://7xohpi.com2.z0.glb.qiniucdn.com/";
        	String key = QiNiuUtil.upload(infoFile.getFilePath(), "xiushan-xhl");
        	//上传完成，设置为转码中
        	infoFile.setStatus(InfoFile.STATUS2);
        	infoFile.setCloudName(key);
        	infoFile.setCloudUrl(url + key);
        	infoFileManager.save(infoFile);
        } catch (Exception e) {
        	e.printStackTrace();
            logger.error(workName + "入库存失败:" + e.getMessage());
            return false;
        }
        return false;
    }*/
}
