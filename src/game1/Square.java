/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game1;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Vision
 */
class Square implements Serializable{
    private int x,y;
    private Mine mine;
    private List <player> playerMoves ;
    private int countmine;
    private  SquareState state;
    private Shield shield;
    private String PlayerId;
    private boolean visited;
    Square()
    {
    	visited=false;
    	shield=null;
    	state=SquareState.Closed;
    countmine=0;
    mine=new Mine();
    }
    public void SetVisited()
    {
    	visited=true;
    }
    public boolean GetVisited()
    {
    	return visited;
    }
    public void SetPlayerId(String Id)
    {
    	this.PlayerId=Id;
    }
    public String GetPlayerId()
    {
    	return this.PlayerId;
    }
    public boolean IsMine()
    {
        if(this.mine.getmine().equals("mine"))
        {
          return true;  
        }
        else{
         return false;   
        }
        
    }
    public void setx_y(int x,int y)
    {
    this.x=x;
    this.y=y;
    countmine=0;
    }
    int getX()
    {
    return this.x;
    }
      int getY()
    {
    return this.y;
    }
    public void setmine()
    {
    mine.setid("mine"); 
    countmine=9;
    }
    void setcountmine()
    {
    countmine++;
    }
    int get()
    {
    return countmine;
    }

    public void setstate(SquareState st)
    {
    state=st;
    }
    public SquareState getstate()
    {
    return state;
    }
    public int GetNumber()
    {
        return this.countmine;
    }
   
    public int Get_Shield()
    {
    	return this.shield.GetFinalScore();
    }
    public boolean IsShield()
    {
    	if(shield==null)
    		return false;
        	return true;
    		
    }
    public void IntShield(int x)
    {
    	switch(x)
    	{
    	case 0:
    	{
    		shield=new Half();
    		break;
    	}
    	case 1:
    	{
    		shield=new Full();
    		break;
    	}
    	case 2:
    	{
    		shield=new Extra();
    		break;
    	}
    	default :
    	{    		
    		shield=new Full();
    		break;
    	}
    	}
    	this.shield.Set_Id("shield");
    }
}
