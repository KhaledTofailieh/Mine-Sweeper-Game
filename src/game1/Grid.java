/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class Grid implements Serializable{
	
	private Square[][] square;
	private int m, n, countmine,NumberShield;
	protected ArrayList<Square> Minesauxiliary;

	public Grid(int m, int n, int p) {
		Minesauxiliary = new ArrayList<Square>();
		this.m = m;
		this.n = n;
		this.countmine = p;
		this.NumberShield=this.countmine/4;
		square = new Square[m][n];

		for (int i = 0; i < m; i++) {
			square[i] = new Square[m];
			for (int j = 0; j < n; j++) {
				square[i][j] = new Square();
				square[i][j].setx_y(i, j);
			}
		}
	}

	public void intGrid() {
		int x, y;
		Random random = new Random();
		
		int cm = countmine;

		while (cm > 0) {
			x = random.nextInt(m);
			y = random.nextInt(n);
			if (square[x][y].IsMine() == false) {
				cm--;
				square[x][y].setmine();
				Minesauxiliary.add(Minesauxiliary.size(), square[x][y]);
				CounterMine(x, y);
			}
		}
		SetShield();
	}
    public void SetShield()
    {
    	int numsh=NumberShield,x,y;
    	Random random=new Random();
    	Random randType=new Random();
		while (numsh > 0) {
			x = random.nextInt(m);
			y = random.nextInt(n);
			if (square[x][y].IsMine() == false&&!square[x][y].IsShield())
			{
				int z=randType.nextInt(3);
				square[x][y].IntShield(z);
				numsh--;
				System.out.println("x  "+x+"y  "+y);
			}
		}
    	
    }
	public void CounterMine(int x, int y) {

		int transX[] = { -1, -1, 0, 1, 1, 1, 0, -1 };
		int transY[] = { 0, 1, 1, 1, 0, -1, -1, -1 };
		for (int i = 0; i < 8; i++) {
			int Ox = x + transX[i], Oy = y + transY[i];
			if (Ox >= 0 && Ox < this.m && Oy >= 0 && Oy < this.n) {
				if (!square[Ox][Oy].IsMine())
					square[Ox][Oy].setcountmine();
			}

		}
	}

	public void print() {
		SquareState state;
		for (int i = 0; i < m; i++) {
			System.out.println();
			for (int j = 0; j < n; j++) {
				state = square[i][j].getstate();
				if (state == SquareState.Closed)
					System.out.print("      *");
				else if (state == SquareState.Marked)
					System.out.print("      ?");
				else if (state == SquareState.OpenedNumber)
					System.out.print("      " + square[i][j].GetNumber());
				else
					System.out.print("      -");
			}
		}
		System.out.println();
	}

	// print mines in grid when lose on console
	public void PrintLose() {
		for (int i = 0; i < m; i++) {
			System.out.println();
			for (int j = 0; j < n; j++) {
				if (square[i][j].IsMine()) {
					System.out.print("      @");
				} else {
					System.out.print("      *");
				}
			}
		}
		System.out.println();
	}

	public int getM() {
		return this.m;
	}

	public int getN() {
		return this.n;
	}

	public SquareState GetState(int x, int y) {
		return square[x][y].getstate();
	}

	public void setstate(SquareState sta, int x, int y) {
		square[x][y].setstate(sta);
	}

	public Square getSquare(int x, int y) {
		return square[x][y];
	}

	public void printh() {
		for (int i = 0; i < m; i++) {
			System.out.println();
			for (int j = 0; j < n; j++) {

				System.out.print("      " + square[i][j].GetNumber());
			}
		}
		System.out.println();
	}

	public ArrayList<Square> GetMines() {
		return Minesauxiliary;
	}
	public int GetNumberShield()
	{
		return NumberShield;
	}
}
