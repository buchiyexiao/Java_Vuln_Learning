class Parent{
    static String name = "parent";
    {
        System.out.println("parent block");
    }
    static{
        System.out.println("static parent block");
    }
    public Parent(){
        System.out.println("parent constructor");
    }
}

class Child extends Parent{
    static String childname = "child";
    {
        System.out.println("child block");
    }
    static{
        System.out.println("static child block");
    }
    public Child(){
        System.out.println("child constructor");
    }
}
public class test{
    public static void main(String[] args) {
        new Child();
    }
}