/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game1;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Timer;

import com.sun.javafx.scene.control.SizeLimitedList;

import javafx.animation.ScaleTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.Effect;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

/**
 *
 * @author Vision
 */
public class Game1 extends Application{
	Effect e;
	
	int m, n, cm, sh, count = 0;
	static int b;
	Stage FinalStage = new Stage();
	NumberPlayer num;
	Label lab4 = new Label(), lab5 = new Label(), numbermine = new Label(), gameover = new Label();
	static Label lab6 = new Label(), time = new Label(),labscore=new Label(),Shieldnum=new Label();
	Button[][] grid;
	GuiGame game;//=new GuiGame();
	Button back2 = new Button("Back"), topmenu = new Button("To Top Menu");
	AnchorPane root2 = new AnchorPane();
	int numberplayer = 0;
	boolean bool = false,rep=false,boolsave;// ,st=false;
	static thread th;
	ObjectOutputStream Out;
	ObjectInputStream In;
	String savepath ;
	static boolean sleep;
	TableView<Game> table;
	TableView <Score> table1;
	ArrayList<GuiGame> Games;
	newthread k;
	
	Image saveimg=new Image(getClass().getResourceAsStream("savem.png"));
	Image replayimg=new Image(getClass().getResourceAsStream("replaym.png"));
	
	MenuItem saveItem=new MenuItem("Save",new ImageView(saveimg));
	MenuItem replayItem=new MenuItem("Save Replay",new ImageView(replayimg));
	GamesToSave gamestosave, auxgamestosave;// =new GamesToSave();
	ScoreToSave scoresave,auxscorestosave;
	Score score,score1,score2=new Score();
     /*Game1(GuiGame g)
     {
    	game=g; 
     }*/
	private void IntGrid() {
		//b = cm;
		// st=true
		grid = new Button[m][n];
		int x = 0, y = 10;
		for (int i = 0; i < m; i++) {
			y += 53;
			x = 0;
			grid[i] = new Button[m];
			for (int j = 0; j < n; j++) {
				x += 53;
				grid[i][j] = new Button();
				grid[i][j].setLayoutX(x);
				grid[i][j].setLayoutY(y);

				grid[i][j].setId("grid");
				root2.getChildren().add(grid[i][j]);
			}
		}
	}

	private void printmines() {
		ArrayList<Square> mine = game.GetMines();
		for (Square square : mine) {
			grid[square.getX()][square.getY()].setId("mine");

		}
	}

	public void SetEffects() {
	
		ArrayList<Square> AuxliaryList = game.GetNewOpenedSquare();
		int x, y;
		boolean bo = true;
		for (int i=0;i<AuxliaryList.size();i++) {
			Square square=AuxliaryList.get(i);
			bo = true;
			x = square.getX();
			y = square.getY();
			 if (square.getstate() == SquareState.Marked ) {
				grid[x][y].setId("mark");
			}
			 
		
			if (square.getstate() != SquareState.Marked && square.getstate() != SquareState.Closed) {
				if (square.IsShield()) {
					bo = false;
					grid[x][y].setId("Shield");

				}
				
				else
					grid[x][y].setId(square.GetPlayerId());
			}
			switch (square.getstate()) {
			case OpenedEmpty: {
				if (bo) {
					grid[x][y].setId("OpendEmpty");

				}
				grid[x][y].setDisable(true);
				break;
			}
			case OpenedNumber: {
				switch (square.GetNumber()) {
				case 1: {
					grid[x][y].setText("1");
					grid[x][y].setTextFill(Color.BLUE);
					grid[x][y].setDisable(true);
					break;
				}
				case 2: {
					grid[x][y].setText("2");
					grid[x][y].setTextFill(Color.GREEN);
					grid[x][y].setDisable(true);
					break;
				}
				case 3: {
					grid[x][y].setText("3");
					grid[x][y].setTextFill(Color.RED);
					grid[x][y].setDisable(true);
					break;
				}
				case 4: {
					grid[x][y].setText("4");
					grid[x][y].setTextFill(Color.rgb(233, 179, 35));
					grid[x][y].setDisable(true);
					break;
				}
				case 5: {
					grid[x][y].setText("5");
					grid[x][y].setTextFill(Color.rgb(233, 179, 35));
					grid[x][y].setDisable(true);
					break;
				}
				case 6: {
					grid[x][y].setText("6");
					grid[x][y].setTextFill(Color.rgb(233, 179, 35));
					grid[x][y].setDisable(true);
					break;
				}
				case 7: {
					grid[x][y].setText("7");
					grid[x][y].setTextFill(Color.GREEN);
					grid[x][y].setDisable(true);
					break;
				}
				case 8: {
					grid[x][y].setText("8");
					grid[x][y].setTextFill(Color.RED);
					grid[x][y].setDisable(true);
					break;
				}
				}
				break;
			}
			case OpenedMine: {
				grid[x][y].setId("mine");
				grid[x][y].setDisable(true);

			}
				break;

			}
		}
		game.DeletAuxliaryList();
	}

	private GameState EventOfButton(MouseEvent event, int x, int y) throws Exception {
		if (event.getButton() == MouseButton.PRIMARY) {

			if (game.AcceptMove(x, y, MoveType.Reveal)) {
				return game.Applyplayermove();
			} else {
				return GameState.Running;
			}
		} else {
			game.AcceptMove(x, y, MoveType.Mark);
			if (grid[x][y].getId().equals("mark")) {
				grid[x][y].setId("grid");
				b++;
			} else {

				grid[x][y].setId("mark");
				b--;
			}
			return game.Applyplayermove();

		}

	}

