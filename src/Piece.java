package Tucil1_13523030.src;

import java.util.ArrayList;

public class Piece {
    char id;
    int[][] shape;
    public int baris;
    public int kolom;
    
    public Piece(int[][] bentuk, char C){
        this.baris = bentuk.length;
        this.kolom = bentuk[0].length;

        this.shape = new int[bentuk.length][bentuk[0].length];
        for (int i = 0; i < bentuk.length; i++){
            System.arraycopy(bentuk[i], 0, shape[i], 0, bentuk[i].length);
        }

        this.id = C;
    }


    public Piece(ArrayList<ArrayList<Integer>> bentuk, char C){
        this.baris = bentuk.size();
        this.kolom = bentuk.get(0).size();
        this.shape = new int[bentuk.size()][bentuk.get(0).size()];
        this.id = C;

        for (int i = 0; i < bentuk.size(); i++){
            for (int j = 0; j < bentuk.get(0).size(); j++){
                shape[i][j] = bentuk.get(i).get(j);
            }
        }

    }


    public void cetakPiece(){
        for (int i = 0; i < shape.length; i++){
            for (int j = 0; j < shape[0].length; j++){
                if (shape[i][j] != 0){    
                    System.out.printf("%c", id);
                }
                else{
                    System.out.print(" ");
                }
            }
            System.out.println("");
        }
    }

    public Piece rotate(){
        // Rotasi ke kanan 90 derajat
        int[][] rotated = new int[kolom][baris]; 

        for (int i = 0; i < baris; i++){
            for (int j = 0; j < kolom; j++){
                rotated[j][baris - 1 - i] = shape[i][j];
            }
        }

        Piece  p_rotated = new Piece(rotated, id);
        return p_rotated;
    }

    public Piece mirror_sb_vertical(){
        // Pencerminan dengan sumbu vertical
        int[][] matrix = new int[baris][kolom];
        for (int i = 0; i < baris; i++){
            for (int j = 0; j < kolom; j++){
                matrix[i][kolom-j - 1] = shape[i][j];
            }
        }

        Piece p_mirrored = new Piece(matrix, id);
        return p_mirrored;
    }

    public Piece mirror_sb_horizontal(){
        // Pencerminan dengan sumbu horizontal 
        int[][] matrix = new int[baris][kolom];

        for (int i = 0; i < baris; i++){
            matrix[baris - i - 1] = shape[i];
        }

        Piece p_mirrored = new Piece(matrix, id);
        return p_mirrored;
    }
}
