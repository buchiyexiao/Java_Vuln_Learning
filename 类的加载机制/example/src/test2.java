import javassist.*;
import javassist.bytecode.AccessFlag;

import sun.org.mozilla.javascript.internal.GeneratedClassLoader;

import java.lang.reflect.Method;

public class test2 {
    public static byte[] create(String className, String cmd) {
        ClassPool pool = ClassPool.getDefault();
        // 从classpath中查询该类
        CtClass evilclass = pool.makeClass(className);
        try {
            CtField f = new CtField(CtClass.intType, "id", evilclass); // 获得一个类型为int 名称为id的字段
            f.setModifiers(AccessFlag.PUBLIC); // 字段设置为public
            evilclass.addField(f); // 字段设置到类上
            // 添加静态代码块
            CtConstructor ci = evilclass.makeClassInitializer();
            ci.setBody("{try{Runtime.getRuntime().exec(\"" + cmd + "\");}catch(Exception e){e.printStackTrace();}}");
            // 添加构造函数
            CtConstructor ctConstructor1 = new CtConstructor(new CtClass[]{}, evilclass); // 指定参数构造器
            ctConstructor1.setBody("{try{Runtime.getRuntime().exec(\"" + cmd + "\");}catch(Exception e){e.printStackTrace();}}"); // $x 代表第x个参数 $0 代表this
            evilclass.addConstructor(ctConstructor1);
            // 添加方法
            CtMethod funM = CtNewMethod.make("public void fun(){try{Runtime.getRuntime().exec(\"" + cmd + "\");}catch(Exception e){e.printStackTrace();}}", evilclass);
            evilclass.addMethod(funM);
            evilclass.setName(className);
            evilclass.writeFile("E:\\Java_Vuln_Learning\\类的加载机制"); // 将生成的class文件保存到当前文件夹目录下
            byte[] b = evilclass.toBytecode();
            return b;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        try {
            byte[] st = test2.create("evil", "calc.exe");
            GeneratedClassLoader c1 = new GeneratedClassLoader() {
                @Override
                public Class<?> defineClass(String s, byte[] bytes) {
                    return null;
                }

                @Override
                public void linkClass(Class<?> aClass) {
                    // todo
                }
            };
            Class c = c1.defineClass("evil", st);
            Method m = c.getMethod("fun");
            c.newInstance();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
