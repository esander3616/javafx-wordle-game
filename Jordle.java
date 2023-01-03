import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.FontPosture;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import java.util.Random;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Background;
import javafx.scene.control.CheckBox;


/**
 * Jordle class to write the GUI and other helper methods.
 * @author Eric Sander
 * @version java 11.0.13
 */
public class Jordle extends Application {
    public static boolean isDark = false;
    /**
     * Helper method to get the word from the Word list.
     * @return a random word from the word list
     */
    public static String getWord() {
        Random random = new Random();
        int num = random.nextInt(29);
        return Words.list.get(num);
    }

    public static String theWord = getWord();

    /**
     * Helper method to check if the game is not done.
     * @param row the current row of the grid the player is on
     * @param rows the 2d array representing each button in the grid
     * @return a boolean determining if the game is not done
     */
    public static boolean isNotDone(int row, Button[][] rows) {
        if (row == 0) {
            return true;
        } else {
            int newRow = row - 1;
            int greens = 0;
            for (int i = 0; i < 5; i++) {
                String boxColor = rows[newRow][i].getStyle();
                boxColor = boxColor.isEmpty() ? boxColor : boxColor.substring(23);
                if (boxColor.equals("00ff00")) {
                    greens += 1;
                }
            }
            if (greens == 5) {
                return false;
            }
            return true;
        }
    }

    /**
     * Helper method to check the input character and compare it to the solution word's corresponding index.
     * @param character input character
     * @param index the index of that character
     * @return an in representing if it was the same as the character in the solution word's corresponding index
     */
    public static int checkChar(char character, int index) {
        String inputChar = String.valueOf(character);
        String keyChar = String.valueOf(theWord.charAt(index));
        if (inputChar.equals(keyChar)) {
            return 1;
        } else {
            for (int i = 0; i < 5; i++) {
                String testChar = String.valueOf(theWord.charAt(i));
                if (inputChar.equals(testChar)) {
                    return 0;
                }
            }
            return -1;
        }
    }

    /**
     * Helper method to determine which row of the grid the user is on.
     * @param rows the 2d array representing each button in the grid
     * @return the active row index
     */
    public static int activeRow(Button[][] rows) {
        for (int i = 0; i < 6; i++) {
            if (rows[i][0].getText().isEmpty()) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Helper method to determine if the input string is a 5-letter word with characters in the alphabet.
     * @param input the input guess
     * @return a boolean determining if the input was valid
     */
    public static boolean validInput(String input) {
        if (input.length() == 5) {
            int valid = 0;
            for (int i = 0; i < 5; i++) {
                char ch = input.charAt(i);
                if ((ch >= 'A' && ch <= 'Z') || (ch >= 'a' && ch <= 'z')) {
                    valid += 1;
                }
            }
            if (valid == 5) {
                return true;
            }
        }
        return false;
    }

    /**
     * Changes the backround and label/button colors of the GUI, toggles between light and dark.
     * @param dark checkbox title
     * @param root the StackPane
     * @param title the Text at the top of the GUI
     * @param inputLabel The Label above the text box
     */
    public static void setDark(CheckBox dark, StackPane root, Text title, Label inputLabel) {
        if (dark.getTextFill().equals(Color.BLACK)) {
            isDark = true;
            dark.setTextFill(Color.WHITE);
            title.setStyle("-fx-fill: #ffffff");
            inputLabel.setTextFill(Color.WHITE);
            root.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        } else {
            isDark = false;
            dark.setTextFill(Color.BLACK);
            title.setStyle("-fx-fill: #000000");
            inputLabel.setTextFill(Color.BLACK);
            root.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        }
    }

    /**
     * Helper method that resets the grid and the inputLabel as well as selects a new word for the puzzle.
     * @param rows the 2d array representing each button in the grid
     * @param inputLabel the label above the text box with instructions for the user
     * @param tbox the Text Field that needs to be cleared when resetGame is run
     */
    public static void resetGame(Button[][] rows, Label inputLabel, TextField tbox) {
        theWord = getWord();
        tbox.setText(null);
        for (int r = 0; r < 6; r++) {
            for (int c = 0; c < 5; c++) {
                rows[r][c].setText("");
                rows[r][c].setStyle(null);
            }
        }
        inputLabel.setText("Enter a 5 letter word and press ENTER ");
        if (!isDark) {
            inputLabel.setTextFill(Color.BLACK);
        } else {
            inputLabel.setTextFill(Color.WHITE);
        }
    }

    /**
     * Main method to test the program.
     * @param args paramter that passes arguments from the command line
     */
    public static void main(String[] args) {
        launch(args);
    }
    @Override

    /**
     * The main method for the javafx GUI.
     */
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Jordle");

        StackPane root = new StackPane();
        GridPane grid = new GridPane();
        TextField tbox = new TextField();
        Label inputLabel = new Label("Enter a 5 letter word and press ENTER");

        Text title = new Text("JORDLE");
        title.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 30));

