package arbitrarytree;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task5Tests {
    @Test
    public void testMaxPossibleReward_1() {
        double[] rewards = {0.0, 7.0, 0.0, 0.0};
        assertEquals(21.0, ArbitraryTree.maxPossibleReward(rewards));
    }

    @Test
    public void testMaxPossibleReward_2() {
        double[] rewards = {0.0, 1.0, 3.0, 0.0};
        assertEquals(8.0, ArbitraryTree.maxPossibleReward(rewards));
    }

    @Test
    public void testMaxPossibleReward_3() {
        double[] rewards = {0.0, 1.0, 3.0, 2.0, 5.0, 3.0, 7.0, 5.0};
        assertEquals(20.0, ArbitraryTree.maxPossibleReward(rewards));
    }

    @Test
    /* Case of a single-node tree.
       This is a special case and the only time
       one should use rewards[0]. */
    public void testMaxPossibleReward_4() {
        double[] rewards = {5.0};
        assertEquals(5.0, ArbitraryTree.maxPossibleReward(rewards));
    }
}
