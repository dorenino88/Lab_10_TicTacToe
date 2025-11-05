import java.util.Scanner;

public  class Main
{
    final static int ROW = 3;
    final static int COL = 3;
    static String board[][] = new String[ROW][COL];

    public static void main(String[] args)
    {

        Scanner in = new Scanner(System.in);
        boolean playAgain = true;
        String currentPlayer = "X";

        while (playAgain)
        {
            clearBoard();
            boolean gameOver = false;
            int moveCounter = 0;

            while (!gameOver) {
                printBoard();
                System.out.println("It's player " + currentPlayer + "'s turn");
                int row = SafeInput.getRangedInt(in, "Enter row between ", 0, 2);
                int col = SafeInput.getRangedInt(in, "Enter column between ", 0, 2);

                if (isValidMove(row, col)) {
                    board[row][col] = currentPlayer;
                    moveCounter++;
                    if (moveCounter >= 5 && isWin(currentPlayer)) {
                        printBoard();
                        System.out.println("Player " + currentPlayer + " win");
                        gameOver = true;
                    } else if (moveCounter == 9) {
                        printBoard();
                        System.out.println("It's a tie");
                        gameOver = true;
                    } else {
                        if (currentPlayer.equals("X")) {
                            currentPlayer = "O";
                        } else {
                            currentPlayer = "X";
                        }
                    }
                } else {
                    System.out.println("The space is taken. Please choose another spot");
                }
            }
            playAgain = SafeInput.getYNConfirm(in, "Do you want to play again?");
            System.out.println("Thanks for playing!");
            System.out.println();
            currentPlayer = "X";
        }

    }


    private static void clearBoard()
    {
        for (int r = 0;r < ROW;r++)
        {
            for (int c = 0;c<COL;c++)
            {
                board[r][c] = "";
            }
        }
    }

    private static void printBoard()
    {
        for (int r = 0;r < ROW;r++)
        {
            for (int c = 0;c<COL;c++)
            {
                System.out.printf("|%3s", board[r][c]);
            }
            System.out.println("|");
        }
    }

    private static boolean isValidMove(int row, int col)
    {
        return board[row][col].equals("");
    }

    //row win
    private static boolean isRowWin(String player)
    {
        for (int r = 0;r<ROW;r++)
        {
            if(board[r][0].equals(player) && board[r][1].equals(player) && board[r][2].equals(player))
            {
                return true;
            }
        }
        return false;
    }

    //column win
    private static boolean isColWin(String player)
    {
        for (int c = 0;c<COL;c++)
        {
            if(board[0][c].equals(player) && board[1][c].equals(player) && board[2][c].equals(player))
            {
                return true;
            }
        }
        return false;
    }

    //diagonal win
    private static boolean isDiagonalWin(String player)
    {
        if (board[0][0].equals(player) && board[1][1].equals(player) && board[2][2].equals(player))
        {
            return true;
        }
        else if (board[0][2].equals(player) && board[1][1].equals(player) && board[2][0].equals(player))
        {
            return true;
        }
        else return false;
    }

    private static boolean isWin(String player)
    {
        if(isRowWin(player))
        {
            return true;
        }
        else if (isColWin(player))
        {
            return true;
        }
        else if (isDiagonalWin(player))
        {
            return true;
        }
        else return false;
   }
}
