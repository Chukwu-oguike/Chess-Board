import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

public class Chess {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java Chess layout moves");
        }
        Piece.registerPiece(new KingFactory());
        Piece.registerPiece(new QueenFactory());
        Piece.registerPiece(new KnightFactory());
        Piece.registerPiece(new BishopFactory());
        Piece.registerPiece(new RookFactory());
        Piece.registerPiece(new PawnFactory());
        Board.theBoard().registerListener(new Logger());
        // args[0] is the layout file name
        // args[1] is the moves file name
        // Put your code to read the layout file and moves files
        // here.

        Board b = Board.theBoard();

        String col = "abcdefgh";
        String row = "12345678";
        String color_ = "bw";
        String chessPiece = "kqrbpn";

        //read board
        try {
            Scanner scanner = new Scanner(new File(args[0]));

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.charAt(0) != '#') {
                    // process the line
                    String pieces_ = line.substring(3, 5);
                    String loc_ = line.substring(0, 2);

                    //write code to throw exception for bad output
                    if (!col.contains(loc_.substring(0, 1)) || !row.contains(loc_.substring(1, 2)))
                        throw new UnsupportedOperationException();

                    if (!color_.contains(pieces_.substring(0, 1)) || !chessPiece.contains(pieces_.substring(1, 2)))
                        throw new UnsupportedOperationException();

                    if (line.charAt(2) != '=')
                        throw new UnsupportedOperationException();

                    Piece p_ = Piece.createPiece(pieces_);
                    b.addPiece(p_, loc_);
                }

            }
        } catch(FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        //Board.theBoard().iterate(new BoardPrinter());
        //read move
        try {
            Scanner scanner_ = new Scanner(new File(args[1]));
            while (scanner_.hasNextLine()) {
                String line = scanner_.nextLine();
                if (line.charAt(0) != '#') {
                    // process the line
                    String from_ = line.substring(0, 2);
                    String to_ = line.substring(3, 5);


                    //write code to throw exception for bad output
                    if (!col.contains(from_.substring(0, 1)) || !row.contains(from_.substring(1, 2)))
                        throw new UnsupportedOperationException();
                    if (!col.contains(to_.substring(0, 1)) || !row.contains(to_.substring(1, 2)))
                        throw new UnsupportedOperationException();
                    if (line.charAt(2) != '-')
                        throw new UnsupportedOperationException();

                    b.movePiece(from_, to_);
                }

            }


        } catch(FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        // what data structure do you use to represent the board
        // you would have to read from those files and then
        // when you read the piece, you create it

        // Leave the following code at the end of the simulation:
        System.out.println("Final board:");
        Board.theBoard().iterate(new BoardPrinter());
    }
}