package userinterface;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import userinterface.connection.ClientConnection;
import userinterface.model.Game;
import userinterface.model.Tile;

public class TicTacToemultiController {

		
	 @FXML
	    private Circle CircleOne;

	    @FXML
	    private Circle CircleTwo;

	    @FXML
	    private Circle CircleThree;

	    @FXML
	    private Circle CircleFour;

	    @FXML
	    private Circle CircleFive;

	    @FXML
	    private Circle CircleSix;

	    @FXML
	    private Circle CircleSeven;

	    @FXML
	    private Circle CircleEight;

	    @FXML
	    private Circle CircleNine;

	    @FXML
	    private Label XOne;

	    @FXML
	    private Label XTwo;

	    @FXML
	    private Label XThree;

	    @FXML
	    private Label XFour;

	    @FXML
	    private Label XFive;

	    @FXML
	    private Label XSix;

	    @FXML
	    private Label XSeven;

	    @FXML
	    private Label XEight;

	    @FXML
	    private Label XNine;
	    
	    @FXML
	    private Label lblMessages;
	    private int[] filledSquares = new int[9];
	    private int[] filledCircles = new int[5];
	    private int[] filledX = new int[5];
	    private boolean allowMoves = true;

	    private boolean tie = false;
	   

	    private int filledSquaresCounter = 0;
	    private int filledCirclesCounter = 0;
	    private int filledXCounter = 0;

	    private char winningTeam;
        private Game game = new Game();
        private String tile;

	    final private int[][] winningPositions = {
	        {1, 5, 9},
	        {3, 5, 7},
	        {1, 2, 3},
	        {4, 5, 6},
	        {7, 8, 9},
	        {1, 4, 7},
	        {2, 5, 8},
	        {3, 6, 9}
	    };
	
	
	
	
	@FXML
    public void handleSquareOneClick(MouseEvent event) {
		Userinterface.clientConnection.sendMove(0,0, tile);
    }

    @FXML
    public void handleSquareTwoClick(MouseEvent event) {
        Userinterface.clientConnection.sendMove(0,1, tile);
		//if(move == o) {
		//	this.handleSquareClick(2);
        //}else {
        //	this.handleSquareClick(11);
        //}
    
       
    }

    @FXML
    public void handleSquareThreeClick(MouseEvent event) {
        Userinterface.clientConnection.sendMove(0,2, tile);
        
    }

    @FXML
    public void handleSquareFourClick(MouseEvent event) {
        Userinterface.clientConnection.sendMove(1,0, tile);
        
        
    }

    @FXML
    public void handleSquareFiveClick(MouseEvent event) {
        Userinterface.clientConnection.sendMove(1,1, tile);
    }

    @FXML
    public void handleSquareSixClick(MouseEvent event) {
        Userinterface.clientConnection.sendMove(1,2, tile);
    }

    @FXML
    public void handleSquareSevenClick(MouseEvent event) {
        Userinterface.clientConnection.sendMove(2,0, tile);
    }

    @FXML
    public void handleSquareEightClick(MouseEvent event) {
        Userinterface.clientConnection.sendMove(2,1, tile);
    }

    @FXML
    public void handleSquareNineClick(MouseEvent event) {
        Userinterface.clientConnection.sendMove(2,2, tile);
    }

    public void handleSquareClick(int squareNumber) {
        if(!isAlreadySelectedBox(squareNumber) && this.allowMoves == true) {
            switch(squareNumber) {
                case 1:
                    this.showCircleOne();
                    break;
                case 2:
                    this.showCircleTwo();
                    break;
                case 3:
                    this.showCircleThree();
                    break;
                case 4:
                    this.showCircleFour();
                    break;
                case 5:
                    this.showCircleFive();
                    break;
                case 6:
                    this.showCircleSix();
                    break;
                case 7:
                    this.showCircleSeven();
                    break;    
                case 8:
                    this.showCircleEight();
                    break;
                case 9:
                    this.showCircleNine();
                    break;
                case 10:
                    this.showXOne();
                    break;
                case 11:
                    this.showXTwo();
                    break;
                case 12:
                    this.showXThree();
                    break;
                case 13:
                    this.showXFour();
                    break;
                case 14:
                    this.showXFive();
                    break;
                case 15:
                    this.showXSix();
                    break;
                case 16:
                    this.showXSeven();
                    break;    
                case 17:
                    this.showXEight();
                    break;
                case 18:
                    this.showXNine();
                    break;
                default:
                    System.out.println("Impossible choice");
                    break;
            }

            this.filledSquares[this.filledSquaresCounter] = squareNumber;
            this.filledCircles[this.filledCirclesCounter] = squareNumber;
            this.filledSquaresCounter++;
            this.filledCirclesCounter++;

            if(this.checkVictory()) {
                this.endGame();
            }


        } else if(this.filledSquaresCounter >= 9) {
            this.tie = true;
            this.endGame();
        }
    }

