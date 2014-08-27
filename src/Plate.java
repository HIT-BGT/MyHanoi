import java.awt.*;

public class Plate extends Button{
	int number;		//当前Plate对象编号
	boolean hasPlateUpstairs = false;	//当前盘上是否有盘
	//构造方法：初始化一个Place对象
	public Plate(int number, Tower tower)
	{
		this.number = number;
		setBackground(Color.orange);
		addMouseMotionListener(tower);
		addMouseListener(tower);
	}
	public boolean getHasPlateUpstairs(){
		return hasPlateUpstairs;
	}
	public void setHasPlateUpstairs(boolean b){
		hasPlateUpstairs = b;
	}
	public int getNumber(){
		return number;
	}
}