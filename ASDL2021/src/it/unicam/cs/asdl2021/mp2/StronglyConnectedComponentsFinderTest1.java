package it.unicam.cs.asdl2021.mp2;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class StronglyConnectedComponentsFinderTest1 {

    StronglyConnectedComponentsFinder<String> finder = new StronglyConnectedComponentsFinder<>();

    MapAdjacentListDirectedGraph<String> adjMatrix = new MapAdjacentListDirectedGraph<>();

    @Test
    void findStronglyConnectedComponentsTest1() {
        this.adjMatrix.clear();

        GraphNode<String> n1 = new GraphNode<>("a");
        GraphNode<String> n2 = new GraphNode<>("b");
        GraphNode<String> n3 = new GraphNode<>("c");
        GraphNode<String> n4 = new GraphNode<>("d");

        GraphEdge<String> a1 = new GraphEdge<>(n1, n2, true);
        GraphEdge<String> a2 = new GraphEdge<>(n2, n3, true);
        GraphEdge<String> a3 = new GraphEdge<>(n3, n1, true);

        this.adjMatrix.addNode(n1);
        this.adjMatrix.addNode(n2);
        this.adjMatrix.addNode(n3);
        this.adjMatrix.addNode(n4);

        this.adjMatrix.addEdge(a1);
        this.adjMatrix.addEdge(a2);
        this.adjMatrix.addEdge(a3);

        Set<GraphNode<String>> SCC1 = new HashSet<>();
        SCC1.add(n1);
        SCC1.add(n2);
        SCC1.add(n3);

        Set<GraphNode<String>> SCC2 = new HashSet<>();
        SCC2.add(n4);

        Set<Set<GraphNode<String>>> dsfForest = finder.findStronglyConnectedComponents(adjMatrix);

        assertEquals(dsfForest.size(), 2);

        assertTrue(dsfForest.contains(SCC1));
        assertTrue(dsfForest.contains(SCC2));
    }

    @Test
    void findStronglyConnectedComponentsTest2() {
        this.adjMatrix.clear();

        GraphNode<String> n1 = new GraphNode<>("a");
        GraphNode<String> n2 = new GraphNode<>("b");
        GraphNode<String> n3 = new GraphNode<>("c");
        GraphNode<String> n4 = new GraphNode<>("d");
        GraphNode<String> n5 = new GraphNode<>("e");

        GraphEdge<String> a1 = new GraphEdge<>(n1, n2, true);
        GraphEdge<String> a2 = new GraphEdge<>(n2, n3, true);
        GraphEdge<String> a3 = new GraphEdge<>(n3, n1, true);
        GraphEdge<String> a4 = new GraphEdge<>(n4, n5, true);

        this.adjMatrix.addNode(n1);
        this.adjMatrix.addNode(n2);
        this.adjMatrix.addNode(n3);
        this.adjMatrix.addNode(n4);
        this.adjMatrix.addNode(n5);

        this.adjMatrix.addEdge(a1);
        this.adjMatrix.addEdge(a2);
        this.adjMatrix.addEdge(a3);
        this.adjMatrix.addEdge(a4);

        Set<GraphNode<String>> SCC1 = new HashSet<>();
        SCC1.add(n1);
        SCC1.add(n2);
        SCC1.add(n3);

        Set<GraphNode<String>> SCC2 = new HashSet<>();
        SCC2.add(n4);

        Set<GraphNode<String>> SCC3 = new HashSet<>();
        SCC3.add(n5);

        Set<Set<GraphNode<String>>> dsfForest = finder.findStronglyConnectedComponents(adjMatrix);

        assertEquals(dsfForest.size(), 3);

        assertTrue(dsfForest.contains(SCC1));
        assertTrue(dsfForest.contains(SCC2));
        assertTrue(dsfForest.contains(SCC3));
    }

    @Test
    void findStronglyConnectedComponentsTest3() {
        this.adjMatrix.clear();

        GraphNode<String> n1 = new GraphNode<>("a");
        GraphNode<String> n2 = new GraphNode<>("b");
        GraphNode<String> n3 = new GraphNode<>("c");
        GraphNode<String> n4 = new GraphNode<>("d");
        GraphNode<String> n5 = new GraphNode<>("e");
        GraphNode<String> n6 = new GraphNode<>("f");
        GraphNode<String> n7 = new GraphNode<>("g");

        GraphEdge<String> a1 = new GraphEdge<>(n1, n2, true);
        GraphEdge<String> a2 = new GraphEdge<>(n2, n3, true);
        GraphEdge<String> a3 = new GraphEdge<>(n3, n4, true);
        GraphEdge<String> a4 = new GraphEdge<>(n4, n1, true);
        GraphEdge<String> a5 = new GraphEdge<>(n2, n5, true);
        GraphEdge<String> a6 = new GraphEdge<>(n5, n6, true);
        GraphEdge<String> a7 = new GraphEdge<>(n6, n7, true);
        GraphEdge<String> a8 = new GraphEdge<>(n7, n5, true);

        this.adjMatrix.addNode(n1);
        this.adjMatrix.addNode(n2);
        this.adjMatrix.addNode(n3);
        this.adjMatrix.addNode(n4);
        this.adjMatrix.addNode(n5);
        this.adjMatrix.addNode(n6);
        this.adjMatrix.addNode(n7);

        this.adjMatrix.addEdge(a1);
        this.adjMatrix.addEdge(a2);
        this.adjMatrix.addEdge(a3);
        this.adjMatrix.addEdge(a4);
        this.adjMatrix.addEdge(a5);
        this.adjMatrix.addEdge(a6);
        this.adjMatrix.addEdge(a7);
        this.adjMatrix.addEdge(a8);

        Set<GraphNode<String>> SCC1 = new HashSet<>();
        SCC1.add(n1);
        SCC1.add(n2);
        SCC1.add(n3);
        SCC1.add(n4);

        Set<GraphNode<String>> SCC2 = new HashSet<>();
        SCC2.add(n5);
        SCC2.add(n6);
        SCC2.add(n7);

        Set<Set<GraphNode<String>>> dsfForest = finder.findStronglyConnectedComponents(adjMatrix);

        assertEquals(dsfForest.size(), 2);
        assertTrue(dsfForest.contains(SCC1));
        assertTrue(dsfForest.contains(SCC2));
    }

    @Test
    void findStronglyConnectedComponentsTest4() {
        this.adjMatrix.clear();

        GraphNode<String> n1 = new GraphNode<>("a");
        GraphNode<String> n2 = new GraphNode<>("b");
        GraphNode<String> n3 = new GraphNode<>("c");
        GraphNode<String> n4 = new GraphNode<>("d");
        GraphNode<String> n5 = new GraphNode<>("e");
        GraphNode<String> n6 = new GraphNode<>("f");
        GraphNode<String> n7 = new GraphNode<>("g");
        GraphNode<String> n8 = new GraphNode<>("h");

        GraphEdge<String> a1 = new GraphEdge<>(n1, n2, true);
        GraphEdge<String> a2 = new GraphEdge<>(n1, n8, true);
        GraphEdge<String> a3 = new GraphEdge<>(n2, n7, true);
        GraphEdge<String> a4 = new GraphEdge<>(n3, n2, true);
        GraphEdge<String> a5 = new GraphEdge<>(n3, n4, true);
        GraphEdge<String> a6 = new GraphEdge<>(n3, n6, true);
        GraphEdge<String> a7 = new GraphEdge<>(n4, n6, true);
        GraphEdge<String> a8 = new GraphEdge<>(n5, n4, true);
        GraphEdge<String> a9 = new GraphEdge<>(n6, n5, true);
        GraphEdge<String> a10 = new GraphEdge<>(n7, n3, true);
        GraphEdge<String> a11 = new GraphEdge<>(n7, n6, true);
        GraphEdge<String> a12 = new GraphEdge<>(n8, n1, true);
        GraphEdge<String> a13 = new GraphEdge<>(n8, n7, true);

        this.adjMatrix.addNode(n1);
        this.adjMatrix.addNode(n2);
        this.adjMatrix.addNode(n3);
        this.adjMatrix.addNode(n4);
        this.adjMatrix.addNode(n5);
        this.adjMatrix.addNode(n6);
        this.adjMatrix.addNode(n7);
        this.adjMatrix.addNode(n8);

        this.adjMatrix.addEdge(a1);
        this.adjMatrix.addEdge(a2);
        this.adjMatrix.addEdge(a3);
        this.adjMatrix.addEdge(a4);
        this.adjMatrix.addEdge(a5);
        this.adjMatrix.addEdge(a6);
        this.adjMatrix.addEdge(a7);
        this.adjMatrix.addEdge(a8);
        this.adjMatrix.addEdge(a9);
        this.adjMatrix.addEdge(a10);
        this.adjMatrix.addEdge(a11);
        this.adjMatrix.addEdge(a12);
        this.adjMatrix.addEdge(a13);

        Set<GraphNode<String>> SCC1 = new HashSet<>();
        SCC1.add(n1);
        SCC1.add(n8);

        Set<GraphNode<String>> SCC2 = new HashSet<>();
        SCC2.add(n2);
        SCC2.add(n3);
        SCC2.add(n7);

        Set<GraphNode<String>> SCC3 = new HashSet<>();
        SCC3.add(n4);
        SCC3.add(n5);
        SCC3.add(n6);

        Set<Set<GraphNode<String>>> dsfForest = finder.findStronglyConnectedComponents(adjMatrix);

        assertEquals(dsfForest.size(), 3);
        assertTrue(dsfForest.contains(SCC1));
        assertTrue(dsfForest.contains(SCC2));
        assertTrue(dsfForest.contains(SCC3));
    }
}