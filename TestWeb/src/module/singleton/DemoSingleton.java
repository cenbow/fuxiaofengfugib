package module.singleton;

public class DemoSingleton {

	private static DemoSingleton demoSingleton;
	
	private DemoSingleton(){
		System.out.println("DemoSingleton初始化。。。。");
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static DemoSingleton getInstance(){
		synchronized (DemoSingleton.class){
			if(null == demoSingleton){
				demoSingleton = new DemoSingleton();
			}
		}
		return demoSingleton;
	}
	
	public static void main(String[] args) {
		
	}
}
