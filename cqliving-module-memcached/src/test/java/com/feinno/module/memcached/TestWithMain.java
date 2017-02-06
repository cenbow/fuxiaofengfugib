package com.feinno.module.memcached;

import java.net.SocketAddress;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.spy.memcached.MemcachedClient;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestWithMain {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext-test-main.xml");
		SpyMemcachedClient client = (SpyMemcachedClient) context.getBean("spyMemcachedClient");
		List<String> keys = client.listKeys("key7", 5);
		System.out.println(keys.size());
		System.out.println(keys);
	}

	public static void testCachedump(SpyMemcachedClient client) {
		MemcachedClient mc = client.getMemcachedClient();

		// for (int i = 1; i <= 100; i++) {
		// client.set("key" + i, RandomStringUtils.random(i * 10));
		// }

		// for (int i = 1; i <= 50; i++) {
		// client.delete("key" + i);
		// }

		Map<SocketAddress, Map<String, String>> stats = mc.getStats("items");
		Set<String> slabs = new HashSet<String>();
		for (Map.Entry<SocketAddress, Map<String, String>> entry : stats.entrySet()) {
			System.out.println(entry);
			for (Map.Entry<String, String> entry3 : entry.getValue().entrySet()) {
				System.out.println(entry3);
				slabs.add(StringUtils.split(entry3.getKey(), ':')[1]);
			}
			System.out.println(slabs);
		}
		for (String slab : slabs) {
			cachedump(mc, slab);
		}
	}

	public static void cachedump(MemcachedClient mc, String slab) {
		String cachedumpArgs = "cachedump " + slab + " 0";
		Map<SocketAddress, Map<String, String>> data = mc.getStats(cachedumpArgs);
		for (Map.Entry<SocketAddress, Map<String, String>> entry2 : data.entrySet()) {
			System.out.println(entry2);
		}
	}

}
