package solver;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SolverTest {
    Solver emptySolver;
    Solver figurSolver;
    Solver wrongFigurSolver;
    Solver wrongSolver;

    @Before
    public void setUp() {
        emptySolver = new Solver(new int[81]);
        int[] l= new int[81];
        l[2] = 8;
        l[5] = 9;
        l[7] = 6;
        l[8] = 2;
        l[17] = 5;
        l[18] = 1;
        l[20] = 2;
        l[21] = 5;
        l[30] = 2;
        l[31] = 1;
        l[34] = 9;
        l[37] = 5;
        l[42] = 6;
        l[45] = 6;
        l[52] = 2;
        l[53] = 8;
        l[54] = 4;
        l[55] = 1;
        l[57] = 6;
        l[59] = 8;
        l[63] = 8;
        l[64] = 6;
        l[67] = 3;
        l[69] = 1;
        l[78] = 4;
        figurSolver = new Solver(l);
        l[79] = 4;
        wrongFigurSolver = new Solver(l);
        int[]s = new int[81];
        s[1] = 1;
        s[5] = 1;
        wrongSolver = new Solver(s);
    }
    @Test
    public void getSudoku() throws Exception {
        assertTrue(figurSolver.getSudoku() instanceof int[]);
    }

    @Test
    public void solve() throws Exception {
        assertTrue(emptySolver.solve());
        assertTrue(figurSolver.solve());
        assertFalse(wrongFigurSolver.solve());
        assertFalse(wrongSolver.solve());
    }

}