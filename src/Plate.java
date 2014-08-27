import java.awt.*;

public class Plate extends Button{
	int number;		//��ǰPlate������
	boolean hasPlateUpstairs = false;	//��ǰ�����Ƿ�����
	//���췽������ʼ��һ��Place����
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