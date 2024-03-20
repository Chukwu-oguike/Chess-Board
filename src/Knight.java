import java.util.*;

public class Knight extends Piece {
    public Knight(Color c) {

        super.color = c;
        createMoves();

        //throw new UnsupportedOperationException();
    }
    // implement appropriate methods

    public String toString() {

        if (super.color().equals(Color.WHITE)){
            return "wn";
        } else {
            return "bn";
        }


        //throw new UnsupportedOperationException();
    }

    public List<String> moves(Board b, String loc) {

        List<String> ll = new LinkedList<String>();

        //convert current loc to coord
        ArrayList<Integer> loc_coor = super.getIntLoc(loc);
        ArrayList<Integer> current = super.getIntLoc(loc);
        //System.out.println(current);

        for (Integer key : directions.keySet()){

            int valid = 0;

            while(valid < 1){
                current.set(0,current.get(0) + directions.get(key).get(0));
                current.set(1,current.get(1) + directions.get(key).get(1));
                //System.out.println(current);
                // check bounds
                if (current.get(0) > 7 || current.get(1) > 7 || current.get(0) < 0 || current.get(1) < 0) {
                    valid = valid + 1;
                }else if(valid < 1 && b.getPiece(super.getStringLoc(current)) == null){
                    ll.add(super.getStringLoc(current));
                    valid = valid + 1;

                } else if (valid < 1 && !b.getPiece(super.getStringLoc(current)).color().equals(super.color())){
                    ll.add(super.getStringLoc(current));
                    valid = valid + 1;
                } else{ valid = valid + 1;}

            }

            current.set(0, loc_coor.get(0));
            current.set(1, loc_coor.get(1));
        }
        //System.out.println(loc);
        //System.out.println(ll);
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
        directions.get(1).add(2);
        directions.get(1).add(1);

        directions.put(2,new ArrayList<Integer>());
        directions.get(2).add(2);
        directions.get(2).add(-1);

        directions.put(3,new ArrayList<Integer>());
        directions.get(3).add(-2);
        directions.get(3).add(1);

        directions.put(4,new ArrayList<Integer>());
        directions.get(4).add(-2);
        directions.get(4).add(-1);

        directions.put(5,new ArrayList<Integer>());
        directions.get(5).add(1);
        directions.get(5).add(2);

        directions.put(6,new ArrayList<Integer>());
        directions.get(6).add(1);
        directions.get(6).add(-2);

        directions.put(7,new ArrayList<Integer>());
        directions.get(7).add(-1);
        directions.get(7).add(2);

        directions.put(8,new ArrayList<Integer>());
        directions.get(8).add(-1);
        directions.get(8).add(-2);

    }

    private HashMap<Integer, ArrayList<Integer>> directions;

}