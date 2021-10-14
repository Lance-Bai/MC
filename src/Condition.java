public class Condition {
    int[][] state;
    Direction direction;
    int h, g;
    static int N, k;
    Condition parent;

    Condition(int N, int k) {
        state = new int[][] { { N, 0, 0 }, { N, 0, 0 } };
        direction = Direction.TOLEFT;
        g = 0;
        Condition.N = N;
        Condition.k = k;
    }

    Condition(Condition past, Rule rule) {
        int m, c;
        if (past.direction == Direction.TOLEFT) {
            direction = Direction.TORIGHT;
            m = past.state[0][0] + past.state[0][1];
            c = past.state[1][0] + past.state[1][1];
            state = new int[][] { { m - rule.M, rule.M, past.state[0][2] }, { c - rule.C, rule.C, past.state[1][2] } };
        } else {
            direction = Direction.TOLEFT;
            m = past.state[0][1] + past.state[0][2];
            c = past.state[1][1] + past.state[1][2];
            state = new int[][] { { past.state[0][0], rule.M, m - rule.M }, { past.state[1][0], rule.C, c - rule.C } };
        }
        g = past.g + 1;
        parent = past;
    }

    Condition() {
        state = new int[][] { { 0, 0, Condition.N }, { 0, 0, Condition.N } };
        h = 0;
        g = Integer.MAX_VALUE;
    }

    void LegalCheck() throws IllegalNewStateException {
        // are numbers of people on both sides possible?
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 2; j++) {
                if (state[j][i] < 0 || state[j][i] > Condition.N) {
                    throw new IllegalNewStateException();
                }
            }
        }

        // is M >= C in transit?
        for (int i = 0; i < 3; i++) {
            if (state[0][i] < state[1][i] && state[0][i] != 0) {
                throw new IllegalNewStateException();
            }
        }

        // is M >= C when on land?
        int totalM, totalC;
        if (direction == Direction.TORIGHT) {
            totalM = state[0][1] + state[0][2];
            totalC = state[1][1] + state[1][2];
        } else {
            totalM = state[0][0] + state[0][1];
            totalC = state[1][0] + state[1][2];
        }
        if (totalM < totalC && totalM != 0) {
            throw new IllegalNewStateException();
        }

        // repeat the same operation?
        if (state[0][1] == parent.state[0][1] && state[1][1] == parent.state[1][1]) {
            throw new IllegalNewStateException();
        }

    }

    boolean isFinish() {
        return state[0][0] == 0 && state[1][0] == 0 && direction == Direction.TORIGHT;
    }
}

enum Direction {
    TOLEFT, TORIGHT;
}

class IllegalNewStateException extends Exception {
    IllegalNewStateException() {
    }

    IllegalNewStateException(String s) {
        super(s);
    }
}