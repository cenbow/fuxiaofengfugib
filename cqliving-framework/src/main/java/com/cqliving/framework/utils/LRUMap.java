package com.cqliving.framework.utils;  
  
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
  
/** 
 * MomoLRUMap 
 *  
 * @author wanghailong 
 */  
public class LRUMap<V> {  
  
    private ConcurrentMap<String, V> map;  
      
    private ConcurrentMap<String, Long[]> expireMap;  
      
    public LRUMap() {  
        this.map = new ConcurrentHashMap<String,V>();
        this.expireMap = new ConcurrentHashMap<String,Long[]>();
    }  
  
    public V get(String k) {  
        Long[] expire = expireMap.get(k);  
          
        if (expire != null && expire.length == 2) {  
            // If expired: expireSecond*1000 < (now - createTime)  
            if (expire[1] * 1000 < (now() - expire[0])) {  
                remove(k);  
            }  
        }
  
        return map.get(k);  
    }  
  
    public int size() {  
        return this.map.size();  
    }  
  
    /** expire:second */  
    public void put(String k, V v, long expireSecond) {  
        Long[] expire = new Long[] { now(), expireSecond };  
        this.map.put(k, v);  
        this.expireMap.put(k, expire);  
          
    }
    
    public void put(String k, V v) {  
        this.map.put(k, v);  
          
    }
  
    public V remove(String k) {  
        this.expireMap.remove(k);  
        return this.map.remove(k);  
    }  
  
    public void clear() {  
        this.map.clear();  
        this.expireMap.clear();  
    }  
      
    public boolean containsKey(String k) {  
        return get(k) != null;  
    }  
  
    public Set<String> keySet() {  
        return this.map.keySet();  
    }  
  
    private long now() {  
        return System.currentTimeMillis();  
    }  
   
}  