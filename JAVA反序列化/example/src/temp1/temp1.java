package temp1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class temp1 implements Serializable{
    // 创建一个可以被序列化的类
    // 实例化对象
    private static final long serialVersionUID = 1L;
    private int n;
    // 构造函数
    public temp1(int n){
        this.n = n;
    }
    public static void main(String[] args) {
        temp1 x = new temp1(5);//实例化一个对象
        operation.ser(x);//序列化
        operation.deser();//反序列化
    }
}

class operation{
    public static void ser(Object obj) {
        //序列化 写操作
        try{
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("object.obj"));
            //ObjectOutputStream把Obj输出成Byte
            oos.writeObject(obj);//序列化关键函数 写
            oos.flush();//缓冲流
            oos.close();//关闭流
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public static void deser() {
        //反序列化 读操作
        try{
            File file = new File("object.obj");
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
            Object x = ois.readObject();//反序列化关键函数 读
            System.out.print(x);
            ois.close();
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}