package abstractmodel;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;

public class GraphTests {
    @Test
    void AddIsolatedNode_DoesNotThrow_WhenIDIsNew() {
        IGraph testGraph = new Graph();

        testGraph.AddIsolatedNode( "1" );
    }

    @Test
    void AddIsolatedNode_Throws_WhenIDIsNotNew() {
        IGraph testGraph = new Graph() {{
            SetGraph(new HashMap<String, Node>() {{
                put( "1", new Node( "1", new ArrayList<String>( ) ) );
            }});
        }};

        assertThrows( IllegalArgumentException.class, () -> { testGraph.AddIsolatedNode( "1" ); } );
    }

    @Test
    void ContainsNode_ReturnsFalse_WhenNodeDoesNotExist() {
        IGraph testGraph = new Graph();
        
        assertFalse( testGraph.ContainsNode( "1" ) );
    }

    @Test
    void ContainsNode_ReturnsTrue_WhenNodeExists() {
        IGraph testGraph = new Graph() {{
            SetGraph(new HashMap<String, Node>() {{
                put( "1", new Node( "1", new ArrayList<String>( ) ) );
            }});
        }};
        
        assertTrue( testGraph.ContainsNode( "1" ) );
    }

    @Test
    void AddIsolatedNode_AddsNode_WhenIdIsNew() {
        IGraph testGraph = new Graph();

        testGraph.AddIsolatedNode( "1" );
        
        assertTrue(testGraph.ContainsNode( "1" ) );
    }

    @Test
    void AddSpecificNode_DoesNotThrow_WhenIDIsNew() {
        IGraph testGraph = new Graph();

        testGraph.AddSpecificNode( "1", new ArrayList<String>( ) );
    }

    @Test
    void AddSpecificNode_Throws_WhenIDIsNotNew() {
        IGraph testGraph = new Graph() {{
            SetGraph(new HashMap<String, Node>() {{
                put( "1", new Node( "1", new ArrayList<String>( ) ) );
            }});
        }};

        assertThrows( IllegalArgumentException.class, () -> { testGraph.AddSpecificNode( "1", new ArrayList<String>( ) ); } );
    }

    @Test
    void AddSpecificNode_Throws_WhenANeighborDoesNotExist() {
        IGraph testGraph = new Graph() {{
            SetGraph(new HashMap<String, Node>() {{
                put( "1", new Node( "1", new ArrayList<String>( ) ) );
            }});
        }};

        assertThrows( IllegalArgumentException.class, () -> { testGraph.AddSpecificNode( "2", new ArrayList<String>( ) {{ add("3"); }} ); } );
    }

    @Test
    void AddSpecificNode_AddsNode_WhenIDIsNewAndNoNeighbors() {
        IGraph testGraph = new Graph() {{
            SetGraph(new HashMap<String, Node>() {{
                put( "1", new Node( "1", new ArrayList<String>( ) ) );
            }});
        }};

        testGraph.AddSpecificNode( "2", new ArrayList<String>( ) );

        assertTrue(testGraph.ContainsNode("2"));
    }

    @Test
    void AddSpecificNode_AddsNode_WhenIDIsNewAndNeighborsExist() {
        IGraph testGraph = new Graph() {{
            SetGraph(new HashMap<String, Node>() {{
                put( "1", new Node( "1", new ArrayList<String>( ) ) );
            }});
        }};

        testGraph.AddSpecificNode( "2", new ArrayList<String>( ) {{ add("1"); }} );
    }

    @Test
    void ContainsEdge_DoesNotThrow_WhenNodesExist() {
        IGraph testGraph = new Graph() {{
            SetGraph(new HashMap<String, Node>() {{
                put( "1", new Node( "1", new ArrayList<String>( ) ) );
                put( "2", new Node("2", new ArrayList<String>( ) ) );
            }});
        }};

        testGraph.ContainsEdge("1", "2");
    }

    @Test
    void ContainsEdge_ReturnsTrue_WhenEdgeExists() {
        IGraph testGraph = new Graph() {{
            SetGraph(new HashMap<String, Node>() {{
                put( "1", new Node( "1", new ArrayList<String>( ) {{ add( "2" ); }} ) );
                put( "2", new Node("2", new ArrayList<String>( ) {{ add( "1" ); }} ) );
            }});
        }};

        assertTrue( testGraph.ContainsEdge("1", "2") );
    }

    @Test
    void ContainsEdge_ReturnsFalse_WhenEdgeDoesNotExist() {
        IGraph testGraph = new Graph() {{
            SetGraph(new HashMap<String, Node>() {{
                put( "1", new Node( "1", new ArrayList<String>( ) ) );
                put( "2", new Node("2", new ArrayList<String>( ) ) );
            }});
        }};

        assertFalse( testGraph.ContainsEdge("1", "2") );
    }

    @Test
    void ContainsEdge_Throws_WhenNodeDoesNotExist() {
        IGraph testGraph = new Graph() {{
            SetGraph(new HashMap<String, Node>() {{
                put( "1", new Node( "1", new ArrayList<String>( ) ) );
            }});
        }};

        assertThrows(IllegalArgumentException.class, () -> { testGraph.ContainsEdge("1", "2"); } );
    }

