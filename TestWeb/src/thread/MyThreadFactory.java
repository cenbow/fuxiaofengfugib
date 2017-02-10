package thread;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ThreadFactory;

public class MyThreadFactory implements ThreadFactory{

	private int threadCount;
	private List<String> status;
	private String threadName;
	
	public MyThreadFactory(String threadName){
		this.threadName = threadName;
		this.threadCount =0;
		this.status = new ArrayList<String>();
	}
	
	@Override
	//创建线程并计数
	public Thread newThread(Runnable r) {
		Thread thread = new Thread(r,this.threadName+"-Thread_"+this.threadCount);
		String state = String.format("create thread id=%s,name=%s,state=%s,date=%s"
				,thread.getId(),thread.getName(),thread.getState(),new Date());
		this.threadCount ++;
		this.status.add(state);
		return thread;
	}

	public String getAllTreadsInfo(){
		StringBuffer buffer = new StringBuffer();
		buffer.append("totalcount:").append(this.threadCount).append("\n");
		Iterator<String> it = this.status.iterator();
		while(it.hasNext()){
			buffer.append(it.next()).append("\n");
		}
		return buffer.toString();
	}

	public int getThreadCount() {
		return threadCount;
	}

	public void setThreadCount(int threadCount) {
		this.threadCount = threadCount;
	}
	
}
