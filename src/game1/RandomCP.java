package game1;

import java.util.Random;

public class RandomCP extends ComputerPlayer{
	
public PlayerMove GetPlayermove(int i,int j,MoveType type) 
	{
		PlayerMove move=new PlayerMove();
		Random rand=new Random();
		int x=rand.nextInt(i);
		int y=rand.nextInt(j);
		move.SetMoveType(MoveType.Reveal);
		move.setsquare(x, y);
		return move;
	}

}
