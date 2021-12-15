package game1;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public abstract class Game implements Serializable{
    protected ArrayList<player> players;
     protected player currentplayer;
     protected ArrayList <PlayerMove> moves;
     protected Grid Gamegrid;
    protected PlayerMove currentmove;
     protected GameState gamestate;
     protected int numberrevealsquare,numbersquare,cm,marksquare=0,numberopenmin=0,X,Y,i,index,auxcm,auxcm2,index2;
     static int rev=0;
     protected NumberPlayer num;
     protected ArrayList<Square> AuxliaryList;
     protected String Name;
     protected boolean replay;
     //method
     //default constructer
     Game()
     {
    	 replay=false;
    	 index=index2=0;
    	 i=0;
    	 marksquare=0;
    	 AuxliaryList=new ArrayList<Square>();
    	 moves=new ArrayList<PlayerMove>();
    	 currentmove=new PlayerMove();
    	this.players=new ArrayList<player>();
     }
     //inner class
     class  GameRules implements Serializable
     {
    	 int GetScoreChange(PlayerMove playermove)
    	 {return 0;}
    	 void DecideNextPlayer()
    	 {}
     }
     void SetTime(int time)
     {
     	this.currentmove.SetTime(time);
     }
     int GetTime()
     {
     	return this.currentmove.GetTime();
     }
     public void SetName(String Name)
     {
    	 this.Name=Name;
     }
     public String getName()
     {
    	 return this.Name;
     }
     public void SetAuxCm2(int auxcm2)
     {
    	 this.auxcm2=auxcm2;
     }
     public int GetAuxCm2()
     {
    	 return this.auxcm2;
     }
     public void SetReplay()
     {
    	 replay=true;
     }
     protected void FillFolad(int x, int y){}
     

      //add player to players list
      public void setplayerslist(player  player1)
     {  	
    	 players.add(i,player1);
    	 i++;
     }


   public NumberPlayer GetNumberPlayer()
   {
	   return num;
   }
    public void intGame(int m,int n ,int Cm,NumberPlayer num)
    {
    marksquare=0;
    numberopenmin=0;
   	cm=Cm;
   	auxcm=cm;
    numbersquare=m*n;
    this.num=num;
    Gamegrid=new Grid(m,n,Cm);
    //int grid
    Gamegrid.intGrid();
    currentmove=new PlayerMove();
	if(num==NumberPlayer.TwoPlayer)
	{
		Random random=new Random();
		currentplayer=players.get(random.nextInt(2));
	}
	else
	{
		currentplayer=players.get(0);
	}
    }
    //check if game is end
    public GameState EndGame()
    {
    	if(num==NumberPlayer.OnePlayer)
    	{
    		if(Gamegrid.getSquare(X, Y).GetNumber()==9&&currentmove.getmovetype()==MoveType.Reveal)
    		{		
    			if(currentplayer.GetNumberShield()>0)
    			{
    			
    				currentplayer.SetNumberShield(-1); 
    				Game1.b--;
    				currentplayer.setscore(currentplayer.GetShieldValue());
    				return GameState.Running;
    			}
    		return GameState.EndWithLose;
    		}
    		else if(numberrevealsquare==(numbersquare-cm))
    		{
    			
      			currentmove.setscore(((auxcm-(marksquare))*100)+(currentplayer.GetNumberShield()*50));
    			return GameState.EndWithWin;
    		}
    		return GameState.Running;
    	}
    	
    	else
    	{
    	if(numberrevealsquare==(numbersquare-cm))
    		{
      			currentmove.setscore((auxcm-(marksquare))*100);
      			players.get(0).setscore(players.get(0).GetNumberShield()*50);
      			players.get(1).setscore(players.get(1).GetNumberShield()*50);
    			return GameState.EndWithWin;
    		}
    		 else if(Gamegrid.getSquare(X, Y).GetNumber()==9&&currentmove.getmovetype()==MoveType.Reveal)
    		 {    			
    			 if(currentplayer.GetNumberShield()>0)
        			{

    				    currentplayer.SetNumberShield(-1);
    				    Game1.b--;
        				currentplayer.setscore(currentplayer.GetShieldValue());
        				return GameState.Running;
        			}
    	       return GameState.EndWithLose;
    	    }
    		 return GameState.Running;
    	}
    	}
    //check move
    public boolean AcceptMove(int x,int y,MoveType type) throws Exception
    {
		return false;}
    
    GameState Applyplayermove() throws Exception {
        
    	if (currentmove.getmovetype()==MoveType.Reveal)
    	{
    		int cm=Gamegrid.getSquare(X,Y).GetNumber();
            if(Gamegrid.getSquare(X, Y).IsShield())
            {
            	currentplayer.SetNumberShield(1);
            	currentplayer.AddShieldtoList(Gamegrid.getSquare(X, Y).Get_Shield());
            }
    		//state of empty square 
    	    if(cm==0)
    		{
    			 Gamegrid.getSquare(X, Y).setstate(SquareState.OpenedEmpty);
    			 numberrevealsquare++;
    			 FillFload(X, Y);
    		}
    		//state of number square
    		else if(cm!=9)
    		{
    			 Gamegrid.getSquare(X, Y).setstate(SquareState.OpenedNumber);
                numberrevealsquare++;
                
    		}
    		else
    		{
    			Gamegrid.getSquare(X, Y).setstate(SquareState.OpenedMine);
    			auxcm--;
    		}
    	}
    	//mark
    	else if(currentmove.getmovetype()==MoveType.Mark)
    	{
    		//state of unmarked
    		if(Gamegrid.getSquare(X, Y).getstate()==SquareState.Marked)
    		{
    			currentmove.SetMoveType(MoveType.UnMark);
    			 Gamegrid.getSquare(X, Y).setstate(SquareState.Closed);
    		}
    		else
    		{
    	     Gamegrid.getSquare(X, Y).setstate(SquareState.Marked);
    		}
    		
    	}
   	 AuxliaryList.add(index++,Gamegrid.getSquare(X, Y));
   	Gamegrid.getSquare(X, Y).SetPlayerId(currentplayer.GetId());
		return gamestate;
    	
    }
    
    void setplayer(String name){}
    
    player getplayer()
    {
    	return currentplayer;
    }
    
    void FillFload(int x, int y) {
        SquareState state;      
        int transX[] = {-1,-1,-1,0,0,1,1,1},Ox;
        int transY[] = {-1,0,1,-1,1,-1,0,1},Oy;
        for (int i = 0; i < 8; i++) {
             Ox = x + transX[i];
             Oy = y + transY[i];
             //in of range array
            if (Ox >= 0 && Ox < Gamegrid.getM() && Oy >= 0 && Oy < Gamegrid.getN()) {
            	state=Gamegrid.getSquare(Ox, Oy).getstate();
            	//square is closed and not mine
            	if((state==SquareState.Closed&&Gamegrid.getSquare(Ox, Oy).GetNumber() != 9)||(replay&&!Gamegrid.getSquare(Ox, Oy).GetVisited()))
            	{
            		if(replay)
            		Gamegrid.getSquare(Ox, Oy).SetVisited();
            		 if(Gamegrid.getSquare(Ox, Oy).IsShield()&&!replay)
                     {
            					currentplayer.SetNumberShield(1);
                             	currentplayer.AddShieldtoList(Gamegrid.getSquare(Ox, Oy).Get_Shield());
            			
                     }
            		//square is not empty but is contain number
            	if(Gamegrid.getSquare(Ox, Oy).GetNumber() != 0 )
            	{
            		Gamegrid.getSquare(Ox, Oy).SetPlayerId(currentplayer.GetId());
            	       numberrevealsquare++;
                       rev++;
                  Gamegrid.getSquare(Ox, Oy).setstate(SquareState.OpenedNumber);
                  AuxliaryList.add(index++,Gamegrid.getSquare(Ox, Oy));
            	}
            	//square is empty
            	else
            	{
            		 numberrevealsquare++;
                     rev++;
                Gamegrid.getSquare(Ox, Oy).setstate(SquareState.OpenedEmpty);
                AuxliaryList.add(index++,Gamegrid.getSquare(Ox, Oy));
                 FillFload(Ox, Oy);
            	}
            	
            	}
            }
        }
		}
    // it return result of move for print results in gui
    ArrayList<Square> GetNewOpenedSquare()
    {
    	return AuxliaryList;
    }
    //it delete result of move
    void DeletAuxliaryList() {
    index=0;
		while(!AuxliaryList.isEmpty()) {
			AuxliaryList.remove(0);
		}
	}
    //return squares of mines
    public ArrayList<Square> GetMines()
    {
   	 return Gamegrid.GetMines();
    }
     
    public void DecideNextPlayer()
    {
    	
    }
    public PlayerMove GetCurrentMove()
    {
    	return this.currentmove;
    }
    public Grid GetGameGrid()
    {
    	return Gamegrid;
    }
    public void SaveSquareStatus()
    {
    	int in=0;
    	for(int i=0;i<Gamegrid.getM();i++)
    		for(int j=0;j<Gamegrid.getN();j++)
    		{
    			if(Gamegrid.getSquare(i, j).getstate()!=SquareState.Closed)
    				AuxliaryList.add(in++,Gamegrid.getSquare(i, j));
    		}
    }
    public ArrayList <PlayerMove> GetMoves()

        {
    	return moves;
    	}
    public void segt()
    {
    	index2=0;
    }
    public void arraytoauxliary()
    {
    	index=0;
    	Square sq=moves.get(index2).getsquare();
    	currentmove=moves.get(index2);
    	currentplayer=moves.get(index2++).GetPlayer();
        AuxliaryList.add(index++,sq);
        System.out.println("sq "+sq.GetNumber());
        if(sq.GetNumber()==0)
        	{
        	System.out.println("fill");
        	FillFload(sq.getX(), sq.getY());
        	}
    }
}
