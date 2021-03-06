/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package onlinechess.controller.game.pieces;

//Board borders

import onlinechess.views.Board;

//Other team pieces stops advance (a++)

/**
 *
 * @author Javier Palacios
 */
public class PiecesChess {
    
    /**
    * Returns false if the tile is occupied by a same team piece.
    * @param piece Piece of the current move
    * @param target  Piece of the target file
    * @return boolean indicating if the path is blocked
    */
    public static boolean isDiffTeam(String piece, String target, int w, int h){
        
        boolean pieceTeam = piece.equals(piece.toUpperCase());
        boolean targetTeam = target.equals(target.toUpperCase());
        
        return (pieceTeam != targetTeam)|| target.equals("-");         
    }
    
    /**
    * Returns false if the path of the piece is blocked by other piece in vertical axis
    * @param from Tile number of the piece you want to move
    * @param to   Number of the target tile
    * @return boolean indicating if the path is blocked
    */
    protected static boolean upDownCollisions(int from, int to, int w, int h, Board board){
        //Up & down collisions logic
        int up=from-w*h,down=from+w*h,stpd=0,stpu=0;
        for(int i = 1; i < (int)Math.abs((from-to)/w); i++){                
            try{                   
                if(stpu <= 0&&!board.isTileEmpty((int)from-w*i)){
                    up = from-w*i;
                    stpu++; //avoid further actualization
                }; }catch(Exception e){}
            try{
                if(stpd<=0 && !board.isTileEmpty((int)from+w*i)){
                    down = from+w*i;
                    stpd++; //avoid further actualization
                }; } catch (Exception e){}
            }            
            if(from-to > 0 && to < up){return false;}
            if(from-to < 0 && to > down){return false;}
            
            return true;
    }
    
    /**
    * Returns false if the path of the piece is blocked by other piece in horizontal axis
    * @param from Tile number of the piece you want to move
    * @param to   Number of the target tile
    * @param row  Initial number of the row (e. 9 -> 2nd row of 8x8 board)
    * @return boolean indicating if the path is blocked
    */
    protected static boolean  leftRigthCollisions(int from, int to, int row,int w, Board board){
            //Left & rigth collisions logic
            int left=row,rigth=row+w,stpl=0,stpr=0;
            for(int i = 1; i <  w; i++){                
                try{                    
                    if(stpl <= 0&&!board.isTileEmpty(from-1*i)){
                        left = from-1*i;
                        stpl++; //avoid further actualization
                    }; }catch(Exception e){}
                try{
                    if(stpr<=0 && !board.isTileEmpty(from+1*i)){
                        rigth = from+1*i;
                        stpr++; //avoid further actualization
                    }; } catch (Exception e){}
               
            }            
            if(from-to > 0 && to < left){return false;}
            if(from-to < 0 && to > rigth){return false;}
            
            return true;
    }
    
}

//    private String target;
//    private String piece;
//    private int from;
//    private int to;
//    
//    public King(int from, int to, String piece, String target){
//        this.target = target;
//        this.piece = piece;
//        this.from = from;
//        this.to = to;
//    }