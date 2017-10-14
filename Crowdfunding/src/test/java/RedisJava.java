import java.util.HashMap;
import java.util.Map;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import com.crowdfunding.sjtu.model.Orders;

import redis.clients.jedis.Jedis;

//redis related testing
public class RedisJava {
	public void testRedis() {
		// 连接本地的 Redis 服务
		Jedis jedis = new Jedis("192.168.0.104", 6379);
		System.out.println("连接成功");
		// 查看服务是否运行
		// System.out.println("服务正在运行: "+jedis.ping());
		jedis.set("name", "xinxin");
		System.out.println(jedis.get("name"));
	}

	public static void main(String[] args) {
		RedisJava.testOrderClassPut();
		RedisJava.testOrderClassGet();
	}
	public static void testOrderClassPut(){
		ClassPathXmlApplicationContext appCtx = new ClassPathXmlApplicationContext("redis.xml");
		final RedisTemplate<String, Object> redisTemplate = appCtx.getBean("redisTemplate", RedisTemplate.class);
		HashOperations<String, Object, Object> hash = redisTemplate.opsForHash();
		HashMap<String,Object> map = new HashMap();
		Orders o = new Orders();
		o.setComment("dd");
		o.setCreateDateTime("fff");
		o.setOrderId(22);
		o.setProjectId(11);
		o.setShares(2);
		o.setStatus(1);
		o.setTotalAmount(5f);
		o.setUserId(3);
		
		map.put("orderId",o.getOrderId());
		map.put("createDateTime",o.getCreateDateTime());
		map.put("projectId",o.getProjectId());
		map.put("userId",o.getUserId());
		map.put("totalAmount",o.getTotalAmount());
		map.put("status",o.getStatus());
		map.put("shares",o.getShares());
		map.put("comment",o.getComment());
		
		hash.putAll("uuid11111", map);
		
	}
	public static void testOrderClassGet(){
		ClassPathXmlApplicationContext appCtx = new ClassPathXmlApplicationContext("redis.xml");
		final RedisTemplate<String, Object> redisTemplate = appCtx.getBean("redisTemplate", RedisTemplate.class);
		HashOperations<String, Object, Object> hash = redisTemplate.opsForHash();
		Map<Object, Object> map = new HashMap();
		Orders o = new Orders();
		
		map = hash.entries("uuid11111");
		System.out.println(hash.entries("uuid11111"));
		
		o.setComment((String) map.get("comment"));
		o.setShares((Integer) map.get("shares"));
		o.setStatus((Integer) map.get("shares"));
		o.setTotalAmount((Float) map.get("totalAmount"));
		o.setUserId((Integer) map.get("userId"));
		o.setProjectId((Integer) map.get("projectId"));
		o.setCreateDateTime((String) map.get("createDateTime"));
		o.setOrderId((Integer) map.get("orderId"));
		
	}	

	public static void testRedisWithMapPut() {
		ClassPathXmlApplicationContext appCtx = new ClassPathXmlApplicationContext("redis.xml");
		final RedisTemplate<String, Object> redisTemplate = appCtx.getBean("redisTemplate", RedisTemplate.class);
		HashOperations<String, Object, Object> hash = redisTemplate.opsForHash();
		HashMap<String,Object> map = new HashMap();
		map.put("userid", "xxxxx");
		map.put("price", 22);
		map.put("totalamount", 33);
		hash.putAll("uuid11111", map);
	}
	
	public static void testRedisWithMapGet() {
		ClassPathXmlApplicationContext appCtx = new ClassPathXmlApplicationContext("redis.xml");
		final RedisTemplate<String, Object> redisTemplate = appCtx.getBean("redisTemplate", RedisTemplate.class);
		HashOperations<String, Object, Object> hash = redisTemplate.opsForHash();
		HashMap<Object,Object> map =new HashMap();
		map =  (HashMap<Object, Object>) hash.entries("uuid11111");
		System.out.println(hash.entries("uuid11111"));
		System.out.println(hash.entries("uuid1111123"));
		System.out.println((hash.entries("uuid1111123").size()==0));
	}

	public static void testRedisWithSpring() {
		ClassPathXmlApplicationContext appCtx = new ClassPathXmlApplicationContext("redis.xml");
		final RedisTemplate<String, Object> redisTemplate = appCtx.getBean("redisTemplate", RedisTemplate.class);
		// 添加一个 key
		ValueOperations<String, Object> value = redisTemplate.opsForValue();
		value.set("lp", "hello word");
		// 获取 这个 key 的值
		System.out.println(value.get("lp"));

	}
}
