package game1;

import java.io.Serializable;

public class Shield implements Serializable{
protected String Id;
Shield()
{
	Id="";
}
public void Set_Id(String Id)
{
	this.Id=Id;
}
public String Get_Id()
{
	return this.Id;
}
public int  GetFinalScore()
{
	return 1;
}
}

