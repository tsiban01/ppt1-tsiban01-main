package arbitrarytree;

import java.util.*;


public class ArbitraryTree {
    public static final int ROOT = -1;
    private Map<Integer, TreeNode> nodes;
    private final Map<Integer, List<Integer>> childrenMap = new HashMap<>();
    private final Map<Integer, Integer> parentMap = new HashMap<>();
    private final Map<Integer, String> nodeNamesMap = new HashMap<>();

    private Integer rootId;

    private static class TreeNode {
        int id;
        String name;
        Integer parentId;
        List<Integer> children;

        TreeNode(int id, String name, Integer parentId) {
            this.id = id;
            this.name = name;
            this.parentId = parentId;
            this.children = new ArrayList<>();
        }
    }

    public ArbitraryTree() {
        this.nodes = new HashMap<>();
        this.rootId = null;
    }

    /* ======= TASK 1 ======== */

    /**
     * Adds the root node.
     * If no root node exists, adds it and returns true.
     * If a root node already exists, does nothing and returns false.
     *
     * @param rootId   is greater than or equal to 0.
     * @param rootName is not null and is not an empty string.
     * @return true if the root node was added and false otherwise.
     */
    public boolean addRoot(int rootId, String rootName) {
        if (this.rootId != null || rootId < 0 || rootName == null || rootName.isEmpty()) {
            return false; // Check if root already exists, rootId is negative, or rootName is invalid
        }
        TreeNode rootNode = new TreeNode(rootId, rootName, null);
        nodes.put(rootId, rootNode);
        this.rootId = rootId;
        return true; // A successfully added root will return true
    }

    /**
     * Adds a node as a child to a specific parent node.
     * Returns true if the child was successfully added.
     * Returns false if the parent does not exist or if the child ID is a duplicate.
     *
     * @param parentId  the identifier of the parent node; requires {@code this.contains(parentId) == true}.
     * @param childId   the identifier of the child node, >= 0, and unique to the tree.
     * @param childName is not null and is not an empty string.
     * @return true if the child was added successfully, false otherwise.
     */
    public boolean addChildOf(int parentId, int childId, String childName) {
        if (!contains(parentId) || contains(childId) || childId < 0 || childName == null || childName.isEmpty()) {
            return false; // Check if parent exists,  child ID is unique, and if child name is valid
        }
        TreeNode childNode = new TreeNode(childId, childName, parentId);
        nodes.put(childId, childNode);
        nodes.get(parentId).children.add(childId); // Add child to parent's  list of children
        return true;
    }

    /**
     * Checks if a node with {@code nodeId} as identifier is present in this tree.
     *
     * @param nodeId the node to check for, is >= 0.
     * @return true if there is a node with ID {@code nodeId}, false otherwise.
     */
    public boolean contains(int nodeId) {
        return nodes.containsKey(nodeId);
    }

    /**
     * Retrieves the name of the node associated with the specified identifier {@code nodeId}.
     *
     * @param nodeId the identifier of the node whose name is to be retrieved;
     *               requires {@code this.contains(nodeId) == true}.
     * @return the name of the node with the given {@code nodeId}, or null if not found.
     */
    public String getNodeName(int nodeId) {
        TreeNode node = nodes.get(nodeId);
        return (node != null) ? node.name : null;
    }

    /**
     * Returns the parent ID of a node specified by {@code nodeId}.
     * Returns {@code ArbitraryTree.ROOT} if {@code nodeId} represents the root node.
     *
     * @param nodeId the node for which to find the parent ID;
     *               requires {@code this.contains(nodeId) == true}.
     * @return the ID of the parent for the given {@code nodeId}, or {@code ArbitraryTree.ROOT} if the node is root.
     */
    public int getParentId(int nodeId) {
        TreeNode node = nodes.get(nodeId);
        if (node == null) return -1; // Node doesn't exist
        return node.parentId == null ? ROOT : node.parentId;
    }




