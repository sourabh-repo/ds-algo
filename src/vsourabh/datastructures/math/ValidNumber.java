package vsourabh.datastructures.math;

/**
 * @author Sourabh
 * 
 * LeetCode:
 * Validate if a given string is numeric.
 * 
 * Some examples:
 * "0" => true
 * " 0.1 " => true
 * "abc" => false
 * "1 a" => false
 * "2e10" => true
 * 
 * Note: It is intended for the problem statement to 
 * be ambiguous. You should gather all requirements 
 * up front before implementing one.
 *
 */
public class ValidNumber {
	
	
	/**
	 * We start with trimming.
	 * 
	 * If we see [0-9] we reset the number flags.
	 * We can only see . if we didn't see e or ..
	 * We can only see e if we didn't see e but we did see 
	 * 		a number. We reset numberAfterE flag.
	 * We can only see + and - in the beginning and after an e
	 * any other character break the validation.
	 * At the and it is only valid if there was at least 1 number 
	 * 		and if we did see an e then a number after it as well.
	 * 
	 * So basically the number should match this regular expression:
	 *      [-+]?(([0-9]+(.[0-9]*)?)|.[0-9]+)(e[-+]?[0-9]+)?
	 */
	public boolean isNumber(String s) {
	    s = s.trim();
	    
	    boolean pointSeen = false;
	    boolean eSeen = false;
	    boolean numberSeen = false;
	    boolean numberAfterE = true;
	    for(int i=0; i<s.length(); i++) {
	        if('0' <= s.charAt(i) && s.charAt(i) <= '9') {
	            numberSeen = true;
	            numberAfterE = true;
	        } else if(s.charAt(i) == '.') {
	            if(eSeen || pointSeen) {
	                return false;
	            }
	            pointSeen = true;
	        } else if(s.charAt(i) == 'e') {
	            if(eSeen || !numberSeen) {
	                return false;
	            }
	            numberAfterE = false;
	            eSeen = true;
	        } else if(s.charAt(i) == '-' || s.charAt(i) == '+') {
	            if(i != 0 && s.charAt(i-1) != 'e') {
	                return false;
	            }
	        } else {
	            return false;
	        }
	    }
	    
	    return numberSeen && numberAfterE;
	}
	
	public static void main(String[] args) {
		ValidNumber obj = new ValidNumber();
		boolean result = obj.isNumber("121");
		System.out.println("The input is number " + result);

		result = obj.isNumber("1 ab");
		System.out.println("The input is number " + result);
		
		result = obj.isNumber("001");
		System.out.println("The input is number " + result);
		
		result = obj.isNumber("1e5");
		System.out.println("The input is number " + result);
		
		result = obj.isNumber("abc");
		System.out.println("The input is number " + result);
	}

}