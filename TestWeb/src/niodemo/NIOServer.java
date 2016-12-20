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
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author fuxiaofeng on 2016年11月16日
 */
public class NIOServer {

	ServerSocketChannel server;
	Selector selector;
	//读取客户端的内容大小
	ByteBuffer recievebuff = ByteBuffer.allocate(1024);//数据缓冲区
	//返回给客户端写大小
	ByteBuffer sendBuff = ByteBuffer.allocate(2048);//数据缓冲区
	
	Map<SelectionKey,String> sessionMsg = new HashMap<SelectionKey,String>();
	
	public NIOServer(int port) throws IOException{
		//打开通道
		server = ServerSocketChannel.open();
		//设置非阻塞
		server.configureBlocking(false);
		//绑定域名及端口
        server.socket().bind(new InetSocketAddress("localhost",port));
        selector =  Selector.open();
        server.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("服务端已打开，接收请求中。。。。");
	}
	
	public void listener() throws IOException{
		
		//用selector轮询
		while(true){
			int i = selector.select();
			//没轮询到有注册请求，则继续轮询下一次
			if(i == 0){continue;}
			Set<SelectionKey> keys  = selector.selectedKeys();//获取注册请求的Key
			Iterator<SelectionKey> iterator = keys.iterator();
			while(iterator.hasNext()){
				SelectionKey key = iterator.next();
				this.proccess(key);
				iterator.remove();
			}
		}
	}
	
	//处理单个注册请求
	public void proccess(SelectionKey key) throws IOException{
		if(key.isAcceptable()){//客户端建立了链接
			SocketChannel socketChannel = server.accept();
			socketChannel.configureBlocking(false);
			socketChannel.register(selector, SelectionKey.OP_READ);
		}else if(key.isReadable()){//客户端可读,读取客户端的内容并处理
			
			SocketChannel client = (SocketChannel)key.channel();
			recievebuff.clear();
			int len = client.read(recievebuff);
			if(len>0){
				String str = new String(recievebuff.array(),0,len);
				System.out.println("读取到客户端的内容："+ str);
				sessionMsg.put(key,"读到客户端的数据："+str);
			}
			client.register(selector, SelectionKey.OP_WRITE);
		}else if(key.isWritable()){//客户端可写，把处理的结果返回给客户端
			//只有同一个请求可以处理，保证请求同步
			if(!sessionMsg.containsKey(key)){
				return;
			}
			SocketChannel client = (SocketChannel)key.channel();
			sendBuff.clear();//写了后要把缓冲区清空
			sendBuff.put(new String(sessionMsg.get(key)+"，服务端处理完成并返回：").getBytes());
			sendBuff.flip();
			client.write(sendBuff);
			client.register(selector, SelectionKey.OP_READ);
		}
	}
	
	public static void main(String[] args) {
		try {
			new NIOServer(8080).listener();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
