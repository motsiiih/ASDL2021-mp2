package it.unicam.cs.asdl2021.mp2;


import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

class AdjacencyMatrixUndirectedGraphTest {

    @Test
    final void testNodeCount() {
        Graph<String> g = new AdjacencyMatrixUndirectedGraph<String>();
        assertEquals(0, g.nodeCount());
        g.addNode(new GraphNode<String>("s"));
        assertEquals(1, g.nodeCount());
        g.addNode(new GraphNode<String>("u"));
        assertEquals(2, g.nodeCount());
    }

    @Test
    final void testEdgeCount() {
        Graph<String> g = new AdjacencyMatrixUndirectedGraph<String>();
        assertEquals(0, g.edgeCount());
        GraphNode<String> ns = new GraphNode<String>("s");
        g.addNode(ns);
        assertEquals(0, g.edgeCount());
        GraphNode<String> nu = new GraphNode<String>("u");
        g.addNode(nu);
        GraphEdge<String> esu = new GraphEdge<String>(ns, nu, false, 10.1);
        g.addEdge(esu);
        assertEquals(1, g.edgeCount());
        g.addEdge(esu);
        assertEquals(1, g.edgeCount());
        GraphNode<String> nx = new GraphNode<String>("x");
        g.addNode(nx);
        GraphEdge<String> esx = new GraphEdge<String>(ns, nx, false, 5.12);
        g.addEdge(esx);
        assertEquals(2, g.edgeCount());
    }

    @Test
    final void testClear() {
        Graph<String> g = new AdjacencyMatrixUndirectedGraph<String>();
        assertTrue(g.isEmpty());
        GraphNode<String> ns = new GraphNode<String>("s");
        g.addNode(ns);
        assertFalse(g.isEmpty());
        GraphNode<String> nu = new GraphNode<String>("u");
        g.addNode(nu);
        GraphEdge<String> esu = new GraphEdge<String>(ns, nu, false, 10.1);
        g.addEdge(esu);
        assertFalse(g.isEmpty());
        g.clear();
        assertTrue(g.isEmpty());
    }

    @Test
    final void testIsDirected() {
        Graph<String> g = new AdjacencyMatrixUndirectedGraph<String>();
        assertFalse(g.isDirected());
    }

    @Test
    final void testGetNodes() {
        Graph<String> g = new AdjacencyMatrixUndirectedGraph<String>();
        Set<GraphNode<String>> nodes = g.getNodes();
        assertTrue(nodes.isEmpty());
        GraphNode<String> ns = new GraphNode<String>("s");
        g.addNode(ns);
        GraphNode<String> nu = new GraphNode<String>("u");
        g.addNode(nu);
        nodes = g.getNodes();
        Set<GraphNode<String>> testNodes = new HashSet<GraphNode<String>>();
        GraphNode<String> nsTest = new GraphNode<String>("s");
        GraphNode<String> nuTest = new GraphNode<String>("u");
        testNodes.add(nuTest);
        testNodes.add(nsTest);
        assertTrue(nodes.equals(testNodes));
        GraphNode<String> nuTestBis = new GraphNode<String>("u");
        g.addNode(nuTestBis);
        nodes = g.getNodes();
        assertTrue(nodes.equals(testNodes));
    }

    @Test
    final void testAddNode() {
        Graph<String> g = new AdjacencyMatrixUndirectedGraph<String>();
        assertThrows(NullPointerException.class, () -> g.addNode(null));
        GraphNode<String> ns = new GraphNode<String>("s");
        GraphNode<String> nsTest = new GraphNode<String>("s");
        assertFalse(g.containsNode(ns));
        g.addNode(ns);
        assertTrue(g.containsNode(nsTest));
        GraphNode<String> nu = new GraphNode<String>("u");
        GraphNode<String> nuTest = new GraphNode<String>("u");
        g.addNode(nu);
        assertTrue(g.containsNode(nuTest));
    }

    @Test
    final void testContainsNode() {
        Graph<String> g = new AdjacencyMatrixUndirectedGraph<String>();
        assertThrows(NullPointerException.class, () -> g.containsNode(null));
        GraphNode<String> ns = new GraphNode<String>("s");
        GraphNode<String> nsTest = new GraphNode<String>("s");
        assertFalse(g.containsNode(nsTest));
        g.addNode(ns);
        assertTrue(g.containsNode(nsTest));
    }

