import  java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    // Define the directions associated with path characters
    static final int[][] dir = { {-1, 0}, {0, 1}, {1, 0}, {0, -1} };
    static final String directions = "WDSA";

    static boolean isValid(int x, int y, int height, int width) {
        return x >= 0 && x < height && y >= 0 && y < width;
    }

    static String validatePath(char[][] lawn, int startX, int startY, String path, int height, int width) {
        boolean[][] visited = new boolean[height][width];
        int x = startX, y = startY;
        visited[x][y] = true;  // Start position is marked as visited
        int freeCells = 0;

        // Count free cells and ensure the starting position is valid
        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                if (lawn[i][j] == '.') freeCells++;
            }
        }

        for (char step : path.toCharArray()) {
            int index = directions.indexOf(step);
            x += dir[index][0];
            y += dir[index][1];

            if (!isValid(x, y, height, width) || lawn[x][y] == 'X' || visited[x][y]) {
                return "INVALID";
            }
            visited[x][y] = true;
        }

        int visitedCount = 0;
        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                if (visited[i][j]) visitedCount++;
            }
        }

        if (visitedCount == freeCells) return "VALID";
        return "INVALID";
    }

    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(new File("level3_1.in"));
            FileWriter outFile = new FileWriter("output.out");

            int N = Integer.parseInt(scanner.nextLine());

            for (int t = 0; t < N; ++t) {
                String[] dimensions = scanner.nextLine().split(" ");
                int width = Integer.parseInt(dimensions[0]);
                int height = Integer.parseInt(dimensions[1]);

                char[][] lawn = new char[height][width];
                for (int i = 0; i < height; ++i) {
                    lawn[i] = scanner.nextLine().toCharArray();
                }

                String path = scanner.nextLine().trim();

                boolean anyValid = false;
                for (int i = 0; i < height && !anyValid; ++i) {
                    for (int j = 0; j < width && !anyValid; ++j) {
                        if (lawn[i][j] == '.' && validatePath(lawn, i, j, path, height, width).equals("VALID")) {
                            outFile.write("VALID\n");
                            anyValid = true;
                        }
                    }
                }

                if (!anyValid) outFile.write("INVALID\n");
            }

            outFile.close();
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
