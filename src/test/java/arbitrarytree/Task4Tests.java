package arbitrarytree;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Task4Tests {
    @Test
    public void testGetTotalReward() {
        /* Simple Test:
        Gauss has family size of 2 for a reward of 3.5;
        Riemann and Germain have a family size of 1 each for a reward of 1.0 each;
        total reward is 5.5.
         */
        ArbitraryTree tree = new ArbitraryTree();
        tree.addRoot(0, "Carl Gauss");
        tree.addChildOf(0, 1, "Bernhard Riemann");
        tree.addChildOf(0, 2, "Sophie Germain");

        double[] rewards = {0.0, 1.0, 3.5};

        assertEquals(5.5, tree.totalReward(rewards));
    }

    @Test
    public void testGetTotalReward_SingleNodeTree() {
        ArbitraryTree tree = new ArbitraryTree();
        tree.addRoot(0, "Carl Gauss");
        double[] rewards = {0.5, 1.0, 3.5};
        assertEquals(0.5, tree.totalReward(rewards));
    }

    @Test
    public void testRewardIncrease_growSubTree() {
        ArbitraryTree tree = new ArbitraryTree();
        tree.addRoot(0, "Carl Gauss");
        tree.addChildOf(0, 1, "Bernhard Riemann");
        tree.addChildOf(0, 2, "Sophie Germain");
        double[] rewards = {0.0, 1.0, 3.5, 4.0};
        assertEquals(3.5, tree.rewardIncreaseWithSingleNodeAddition(rewards), 1e-5);
    }

    @Test
    public void testRewardIncrease_growRoot() {
        ArbitraryTree tree = new ArbitraryTree();
        tree.addRoot(0, "Carl Gauss");
        tree.addChildOf(0, 1, "Bernhard Riemann");
        tree.addChildOf(0, 2, "Sophie Germain");
        double[] rewards = {0.0, 1.0, 3.5, 7.0};
        assertEquals(4.5, tree.rewardIncreaseWithSingleNodeAddition(rewards), 1e-5);
    }

    @Test
    public void testRewardIncrease_addIntermediateLeaf() {
        ArbitraryTree tree = new ArbitraryTree();
        tree.addRoot(0, "Carl Gauss");
        tree.addChildOf(0, 1, "Bernhard Riemann");
        tree.addChildOf(0, 2, "Sophie Germain");
        tree.addChildOf(1, 3, "Maryam Mirzakhani");
        tree.addChildOf(2, 4, "Manjul Bhargava");
        double[] rewards = {0.0, 3.0, 3.5, 4.5, 5.0, 100.0};
        assertEquals(4.0, tree.rewardIncreaseWithSingleNodeAddition(rewards), 1e-5);
    }

    @Test
    public void testRewardIncrease_addBottomLeaf() {
        ArbitraryTree tree = new ArbitraryTree();
        tree.addRoot(0, "Carl Gauss");
        tree.addChildOf(0, 1, "Bernhard Riemann");
        tree.addChildOf(0, 2, "Sophie Germain");
        tree.addChildOf(1, 3, "Maryam Mirzakhani");
        tree.addChildOf(2, 4, "Manjul Bhargava");
        double[] rewards = {0.0, 3.0, 4.1, 4.5, 5.0, 100.0};
        assertEquals(4.1, tree.rewardIncreaseWithSingleNodeAddition(rewards), 1e-5);
    }
}