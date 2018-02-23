package solver;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Solver {
    //Håller koll på värdena för sudukorutnätet
    //Håller koll på vilken rad, kolumn, och sektion av sudokurutnätet som en specifik plats är på
    Integer[][] sudoku = new Integer[9][9];
    Tile[][] tiles = new Tile[9][9];
    Solver(){
        for(int i = 0; i<= 8; i++){
            for(int j = 0; j<= 8; j++){
                tiles[i][j] = new Tile(i,j);
            }
        }
    }
    //En klass som håller koll på koordinater
    private class Coords{
        int x;
        int y;
        Coords(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    //En klass som sparar en lista med koordinater för varje rad, kolumn, och sektion av sudokurutnätet som en specifik plats är på
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
    //Kollar om en placerad siffra går att placera tillsammans med checklist
    private Boolean checkValid(int x, int y){
        return checkList(tiles[x][y].cols)&&checkList(tiles[x][y].rows)&&checkList(tiles[x][y].section);
    }
    private Boolean checkList(List<Coords> list){
        ArrayList<Integer> values = new ArrayList();
        for(Coords c : list){
            if(sudoku[c.x][c.y] != null){
                values.add(sudoku[c.x][c.y]);
            }
        }
        return ((new HashSet(values).size())==values.size());

    }


    private Boolean solve(int x, int y){
        if(y>8){
            return true;
        }
        else{
            int x1;
            int y1;
            if( x < 8 ){
                x1 = x+1;
                y1 = y;
            }
            else{
                x1= 0;
                y1 = y+1;
            }


            if(sudoku[x][y]==null){
                for(int i = 1; i<= 9; i++){
                    sudoku[x][y] = i;
                    if (checkValid(x,y)){
                        if(solve(x1,y1)){
                            return true;
                        }
                        sudoku[x][y] = null;
                    }
                    sudoku[x][y] = null;
                }
                return false;
            }
            else{
                if (checkValid(x,y)){
                    if(solve(x1,y1)){
                        return true;
                    }
                }
                return false;
            }
        }
    }


    public static void main(String[] args){
        Solver solver = new Solver();
        solver.sudoku[1][1]=1;
        for(int i = 0; i<= 8; i++){
            for(int j = 0; j<= 8; j++){
                if(solver.checkValid(j,i)){
                    System.out.print(" O ");
                }
                else{
                    System.out.print(" X ");
                }
            }
            System.out.println();
        }
       System.out.println( solver.solve(0,0));
        for(int i = 0; i<= 8; i++){
            for(int j = 0; j<= 8; j++){
                if(solver.sudoku[j][i]!=null){
                    System.out.print("["+solver.sudoku[j][i]+"]");
                }
                else{
                    System.out.print("[0]");
                }

            }
            System.out.println();
        }
        for(int i = 0; i<= 8; i++){
            for(int j = 0; j<= 8; j++){
                if(solver.checkValid(j,i)){
                    System.out.print(" O ");
                }
                else{
                    System.out.print(" X ");
                }
            }
            System.out.println();
        }
    }



}
