package it.unicam.cs.asdl2021.mp2;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

class StronglyConnectedComponentsFinderTest {

    @Test
    final void testExceptions() {
        StronglyConnectedComponentsFinder<String> alg = new StronglyConnectedComponentsFinder<String>();
        assertThrows(NullPointerException.class,
                () -> alg.findStronglyConnectedComponents(null));
        Graph<String> gr = new AdjacencyMatrixUndirectedGraph<String>();
        assertThrows(IllegalArgumentException.class,
                () -> alg.findStronglyConnectedComponents(gr));
    }

    @Test
    final void testFindStronglyConnectedComponents1() {
        Graph<String> g = new MapAdjacentListDirectedGraph<String>();
        GraphNode<String> n1 = new GraphNode<String>("1");
        g.addNode(n1);
        GraphNode<String> n2 = new GraphNode<String>("2");
        g.addNode(n2);
        GraphNode<String> n3 = new GraphNode<String>("3");
        g.addNode(n3);
        GraphNode<String> n4 = new GraphNode<String>("4");
        g.addNode(n4);
        GraphNode<String> n5 = new GraphNode<String>("5");
        g.addNode(n5);
        GraphNode<String> n6 = new GraphNode<String>("6");
        g.addNode(n6);
        GraphEdge<String> e12 = new GraphEdge<String>(n1, n2, true);
        g.addEdge(e12);
        GraphEdge<String> e22 = new GraphEdge<String>(n2, n2, true);
        g.addEdge(e22);
        GraphEdge<String> e24 = new GraphEdge<String>(n2, n4, true);
        g.addEdge(e24);
        GraphEdge<String> e41 = new GraphEdge<String>(n4, n1, true);
        g.addEdge(e41);
        GraphEdge<String> e25 = new GraphEdge<String>(n2, n5, true);
        g.addEdge(e25);
        GraphEdge<String> e45 = new GraphEdge<String>(n4, n5, true);
        g.addEdge(e45);
        GraphEdge<String> e54 = new GraphEdge<String>(n5, n4, true);
        g.addEdge(e54);
        GraphEdge<String> e63 = new GraphEdge<String>(n6, n3, true);
        g.addEdge(e63);
        Set<GraphNode<String>> component1 = new HashSet<GraphNode<String>>();
        component1.add(n1);
        component1.add(n2);
        component1.add(n4);
        component1.add(n5);
        Set<GraphNode<String>> component2 = new HashSet<GraphNode<String>>();
        component2.add(n3);
        Set<GraphNode<String>> component3 = new HashSet<GraphNode<String>>();
        component3.add(n6);
        Set<Set<GraphNode<String>>> stronglyConnectedComponents = new HashSet<Set<GraphNode<String>>>();
        stronglyConnectedComponents.add(component1);
        stronglyConnectedComponents.add(component2);
        stronglyConnectedComponents.add(component3);
        StronglyConnectedComponentsFinder<String> f = new StronglyConnectedComponentsFinder<String>();
        assertTrue(f.findStronglyConnectedComponents(g)
                .equals(stronglyConnectedComponents));
    }

    @Test
    final void testFindStronglyConnectedComponents2() {
        Graph<String> g = new MapAdjacentListDirectedGraph<String>();
        GraphNode<String> n1 = new GraphNode<String>("1");
        g.addNode(n1);
        GraphNode<String> n2 = new GraphNode<String>("2");
        g.addNode(n2);
        GraphNode<String> n3 = new GraphNode<String>("3");
        g.addNode(n3);
        GraphNode<String> n4 = new GraphNode<String>("4");
        g.addNode(n4);
        GraphNode<String> n5 = new GraphNode<String>("5");
        g.addNode(n5);
        GraphNode<String> n6 = new GraphNode<String>("6");
        g.addNode(n6);
        GraphEdge<String> e12 = new GraphEdge<String>(n1, n2, true);
        g.addEdge(e12);
        GraphEdge<String> e22 = new GraphEdge<String>(n2, n2, true);
        g.addEdge(e22);
        GraphEdge<String> e24 = new GraphEdge<String>(n2, n4, true);
        g.addEdge(e24);
        GraphEdge<String> e41 = new GraphEdge<String>(n4, n1, true);
        g.addEdge(e41);
        GraphEdge<String> e25 = new GraphEdge<String>(n2, n5, true);
        g.addEdge(e25);
        GraphEdge<String> e45 = new GraphEdge<String>(n4, n5, true);
        g.addEdge(e45);
        GraphEdge<String> e54 = new GraphEdge<String>(n5, n4, true);
        g.addEdge(e54);
        GraphEdge<String> e63 = new GraphEdge<String>(n6, n3, true);
        g.addEdge(e63);
        GraphEdge<String> e26 = new GraphEdge<String>(n2, n6, true);
        g.addEdge(e26);
        Set<GraphNode<String>> component1 = new HashSet<GraphNode<String>>();
        component1.add(n1);
        component1.add(n2);
        component1.add(n4);
        component1.add(n5);
        Set<GraphNode<String>> component2 = new HashSet<GraphNode<String>>();
        component2.add(n3);
        Set<GraphNode<String>> component3 = new HashSet<GraphNode<String>>();
        component3.add(n6);
        Set<Set<GraphNode<String>>> stronglyConnectedComponents = new HashSet<Set<GraphNode<String>>>();
        stronglyConnectedComponents.add(component1);
        stronglyConnectedComponents.add(component2);
        stronglyConnectedComponents.add(component3);
        StronglyConnectedComponentsFinder<String> f = new StronglyConnectedComponentsFinder<String>();
        assertTrue(f.findStronglyConnectedComponents(g)
                .equals(stronglyConnectedComponents));
    }

