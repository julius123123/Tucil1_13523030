package Tucil1_13523030.src;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class FileRead {
    public FileRead(){}
    public Game read() throws Exception{
        BufferedReader br = new BufferedReader(new FileReader("Tucil1_13523030\\tes\\game.txt"));
    try {
        int max_length = 0;
        String line = br.readLine();
        
        String[] meta = line.split(" ");
        
        int N = meta[0].charAt(0) - '0';
        int M = meta[1].charAt(0) - '0';
        int P = meta[2].charAt(0) - '0';
        
        line = br.readLine();
        
        Board board = new Board(N, M, P, line);

        
        char p = meta[2].charAt(0);
        int num_piece = p - '0';
        int index = 0;

        Piece[] l_piece = new Piece[num_piece];

        char current_char = '0';
        ArrayList<ArrayList<Integer>> matrix = new ArrayList<ArrayList<Integer>>();


        line = br.readLine();
        ArrayList<Integer> buff = new ArrayList<Integer>();

        while (line != null){
            for (int i = 0; i < line.length(); i++){
                if (line.charAt(i) == ' '){
                    buff.add(0);
                }
                else{
                    // Piece pertama
                    if (current_char == '0'){
                        current_char = line.charAt(i);
                    }

                    // Piece baru
                    if (line.charAt(i) != current_char){
                        //Panjang maksimum dari baris - baris pada matrix
                        max_length = 0;
                        for (int k = 0; k < matrix.size(); k++){
                            if (matrix.get(k).size() > max_length){
                                max_length = matrix.get(k).size();
                            }
                        }

                        // Mengisi matriks dengan 0 agar menjadi persegi/ persegi panjang
                        for (int k = 0; k < matrix.size(); k++){
                            if (matrix.get(k).size() != max_length){
                                int baris_size = matrix.get(k).size();
                                for (int l = 0; l < max_length - baris_size; l++){
                                    matrix.get(k).add(0);
                                }
                            } 
                        }

                        Piece current_Piece = new Piece(matrix, current_char);
                        l_piece[index] = current_Piece; // Menyimpan piece yang sudah selesai
                        index++;

                        matrix = new ArrayList<ArrayList<Integer>>();
                        current_char = line.charAt(i);
                        buff.add(1);
                    }
                    else{
                        //Piece yang sama
                        buff.add(1);
                    }
                }
            }
            matrix.add(buff);
            buff = new ArrayList<Integer>();
            line = br.readLine();
        }
        //Panjang maksimum dari baris - baris pada matrix
        max_length = 0;
        for (int k = 0; k < matrix.size(); k++){
            if (matrix.get(k).size() > max_length){
                max_length = matrix.get(k).size();
            }
        }

        // Mengisi matriks dengan 0 agar menjadi persegi/ persegi panjang
        for (int k = 0; k < matrix.size(); k++){
            if (matrix.get(k).size() != max_length){
                int baris_size = matrix.get(k).size();
                for (int l = 0; l < max_length - baris_size; l++){
                    matrix.get(k).add(0);
                }
            } 
        }

        Piece current_piece = new Piece(matrix, current_char);
        l_piece[index] = current_piece;//Menyimpan piece
        index++;
        br.close();

        Game game = new Game(board, l_piece);
        return game;
        }
        
    finally {
        br.close();
    }
    }


}
