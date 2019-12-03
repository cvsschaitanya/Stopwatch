
public class FactoryCoordinator {

	public static void main(String[] args) {
		// TODO Auto-generated constructor stub
		Interface I = Interface.sproutInterface("foreground");
		
		timer T = timer.sproutTimer(I, "background");
		
		System.out.println("With Factory");
	}

}
