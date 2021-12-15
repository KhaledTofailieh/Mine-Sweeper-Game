package game1;

import javafx.application.Platform;
import javafx.scene.control.Label;

public class thread extends Thread{
	private boolean move,end,replay;
	private int time;
	private GuiGame game;
	thread()
	{
		move=end=false;
		time=0;
	}
	public void SetMove()
	{
		move=true;
	}
    public void SetEnd()
    {
    	end=true;
    }
    public void SetReplay()
    {
    	replay=true;
    }
	public void Set_Game(GuiGame game)
	{
		this.game= game;
	}
public void run()
{
	while(!end)
	{
		while(!move&&time<10&&!end)
		{
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			time++;
			
		Platform.runLater(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				Game1.time.setText(String.valueOf(time));
			   
			}
		});
			
		}
		if(replay)
		{
			if(time==game.GetTime())
			{
				Game1.sleep=false;
				System.out.println("bnkludh");
				System.out.println(Game1.sleep+"mmmmm");
				move=true;
			}
		}
		if(time==10)
		{
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			this.game.DecideNextPlayer();
			 game.SetTime(time);
			 Platform.runLater(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
				
						{
							
						
						Game1.lab6.setText(game.getplayer().getname());
						Game1.Shieldnum.setText(String.valueOf(game.getplayer().GetNumberShield()));

					
						}
					}
				});
		}
		 game.SetTime(time);
		move=false;
		time=0;
	}
}
}
