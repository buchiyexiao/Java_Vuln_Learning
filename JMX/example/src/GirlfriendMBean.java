import javax.management.DynamicMBean;

public interface GirlfriendMBean {
    String name = "";
    public void setName(String name);
    public String getName();
    public void sayHello();
}
