package test;

import classLoad.ConstructorLoad;

public class ConstructorTest extends ConstructorLoad{

	public static String name="subName";
	private static ConstructorLoad constructorLoad = new ConstructorLoad();
	
	private ConstructorTest(){
		//初始化是在所有属性定义后再在构造器里面赋值给该属性
		//该属性在调用父构造器后赋值
		super();
		System.out.println(super.getName());
		System.out.println(ConstructorTest.name);
		System.out.println("初始化。。。。");
	}

	public static void main(String[] args) {
		ConstructorTest test = new ConstructorTest();
		System.out.println(test.getName());
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		ConstructorTest.name = name;
	}

	public ConstructorLoad getConstructorLoad() {
		return constructorLoad;
	}

	public void setConstructorLoad(ConstructorLoad constructorLoad) {
		ConstructorTest.constructorLoad = constructorLoad;
	}
	
}
