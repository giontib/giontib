/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameOfLife;

import java.util.Arrays;

/**
 *
 * @author giovanti
 */
public class Grid {
    private int gridSize, startCells;
    
    
    public Grid(int _gridSize,int _startCells){
        this.gridSize = _gridSize;
        this.startCells = _startCells;
        
        
    }
    //construct the grid with a square starting cells
    private void constructGrid(Cell[][] grid) {
         int x=0;
        if(startCells%2==0)
             x = (gridSize - startCells)/2;
        else
         x = (gridSize - startCells-1)/2;
        for(int i = 0;i < this.gridSize;i++){
            
            for(int j = 0; j < this.gridSize;j++){
                //when the gridsize is one line bigger than the starting cells
                if(gridSize-1 == startCells){
                    if( j == 0 || j == gridSize-1  )
                        changeState(grid[i][j]=new borderCell(i,j)); 
                    else if( i == 0 || i == gridSize-1  )
                        changeState(grid[i][j]=new borderCell(i,j)); 
                    else
                        grid[i][j]=new MiddleCell(i,j);
                }
                else{
                if( j == 0 || j == gridSize-1  )
                    grid[i][j]=new borderCell(i,j); 
                else if( i == 0 || i == gridSize-1  )
                    grid[i][j]=new borderCell(i,j); 
                
                else if(i == x && (j>=x && j<x+startCells))
                    changeState(grid[i][j]=new MiddleCell(i,j));
                else if(j == x && (i>=x && i<x+startCells))
                    changeState(grid[i][j]=new MiddleCell(i,j));
                else if(j == x+startCells-1 && (i>=x && i<x+startCells))
                    changeState(grid[i][j]=new MiddleCell(i,j));
                else if(i == x+startCells-1 && (j>=x && j<x+startCells))
                    changeState(grid[i][j]=new MiddleCell(i,j));
                else
                    grid[i][j]=new MiddleCell(i,j);
            }
            }
        }
        
    }
    //print the grid
    private void writeGrid(Cell[][] _grid) {
        
        borderCell tmp=null;
        borderCell tmp2=null;
        for(int i = 0;i < this.gridSize;i++){
            tmp2=(borderCell)_grid[i][this.gridSize-1];
            tmp=(borderCell)_grid[i][0];
            System.out.print("[ + "+tmp.getNumberLife()+"/-" +tmp.getNumberDeath()+"]");
            for(int j = 0; j < this.gridSize;j++){
                
                System.out.print(_grid[i][j]);
            }
            System.out.print("[ + "+tmp2.getNumberLife()+"/-" +tmp2.getNumberDeath()+"]");
            System.out.println();
        }
        System.out.println();
        System.out.println();
    }
    //game of life rules
    private void cellRules(Cell[][] xmp){
        
        for(int i = 0; i < this.gridSize;i++){
                for(int j = 0; j < this.gridSize;j++){
            
            int neigborsAlive = neighborAlive(i,j,xmp);
            if(! iAmAlive(xmp[i][j])){
                if(neigborsAlive == 2 ||neigborsAlive == 3  ){
                    
                        changeState(xmp[i][j]);
                    
                }
            }
            if(iAmAlive(xmp[i][j])){
            
                if(neigborsAlive  >= 4)
                            changeState(xmp[i][j]);
                
                else if(neigborsAlive == 0 ||neigborsAlive == 1)
                        changeState(xmp[i][j]);
                    
            }
            
        }
        }
        
    }
    
    private int neighborAlive(int x, int y, Cell[][] tmp){
		int living=0;
		//search all living cells in the 3*3 square surrounding the specified cell
		for(int i=0;i<3;i++)
			for(int j=0;j<3;j++)
				if(canExist(x-1+i,y-1+j))
					if(iAmAlive(tmp[x-1+i][y-1+j]))
						living++;
		//uncount the central cell as it has been counted if it was alive
		if(iAmAlive(tmp[x][y]))
			living--;
		return living;
    }
    
    //avoid the outofbound exception
    private boolean canExist(int x,int y){
		if(x<0 || x>=gridSize || y<0 || y>=gridSize)
			return false;
		return true;
    }
    //check if a cell is alive
    private boolean iAmAlive(Cell xmp) {
       return xmp.getLifeState();
    }
    
    //change the state of the cell
    private void changeState(Cell xmp) {
        if(xmp instanceof borderCell){
            if(xmp.getLifeState()){
                xmp.setLifeState(false);
                adddeath((borderCell)xmp);
            }
            else{
                 xmp.setLifeState(true);
                 addlife((borderCell)xmp);
            }
        }
        else{
            if(xmp.getLifeState())
                xmp.setLifeState(false);
            else
                 xmp.setLifeState(true);
        }
        
    }
    //run the game
    public void tunGameOfLife( ){
        Cell[][] gridTmp = new Cell[gridSize][gridSize];
        constructGrid(gridTmp);
        writeGrid(gridTmp);
        while(true){
       cellRules(gridTmp);
        writeGrid(gridTmp);
        
        }
    }
    
    private void addlife(borderCell xmp){
        xmp.addNumberLife();
    }
    
    private void adddeath(borderCell xmp){
        xmp.addNumberDeath();
    }
    
}
