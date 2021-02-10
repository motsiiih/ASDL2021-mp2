package it.unicam.cs.asdl2021.mp2;

import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * Class that provides an implementation of a "dynamic" min-priority queue based
 * on a ternary heap. "Dynamic" means that the priority of an element already
 * present in the queue may be decreased, so possibly this element may become
 * the new minumum element. The elements that can be inserted may be of any
 * class implementing the interface <code>PriorityQueueElement</code>. This
 * min-priority queue does not have capacity restrictions, i.e., it is always
 * possible to insert new elements and the number of elements is unbound.
 * Duplicated elements are permitted while <code>null</code> elements are not
 * permitted.
 * 
 * @author Template: Luca Tesei, Implementation: Luca Mozzoni - luca.mozzoni@studenti.unicam.it
 *
 */
public class TernaryHeapMinPriorityQueue {

    /*
     * ArrayList for representing the ternary heap. Use all positions, including
     * position 0 (the JUnit tests will assume so). You have to adapt
     * child/parent indexing formulas consequently.
     */
    private ArrayList<PriorityQueueElement> heap;

    /**
     * Create an empty queue.
     */
    public TernaryHeapMinPriorityQueue() {
        this.heap = new ArrayList<PriorityQueueElement>();
    }

    /**
     * Return the current size of this queue.
     * 
     * @return the number of elements currently in this queue.
     */
    public int size() {
        return this.heap.size();
    }

    /**
     * Add an element to this min-priority queue. The current priority
     * associated with the element will be used to place it in the correct
     * position in the ternary heap. The handle of the element will also be set
     * accordingly.
     * 
     * @param element
     *                    the new element to add
     * @throws NullPointerException
     *                                  if the element passed is null
     */
    public void insert(PriorityQueueElement element) {
        if (element == null)
            throw new NullPointerException("L'elemento passato è nullo;");
        //Inserisce l'elemento ed aggiorno la sua handle
        this.heap.add(element);
        this.heap.get(this.size()-1).setHandle(this.size()-1);
        //Utilizzando il metodo in questo modo la priorità
        // assegnata all'elemento resterà la stessa ma egli
        // verrà posizionato correttamente all'interno della coda
        this.decreasePriority(element, element.getPriority());
    }

    /**
     * Returns the current minimum element of this min-priority queue without
     * extracting it. This operation does not affect the ternary heap.
     * 
     * @return the current minimum element of this min-priority queue
     * 
     * @throws NoSuchElementException
     *                                    if this min-priority queue is empty
     */
    public PriorityQueueElement minimum() {
        if (this.size() == 0)
            throw new NoSuchElementException("La coda è priva di elementi.");
        return this.heap.get(0);
    }

    /**
     * Extract the current minimum element from this min-priority queue. The
     * ternary heap will be updated accordingly.
     * 
     * @return the current minimum element
     * @throws NoSuchElementException
     *                                    if this min-priority queue is empty
     */
    public PriorityQueueElement extractMinimum() {
        if (this.size() == 0)
            throw new NoSuchElementException("La coda è priva di elementi.");
        PriorityQueueElement min = this.minimum();
        //Sovrascrive il primo elemento con lo stesso valore dell'ultimo
        this.heap.set(0, this.heap.get(this.size()-1));
        this.heap.get(0).setHandle(0);
        //Rimuove l'ultimo elemento
        this.heap.remove(this.size()-1);
        //Ristabilisce la proprietà principale del min-heap
        this.heapify(0);
        return min;
    }

    /**
     * Decrease the priority associated to an element of this min-priority
     * queue. The position of the element in the ternary heap must be changed
     * accordingly. The changed element may become the minimum element. The
     * handle of the element will also be changed accordingly.
     * 
     * @param element
     *                        the element whose priority will be decreased, it
     *                        must currently be inside this min-priority queue
     * @param newPriority
     *                        the new priority to assign to the element
     * 
     * @throws NoSuchElementException
     *                                      if the element is not currently
     *                                      present in this min-priority queue
     * @throws IllegalArgumentException
     *                                      if the specified newPriority is not
     *                                      strictly less than the current
     *                                      priority of the element
     */
    public void decreasePriority(PriorityQueueElement element,
            double newPriority) {
        int elementIndex = element.getHandle();
        if (!this.heap.contains(element))
            throw new NoSuchElementException("L'elemento inserito non è presente nella coda.");
        if (newPriority > element.getPriority())
            throw new IllegalArgumentException(
                    "La priorità inserita deve essere minore della vecchia priorità");
        //Aggiorna la priorità dell'elemento specificato
        this.heap.get(elementIndex).setPriority(newPriority);
        //Finchè questo elemento è diverso dalla radice
        // e ha priorità più bassa dell'elemento padre,
        // scambio padre e figlio
        while (elementIndex > 0 &&
                this.heap.get(parentIndex(elementIndex)).getPriority() > this.heap.get(elementIndex).getPriority()){
            swap(parentIndex(elementIndex), elementIndex);
            elementIndex = parentIndex(elementIndex);
        }
    }

    /**
     * Erase all the elements from this min-priority queue. After this operation
     * this min-priority queue is empty.
     */
    public void clear() {
        this.heap.clear();
    }

    private int leftIndex(int i) {
        return 3*i + 1;
    }

    private int centralIndex(int i) {
        return 3*i + 2;
    }

    private int rightIndex(int i) {
        return 3*i + 3;
    }

    private int parentIndex(int i) {
        if (i == 0)
            return 0;
        return (i-1)/3;
    }

    //metodo privato che ristabilisce l'ordine degli elementi
    //facendo sì che venga rispettata la proprietà principale
    //dell'heap
    private void heapify(int i) {
        int min = i;
        int left = this.leftIndex(i);
        int central = this.centralIndex(i);
        int right = this.rightIndex(i);
        if (left < this.size() && this.heap.get(left).getPriority() < this.heap.get(min).getPriority())
            min = left;
        if (central < this.size() && this.heap.get(central).getPriority() < this.heap.get(min).getPriority())
            min = central;
        if (right < this.size() && this.heap.get(right).getPriority() < this.heap.get(min).getPriority())
            min = right;
        if (min != i) {
            swap(i, min);
            this.heapify(min);
        }
    }

    //metodo privato che scambia i due elementi agli indici specificati
    //e aggiorna gli handle di entrambi
    private void swap(int i, int j) {
        PriorityQueueElement tmp = this.heap.get(i);
        this.heap.set(i, this.heap.get(j));
        this.heap.get(i).setHandle(i);
        this.heap.set(j, tmp);
        this.heap.get(j).setHandle(j);
    }

    /*
     * This method is only for JUnit testing purposes.
     */
    protected ArrayList<PriorityQueueElement> getTernaryHeap() {
        return this.heap;
    }

}
