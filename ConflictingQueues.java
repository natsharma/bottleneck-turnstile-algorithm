import java.util.*;
import java.io.*;
public class ConflictingQueues {
	
	public static void main(String [] args) throws FileNotFoundException {
		List<Integer> time = Arrays.asList(1, 1, 1, 2);
		List<Integer> direction = Arrays.asList(0, 1, 1, 0);
		/*
		 * other inputs for test
		 * times = [1, 1, 1, 2], directio = [0, 1, 1, 0]
		 * times = [0, 0, 0, 0, 1, 1, 3], direction = [0, 0, 1, 1, 1, 0, 1]
		 * times = [0, 0, 0, 0, 1, 1, 4], direction = [0, 0, 1, 1, 1, 0, 1]
		 */
		List<Integer> list = getTimes(time, direction);
		for(Integer i : list) {System.out.print(i + " ");}
		
	}
	public static List<Integer> getTimes(List<Integer> time, List<Integer> direction){
		List<Integer> passes = new ArrayList<Integer>(time.size());
		int[] result = new int[time.size()];
		Queue<Integer> iQ = new LinkedList<Integer>();
		Queue<Integer> oQ = new LinkedList<Integer>();
		//current second
		int globalTimer = 0;
		boolean used = false;
		boolean out = false;
		int i = 0;
		while(i < time.size() || !iQ.isEmpty() || !oQ.isEmpty()) {
			while (i < time.size() && globalTimer == time.get(i)){
	            // enter
	            if (direction.get(i) == 0){
	                iQ.offer(i);
	            }else{
	                oQ.offer(i);
	            }
	            i++;
	        }
			if (!oQ.isEmpty() || !iQ.isEmpty()) {
				if(!used) {
					//whoever wants to leave goes first
					if(!oQ.isEmpty()) {
						result[oQ.poll()] = globalTimer;
						//passes.add(oQ.poll(), globalTimer);
						out = true;
					} else {
						result[iQ.poll()] = globalTimer;
						//passes.add(iQ.poll(), globalTimer);
						out = false;
					}
					used = true;
				} else {
					if(out && !oQ.isEmpty()) {
						result[oQ.poll()] = globalTimer;
						//passes.add(oQ.poll(), globalTimer);
						out = true;
					} else {
						result[iQ.poll()] = globalTimer;
						//passes.add(iQ.poll(), globalTimer);
						out = false;
					}
				}
			} else {
				//queue is empty, and used is reset to false
				used = false;
			}
			globalTimer++;
		}
		for(int r=0; r<time.size(); r++) {passes.add(result[r]);}
		return passes;
	}

}
	
	

