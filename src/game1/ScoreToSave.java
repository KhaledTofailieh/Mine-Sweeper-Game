package game1;

import java.io.Serializable;
import java.util.ArrayList;

public class ScoreToSave implements Serializable{
	ArrayList<Score> scoreslist;
	int  index ;

	ScoreToSave()
	{
		scoreslist=new ArrayList<Score>();
		index=0;
	}
	
	public void AddToList(Score score)
	{
		//index=gameslist.size()+1;
		
		scoreslist.add(index++,score);
		System.out.println("indexscc"+index);
	}

	public ArrayList<Score> GetScoresList()
	{
		return scoreslist;
	}
    public boolean search(Score newscore)
    {
    	if(scoreslist.size()<10)
    		{
    		this.AddToList(newscore);
    		return true;
    		}
    	int i=0;
        boolean t=false;
        Score s;
    	int sco=newscore.getPlayer().getscore();
    	System.out.println("sco ="+sco);
    	for (Score score : scoreslist) {
			if(sco>score.getPlayer().getscore())
				{
				s=score;
				scoreslist.set(i,newscore);
				sco=s.getScore();
				System.out.println("true");
				sco=score.getPlayer().getscore();
				newscore=s;
				t=true;
				}
			i++;
		}
    	
    	return t;
    }

}
