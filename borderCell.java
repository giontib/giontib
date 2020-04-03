/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameOfLife;

/**
 *
 * @author giovanti
 */
public class borderCell extends Cell {
    
    private int numberLife, numberDeath;
    
    public borderCell(int _i, int _j){
        super(_i,_j);
        this.numberDeath = 0;
        this.numberLife = 0;
    }
    
    public void addNumberLife(){
        this.numberLife ++;
    }
    
    public int getNumberLife(){
        return this.numberLife;
    }
    public void addNumberDeath(){
        this.numberDeath ++;
    }
    
    public int getNumberDeath(){
        return this.numberDeath;
    }
  
}
