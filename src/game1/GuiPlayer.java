package game1;

import java.util.ArrayList;

public class GuiPlayer extends player{
	public GuiPlayer() {
		// TODO Auto-generated constructor stub
		currentscore=0;
	}
@Override
public PlayerMove GetPlayermove(int i,int j,MoveType type) throws Exception {
	PlayerMove move=new PlayerMove();
	move.SetMoveType(type);
	move.setsquare(i, j);
	return move;	
}
}
