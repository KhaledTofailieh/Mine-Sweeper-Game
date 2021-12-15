package game1;

import java.io.Serializable;
import java.util.ArrayList;

public class GamesToSave implements Serializable{
	ArrayList<Game> gameslist;
	int  index ;

	GamesToSave()
	{
		gameslist=new ArrayList<Game>();
		index=0;
	}
	
	public void AddToList(Game game)
	{
		//index=gameslist.size()+1;
		
		gameslist.add(index++,game);
		System.out.println("index"+index);
	}

	public ArrayList<Game> GetGamesList()
	{
		return gameslist;
	}

}
