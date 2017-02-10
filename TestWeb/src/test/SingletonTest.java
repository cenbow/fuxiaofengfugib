package test;

import thread.MyThreadFactory;
import thread.SingletonThreadTest;

public class SingletonTest {

	public static void main(String[] args) {
		MyThreadFactory myThreadFactory = new MyThreadFactory("singleton");
		SingletonThreadTest singletonTread = new SingletonThreadTest();
		for(int i=1;i<=1000;i++){
			myThreadFactory.newThread(singletonTread).start();;
		}
		System.out.println(myThreadFactory.getThreadCount());
	}
}
