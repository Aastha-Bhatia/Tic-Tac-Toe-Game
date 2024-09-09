
// importing all the libraries
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TicTacToe {
    // dimentions of our game
    int boardWidth = 600;
    // 50 px for the text on top
    int boardHeight = 650;
    // created our frame
    JFrame frame = new JFrame("Tic-Tac-Toe"); // sets the title

    // adding panel for our text
    // Text label
    JLabel textLabel = new JLabel();
    // for our text label, we need out textpanel.
    JPanel textPanel = new JPanel();

    // we are creating our board now
    JPanel boardPanel = new JPanel();

    JPanel footerPanel = new JPanel();
    final JLabel footerLabel = new JLabel("Developed by Aastha Bhatia");

    // we are creating an array now of 3x3 size using jbuttons to keep track of all
    // the buttons of the board.
    JButton[][] board = new JButton[3][3];
    // 2 players
    String playerX = "X";
    String playerO = "O";
    // current player set- X
    String currentPlayer = playerX;

    boolean gameOver = false;
    int turns=0;  

    // constructor with properties
    TicTacToe() {
        // makes the frame visible
        frame.setVisible(true);
        // sets the size
        frame.setSize(boardWidth, boardHeight);
        // it will make sure the frame is at the center of the screen no matter what the
        // dimentions of the device is
        frame.setLocationRelativeTo(null);
        // we cannot resize the frame by writing this.
        frame.setResizable(false);
        // this makes a closing option or exit option.
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // sets the background of the text
        textLabel.setBackground(Color.decode("#8B7B8B"));
        // sets the foreground of the text
        textLabel.setForeground(Color.BLACK);
        // font and font size
        textLabel.setFont(new Font("Arial", Font.BOLD, 50));
        // this makes the text allign at the center
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        // sets the text
        textLabel.setText("Tic-Tac-Toe");
        // makes it opaque
        textLabel.setOpaque(true);

        // now we will put this text into text panel.
        textPanel.setLayout(new BorderLayout());
        // adding text to textpanel
        textPanel.add(textLabel);
        // adding panel to the frame
        frame.add(textPanel, BorderLayout.NORTH); // sets it in north direction

        // BOARD PANEL
        // it will make a grid layout with 3 rows and 3 columns
        boardPanel.setLayout(new GridLayout(3, 3));
        // sets background
        boardPanel.setBackground(Color.decode("#CDB5CD"));
        // adding board panel to the frame
        frame.add(boardPanel);

        // adding buttons now
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                JButton tile = new JButton();
                board[i][j] = tile;
                boardPanel.add(tile);

                tile.setBackground(Color.decode("#CDB5CD"));
                tile.setForeground(Color.BLACK);
                tile.setFont(new Font("Arial", Font.BOLD, 120));
                // on clicking, now no border like focus will be seen
                tile.setFocusable(false);
                // it will set X on each tile.
                // tile.setText(currentPlayer);

                tile.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (gameOver)
                            return;
                        JButton tile = (JButton) e.getSource();
                        // it will set the text to x only when the tile is clicked now

                        // we do not want overlapping. and we need the player to click only if the tile
                        // is empty. so, we need to check
                        if (tile.getText() == "") {
                            tile.setText(currentPlayer);
                            turns++;
                            // we have to check winner and if there is a winner, then we do not have to
                            // proxeed the game further.
                            checkWinner();

                            if (!gameOver) {
                                // this is a ternary operator, it will check if current player is equal to X, if
                                // yes, it will change it into player O and if it is player O, it will change it
                                // into player X
                                currentPlayer = currentPlayer == playerX ? playerO : playerX;
                                // also, we need to change the text now to whose turn it is
                                textLabel.setText(currentPlayer + "'s turn. ");
                            }
                        }

                    }
                });
            }
        }
        
        // Footer Panel for developer credit
        footerLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        footerLabel.setHorizontalAlignment(JLabel.CENTER);
        footerPanel.setLayout(new BorderLayout());
        footerPanel.add(footerLabel, BorderLayout.CENTER);
        footerLabel.setFocusable(false);
        footerPanel.setBackground(Color.decode("#8B7B8B")); 
        frame.add(footerPanel, BorderLayout.SOUTH); // Add footer panel to the south
    }

    void checkWinner() {
        // check horizontally
        for (int i = 0; i < 3; i++) {
            // if the first row is empty, we do need to check for the next ones.
            if (board[i][0].getText() == "") {
                continue;
            }
            // if three are equal horizontally, we need to stop the game.
            if (board[i][0].getText() == board[i][1].getText() && board[i][1].getText() == board[i][2].getText()) {
                for (int a = 0; a < 3; a++) {
                    // if the three in a row becomes equal, we will call our setwinner function.
                    setWinner(board[i][a]);
                }
                gameOver = true;
                return;
            }
        }
        // now we have to check vertically.
        for (int j = 0; j < 3; j++) {
            // if our column is empty, we do not need to chrck for further as three will not
            // be found in columns.
            if (board[0][j].getText() == "") {
                continue;
            }
            if (board[0][j].getText() == board[1][j].getText() && board[1][j].getText() == board[2][j].getText()) {
                for (int b = 0; b < 3; b++) {
                    setWinner(board[b][j]);
                }
                gameOver = true;
                return;
            }
        }
        // now we need to check diagonally.
        // for our primary diagonal,((i,j) is equal everytime)
        // so we need to check if each value of diagonal is equal or not, and it should
        // not be empty, if they all are equal, set the winner
        if (board[0][0].getText() == board[1][1].getText() && board[1][1].getText() == board[2][2].getText()
                && board[0][0].getText() != "") {
            for (int i = 0; i < 3; i++) {
                // board[i][i] because i, j are equal in case of primary diagonal
                setWinner(board[i][i]);
            }
            gameOver = true;
            return;
        }
        // now we have to check for secondary diagonal too
        if(board[0][2].getText()==board[1][1].getText() && board[1][1].getText()==board[2][0].getText() && board[0][2].getText()!=""){
            // we will individually call setwinner for this case.
            setWinner(board[0][2]);
            setWinner(board[1][1]);
            setWinner(board[2][0]);
            gameOver=true;
            return;
        }

        // we still have 1 condition left, that is tie.
        if(turns==9){
            for(int i=0;i<3;i++){
                for(int j=0;j<3;j++){
                    setTie(board[i][j]);
                }
            }
            gameOver=true;
            return;
        }

    }

    void setWinner(JButton tile) {
        tile.setBackground(Color.decode("#008000"));
        tile.setForeground(Color.decode("#00CD00"));
        textLabel.setText(currentPlayer + " is the Winner!");
    }
    void setTie(JButton tile){
        tile.setBackground(Color.decode("#8B7B8B"));
        tile.setForeground(Color.decode("#DC143C"));
        textLabel.setText("It's a Tie!");
    }
}
