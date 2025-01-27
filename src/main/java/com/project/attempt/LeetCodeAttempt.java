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

    // This method will number of points returned by the second robot,
    // as per challenge specifications.
    public static int gridGame(int[][] grid) {

        // We will use a Linked List to store the path that the first robot takes.
        // The first index will count the points collected by the first robot, while
        // every index after that will store each position (grid[][] index) that
        // the robot travelled through in the array.
        LinkedList<Integer> firstPath = new LinkedList<>();
        firstPath.add(0);

        // We call a recursive function here, which will return the maximum points that
        // the first robot can obtain, and also the path needed to be traversed to attain it.
        firstPath = gridGameRecursive(grid, 0, 0, firstPath);

        // As per challenge instructions, we set each position that the first robot travelled
        // through to 0 so that the second robot can't accumulate points at those positions.
        // We start at firstPath[1] because firstPath[0] contains the robot's total points and
        // not any of the positions it traversed through.
        for (int i = 1; i < firstPath.size(); i++) {

            if (firstPath.get(i) < grid[0].length) {
                grid[0][firstPath.get(i)] = 0;
            } else if (firstPath.get(i) >= grid[0].length) {
                grid[1][firstPath.get(i) - grid[0].length] = 0;
            }

        }

        // Like we did with the first robot, we use a Linked List to store the path
        // that the second robot takes, as well as the total points it accumulated.
        LinkedList<Integer> secondPath = new LinkedList<>();
        secondPath.add(0);

        secondPath = gridGameRecursive(grid, 0, 0, secondPath);

        // We return the total points that the second robot accumulated.
        return secondPath.get(0);

    }

    // A recursive helper method that traverses through the grid[][] array and tests all
    // possible directions that the robot could take. It will return a Linked List that
    // contains the total points accumulated by the robot, as well as the path taken by it.
    private static LinkedList<Integer> gridGameRecursive(int[][] grid, int x, int y, LinkedList<Integer> path) {

        // At the start of the method, we create a new LinkedList containing the values of path
        // to avoid issues caused by recursive calls of the method. We then update the value at
        // index 0 (which is meant to contain the total 'points' accumulated by the robot so far)
        // and also add a new value to the list that indicates the robot's current position. We
        // represent the x-y coordinates with a single integer to save on confusion.
        LinkedList<Integer> cPath = new LinkedList<>(path);
        cPath.set(0, cPath.get(0) + grid[x][y]);
        cPath.add(x * grid[0].length + y);

        // If we're at the final index of grid[][], simply return the path linked list as it is.
        // Otherwise, if we've already went down once, we can't go down anymore so simply go right.
        // And if we're all the way at the rightmost position, go down as that's the only direction left.
        if (x == 1 && y == grid[0].length - 1) { return cPath; }
        if (x == 1 && y < grid[0].length - 1) { return gridGameRecursive(grid, x, y + 1, cPath); }
        if (x < 1 && y == grid[0].length - 1) { return gridGameRecursive(grid, x + 1, y, cPath); }

        // If both right and down are possible options, then we try both directions.
        // We call a recursive function for both possibilities, and return the path
        // that ends up having accumulated more points.
        LinkedList<Integer> downPath = gridGameRecursive(grid, x + 1, y, cPath);
        LinkedList<Integer> rightPath = gridGameRecursive(grid, x, y + 1, cPath);

        if (downPath.get(0) > rightPath.get(0)) { return downPath;}
        else { return rightPath; }

    }

}