    /* ======= TASK 2 ======== */

    /**
     * Adds a child node to a parent identified by parentName.
     * If exactly one node matches parentName, adds the child; if multiple/no matches, returns false.
     *
     * @param parentName the name of the parent node; must be non-null and non-empty.
     * @param childId    the identifier for the child node, > 0.
     * @param childName  the name for the child; must be non-null and non-empty.
     * @return true if the child node was successfully added, false otherwise.
     */
    public boolean addChildOf(String parentName, int childId, String childName) {
        if (parentName == null || parentName.isEmpty() || childName == null || childName.isEmpty() || childId <= 0) {
            return false;
        }
        Set<Integer> parentIds = getAllMatches(parentName);
        if (parentIds.size() != 1 || contains(childId)) {
            return false;
        }
        int parentId = parentIds.iterator().next();
        return addChildOf(parentId, childId, childName);
    }

    /**
     * Checks if there is at least one node with the specified nodeName.
     *
     * @param nodeName the name to check for, non-null and non-empty.
     * @return true if there is at least one node that matches nodeName, false otherwise.
     */
    public boolean contains(String nodeName) {
        return getAllMatches(nodeName).size() > 0;
    }

    /**
     * Returns the nodeId for the node with the specified nodeName.
     * Throws AmbiguityException if multiple nodes match nodeName.
     *
     * @param nodeName the name to search for, non-null and non-empty.
     * @return the node identifier for the node matching nodeName.
     * @throws AmbiguityException if multiple nodes match nodeName.
     */
    public int getNodeId(String nodeName) throws AmbiguityException {
        Set<Integer> matches = getAllMatches(nodeName);
        if (matches.size() == 1) {
            return matches.iterator().next();
        }
        throw new AmbiguityException("Multiple nodes match the specified name.");
    }

    /**
     * Retrieves all node IDs that match the specified nodeName.
     *
     * @param nodeName the name to search for, non-null and non-empty.
     * @return a set of nodeIds for nodes matching nodeName; empty set if no matches.
     */
    public Set<Integer> getAllMatches(String nodeName) {
        Set<Integer> matches = new HashSet<>();
        if (nodeName != null && !nodeName.isEmpty()) {
            for (TreeNode node : nodes.values()) {
                if (node.name.equals(nodeName)) {
                    matches.add(node.id);
                }
            }
        }
        return matches;
    }

    /**
     * Returns the name of the parent for the node with the specified nodeId.
     * Throws NoParentException if nodeId is the root.
     *
     * @param nodeId the identifier of the node, > 0.
     * @return the name of the parent node of nodeId.
     * @throws NoParentException if nodeId is the root node.
     */
    public String getParentName(int nodeId) throws NoParentException {
        TreeNode node = nodes.get(nodeId);
        if (node == null || node.parentId == null) {
            throw new NoParentException();
        }
        return nodes.get(node.parentId).name;
    }

    /**
     * Retrieves all sibling nodes for the specified nodeId.
     *
     * @param nodeId the node identifier for which to find siblings.
     * @return a set of sibling nodeIds sharing the same parent as nodeId, excluding nodeId itself.
     */
    public Set<Integer> getSiblings(int nodeId) {
        TreeNode node = nodes.get(nodeId);
        if (node == null || node.parentId == null) {
            return Collections.emptySet();
        }
        Set<Integer> siblings = new HashSet<>(nodes.get(node.parentId).children);
        siblings.remove(nodeId);
        return siblings;
    }

    /* ======= TASK 3 ======== */

