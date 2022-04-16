package test1;

import java.lang.reflect.Field;

public class test2 {
    public static void printfield(Object obj) {
        Class a = obj.getClass();
        Field[] f = a.getFields();
        for (Field field: f){
            // 字段类型的名称
            Class ftype = field.getType();
            String typename = ftype.getName();
            // 字段名称
            String fieldname = field.getName();
            System.out.println(typename + fieldname);
        }
    }
    public static void main(String[] args) {
        User user = new User("18", 20);
        printfield(user);
    }
}