	private void ActionOfButton(Stage primaryStage, Scene scene3) {
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				int x = i, y = j;
				// lab6.setText(game.getplayer().GetName()+" |Shields
				// "+game.getplayer().GetNumberShield());
				grid[i][j].setOnMouseClicked(new EventHandler<MouseEvent>() {

					@Override
					public void handle(MouseEvent event) {
						// TODO Auto-generated method stub
						try {

							GameState state;
							// one player
							if (num == NumberPlayer.OnePlayer) {
								state = EventOfButton(event, x, y);
								if (state == GameState.EndWithLose) {

									// lose and score more than 250 new chance
									if (game.getplayer().getscore() > 10) {
										lab4.setText("You lose your score is:   " + game.getplayer().getscore()
												+ "  \nDo you want to complete game?");
										primaryStage.setWidth(525);
										primaryStage.setHeight(400);
										primaryStage.setScene(scene3);
									}
									// lose end game
									else {
										gameover.setManaged(true);
										lab5.setText("You Lose Your Score Is " + game.getplayer().getscore());
										gameover.setText("Game Over");
										SetEffects();
										printmines();
										setdisable();
										FinalStage.show();
										saveItem.setDisable(true);
										LoadScore();
										score.Setplayer(game.getplayer());
										score.SetGameName("Game");
									
										if(scoresave.search(score))
											{
											//scoresave.AddToList(score);
											SaveScore();
											}
									}
								}
								// win end game
								else if (state == GameState.EndWithWin) {

									gameover.setText("Game Ended");
									gameover.setManaged(true);
									lab5.setText("You Win Your Score Is " + game.getplayer().getscore());

									LoadScore();
									score.Setplayer(game.getplayer());
									score.SetGameName("Game");
								
									if(scoresave.search(score))
										{
										//scoresave.AddToList(score);
										SaveScore();
										}
									SetEffects();
									setdisable();
									FinalStage.show();
									saveItem.setDisable(true);
								}
								// running game
								else {
									saveItem.setDisable(false);
									SetEffects();
								}
							} else if (num == NumberPlayer.TwoPlayer) {
								th.SetMove();
								state = EventOfButton(event, x, y);
								saveItem.setDisable(false);
								replayItem.setDisable(true);

								SetEffects();
								if (state == GameState.EndWithWin) {

									String s1 = game.GetPlayers().get(0).getname(),
											s2 = game.GetPlayers().get(1).getname();
									int n1 = game.GetPlayers().get(0).getscore(),
											n2 = game.GetPlayers().get(1).getscore();
									lab5.setText(s1 + " " + n1 + "         " + s2 + " " + n2);
									gameover.setText("Game Ended");
									gameover.setManaged(true);
									setdisable();
									FinalStage.show();
									th.SetEnd();
									saveItem.setDisable(true);
									replayItem.setDisable(false);
									LoadScore();
									score.Setplayer(game.GetPlayers().get(0));
									score.SetGameName("Game");
								
									scoresave.search(score);
									score1=new Score();
									score1.Setplayer(game.GetPlayers().get(1));
									score1.SetGameName("Game");
								
									scoresave.search(score1);
										
										SaveScore();
										
								} else if (state == GameState.EndWithLose) {
									game.getplayer().setscore(-5);
									b--;
									
								}
								
							} else {
								if (game.getplayer().getname().equals("computer")) {
									game.AcceptMove(m, n, MoveType.Reveal);
									state = game.Applyplayermove();
									th.SetMove();
								} else {
									state = EventOfButton(event, x, y);
									th.SetMove();
								}
								SetEffects();
								if (state == GameState.EndWithWin) {

									String s1 = game.GetPlayers().get(0).getname(),
											s2 = game.GetPlayers().get(1).getname();
									int n1 = game.GetPlayers().get(0).getscore(),
											n2 = game.GetPlayers().get(1).getscore();
									lab5.setText(s1 + " " + n1 + "         " + s2 + " " + n2);
									gameover.setText("Game Ended");
									gameover.setManaged(true);
									LoadScore();
									score.Setplayer(game.GetPlayers().get(0));
									score.SetGameName("Game");
								
									scoresave.search(score);
									score1=new Score();
									score1.Setplayer(game.GetPlayers().get(1));
									score1.SetGameName("Game");
									scoresave.search(score1);
									SaveScore();
									FinalStage.show();
									setdisable();
									saveItem.setDisable(true);
									replayItem.setDisable(false);
								} else if (state == GameState.EndWithLose) {
									game.getplayer().setscore(-5);
									b--;
									saveItem.setDisable(true);
									replayItem.setDisable(false);
								}
							}
						}

						catch (Exception e) {
							// TODO Auto-generated catch block

						}
						lab6.setText(game.getplayer().getname());
						Shieldnum.setText(String.valueOf(game.getplayer().GetNumberShield()));

						numbermine.setText(String.valueOf(b));
					}
				});
			}
		}

	}

	private void setdisable() {
		for (int i = 0; i < m; i++)
			for (int j = 0; j < n; j++)
				grid[i][j].setDisable(true);

	}

	private void SaveGame() {
		try {

			LoadGame();
			Out = new ObjectOutputStream(new FileOutputStream(savepath));
			gamestosave.AddToList(game);
			Out.writeObject(gamestosave);
			Out.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void LoadGame() {
		try {
			In = new ObjectInputStream(new FileInputStream(savepath));
			// Games=new ArrayList<GuiGame>();
			try {
				int i = 0;
				auxgamestosave = (GamesToSave) In.readObject();
				gamestosave = new GamesToSave();
				System.out.println("load");
				In.close();
		
				for (Game gamesave : auxgamestosave.GetGamesList()) {
					gamestosave.AddToList(gamesave);
					System.out.println("name" + gamesave.getName());
				}

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				In.close();
				gamestosave = new GamesToSave();
			} catch (EOFException e) {
				gamestosave = new GamesToSave();
				In.close();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
		
			gamestosave = new GamesToSave();
		
		}
	}
public boolean search(String name)
{
	for (Game guiGame : gamestosave.GetGamesList()) {
		if(guiGame.getName().equals(name))
		{
			game=(GuiGame)guiGame;
			b = game.GetAuxCm2();
			m = game.GetGameGrid().getM();
			n = game.GetGameGrid().getN();
			num = game.GetNumberPlayer();
			return true;
		}
	}
	return false;
}
	public void SaveScore()
	{
		try {
		Out = new ObjectOutputStream(new FileOutputStream("savescore"));
		//scoresave.AddToList(score);
		//scoresave=new ScoreToSave();
		Out.writeObject(scoresave);
		Out.close();

	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

		
	}
	public void LoadScore()
	{
		try {
			In = new ObjectInputStream(new FileInputStream("savescore"));
			// Games=new ArrayList<GuiGame>();
			try {
				int i = 0;
				auxscorestosave = (ScoreToSave) In.readObject();
				scoresave = new ScoreToSave();
				System.out.println("load");
				In.close();
		
				for (Score sco : auxscorestosave.GetScoresList()) {
					scoresave.AddToList(sco);
					System.out.println("name" + sco.getPlayer().getname());
				}

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				In.close();
				scoresave = new ScoreToSave();
			} catch (EOFException e) {
				scoresave = new ScoreToSave();
				In.close();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
		
			scoresave = new ScoreToSave();
		
		}
	
	}
	private ObservableList<Game> GetGames()
	{
		ObservableList<Game> GamesName=FXCollections.observableArrayList();
		for (Game game : gamestosave.GetGamesList()) {
			GamesName.add(game);
		}
		return GamesName;
	}
	private ObservableList<Score> GetScore()
	{
		ObservableList<Score> Scores=FXCollections.observableArrayList();
		for (Score game : scoresave.GetScoresList()) {
			Scores.add(game);
		}
		return Scores;
	}
	private void MouseEnteredExited(Button button) {
		button.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
			switch (button.getId()) {
			case "mainbtn": {
				button.setId("mainselected");
				break;
			}
			case "btn": {
				button.setId("btnselected");
				break;
			}
			}
		});
		button.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {

			switch (button.getId()) {
			case "mainselected": {
				button.setId("mainbtn");
				break;
			}
			case "btnselected": {
				button.setId("btn");
				break;
			}
			}
		});
	}

	
	private void Replay()
	{
		/*th=new thread();
		th.start();
		th.Set_Game(game);
		th.SetReplay();*/
		k=new newthread();
k.SetGame(game);
k.SetGame1(this);
k.start();
		
			
			
				
			//SetEffects();
			
		
	}
	@Override
	public void start(Stage primaryStage) throws IOException {
		try {
			
            Stage getloadinfo=new Stage();
            
			AnchorPane root = new AnchorPane();
			AnchorPane root1 = new AnchorPane();
			Group root3 = new Group();
			AnchorPane root4 = new AnchorPane();
			AnchorPane root5 = new AnchorPane();
			AnchorPane root6 = new AnchorPane();
			Group root7 = new Group();
			AnchorPane root8 = new AnchorPane();
			
		
			Scene scene  = new Scene(root, 600, 600, Color.AZURE);
			Scene scene1 = new Scene(root1, 600, 600, Color.BEIGE);
			Scene scene2 = new Scene(root2, 625, 725,Color.rgb(3,47,125));
			Scene scene3 = new Scene(root3, 500, 350, Color.BLACK);
			Scene scene4 = new Scene(root4, 600, 600, Color.BLACK);
			Scene scene5 = new Scene(root5, 400, 200);
			Scene scene6 = new Scene(root6, 600,600);
			Scene scene7 = new Scene(root7, 300, 200);
            Scene scene8 = new Scene(root8,600,600);
            
            
			Button single = new Button("Single Player"), multi = new Button("Multi Player");
			Button exit   = new Button("Exit"), back = new Button("Back");
			Button Start  = new Button("Start Game"), Pc = new Button("Computer"), yes = new Button("Yes"),
					   no = new Button("No");
			Button Startcustom = new Button("Start Game"), Backcustom = new Button("Back");
			Button Save = new Button("save"), Load = new Button("Load game"),Go = new Button("Go"),Delet = new Button("Delete"),
			      back3 = new Button("Back"),submit= new Button("Submit"),Replay= new Button("Replay game"),
					scoreboard=new Button("Score Board"),showreplay = new Button("Watch"),back4 = new Button("Back");
			showreplay.setDisable(true);
			
            ScaleTransition Scale=new ScaleTransition(Duration.millis(600));
            Scale.setByX(0.2);
            Scale.setByY(0.2);
            Scale.setAutoReverse(true);
            Scale.setCycleCount(10);
           
           
            
			MenuBar Gamemenu=new MenuBar();
			Menu file=new Menu("File");
			Gamemenu.setId("menu");
			
		   Alert developer=new Alert(AlertType.INFORMATION);
		    developer.setTitle("Mine Sweeper");
		    developer.setHeaderText("Developers :");
		    developer.setContentText("Qusai Hossien \nAli Durrah \nKhaled Tofaileeh");
		Stage window=(Stage)developer.getDialogPane().getScene().getWindow();
	
		
		file.getItems().addAll(saveItem,replayItem);
			Gamemenu.getMenus().add(file);
			
			Image startarrow=new Image(getClass().getResourceAsStream("arrow.png"));
			Image backimg=new Image(getClass().getResourceAsStream("barrow.png"));
			Image backimage=new Image(getClass().getResourceAsStream("barrowW.png"));
			Image backRed=new Image(getClass().getResourceAsStream("barrowR.png"));
			
			Image homeimage=new Image(getClass().getResourceAsStream("home.png"));
			Image exitimage=new Image(getClass().getResourceAsStream("exit.png"));
			Image loadimage=new Image(getClass().getResourceAsStream("loimg.png"));
            Image  singleimage=new Image(getClass().getResourceAsStream("single.png"));
			Image  multiimage=new Image(getClass().getResourceAsStream("multi.png"));
			Image  repimage=new Image(getClass().getResourceAsStream("rep.png"));
			Image  Pcimage=new Image(getClass().getResourceAsStream("Pc.png"));
			Image  subimage=new Image(getClass().getResourceAsStream("submit.png"));
			Image  delimage=new Image(getClass().getResourceAsStream("del.png"));
			Image goimage=new Image(getClass().getResourceAsStream("go1.png"));
			Image heart=new Image(getClass().getResourceAsStream("shieder.png"));
			Image highsco=new Image(getClass().getResourceAsStream("highsco.png"));
			Image animation=new Image(getClass().getResourceAsStream("animaion.png"));
			Image icon=new Image(getClass().getResourceAsStream("icon.png"));
			Image watchimage=new Image(getClass().getResourceAsStream("watch.png"));
			
			ImageView heartv=new ImageView(heart);
			ImageView animationv=new ImageView(animation);
			
			 primaryStage.getIcons().add(icon);
			 window.getIcons().add(singleimage);
			 Scale.setNode(animationv);
			developer.setGraphic(new ImageView(icon));
			 
			 Scale.play(); 
		
			heartv.setLayoutX(260);
			heartv.setLayoutY(22);
			
			root. setId("root1");
			root1.setId("root2");
			root2.setId("root3");
			root4.setId("root4");
			root5.setId("root5");
			root6.setId("root6");
			root8.setId("root6");
			
			
			
		
			CheckBox RbEasy = new CheckBox("Easy"), RbHard = new CheckBox("Hard"),
			Custom = new CheckBox("Custom");
			TextField player1 = new TextField(), player2 = new TextField();
			TextField Column = new TextField(), Rows = new TextField(), Mines = new TextField();
			TextField Shields = new TextField(),getGameName=new TextField();

			Label lab1 = new Label("Player 1 "), lab2 = new Label("Player 2"), lab3 = new Label("Level");
			Label ColLab = new Label("Columns"), RowLab = new Label("Rows"), MinLab = new Label("Mines");
			Label lab7 = new Label(), SheldLab = new Label("Sheilds"), text = new Label(),
			chance = new Label("New Chance"),entername=new Label(" Game You Will Save");

			TableColumn<Game,String> name=new TableColumn<>("Saved games");
			name.setMinWidth(250);
			name.setCellValueFactory(new PropertyValueFactory<>("Name"));
		      
			TableColumn<Score,String> playername=new TableColumn<>("Player Name");
			playername.setMinWidth(200);
			playername.setCellValueFactory(new PropertyValueFactory<>("name"));
			
			TableColumn<Score,String> gamename=new TableColumn<>("Game Name");
			gamename.setMinWidth(200);
			gamename.setCellValueFactory(new PropertyValueFactory<>("gamename"));
			
			TableColumn<Score,String> sco=new TableColumn<>("Score");
			sco.setMinWidth(200);
			sco.setCellValueFactory(new PropertyValueFactory<>("score"));
			
			single.setLayoutX(200);
			single.setLayoutY(70);
			single.setId("mainbtn");
			single.setGraphic(new ImageView(singleimage));
			
			multi.setLayoutX(200);
			multi.setLayoutY(140);
			multi.setId("mainbtn");
			multi.setGraphic(new ImageView(multiimage));
			
			Pc.setLayoutX(200);
			Pc.setLayoutY(210);
			Pc.setId("mainbtn");
			Pc.setGraphic(new ImageView(Pcimage));
			
			Load.setLayoutX(200);
			Load.setLayoutY(280);
			Load.setId("mainbtn");
			Load.setGraphic(new ImageView(loadimage));
			
			Replay.setLayoutX(200);
			Replay.setLayoutY(350);
			Replay.setId("mainbtn");
			Replay.setGraphic(new ImageView(repimage));
			
			
			scoreboard.setLayoutX(200);
			scoreboard.setLayoutY(420);
			scoreboard.setId("mainbtn");
			scoreboard.setGraphic(new ImageView(highsco));
			
			exit.setLayoutX(200);
			exit.setLayoutY(490);
			exit.setId("mainbtn");
			exit.setGraphic(new ImageView(exitimage));
			
			Start.setLayoutX(400);
			Start.setLayoutY(500);
			Start.setId("btn");
			Start.setGraphic(new ImageView(startarrow));
			
			back.setLayoutX(30);
			back.setLayoutY(500);
			back.setId("btn");
			back.setGraphic(new ImageView(backimage));
			
			no.setLayoutX(130);
			no.setLayoutY(250);
			no.setId("btn");
			yes.setLayoutX(280);
			yes.setLayoutY(250);
			yes.setId("btn");
			
			topmenu.setLayoutX(50);
			topmenu.setLayoutY(625);
			topmenu.setId("mainbtn");
			topmenu.setGraphic(new ImageView(homeimage));
			
			back2.setLayoutX(385);
			back2.setLayoutY(625);
			back2.setId("mainbtn");
			back2.setGraphic(new ImageView(backimg));
			
			Startcustom.setLayoutX(400);
			Startcustom.setLayoutY(500);
			Startcustom.setId("btn");
			Startcustom.setGraphic(new ImageView(startarrow));
			
			Backcustom.setLayoutX(30);
			Backcustom.setLayoutY(500);
			Backcustom.setId("btn");
			Backcustom.setGraphic(new ImageView(backimage));
			
			Go.setLayoutX(450);
			Go.setLayoutY(500);
			Go.setId("btn6");
			Go.setGraphic(new ImageView(goimage));
			
			Delet.setLayoutX(450);
			Delet.setLayoutY(400);
			Delet.setId("btn6");
			Delet.setGraphic(new ImageView(delimage));
			
			back3.setLayoutX(30);
			back3.setLayoutY(500);
			back3.setId("btn6");
			back3.setGraphic(new ImageView(backRed));
			
			submit.setLayoutX(100);
			submit.setLayoutY(100);
            submit.setGraphic(new ImageView(subimage));
            
            showreplay.setLayoutX(450);
            showreplay.setLayoutY(500);
            showreplay.setId("btn6");
            showreplay.setGraphic(new ImageView(watchimage));
            
            back4.setLayoutX(30);
            back4.setLayoutY(500);
            back4.setId("btn6");
            back4.setGraphic(new ImageView(backRed));
            
            
            
			RbEasy.setLayoutX(400);
			RbEasy.setLayoutY(275);
			RbEasy.setId("Radio");
			
			RbHard.setLayoutX(400);
			RbHard.setLayoutY(305);
			RbHard.setId("Radio");
			
			Custom.setLayoutX(400);
			Custom.setLayoutY(335);
			Custom.setId("Radio");

			//text fields
			
			player1.setLayoutX(150);
			player1.setId("field");
			player1.setLayoutY(100);
			
			player2.setLayoutX(150);
			player2.setLayoutY(150);
			player2.setId("field");
			
			Rows.setLayoutX(150);
			Rows.setLayoutY(200);
			Rows.setId("field2");
			
			Column.setLayoutX(150);
			Column.setLayoutY(250);
			Column.setId("field2");
			
			Mines.setLayoutX(150);
			Mines.setLayoutY(300);
			Mines.setId("field2");
			
			Shields.setLayoutX(150);
			Shields.setLayoutY(350);
			Shields.setId("field2");
			
			getGameName.setLayoutX(10);
			getGameName.setLayoutY(50);
			
			//labels
			
			chance.setLayoutX(90);
			chance.setLayoutY(50);
			chance.setId("gameover");
			
			lab1.setLayoutX(30);
			lab1.setLayoutY(100);
			lab2.setLayoutX(30);
			lab2.setLayoutY(150);
			

			lab3.setLayoutX(320);
			lab3.setLayoutY(305);
			lab3.setId("lab3");
			
			lab4.setLayoutX(75);
			lab4.setLayoutY(120);
			lab4.setId("lab3");
			lab5.setLayoutX(50);
			lab5.setLayoutY(100);
			lab5.setId("lab5");
			gameover.setLayoutX(40);
			gameover.setLayoutY(30);
			gameover.setManaged(false);
			gameover.setId("gameover");
			lab6.setLayoutX(125);
			lab6.setLayoutY(21);
			lab6.setMaxWidth(150);
			
			Shieldnum.setLayoutX(300);
			Shieldnum.setLayoutY(23);
			
			labscore.setLayoutX(400);
			labscore.setLayoutY(21);
			time.setLayoutX(10);
			time.setLayoutY(400);
			
			numbermine.setLayoutX(500);
			numbermine.setLayoutY(20);
			numbermine.setId("time");
			lab7.setLayoutX(80);
			lab7.setLayoutY(350);
			lab7.setId("lab5");
			time.setLayoutX(310);
			time.setLayoutY(625);
			time.setId("time");
			
			ColLab.setLayoutX(30);
			ColLab.setLayoutY(200);
			ColLab.setId("lab3");
			
			RowLab.setLayoutX(30);
			RowLab.setLayoutY(250);
			RowLab.setId("lab3");
			
			MinLab.setLayoutX(30);
			MinLab.setLayoutY(300);
			MinLab.setId("Lab3");
			
			SheldLab.setLayoutX(30);
			SheldLab.setLayoutY(350);
			text.setLayoutX(30);
			text.setLayoutY(20);
			text.setText("Notice :\nThe Count Of Mines Must Be Less Than (m*n/4)\nAnd More Than (m*n/6)\n"
					+ "The Count Of Shields Must Be Less Than (mines/4)\nAnd More Than 0 ");
			text.setId("txt");
			ProgressBar Pb = new ProgressBar();
			Pb.setManaged(false);
			Pb.setLayoutX(135);
			Pb.setLayoutY(330);
			animationv.setLayoutX(500);
			animationv.setLayoutY(500);

			single.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					num = NumberPlayer.OnePlayer;
					player2.setDisable(true);
					lab2.setDisable(true);
					primaryStage.setScene(scene1);

				}
			});

			multi.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					num = NumberPlayer.TwoPlayer;
					player2.setDisable(false);
					lab2.setDisable(false);
					primaryStage.setScene(scene1);

				}
			});

			back.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					primaryStage.setScene(scene);

				}
			});

			Pc.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					num = NumberPlayer.ComputerPlayer;
					player2.setDisable(true);
					lab2.setDisable(true);
					primaryStage.setScene(scene1);

				}
			});

			RbHard.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					// TODO Auto-generated method stub
					m = n = 15;
					cm = 30;
					b = cm;
					back2.setLayoutY(870);
					time.setLayoutX(450);
					time.setLayoutY(880);
					topmenu.setLayoutY(870);
					back2.setLayoutX(644);
					
					RbEasy.setSelected(false);
					Custom.setSelected(false);

				}
			});
			RbEasy.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					// TODO Auto-generated method stub
					
					m = n = 10;
					cm = 10;
					b = cm;
					RbHard.setSelected(false);
					Custom.setSelected(false);
					time.setLayoutX(310);
					time.setLayoutY(625);
					topmenu.setLayoutX(50);
					topmenu.setLayoutY(625);
					back2.setLayoutX(385);
					back2.setLayoutY(625);
				}
			});
			
			Custom.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					// TODO Auto-generated method stub
					topmenu.setLayoutX(50);
					topmenu.setLayoutY(625);
					back2.setLayoutX(385);
					back2.setLayoutY(625);
					time.setLayoutX(310);
					time.setLayoutY(635);
					RbHard.setSelected(false);
					RbEasy.setSelected(false);

				}
			});

			Start.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					// TODO Auto-generated method stub
					boolean p1, p2;
					int n1,n2;
					replayItem.setDisable(true);
					p1 = player1.getText().isEmpty();
					p2 = player2.getText().isEmpty();

					if ((p1 && player2.isDisable()) || ((p1 || (!player2.isDisable() && p2)))
							|| (!RbEasy.isSelected() && !RbHard.isSelected() && !Custom.isSelected())) {
						Pb.setManaged(true);
					} else {
						
					
					
						
						if (!Custom.isSelected()) {
							boolsave=false;
							game = new GuiGame();
							score=new Score();
							IntGrid();
							numbermine.setText(String.valueOf(b));
						
							primaryStage.setHeight((m*60+150));
							primaryStage.setWidth((n*60+50));
							
							back2.setLayoutX((n*55)-180);
							back2.setLayoutY(m*60);
							topmenu.setLayoutY(m*60);
							time.setLayoutX((n*55)/2);
							time.setLayoutY(m*60);
							if (num == NumberPlayer.TwoPlayer) {

								game.setplayer(player1.getText());
								game.setplayer(player2.getText());
								game.GetPlayers().get(0).SetId("player1");
								game.GetPlayers().get(1).SetId("player2");
								game.intGame(m, n, cm, NumberPlayer.TwoPlayer);
								th = new thread();
								th.start();
								th.Set_Game(game);

							} else if (num == NumberPlayer.ComputerPlayer) {
								game.setplayer(player1.getText());
								game.SetPlayerComputer();
								game.GetPlayers().get(0).SetId("player1");
								game.GetPlayers().get(1).SetId("player2");
								game.intGame(m, n, cm, NumberPlayer.TwoPlayer);
								th = new thread();
								th.start();
								th.Set_Game(game);
							}

							else {

								game.setplayer(player1.getText());
								game.GetPlayers().get(0).SetId("player2");
								game.intGame(m, n, cm, NumberPlayer.OnePlayer);

							}
							lab6.setText(game.getplayer().getname());
							Shieldnum.setText(String.valueOf(game.getplayer().GetNumberShield()));

							ActionOfButton(primaryStage, scene3);

							primaryStage.setScene(scene2);
						} else {
							primaryStage.setScene(scene4);
						}

					}
				}

			});
			no.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					// TODO Auto-generated method stub
					setdisable();

					printmines();
					LoadScore();
					score.Setplayer(game.getplayer());
					score.SetGameName("null");
				
					if(scoresave.search(score))
						{
						//scoresave.AddToList(score);
						SaveScore();
						}
					primaryStage.setWidth(scene2.getWidth()+10);
					primaryStage.setHeight(scene2.getHeight()+50);

					primaryStage.setScene(scene2);
					
				}
			});
			yes.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					// TODO Auto-generated method stub
					b--;
					numbermine.setText(String.valueOf(b));
					game.getplayer().setscore(-250);
					SetEffects();
					primaryStage.setWidth(scene2.getWidth()+10);
					primaryStage.setHeight(scene2.getHeight()+50);
					primaryStage.setScene(scene2);
					
				}
			});
			back2.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					// TODO Auto-generated method stub
					root2.getChildren().clear();
					player1.clear();
					player2.clear();
					RbHard.setSelected(false);
					RbEasy.setSelected(false);
					Custom.setSelected(false);
					lab7.setText(null);
					lab6.setText(null);
					gameover.setText(null);
					try {
						th.SetEnd();
				
					} catch (Exception e) {

					}
					try {
						k.set_end();
						} catch (Exception e) {

						}
					root5.getChildren().clear();
					root5.getChildren().addAll(lab5, gameover);
	
					root2.getChildren().addAll(back2, topmenu, lab6, numbermine, time,Gamemenu,heartv,labscore,Shieldnum);
					
					boolsave=true;
					primaryStage.setHeight(650);
					primaryStage.setWidth(650);
					primaryStage.setScene(scene1);
				}
			});
			topmenu.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					// TODO Auto-generated method stub
					primaryStage.setHeight(650);
					primaryStage.setWidth(650);
					player1.clear();
					player2.clear();
					RbHard.setSelected(false);
					RbEasy.setSelected(false);
					Custom.setSelected(false);
					gameover.setText(null);
					lab6.setText(null);
					Shieldnum.setText(null);
					lab7.setText(null);
					try {
						th.SetEnd();
					
					} catch (Exception e) {

					}
					try {
					k.set_end();
					} catch (Exception e) {

					}
				
					root2.getChildren().clear();
					root5.getChildren().clear();
					root5.getChildren().addAll(gameover, lab5);
					root2.getChildren().addAll(back2, topmenu, lab6, numbermine, time,Gamemenu,heartv,labscore,Shieldnum);
				
				
					boolsave=true;
					primaryStage.setScene(scene);
				}
			});

			Backcustom.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					// TODO Auto-generated method stub
					primaryStage.setHeight(650);
					primaryStage.setWidth(625);
					primaryStage.setScene(scene1);
					Column.clear();
					Rows.clear();
					Mines.clear();

				}
			});

			Startcustom.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					try {
						replayItem.setDisable(true);
						n = Integer.parseInt(Column.getText().toString());
						m = Integer.parseInt(Rows.getText().toString());
						cm = Integer.parseInt(Mines.getText().toString());
						sh = Integer.parseInt(Shields.getText().toString());
					
							
						
						int s = (m * n) / 4, s1 = (m * n) / 6;
						if (m > 15 || n > 15 || m < 5 || n < 5 || cm > s || cm < s1 || sh < 0 || sh > cm / 4) {
							lab7.setText("There is Error Number");
						} else {
							boolsave=false;
							score=new Score();
							game = new GuiGame();
							IntGrid();
							
						/*	primaryStage.setHeight(m*70+150);
							primaryStage.setWidth(n*70+150);
							
							back2.setLayoutX(n*50);
							time.setLayoutX((n*40)+25);
							time.setLayoutY((m*55)+100);
							topmenu.setLayoutY((m*55)+100);
							back2.setLayoutY((m*55)+100);
							*/
							primaryStage.setHeight((m*60+175));
							primaryStage.setWidth((n*60+100));
							
							back2.setLayoutX((n*55));
							back2.setLayoutY(m*60+20);
							topmenu.setLayoutY(m*60+20);
							time.setLayoutX((n*55)/2);
							time.setLayoutY(m*60);
							
							numbermine.setText(String.valueOf(b));
							if (num == NumberPlayer.TwoPlayer) {

								game.setplayer(player1.getText());
								game.setplayer(player2.getText());
								game.GetPlayers().get(0).SetId("player1");
								game.GetPlayers().get(1).SetId("player2");
								game.intGame(m, n, cm, NumberPlayer.TwoPlayer);
								b = cm;
								game.GetPlayers().get(0).SetNumberShield(sh);
								game.GetPlayers().get(1).SetNumberShield(sh);
								for (int g = 0; g < sh; g++) {
									game.GetPlayers().get(0).AddShieldtoList(-2);
									game.GetPlayers().get(1).AddShieldtoList(-2);
								}
								th = new thread();
								th.start();
								th.Set_Game(game);
							} else if (num == NumberPlayer.ComputerPlayer) {
								player2.setText("computer");
								game.setplayer(player1.getText());
								game.SetPlayerComputer();
								game.GetPlayers().get(0).SetId("player1");
								game.GetPlayers().get(1).SetId("player2");
								game.intGame(m, n, cm, NumberPlayer.TwoPlayer);
								game.GetPlayers().get(0).SetNumberShield(sh);
								game.GetPlayers().get(1).SetNumberShield(sh);
								for (int g = 0; g < sh; g++) {
									game.GetPlayers().get(0).AddShieldtoList(-2);
									game.GetPlayers().get(1).AddShieldtoList(-2);
								}
								b = cm;
								th = new thread();
								th.start();
								th.Set_Game(game);
							}

							else {

								game.setplayer(player1.getText());
								game.GetPlayers().get(0).SetId("player2");
								game.getplayer().SetNumberShield(sh);
								for (int g = 0; g < sh; g++) {
									game.getplayer().AddShieldtoList(-2);
								}
								game.intGame(m, n, cm, NumberPlayer.OnePlayer);
								b = cm;
								
							}
							lab6.setText(game.getplayer().getname()); 
							Shieldnum.setText(String.valueOf(game.getplayer().GetNumberShield()));

							ActionOfButton(primaryStage, scene3);
							primaryStage.setScene(scene2);
						}

					} catch (Exception e) {
						lab7.setText("There is Error Number");
					}
					Column.clear();
					Rows.clear();
					Mines.clear();
					Shields.clear();

				}
			});
			saveItem.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					// TODO Auto-generated method stub
					getloadinfo.show();
				}

			});
			replayItem.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent arg0) {
					// TODO Auto-generated method stub
					rep=true;
					getloadinfo.show();
				}
			});
			Load.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					// TODO Auto-generated method stub
					savepath="savinggames";
					LoadGame();
					table=new TableView<>();
					table.setLayoutX(170);
				    table.setLayoutY(30);
					table.setItems(GetGames());
					table.getColumns().addAll(name);
					rep=false;
					root6.getChildren().addAll(table,Go,back3,Delet);
					
					//listsave = new ListView<String>(GamesName);
					/*root6.getChildren().addAll(listsave);
					primaryStage.setScene(scene6);
					MultipleSelectionModel<String> selectionModel = listsave.getSelectionModel();
					selectionModel.selectedItemProperty().addListener(new ChangeListener<String>() {

						@Override
						public void changed(ObservableValue<? extends String> Change, String arg1, String arg2) {
							// TODO Auto-generated method stub
							int i = 0;
							while (!arg2.equals(game.GetName())) {
								game = Games.get(i++);
							}*/
					primaryStage.setScene(scene6);
							/*b = game.GetAuxCm2();
							m = game.GetGameGrid().getM();
							n = game.GetGameGrid().getN();
							num = game.GetNumberPlayer();
							if (num != NumberPlayer.OnePlayer) {
								th = new thread();
								th.start();
							}
							game.SaveSquareStatus();
							IntGrid();
							primaryStage.setScene(scene2);
							SetEffects();
							ActionOfButton(primaryStage, scene3);*/
					
					/*
					 * IntGrid(); primaryStage.setScene(scene2); SetEffects();
					 * ActionOfButton(primaryStage, scene3);
					 */

				}
			});
			back3.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent arg0) {
					primaryStage.setScene(scene);
					table.getItems().clear();
					root6.getChildren().clear();
		     
					
				}
			});
			back4.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent arg0) {
					primaryStage.setScene(scene);
					table1.getItems().clear();
					root8.getChildren().clear();
					
					
				}
			});
			Delet.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent arg0) {
					ObservableList<Game> gameselected,allgames;
					allgames=table.getItems();
					gameselected=table.getSelectionModel().getSelectedItems();
					gameselected.forEach(allgames::remove);
					gamestosave=new GamesToSave();
					for (Game game : allgames) {
						gamestosave.AddToList(game);
					}
					try {
						Out = new ObjectOutputStream(new FileOutputStream(savepath));
						Out.writeObject(gamestosave);
						Out.close();
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
			Go.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent arg0) {
					// TODO Auto-generated method stub
					int lettercount=0,n2;
					ObservableList<Game> gameselected;
					gameselected=table.getSelectionModel().getSelectedItems();
					game=(GuiGame) gameselected.get(0);
					
					b = game.GetAuxCm2();
					m = game.GetGameGrid().getM();
					n = game.GetGameGrid().getN();
					num = game.GetNumberPlayer();
		
					primaryStage.setHeight((m*60+175));
					primaryStage.setWidth((n*60+75));
					
					back2.setLayoutX((n*55)-180);
					back2.setLayoutY(m*60);
					topmenu.setLayoutY(m*60);
					time.setLayoutX(((n*55)/2)+25);
					time.setLayoutY(m*60);
					/*if (num != NumberPlayer.OnePlayer) {
						th = new thread();
						th.start();
					}*/
					//game.SaveSquareStatus();
					IntGrid();
					lab6.setText(game.getplayer().getname());
					Shieldnum.setText(String.valueOf(game.getplayer().GetNumberShield()));

					primaryStage.setScene(scene2);
	                	if(rep)
	                		{
	                		Replay();
	                		rep=false;
	                		}
	                	else
	                	{
	                		numbermine.setText(String.valueOf(b));
	                		game.SaveSquareStatus();
	                		SetEffects();
	    					ActionOfButton(primaryStage, scene3);
	                	}
					//SetEffects();
					//ActionOfButton(primaryStage, scene3);
					root6.getChildren().clear();
				}
			});
			submit.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent arg0) {
					// TODO Auto-generated method stub
					if(!getGameName.getText().isEmpty())
					{
						if(rep)
						{
							savepath="savingreplays";
							rep=false;
							score.SetGameName(getGameName.getText());
							score1.SetGameName(getGameName.getText());
							if(!boolsave)
							SaveScore();
						}
						else
						{
							savepath="savinggames";
						}
					game.SetName(getGameName.getText());
					game.SetAuxCm2(b);
					SaveGame();
					getloadinfo.close();
					getGameName.clear();
					}
				}
			});
			Replay.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent arg0) {
					// TODO Auto-generated method stub
					savepath="savingreplays";
					LoadGame();
					
				    
					table=new TableView<>();
					table.setLayoutX(170);
				    table.setLayoutY(30);
				    name.setText("Saved Replays");
				    
					table.setItems(GetGames());
					table.getColumns().addAll(name);
					primaryStage.setScene(scene6);
					root6.getChildren().addAll(table,Go,back3,Delet);
					rep=true;
					
					/*ObservableList<Game> gameselected;
					gameselected=table.getSelectionModel().getSelectedItems();
					game=(GuiGame) gameselected.get(0);
					b = game.GetAuxCm2();
					m = game.GetGameGrid().getM();
					n = game.GetGameGrid().getN();
					num = game.GetNumberPlayer();
					/*if (num != NumberPlayer.OnePlayer) {
						th = new thread();
						th.start();
					}*/
					//game.SaveSquareStatus();
			
					//SetEffects();
					//ActionOfButton(primaryStage, scene3);
					//root6.getChildren().clear();
				
				}
			});
			scoreboard.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent arg0) {
					// TODO Auto-generated method stub
					LoadScore();
					table1=new TableView<>();
					table1.setItems(GetScore());
					table1.getColumns().addAll(playername,gamename,sco);
					primaryStage.setScene(scene8);
					root8.getChildren().clear();
					 root8.getChildren().addAll(table1,showreplay,back4);
						
						/*if(!score2.getGamename().equals("Game"))
							showreplay.setDisable(false);*/
				}
			});
			animationv.setOnMouseClicked(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent arg0) {
					// TODO Auto-generated method stub
					developer.showAndWait();
					Scale.pause();
				}
			});
			primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {

				@Override
				public void handle(WindowEvent arg0) {
					// TODO Auto-generated method stub
					try {
						th.SetEnd();
					} catch (Exception e) {
						
					}

					try {
						k.set_end();
						} catch (Exception e) {

						}
				
					boolsave=true;
				}
			});
			showreplay.setDisable(false);
			showreplay.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent arg0) {
					// TODO Auto-generated method stub
				
					score2=table1.getSelectionModel().getSelectedItem();
					if(!score2.getGamename().equals("Game"))
					{
						savepath="savingreplays";
						LoadGame();
						
						if(search(score2.getGamename()))
						{
							IntGrid();
							
							int h= game.GetGameGrid().getM();
							int w = game.GetGameGrid().getN();
							primaryStage.setHeight((h*70));
							primaryStage.setWidth((w*61));
							
							back2.setLayoutX((w*55)-180);
							back2.setLayoutY(h*60);
							topmenu.setLayoutY(h*60);
							time.setLayoutX((w*55)/2);
							time.setLayoutY(h*60);
							
						lab6.setText(game.getplayer().getname());
						Shieldnum.setText(String.valueOf(game.getplayer().GetNumberShield()));

						numbermine.setText(String.valueOf(b));
						primaryStage.setScene(scene2);

						Replay();
                		rep=false;
						}
					}
				}
			});
			MouseEnteredExited(exit);
			MouseEnteredExited(single);
			MouseEnteredExited(multi);
			MouseEnteredExited(Pc);
			MouseEnteredExited(back);
			MouseEnteredExited(Start);
			MouseEnteredExited(topmenu);
			MouseEnteredExited(back2);
			MouseEnteredExited(Startcustom);
			MouseEnteredExited(Backcustom);
			MouseEnteredExited(no);
			MouseEnteredExited(yes);
			MouseEnteredExited(Load);
			MouseEnteredExited(Replay);
			MouseEnteredExited(scoreboard);
			exit.setOnAction(ActionEvent -> Platform.exit());
	

			exit.setWrapText(true);
			
			primaryStage.setTitle("Mine Sweeper");
			root.getChildren().addAll(single, multi, exit, Pc, Load,Replay,scoreboard,animationv);
			root1.getChildren().addAll(Start, back, RbEasy, RbHard, player1, player2, lab1, lab2, lab3, Pb, Custom);
			root2.getChildren().addAll(back2, topmenu, lab6, numbermine, time, Gamemenu,heartv,labscore,Shieldnum);
			root3.getChildren().addAll(yes, no, lab4, chance);

			root4.getChildren().addAll(Startcustom, Backcustom, Rows, RowLab, Column, ColLab, Mines, MinLab, lab7,
			Shields, SheldLab, text);
			root5.getChildren().addAll(gameover, lab5);
            root7.getChildren().addAll(submit,getGameName,entername);
			scene.getStylesheets().add(Game1.class.getResource("application1.css").toExternalForm());
			scene1.getStylesheets().add(Game1.class.getResource("application1.css").toExternalForm());
			scene2.getStylesheets().add(Game1.class.getResource("application1.css").toExternalForm());
			scene3.getStylesheets().add(Game1.class.getResource("application1.css").toExternalForm());
			scene4.getStylesheets().add(Game1.class.getResource("application1.css").toExternalForm());
			scene5.getStylesheets().add(Game1.class.getResource("application1.css").toExternalForm());
			scene6.getStylesheets().add(Game1.class.getResource("application1.css").toExternalForm());
			scene8.getStylesheets().add(Game1.class.getResource("application1.css").toExternalForm());
			developer.getDialogPane().getScene().getStylesheets().add(Game1.class.getResource("application1.css").toExternalForm());
			primaryStage.setScene(scene);
			FinalStage.setScene(scene5);
			primaryStage.show();
			getloadinfo.setScene(scene7);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {

		launch(args);

		/*
		 * ConsoleGame Cgame = new ConsoleGame(); Scanner reader=new Scanner(System.in);
		 * Scanner reader1=new Scanner(System.in); //while(true) {
		 * System.out.println("1-New Consol Game\n2-New GUI Game"); int
		 * choose=reader.nextInt(); switch(choose) { case 1: {
		 * System.out.println("Enter Player Name .\n"); String ss=reader1.nextLine();
		 * Cgame.setplayer(ss); Cgame.intGame(10, 10, 20, NumberPlayer.OnePlayer);
		 * Cgame.printh(); Cgame.PrintGrid(); while(true) { try {
		 * Cgame.AcceptMove(0,0,MoveType.Mark); GameState state=Cgame.Applyplayermove();
		 * if(state==GameState.EndWithLose) {
		 * System.out.println("You Lose And Your Score is :"+Cgame.getplayer().getscore(
		 * )); Cgame.PrintLose(); break; } else if(state==GameState.EndWithWin) {
		 * System.out.println("You Win And Your Score is :"+Cgame.getplayer().getscore()
		 * ); Cgame.PrintGrid(); break; } Cgame.PrintGrid(); }catch(Exception e) {
		 * System.out.println("Try Again "); }
		 * 
		 * } break; } case 2: {
		 * 
		 * break; } default:
		 * 
		 * System.out.println("ERROR");
		 * 
		 * } //break; }
		 */
	}

		

}
