package arbitrarytree;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class Task3Tests {
    @Test
    public void testConstructorWithValidArray() {
        int[] arbitraryTree = {
                // index: 0
                0, 2, 4, 8,         // Carl Gauss (nodeId 0) has children: Bernhard Riemann (at index: 4), Sophie Germain (index: 8)
                // index: 4
                1, 2, 11, 17,       // Bernhard Riemann (1) has children: Felix Klein (index: 11), Richard Dedekind (index: 17)
                // index: 8
                2, 1, 14,           // Sophie Germain (2) has one child: Joseph Fourier (index: 14)
                // index: 11
                3, 1, 19,           // Felix Klein (3) has one child: David Hilbert (index: 19)
                // index: 14
                5, 1, 23,           // Joseph Fourier (5) has one child: Ada Lovelace (index: 23)
                // index: 17
                4, 0,               // Richard Dedekind (4) has no children
                // index: 19
                6, 2, 25, 27,       // David Hilbert (6) has children: Emmy Noether (index: 25), John von Neumann (index: 27)
                // index: 23
                9, 0,               // Ada Lovelace (9) has no children
                // index: 25
                7, 0,               // Emmy Noether (7) has no children
                // index: 27
                8, 1, 30,           // John von Neumann (8) has one child: Julia Robinson (index: 30)
                // index: 30
                10, 0               // Julia Robinson (10) has no children
        };

        String[] nodeNames = {
                "Carl Gauss",
                "Bernhard Riemann",
                "Sophie Germain",
                "Felix Klein",
                "Richard Dedekind",
                "Joseph Fourier",
                "David Hilbert",
                "Emmy Noether",
                "John von Neumann",
                "Ada Lovelace",
                "Julia Robinson"
        };

        ArbitraryTree tree = new ArbitraryTree(arbitraryTree, nodeNames);

        assertEquals("Carl Gauss", tree.getNodeName(0));
        assertEquals(3, tree.getParentId(6)); // Felix Klein is the parent of David Hilbert
        assertEquals(Set.of(9), tree.getAllMatches("Ada Lovelace"));
    }

    @Test
    public void testConstructorWithValidArray_OutOfOrder() {
        int[] arbitraryTree = {
                // index: 0
                11, 2, 4, 8,         // Carl Gauss (nodeId 11 in nodeNames) has children: Bernhard Riemann (at index: 4), Sophie Germain (index: 8)
                // index: 4
                1, 2, 11, 17,       // Bernhard Riemann (1) has children: Felix Klein (index: 11), Richard Dedekind (index: 17)
                // index: 8
                2, 1, 14,           // Sophie Germain (2) has one child: Joseph Fourier (index: 14)
                // index: 11
                3, 1, 19,           // Felix Klein (3) has one child: David Hilbert (index: 19)
                // index: 14
                5, 1, 23,           // Joseph Fourier (5) has one child: Ada Lovelace (index: 23)
                // index: 17
                4, 0,               // Richard Dedekind (4) has no children
                // index: 19
                6, 2, 25, 27,       // David Hilbert (6) has children: Emmy Noether (index: 25), John von Neumann (index: 27)
                // index: 23
                9, 0,               // Ada Lovelace (9) has no children
                // index: 25
                7, 0,               // Emmy Noether (7) has no children
                // index: 27
                8, 1, 30,           // John von Neumann (8) has one child: Julia Robinson (index: 30)
                // index: 30
                10, 0               // Julia Robinson (10) has no children
        };

        // nodeNames may have unused names
        String[] nodeNames = {
                "Euclid",
                "Bernhard Riemann",
                "Sophie Germain",
                "Felix Klein",
                "Richard Dedekind",
                "Joseph Fourier",
                "David Hilbert",
                "Emmy Noether",
                "John von Neumann",
                "Ada Lovelace",
                "Julia Robinson",
                "Carl Gauss",
                "Pythagoras"
        };

        ArbitraryTree tree = new ArbitraryTree(arbitraryTree, nodeNames);

        assertEquals("Carl Gauss", tree.getNodeName(11));
        assertEquals(3, tree.getParentId(6)); // Felix Klein is the parent of David Hilbert
        assertEquals(Set.of(9), tree.getAllMatches("Ada Lovelace"));
        assertEquals(Set.of(), tree.getAllMatches("Pythagoras"));
    }

    @Test
    public void testConstructorWithTwoRoots() {
        int[] arbitraryTree = {
                // index: 0
                0, 2, 4, 8,         // Carl Gauss (nodeId 0) has children: Bernhard Riemann (at index: 4), Sophie Germain (index: 8)
                // index: 4
                1, 2, 11, 17,       // Bernhard Riemann (1) has children: Felix Klein (index: 11), Richard Dedekind (index: 17)
                // index: 8
                2, 1, 14,           // Sophie Germain (2) has one child: Joseph Fourier (index: 14)
                // index: 11
                3, 1, 19,           // Felix Klein (3) has one child: David Hilbert (index: 19)
                // index: 14
                5, 1, 23,           // Joseph Fourier (5) has one child: Ada Lovelace (index: 23)
                // index: 17
                4, 0,               // Richard Dedekind (4) has no children
                // index: 19
                6, 2, 25, 27,       // David Hilbert (6) has children: Emmy Noether (index: 25), John von Neumann (index: 27)
                // index: 23
                9, 0,               // Ada Lovelace (9) has no children
                // index: 25
                7, 0,               // Emmy Noether (7) has no children
                // index: 27
                8, 1, 30,           // John von Neumann (8) has one child: Julia Robinson (index: 30)
                // index: 30
                10, 0,              // Julia Robinson (10) has no children
                // index: 32
                11, 1, 35,          // Terence Tao (11) has one child: Julia Garibaldi (index: 35)
                // index: 35
                12, 0               // Julia Garibaldi (12) has no children.
                // This input is invalid because Terence Tao is being added as a second root node.
        };

        String[] nodeNames = {
                "Carl Gauss",
                "Bernhard Riemann",
                "Sophie Germain",
                "Felix Klein",
                "Richard Dedekind",
                "Joseph Fourier",
                "David Hilbert",
                "Emmy Noether",
                "John von Neumann",
                "Ada Lovelace",
                "Julia Robinson",
                "Terence Tao",
                "Julia Garibaldi"
        };

        assertThrows(IllegalArgumentException.class,
                () -> new ArbitraryTree(arbitraryTree, nodeNames));
    }

    @Test
    public void testConstructorWithMissingNodeNames() {
        int[] arbitraryTree = {0, 1, 1};
        String[] nodeNames = {"Carl Gauss"};
        assertThrows(IllegalArgumentException.class, () -> {
            new ArbitraryTree(arbitraryTree, nodeNames);
        });
    }

    @Test
    public void testConstructorWithInvalidNodeReferences() {
        int[] arbitraryTree = {0, 2, 1, 20};
        String[] nodeNames = {"Carl Gauss", "Manwë", "Ulmo"};
        assertThrows(IllegalArgumentException.class, () -> {
            new ArbitraryTree(arbitraryTree, nodeNames);
        });
    }

    @Test
    public void testConstructorWithIncompleteEntries() {
        int[] arbitraryTree = {0, 2, 1}; // Missing child entry
        String[] nodeNames = {"Carl Gauss", "Manwë"};
        assertThrows(IllegalArgumentException.class, () -> {
            new ArbitraryTree(arbitraryTree, nodeNames);
        });
    }

    @Test
    public void testIsAncestorOf() {
        ArbitraryTree tree = new ArbitraryTree();
        tree.addRoot(0, "Carl Gauss");
        tree.addChildOf(0, 1, "Bernhard Riemann");
        tree.addChildOf(0, 2, "Sophie Germain");
        tree.addChildOf(1, 3, "Felix Klein");
        tree.addChildOf(3, 4, "David Hilbert");
        tree.addChildOf(4, 5, "Emmy Noether");

        assertTrue(tree.isAncestorOf(0, 1)); // Carl Gauss -> Bernhard Riemann
        assertTrue(tree.isAncestorOf(0, 5)); // Carl Gauss -> Emmy Noether
        assertTrue(tree.isAncestorOf(1, 4)); // Bernhard Riemann -> David Hilbert
        assertFalse(tree.isAncestorOf(2, 5)); // Sophie Germain is not an ancestor of Emmy Noether
        assertFalse(tree.isAncestorOf(3, 1)); // Felix Klein is not an ancestor of Bernhard Riemann
        assertFalse(tree.isAncestorOf(5, 4)); // Emmy Noether is not an ancestor of David Hilbert
    }

    @Test
    public void testIsDescendantOf() {
        ArbitraryTree tree = new ArbitraryTree();
        tree.addRoot(0, "Carl Gauss");
        tree.addChildOf(0, 1, "Bernhard Riemann");
        tree.addChildOf(0, 2, "Sophie Germain");
        tree.addChildOf(1, 3, "Felix Klein");
        tree.addChildOf(3, 4, "David Hilbert");
        tree.addChildOf(4, 5, "Emmy Noether");

        assertTrue(tree.isDescendantOf(1, 0)); // Bernhard Riemann is a descendant of Carl Gauss
        assertTrue(tree.isDescendantOf(5, 0)); // Emmy Noether is a descendant of Carl Gauss
        assertTrue(tree.isDescendantOf(4, 1)); // David Hilbert is a descendant of Bernhard Riemann
        assertFalse(tree.isDescendantOf(5, 2)); // Emmy Noether is not a descendant of Sophie Germain
        assertFalse(tree.isDescendantOf(1, 3)); // Bernhard Riemann is not a descendant of Felix Klein
        assertFalse(tree.isDescendantOf(4, 5)); // David Hilbert is not a descendant of Emmy Noether
    }

    @Test
    public void testRootAncestorAndDescendant() {
        ArbitraryTree tree = new ArbitraryTree();
        tree.addRoot(0, "Carl Gauss");
        tree.addChildOf(0, 1, "Bernhard Riemann");
        tree.addChildOf(0, 2, "Sophie Germain");
        tree.addChildOf(1, 3, "Felix Klein");

        assertTrue(tree.isAncestorOf(0, 3)); // Carl Gauss is ancestor of Felix Klein
        assertFalse(tree.isAncestorOf(3, 0)); // Felix Klein is not ancestor of Carl Gauss

        assertTrue(tree.isDescendantOf(3, 0)); // Felix Klein is descendant of Carl Gauss
        assertFalse(tree.isDescendantOf(0, 3)); // Carl Gauss is not descendant of Felix Klein
    }

    @Test
    public void testNonExistentNodes() {
        ArbitraryTree tree = new ArbitraryTree();
        tree.addRoot(0, "Carl Gauss");
        tree.addChildOf(0, 1, "Bernhard Riemann");

        assertFalse(tree.isAncestorOf(0, 99)); // Non-existent node
        assertFalse(tree.isAncestorOf(99, 1)); // Non-existent node
        assertFalse(tree.isDescendantOf(99, 0)); // Non-existent node
        assertFalse(tree.isDescendantOf(1, 99)); // Non-existent node
    }
}