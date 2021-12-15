package game1;

import java.util.ArrayList;

public class GuiGame extends NormalGame{

	   void setplayer(String name)
	    {
	    	    currentplayer=new GuiPlayer();
				currentplayer.IntPlayer(name);
	    	    setplayerslist(currentplayer);
	    }
	   void SetPlayerComputer()
	   {
		   currentplayer=new RandomCP();
			currentplayer.IntPlayer("computer");
   	    setplayerslist(currentplayer);
	   }
	   @Override
	public boolean AcceptMove(int x, int y, MoveType type) throws Exception {
		  	super.rev=0;
		   if(currentplayer.getname().equals("computer"))
		   {
			   currentmove = currentplayer.GetPlayermove(x,y,type);  
			   while(true)
			   {
				   if(Gamegrid.getSquare(currentmove.getsquare().getX(),currentmove.getsquare().getY()).getstate()==SquareState.Closed)
					   break;
				   
				   currentmove = currentplayer.GetPlayermove(x,y,MoveType.Reveal);
			   }
				  
			   this.Y=currentmove.getsquare().getY();
			      this.X=currentmove.getsquare().getX();
			   currentmove.SetPlayer(currentplayer);
			  	return true;
		   }
		   else
   	   	{
			   currentmove = currentplayer.GetPlayermove(x,y,type);        
	           this.Y=currentmove.getsquare().getY();
	           this.X=currentmove.getsquare().getX();
	           int m = Gamegrid.getM(), n = Gamegrid.getN();
	           
	           if(currentmove.getmovetype()==MoveType.Reveal)
	           {
	        	   if (Gamegrid.GetState(X, Y)==SquareState.Closed) 
	   	           	{
	   	           		currentmove.SetPlayer(currentplayer);
	   	           		return true;
	   	           	} 
	        	   else
	        		   return false;
	           }
	           else
	           {
	        	   return true;
	           }
	       }
	}

public ArrayList<player> GetPlayers()
{
	return this.players;
}
public void DecideNextPlayer()
{
	super.DecideNextPlayer();
}
}
