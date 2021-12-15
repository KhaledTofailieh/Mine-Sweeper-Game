/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game1;

import java.io.Serializable;
import java.util.ArrayList;

class player implements Serializable{
	protected int NumberShield;
    protected String name,Id;
    protected int currentscore,index;
   protected ArrayList<Integer> Shields;
    player()
    {
        currentscore=0;
        NumberShield=0;
        index=0;
    }
    public void SetId(String Id)
    {
    	this.Id=Id;
    }
    public String GetId()
    {
    	return this.Id;
    }
    public void IntPlayer(String name)  {
        Shields =new ArrayList<Integer>();

    	 this.name=name;
    }
    public String getname()
    {

    	return this.name;
    }
    public void SetNumberShield(int NumberShield)
    {
    	this.NumberShield+=NumberShield;
    }
    public int GetNumberShield()
    {
    	return this.NumberShield;
    }
   public PlayerMove GetPlayermove(int i,int j,MoveType type) throws Exception  {return null;}
public void setscore(int score) {
	// TODO Auto-generated method stub
	
	this.currentscore+=score;	
}
public int getscore() {
	// TODO Auto-generated method stub
	
	return currentscore;
}
public int GetShieldValue()
{
	int x=0;
		 x=Shields.get(0).intValue();	
 index--;
 Shields.remove(0);
 
 return x;
}
public void AddShieldtoList(int x)
{
	Shields.add(index++,(Integer)x);
}
}
    
  

