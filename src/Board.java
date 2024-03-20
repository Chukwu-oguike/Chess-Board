import java.util.*;

public class Board {

    private Piece[][] pieces = new Piece[8][8];

    private static Board board_;
    private ArrayList<BoardListener> listeners = new ArrayList<BoardListener>();

    private Board() { }

    public static Board theBoard() {
        if(board_ == null){

            board_ = new Board();
        }

        return board_;
        // implement this
    }

    // Returns piece at given loc or null if no such piece
    // exists
    public Piece getPiece(String loc) {

        ArrayList<Integer> ll = getIntLoc(loc);

        if (ll.get(0) < 0 || ll.get(0) > 7 || ll.get(1) < 0 || ll.get(1) > 7) {
            throw new UnsupportedOperationException();
        } else{
            return pieces[ll.get(0)][ll.get(1)];
        }

    }

    public void addPiece(Piece p, String loc) {

        ArrayList<Integer> ll = getIntLoc(loc);

        if (ll.get(0) < 0 || ll.get(0) > 7 || ll.get(1) < 0 || ll.get(1) > 7) {
            throw new UnsupportedOperationException();
        } else if(pieces[ll.get(0)][ll.get(1)] != null){
            throw new UnsupportedOperationException();
        }
        else{
            pieces[ll.get(0)][ll.get(1)] = p;
        }
    }

    public void movePiece(String from, String to) {


        ArrayList<Integer> ll1 = getIntLoc(from);
        ArrayList<Integer> ll2 = getIntLoc(to);

        if (ll1.get(0) < 0 || ll1.get(0) > 7 || ll1.get(1) < 0 || ll1.get(1) > 7) {
            throw new UnsupportedOperationException();
        } else if(ll2.get(0) < 0 || ll2.get(0) > 7 || ll2.get(1) < 0 || ll2.get(1) > 7){
            throw new UnsupportedOperationException();
        } else if(getPiece(from) == null){
            throw new UnsupportedOperationException();
        } else if(!getPiece(from).moveIsValid(this, from, to)){
            throw new UnsupportedOperationException(); //
        } else if(pieces[ll2.get(0)][ll2.get(1)] == null){
            updateListenersOnMove(from, to, pieces[ll1.get(0)][ll1.get(1)]);
            pieces[ll2.get(0)][ll2.get(1)] = pieces[ll1.get(0)][ll1.get(1)];
            pieces[ll1.get(0)][ll1.get(1)] = null;


        } else{
            updateListenersOnMove(from, to, pieces[ll1.get(0)][ll1.get(1)]);
            updateListenersOnCapture(pieces[ll1.get(0)][ll1.get(1)], pieces[ll2.get(0)][ll2.get(1)]);
            pieces[ll2.get(0)][ll2.get(1)] = pieces[ll1.get(0)][ll1.get(1)];
            pieces[ll1.get(0)][ll1.get(1)] = null;
        }

    }

    public void clear() {

        for (int row = 0; row < 8; row++){
            for (int col = 0; col < 8; col++){
                pieces[row][col] = null;
            }
        }

    }

    public void registerListener(BoardListener bl) {

        listeners.add(bl);
        //throw new UnsupportedOperationException();
    }

    public void removeListener(BoardListener bl) {

        listeners.remove(bl);
        //throw new UnsupportedOperationException();
    }

    public void removeAllListeners() {
        listeners.clear();

        //throw new UnsupportedOperationException();
    }

    public void iterate(BoardInternalIterator bi) {
        String loc;
        ArrayList<Integer> ll = new ArrayList<Integer>();
        ll.add(0,0);
        ll.add(1,0);
        for (int i = 0; i <= 7; i++) {
            for (int j = 0; j <= 7; j++) {
                ll.set(0,i);
                ll.set(1,j);
                loc = getStringLoc(ll);
                bi.visit(loc, pieces[i][j]);
            }
        }
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

    public void updateListenersOnMove(String from, String to, Piece p){

        for(BoardListener key: listeners){

            key.onMove(from, to, p);
        }
    }

    public void updateListenersOnCapture(Piece attacker, Piece captured){

        for(BoardListener key: listeners){

            key.onCapture(attacker, captured);
        }
    }
}