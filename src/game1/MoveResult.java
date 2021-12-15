/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game1;

import java.io.Serializable;

/**
 *
 * @author Vision
 */
class MoveResult implements Serializable{
    private int scorechange;
    private SquareState newstatus;
    
    MoveResult()
    {
    	scorechange=0;
    }
    public void setscorechange(int scorechange)
    {	
    this.scorechange=scorechange;
    }
    public void setnewstatus(SquareState state)
    {
    this.newstatus=state;
    }
    public int getscore()
    {
    return scorechange;
    }
    public SquareState getstatus()
    {
    return newstatus;
    }
}
