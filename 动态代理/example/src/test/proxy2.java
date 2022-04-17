package test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class proxy2{
	public static void main(String[] args) {
		DynamicSub sub = new RealSub(); //动态代理sub的类型需要为接口
		Handler handler = new Handler(sub);
		/*
		 * newProxyInstance(ClassLoader loader, Class<?>[] interfaces, InvocationHandler h)
		 * ClassLoader loader 动态代理类的类加载库
		 * Class<?> interface 指定动态代理类需要实现的所有接口
		 * 指定动态代理类相关的Handler
		 */
		DynamicSub sub1 = (DynamicSub)Proxy.newProxyInstance(DynamicSub.class.getClassLoader(), new Class[] {DynamicSub.class}, handler);
		DynamicSub sub2 = (DynamicSub)Proxy.newProxyInstance(DynamicSub.class.getClassLoader(), sub.getClass().getInterfaces(), handler);
		DynamicSub sub3 = (DynamicSub)Proxy.newProxyInstance(DynamicSub.class.getClassLoader(), RealSub.class.getInterfaces(), handler);
		// 通过不同的对象使用相同的模式完成代理
		System.out.println(DynamicSub.class);
		System.out.println(sub.getClass());
		System.out.println(RealSub.class.getInterfaces());
		sub1.request();
		sub2.request();
		sub3.request();
	}
}

interface DynamicSub{
	//抽象角色 动态代理只能是接口
	abstract void request();
}

class RealSub implements DynamicSub{
	public RealSub() {
		// TODO 自动生成的构造函数存根
	}
	public void request() {
		System.out.println("real real real");
	}
}

// 动态代理处理器

class Handler implements InvocationHandler{
	private Object obj; // 被代理的对象
	/**
	 * 重写invoke方法
	 * proxy代理类 method调用方法 args调用方法的参数
	 */
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		// 接口必须实现的方法，也是逻辑的核心
		System.out.println("before before");
		Object o = method.invoke(this.obj, args);
		System.out.println("after after");
		return o;
	}
	public Handler(Object obj) {
		this.obj = obj;
	}
}
