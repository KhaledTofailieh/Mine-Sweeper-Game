
package game1;

import java.util.Scanner;

public class ConsolPlayer extends player {
	 public PlayerMove GetPlayermove(int i,int j,MoveType type) throws Exception 

	    {
		    PlayerMove move;
		    move=new PlayerMove();
	        
	        String InputMove=new String();
	        Scanner read=new Scanner(System.in);
	        InputMove=read.nextLine();
	        if(InputMove.isEmpty())
	        {
	        	throw new Exception();
	        }
	          int x=0, y=0,R;
	        if(InputMove.startsWith("-"))
	        {
	           R=InputMove.charAt(1);
	           if(R>=65&&R<=90)
	           {
	               x=R-65;
	               y=InputMove.charAt(2)-48; 
	               move.SetMoveType(MoveType.Mark);
	           }
	           else
	           {
	            x=R-48;
	            y=InputMove.charAt(2)-65;
	            move.SetMoveType(MoveType.Mark);
	           }
	               
	         
	       }
	       else
	        {
	             R=InputMove.charAt(0);
	           if(R>=65&&R<=90)
	           {
	               x=R-65;
	               y=InputMove.charAt(1)-48;
	               move.SetMoveType(MoveType.Reveal);
	           }
	           else
	           {
	            x=R-48;
	            y=InputMove.charAt(1)-65;
	            move.SetMoveType(MoveType.Reveal);
	           }
	       
	        }
	        move.setsquare(x,y);
	        return move;
	    }
	 
}
