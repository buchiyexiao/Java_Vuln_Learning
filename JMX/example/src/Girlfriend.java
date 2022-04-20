import javax.management.DynamicMBean;
public class Girlfriend implements GirlfriendMBean{
    String name;
    public Girlfriend(String name){
        this.name = name;
    }
    public Girlfriend(){
        this.name = "My girl";
    }
    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void sayHello() {
        System.out.println("hello, I'm your girlfriend");
    }
}
