package onlinechess.controller.pieces;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import onlinechess.controller.Game;
import onlinechess.views.Board;

/**
 *
 * @author admin
 */
public class Bishop extends PiecesChess{
    
    /**
     * Resource eater2! bunch of statements and variables bound togheder by some dark magic,
     * checking for possible tiles to hold the source piece with loops inside loops and shit. 
     * Is ugly as fuck but gets the job done (slowly).
     * @param from position of the source piece
     * @param to destination of such piece
     * @param piece the reason you are here and wandering why it takes so much time
     * @param target your destination, if you are patient enougth maye you would get there
     */
     public static boolean allowed(int from, int to, String piece, String target){
        if(!isDiffTeam(piece, target)){return false;}
        
        int a = 0, b = 0, c = 0, d = 0;
        boolean dL=false, dR=false, uL=false, uR=false;
        //Separation to allow wasting resources
        if(from-to>0){//up
            for(int i = 1; i < (int)Board.w*Board.h/4; i++){
                int upLeft = (from - (Board.w - 1)*i);
                int upRigth = (from - (Board.w + 1)*i);
                //Aboid board out of bounds
                if(upRigth % Board.w == 0 || uR){c++;}
                if((upLeft-1) % Board.w == 0 || uL){d++;}
                //Piece logical moves
                else if(to == upRigth && c < 1){return true;} 
                else if(to == upLeft && d < 1){return true;}
                //Collisions updated last to mark target pieces as allowed move
                uL = !Game.board.isTileEmpty(upLeft);
                uR = !Game.board.isTileEmpty(upRigth);
            }
        }
        else if(from-to<0){//down
            for(int i = 1; i < (int)Board.w*Board.h/4; i++){
                int downLeft = (from + (Board.w - 1)*i);    
                int downRigth = (from + (Board.w + 1)*i);
                //Aboid board out of bounds
                if((downRigth-1) % Board.w == 0 || dR){a++;}
                if(downLeft % Board.w == 0 || dL){b++;}
                //Piece logical moves
                if(to == downRigth && a < 1){return true;} 
                else if(to == downLeft && b < 1){return true;}
                //Collisions updated last to mark target pieces as allowed move
                dL = !Game.board.isTileEmpty(downLeft);
                dR = !Game.board.isTileEmpty(downRigth);
            }
        }
        return false;
    }
        //Know if target tile is full (v)
        //Know if target cell is fiend/foe (v)
        //Aboid bumping into other pieces
}

