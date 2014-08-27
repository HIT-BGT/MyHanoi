import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Label;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class Hanoi extends Frame implements ActionListener,Runnable{
	public Tower tower = null;
	GameConfig defaultConfig = null;
	GameConfig userConfig = null;
	public Button restart = null;
	public Button auto = null;
	public Thread thread = null;
	public Label label = null;
	public Choice choice = null;
	public static TextArea area = new TextArea(12,12);
	public char towerName[] = {'A', 'B', 'C' };
	
	public Hanoi(){
		defaultConfig = new GameConfig();
		defaultConfig.setTotalPlate(3);
		defaultConfig.setPlateHeight(20);
		defaultConfig.setPlateWidth(120);
		defaultConfig.setGamePanelHeight();
		defaultConfig.setGamePanelWidth();
		defaultConfig.setMainFrameHeight();
		defaultConfig.setMainFrameWidth();
		init();
	}
	public void init(){
		this.setTitle("1130310317_白广通_汉诺塔游戏");
		this.setBounds(100, 50, defaultConfig.getMainFrameWidth(), defaultConfig.getMainFrameHeight());
		this.setLayout(null);
		
		//在主窗口中增加塔的面板
		tower = new Tower(defaultConfig, towerName);
		tower.setBounds(0, 0, defaultConfig.getGamePanelWidth(), defaultConfig.getGamePanelHeight());
		this.add(tower);
		
		//在主窗口中增加重新开始的按钮
		restart = new Button("重新开始");
		restart.setBounds(defaultConfig.getGamePanelWidth()-100, defaultConfig.getGamePanelHeight(),
				100,50);
		restart.addActionListener(this);
		this.add(restart);
		
		thread = new Thread(this);
		
		//在主窗口中增加自动演示的按钮
		auto = new Button("自动演示");
		auto.setBounds(defaultConfig.getGamePanelWidth()-3*defaultConfig.plateWidth-100, defaultConfig.getGamePanelHeight()
				,100,50);
		auto.addActionListener(this);
		this.add(auto);
		
		label = new Label("选择盘数:");
		label.setBounds(170, defaultConfig.getGamePanelHeight(),
				50, 50);
		this.add(label);
		
		choice = new Choice();
		for (int i = 1; i<= 9; i++)
		{
			choice.add(String.valueOf(i));
		}
		choice.setBounds(240, defaultConfig.getGamePanelHeight()+15, 100, 50);
		this.add(choice);
		
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		this.setVisible(true);
		this.validate();
	}
	
	//点击按钮时执行的方法
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == restart)
		{
			if (area.isVisible()) this.remove(area);
			this.myRepaint();
		}
		else if(e.getSource()==auto)
		{
			if (!(thread.isAlive()))
			{
				thread = new Thread(this);
			}
			try
			{
				thread.start();
			}catch(Exception e1)
			{
				thread.stop();
				thread = new Thread(this);
				thread.start();
			}
		}
		
	}
	public static void main(String[] args){
		new Hanoi();
	} 
	public void stop()
	 {
		 thread = null;
	 }
	@Override
	public void run() { 
		area.setBackground(Color.white);
		area.setText("");
		area.setBounds(defaultConfig.gamePanelWidth-defaultConfig.plateWidth+5, 
				50, 105, 150);
		area.append("自动演示中：\n");
		this.add(area);
		this.myRepaint();
		tower.autoPerform(userConfig.totalPlate,towerName[0],
				towerName[1], towerName[2]);
		area.setBackground(Color.yellow);
		area.append("演示完成！\n");
		this.validate();
		this.repaint();
	}
	public void myRepaint(){
		this.remove(tower);
		userConfig = new GameConfig();
		userConfig = defaultConfig;
		userConfig.setTotalPlate(Integer.parseInt(choice.getSelectedItem()));
		userConfig.setGamePanelWidth();
		userConfig.setGamePanelHeight();
		userConfig.setMainFrameWidth();
		userConfig.setMainFrameHeight();
		
		tower = new Tower(userConfig, towerName);
		tower.setBounds(0, 0, userConfig.getGamePanelWidth(), userConfig.getGamePanelHeight());
		this.setBounds(100, 50, userConfig.getMainFrameWidth(), userConfig.getMainFrameHeight());
		
		restart.setBounds(userConfig.getGamePanelWidth()-100, userConfig.getGamePanelHeight(),
				100,50);
		label.setBounds(170, userConfig.getGamePanelHeight(), 50, 50);
		choice.setBounds(240, userConfig.getGamePanelHeight()+15, 100, 50);
		auto.setBounds(defaultConfig.getGamePanelWidth()-3*defaultConfig.plateWidth-100, defaultConfig.getGamePanelHeight()
				,100,50);
		this.add(tower);
		this.validate();
		this.repaint();
	}
	
}