    @Test
    final void testGetNodeOf() {
        Graph<String> g = new AdjacencyMatrixUndirectedGraph<String>();
        assertThrows(NullPointerException.class, () -> g.getNodeOf(null));
        GraphNode<String> ns = new GraphNode<String>("s");
        ns.setColor(1);
        g.addNode(ns);
        GraphNode<String> nu = new GraphNode<String>("u");
        g.addNode(nu);
        GraphNode<String> node = g.getNodeOf("s");
        assertEquals("s", node.getLabel());
        assertEquals(1, node.getColor());
        node = g.getNodeOf("u");
        assertEquals("u", node.getLabel());
        assertEquals(0, node.getColor());
        assertTrue(g.getNodeOf("p") == null);
    }

    @Test
    final void testGetNodeIndexOf() {
        Graph<String> g = new AdjacencyMatrixUndirectedGraph<String>();
        assertThrows(NullPointerException.class, () -> g.getNodeIndexOf(null));
        GraphNode<String> ns = new GraphNode<String>("s");
        ns.setColor(1);
        g.addNode(ns);
        assertTrue(g.getNodeIndexOf("s") == 0);
        assertThrows(IllegalArgumentException.class,
                () -> g.getNodeIndexOf("u"));
        GraphNode<String> nu = new GraphNode<String>("u");
        g.addNode(nu);
        assertTrue(g.getNodeIndexOf("u") == 1);
        assertTrue(g.getNodeIndexOf("s") == 0);
    }

    @Test
    final void testGetNodeAtIndex() {
        Graph<String> g = new AdjacencyMatrixUndirectedGraph<String>();
        assertThrows(IndexOutOfBoundsException.class,
                () -> g.getNodeAtIndex(0));
        GraphNode<String> ns = new GraphNode<String>("s");
        ns.setColor(1);
        g.addNode(ns);
        assertThrows(IndexOutOfBoundsException.class,
                () -> g.getNodeAtIndex(1));
        GraphNode<String> nsTest = new GraphNode<String>("s");
        assertTrue(nsTest.equals(g.getNodeAtIndex(0)));
        assertTrue(g.getNodeAtIndex(0).getColor() == 1);
        GraphNode<String> nu = new GraphNode<String>("u");
        g.addNode(nu);
        assertThrows(IndexOutOfBoundsException.class,
                () -> g.getNodeAtIndex(2));
        GraphNode<String> nuTest = new GraphNode<String>("u");
        assertTrue(nuTest.equals(g.getNodeAtIndex(1)));
    }

    @Test
    final void testGetAdjacentNodesOf() {
        Graph<String> g = new AdjacencyMatrixUndirectedGraph<String>();
        assertThrows(NullPointerException.class,
                () -> g.getAdjacentNodesOf(null));
        GraphNode<String> ns = new GraphNode<String>("s");
        g.addNode(ns);
        Set<GraphNode<String>> adjNodes = new HashSet<GraphNode<String>>();
        assertTrue(g.getAdjacentNodesOf(ns).equals(adjNodes));
        GraphNode<String> nsTest = new GraphNode<String>("s");
        GraphNode<String> nu = new GraphNode<String>("u");
        GraphNode<String> nuTest = new GraphNode<String>("u");
        assertThrows(IllegalArgumentException.class,
                () -> g.getAdjacentNodesOf(nu));
        g.addNode(nu);
        GraphEdge<String> esu = new GraphEdge<String>(ns, nu, false, 10.1);
        g.addEdge(esu);
        GraphNode<String> nx = new GraphNode<String>("x");
        GraphNode<String> nxTest = new GraphNode<String>("x");
        g.addNode(nx);
        GraphEdge<String> esx = new GraphEdge<String>(ns, nx, false, 5.12);
        g.addEdge(esx);
        adjNodes.add(nxTest);
        adjNodes.add(nuTest);
        assertTrue(g.getAdjacentNodesOf(nsTest).equals(adjNodes));
        adjNodes.clear();
        adjNodes.add(nsTest);
        assertTrue(g.getAdjacentNodesOf(nxTest).equals(adjNodes));
        assertTrue(g.getAdjacentNodesOf(nuTest).equals(adjNodes));
        GraphNode<String> np = new GraphNode<String>("p");
        GraphNode<String> npTest = new GraphNode<String>("p");
        g.addNode(np);
        adjNodes.clear();
        assertTrue(g.getAdjacentNodesOf(npTest).equals(adjNodes));
    }

