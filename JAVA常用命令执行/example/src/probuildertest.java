import java.io.IOException;

public class probuildertest {
    public static void main(String[] args) throws IOException {
        ProcessBuilder pb = new ProcessBuilder("calc.exe");
        Process process = pb.start();
        System.out.println(process);
    }
}
