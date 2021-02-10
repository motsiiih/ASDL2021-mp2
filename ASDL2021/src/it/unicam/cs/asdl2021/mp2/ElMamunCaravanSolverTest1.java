package it.unicam.cs.asdl2021.mp2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ElMamunCaravanSolverTest1 {
    ObjectiveFunction max = new MaximumFunction();
    ObjectiveFunction min = new MinimumFunction();

    Expression expression1 = new Expression("4+2*5");
    ElMamunCaravanSolver solver1 = new ElMamunCaravanSolver(expression1);
    Expression expression2 = new Expression("1+2*3*4+5");
    ElMamunCaravanSolver solver2 = new ElMamunCaravanSolver(expression2);
    Expression expression3 = new Expression("1+2*3*4+5");
    ElMamunCaravanSolver solver3 = new ElMamunCaravanSolver(expression3);

    @Test
    void getExpression() {
        assertEquals(solver1.getExpression(), expression1);
        assertEquals(solver2.getExpression(), expression2);
    }

    @Test
    void solveMax() {
        solver1.solve(max);
        assertEquals(30, solver1.getOptimalSolution());
        solver2.solve(max);
        assertEquals(81, solver2.getOptimalSolution());
        assertThrows(NullPointerException.class, () -> solver3.solve(null));
    }

    @Test
    void solveMin() {
        solver1.solve(min);
        assertEquals(14, solver1.getOptimalSolution());
        solver2.solve(min);
        assertEquals(30, solver2.getOptimalSolution());
        assertThrows(NullPointerException.class, () -> solver3.solve(null));
    }

    @Test
    void getOptimalSolution() {
        assertThrows(IllegalStateException.class, () -> solver2.getOptimalSolution());
        solver2.solve(max);
        assertEquals(81, solver2.getOptimalSolution());
    }

    @Test
    void getOptimalParenthesizationMax() {
        assertThrows(IllegalStateException.class, () -> solver1.getOptimalParenthesization());
        solver1.solve(max);
        System.out.println(this.solver1.getOptimalParenthesization());
        assertEquals("((4+2)*5)", this.solver1.getOptimalParenthesization());
        solver2.solve(max);
        System.out.println(this.solver2.getOptimalParenthesization());
        assertEquals("((1+2)*(3*(4+5)))", this.solver2.getOptimalParenthesization());
    }

    @Test
    void getOptimalParenthesizationMin() {
        assertThrows(IllegalStateException.class, () -> solver1.getOptimalParenthesization());
        solver1.solve(min);
        System.out.println(this.solver1.getOptimalParenthesization());
        assertEquals("(4+(2*5))", this.solver1.getOptimalParenthesization());
        solver2.solve(min);
        System.out.println(this.solver2.getOptimalParenthesization());
        assertEquals("(1+((2*(3*4))+5))", this.solver2.getOptimalParenthesization());
    }

    @Test
    void isSolved() {
        assertFalse(solver1.isSolved());
        solver1.solve(max);
        assertTrue(solver1.isSolved());
    }
}