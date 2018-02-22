package solver;

import java.util.ArrayList;
import java.util.List;

public class Solver {
    ArrayList<Integer>[][] sudoku = new ArrayList[9][9];
    Tile[][] tiles = new Tile[9][9];
    Solver(){
        for(int i = 0; i<= 8; i++){
            for(int j = 0; j<= 8; j++){
                tiles[i][j] = new Tile(i,j);
            }
        }
    }
    private class Coords{
        int x;
        int y;
        Coords(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    private class Tile{

        List<Coords> cols;
        List<Coords> rows;
        List<Coords> section;
        Tile(int x,int y){
            rows = generateRow(x);
            cols = generateCol(y);
            section = generateSec(x,y);
        }
        private List<Coords> generateCol(int y){
            List<Coords> cols = new ArrayList();
            for(int i = 0; i<= 8;i++){
                cols.add(new Coords(i,y));
            }
            return cols;
        }
        private List<Coords> generateRow(int x){
            List<Coords> rows = new ArrayList();
            for(int i = 0; i<= 8;i++){
                rows.add(new Coords(x,i));
            }
            return rows;
        }
        private List<Coords> generateSec(int x, int y){
            List<Integer> xc = new ArrayList();
            List<Integer> yc = new ArrayList();
            List<Coords> section = new ArrayList();

            if(x < 3){
                xc.add(0);
                xc.add(1);
                xc.add(2);
            }
            else if(x > 5){
                xc.add(6);
                xc.add(7);
                xc.add(8);
            }
            else{
                xc.add(3);
                xc.add(4);
                xc.add(5);
            }
            if(y < 3){
                yc.add(0);
                yc.add(1);
                yc.add(2);
            }
            else if(y > 5){
                yc.add(6);
                yc.add(7);
                yc.add(8);
            }
            else{
                yc.add(3);
                yc.add(4);
                yc.add(5);
            }
            for(Integer xt : xc){
                for(Integer yt : yc){
                    section.add(new Coords(xt,yt));
                }
            }
            return section;
        }


    }

    public static void main(String[] args){
        Solver solver = new Solver();
        for(Coords t : solver.tiles[3][1].section){
            System.out.println(t.x+", "+t.y);
        }
    }


}
