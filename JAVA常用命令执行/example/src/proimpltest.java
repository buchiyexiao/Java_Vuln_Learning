import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

public class proimpltest {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        // 反射调用java.lang.ProcessImpl start
        Class clazz = Class.forName("java.lang.ProcessImpl");
        Method start = clazz.getDeclaredMethod("start", String[].class, Map.class, String.class, ProcessBuilder.Redirect[].class, boolean.class);
        start.setAccessible(true);
        start.invoke(null, new String[]{"calc"}, null, null, null, false);
    }
}