    @Test
    void AddSpecificNode_AddsEdges_WhenIDIsNewAndNeighborsExist() {
        IGraph testGraph = new Graph() {{
            SetGraph(new HashMap<String, Node>() {{
                put( "1", new Node( "1", new ArrayList<String>( ) ) );
            }});
        }};

        testGraph.AddSpecificNode( "2", new ArrayList<String>( ) {{ add("1"); }} );

        assertTrue( testGraph.ContainsEdge( "1", "2" ) );
    }

    @Test
    void AddEdge_DoesNotThrow_WhenNodesExist() {
        IGraph testGraph = new Graph() {{
            SetGraph(new HashMap<String, Node>() {{
                put( "1", new Node( "1", new ArrayList<String>( ) ) );
                put( "2", new Node("2", new ArrayList<String>( ) ) );
            }});
        }};

        testGraph.AddEdge( "1", "2" );
    }

    @Test
    void AddEdge_AddsEdge_WhenNodesExist() {
        IGraph testGraph = new Graph() {{
            SetGraph(new HashMap<String, Node>() {{
                put( "1", new Node( "1", new ArrayList<String>( ) ) );
                put( "2", new Node("2", new ArrayList<String>( ) ) );
            }});
        }};

        testGraph.AddEdge( "1", "2" );

        assertTrue( testGraph.ContainsEdge( "1", "2" ) );
    }

    @Test
    void AddEdge_Throws_WhenNodeDoesNotExist() {
        IGraph testGraph = new Graph() {{
            SetGraph(new HashMap<String, Node>() {{
                put( "1", new Node( "1", new ArrayList<String>( ) ) );
            }});
        }};

        assertThrows(IllegalArgumentException.class, () -> { testGraph.AddEdge( "1", "2" ); } );
    }

    @Test
    void RemoveNode_DoesNotThrow_WhenNodeExists() {
        IGraph testGraph = new Graph() {{
            SetGraph(new HashMap<String, Node>() {{
                put( "1", new Node( "1", new ArrayList<String>( ) ) );
                put( "2", new Node("2", new ArrayList<String>( ) ) );
            }});
        }};

        testGraph.RemoveNode("2");
    }

    @Test
    void RemoveNode_Throws_WhenNodeDoesNotExist() {
        IGraph testGraph = new Graph() {{
            SetGraph(new HashMap<String, Node>() {{
                put( "1", new Node( "1", new ArrayList<String>( ) ) );
            }});
        }};

        assertThrows( IllegalArgumentException.class, () -> { testGraph.RemoveNode("2"); } ) ;
    }

    @Test
    void RemoveNode_RemovesNode(){
        IGraph testGraph = new Graph() {{
            SetGraph(new HashMap<String, Node>() {{
                put( "1", new Node( "1", new ArrayList<String>( ) ) );
                put( "2", new Node("2", new ArrayList<String>( ) ) );
            }});
        }};

        testGraph.RemoveNode("2");

        assertFalse( testGraph.ContainsNode( "2" ) );
    }

    @Test
    void RemoveNode_RemovesExtantEdges(){
        IGraph testGraph = new Graph() {{
            SetGraph(new HashMap<String, Node>() {{
                put( "1", new Node( "1", new ArrayList<String>( ) {{ add( "2" ); }} ) );
                put( "2", new Node("2", new ArrayList<String>( ) {{ add( "1" ); }} ) );
            }});
        }};

        testGraph.RemoveNode("2");

        assertFalse( testGraph.GetGraph().contains( new ArrayList<String> () {{ add("1"); add("2"); }}) );
    }

    @Test
    void RemoveEdge_Throws_WhenEdgeDoesNotExist() {
        IGraph testGraph = new Graph() {{
            SetGraph(new HashMap<String, Node>() {{
                put( "1", new Node( "1", new ArrayList<String>( ) ) );
                put( "2", new Node("2", new ArrayList<String>( ) ) );
            }});
        }};

        assertThrows( IllegalArgumentException.class, () -> { testGraph.RemoveEdge("1", "2"); } );
    }

    @Test
    void RemoveEdge_DoesNotThrow_WhenEdgeExists() {
        IGraph testGraph = new Graph() {{
            SetGraph(new HashMap<String, Node>() {{
                put( "1", new Node( "1", new ArrayList<String>( ) {{ add( "2" ); }} ) );
                put( "2", new Node("2", new ArrayList<String>( ) {{ add( "1" ); }} ) );
            }});
        }};

        testGraph.RemoveEdge( "1", "2" );
    }

    @Test
    void RemoveEdge_RemovesEdge() {
        IGraph testGraph = new Graph() {{
            SetGraph(new HashMap<String, Node>() {{
                put( "1", new Node( "1", new ArrayList<String>( ) {{ add( "2" ); }} ) );
                put( "2", new Node("2", new ArrayList<String>( ) {{ add( "1" ); }} ) );
            }});
        }};

        testGraph.RemoveEdge( "1", "2" );

        assertFalse( 
            testGraph.GetGraph().contains( new ArrayList<String> () {{ add("1"); add("2"); }} )
            || 
            testGraph.GetGraph().contains( new ArrayList<String> () {{ add("2"); add("1"); }} )
        );
    }
}