import java.util.*;

abstract public class Piece {
    public static void registerPiece(PieceFactory pf) {

        map.put(String.valueOf(pf.symbol()), pf);

        //throw new UnsupportedOperationException();
    }

    public static Piece createPiece(String name) {

        String color_ = name.substring(0,1);
        String piece_ = name.substring(1,2);

        //String chessPiece = "kqrbpn";

        if(!map.containsKey(piece_)){
            throw new UnsupportedOperationException("Piece not Registered");
        }

//        if(!chessPiece.contains(piece_)){
//            throw new UnsupportedOperationException("Not a valid chess piece");
//        }

        if (color_.equals("b")){
            PieceFactory genPiece = map.get(piece_);
            return genPiece.create(Color.BLACK);

        } else if(color_.equals("w")){
            PieceFactory genPiece = map.get(piece_);
            return genPiece.create(Color.WHITE);
        } else {
            throw new UnsupportedOperationException("Not a valid Color");
        }

    }

    public Color color() {
        // You should write code here and just inherit it in
        // subclasses. For this to work, you should know
        // that subclasses can access superclass fields.
        return color;
        //throw new UnsupportedOperationException();
    }

    public ArrayList<Integer> getIntLoc(String loc){
        char col = loc.charAt(0);
        int row = loc.charAt(1) - '0';
        row = row - 1;
        ArrayList<Integer> ll = new ArrayList<Integer>();
        ll.add(0, row);
        ll.add(1, col - 'a');

        return ll;

    }

    public String getStringLoc(ArrayList<Integer> loc){
        char col = (char)(loc.get(1)+97);
        String row = Integer.toString(loc.get(0)+1);
        return col + row;
    }

    protected Color color;

    protected static HashMap<String, PieceFactory> map = new HashMap<String, PieceFactory>();

    abstract public String toString();

    abstract public List<String> moves(Board b, String loc);

    abstract public boolean moveIsValid(Board b, String from, String to);



}