
public class GameConfig {
	int totalPlate;	//盘子总数
	int plateWidth;	//最大盘的宽度
	int plateHeight;	//盘的高度
	int mainFrameHeight;	//游戏主窗口高度
	int mainFrameWidth;	//游戏主窗口宽度
	int gamePanelWidth;	//游戏面板宽度
	int gamePanelHeight;	//游戏面板高度
	
	public int getPlateWidth(){
		return plateWidth;	//获取最大盘子的宽度
	}
	public int getPlateHeight(){
		return plateHeight;	//获得盘子的高度
	}
	public int getGamePanelHeight(){
		return gamePanelHeight;	//获得游戏面板高度
	}
	public int getGamePanelWidth(){
		return gamePanelWidth;	//获得游戏面板宽度
	}
	public int getMainFrameHeight(){
		return mainFrameHeight; //获得游戏主窗口宽度
	}
	public int getMainFrameWidth(){
		return mainFrameWidth;  //获得游戏主面板宽度
	}
	public int getTotalPlateNumber(){
		return totalPlate;	//获得总盘数
	}
	public void setPlateWidth(int plateWidth){
		this.plateWidth = plateWidth;	//设置最大盘宽度
	}
	public void setPlateHeight(int plateHeight){
		this.plateHeight = plateHeight;	//设置盘高度
	}
	public void setGamePanelWidth(){
		this.gamePanelWidth = 8 * this.plateWidth;	//设置游戏主面板宽度
	}
	public void setGamePanelHeight(){	//设置游戏主面板高度
		this.gamePanelHeight = this.totalPlate * this.plateHeight + 200;
	}
	public void setMainFrameWidth(){
		this.mainFrameWidth = this.gamePanelWidth;	//设置游戏主窗口宽度
	}
	public void setMainFrameHeight(){
		this.mainFrameHeight = this.gamePanelHeight + 50;	//设置游戏主窗口高度
	}
	public void setTotalPlate(int totalPlate){
		this.totalPlate = totalPlate;	//设置盘数
	}
}
