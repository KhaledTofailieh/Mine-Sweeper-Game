
package game1;

import java.io.Serializable;

class PlayerMove implements Serializable{
    private player Player;
    private Square square;
    private MoveType type;
    private MoveResult result;
    private int time;
PlayerMove()
{
    time=0;
    square=new Square();
    result=new MoveResult();
    Player=new player();
}
void SetTime(int time)
{
	this.time=time;
}
int GetTime()
{
	return this .time;
}
void setsquare(int x, int y) 
{
        square.setx_y(x, y);
}
void SetSquare(Square square)
{
	this.square=square;
}
public void SetPlayer(player p)
{
 this.Player=p;   
 //SetScoreMoveResult(Player.getscore());
}
public Square getsquare()
{     
  return square;
}
    public MoveType getmovetype()
    {
        return this.type;
    }
 public void SetMoveType(MoveType type)
        {
        this.type=type;
        }
        public player GetPlayer()
        {
        	return Player;
        }
        public void setscore(int score)
        {
        	Player.setscore(score);
        	result.setscorechange(score);
        }
        public void SetScoreMoveResult(int score)
        {
        	result.setscorechange(score);
        }
        public void SetSquareStateMoveResult(SquareState state)
        {
        	result.setnewstatus(state);
        }
        public MoveResult GetMoveResult()
        {
        	return result;
        }
}
