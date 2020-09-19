package online.memphis;

import java.util.Date;

public class Main {

	public static void main(String[] args) throws Exception {
		Date start = new Date();
		System.out.println(ProjectEuler.solveTask20(100));
		System.out.println(System.currentTimeMillis() - start.getTime());
	}
}
