/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game1;

import java.util.ArrayList;

import game1.NormalGame.DefaultRules;
import sun.util.resources.cldr.ur.CurrencyNames_ur;

/**
 *
 * @author Vision
 */
public class NormalGame extends Game{
	DefaultRules ob;
	NormalGame()
	{
		ob=new DefaultRules();
		  this.numberrevealsquare=0;
	}
    class DefaultRules extends GameRules
    {
    	 int GetScoreChange(PlayerMove currentmove)
    	 {
    		 switch(currentmove.getmovetype())
    		 {
    		 case Reveal:
    		 {
    		 switch(Gamegrid.getSquare(X, Y).getstate())
    		 {
    		 case  OpenedEmpty:
    			 return 10;
    			 
    		 case OpenedNumber:
    			 return Gamegrid.getSquare(X, Y).GetNumber();
    		 
    		 }
    		 break;
    		 }
    		 case Mark:
    		 {
    			 switch(Gamegrid.getSquare(X, Y).GetNumber())
    			 {
    			 case 9:
    				 {
    					 marksquare++;
    					 return 5;
    				 }
    				 
    			  default :
    				  return -1;
    				  
    			 }
    			 
    		 }
    		 default:
    		 {
    			 switch(Gamegrid.getSquare(X, Y).GetNumber())
    			 {
    			 case 9:
    			      {
    			    	  marksquare--;
    			    	  return -5;
    			      }
    				 
    			  default :
    				  return +1;
    				  
    			 }
    		 }
    		 }
			return 0;
         }
    	 void DecideNextPlayer()
    	 {
    		if(currentplayer.getname().equals(players.get(0).getname()))
    			{
    			if(players.size()==2)
    			
    				currentplayer= players.get(1);
    		else currentplayer=players.get(0);
    			}
    		else {
    			currentplayer=players.get(0);
    		}
    		
    	 }
    	
    }
@Override
GameState Applyplayermove() throws Exception {
	
    super.Applyplayermove();
	int sco=ob.GetScoreChange(currentmove);
	currentplayer.setscore(sco+rev);
	currentmove.SetPlayer(currentplayer);
	currentmove.SetScoreMoveResult(currentplayer.getscore());
	currentmove.SetSquareStateMoveResult(Gamegrid.GetState(X, Y));

	currentmove.SetSquare(Gamegrid.getSquare(X, Y));
	moves.add(currentmove);
	GameState state=EndGame();
	ob.DecideNextPlayer();
	return state;
}
public DefaultRules GetOb()
{
	return ob;
}
public void DecideNextPlayer()
{
	ob.DecideNextPlayer();
}
}
