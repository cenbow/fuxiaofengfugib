package com.cqliving.cloud.scheduler.task.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cqliving.cloud.online.info.domain.InfoFile;
import com.cqliving.cloud.online.info.manager.InfoFileManager;
import com.cqliving.cloud.scheduler.task.TaskScheduler;
import com.cqliving.cloud.scheduler.thread.ThreadPool;
import com.cqliving.cloud.scheduler.thread.WorkerThead;
import com.cqliving.cloud.scheduler.work.impl.UploadWorkService;


@Component("syncFileTask")
public class SyncFileTask extends TaskScheduler {

    //private static Logger logger = Logger.getLogger(ScanServiceJob.class);

    @Resource(name="threadPool1")
    private ThreadPool threadPool1;

    @Resource(name="threadPool2")
    private ThreadPool threadPool2;
    @Autowired
    private InfoFileManager infoFileManager;

    @Override
    public void execute() {
        try {
        	List<InfoFile> list = infoFileManager.getNotUploadList();
        	for(InfoFile infoFile : list){
        		//设置添加至队列
        		infoFile.setDownloadStatus(InfoFile.DOWNLOADSTATUS2);
        		infoFile.setAddQueueTime(new Date());
        		infoFileManager.save(infoFile);
        		this.threadPool1.execute(new WorkerThead(new UploadWorkService(infoFileManager,infoFile,"文件下载" + infoFile.getQiniuKey() + "===",threadPool2),1));
        	}
        	//infoFileManager.saves(list);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