    @Test
    final void testGetEdges() {
        Graph<String> g = new AdjacencyMatrixUndirectedGraph<String>();
        GraphNode<String> ns = new GraphNode<String>("s");
        g.addNode(ns);
        Set<GraphEdge<String>> edgesTest = new HashSet<GraphEdge<String>>();
        assertTrue(g.getEdges().equals(edgesTest));
        GraphNode<String> nu = new GraphNode<String>("u");
        g.addNode(nu);
        GraphEdge<String> esu = new GraphEdge<String>(ns, nu, false);
        g.addEdge(esu);
        GraphEdge<String> esuTest = new GraphEdge<String>(nu, ns, false);
        edgesTest.add(esuTest);
        assertTrue(g.getEdges().equals(edgesTest));
        GraphNode<String> nx = new GraphNode<String>("x");
        g.addNode(nx);
        GraphEdge<String> esx = new GraphEdge<String>(ns, nx, false, 5.12);
        g.addEdge(esx);
        GraphEdge<String> eux = new GraphEdge<String>(nu, nx, false, 2.05);
        g.addEdge(eux);
        GraphEdge<String> exu = new GraphEdge<String>(nx, nu, false, 3.04);
        g.addEdge(exu);
        edgesTest.add(eux);
        edgesTest.add(esx);
        edgesTest.add(exu);
        assertTrue(g.getEdges().equals(edgesTest));
        g.clear();
        edgesTest.clear();
        assertTrue(g.getEdges().equals(edgesTest));
    }

    @Test
    final void testAddEdge() {
        Graph<String> g = new AdjacencyMatrixUndirectedGraph<String>();
        assertThrows(NullPointerException.class, () -> g.addEdge(null));
        GraphNode<String> ns = new GraphNode<String>("s");
        g.addNode(ns);
        GraphNode<String> nu = new GraphNode<String>("u");
        assertThrows(IllegalArgumentException.class,
                () -> g.addEdge(new GraphEdge<String>(ns, nu, false)));
        assertThrows(IllegalArgumentException.class,
                () -> g.addEdge(new GraphEdge<String>(nu, ns, false)));
        g.addNode(nu);
        assertThrows(IllegalArgumentException.class,
                () -> g.addEdge(new GraphEdge<String>(ns, nu, true)));
        GraphEdge<String> esu = new GraphEdge<String>(ns, nu, false);
        assertTrue(g.addEdge(esu));
        assertTrue(g.containsEdge(new GraphEdge<String>(ns, nu, false)));
        assertFalse(g.addEdge(new GraphEdge<String>(nu, ns, false, 6.0)));
    }

    @Test
    final void testContainsEdge() {
        Graph<String> g = new AdjacencyMatrixUndirectedGraph<String>();
        assertThrows(NullPointerException.class,
                () -> g.containsEdge(null));
        GraphNode<String> ns = new GraphNode<String>("s");
        g.addNode(ns);
        GraphNode<String> nu = new GraphNode<String>("u");
        assertThrows(IllegalArgumentException.class,
                () -> g.containsEdge(new GraphEdge<String>(ns, nu, false)));
        assertThrows(IllegalArgumentException.class,
                () -> g.containsEdge(new GraphEdge<String>(nu, ns, false)));
        g.addNode(nu);
        GraphEdge<String> esu = new GraphEdge<String>(ns, nu, false);
        assertFalse(g.containsEdge(new GraphEdge<String>(ns, nu, false)));
        g.addEdge(esu);
        assertTrue(g.containsEdge(new GraphEdge<String>(ns, nu, false)));
    }

    @Test
    final void testGetEdgesOf() {
        Graph<String> g = new AdjacencyMatrixUndirectedGraph<String>();
        GraphNode<String> ns = new GraphNode<String>("s");
        g.addNode(ns);
        Set<GraphEdge<String>> edgesTest = new HashSet<GraphEdge<String>>();
        assertThrows(NullPointerException.class,
                () -> g.getEdgesOf(null));
        GraphNode<String> nu = new GraphNode<String>("u");
        assertThrows(IllegalArgumentException.class,
                () -> g.getEdgesOf(nu));
        g.addNode(nu);
        GraphEdge<String> esu = new GraphEdge<String>(ns, nu, false);
        g.addEdge(esu);
        GraphNode<String> nx = new GraphNode<String>("x");
        g.addNode(nx);
        GraphEdge<String> esx = new GraphEdge<String>(ns, nx, false, 5.12);
        g.addEdge(esx);
        GraphEdge<String> eux = new GraphEdge<String>(nu, nx, false, 2.05);
        g.addEdge(eux);
        GraphNode<String> ny = new GraphNode<String>("y");
        g.addNode(ny);
        GraphEdge<String> exy = new GraphEdge<String>(nx, ny, false, 2.0);
        g.addEdge(exy);
        GraphEdge<String> eys = new GraphEdge<String>(ny, ns, false, 7.03);
        g.addEdge(eys);
        GraphNode<String> nw = new GraphNode<String>("w");
        g.addNode(nw);
        edgesTest.add(esu);
        edgesTest.add(esx);
        edgesTest.add(eys);
        assertTrue(g.getEdgesOf(ns).equals(edgesTest));
        edgesTest.clear();
        edgesTest.add(eux);
        edgesTest.add(exy);
        edgesTest.add(new GraphEdge<String>(nx, ns, false));
        assertTrue(g.getEdgesOf(nx).equals(edgesTest));
        edgesTest.clear();
        assertTrue(g.getEdgesOf(nw).equals(edgesTest));
    }

