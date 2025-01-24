package com.project;

import com.project.solution.LeetCodeSolution;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class LeetCodeSolutionTest {

    @Test
    public void gridGameTest() {

        int[][] grid1 = new int[][]{{2,5,4},{1,5,1}};
        assertEquals(4, LeetCodeSolution.gridGame(grid1));

        int[][] grid2 = new int[][]{{3,3,1},{8,5,2}};
        assertEquals(4, LeetCodeSolution.gridGame(grid2));

        int[][] grid3 = new int[][]{{1,3,1,15},{1,3,3,1}};
        assertEquals(7, LeetCodeSolution.gridGame(grid3));

    }

}
