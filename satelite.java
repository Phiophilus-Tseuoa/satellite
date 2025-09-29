package satellite;

import java.io.*;

public class Satellite {
    static int[][] oldImage, newImage;
    static int noOfRows, noOfCols;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        noOfRows = Integer.parseInt(reader.readLine().trim());
        noOfCols = Integer.parseInt(reader.readLine().trim());
        oldImage = new int[noOfRows][noOfCols];
        newImage = new int[noOfRows][noOfCols];

        readImage(reader, oldImage);
        readImage(reader, newImage);
        reader.close();

        int x1 = findBoundary(true, true);
        int x2 = findBoundary(true, false);
        int y1 = findBoundary(false, true);
        int y2 = findBoundary(false, false);

        printResult(x1, y1, x2, y2);
    }

    static void readImage(BufferedReader reader, int[][] image) throws IOException {
        for (int r = 0; r < noOfRows; r++) {
            String[] parts = reader.readLine().trim().split("\\s+");
            for (int c = 0; c < noOfCols; c++) image[r][c] = Integer.parseInt(parts[c]);
        }
    }

    static int findBoundary(boolean isRow, boolean fromStart) {
        int start = fromStart ? 0 : (isRow ? noOfRows - 1 : noOfCols - 1);
        int step = fromStart ? 1 : -1;
        while (withinBounds(start, isRow) && equalLine(start, isRow)) start += step;
        return start;
    }

    static boolean withinBounds(int idx, boolean isRow) {
        return isRow ? (idx >= 0 && idx < noOfRows) : (idx >= 0 && idx < noOfCols);
    }

    static boolean equalLine(int idx, boolean isRow) {
        if (isRow) for (int c = 0; c < noOfCols; c++) if (oldImage[idx][c] != newImage[idx][c]) return false;
        else for (int r = 0; r < noOfRows; r++) if (oldImage[r][idx] != newImage[r][idx]) return false;
        return true;
    }

    static void printResult(int x1, int y1, int x2, int y2) {
        if (x1 > x2 || y1 > y2) System.out.println("The two images are the same");
        else System.out.println((x1+1) + " " + (y1+1) + " " + (x2+1) + " " + (y2+1));
    }
}
