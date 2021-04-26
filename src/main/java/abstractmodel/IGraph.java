package abstractmodel;

import java.util.ArrayList;

/**
 * This is the interface for the an undirected graph representation
 */
public interface IGraph {

    /**
     * Returns a representation of the graph as a list of lists, where the 
     * first element of each list is the Node identifier and the following 
     * elements are the neighbors of the node.
     */
    public ArrayList<ArrayList<String>> GetGraph();

    /**
     * This method adds a node with no neighbors
     * 
     * @param id 
     *          The id of the vertices to add
     * @throws IllegalArgumentException 
     *          If the {@code id} already exists in the graph
     */
    public void AddIsolatedNode( String id ) throws IllegalArgumentException;

    /**
     * Adds a node to the graph with a specified list of neighbors.
     * @param id The identifier of the node to add
     * @param neighbors The list of neighbors
     * @throws IllegalArgumentException If the id already exists or if one of the neighbors does not exist.
     */
    public void AddSpecificNode( String id, ArrayList<String> neighbors ) throws IllegalArgumentException;

    /**
     * Returns true if the specified identifier exists in the graph.
     * 
     * @param node_id 
     *          The identifier for the node
     * @return 
     *          True if {@code id} exists in the graph
     */
    public boolean ContainsNode( String node_id );

    /** 
     * This method creates an edge between the specified vertices.
     * 
     * @param node_one 
     *          One node in the edge
     * @param node_two 
     *          The other node in the edge
     * @throws IllegalArgumentException 
     *          If either node does not exist in the graph.
     */
    public void AddEdge( String node_one, String node_two ) throws IllegalArgumentException;

    /**
     * Determines if an edge exists between two speficied vertices 
     * 
     * @param node_one
     * @param node_two
     * @return 
     *          True if {@code node_one} is neighbor of {@code node_two} and vice versa
     * @throws IllegalArgumentException 
     *          if either {@code node_one} or {@code node_two} does not exist in the graph
     */
    public boolean ContainsEdge( String node_one, String node_two ) throws IllegalArgumentException;

    /**
     * Removes a node from the graph
     * 
     * @param id
     *          The node to remove
     * 
     * @throws IllegalArgumentException
     *          If the specified node does not exist in the graph
     */
    public void RemoveNode( String id ) throws IllegalArgumentException;

    /**
     * Removes an edge from the graph
     * 
     * @param node_one
     * @param node_two
     * 
     * @throws IllegalArgumentException
     *          If the specified edge does not exist or either node does not exist.
     */
    public void RemoveEdge( String node_one, String node_two ) throws IllegalArgumentException;
}
