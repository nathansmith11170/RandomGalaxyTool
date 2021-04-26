package abstractmodel;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * Implementation of an undirected graph
 */
public class Graph implements IGraph {
    /**
     * A representation of a node in a graph. Includes a list of neighbors.
     */
    public class Node implements Comparable<Node>, Comparator<Node>{
        /* fields */
        private String identifier;
        private ArrayList<String> neighbors;
    
        /* public facing accessors */
        public String GetIdentifier() {
            return this.identifier;
        }
    
        public ArrayList<String> GetNeighbors() {
            return this.neighbors;
        }
    
        /* Constructor */
        public Node( String id, ArrayList<String> neighbors ) {
            this.identifier = id;
            this.neighbors = neighbors;
        }
    
        @Override
        public int compare(Node o1, Node o2) {
            return o1.GetIdentifier().compareTo( o2.GetIdentifier() );
        }
    
        @Override
        public int compareTo(Node o) {
            return this.GetIdentifier().compareTo( o.GetIdentifier() );
        }
    
        /**
         * This method adds to the list of neighbors
         * 
         * @param id
         *          The ID of the Node we are adding to {@code this.neighbors}
         * @throws IllegalArgumentException
         *          If the id is already a neighbor
         */
        public void AddNeighbor(String id) throws IllegalArgumentException {
            if( this.neighbors.contains(id) ) {
                throw new IllegalArgumentException(id + " already is a neighbor.");
            }
            else {
                this.neighbors.add(id);
            }
        }

        /**
         * This method removes from the list of neighbors
         * 
         * @param id
         *          The ID of the Node we are adding to {@code this.neighbors}
         * @throws IllegalArgumentException 
         *          If the id is not a neighbor
         */
        public void RemoveNeighbor( String id ) throws IllegalArgumentException {
            if( !this.neighbors.contains( id ) ) {
                throw new IllegalArgumentException( id + " is not a neighbor of " + this.identifier );
            }
            else {
                this.neighbors.remove( id );
            }
        }
    }

    /**
     * This map of {@code Node} objects represents the graph. Each key matches the private field {@code Node.identifier}
     */
    private Map<String, Node> graph;

    /**
     * (@inheritDoc)
     */
    public ArrayList<ArrayList<String>> GetGraph() {
        ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
        for( Node n : this.graph.values() )  {
            ArrayList<String> temp = new ArrayList<String>();
            temp.add( n.GetIdentifier() );
            temp.addAll(n.GetNeighbors());
            result.add(temp);
        }
        return result;
    }

    public void SetGraph(Map<String, Node> g) {
        this.graph = g;
    }

    public Graph() {
        this.graph = new HashMap<String, Node>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void AddIsolatedNode(String id) throws IllegalArgumentException {
        Node toAdd = new Node(id, new ArrayList<String>());
        if( this.ContainsNode(id) ) {
            throw new IllegalArgumentException( "The graph already contains a Node with identifier " + id );
        }
        else {
            this.graph.put(id, toAdd);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void AddSpecificNode(String id, ArrayList<String> neighbors) throws IllegalArgumentException {
        Node toAdd = new Node(id, neighbors);
        if( this.ContainsNode(id) ) {
            throw new IllegalArgumentException(id + " already exists in graph.");
        }
        else if ( !neighbors.stream().allMatch( (String s) -> {return this.ContainsNode(s);} ) ) {
            throw new IllegalArgumentException( "The graph does not contain one of the specified neighbors " + neighbors.toString() );
        }
        else {
            this.graph.put(id, toAdd);
            for( String neighbor : neighbors ) {
                Node alteredNeighbor = this.graph.get(neighbor);
                alteredNeighbor.AddNeighbor(id);
                this.graph.replace(neighbor, alteredNeighbor);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean ContainsNode(String Node_id) {
        return this.graph.containsKey(Node_id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void AddEdge( String Node_one, String Node_two ) throws IllegalArgumentException {
        if( !this.ContainsNode(Node_one) ) {
            throw new IllegalArgumentException("Node " + Node_one + " does not exist in graph.");
        }
        if( !this.ContainsNode(Node_two) ) {
            throw new IllegalArgumentException("Node " + Node_two + " does not exist in graph.");
        }
        Node v1 = this.graph.get(Node_one);
        Node v2 = this.graph.get(Node_two);
        v1.AddNeighbor(Node_two);
        v2.AddNeighbor(Node_one);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean ContainsEdge(String Node_one, String Node_two) throws IllegalArgumentException {
        if( !this.ContainsNode(Node_one) ) {
            throw new IllegalArgumentException("Node " + Node_one + " does not exist in graph.");
        }
        if( !this.ContainsNode(Node_two) ) {
            throw new IllegalArgumentException("Node " + Node_two + " does not exist in graph.");
        }

        Node v1 = this.graph.get(Node_one);
        Node v2 = this.graph.get(Node_two);

        boolean v2ExistsinNeighborsofv1 = v1.GetNeighbors().contains(Node_two);
        boolean v1ExistsinNeighborsofv2 = v2.GetNeighbors().contains(Node_one);

        return v2ExistsinNeighborsofv1 && v1ExistsinNeighborsofv2;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void RemoveNode( String id ) throws IllegalArgumentException {
        if( !this.ContainsNode( id ) ) {
            throw new IllegalArgumentException( "Node " + id + " does not exist in graph." );
        }
        Node toRemove = this.graph.get( id );
        this.graph.remove( id );
        for( String neighbor : toRemove.GetNeighbors() ) {
            Node temp = this.graph.get( neighbor );
            temp.RemoveNeighbor( id );
            this.graph.replace( neighbor, temp );
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void RemoveEdge( String Node_one, String Node_two ) throws IllegalArgumentException {
        if( !this.ContainsNode( Node_one ) ) {
            throw new IllegalArgumentException( "Node " + Node_one + " does not exist in graph." );
        }
        if( !this.ContainsNode( Node_two ) ) {
            throw new IllegalArgumentException( "Node " + Node_two + " does not exist in graph." );
        }
        if( !this.ContainsEdge( Node_one, Node_two) ) {
            throw new IllegalArgumentException( "The specified edge (" + Node_one + "," + Node_two + ") does not exist.");
        }
        Node n1 = this.graph.get( Node_one );
        Node n2 = this.graph.get( Node_two );

        n1.RemoveNeighbor( Node_two );
        n2.RemoveNeighbor( Node_one );

        this.graph.replace( Node_one, n1 );
        this.graph.replace( Node_two, n2 );
    }
}