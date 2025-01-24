package com.project.attempt;

import java.util.LinkedList;

public class LeetCodeAttempt {

    public static void main(String[] args) {

        int[][] grid1 = new int[][]{{2,5,4},{1,5,1}};
        System.out.println(gridGame(grid1));

        int[][] grid2 = new int[][]{{3,3,1},{8,5,2}};
        System.out.println(gridGame(grid2));

        int[][] grid3 = new int[][]{{1,3,1,15},{1,3,3,1}};
        System.out.println(gridGame(grid3));

    }

    public static int gridGame(int[][] grid) {

        LinkedList<Integer> firstPath = new LinkedList<>();
        firstPath.add(0);

        LinkedList<Integer> firstRobot = gridGameRecursive(grid, 0, 0, firstPath);
        // System.out.println(firstRobot);

        for (int i = 1; i < firstRobot.size(); i++) {

            if (firstRobot.get(i) < grid[0].length) {
                grid[0][firstRobot.get(i)] = 0;
            } else if (firstRobot.get(i) >= grid[0].length) {
                grid[1][firstRobot.get(i) - grid[0].length] = 0;
            }

        }

        LinkedList<Integer> secondPath = new LinkedList<>();
        secondPath.add(0);

        LinkedList<Integer> secondRobot = gridGameRecursive(grid, 0, 0, secondPath);
        // System.out.println(secondRobot);

        return secondRobot.get(0);

    }

    private static LinkedList<Integer> gridGameRecursive(int[][] grid, int x, int y, LinkedList<Integer> path) {

        LinkedList<Integer> cPath = new LinkedList<>(path);
        cPath.set(0, cPath.get(0) + grid[x][y]);
        cPath.add(x * grid[0].length + y);

        if (x == 1 && y == grid[0].length - 1) {
            return cPath;
        }

        if (x == 1 && y < grid[0].length - 1) { return gridGameRecursive(grid, x, y + 1, cPath); }
        if (x < 1 && y == grid[0].length - 1) { return gridGameRecursive(grid, x + 1, y, cPath); }

        LinkedList<Integer> downPath = gridGameRecursive(grid, x + 1, y, cPath);
        LinkedList<Integer> rightPath = gridGameRecursive(grid, x, y + 1, cPath);

        if (downPath.get(0) > rightPath.get(0)) { return downPath;}
        else { return rightPath; }

    }

}
