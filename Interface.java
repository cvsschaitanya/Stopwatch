import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;;

public class Interface extends JFrame implements ActionListener, Runnable {

	JButton start,pause,reset;
	JLabel time;
	Container pane;
	Thread fore;
	
	String Reading;
	
	volatile boolean Running,changed;
	
	volatile long centisec;
	
	public Interface()
	{
		this(null);
	}
	
	public Interface(String ThreadName)
	{
		Reading = "00:00:00:00";
		Running = false;
		changed = false;
		centisec = 0;
		if(ThreadName==null)
			fore = new Thread(this);
		else
			fore = new Thread(this,ThreadName);
		
		pane = getContentPane();
		pane.setBackground(Color.WHITE);
		pane.setLayout(new FlowLayout());
		
		time = new JLabel(Reading);
		
		start = new JButton("START");
		pause = new JButton("PAUSE");
		reset = new JButton("RESET");
		
		start.addActionListener(this);
		pause.addActionListener(this);
		reset.addActionListener(this);
						
		pane.add(time);
		pane.add(start);
		pane.add(pause);
		pane.add(reset);
		
	}
	

	@Override
	public synchronized void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		String but = e.getActionCommand();
		
		if (but.equals("START"))
		{
			Running = true;
		}
		if (but.equals("PAUSE"))
		{
			Running = false;
		}
		if (but.equals("RESET"))
		{
			Running = false;
			centisec = 0;
			changed = true;
		}
		
	}

	private void updateReading()
	{
		int hour,minute,second,centisecond;
		long temp = centisec;
		String h,m,s,c;
		
		hour = (int) (temp / (360000));
		temp = temp % 360000;
		h = (hour>=10)?"":"0";
		
		minute = (int) (temp / 6000);
		temp = temp % 6000;
		m = (minute>=10)?"":"0";
		
		second = (int) (temp / 100);
		temp = temp % 100;
		s = (second>=10)?"":"0";
		
		centisecond = (int) temp;
		c = (centisecond>=10)?"":"0";
		
		Reading = h+hour+':'+m+minute+':'+s+second+':'+c+centisecond;

	}
	
	public synchronized boolean isRunning() {
		return Running;
	}


	public void incCentisec() {
		centisec++;
	}


	@Override
	public void run() {
		// TODO Auto-generated method stub
		setSize(2000,1000);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
		for(;;)
		{
			
			
			if(changed)
			{
				updateReading();
				time.setText(Reading);
				changed = false;
			}
		}
		
	}


	public void setChanged(boolean changed) {
		this.changed = changed;
	}
	
	public static Interface sproutInterface(String name)
	{
		Interface I = new Interface(name);
		I.fore.start();
		return I;
	}

}
