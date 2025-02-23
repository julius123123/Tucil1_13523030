package Tucil1_13523030.src;

public class Board {
    int N;
    int M;
    public int puzzle_pieces;
    String kasus;
    char[][] board;

    public Board(int X, int Y, int P, String K){
        this.N = X;
        this.M = Y;
        this.puzzle_pieces = P;
        this.kasus = K;
        this.board = new char[X][Y];

        for (int i = 0; i < N; i++){
            for (int j = 0; j < M; j++){
                board[i][j] = '0';
            }
        }
    }

    public void cetakBoard(){
        for (int i = 0; i < N; i++){
            for (int j = 0; j < M; j++){
                System.out.print(board[i][j]);
            }
            System.err.println();
        }
    }
    
    public String getBoard(){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++){
           for (int j = 0; j < M; j++){
            sb.append(board[i][j]);
           }
           sb.append('\n');
        }
        String out = sb.toString();
        return out;
    }
    public void GetDesc(){
        System.out.printf("Board: %d, %d, %d, %s", N, M, puzzle_pieces, kasus);
    }

    public void removePiece(Piece p, int x, int y){
        // Menghapus piece dari board dengan posisi (x,y)
        for (int i = 0; i < p.kolom; i++){
            for (int j = 0; j < p.baris; j++){
                if (p.shape[j][i] !=  0){
                    board[y + j][x + i] = '0';
                }
            }
        }
    }

    public boolean setPiece(Piece p1, int x, int y){
        // Memasukkan piece ke board dengan posisi (x,y)
        if (x + p1.kolom > M){
            // System.out.println("1");
            return false;
        }
        
        if (y + p1.baris > N){
            // System.out.println("2");
            return false;
        }

        char[][] result = new char[N][M];

        for (int i = 0; i < N; i++){
            for (int j = 0; j < M; j++){
                result[i][j] = board[i][j];
            }
        }
        
        for (int i = 0;i < p1.kolom; i++){
            for (int j = 0; j < p1.baris; j++){
                if (result[y + j][x + i] != '0'){
                    if (p1.shape[j][i] != 0){
                        return false;
                    }
                } 
                else{
                    if (p1.shape[j][i] != 0){
                        result[y + j][x + i] = p1.id;
                    }
                }
            }
        }

        this.board = result;
        return true;
    }

    public boolean solve_one(Piece p1){
        // Meletakkan piece di posisi pertama yang mungkin
        for (int i = 0; i < M; i++){
            for (int j = 0; j < N; j++){
             if (setPiece(p1, j, i)){
                return true;
             }   
            }
        }
        return false;
    }

    public boolean check(){
        // Memeriksa apakah semua sudah posisi pada board sudah terisi
        for (int i = 0; i < N; i++){
            for (int j = 0; j < M; j++){
                if (board[i][j] == '0'){
                    return false;
                }
            }
        }
        return true;
    }
}