    public void createNewGame(){
	    this.game = new Game();
    }
    
    public boolean checkVictory() {
        if(this.filledCirclesCounter < 3 && this.filledXCounter < 3) {
            return false;
        }

        for(int[] filled : this.winningPositions) {
            int slotCounter = 0;

            for(int singleFilled : filled) {
                if(this.isOccupiedByCircle(singleFilled)) {
                    slotCounter++;
                }
            }

            if(slotCounter == 3) {
                this.winningTeam = 'O';
                this.allowMoves = false;
                return true;
            }

            slotCounter = 0;

            for(int singleFilled : filled) {
                if(this.isOccupiedByX(singleFilled)) {
                    slotCounter++;
                }
            }

            if(slotCounter == 3) {
                this.winningTeam = 'X';
                this.allowMoves = false;
                return true;
            }
        }

        return false;
    }
    public boolean isOccupiedByCircle(int circlePosition) {
        boolean found = false;

        for(int filledCircle : this.filledCircles) {
            if(filledCircle == circlePosition) {
                found = true;
            }
        }

        return found == true;
    }

    public boolean isOccupiedByX(int xPosition) {
        boolean found = false;

        for(int filled : this.filledX) {
            if(filled == xPosition) {
                found = true;
            }
        }

        return found == true;
    }

    public void endGame() {
        this.allowMoves = false;

        if(this.tie == true) {
            this.lblMessages.setText("It was a tie!");
        } else if(String.valueOf(this.winningTeam).equals("O")) {
            this.lblMessages.setText("You win!");
        } else if(String.valueOf(this.winningTeam).equals("X")) {
            this.lblMessages.setText("Sorry, you lose!");
        }
    }

    
   
            public void showCircleOne() {
                this.CircleOne.setVisible(true);
            }

            public void showCircleTwo() {
                this.CircleTwo.setVisible(true);
            }

            public void showCircleThree() {
                this.CircleThree.setVisible(true);
            }

            public void showCircleFour() {
                this.CircleFour.setVisible(true);
            }

            public void showCircleFive() {
                this.CircleFive.setVisible(true);
            }

            public void showCircleSix() {
                this.CircleSix.setVisible(true);
            }

            public void showCircleSeven() {
                this.CircleSeven.setVisible(true);
            }

            public void showCircleEight() {
                this.CircleEight.setVisible(true);
            }

            public void showCircleNine() {
                this.CircleNine.setVisible(true);
            }
	
            public void showXOne() {
                this.XOne.setVisible(true);
            }

            public void showXTwo() {
                this.XTwo.setVisible(true);
            }

            public void showXThree() {
                this.XThree.setVisible(true);
            }

            public void showXFour() {
                this.XFour.setVisible(true);
            }

            public void showXFive() {
                this.XFive.setVisible(true);
            }

            public void showXSix() {
                this.XSix.setVisible(true);
            }

            public void showXSeven() {
                this.XSeven.setVisible(true);
            }

            public void showXEight() {
                this.XEight.setVisible(true);
            }

            public void showXNine() {
                this.XNine.setVisible(true);
            }
	
	
            public boolean isAlreadySelectedBox(int squareNumber) {
                boolean found = false;

                for(int filledSquare : this.filledSquares) {
                    if(squareNumber == filledSquare) {
                        found = true;
                    }
                }

                return found == true;
            }

	
	 public void backbutton2(ActionEvent event)throws Exception {
	       
	        {
	            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("userinterface/main.fxml"));
	            Stage stage = new Stage();
	            stage.setTitle(" ");
	            stage.setScene(new Scene(root, 600, 570));
	            stage.show();
	            
	            ((Node)(event.getSource())).getScene().getWindow().hide();
	        }
	        
	    }

}
