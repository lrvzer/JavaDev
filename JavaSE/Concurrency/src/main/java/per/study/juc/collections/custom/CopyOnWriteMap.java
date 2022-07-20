package per.study.juc.collections.custom;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;

public class CopyOnWriteMap<K, V> implements Map<K, V>
{

    private volatile Map<K, V> map;

    ReentrantLock lock = new ReentrantLock();

    public CopyOnWriteMap() {
        this.init();
    }

    private void init() {
        this.map = new HashMap<>();
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return map.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return map.containsValue(value);
    }

    @Override
    public V get(Object key) {
        return map.get(key);
    }

    @Override
    public V put(K key, V value) {
        try {
            lock.lock();
            Map<K, V> newMap = new HashMap<>(map);
            V val = newMap.put(key, value);
            this.map = newMap;
            return val;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public V remove(Object key) {
        try {
            lock.lock();
            Map<K, V> newMap = new HashMap<>(map);
            V val = newMap.remove(key);
            this.map = newMap;
            return val;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void putAll(Map m) {
        try {
            lock.lock();
            Map<K, V> newMap = new HashMap<>(map);
            newMap.putAll(m);
            this.map = newMap;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void clear() {
        try {
            lock.lock();
            map = new HashMap<>();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public Set<K> keySet() {
        return map.keySet();
    }

    @Override
    public Collection<V> values() {
        return map.values();
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return map.entrySet();
    }
}
