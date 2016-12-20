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
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author fuxiaofeng on 2016年11月16日
 */
public class NioServer2 {

	private int port;

	private ByteBuffer receive = ByteBuffer.allocate(1024);
	private ByteBuffer send = ByteBuffer.allocate(1024);
	private Map<SelectionKey,String> msg = new HashMap<SelectionKey,String>();
	
	ServerSocketChannel server;
	Selector selector;
	
	public NioServer2(int port) throws IOException{
		this.port  = port;
		
		server = ServerSocketChannel.open();
		server.configureBlocking(false);
		server.socket().bind(new InetSocketAddress("localhost",this.port));
		
		selector = Selector.open();
		server.register(selector, SelectionKey.OP_ACCEPT);
		System.out.println(">>>>>>>>>>>>>>>>>服务启动成功,端口号是："+this.port);
	}
	
	//监听器
	public void listener() throws IOException{
		
		while(true){
			int i = selector.select();
			if(i <= 0){continue;}
			Iterator<SelectionKey> keys = selector.selectedKeys().iterator();
			while(keys.hasNext()){
				SelectionKey key  = keys.next();
				proccess(key);
				keys.remove();
			}	
		}
	}
	/**
	 * Title:
	 * <p>Description:</p>
	 * @author fuxiaofeng on 2016年11月16日
	 * @param key
	 * @throws ClosedChannelException 
	 */
	private void proccess(SelectionKey key) throws IOException {
		
		if(key.isAcceptable()){
			SocketChannel client = server.accept();
			client.configureBlocking(false);
			client.register(selector, SelectionKey.OP_READ);
		}else if(key.isReadable()){
			SocketChannel client = (SocketChannel)key.channel();
			receive.clear();
			int len = client.read(receive);
			if(len>0){
				System.out.println("收到客户端的消息："+new String(receive.array(),0,len));
				msg.put(key,new String(receive.array(),0,len));
			}
			client.register(selector, SelectionKey.OP_WRITE);
		}else if(key.isWritable()){
			
			if(!msg.containsKey(key))return;
			SocketChannel client = (SocketChannel)key.channel();
			send.clear();
			send.put("服务器返回内容".getBytes());
			send.flip();
			client.write(send);
			client.register(selector, SelectionKey.OP_READ);
		}
		
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}
	
	public static void main(String[] args) {
		
		try {
			new NioServer2(8080).listener();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
