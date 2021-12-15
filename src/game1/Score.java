package game1;

import java.io.Serializable;

public class Score implements Serializable{
private player Player;
private String gamename=".",name;
private int score;
Score()
{

	name="null";
	score=0;
	//Player=new GuiPlayer();
}
public void Setplayer(player Player)
{
	
	this.Player=Player;
	System.out.println(Player.getname());
	name=Player.getname();
	score=Player.getscore();
}
public player getPlayer()
{
	return this.Player; 
}
public String getName()
{
	return name;
}
public int getScore()
{
	return score;
}
public void SetGameName(String gamename)
{
	this.gamename=gamename;
}
public  String getGamename()
{
	return gamename;
	
}
}
