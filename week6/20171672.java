import java.util.*;



class Expression {
	private static Vector<String> postfix = new Vector<String>();
	private static Stack<Character> stack = new Stack<Character>();
	private static Vector<Character> operator = new Vector<Character>();
	private static Stack<Double> calc = new Stack<Double>();

	static double Eval(Vector<String> infix) throws Exception {
		postfix.clear();
		stack.clear();
		calc.clear();
		operator.add('*');
		operator.add('/');
		operator.add('+');
		operator.add('-');
		System.out.print("infix expression : [");
		for(int u=0; u<infix.size(); u++) {
			if(u!=infix.size()-1)
				System.out.print(infix.get(u) + "  ");
			else
				System.out.print(infix.get(u) + "]");
		}
		System.out.println("");
		for(int i=0; i<infix.size(); i++) {
			if(isNumber(infix.get(i))) {
				postfix.add(infix.get(i));
				System.out.println("Token : " + infix.get(i));
				System.out.print("Stack : ");
				for(int y=0; y<stack.size(); y++) {
					System.out.print(stack.get(y)+ " ");
				}
				System.out.println("");
			}
			else {
				char c =(infix.get(i).toCharArray())[0];
				if(c=='(') {
					stack.push(c);
					System.out.println("Token : " + c);
					System.out.print("Stack : ");
					for(int z=0; z<stack.size(); z++) {
						System.out.print(stack.get(z)+ " ");
					}
					System.out.println("");
				}
				else if(c==')') {
					while(true) {
						char top = stack.pop();
						if(top=='(') {
							break;
					}
					postfix.add(String.format("%c",top));
					}
					System.out.println("Token : " + c);
					System.out.print("Stack : ");
					for(int b=0; b<stack.size(); b++) {
						System.out.print(stack.get(b)+ " ");
					}
					System.out.println("");
			}
				else if(operator.contains(c)) {
					int p = pref(c);
					while(!stack.isEmpty()) {
						char top = stack.peek();
						if(pref(top)<p) {
							break;
						}
						postfix.add(String.format("%c",stack.pop()));
					}
					stack.add(c);
					System.out.println("Token : " + c);
					System.out.print("Stack : ");
					for(int z=0; z<stack.size(); z++) {
						System.out.print(stack.get(z)+ " ");
					}
					System.out.println("");
		}

	}
		}
	while(!stack.isEmpty()){
		postfix.add(String.format("%c",stack.pop()));
	}
		System.out.print("Postfix expression : [");
		for(int u=0; u<postfix.size(); u++) {
			if(u!=postfix.size()-1)
				System.out.print(postfix.get(u) + "  ");
			else
				System.out.println(postfix.get(u) + "]");
		}
	
	for(int e = 0; e<postfix.size(); e++) {
		if(isNumber(postfix.get(e))) {
			calc.push(Double.parseDouble(postfix.get(e)));
		}
		else {
			if(postfix.get(e).equals("+")){
				double right = calc.pop();
				double left = calc.pop();
				calc.push(left + right);
			}
			else if(postfix.get(e).equals("-")){
				double right = calc.pop();
				double left = calc.pop();
				calc.push(left - right);
			}
			else if(postfix.get(e).equals("*")){
				double right = calc.pop();
				double left = calc.pop();
				calc.push(left * right);
			}
			else if(postfix.get(e).equals("/")){
				double right = calc.pop();
				double left = calc.pop();
				calc.push(left / right);
			}
		}
	}

	return calc.get(0);
	
	}
		public static boolean isNumber(String s) {
			char c = (s.toCharArray())[0];
			return Character.isDigit(c);
		}
		public static int pref(char c) {
			if(c=='*' || c== '/')
				return 1;
			else if(c=='(')
				return -1;
			else 
				return 0;
		}

	
}; 



