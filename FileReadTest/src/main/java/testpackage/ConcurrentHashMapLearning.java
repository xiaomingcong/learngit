package testpackage;

import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapLearning {
	
	public static void main(String[] args) {
		ConcurrentHashMap<String, String> crm = new ConcurrentHashMap<String, String>(512);
		for(int i = 0;i<=46 ;i++) {
			crm.put(String.valueOf(i), String.valueOf(i));
		}
		crm.put("88", "88");
		crm.put("89", "89");
		int j=2;
		System.out.println("end");
	}

}
class myConcurrentHashMap<K,V> extends ConcurrentHashMap{

	static final int HASH_BITS = 0x7fffffff; // usable bits of normal node hash
	private transient volatile int sizeCtl;

	@Override
	public Object put(Object key, Object value) {
		return super.put(key, value);
	}

	@Override
	public Object putIfAbsent(Object key, Object value) {
		return super.putIfAbsent(key, value);
	}

	final V putVal(K key, V value, boolean onlyIfAbsent) {
		if(key == null || value == null)
			throw new IllegalArgumentException();
		int hash = spread(key.hashCode());

		return null;
	}
	static final int spread(int h) {
		return (h ^ (h >>> 16)) & HASH_BITS;
	}
}