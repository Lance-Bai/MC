import java.util.Iterator;
import java.util.Vector;

public class Ruler {
    Vector<Rule> rules;

    Ruler(Condition past) {
        int m, c;
        rules = new Vector<Rule>();
        if (past.direction == Direction.TOLEFT) {
            m = past.state[0][0] + past.state[0][1];
            c = past.state[1][0] + past.state[1][1];
        } else {
            m = past.state[0][1] + past.state[0][2];
            c = past.state[1][1] + past.state[1][2];
        }
        for (int i = 0; i <= m && i <= Condition.k; i++) {
            for (int j = 0; j <= c && j <= i && i + j <= Condition.k; j++) {
                rules.add(new Rule(i, j));
            }
        }
        for (int i = 1; i <= Condition.k && i <= c; i++) {
            rules.add(new Rule(0, i));
        }
        rules.remove(0);
    }

    Iterator<Rule> getRuler(){
        return rules.iterator();
    }
}

class Rule {
    int M;
    int C;

    Rule(int M, int C) {
        this.M = M;
        this.C = C;
    }
}
