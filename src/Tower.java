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
	TowerPoint point[];	//所有塔点组成的数组
	int x, y;			//记录当前鼠标位置
	boolean move;		//控制盘子移动的变量
	Plate plateArray[];		//盘子组成的数组
	int startX, startY;	//记录盘子初始坐标
	int startI;			//记录盘子初始塔点
	int totalPlate;		
	int width, height;	//最大盘的宽度和高度
	char towerName[];	//塔名数组
	GameConfig config;
	//塔构造方法
	public Tower(GameConfig config, char[] towerName){
		this.config = config;
		this.totalPlate = config.getTotalPlateNumber();
		this.towerName = towerName;
		this.width = config.getPlateWidth();
		this.height = config.getPlateHeight();
		
		this.setLayout(null);
		this.setBackground(Color.cyan);
		addMouseListener(this);
		
		//创建盘组
		plateArray = new Plate[this.totalPlate];
		point = new TowerPoint[3 * this.totalPlate];
		
		//A塔中塔中塔点的坐标
		int plateHeight = 20;
		for (int i = 0; i<this.totalPlate; i++)
		{
			point[i] = new TowerPoint(2 * width, 100 + plateHeight, false);
			plateHeight += height;
		}
		//B塔中塔中塔点的坐标
		plateHeight = 20;
		for (int i = this.totalPlate; i < 2*this.totalPlate; i++)
		{
			point[i] = new TowerPoint(4*width, 100 + plateHeight, false);
			plateHeight += height;
		}
		//C塔中塔中塔点的坐标
		plateHeight = 20;
		for (int i = 2*this.totalPlate; i < 3*this.totalPlate; i++)
		{
			point[i] = new TowerPoint(6*width, 100 + plateHeight, false);
			plateHeight += height;
		}
		//创建盘子对象数组
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
	//此函数用于将三个塔的塔点连成直线
	public void paint(Graphics g)	
	{
		super.paint(g);
		//画出A塔
		g.drawLine(point[0].getX(), point[0].getY(), point[this.totalPlate - 1].getX(), 
				point[this.totalPlate - 1].getY() + height/2);
		//画出B塔
		g.drawLine( point[this.totalPlate].getX(), point[this.totalPlate].getY(), 
				 point[2*this.totalPlate - 1].getX(), point[2*this.totalPlate - 1].getY()+ height/2);
		//画出C塔
		g.drawLine(point[2*this.totalPlate].getX(), point[2*this.totalPlate].getY(), 
				 point[3*this.totalPlate - 1].getX(), point[3*this.totalPlate - 1].getY()+ height/2);
		//画出下端基准线
		int leftx = point[this.totalPlate - 1].getX() - width;
		int lefty = point[this.totalPlate - 1].getY() + height/2;
		int w = (point[3*this.totalPlate -1].getX() + width) - leftx;
		int h = height / 2;
		g.setColor(Color.black);
		g.fillRect(leftx, lefty, w, h);
		
		
		//画出各个塔名
		g.drawString(" " + towerName[0] + "塔", point[this.totalPlate - 1].getX()-10,
				point[this.totalPlate - 1].getY() + 30);
		g.drawString(" " + towerName[1] + "塔", point[2 * this.totalPlate - 1].getX()-10,
				point[this.totalPlate - 1].getY() + 30);
		g.drawString(" " + towerName[2] + "塔", point[3 * this.totalPlate - 1].getX()-10,
				point[this.totalPlate - 1].getY() + 30);
		
		//画出游戏任务提示，即起点、终点
		g.setColor(Color.red);
		g.drawString("将全部盘子从" + towerName[0] + "塔搬运到" + towerName[1] + "塔或"
				+ towerName[2] + "塔", point[2 * this.totalPlate - 1].getX() - 80, point[this.totalPlate - 1]
				.getY() + 80);
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		Plate plate = null;
		if (e.getSource() instanceof Plate)
		{
			plate = (Plate) (e.getSource());
			move = true;
			// 将鼠标拖动事件转移到容器，导致容器上发生鼠标拖动事件
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
	//实现MouseLitsener中的抽象方法
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
			plate = (Plate) e.getSource();	//返回盘子对象
			rect = plate.getBounds();	//初始化所移动的图形
			// 将鼠标拖动事件转移到容器，导致容器上发生鼠标拖动事件
			e = SwingUtilities.convertMouseEvent(plate, e, this);
		}
		if (e.getSource() == this)
		{
			boolean containTowerPoint = false;
			int x = 0, y = 0;
			int endI = 0;
			
			if (plate != null)
			{
				// 检查盘子是否被移动到了一个塔点上
				for (int i = 0; i < 3 * totalPlate; i++) {
					x = point[i].getX();
					y = point[i].getY();

					// 检查当前所一定能够图形中是否包含某个塔点，包含则将此塔点设置为移动的终点
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
					//若终点塔点有盘子， 则将盘子放回原处
					plate.setLocation(startX, startY);
				}
				else
				{
					//如果目的地是个塔底
					if (endI ==  this.totalPlate - 1 || endI == 2*this.totalPlate - 1
							|| endI == 3*this.totalPlate - 1)
					{
						point[endI].addPlate(plate, this);
						//假如盘子起点不是塔底
						if (startI != this.totalPlate - 1 && startI != 2*this.totalPlate - 1
								&& startI != 3*this.totalPlate - 1)
						{
							point[startI+1].getPlate().setHasPlateUpstairs(false);
							point[startI].setHasPlate(false);
						}
						//假如盘子起点是塔底
						else
						{
							point[startI].setHasPlate(false);
						}
					}
					//如果目的地不是塔底
					else
					{
						if (point[endI + 1].getHasPlate() == true)
						{
							Plate tempPlate = point[endI + 1].getPlate();
							if (tempPlate.getNumber() > plate.getNumber())
							{
								point[endI].addPlate(plate, this);
								
								//如果起点不是塔底
								if (startI != this.totalPlate - 1 && startI != 2*this.totalPlate - 1
										&& startI != 3*this.totalPlate - 1)
								{
									point[startI].setHasPlate(false);
									point[startI + 1].getPlate().setHasPlateUpstairs(false);
									tempPlate.setHasPlateUpstairs(true);
								}
								//如果起点是塔底
								else
								{
									point[startI].setHasPlate(false);
									tempPlate.setHasPlateUpstairs(true);
								}
								
							}
							//如果下边的盘子小，那么将盘子放回原处
							else
							{
								plate.setLocation(startX, startY);
							}
						}
						//如果目的塔底下面的塔点没有盘子，则将盘子放回原处
						else
						{
							plate.setLocation(startX, startY);
						}
					}
				}
			}
			//如果没有放到塔点位置
			if (plate != null && !containTowerPoint)
			{
				plate.setLocation(startX, startY);
			}
			if (plate != null && (point[this.totalPlate].getHasPlate() || point[2*this.totalPlate].getHasPlate()))
			{
				JOptionPane.showMessageDialog(this,"恭喜你，完成了此局游戏！");
			}
		}
		this.repaint();
	}
	public void autoPerform(int plateNumber, char A, char B, char C){
		if (plateNumber == 1)
		{
			Hanoi.area.append(A+" 到："+C+"塔\n");
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
			Hanoi.area.append(A+" 到："+C+"塔\n");
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
