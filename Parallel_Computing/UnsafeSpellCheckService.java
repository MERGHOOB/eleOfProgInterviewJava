import java.util.LinkedHashMap;
import java.util.*;


public class UnsafeSpellCheckService { //extends SpellCheckService {


	private static  final int MAX_ENTRIES = 3;

	private static LinkedHashMap<String, String[]> cache = new LinkedHashMap<>() {
		protected boolean removeEldestEntry(Map.Entry eldest) {
			return size() > MAX_ENTRIES;
		}
	};


	public static void service(ServiceRequest req, ServiceResponse resp) {
		String w = req.extractWordFromRequest();

		if(cache.containsKey(w)) {
			resp.encodeIntoResponse(cache.get(w));
			return;
		}
		String[] closestToLastWord = getClosestInDictionary(w);
		cache.put(w,closestToLastWord);


	}

/*

	client A, B --> wants to use service
	client A --> cache does have the word for A 
	cleint B --> is scheduled.
	client B--> cache check if failed
			B -->  it computes the result and adds to the cache
			B--> if the cache is full and an entry will be evicted; this may be the result wanted by A
			A --> is scheduled back
			A --> cache will return null.


	Solution: A thread-safe solution would be to synchronize each call to the service; in this way only one trhead could be executing the method &nd no races
	b/w reads and writes.

	However poor performance.

	Other better solution is to lock the code which operates on the cached values. : the check on the cached value and update on the cache.

	Locking ensures thtat the read assingment on a hit and write assignment oncompletion are atomic.
*/
}

class SafeSpellCheckService  { // extends SpellCheckService
	private static  final int MAX_ENTRIES = 3;

	private static LinkedHashMap<String, String[]> cache = new LinkedHashMap<>() {
		protected boolean removeEldestEntry(Map.Entry eldest) {
			return size() > MAX_ENTRIES;
		}
	};


	public static void service(ServiceRequest req, ServiceResponse resp) {
		String w = req.extractWordFromRequest();

		synchronized(S2Alternative.class) {

			if(cache.containsKey(w)) {
			resp.encodeIntoResponse(cache.get(w));
			return;
			}
		}

		String[] closestToLastWord = getClosestInDictionary(w);
		synchronized(S2Alternative.class) {
			cache.put(w,closestToLastWord);
		}

	}



}