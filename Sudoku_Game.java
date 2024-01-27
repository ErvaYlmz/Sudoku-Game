package com.mycompany.sudoku_game;

public class Sudoku_Game {
    
    public static int GRID=9;
    
    public static boolean isRowValid(int[][] game, int number, int row){
        for(int i=0; i<GRID; i++){
            if(game[row][i]==number){
                return true;
            }
        }
        return false;
    }
    
    public static boolean isColumnValid(int[][] game, int number, int column){
        for(int i=0; i<GRID; i++){
            if(game[i][column]==number){
                return true;
            }
        }
        return false;
    }
    
    public static boolean isBoxValid(int[][] game, int number, int row, int column){
        
        int boxRow= row - (row%3);
        int boxColumn= column - (column%3);
        
        for(int i=boxRow; i<boxRow+3; i++){
            for(int j=boxColumn; j<boxColumn+3; j++){
                if(game[i][j]==number){
                    return true;
                }
            }
        }
        return false;
    }
    
    public static boolean isConditionsValid(int[][] game, int number, int row, int column){
        return !(isRowValid(game, number, row)) && 
                !(isColumnValid(game, number, column)) && 
                !(isBoxValid(game, number, row, column));
    }
    
    public static boolean SolveIt(int[][] game){
        for(int row=0; row<GRID; row++){
            for(int col=0; col<GRID; col++){
                if(game[row][col]==0){
                    for(int guess=1; guess<GRID+1; guess++){
                        if(isConditionsValid(game, guess, row, col)){
                            game[row][col]=guess;
                            
                            if(SolveIt(game)){
                                return true;
                            }else{
                                game[row][col]=0;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }
    
    public static void printGame(int[][] game){
        
        for(int i=0; i<GRID; i++){
            if(i%3==0 && i!=0){
                System.out.println("---------------");
            }
            
            for(int j=0; j<GRID; j++){
                if(j%3==0 && j!=0){
                    System.out.print(" | ");
                }
                
                System.out.print(game[i][j]);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        
        int[][] sudoku={
            {7,0,2,0,5,0,6,0,0},
            {0,0,0,0,0,3,0,0,0},
            {1,0,0,0,0,9,5,0,0},
            {8,0,0,0,0,0,0,9,0},
            {0,4,3,0,0,0,7,5,0},
            {0,9,0,0,0,0,0,0,8},
            {0,0,9,7,0,0,0,0,5},
            {0,0,0,2,0,0,0,0,0},
            {0,0,7,0,4,0,2,0,3}
        };
       SolveIt(sudoku); 
        printGame(sudoku);
    }
}