    @Test
    final void testFindStronglyConnectedComponents3() {
        Graph<String> g = new MapAdjacentListDirectedGraph<String>();
        GraphNode<String> n1 = new GraphNode<String>("1");
        g.addNode(n1);
        GraphNode<String> n2 = new GraphNode<String>("2");
        g.addNode(n2);
        GraphNode<String> n3 = new GraphNode<String>("3");
        g.addNode(n3);
        GraphNode<String> n4 = new GraphNode<String>("4");
        g.addNode(n4);
        GraphNode<String> n5 = new GraphNode<String>("5");
        g.addNode(n5);
        GraphNode<String> n6 = new GraphNode<String>("6");
        g.addNode(n6);
        GraphEdge<String> e12 = new GraphEdge<String>(n1, n2, true);
        g.addEdge(e12);
        GraphEdge<String> e22 = new GraphEdge<String>(n2, n2, true);
        g.addEdge(e22);
        GraphEdge<String> e24 = new GraphEdge<String>(n2, n4, true);
        g.addEdge(e24);
        GraphEdge<String> e41 = new GraphEdge<String>(n4, n1, true);
        g.addEdge(e41);
        GraphEdge<String> e25 = new GraphEdge<String>(n2, n5, true);
        g.addEdge(e25);
        GraphEdge<String> e45 = new GraphEdge<String>(n4, n5, true);
        g.addEdge(e45);
        GraphEdge<String> e54 = new GraphEdge<String>(n5, n4, true);
        g.addEdge(e54);
        GraphEdge<String> e63 = new GraphEdge<String>(n6, n3, true);
        g.addEdge(e63);
        GraphEdge<String> e36 = new GraphEdge<String>(n3, n6, true);
        g.addEdge(e36);
        Set<GraphNode<String>> component1 = new HashSet<GraphNode<String>>();
        component1.add(n1);
        component1.add(n2);
        component1.add(n4);
        component1.add(n5);
        Set<GraphNode<String>> component2 = new HashSet<GraphNode<String>>();
        component2.add(n3);
        component2.add(n6);
        Set<Set<GraphNode<String>>> stronglyConnectedComponents = new HashSet<Set<GraphNode<String>>>();
        stronglyConnectedComponents.add(component1);
        stronglyConnectedComponents.add(component2);
        StronglyConnectedComponentsFinder<String> f = new StronglyConnectedComponentsFinder<String>();
        assertTrue(f.findStronglyConnectedComponents(g)
                .equals(stronglyConnectedComponents));
    }

    @Test
    final void testFindStronglyConnectedComponents4() {
        Graph<String> g = new MapAdjacentListDirectedGraph<String>();
        GraphNode<String> n1 = new GraphNode<String>("1");
        g.addNode(n1);
        GraphNode<String> n2 = new GraphNode<String>("2");
        g.addNode(n2);
        GraphNode<String> n3 = new GraphNode<String>("3");
        g.addNode(n3);
        GraphNode<String> n4 = new GraphNode<String>("4");
        g.addNode(n4);
        GraphNode<String> n5 = new GraphNode<String>("5");
        g.addNode(n5);
        GraphNode<String> n6 = new GraphNode<String>("6");
        g.addNode(n6);
        GraphEdge<String> e12 = new GraphEdge<String>(n1, n2, true);
        g.addEdge(e12);
        GraphEdge<String> e22 = new GraphEdge<String>(n2, n2, true);
        g.addEdge(e22);
        GraphEdge<String> e24 = new GraphEdge<String>(n2, n4, true);
        g.addEdge(e24);
        GraphEdge<String> e41 = new GraphEdge<String>(n4, n1, true);
        g.addEdge(e41);
        GraphEdge<String> e25 = new GraphEdge<String>(n2, n5, true);
        g.addEdge(e25);
        GraphEdge<String> e45 = new GraphEdge<String>(n4, n5, true);
        g.addEdge(e45);
        GraphEdge<String> e54 = new GraphEdge<String>(n5, n4, true);
        g.addEdge(e54);
        GraphEdge<String> e63 = new GraphEdge<String>(n6, n3, true);
        g.addEdge(e63);
        GraphEdge<String> e36 = new GraphEdge<String>(n3, n6, true);
        g.addEdge(e36);
        GraphEdge<String> e26 = new GraphEdge<String>(n2, n6, true);
        g.addEdge(e26);
        Set<GraphNode<String>> component1 = new HashSet<GraphNode<String>>();
        component1.add(n1);
        component1.add(n2);
        component1.add(n4);
        component1.add(n5);
        Set<GraphNode<String>> component2 = new HashSet<GraphNode<String>>();
        component2.add(n3);
        component2.add(n6);
        Set<Set<GraphNode<String>>> stronglyConnectedComponents = new HashSet<Set<GraphNode<String>>>();
        stronglyConnectedComponents.add(component1);
        stronglyConnectedComponents.add(component2);
        StronglyConnectedComponentsFinder<String> f = new StronglyConnectedComponentsFinder<String>();
        assertTrue(f.findStronglyConnectedComponents(g)
                .equals(stronglyConnectedComponents));
    }