    /**
     * Initializes an ArbitraryTree from an array structure and an array of node names.
     *
     * @param arbitraryTree an array defining the tree structure:
     *        each entry has a `nodeId`, the count of children, and
     *        references to the indices of the children in the same array.
     *        Parents are always listed before their children in this array representation.
     * @param nodeNames an array where each index represents the name of the node with the same id.
     * @throws IllegalArgumentException if the structure is invalid or the arrays are insufficient.
     */
    public ArbitraryTree(int[] arbitraryTree, String[] nodeNames) {
        if (arbitraryTree == null || nodeNames == null || arbitraryTree.length == 0 || nodeNames.length == 0) {
            throw new IllegalArgumentException("Invalid input arrays.");
        }

        int i = 0;
        Set<Integer> nodeSet = new HashSet<>();

        while (i < arbitraryTree.length) {
            int nodeId = arbitraryTree[i++];
            int numChildren = arbitraryTree[i++];
            List<Integer> children = new ArrayList<>();

            for (int j = 0; j < numChildren; j++) {
                int childId = arbitraryTree[i++];
                if (parentMap.containsKey(childId)) {
                    throw new IllegalArgumentException("Multiple parents for node " + childId);
                }
                children.add(childId);
                parentMap.put(childId, nodeId);
            }

            if (rootId == null) {
                rootId = nodeId;
            }
            childrenMap.put(nodeId, children);
            nodeSet.add(nodeId);
        }

        // Initialize node names
        for (int id = 0; id < nodeNames.length; id++) {
            nodeNamesMap.put(id, nodeNames[id]);
        }

        // Validate node names and root
        if (!nodeNamesMap.containsKey(rootId)) {
            throw new IllegalArgumentException("Missing root node name.");
        }
        if (!nodeSet.containsAll(parentMap.keySet())) {
            throw new IllegalArgumentException("Invalid node references in tree structure.");
        }
    }



    /**
     * Checks if the node identified by {@code ancestorId} is an ancestor of the
     * node identified by {@code childId}.
     *
     * @param ancestorId the identifier of the node that might be the ancestor, > 0.
     * @param childId the identifier of the node that might be a descendant of {@code ancestorId}, > 0.
     * @return true if the node with {@code ancestorId} is an ancestor of the node
     *         with {@code childId}, and false otherwise.
     */
    public boolean isAncestorOf(int ancestorId, int childId) {
        Integer parentId = parentMap.get(childId);
        while (parentId != null) {
            if (parentId == ancestorId) {
                return true;
            }
            parentId = parentMap.get(parentId);
        }
        return false;
    }

    /**
     * Checks if the node identified by {@code descendantId} is a descendant of the
     * node identified by {@code ancestorId}.
     *
     * @param descendantId the identifier of the node that might be a descendant, > 0.
     * @param ancestorId the identifier of the node that might be the ancestor of {@code descendantId}, > 0.
     * @return true if the node with {@code descendantId} is a descendant of the node
     *         with {@code ancestorId}, and false otherwise.
     */
    public boolean isDescendantOf(int descendantId, int ancestorId) {
        return isAncestorOf(ancestorId, descendantId);
    }



    /* ======= TASK 4 ======== */

    /**
     * Computes the total reward for this tree based on family sizes and rewards array.
     *
     * @param rewards is not null and rewards.size == number of nodes in the tree
     * @return the total reward computed from family sizes and corresponding rewards.
     */
    public double totalReward(double[] rewards) {
        return 0; // TODO: Implement this method
    }

    /**
     * Computes the maximum reward increase that is possible by adding a new leaf node.
     * If the total reward will not increase then this method will return 0.0 to
     * indicate that adding a new node is not beneficial.
     *
     * @param rewards is not null and rewards.size == (number of nodes in the tree) + 1
     * @return the maximum reward increase that is possible by adding a new leaf node.
     */
    public double rewardIncreaseWithSingleNodeAddition(double[] rewards) {
        return -1;
    }

    /* ======== TASK 5 ======== */

    /**
     * Computes the maximum possible reward for any tree with n nodes.
     *
     * @param rewards the rewards array corresponding to family sizes 0 to n-1.
     * @return the maximum possible reward achievable for any tree
     *         with rewards.size nodes.
     */
    public static double maxPossibleReward(double[] rewards) {
        return 0; // TODO: Implement this method
    }
}