        Button one1 = new Button();
        Button one2 = new Button();
        Button one3 = new Button();
        Button one4 = new Button();
        Button one5 = new Button();
        Button two1 = new Button();
        Button two2 = new Button();
        Button two3 = new Button();
        Button two4 = new Button();
        Button two5 = new Button();
        Button three1 = new Button();
        Button three2 = new Button();
        Button three3 = new Button();
        Button three4 = new Button();
        Button three5 = new Button();
        Button four1 = new Button();
        Button four2 = new Button();
        Button four3 = new Button();
        Button four4 = new Button();
        Button four5 = new Button();
        Button five1 = new Button();
        Button five2 = new Button();
        Button five3 = new Button();
        Button five4 = new Button();
        Button five5 = new Button();
        Button six1 = new Button();
        Button six2 = new Button();
        Button six3 = new Button();
        Button six4 = new Button();
        Button six5 = new Button();

        for (Button button : new Button[]{one1, one2, one3, one4, one5, two1, two2, two3, two4, two5, three1, three2,
            three3, three4, three5, four1, four2, four3, four4, four5, five1,
            five2, five3, five4, five5, six1, six2, six3, six4, six5}) {
            button.setPrefSize(40, 40);
            button.setTextAlignment(TextAlignment.CENTER);
        }

        Button[][] rows = new Button[][]{{one1, one2, one3, one4, one5}, {two1, two2, two3, two4, two5},
            {three1, three2, three3, three4, three5}, {four1, four2, four3, four4, four5},
            {five1, five2, five3, five4, five5}, {six1, six2, six3, six4, six5}};

        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                String input = tbox.getText();
                int row = activeRow(rows);
                if (row != -1 && isNotDone(row, rows)) {
                    if (validInput(input)) {
                        tbox.setText(null);
                        for (int i = 0; i < 5; i++) {
                            rows[row][i].setText((String.valueOf(input.charAt(i))).toUpperCase());
                            int test = checkChar(input.charAt(i), i);
                            if (test == 1) {
                                rows[row][i].setStyle("-fx-background-color: #00ff00");
                            } else if (test == 0) {
                                rows[row][i].setStyle("-fx-background-color: #ffff00");
                            } else {
                                rows[row][i].setStyle("-fx-background-color: #808080");
                            }
                        }
                        if (!isNotDone(row + 1, rows)) {
                            inputLabel.setText("Congratulations! You guessed the word!");
                            inputLabel.setTextFill(Color.GREEN);
                        }
                        if (row == 5) {
                            inputLabel.setText("GAME OVER! the word was: " + theWord.toUpperCase());
                            inputLabel.setTextFill(Color.RED);
                        }
                    } else {
                        inputLabel.setText("Please enter a valid 5 letter word, then press ENTER");
                    }
                }
            }
        };

        tbox.setOnAction(event);
        grid.addRow(0, one1, one2, one3, one4, one5);
        grid.addRow(1, two1, two2, two3, two4, two5);
        grid.addRow(2, three1, three2, three3, three4, three5);
        grid.addRow(3, four1, four2, four3, four4, four5);
        grid.addRow(4, five1, five2, five3, five4, five5);
        grid.addRow(5, six1, six2, six3, six4, six5);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setAlignment(Pos.CENTER);

        Button infoButton = new Button("INSTRUCTIONS");
        root.setAlignment(infoButton, Pos.TOP_LEFT);
        infoButton.setPrefSize(150, 20);
        infoButton.setTextAlignment(TextAlignment.CENTER);
        Stage infoStage = new Stage();
        Label infoLabel = new Label("Enter a 5 letter word into the text box below the grid\n"
                + " then press enter to input your guess. If a box turns yellow then the letter\n"
                + " is present in the word but in the wrong position. If a box turns green then the letter\n"
                + " is present in the word and in the right position. If a box does not change color\n"
                + " then the letter is not present in the word. You are allowed 6 guesses total. Pressing the\n"
                + " reset button will clear the board and change the word.");
        infoStage.setTitle("Instructions");
        infoLabel.setAlignment(Pos.CENTER);
        infoStage.setScene(new Scene(infoLabel, 500, 400));

        infoButton.setOnAction(e ->
                infoStage.show()
        );

        CheckBox dark = new CheckBox("Dark Mode: ");
        dark.setTextFill(Color.BLACK);
        dark.setOnAction(e ->
                setDark(dark, root, title, inputLabel)
        );

        Button resetButton = new Button("RESET");
        root.setAlignment(resetButton, Pos.TOP_RIGHT);
        resetButton.setPrefSize(150, 20);
        resetButton.setTextAlignment(TextAlignment.CENTER);

        resetButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                resetGame(rows, inputLabel, tbox);
            }
        });

        root.getChildren().add(grid);
        root.getChildren().add(tbox);
        root.getChildren().add(title);
        root.getChildren().add(dark);
        root.getChildren().add(infoButton);
        root.getChildren().add(resetButton);
        root.getChildren().add(inputLabel);
        root.setAlignment(dark, Pos.CENTER_LEFT);
        inputLabel.setTranslateX(-190);
        inputLabel.setTranslateY(200);
        root.setAlignment(tbox, Pos.BOTTOM_CENTER);
        root.setAlignment(title, Pos.TOP_CENTER);
        root.setPadding(new Insets(50));
        primaryStage.setScene(new Scene(root, 700, 600));
        primaryStage.show();
    }
}