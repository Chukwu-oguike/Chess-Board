import java.util.*;

public class Bishop extends Piece {
    public Bishop(Color c) {
        super.color = c;
        createMoves();

        //throw new UnsupportedOperationException();
        }
    // implement appropriate methods

    public String toString() {

        if (super.color().equals(Color.WHITE)){
            return "wb";
        } else {
            return "bb";
        }

        //throw new UnsupportedOperationException();
    }

    public List<String> moves(Board b, String loc) {

        List<String> ll = new LinkedList<String>();

        //consider color of piece

        //convert current loc to coord
        ArrayList<Integer> loc_coor = super.getIntLoc(loc);
        ArrayList<Integer> current = super.getIntLoc(loc);

        for (Integer key : directions.keySet()){

            boolean valid = true;

            while(valid == true){
                current.set(0,current.get(0) + directions.get(key).get(0));
                current.set(1,current.get(1) + directions.get(key).get(1));
                // check bounds
                if (current.get(0) > 7 || current.get(1) > 7 || current.get(0) < 0 || current.get(1) < 0) {
                    valid = false;
                }else if(b.getPiece(super.getStringLoc(current))== null){
                    ll.add(super.getStringLoc(current));
                } else if (!b.getPiece(super.getStringLoc(current)).color().equals(super.color())){
                    ll.add(super.getStringLoc(current));
                    valid = false;
                }else { valid = false;}

            }

            current.set(0, loc_coor.get(0));
            current.set(1, loc_coor.get(1));


        }

        return ll;


        //throw new UnsupportedOperationException();
    }

    public boolean moveIsValid(Board b, String from, String to){

        List<String> ll = moves(b, from);

        return ll.contains(to);
    }

    public void createMoves(){
        directions = new HashMap<Integer, ArrayList<Integer>>();

        directions.put(1,new ArrayList<Integer>());
        directions.get(1).add(1);
        directions.get(1).add(1);

        directions.put(2,new ArrayList<Integer>());
        directions.get(2).add(1);
        directions.get(2).add(-1);

        directions.put(3,new ArrayList<Integer>());
        directions.get(3).add(-1);
        directions.get(3).add(1);

        directions.put(4,new ArrayList<Integer>());
        directions.get(4).add(-1);
        directions.get(4).add(-1);


    }

    private HashMap<Integer, ArrayList<Integer>> directions;


}