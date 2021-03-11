package _01_复杂度;
import _00_utils.TimeUtil;
import _00_utils.TimeUtil.Task;

public class Main {

	/*
	 * 斐波拉切数
	 * 0 1 1 2 3 5 8 13 .....
	 * 需求：求斐波拉切数的第n数
	 */
	
	// 第一种方法（递归）
	// 复杂度 O(2^n)
	public static int fib1(int n) {
		if (n <= 1) return n;
		return fib1(n - 1) + fib1(n - 2);
	}
	
	// 第二种方法（遍历 for循环）
	// 复杂度 O(n)
	public static int fib2(int n) {
		if (n <= 1) return n;
		
		int frist = 0;
		int second = 1;
		for (int i = 0; i < n - 1; i++) {
			int sum = frist + second;
			frist = second;
			second = sum;
		}
		return second;
	}
	
	// 第3种方法（遍历 while循环）
	// 复杂度 O(n)
	public static int fib3(int n) {
		if (n <= 1) return n;

		int frist = 0;
		int second = 1;
		while (n-- > 1) {
			second = frist + second;
			frist = second - frist;
		}
		return second;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TimeUtil.check("fib1", new Task() {
			public void execute() {
				System.out.println(fib1(42));
			}
		});
		
		TimeUtil.check("fib2", new Task() {
			public void execute() {
				System.out.println(fib2(42));
			}
		});

		TimeUtil.check("fib3", new Task() {
			public void execute() {
				System.out.println(fib3(42));
			}
		});
	}

}
