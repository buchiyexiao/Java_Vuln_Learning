package test;

public class proxy1 {
    public static void main(String[] args) {
        //Subject sub = new OldSubject(); // 老程序员的代码
        Subject sub = new NewSubject(); // new
        sub.request();
    }
}

abstract class Subject//抽象 也可以是接口interface
{
    //类比网络代理
    abstract void request();
}

//old code
class OldSubject extends Subject{
    public OldSubject(){

    }
    public void request(){
        System.out.println("old old subject.");
    }
}

class NewSubject extends Subject{//类的继承
    private OldSubject oldSubject;
    public NewSubject(){

    }
    public void request(){//
        preRequest();
        if(oldSubject == null){
            oldSubject = new OldSubject();
        }
        oldSubject.request();
        postRequest();
    }
    private void preRequest(){
        //请求前处理 打印日志 修改包
        System.out.println("do something before requesting");
    }
    private void postRequest(){
        //请求后处理
        System.out.println("do somethin after requesting");
    }
}