// Importing libraries
import java.util.Scanner;

public class WampusWorld {
    // Method to print the board
    public static void printbox(String[][] box) {
        for (int i = 0; i < box.length; i++) {
            for (int j = 0; j < box[0].length; j++) {
                System.out.print(box[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Method to check the new position
    public static boolean checksq(String[][] box, int row, int col) {
        String s = box[row][col];
        String[] goal = s.split("/");
        for (int i = 0; i < goal.length; i++) {
            if (goal[i].equals("G")) {
                System.out.println("You found the gold! You win!");
                return false; // End the game
            }
            if (goal[i].equals("P") || goal[i].equals("W")) {
                System.out.println("Agent died - Game Over");
                return false; // End the game
            }
        }
        return true; // Continue playing
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // Initializing 4x4 WampusWorld board
        String box[][] = {
            {"S", "E", "B", "P"},
            {"W", "S/G/B", "P", "B"},
            {"S", "E", "B", "E"},
            {"A", "B", "P", "B"}
        };

        printbox(box);

        // Initializing start position
        int row = box.length - 1;
        int col = 0;
        boolean check = true;

        // Main game loop
        do {
            System.out.println("Choose the move\nleft:l right:r\nup:u   down:d break:b");
            String ch = sc.next();

            switch (ch) {
                case "l":
                    if (col == 0) {
                        System.out.println("Can't move - end of the box");
                    } else {
                        col--; // Move left
                        check = checksq(box, row, col); // Check new position
                        if (check && !box[row][col].contains("/VA")) {
                            box[row][col] += "/VA"; // Mark as visited
                        }
                    }
                    break;

                case "r":
                    if (col == box[0].length - 1) {
                        System.out.println("Can't move - end of the box");
                    } else {
                        col++; // Move right
                        check = checksq(box, row, col); // Check new position
                        if (check && !box[row][col].contains("/VA")) {
                            box[row][col] += "/VA"; // Mark as visited
                        }
                    }
                    break;

                case "u":
                    if (row == 0) {
                        System.out.println("Can't move - end of the box");
                    } else {
                        row--; // Move up
                        check = checksq(box, row, col); // Check new position
                        if (check && !box[row][col].contains("/VA")) {
                            box[row][col] += "/VA"; // Mark as visited
                        }
                    }
                    break;

                case "d":
                    if (row == box.length - 1) {
                        System.out.println("Can't move - end of the box");
                    } else {
                        row++; // Move down
                        check = checksq(box, row, col); // Check new position
                        if (check && !box[row][col].contains("/VA")) {
                            box[row][col] += "/VA"; // Mark as visited
                        }
                    }
                    break;

                case "b":
                    check = false;
                    System.out.println("Breaking the loop");
                    break;

                default:
                    System.out.println("Invalid choice. Choose again:");
            }

            printbox(box); // Print the updated board
        } while (check);

        System.out.println("Game Over!");
    }
}