    @Test
    final void testFindStronglyConnectedComponents5() {
        Graph<String> g = new MapAdjacentListDirectedGraph<String>();
        GraphNode<String> na = new GraphNode<String>("a");
        g.addNode(na);
        GraphNode<String> nb = new GraphNode<String>("b");
        g.addNode(nb);
        GraphNode<String> nc = new GraphNode<String>("c");
        g.addNode(nc);
        GraphNode<String> nd = new GraphNode<String>("d");
        g.addNode(nd);
        GraphNode<String> ne = new GraphNode<String>("e");
        g.addNode(ne);
        GraphNode<String> nf = new GraphNode<String>("f");
        g.addNode(nf);
        GraphNode<String> ng = new GraphNode<String>("g");
        g.addNode(ng);
        GraphNode<String> nh = new GraphNode<String>("h");
        g.addNode(nh);
        GraphEdge<String> eab = new GraphEdge<String>(na, nb, true);
        g.addEdge(eab);
        GraphEdge<String> ebc = new GraphEdge<String>(nb, nc, true);
        g.addEdge(ebc);
        GraphEdge<String> ebe = new GraphEdge<String>(nb, ne, true);
        g.addEdge(ebe);
        GraphEdge<String> ebf = new GraphEdge<String>(nb, nf, true);
        g.addEdge(ebf);
        GraphEdge<String> ecd = new GraphEdge<String>(nc, nd, true);
        g.addEdge(ecd);
        GraphEdge<String> ecg = new GraphEdge<String>(nc, ng, true);
        g.addEdge(ecg);
        GraphEdge<String> edc = new GraphEdge<String>(nd, nc, true);
        g.addEdge(edc);
        GraphEdge<String> edh = new GraphEdge<String>(nd, nh, true);
        g.addEdge(edh);
        GraphEdge<String> eea = new GraphEdge<String>(ne, na, true);
        g.addEdge(eea);
        GraphEdge<String> eef = new GraphEdge<String>(ne, nf, true);
        g.addEdge(eef);
        GraphEdge<String> efg = new GraphEdge<String>(nf, ng, true);
        g.addEdge(efg);
        GraphEdge<String> egf = new GraphEdge<String>(ng, nf, true);
        g.addEdge(egf);
        GraphEdge<String> egh = new GraphEdge<String>(ng, nh, true);
        g.addEdge(egh);
        GraphEdge<String> ehh = new GraphEdge<String>(nh, nh, true);
        g.addEdge(ehh);
        Set<GraphNode<String>> component1 = new HashSet<GraphNode<String>>();
        component1.add(na);
        component1.add(nb);
        component1.add(ne);
        Set<GraphNode<String>> component2 = new HashSet<GraphNode<String>>();
        component2.add(nc);
        component2.add(nd);
        Set<GraphNode<String>> component3 = new HashSet<GraphNode<String>>();
        component3.add(nf);
        component3.add(ng);
        Set<GraphNode<String>> component4 = new HashSet<GraphNode<String>>();
        component4.add(nh);
        Set<Set<GraphNode<String>>> stronglyConnectedComponents = new HashSet<Set<GraphNode<String>>>();
        stronglyConnectedComponents.add(component1);
        stronglyConnectedComponents.add(component2);
        stronglyConnectedComponents.add(component3);
        stronglyConnectedComponents.add(component4);
        StronglyConnectedComponentsFinder<String> f = new StronglyConnectedComponentsFinder<String>();
        assertTrue(f.findStronglyConnectedComponents(g)
                .equals(stronglyConnectedComponents));
    }

}
