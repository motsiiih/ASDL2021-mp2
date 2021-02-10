package it.unicam.cs.asdl2021.mp2;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * Class that solves an instance of the the El Mamun's Caravan problem using
 * dynamic programming.
 * 
 * Template: Daniele Marchei and Luca Tesei, Implementation: Luca Mozzoni - luca.mozzoni@studenti.unicam.it
 *
 */
public class ElMamunCaravanSolver {

    // the expression to analyse
    private final Expression expression;

    // table to collect the optimal solution for each sub-problem,
    // protected just for Junit Testing purposes
    protected Integer[][] table;

    // table to record the chosen optimal solution among the optimal solution of
    // the sub-problems, protected just for JUnit Testing purposes
    protected Integer[][] tracebackTable;

    // flag indicating that the problem has been solved at least once
    private boolean solved;

    /**
     * Create a solver for a specific expression.
     * 
     * @param expression
     *                       The expression to work on
     * @throws NullPointerException
     *                                  if the expression is null
     */
    public ElMamunCaravanSolver(Expression expression) {
        if (expression == null)
            throw new NullPointerException(
                    "Creazione di solver con expression null");
        this.expression = expression;
        this.table = new Integer[expression.size()][expression.size()];
        this.tracebackTable = new Integer[expression.size()][expression.size()];
        this.solved = false;
    }

    /**
     * Returns the expression that this solver analyse.
     * 
     * @return the expression of this solver
     */
    public Expression getExpression() {
        return this.expression;
    }

    /**
     * Solve the problem on the expression of this solver by using a given
     * objective function.
     * 
     * @param function
     *                     The objective function to be used when deciding which
     *                     candidate to choose
     * @throws NullPointerException
     *                                  if the objective function is null
     */
    public void solve(ObjectiveFunction function) {
        //Se la soluzione è già disponibile il  metodo termina
        if (isSolved())
            return;
        if (function == null)
            throw new NullPointerException("Function null.");
        //Riempo la matrice con i valori dell'espressione
        for (int i = 0; i < this.expression.size(); i += 2)
            this.table[i][i] = (Integer) this.expression.get(i).getValue();
        //Richiamo il metodo privato best che risolve il problema
        // sull'espressione data di lunghezza N e metto il risultato
        // restituito alla posizione (0, N-1) della matrice
        best(0, this.expression.size() - 1, function);
        this.solved = true;
    }

    private int best(int i, int j, ObjectiveFunction function) {
        //Se il risultato è già nella matrice, lo restituisco
        // senza eseguire il corpo del metodo
        if (this.table[i][j] != null)
            return table[i][j];
        List<Integer> candidates = new ArrayList<>();
        Integer candidate = null;
        //Per ogni k = 0, 2, 4, ... t.c. k + i + 2 <= j, calcolo
        // un risultato "candidato" e lo aggiungo alla lista
        for (int k = 0; k <= j - i - 2; k += 2){
            if (this.expression.get(i + k + 1).toString().equals("+"))
                candidate = best(i, i + k, function) + best(i + k + 2, j, function);
            else if (this.expression.get(i + k + 1).toString().equals("*"))
                candidate = best(i, i + k, function) * best(i + k + 2, j, function);
            candidates.add(candidate);
        }
        //Il risultato scelto dal metodo getBest ed il
        // suo indice vengono inseriti nelle matrici
        this.table[i][j] = function.getBest(candidates);
        this.tracebackTable[i][j] = function.getBestIndex(candidates) * 2;
        return function.getBest(candidates);
    }

    /**
     * Returns the current optimal value for the expression of this solver. The
     * value corresponds to the one obtained after the last solving (which used
     * a particular objective function).
     * 
     * @return the current optimal value
     * @throws IllegalStateException
     *                                   if the problem has never been solved
     */
    public int getOptimalSolution() {
        if (!isSolved())
            throw new IllegalStateException("The problem has never been solved.");
        return this.table[0][this.expression.size() - 1];
    }

    /**
     * Returns an optimal parenthesization corresponding to an optimal solution
     * of the expression of this solver. The parenthesization corresponds to the
     * optimal value obtained after the last solving (which used a particular
     * objective function).
     * 
     * If the expression is just a digit then the parenthesization is the
     * expression itself. If the expression is not just a digit then the
     * parethesization is of the form "(<parenthesization>)". Examples: "1",
     * "(1+2)", "(1*(2+(3*4)))"
     * 
     * @return the current optimal parenthesization for the expression of this
     *         solver
     * @throws IllegalStateException
     *                                   if the problem has never been solved
     */
    public String getOptimalParenthesization() {
        if (!isSolved())
            throw new IllegalStateException("The problem has never been solved.");
        return traceback(0, this.expression.size() - 1, expression.toString());
    }

    //Metodo privato ricorsivo che ricostruisce l'espressione
    // parentesizzata correttamente
    private String traceback(int i, int j, String s){
        //La differenza tra gli indici i e j
        int n = Math.abs(j - i);
        //L'espressione da parentesizzare
        Expression exp = new Expression(s);
        //CASO 1: gli indici i e j sono uguali e pertanto
        // restituisco la stringa (o sottostringa) che ho
        // in input che sarà di lunghezza 1
        if (n == 0)
            return exp.toString();
        //CASO 2: sto prendendo in considerazione una stringa (o sottostringa)
        // di lunghezza 3 (del tipo "DIGIT*OPERATOR*DIGIT") e pertanto richiamo
        // l'algoritmo sulle due cifre
        if (n == 2)
            return "("
                    + traceback(0, 0, exp.toString().substring(0, 1))
                    + exp.get(1).toString()
                    + traceback(2, 2, exp.toString().substring(2, 3))
                    + ")";
        //CASO 3: sto operando su una stringa di lunghezza pari a 5 o superiore
        // e pertanto richiamo il metodo sulle due sottostringhe separate
        // dall'operatore in posizione tracebackTable[i][j] + 1
        else return "("
                + traceback(0, tracebackTable[i][j], exp.toString().substring(0, tracebackTable[i][j] + 1))
                + exp.get(tracebackTable[i][j] + 1).toString()
                + traceback(tracebackTable[i][j] + 2, n, exp.toString().substring(tracebackTable[i][j] + 2))
                + ")";
    }

    /**
     * Determines if the problem has been solved at least once.
     * 
     * @return true if the problem has been solved at least once, false
     *         otherwise.
     */
    public boolean isSolved() {
        return this.solved;
    }

    @Override
    public String toString() {
        return "ElMamunCaravanSolver for " + expression;
    }
}
