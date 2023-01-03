import java.util.ArrayList;
import java.util.Arrays;
import java.util.*;

public class Words {
    public static ArrayList<String> list = new ArrayList<>(Arrays.asList(
            "alert", "bound", "break", "clear", "close", "codes", "enums", "false", "files", "final", "float", "index",
            "inset", "logic", "mouse", "nodes", "pixel", "print", "scope", "short", "stack", "stage", "super", "throw",
            "token", "value", "while", "world", "write"
    ));

    public static String getWord() {
        Random random = new Random();
        int num = random.nextInt(29);
        return list.get(num);
    }

    public static void main(String[] args) {
        System.out.println(getWord());
    }

    // use this ArrayList if you want to attempt one of the extra credit options (accounting for duplicate characters)
    /**
     public static ArrayList<String> list = new ArrayList<>(Arrays.asList(
     "array", "catch", "class","error", "event", "hello", "inner", "javac", "merge", "nlogn", "queue", "scene"
     ));
     */
}
