package test1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Method;

public class reflection2 implements Serializable{
    private int age;
    private String name;
    public reflection2() {
    	
    }
    public reflection2(String name, int age) {
    	this.age = age;
    	this.name = name;
    }
    private void readObject(java.io.ObjectInputStream in) throws IOException,ClassNotFoundException{
        in.defaultReadObject();//调用原始的readOject方法

        try {//通过反射方法执行命令；
        Method method= java.lang.Runtime.class.getMethod("exec", String.class);
        Object result = method.invoke(Runtime.getRuntime(), "calc.exe");    
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        reflection2 x= new reflection2();
        operation.ser(x);
        operation.deser();
    }
}

class operation {
    public static void ser(Object obj) {
        //序列化操作，写数据
        try{
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("object.obj"));
            //ObjectOutputStream能把Object输出成Byte流
            oos.writeObject(obj);//序列化关键函数
            oos.flush();  //缓冲流 
            oos.close(); //关闭流
        } catch (FileNotFoundException e) 
        {        
            e.printStackTrace();
        } catch (IOException e) 
        {
            e.printStackTrace();
        }
    }

    public static void deser() {
        //反序列化操作，读取数据
        try {
            File file = new File("object.obj");
            ObjectInputStream ois= new ObjectInputStream(new FileInputStream(file));
            Object x = ois.readObject();//反序列化的关键函数
            System.out.print(x);
            ois.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
