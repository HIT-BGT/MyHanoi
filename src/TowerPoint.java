/*
 * 塔点类：
 * 塔点为组成塔的元素
 */
public class TowerPoint {
	int x,y;	//当前塔点坐标
	boolean hasPlate;	//当前塔点位置上是否有盘
	Plate plate;	//当前塔点上盘对象
	Tower tower;	//当前塔点所在塔对象
	//塔点构造方法
	public TowerPoint(int x, int y, boolean hasPlate){
		this.x = x;
		this.y = y;
		this.hasPlate = hasPlate;
	}
	public boolean getHasPlate(){
		return this.hasPlate;	//得知当前塔点上是否有盘
	}
	public void setHasPlate(boolean hasPlate){
		this.hasPlate = hasPlate;	//设置当前塔点上是否有盘
	}
	public int getX(){
		return this.x;		//获得当前塔点的横坐标
	}
	public int getY(){
		return this.y;		//获得当前塔点的纵坐标
	}
	public Plate getPlate(){
		return this.plate;  //获得当前塔点上的盘对象
	}
	//向当前塔点上增加盘子的方法
	public void addPlate(Plate plate, Tower tower)
	{
		this.plate = plate;
		this.tower = tower;
		tower.setLayout(null);
		tower.add(plate);
		int w = plate.getBounds().width, h = plate.getBounds().height;
		this.plate.setBounds(x - w/2, y - h/2, w, h);
		this.hasPlate = true;
		tower.validate();
	}
}
