import java.util.*;

public class Pawn extends Piece {
    public Pawn(Color c) {

        super.color = c;
        createMoves(c);

    }

        //throw new UnsupportedOperationException(); }
    // implement appropriate methods

    public String toString() {

        if (super.color().equals(Color.WHITE)){
            return "wp";
        } else {
            return "bp";
        }

        //throw new UnsupportedOperationException();
    }

    public List<String> moves(Board b, String loc) {

        LinkedList<String> ll = new LinkedList<String>();

        //convert current loc to coord
        ArrayList<Integer> loc_coor = super.getIntLoc(loc);
        ArrayList<Integer> current = super.getIntLoc(loc);

        for (Integer key : directions.keySet()){

            // account for typical pawn move
            int valid = 0;

            while(valid < 1){
                current.set(0,current.get(0) + directions.get(key).get(0));
                current.set(1,current.get(1) + directions.get(key).get(1));
                // check bounds
                if (current.get(0) > 7 || current.get(1) > 7 || current.get(0) < 0 || current.get(1) < 0) {
                    valid = valid + 1;
                }else if(b.getPiece(super.getStringLoc(current)) == null){
                    ll.add(super.getStringLoc(current));
                    valid = valid + 1;

                } else if (valid < 1 && !b.getPiece(super.getStringLoc(current)).color().equals(super.color())){
                    ll.add(super.getStringLoc(current));
                    valid = valid + 1;
                } else if (valid < 1 && b.getPiece(super.getStringLoc(current)).color().equals(super.color())) {
                    valid = valid + 1;
                } else{
                    valid = valid + 1;
                }

            }

            // account for first move
            valid = 0;

            current.set(0, loc_coor.get(0));
            current.set(1, loc_coor.get(1));

            while(valid < 1){
                current.set(0,current.get(0) + firstDirections.get(key).get(0));
                current.set(1,current.get(1) + firstDirections.get(key).get(1));
                // check bounds
//                if (b.getPiece(super.getStringLoc(current)) == null) {
//                    ll.add(super.getStringLoc(current));
//                    valid = 1;
//                }
                if(loc_coor.get(0) == 6 && b.getPiece(super.getStringLoc(current)) == null && super.color().equals(Color.BLACK)){
                    ll.add(super.getStringLoc(current));
                    valid = 1;
                }else if(loc_coor.get(0) == 1 && b.getPiece(super.getStringLoc(current)) == null && super.color().equals(Color.WHITE)){
                    ll.add(super.getStringLoc(current));
                    valid = 1;
                } else{ valid = valid + 1;}

            }


            //account for taking opposition pieces
            valid = 0;

            current.set(0, loc_coor.get(0));
            current.set(1, loc_coor.get(1));

            while(valid < 1){
                current.set(0,current.get(0) + eatDirections.get(key).get(0));
                current.set(1,current.get(1) + eatDirections.get(key).get(1));
                // check bounds
                if (current.get(0) > 7 || current.get(1) > 7 || current.get(0) < 0 || current.get(1) < 0) {
                    valid = valid + 1;
                } else if(b.getPiece(super.getStringLoc(current)) == null){
                    valid = valid + 1;
                } else if (!b.getPiece(super.getStringLoc(current)).color().equals(super.color())){
                    ll.add(super.getStringLoc(current));
                    valid = valid + 1;
                } else if (valid < 1 && b.getPiece(super.getStringLoc(current)).color().equals(super.color())){
                    valid = valid + 1;
                } else{ valid = valid + 1;}

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

    public void createMoves(Color c){


        if (c.equals(Color.WHITE)){
            directions = new HashMap<Integer, ArrayList<Integer>>();
            directions.put(1,new ArrayList<Integer>());
            directions.get(1).add(1);
            directions.get(1).add(0);

            firstDirections = new HashMap<Integer, ArrayList<Integer>>();
            firstDirections.put(1,new ArrayList<Integer>());
            firstDirections.get(1).add(2);
            firstDirections.get(1).add(0);

            eatDirections = new HashMap<Integer, ArrayList<Integer>>();
            eatDirections.put(1,new ArrayList<Integer>());
            eatDirections.get(1).add(1);
            eatDirections.get(1).add(-1);

            eatDirections.put(2,new ArrayList<Integer>());
            eatDirections.get(2).add(1);
            eatDirections.get(2).add(1);


        } else{

            directions = new HashMap<Integer, ArrayList<Integer>>();
            directions.put(1,new ArrayList<Integer>());
            directions.get(1).add(-1);
            directions.get(1).add(0);

            firstDirections = new HashMap<Integer, ArrayList<Integer>>();
            firstDirections.put(1,new ArrayList<Integer>());
            firstDirections.get(1).add(-2);
            firstDirections.get(1).add(0);

            eatDirections = new HashMap<Integer, ArrayList<Integer>>();
            eatDirections.put(1,new ArrayList<Integer>());
            eatDirections.get(1).add(-1);
            eatDirections.get(1).add(-1);

            eatDirections.put(2,new ArrayList<Integer>());
            eatDirections.get(2).add(-1);
            eatDirections.get(2).add(1);
        }
    }



    private HashMap<Integer, ArrayList<Integer>> directions;
    private HashMap<Integer, ArrayList<Integer>> eatDirections;
    private HashMap<Integer, ArrayList<Integer>> firstDirections;


}