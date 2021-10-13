import java.util.Arrays;
import java.util.Stack;

public class Shower {
    static void show(Condition termination) {
        Stack<Condition> path = new Stack<Condition>();
        while (termination != null) {
            path.add(termination);
            termination = termination.parent;
        }
        while (!path.isEmpty()) {
            Condition tmp = path.pop();
            System.out.println("_________");
            System.out.println(Arrays.toString(tmp.state[0]));
            System.out.println(Arrays.toString(tmp.state[1]));
            System.out.println();
        }
    }
}
