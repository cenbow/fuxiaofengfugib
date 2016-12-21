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

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author fuxiaofeng on 2016年11月16日
 */
public class NioClient2 {

	SocketChannel client;
	Selector selector;
	ByteBuffer receive = ByteBuffer.allocate(1024);
	ByteBuffer send = ByteBuffer.allocate(1024);
	
	
	public NioClient2() throws IOException{
		
		client = SocketChannel.open();
		client.configureBlocking(false);
		client.connect(new InetSocketAddress("localhost",8080));
		
		selector = Selector.open();
		client.register(selector, SelectionKey.OP_CONNECT);
	}
	
	
	public void listener() throws IOException{
		
	   if(client.isConnectionPending()){
		   System.out.println("结束链接，在控制台输入内容建立新链接");
		   client.finishConnect();
		   client.register(selector, SelectionKey.OP_WRITE);
	   }
	   Scanner scanner = new Scanner(System.in);
	   while(scanner.hasNextLine()){
		   String content = scanner.nextLine();
		   if(null == content || "".equals(content))continue;
		   handleClientContent(content);
	   }
	}
	
	/**
	 * Title:
	 * <p>Description:</p>
	 * @author fuxiaofeng on 2016年11月16日
	 * @param content
	 * @throws IOException 
	 */
	private void handleClientContent(String content) throws IOException {
		
		boolean unfinished = true;
		
		while(unfinished){
			
			int i = selector.select();
			if(i == 0){continue;}
			Iterator<SelectionKey> it = selector.selectedKeys().iterator();
			
			if(it.hasNext()){
				SelectionKey key = it.next();
				if(key.isReadable()){
					receive.clear();
					int len = client.read(receive);
					if(len > 0 ){
						System.out.println("接收到服务器的内容："+new String(receive.array(),0,len));
						client.register(selector, SelectionKey.OP_WRITE);
						unfinished = false;
					}
				}else if(key.isWritable()){
					send.clear();
					send.put(content.getBytes());
					send.flip();
					client.write(send);
					client.register(selector, SelectionKey.OP_READ);
				}
			}
		}
	}


	public static void main(String[] args) {
		try {
			new NioClient2().listener();;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
