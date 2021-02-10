package it.unicam.cs.asdl2021.mp2;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class AdjacencyMatrixUndirectedGraphTest2 {

    @Test
    void nodeCount() {
    }

    @Test
    void edgeCount() {
        Graph<String> g = new AdjacencyMatrixUndirectedGraph<>();
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
    void getNodes() {
    }

    @Test
    void addNode() {
        Graph<String> g = new AdjacencyMatrixUndirectedGraph<>();
        assertThrows(NullPointerException.class, () -> g.addNode(null));
        GraphNode<String> ns = new GraphNode<String>("s");
        GraphNode<String> nsTest = new GraphNode<String>("s");
        assertFalse(g.containsNode(ns));
        g.addNode(ns);
        g.addNode(ns);
        assertTrue(g.nodeCount() == 1);
        assertTrue(g.containsNode(nsTest));
        GraphNode<String> nu = new GraphNode<String>("u");
        GraphNode<String> nuTest = new GraphNode<String>("u");
        g.addNode(nu);
        assertTrue(g.nodeCount() == 2);
        assertTrue(g.containsNode(nuTest));
        GraphNode<String> nf = new GraphNode<String>("d");
        GraphNode<String> nfTest = new GraphNode<String>("d");
        g.addNode(nf);
        assertTrue(g.nodeCount() == 3);
        assertTrue(g.edgeCount() == 0);
        assertTrue(g.containsNode(nfTest));
        g.clear();
        assertTrue(g.edgeCount() == 0);
        assertTrue(g.nodeCount() == 0);
        GraphNode<String> nw = new GraphNode<String>("w");
        GraphNode<String> nwTest = new GraphNode<String>("w");
        g.addNode(nw);
        assertTrue(g.nodeCount() == 1);
        assertTrue(g.containsNode(nwTest));
        GraphNode<String> nv = new GraphNode<String>("v");
        GraphNode<String> nvTest = new GraphNode<String>("v");
        g.addNode(nv);
        assertTrue(g.nodeCount() == 2);
        assertTrue(g.containsNode(nvTest));
        GraphNode<String> nb = new GraphNode<String>("b");
        GraphNode<String> nbTest = new GraphNode<String>("b");
        g.addNode(nb);
        assertTrue(g.nodeCount() == 3);
        assertTrue(g.containsNode(nbTest));
        GraphNode<String> ng = new GraphNode<String>("g");
        GraphNode<String> ngTest = new GraphNode<String>("g");
        GraphNode<String> nh = new GraphNode<String>("h");
        GraphNode<String> nhTest = new GraphNode<String>("h");
        g.addNode(ng);
        g.addNode(nh);
        assertTrue(g.nodeCount() == 5);
        assertTrue(g.containsNode(ngTest));
        assertTrue(g.containsNode(nhTest));
        GraphNode<String> nz = new GraphNode<String>("z");
        GraphNode<String> nzTest = new GraphNode<String>("z");
        GraphNode<String> nx = new GraphNode<String>("x");
        GraphNode<String> nxTest = new GraphNode<String>("x");
        GraphNode<String> nc = new GraphNode<String>("c");
        GraphNode<String> ncTest = new GraphNode<String>("c");
        GraphNode<String> nnernoyz = new GraphNode<String>("noyz");
        GraphNode<String> nnernoyzTest = new GraphNode<String>("noyz");
        GraphNode<String> ny = new GraphNode<String>("y");
        GraphNode<String> nyTest = new GraphNode<String>("y");
        GraphNode<String> nt = new GraphNode<String>("t");
        GraphNode<String> ntTest = new GraphNode<String>("t");
        GraphNode<String> nk = new GraphNode<String>("k");
        GraphNode<String> nkTest = new GraphNode<String>("k");
        GraphNode<String> nm = new GraphNode<String>("m");
        GraphNode<String> nmTest = new GraphNode<String>("m");
        GraphNode<String> nl = new GraphNode<String>("l");
        GraphNode<String> nlTest = new GraphNode<String>("l");
        GraphNode<String> ni = new GraphNode<String>("i");
        GraphNode<String> niTest = new GraphNode<String>("i");
        g.addNode(nz);
        g.addNode(nx);
        g.addNode(nc);
        g.addNode(nnernoyz);
        g.addNode(ny);
        g.addNode(nt);
        g.addNode(nk);
        g.addNode(nm);
        g.addNode(nl);
        g.addNode(ni);
        g.addNode(nx);
        assertTrue(g.nodeCount() == 15);
        assertTrue(g.containsNode(nzTest));
        assertTrue(g.containsNode(nxTest));
        assertTrue(g.containsNode(ncTest));
        assertTrue(g.containsNode(nnernoyzTest));
        assertTrue(g.containsNode(nyTest));
        assertTrue(g.containsNode(ntTest));
        assertTrue(g.containsNode(nkTest));
        assertTrue(g.containsNode(nmTest));
        assertTrue(g.containsNode(nlTest));
        assertTrue(g.containsNode(niTest));
        assertTrue(g.edgeCount() == 0);
    }

    @Test
    void containsNode() {
    }

    @Test
    void getNodeOf() {
    }

    @Test
    void getNodeIndexOf() {
    }

    @Test
    void getNodeAtIndex() {
    }

    @Test
    void getAdjacentNodesOf() {
        Graph<String> g = new AdjacencyMatrixUndirectedGraph<>();
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
        assertEquals(adjNodes, g.getAdjacentNodesOf(nsTest));
    }

    @Test
    void getEdges() {
        Graph<String> g = new AdjacencyMatrixUndirectedGraph<>();
        GraphNode<String> ns = new GraphNode<String>("s");
        g.addNode(ns);
        Set<GraphEdge<String>> edgesTest = new HashSet<GraphEdge<String>>();
        assertEquals(true, g.getEdges().equals(edgesTest));
        GraphNode<String> nu = new GraphNode<String>("u");
        g.addNode(nu);
        GraphEdge<String> esu = new GraphEdge<String>(ns,
                nu, false, 10.1);
        g.addEdge(esu);
        edgesTest.add(esu);
        assertTrue(g.getEdges().equals(edgesTest));
        assertEquals(true, g.getEdges().equals(edgesTest));
        GraphNode<String> nx = new GraphNode<String>("x");
        g.addNode(nx);
        GraphEdge<String> esx = new GraphEdge<String>(ns,
                nx, false, 5.12);
        g.addEdge(esx);
        GraphEdge<String> eux = new GraphEdge<String>(nu,
                nx, false, 2.05);
        g.addEdge(eux);
        GraphEdge<String> exu = new GraphEdge<String>(nx,
                nu, false, 3.04);
        g.addEdge(exu);
        edgesTest.add(eux);
        edgesTest.add(esx);
        edgesTest.add(exu);
        assertEquals(true, g.getEdges().equals(edgesTest));
        GraphNode<String> ny = new GraphNode<String>("y");
        g.addNode(ny);
        GraphEdge<String> exy = new GraphEdge<String>(nx,
                ny, false, 2.0);
        g.addEdge(exy);
        GraphEdge<String> eys = new GraphEdge<String>(ny,
                ns, false, 7.03);
        g.addEdge(eys);
        edgesTest.add(eys);
        edgesTest.add(exy);
        assertEquals(true, g.getEdges().equals(edgesTest));
    }

    @Test
    void addEdge() {
        Graph<String> g = new AdjacencyMatrixUndirectedGraph<>();
        assertThrows(NullPointerException.class,
                () -> g.addEdge(null));
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
        GraphEdge<String> e01 = new GraphEdge<String>(ns, nu, false);
        g.addEdge(e01);
        GraphNode<String> na = new GraphNode<String>("a");
        g.addNode(na);
        GraphNode<String> nb = new GraphNode<String>("b");
        GraphNode<String> nc = new GraphNode<String>("c");
        g.addNode(nb);
        g.addNode(nc);
        GraphNode<String> nd = new GraphNode<String>("d");
        g.addNode(nd);
        assertTrue(g.nodeCount() == 6);
        GraphEdge<String> e03 = new GraphEdge<String>(ns, nb, false);
        g.addEdge(e03);
        GraphEdge<String> e02 = new GraphEdge<String>(ns, na, false);
        g.addEdge(e02);
        GraphEdge<String> e11 = new GraphEdge<String>(nu, nu, false);
        g.addEdge(e11);
        GraphEdge<String> e00 = new GraphEdge<String>(ns, ns, false);
        g.addEdge(e00);
        GraphEdge<String> e22 = new GraphEdge<String>(na, na, false);
        g.addEdge(e22);
        GraphEdge<String> e12 = new GraphEdge<String>(nu, na, false);
        g.addEdge(e12);
        GraphEdge<String> e24 = new GraphEdge<String>(na, nc, false);
        g.addEdge(e24);
        GraphEdge<String> e43 = new GraphEdge<String>(nb, nc, false);
        g.addEdge(e43);
        GraphEdge<String> e51 = new GraphEdge<String>(nd, nu, false);
        g.addEdge(e51);
        GraphEdge<String> e52 = new GraphEdge<String>(nd, na, false);
        g.addEdge(e52);
        GraphEdge<String> e53 = new GraphEdge<String>(nd, nb, false);
        g.addEdge(e53);
        assertFalse(g.addEdge(e52));
        assertTrue(g.containsEdge(new GraphEdge<String>(ns, nu, false)));
        assertTrue(g.containsEdge(e01));
        assertTrue(g.containsEdge(new GraphEdge<String>(ns, nb, false)));
        assertTrue(g.containsEdge(new GraphEdge<String>(ns, na, false)));
        assertTrue(g.containsEdge(new GraphEdge<String>(nu, nu, false)));
        assertTrue(g.containsEdge(new GraphEdge<String>(nu, na, false)));
        assertTrue(g.containsEdge(new GraphEdge<String>(na, nc, false)));
        assertTrue(g.containsEdge(new GraphEdge<String>(nb, nc, false)));
        assertTrue(g.containsEdge(new GraphEdge<String>(nd, nu, false)));
        assertTrue(g.containsEdge(new GraphEdge<String>(nd, na, false)));
        assertTrue(g.containsEdge(new GraphEdge<String>(nd, nb, false)));
        assertTrue(g.containsEdge(new GraphEdge<String>(ns, ns, false)));
        assertTrue(g.containsEdge(new GraphEdge<String>(na, na, false)));
        assertTrue(g.edgeCount() == 12);
    }

    @Test
    void containsEdge() {
        Graph<String> g = new AdjacencyMatrixUndirectedGraph<>();
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
    void getEdgesOf() {
        Graph<String> g = new AdjacencyMatrixUndirectedGraph<>();
        assertThrows(NullPointerException.class, () -> g.addNode(null));
        GraphNode<String> ns = new GraphNode<String>("s");
        GraphNode<String> nsTest = new GraphNode<String>("s");
        assertFalse(g.containsNode(ns));
        g.addNode(ns);
        assertTrue(g.nodeCount() == 1);
        assertTrue(g.containsNode(nsTest));
        GraphNode<String> nu = new GraphNode<String>("u");
        GraphNode<String> nuTest = new GraphNode<String>("u");
        g.addNode(nu);
        assertTrue(g.nodeCount() == 2);
        GraphEdge<String> ed = new GraphEdge<String>(ns, nu, false);
        assertTrue(g.getEdgesOf(ns).size() == 0);
        assertTrue(g.getEdgesOf(nu).size() == 0);
        g.addEdge(ed);
        assertEquals(g.getEdgesOf(ns).size(), g.edgeCount());
        assertTrue(g.getEdgesOf(nu).contains(ed));
        assertTrue(g.getEdgesOf(ns).contains(ed));
        assertTrue(g.containsNode(nuTest));
        GraphNode<String> nf = new GraphNode<String>("d");
        GraphNode<String> nfTest = new GraphNode<String>("d");
        g.addNode(nf);
        assertTrue(g.nodeCount() == 3);
        GraphEdge<String> ed1 = new GraphEdge<String>(ns, nf, false);
        g.addEdge(ed1);
        GraphEdge<String> ed2 = new GraphEdge<String>(nf, nf, false);
        g.addEdge(ed2);
        GraphEdge<String> ed3 = new GraphEdge<String>(nf, nu, false);
        g.addEdge(ed3);
        assertTrue(g.getEdgesOf(ns).contains(ed1));
        assertTrue(g.getEdgesOf(ns).size() == 2);
        assertTrue(g.getEdgesOf(nf).contains(ed1));
        assertTrue(g.getEdgesOf(nf).contains(ed2));
        assertTrue(g.getEdgesOf(nu).contains(ed));
        assertTrue(g.getEdgesOf(nu).contains(ed3));
        assertTrue(g.getEdgesOf(nf).contains(ed3));
        assertTrue(g.getEdgesOf(ns).size() == 2);
        assertTrue(g.getEdgesOf(nu).size() == 2);
        assertTrue(g.getEdgesOf(nf).size() == 3);
        assertTrue(g.edgeCount() == 4);
        assertTrue(g.containsNode(nfTest));
    }
}