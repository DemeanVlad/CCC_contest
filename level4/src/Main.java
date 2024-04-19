import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            File inputFile = new File("src/level4.txt");
            Scanner scanner = new Scanner(inputFile);

            int N = scanner.nextInt(); // Number of lawns
            scanner.nextLine(); // Consume the newline after N

            StringBuilder output = new StringBuilder();

            for (int n = 0; n < N; n++) {
                int rows = scanner.nextInt(); // Lawn height
                int cols = scanner.nextInt(); // Lawn width
                scanner.nextLine(); // Consume the newline after cols

                // Create the matrix and initialize all cells with 0
                char[][] matrix = new char[rows][cols];

                // Mark the position of 'X' in the matrix
                int xRow = -1, xCol = -1;
                for (int i = 0; i < rows; i++) {
                    String line = scanner.nextLine();
                    if (line.length() < cols) {
                        throw new IllegalArgumentException("The line does not have enough characters for the matrix.");
                    }
                    for (int j = 0; j < cols; j++) {
                        matrix[i][j] = line.charAt(j);
                        if (matrix[i][j] == 'X') {
                            xRow = i;
                            xCol = j;
                        }
                    }
                }

                // Traverse the sequence to obtain the path
                int currentRow = xRow;
                int currentCol = xCol;
                StringBuilder path = new StringBuilder();

                String sequence = scanner.nextLine();

                for (char move : sequence.toCharArray()) {
                    // Update the position based on the move
                    if (move == 'W' && currentRow > 0) {
                        currentRow--;
                    } else if (move == 'A' && currentCol > 0) {
                        currentCol--;
                    } else if (move == 'S' && currentRow < rows - 1) {
                        currentRow++;
                    } else if (move == 'D' && currentCol < cols - 1) {
                        currentCol++;
                    }

                    // Add the move to the path
                    path.append(move);
                }

                // Append the path to the output
                output.append(path.toString()).append("\n");
            }

            System.out.println(output.toString());

            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
