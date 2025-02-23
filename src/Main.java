package Tucil1_13523030.src;
import java.io.PrintWriter;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.Scanner;


public class Main {
    public static AtomicInteger  count = new AtomicInteger(0);
    
    public static void main(String[] args) throws Exception{
        count.set(0);
        FileRead f;
        Game game;
        long duration;
        long start_time;
        long end_time;

        Scanner input = new Scanner(System.in);
        System.err.print("Nama file: ");
        String file_name = input.nextLine();
        // input.close();

        f = new FileRead(file_name);
        game = f.read();


        System.out.println("Game Start");

        start_time = System.nanoTime();

        boolean solved = game.solve_game(0);
        
        end_time = System.nanoTime();
        duration = end_time - start_time;
        String out = game.board.getBoard();
        
        if (!(solved)){
            System.out.println("No possible solution");
        }
        else{
            System.out.println("Solved");
            Scanner input2 = new Scanner(System.in);
            System.err.print("Apakah anda ingin menyimpan solusi? (ya/tidak)");
            String save = input2.nextLine();
            input2.close();
            
            if (save.equals("ya")){
                String[] n = file_name.split("[.]");
                String output_file = n[0] + "_result.txt";
                PrintWriter res = new PrintWriter(output_file);
    
                res.println(out);
                res.close();    
            }
        }
        
        System.out.println("Waktu pencarian: " + duration/1000000 + " ms");
        System.out.println("Banyak kasus yang ditinjau:  " + count.get());
        
        
        
        
    }
    
}
