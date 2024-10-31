package arbitrarytree;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Task1Tests {
    @Test
    public void testAddRoot() {
        ArbitraryTree tree = new ArbitraryTree();
        assertTrue(tree.addRoot(0, "Carl Gauss"));
        assertFalse(tree.addRoot(1, "Isaac Newton")); // Only one root should be allowed
    }

    @Test
    public void testContainsNodeId() {
        ArbitraryTree tree = new ArbitraryTree();
        tree.addRoot(0, "Carl Gauss");
        tree.addChildOf(0, 1, "Bernhard Riemann");
        tree.addChildOf(0, 2, "Sophie Germain");
        tree.addChildOf(1, 3, "Felix Klein");
        tree.addChildOf(3, 4, "David Hilbert");
        tree.addChildOf(4, 5, "Emmy Noether");

        assertTrue(tree.contains(0)); // Carl Gauss
        assertTrue(tree.contains(3)); // Felix Klein
        assertTrue(tree.contains(5)); // Emmy Noether
        assertFalse(tree.contains(6)); // Node does not exist
    }

    @Test
    public void testAddChildOf() {
        ArbitraryTree tree = new ArbitraryTree();
        tree.addRoot(0, "Carl Gauss");
        assertTrue(tree.addChildOf(0, 1, "Bernhard Riemann"));
        assertTrue(tree.addChildOf(0, 2, "Sophie Germain"));
        assertTrue(tree.addChildOf(1, 3, "Felix Klein"));
        assertFalse(tree.addChildOf(4, 6, "Nonexistent Parent")); // Parent does not exist
    }

    @Test
    public void testDuplicateId() {
        ArbitraryTree tree = new ArbitraryTree();
        tree.addRoot(0, "Carl Gauss");
        assertTrue(tree.addChildOf(0, 1, "Bernhard Riemann"));
        assertTrue(tree.addChildOf(0, 2, "Sophie Germain"));
        assertTrue(tree.addChildOf(1, 3, "Felix Klein"));
        assertTrue(tree.addChildOf(1, 4, "Terence Tao"));
        assertFalse(tree.addChildOf(1, 3, "Terence Tao")); // Parent does not exist
    }

    @Test
    public void testGetNodeName() {
        ArbitraryTree tree = new ArbitraryTree();
        tree.addRoot(0, "Carl Gauss");
        tree.addChildOf(0, 1, "Bernhard Riemann");
        tree.addChildOf(0, 2, "Sophie Germain");
        tree.addChildOf(2, 6, "Joseph Fourier");
        assertEquals("Carl Gauss", tree.getNodeName(0));
        assertEquals("Joseph Fourier", tree.getNodeName(6));
    }

    @Test
    public void testGetParentId() {
        ArbitraryTree tree = new ArbitraryTree();
        tree.addRoot(0, "Carl Gauss");
        tree.addChildOf(0, 1, "Bernhard Riemann");
        tree.addChildOf(1, 3, "Felix Klein");
        tree.addChildOf(3, 4, "David Hilbert");

        assertEquals(ArbitraryTree.ROOT, tree.getParentId(0)); // Carl Gauss is the root
        assertEquals(1, tree.getParentId(3)); // Parent of Felix Klein is Bernhard Riemann
        assertEquals(3, tree.getParentId(4)); // Parent of David Hilbert is Felix Klein
    }
}
