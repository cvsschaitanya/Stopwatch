
public class Coordinator {

	

	public static void main(final String[] args) {
		
		final Interface I = new Interface();
		final Thread fore = new Thread(I, "foreground");

		final timer T = new timer(I);
		final Thread back = new Thread(T, "background");
		
		fore.start();
		back.start();
		
		System.out.println("Without Factory");
		
	}

}
