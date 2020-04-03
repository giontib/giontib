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
public class Cell {
    private int i,j;
    private boolean lifeState = false;
    
    public Cell(int _i, int _j){
        this.i = _i;
        this.j = _j;
    }
    public int getIndex(){
        return this.i;
    }
    public int getJindex(){
        return this.j;
    }
    
    public void setLifeState(boolean _state){
        this.lifeState = _state;
    }
    
    public boolean getLifeState(){
        return this.lifeState;
    }
    
    @Override
    public String toString() {
        if(this.lifeState)
            return " + ";
        else
            return " - ";
    }
}

