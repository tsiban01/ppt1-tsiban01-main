package arbitrarytree;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Collections;
import java.util.List;
import java.util.Set;

public class Task2Tests {
    @Test
    public void testAddChildOfByName() {
        ArbitraryTree tree = new ArbitraryTree();
        tree.addRoot(0, "Carl Gauss");
        assertTrue(tree.addChildOf("Carl Gauss", 1, "Bernhard Riemann"));
        assertTrue(tree.addChildOf("Carl Gauss", 2, "Sophie Germain"));
        assertTrue(tree.addChildOf("Bernhard Riemann", 3, "Felix Klein"));
        assertTrue(tree.addChildOf("Felix Klein", 4, "David Hilbert"));
        assertFalse(tree.addChildOf("Nonexistent", 5, "Orphan")); // Parent name does not exist
    }

    @Test
    public void testContainsNodeName() {
        ArbitraryTree tree = new ArbitraryTree();
        tree.addRoot(0, "Carl Gauss");
        tree.addChildOf(0, 1, "Bernhard Riemann");
        tree.addChildOf(0, 2, "Sophie Germain");
        tree.addChildOf(1, 3, "Felix Klein");
        tree.addChildOf(2, 6, "Joseph Fourier");
        assertTrue(tree.contains("Carl Gauss"));
        assertTrue(tree.contains("Sophie Germain"));
        assertFalse(tree.contains("Nonexistent"));
    }

    @Test
    public void testGetNodeId() {
        ArbitraryTree tree = new ArbitraryTree();
        tree.addRoot(0, "Carl Gauss");
        tree.addChildOf(0, 1, "Bernhard Riemann");
        tree.addChildOf(0, 2, "Sophie Germain");
        tree.addChildOf(2, 6, "Joseph Fourier");
        try {
            assertEquals(0, tree.getNodeId("Carl Gauss"));
            assertEquals(6, tree.getNodeId("Joseph Fourier"));
        } catch (AmbiguityException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testAmbiguityException() {
        ArbitraryTree tree = new ArbitraryTree();
        tree.addRoot(0, "Carl Gauss");
        tree.addChildOf(0, 1, "Bernhard Riemann");
        tree.addChildOf(0, 2, "Sophie Germain");
        tree.addChildOf(2, 6, "Fourier");
        tree.addChildOf(6, 5, "Fourier");
        assertThrows(AmbiguityException.class, () -> {
            tree.getNodeId("Fourier");
        });
    }

    @Test
    public void testGetAllMatches() {
        ArbitraryTree tree = new ArbitraryTree();
        tree.addRoot(0, "Carl Gauss");
        tree.addChildOf(0, 1, "Bernhard Riemann");
        tree.addChildOf(0, 2, "Sophie Germain");
        tree.addChildOf(1, 3, "Felix Klein");
        tree.addChildOf(1, 4, "Felix Klein");
        Set<Integer> matches = tree.getAllMatches("Felix Klein");
        assertEquals(Set.of(3, 4), matches);
    }

    @Test
    public void testGetParentName() throws NoParentException {
        ArbitraryTree tree = new ArbitraryTree();
        tree.addRoot(0, "Carl Gauss");
        tree.addChildOf(0, 1, "Bernhard Riemann");
        tree.addChildOf(1, 3, "Felix Klein");
        tree.addChildOf(3, 4, "David Hilbert");
        tree.addChildOf(4, 5, "Emmy Noether");
        try {
            assertEquals("Felix Klein", tree.getParentName(4)); // Parent of David Hilbert
            assertEquals("David Hilbert", tree.getParentName(5)); // Parent of Emmy Noether
        } catch (NoParentException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testGetSiblings() {
        ArbitraryTree tree = new ArbitraryTree();
        tree.addRoot(0, "Carl Gauss");
        tree.addChildOf(0, 1, "Bernhard Riemann");
        tree.addChildOf(0, 2, "Sophie Germain");
        tree.addChildOf(0, 3, "Joseph Fourier");
        tree.addChildOf(1, 4, "Felix Klein");

        // Carl Gauss's children (siblings of each other)
        assertEquals(Set.of(2, 3), tree.getSiblings(1));
        assertEquals(Set.of(1, 3), tree.getSiblings(2));
        assertEquals(Set.of(1, 2), tree.getSiblings(3));

        // No siblings
        assertEquals(Collections.emptySet(), tree.getSiblings(0));
        assertEquals(Collections.emptySet(), tree.getSiblings(4));
    }
}