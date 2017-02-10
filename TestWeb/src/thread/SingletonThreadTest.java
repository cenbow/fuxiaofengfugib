package thread;

import module.singleton.DemoSingleton;

public class SingletonThreadTest implements Runnable{

	@Override
	public void run() {
		//test singleton
		DemoSingleton.getInstance();
	}
	
}
