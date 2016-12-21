/**
 * Copyright (c) 2016 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package niodemo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author fuxiaofeng on 2016年11月16日
 */
public class NIOClient {

	SocketChannel client ;
	Selector selector;
	
	//读取客户端的内容大小
	ByteBuffer recievebuff = ByteBuffer.allocate(1024);//数据缓冲区
	//返回给客户端写大小
	ByteBuffer sendBuff = ByteBuffer.allocate(1024);//数据缓冲区
	
	public NIOClient() throws IOException{
		client = SocketChannel.open();
		client.configureBlocking(false);
		client.connect(new InetSocketAddress("localhost",8080));
		
		selector = Selector.open();
		client.register(selector, SelectionKey.OP_CONNECT);//注册连接
	}
	
	public void listener() throws IOException{
		
		if(client.isConnectionPending()){
			client.finishConnect();
			System.out.println("请在控制台输入请求内容：");
			client.register(selector, SelectionKey.OP_WRITE);
		}
		Scanner scan = new Scanner(System.in);
		while(scan.hasNextLine()){
			String content = scan.nextLine();
			if("".equals(content)){continue;}
			this.proccess(content);
		}
	}

	private void proccess(String content) throws IOException{
		
		boolean unfinished = true;
		while(unfinished){
			int s = selector.select();
			if(s == 0 ){continue;}
			Set<SelectionKey> keys = selector.selectedKeys();
			Iterator<SelectionKey> it = keys.iterator();
			while(it.hasNext()){
				SelectionKey key = it.next();
				if(key.isReadable()){
					recievebuff.clear();
					int len = client.read(recievebuff);
					if(len>0){
						recievebuff.flip();
						System.out.println("读取到服务端的数据为："+new String(recievebuff.array(),0,len));
						unfinished = false;//读完服务器的返回，整个请求就结束了
					}
					client.register(selector, SelectionKey.OP_WRITE);
				}else if(key.isWritable()){
					sendBuff.clear();
					System.out.println("客户端的内容："+content);
					sendBuff.put(content.getBytes());
					sendBuff.flip();
					client.write(sendBuff);
					client.register(selector, SelectionKey.OP_READ);
				}
			}
		}
	}
	
	public static void main(String[] args) {
		try {
			new NIOClient().listener();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
