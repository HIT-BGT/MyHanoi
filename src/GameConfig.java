
public class GameConfig {
	int totalPlate;	//��������
	int plateWidth;	//����̵Ŀ��
	int plateHeight;	//�̵ĸ߶�
	int mainFrameHeight;	//��Ϸ�����ڸ߶�
	int mainFrameWidth;	//��Ϸ�����ڿ��
	int gamePanelWidth;	//��Ϸ�����
	int gamePanelHeight;	//��Ϸ���߶�
	
	public int getPlateWidth(){
		return plateWidth;	//��ȡ������ӵĿ��
	}
	public int getPlateHeight(){
		return plateHeight;	//������ӵĸ߶�
	}
	public int getGamePanelHeight(){
		return gamePanelHeight;	//�����Ϸ���߶�
	}
	public int getGamePanelWidth(){
		return gamePanelWidth;	//�����Ϸ�����
	}
	public int getMainFrameHeight(){
		return mainFrameHeight; //�����Ϸ�����ڿ��
	}
	public int getMainFrameWidth(){
		return mainFrameWidth;  //�����Ϸ�������
	}
	public int getTotalPlateNumber(){
		return totalPlate;	//���������
	}
	public void setPlateWidth(int plateWidth){
		this.plateWidth = plateWidth;	//��������̿��
	}
	public void setPlateHeight(int plateHeight){
		this.plateHeight = plateHeight;	//�����̸߶�
	}
	public void setGamePanelWidth(){
		this.gamePanelWidth = 8 * this.plateWidth;	//������Ϸ�������
	}
	public void setGamePanelHeight(){	//������Ϸ�����߶�
		this.gamePanelHeight = this.totalPlate * this.plateHeight + 200;
	}
	public void setMainFrameWidth(){
		this.mainFrameWidth = this.gamePanelWidth;	//������Ϸ�����ڿ��
	}
	public void setMainFrameHeight(){
		this.mainFrameHeight = this.gamePanelHeight + 50;	//������Ϸ�����ڸ߶�
	}
	public void setTotalPlate(int totalPlate){
		this.totalPlate = totalPlate;	//��������
	}
}
