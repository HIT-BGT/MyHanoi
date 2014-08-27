/*
 * �����ࣺ
 * ����Ϊ�������Ԫ��
 */
public class TowerPoint {
	int x,y;	//��ǰ��������
	boolean hasPlate;	//��ǰ����λ�����Ƿ�����
	Plate plate;	//��ǰ�������̶���
	Tower tower;	//��ǰ��������������
	//���㹹�췽��
	public TowerPoint(int x, int y, boolean hasPlate){
		this.x = x;
		this.y = y;
		this.hasPlate = hasPlate;
	}
	public boolean getHasPlate(){
		return this.hasPlate;	//��֪��ǰ�������Ƿ�����
	}
	public void setHasPlate(boolean hasPlate){
		this.hasPlate = hasPlate;	//���õ�ǰ�������Ƿ�����
	}
	public int getX(){
		return this.x;		//��õ�ǰ����ĺ�����
	}
	public int getY(){
		return this.y;		//��õ�ǰ�����������
	}
	public Plate getPlate(){
		return this.plate;  //��õ�ǰ�����ϵ��̶���
	}
	//��ǰ�������������ӵķ���
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
