/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game1;


import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Vision
 */
public class ConsoleGame extends NormalGame {
	
public ConsoleGame()
{

}

    void PrintGrid()
    {
    Gamegrid.print();
    }
         public int getnumberrevealsquare()
     {
         return numberrevealsquare;
     }
         void printh()
         {
         Gamegrid.printh();
         }
       public  void PrintLose()
       {
          Gamegrid.PrintLose();
       }
       public GameState EndGame() 
       {
    	   Scanner reader=new Scanner(System.in);
    	   String choice;
         GameState state=super.EndGame();
         if(state==GameState.EndWithLose)
         {
       	  if(currentplayer.getscore()>250)
       	  {
       		  System.out.println("\nyou lose but you can Continue Game and get 250 from your score");
       		 
       		  while(true)
       		  {
       			 choice=reader.nextLine();
       		  
       		  if(choice.isEmpty()||(!choice.equals("yes")&&!choice.equals("no")))
       		  {
       			System.out.println("Try Again"); 
       		  }
       		  else
       		  {
       			  if(choice.equals("yes"))
       			  {
       				  this.currentplayer.setscore(-250);
       				  return GameState.Running;
       			  }
       			  else
       			  {
       				  return GameState.EndWithLose;
       			  }
       		  }
       		  }
       		  
       	  }
       	 
         }
          return state;
         
       }
       @Override
    	public boolean AcceptMove(int x,int y,MoveType type) throws Exception {
    		// TODO Auto-generated method stub
    	   	currentmove = currentplayer.GetPlayermove(x,y,type);        
    	     this.Y=currentmove.getsquare().getY();
    	      this.X=currentmove.getsquare().getX();
    	      
    	       int m = Gamegrid.getM(), n = Gamegrid.getN();
    	       if (X >= 0 && X < m && Y >= 0 && Y < n) {
    	           if (Gamegrid.GetState(X, Y)==SquareState.Closed||Gamegrid.GetState(X, Y)==SquareState.Marked) {
    	           	currentmove.SetPlayer(currentplayer);
    	           	super.rev=0;
    	              
    	           }
    	           else { throw new Exception();}
    	       }
    	       else {
    	       	 throw new Exception();
    	       }
			return true;
    		
    	}
       void setplayer(String name)
	    {
	    	    currentplayer=new ConsolPlayer();
				currentplayer.IntPlayer(name);
	    	    setplayerslist(currentplayer);
	    }


       @Override
    	GameState Applyplayermove() throws Exception {
    		
    		super.Applyplayermove();
    		return EndGame();
    	}
}
