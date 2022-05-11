import java.util.HashMap;
import java.util.Map;


public class dummy {

	public static void main(String[] args) {
		dummy obj1 = new dummy();
		System.out.println(obj1.readChar("nitesh"));
		
	}
	
	public Map<Character, Integer> readChar(String S){
				
		Map<Character, Integer> map = new HashMap<Character, Integer>();
		
		char[] c = S.toCharArray();
		for(char i: c) {
			if (map.containsKey(c)) {
				map.put(c, map.get(c)+1);
			}
			else
				map.put(c, 1);
		}
		
		return map;
		
		
		
	}

}
