package Tucil1_13523030.src;

import java.util.concurrent.atomic.AtomicInteger;
public class Game
{
    public  Board board;
    public  Piece[] pieces;
    
    public Game(Board b1, Piece[] p){
        this.board =  b1;
        this.pieces = new Piece[b1.puzzle_pieces];
        this.pieces = p;
    }
    
    public boolean solve_game(int index){
        if (index == board.puzzle_pieces && board.check()){
            board.cetakBoard();
            return true;
        }
        Piece[] pieces_rotate = new Piece[12];
        pieces_rotate[0] = pieces[index];
        pieces_rotate[1] = pieces[index].mirror_sb_horizontal();
        pieces_rotate[2] = pieces[index].mirror_sb_vertical();
        
        int j = 0;
        int i = 3;
        while (i < 12){
            pieces_rotate[i] = pieces_rotate[j].rotate();
            pieces_rotate[i + 1] = pieces_rotate[j].mirror_sb_horizontal();
            pieces_rotate[i + 2] = pieces_rotate[j].mirror_sb_vertical();
            
            j = j + 3;
            i = i + 3;
            
        }
        
        for (int k = 0; k < 12; k++){
            for (int x = 0; x < board.M; x++){
                for (int y = 0; y < board.N; y++){
                    // System.out.println(Main.count);
                    // Main.count.incrementAndGet();
                    if (board.setPiece(pieces_rotate[k], x, y)){
                        if (solve_game(index + 1)){
                            return true;
                        }
                        board.removePiece(pieces_rotate[k], x, y);
                    }
                }
            }
        }

        return false;
    }
}
