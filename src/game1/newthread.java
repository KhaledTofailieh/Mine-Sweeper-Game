package game1;

import java.util.ArrayList;

import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.application.Platform;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class newthread extends Thread{
	Game1 game1;
	GuiGame game;
	boolean end=false;
	ScaleTransition scale=new ScaleTransition(Duration.millis(300));
	static int  time=0;
	public void SetGame1(Game1 game1)
	{
		this.game1=game1;
	}
	public void SetGame(GuiGame game)
	{
		this.game=game;
	}
	public void set_end()
	{
		end=true;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("name2 "+game.getName());
	
		try {
			
			System.out.println("time  ");
			game.segt();
			game.SetReplay();
			
			ArrayList <PlayerMove> moves=game.GetMoves();
			System.out.println("size  "+moves.size());
			for (PlayerMove playerMove : moves) {
				if(end)
				{
					Game1.time.setText(" ");
					Game1.labscore.setText(" ");
					break;
				}
				time=0;
				System.out.println("time  ");
				game.arraytoauxliary();
				System.out.println("time  "+playerMove.GetTime());
				if(playerMove.GetTime()==0)
					Thread.sleep(500);
		Platform.runLater(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						if(end)
							return;
							scale.setByX(0.2);
							scale.setByY(0.2);
							scale.setAutoReverse(true);
							scale.setCycleCount(2);
							scale.setNode(game1.grid[playerMove.getsquare().getX()][playerMove.getsquare().getY()]);
							scale.play();
						
					}
				});
				while(time<playerMove.GetTime())
				{
					if(end)
						return;
					Thread.sleep(1000);
					time++;
					Platform.runLater(new Runnable() {
						
						@Override
						public void run() {
							// TODO Auto-generated method stub
							if(end)
								return;
							game1.time.setText(String.valueOf(time));
						}
					});
					
				}
		
					Platform.runLater(new Runnable() {
						
						@Override
						public void run() {
							// TODO Auto-generated method stub
							if(end)
								return;
							
							game1.SetEffects();
							game1.lab6.setText(game.getplayer().getname());
							game1.Shieldnum.setText(String.valueOf(game.getplayer().GetNumberShield()));

							game1.labscore.setText(String.valueOf(playerMove.GetMoveResult().getscore()));
						}
					});
						
						
					
					
				
				
		
				
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
