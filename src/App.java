import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;

public class App {
    public static void main(String[] args) throws Exception {
        int N = 5, k = 3;
        Condition start = new Condition(N, k);
        Condition end = new Condition();
        PriorityQueue<Condition> OpenMap = new PriorityQueue<Condition>(cmp);
        OpenMap.add(start);
        while (!OpenMap.isEmpty()) {
            Condition tmp = OpenMap.poll();
            if (tmp.isFinish()) {
                end.parent = tmp;
                end.g = tmp.g + 1;
                break;
            } else {
                Iterator<Rule> ruler = new Ruler(tmp).getRuler();
                while (ruler.hasNext()) {
                    try {
                        Condition NextCondition = new Condition(tmp, ruler.next());
                        NextCondition.LegalCheck();
                        NextCondition.h = Heuristic.getH(NextCondition);
                        OpenMap.add(NextCondition);
                    } catch (IllegalNewStateException e) {
                        // The new state is illegal, pass it.
                    }
                }
            }
        }
        Shower.show(end);
    }

    static Comparator<Condition> cmp = new Comparator<Condition>() {
        public int compare(Condition c1, Condition c2) {
            return (c1.h + c1.g) - (c2.h + c2.g);
        }
    };

}