    @Test
    final void testAdjacencyMatrixUndirectedGraph() {
        Graph<String> g = new AdjacencyMatrixUndirectedGraph<String>();
        assertTrue(g.isEmpty());
    }

    @Test
    final void testSize() {
        Graph<String> g = new AdjacencyMatrixUndirectedGraph<String>();
        assertTrue(g.size() == 0);
        GraphNode<String> ns = new GraphNode<String>("s");
        g.addNode(ns);
        assertTrue(g.size() == 1);
        GraphNode<String> nu = new GraphNode<String>("u");
        g.addNode(nu);
        assertTrue(g.size() == 2);
        GraphEdge<String> esu = new GraphEdge<String>(ns, nu, false);
        g.addEdge(esu);
        assertTrue(g.size() == 3);
        GraphNode<String> nx = new GraphNode<String>("x");
        g.addNode(nx);
        assertTrue(g.size() == 4);
        GraphEdge<String> esx = new GraphEdge<String>(ns, nx, false, 5.12);
        g.addEdge(esx);
        assertTrue(g.size() == 5);
        GraphEdge<String> eux = new GraphEdge<String>(nu, nx, false, 2.05);
        g.addEdge(eux);
        assertTrue(g.size() == 6);
        g.addEdge(new GraphEdge<String>(nx, nu, false, 2.05));
        assertTrue(g.size() == 6);
        g.clear();
        assertTrue(g.size() == 0);
    }

    @Test
    final void testIsEmpty() {
        Graph<String> g = new AdjacencyMatrixUndirectedGraph<String>();
        assertTrue(g.isEmpty());
        GraphNode<String> ns = new GraphNode<String>("s");
        g.addNode(ns);
        assertFalse(g.isEmpty());
        g.clear();
        assertTrue(g.isEmpty());
    }

    @Test
    final void testGetDegreeOf() {
        Graph<String> g = new AdjacencyMatrixUndirectedGraph<String>();
        GraphNode<String> ns = new GraphNode<String>("s");
        g.addNode(ns);
        assertTrue(g.getDegreeOf(ns) == 0);
        assertThrows(NullPointerException.class,
                () -> g.getDegreeOf(null));
        GraphNode<String> nu = new GraphNode<String>("u");
        assertThrows(IllegalArgumentException.class,
                () -> g.getDegreeOf(nu));
        g.addNode(nu);
        GraphEdge<String> esu = new GraphEdge<String>(ns, nu, false);
        g.addEdge(esu);
        GraphNode<String> nx = new GraphNode<String>("x");
        g.addNode(nx);
        GraphEdge<String> esx = new GraphEdge<String>(ns, nx, false, 5.12);
        g.addEdge(esx);
        GraphEdge<String> exu = new GraphEdge<String>(nx, nu, false, 3.04);
        g.addEdge(exu);
        GraphNode<String> ny = new GraphNode<String>("y");
        g.addNode(ny);
        GraphEdge<String> exy = new GraphEdge<String>(nx, ny, false, 2.0);
        g.addEdge(exy);
        GraphEdge<String> eys = new GraphEdge<String>(ny, ns, false, 7.03);
        g.addEdge(eys);
        GraphNode<String> nw = new GraphNode<String>("w");
        g.addNode(nw);
        GraphEdge<String> euw = new GraphEdge<String>(nu, nw, false, 7.07);
        g.addEdge(euw);
        GraphNode<String> nz = new GraphNode<String>("z");
        g.addNode(nz);
        GraphEdge<String> ezy = new GraphEdge<String>(nz, ny, false, 7.107);
        g.addEdge(ezy);
        assertTrue(g.getDegreeOf(ns)==3);
        assertTrue(g.getDegreeOf(nu)==3);
        assertTrue(g.getDegreeOf(nx)==3);
        assertTrue(g.getDegreeOf(ny)==3);
        assertTrue(g.getDegreeOf(nz)==1);
        assertTrue(g.getDegreeOf(nw)==1);
    }

}
