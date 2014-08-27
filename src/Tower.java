import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;


public class Tower extends JPanel implements MouseListener, MouseMotionListener{
	TowerPoint point[];	//����������ɵ�����
	int x, y;			//��¼��ǰ���λ��
	boolean move;		//���������ƶ��ı���
	Plate plateArray[];		//������ɵ�����
	int startX, startY;	//��¼���ӳ�ʼ����
	int startI;			//��¼���ӳ�ʼ����
	int totalPlate;		
	int width, height;	//����̵Ŀ�Ⱥ͸߶�
	char towerName[];	//��������
	GameConfig config;
	//�����췽��
	public Tower(GameConfig config, char[] towerName){
		this.config = config;
		this.totalPlate = config.getTotalPlateNumber();
		this.towerName = towerName;
		this.width = config.getPlateWidth();
		this.height = config.getPlateHeight();
		
		this.setLayout(null);
		this.setBackground(Color.cyan);
		addMouseListener(this);
		
		//��������
		plateArray = new Plate[this.totalPlate];
		point = new TowerPoint[3 * this.totalPlate];
		
		//A�����������������
		int plateHeight = 20;
		for (int i = 0; i<this.totalPlate; i++)
		{
			point[i] = new TowerPoint(2 * width, 100 + plateHeight, false);
			plateHeight += height;
		}
		//B�����������������
		plateHeight = 20;
		for (int i = this.totalPlate; i < 2*this.totalPlate; i++)
		{
			point[i] = new TowerPoint(4*width, 100 + plateHeight, false);
			plateHeight += height;
		}
		//C�����������������
		plateHeight = 20;
		for (int i = 2*this.totalPlate; i < 3*this.totalPlate; i++)
		{
			point[i] = new TowerPoint(6*width, 100 + plateHeight, false);
			plateHeight += height;
		}
		//�������Ӷ�������
		int tempWidth = width;
		int sub = (int) (tempWidth / this.totalPlate);
		for (int i = this.totalPlate - 1; i>=0; i--)
		{
			plateArray[i] = new Plate(i, this);
			plateArray[i].setSize(tempWidth, height);
			tempWidth -= sub;
		}
		for (int i = 0; i < this.totalPlate; i++)
		{
			point[i].addPlate(plateArray[i], this);
			if (i > 0)
			{
				point[i].setHasPlate(true);
			}
		}
	}
	//�˺������ڽ�����������������ֱ��
	public void paint(Graphics g)	
	{
		super.paint(g);
		//����A��
		g.drawLine(point[0].getX(), point[0].getY(), point[this.totalPlate - 1].getX(), 
				point[this.totalPlate - 1].getY() + height/2);
		//����B��
		g.drawLine( point[this.totalPlate].getX(), point[this.totalPlate].getY(), 
				 point[2*this.totalPlate - 1].getX(), point[2*this.totalPlate - 1].getY()+ height/2);
		//����C��
		g.drawLine(point[2*this.totalPlate].getX(), point[2*this.totalPlate].getY(), 
				 point[3*this.totalPlate - 1].getX(), point[3*this.totalPlate - 1].getY()+ height/2);
		//�����¶˻�׼��
		int leftx = point[this.totalPlate - 1].getX() - width;
		int lefty = point[this.totalPlate - 1].getY() + height/2;
		int w = (point[3*this.totalPlate -1].getX() + width) - leftx;
		int h = height / 2;
		g.setColor(Color.black);
		g.fillRect(leftx, lefty, w, h);
		
		
		//������������
		g.drawString(" " + towerName[0] + "��", point[this.totalPlate - 1].getX()-10,
				point[this.totalPlate - 1].getY() + 30);
		g.drawString(" " + towerName[1] + "��", point[2 * this.totalPlate - 1].getX()-10,
				point[this.totalPlate - 1].getY() + 30);
		g.drawString(" " + towerName[2] + "��", point[3 * this.totalPlate - 1].getX()-10,
				point[this.totalPlate - 1].getY() + 30);
		
		//������Ϸ������ʾ������㡢�յ�
		g.setColor(Color.red);
		g.drawString("��ȫ�����Ӵ�" + towerName[0] + "�����˵�" + towerName[1] + "����"
				+ towerName[2] + "��", point[2 * this.totalPlate - 1].getX() - 80, point[this.totalPlate - 1]
				.getY() + 80);
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		Plate plate = null;
		if (e.getSource() instanceof Plate)
		{
			plate = (Plate) (e.getSource());
			move = true;
			// ������϶��¼�ת�Ƶ����������������Ϸ�������϶��¼�
			e = SwingUtilities.convertMouseEvent(plate, e, this);
		}
		if (e.getSource() == this){
			if (move && plate != null)
			{
				x = e.getX();
				y = e.getY();
				
				if (!plate.getHasPlateUpstairs())
				{
					plate.setLocation(x - plate.getWidth()/2, y - plate.getHeight()/2);
				}
			}
		}
		this.repaint();
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	//ʵ��MouseLitsener�еĳ��󷽷�
	@Override
	public void mousePressed(MouseEvent e) {
		Plate plate;
		Rectangle rect;
		if (e.getSource() == this) {
			move = false;
		}
		if (move == false) {
			if (e.getSource() instanceof Plate) {
				plate = (Plate) e.getSource();
				startX = plate.getBounds().x;
				startY = plate.getBounds().y;

				rect = plate.getBounds();
				for (int i = 0; i < 3 * this.totalPlate; i++) {
					int x = point[i].getX();
					int y = point[i].getY();

					if (rect.contains(x, y)) {
						startI = i;
						break;
					}
				}
			}
		}
		this.repaint();
	}
	

	@Override
	public void mouseReleased(MouseEvent e) {
		Plate plate = null;
		move = false;
		Rectangle rect = null;
		if (e.getSource() instanceof Plate)
		{
			plate = (Plate) e.getSource();	//�������Ӷ���
			rect = plate.getBounds();	//��ʼ�����ƶ���ͼ��
			// ������϶��¼�ת�Ƶ����������������Ϸ�������϶��¼�
			e = SwingUtilities.convertMouseEvent(plate, e, this);
		}
		if (e.getSource() == this)
		{
			boolean containTowerPoint = false;
			int x = 0, y = 0;
			int endI = 0;
			
			if (plate != null)
			{
				// ��������Ƿ��ƶ�����һ��������
				for (int i = 0; i < 3 * totalPlate; i++) {
					x = point[i].getX();
					y = point[i].getY();

					// ��鵱ǰ��һ���ܹ�ͼ�����Ƿ����ĳ�����㣬�����򽫴���������Ϊ�ƶ����յ�
					if (rect.contains(x, y)) {
						containTowerPoint = true;
						endI = i;
						break;
					}
				}
			}
		
			if (plate != null && containTowerPoint)
			{
				if (point[endI].getHasPlate() == true)
				{
					//���յ����������ӣ� �����ӷŻ�ԭ��
					plate.setLocation(startX, startY);
				}
				else
				{
					//���Ŀ�ĵ��Ǹ�����
					if (endI ==  this.totalPlate - 1 || endI == 2*this.totalPlate - 1
							|| endI == 3*this.totalPlate - 1)
					{
						point[endI].addPlate(plate, this);
						//����������㲻������
						if (startI != this.totalPlate - 1 && startI != 2*this.totalPlate - 1
								&& startI != 3*this.totalPlate - 1)
						{
							point[startI+1].getPlate().setHasPlateUpstairs(false);
							point[startI].setHasPlate(false);
						}
						//�����������������
						else
						{
							point[startI].setHasPlate(false);
						}
					}
					//���Ŀ�ĵز�������
					else
					{
						if (point[endI + 1].getHasPlate() == true)
						{
							Plate tempPlate = point[endI + 1].getPlate();
							if (tempPlate.getNumber() > plate.getNumber())
							{
								point[endI].addPlate(plate, this);
								
								//�����㲻������
								if (startI != this.totalPlate - 1 && startI != 2*this.totalPlate - 1
										&& startI != 3*this.totalPlate - 1)
								{
									point[startI].setHasPlate(false);
									point[startI + 1].getPlate().setHasPlateUpstairs(false);
									tempPlate.setHasPlateUpstairs(true);
								}
								//������������
								else
								{
									point[startI].setHasPlate(false);
									tempPlate.setHasPlateUpstairs(true);
								}
								
							}
							//����±ߵ�����С����ô�����ӷŻ�ԭ��
							else
							{
								plate.setLocation(startX, startY);
							}
						}
						//���Ŀ���������������û�����ӣ������ӷŻ�ԭ��
						else
						{
							plate.setLocation(startX, startY);
						}
					}
				}
			}
			//���û�зŵ�����λ��
			if (plate != null && !containTowerPoint)
			{
				plate.setLocation(startX, startY);
			}
			if (plate != null && (point[this.totalPlate].getHasPlate() || point[2*this.totalPlate].getHasPlate()))
			{
				JOptionPane.showMessageDialog(this,"��ϲ�㣬����˴˾���Ϸ��");
			}
		}
		this.repaint();
	}
	public void autoPerform(int plateNumber, char A, char B, char C){
		if (plateNumber == 1)
		{
			Hanoi.area.append(A+" ����"+C+"��\n");
			Plate plate = this.getTopDisk(A);
			if (plate!=null)
			{
				point[this.getTopLocationPlus(C)].addPlate(plate, this);
				point[this.getTopLocation(A)].setHasPlate(false);
				try{
					Thread.sleep(1000);
					
				}catch(Exception e)
				{
				}
			}
		}
		else{
			autoPerform(plateNumber-1,A,C,B);
			Hanoi.area.append(A+" ����"+C+"��\n");
			Plate plate = this.getTopDisk(A);
			if (plate!=null)
			{
				point[this.getTopLocationPlus(C)].addPlate(plate, this);
				point[this.getTopLocation(A)].setHasPlate(false);
				try{
					Thread.sleep(1000);
					
				}catch(Exception e)
				{
				}
			}
			autoPerform(plateNumber-1,B,A,C);
		}
		
	}
	public Plate getTopDisk(char towerNm)
	{
		Plate gotPlate = null;
		if (towerNm == towerName[0])
		{
			for (int i=0;i<totalPlate;i++)
			{
				if (point[i].getHasPlate())
				{
					gotPlate = point[i].getPlate();
					break;
				}
			}
		}
		else if (towerNm == towerName[1])
		{
			for (int i=totalPlate;i<2*totalPlate;i++)
			{
				if (point[i].getHasPlate())
				{
					gotPlate = point[i].getPlate();
					break;
				}
			}
		}
		else if (towerNm == towerName[2])
		{
			for (int i=2*totalPlate;i<3*totalPlate;i++)
			{
				if (point[i].getHasPlate())
				{
					gotPlate = point[i].getPlate();
					break;
				}
			}
		}
		return gotPlate;
	}
	public int getTopLocation(char towerNm)
	{
		int gotLocation=0;
		if (towerNm == towerName[0])
		{
			for (int i=0;i<totalPlate;i++)
			{
				if (point[i].getHasPlate())
				{
					gotLocation = i;
					break;
				}
			}
		}
		else if (towerNm == towerName[1])
		{
			for (int i=totalPlate;i<2*totalPlate;i++)
			{
				if (point[i].getHasPlate())
				{
					gotLocation = i;
					break;
				}
			}
		}
		else if (towerNm == towerName[2])
		{
			for (int i=2*totalPlate;i<3*totalPlate;i++)
			{
				if (point[i].getHasPlate())
				{
					gotLocation = i;
					break;
				}
			}
		}
		return gotLocation;
	}
	public int getTopLocationPlus(char towerNm)
	{
		int i=0;
		int gotLocation=0;
		if (towerNm == towerName[0])
		{
			for (i=0;i<totalPlate;i++)
			{
				if (point[i].getHasPlate())
				{
					gotLocation = Math.max(i-1, 0);
					break;
				}
			}
			if (i==totalPlate) gotLocation = totalPlate-1;
		}
		else if (towerNm == towerName[1])
		{
			for (i=totalPlate;i<2*totalPlate;i++)
			{
				if (point[i].getHasPlate())
				{
					gotLocation = Math.max(i-1, 0);
					break;
				}
			}
			if (i==2*totalPlate) gotLocation = 2*totalPlate-1;
		}
		else if (towerNm == towerName[2])
		{
			for (i=2*totalPlate;i<3*totalPlate;i++)
			{
				if (point[i].getHasPlate())
				{
					gotLocation = Math.max(i-1, 0);
					break;
				}
			}
			if (i==3*totalPlate) gotLocation = 3*totalPlate-1;
		}
		return gotLocation;
	}
}
