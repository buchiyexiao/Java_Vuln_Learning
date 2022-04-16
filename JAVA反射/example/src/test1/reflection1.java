package test1;

import java.lang.reflect.Method;

public class reflection1 {
    public static void main(String[] args) {
        try{
            //实例对象的getclass方法
            User testobj = new User("zzj", 20);
            Class m1 = testobj.getClass();
            //类的.class属性，类似getattr
            Class m2 = User.class;
            //运用Class.forName动态加载类
            Class m3 = Class.forName("test1.User");

            Method[] ms = m1.getMethods();

            for(Method method: ms){
                if(method.getName().equals("getName")){
                    Class[] paramtype = method.getParameterTypes();//获得方法的参数
                    Class returntype = method.getReturnType();//获得方法的返回类型
                    try{
                        User user = (User)m1.newInstance();
                        Object x = method.invoke(user);
                        System.out.println(x);
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
            }
            Method method = m1.getMethod("setName", String.class);
            User user1 = (User)m1.getConstructor(String.class, int.class).newInstance("whw",20);
            //调用自定义构造器的方法
            Object x = method.invoke(user1, "whw");
            System.out.println(user1.getName());
        }catch(Exception e1){
            e1.printStackTrace();
        }
    }
}
