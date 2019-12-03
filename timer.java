
public class timer implements Runnable {

	Interface I;
	Thread back;
	
	public timer(Interface i) {
		// TODO Auto-generated constructor stub
		this(i,null);
	}
	
	public timer(Interface i,String ThreadName) {
		super();
		I = i;
		if(ThreadName==null)
			back = new Thread(this);
		else
			back = new Thread(this,ThreadName);
		
	}



	@Override
	public void run() {
		// TODO Auto-generated method stub
		for(;;) {
			if (I.isRunning())
			{
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			if (I.isRunning())
			{
				I.incCentisec();
				I.setChanged(true);
		
			}
		}
	}
	
	public static timer sproutTimer(Interface I,String name)
	{
		timer T = new timer(I,name);
		T.back.start();
		return T;
	}

}
