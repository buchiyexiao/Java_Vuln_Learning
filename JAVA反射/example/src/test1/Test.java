package test1;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Test{
	public static void methodinfo(Object obj) {
		Class a = obj.getClass();
		Method[] m = a.getMethods();
		for(int i = 0; i < m.length; i++) {
			Class returntype = m[i].getReturnType();
			System.out.println(returntype.getName() + " " + m[i].getName());
			Class []paramtype = m[i].getParameterTypes();
			for(Class class1: paramtype) {
				System.out.println(class1.getName());
			}
		}
	}
	public static Object t1(Object obj) {
		Class a = obj.getClass();
		try{
			Field age_f = a.getDeclaredField("age");
			Object o = a.newInstance();
			age_f.setAccessible(true);
			age_f.set(o, 100);
			return o;
		}catch(Exception e){
			e.printStackTrace();
		}
		return a;
	}
	public static void main(String[] args) {
		User temp_user = new User("18", 20);
		//methodinfo(temp_user);
		System.out.println(temp_user.getAge());
		User temp_user2 = (User)t1(temp_user);
		System.out.println(temp_user2.getAge());
	}
}