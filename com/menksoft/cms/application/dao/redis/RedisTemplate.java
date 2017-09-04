/**
 * 项目名称: 蒙科立SSH框架
 * 文件名     : RedisTemplate.java
 * 描述          : 
 * 作者          : 王弘
 * 日期          : 2012-12-12
 * 公司          : 内蒙古安达传媒文化有限责任公司
 *
 * 修改记录:
 * ------------------------------------------------------------------------------
 * 版本:  1.00
 * 
 * |  序号    |  修改日期     |  作者     |     备注            |  
 *     1     2012-12-12    王弘               新建文件
 */
package com.menksoft.cms.application.dao.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import redis.clients.jedis.BinaryClient.LIST_POSITION;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.SortingParams;
import redis.clients.jedis.Tuple;

/**
 * @author wangh
 *
 */
@Repository
public class RedisTemplate {
	@Resource
	private JedisPool masterJedisPool;
	
	/**
	 * 删除给定的一个或多个 key 。不存在的 key 会被忽略。
	 * @param keys
	 * @return 被删除 key 的数量。
	 * @throws Throwable
	 */
	public long del(String... keys) throws Throwable {
		long count = 0;
		Jedis jedis = null;
		try {
			jedis = masterJedisPool.getResource();
			count = jedis.del(keys);
		} catch (Throwable e) {
			throw e;
		} finally {
			closeConnection(jedis);
		}
		
		return count;
	}
	
	/**
	 * 查找所有符合给定模式 pattern 的 key 。
	 * @param pattern 模式表达式，可用的通配符或形式有：* ? []。 例如：h*llo h?llo h[ae]llo。
	 * @return 符合给定模式的 key 列表。
	 * @throws Throwable
	 */
	public Set<String> keys(String pattern) throws Throwable {
		Set<String> keys = null;
		Jedis jedis = null;
		try {
			jedis = masterJedisPool.getResource();
			keys = jedis.keys(pattern);
		} catch (Throwable e) {
			throw e;
		} finally {
			closeConnection(jedis);
		}
		
		return keys;
	}
	
	/**
	 * 从当前数据库中随机返回(不删除)一个 key。
	 * @return 当数据库不为空时，返回一个 key。当数据库为空时，返回 nil。
	 * @throws Throwable
	 */
	public String randomkey() throws Throwable {
		String key = null;
		Jedis jedis = null;
		try {
			jedis = masterJedisPool.getResource();
			key = jedis.randomKey();
		} catch (Throwable e) {
			throw e;
		} finally {
			closeConnection(jedis);
		}
		
		return key;
	}
	
	/**
	 * 以秒为单位，返回给定 key 的剩余生存时间。
	 * @param key
	 * @return 当 key 不存在或没有设置生存时间时，返回 -1 。否则，返回 key 的剩余生存时间(以秒为单位)
	 * @throws Throwable
	 */
	public long ttl(String key) throws Throwable {
		long seconds = 0;
		Jedis jedis = null;
		try {
			jedis = masterJedisPool.getResource();
			seconds = jedis.ttl(key);
		} catch (Throwable e) {
			throw e;
		} finally {
			closeConnection(jedis);
		}
		
		return seconds;
	}
	
	/**
	 * 检查给定 key 是否存在。
	 * @param key
	 * @return 若 key 存在，返回 1 ，否则返回 0 。
	 * @throws Throwable
	 */
	public boolean exists(String key) throws Throwable {
		boolean exist = false;
		Jedis jedis = null;
		try {
			jedis = masterJedisPool.getResource();
			exist = jedis.exists(key);
		} catch (Throwable e) {
			throw e;
		} finally {
			closeConnection(jedis);
		}
		
		return exist;
	}
	
	/**
	 * 将当前数据库的 key 移动到给定的数据库 db 当中。<br>
	 * 如果当前数据库(源数据库)和给定数据库(目标数据库)有相同名字的给定 key ，或者 key 不存在于当前数据库，那么 MOVE没有任何效果。
	 * @param key
	 * @param database
	 * @return 移动成功返回 1 ，失败则返回 0 。
	 * @throws Throwable
	 */
	public long move(String key, int database) throws Throwable {
		long success = 0;
		Jedis jedis = null;
		try {
			jedis = masterJedisPool.getResource();
			success = jedis.move(key, database);
		} catch (Throwable e) {
			throw e;
		} finally {
			closeConnection(jedis);
		}
		
		return success;
	}
	
	/**
	 * 将 oldkey改名为 newkey 。<br>
	 * 当 oldkey和 newkey相同，或者 oldkey不存在时，返回一个错误。当 newkey已经存在时，RENAME 命令将覆盖旧值。
	 * @param oldKey
	 * @param newKey
	 * @return 改名成功时返回 OK，失败时候返回一个错误。
	 * @throws Throwable
	 */
	public String rename(String oldKey, String newKey) throws Throwable {
		String message = null;
		Jedis jedis = null;
		try {
			jedis = masterJedisPool.getResource();
			message = jedis.rename(oldKey, newKey);
		} catch (Throwable e) {
			throw e;
		} finally {
			closeConnection(jedis);
		}
		
		return message;
	}
	
	/**
	 * 当且仅当 newkey不存在时，将 oldkey改名为 newkey 。
	 * @param oldKey
	 * @param newKey
	 * @return 修改成功时，返回 1 。如果 newkey 已经存在，返回 0 。
	 * @throws Throwable
	 */
	public long renamenx(String oldKey, String newKey) throws Throwable {
		long success = 0;
		Jedis jedis = null;
		try {
			jedis = masterJedisPool.getResource();
			success = jedis.renamenx(oldKey, newKey);
		} catch (Throwable e) {
			throw e;
		} finally {
			closeConnection(jedis);
		}
		
		return success;
	}
	
	/**
	 * 返回 key 所储存的值的类型。
	 * @param key
	 * @return none (key不存在) <br>
	 * string (字符串) <br>
	 * list (列表) <br>
	 * set (集合) <br>
	 * zset (有序集) <br>
	 * hash (哈希表)
	 * 
	 * @throws Throwable
	 */
	public String type(String key) throws Throwable {
		String type = null;
		Jedis jedis = null;
		try {
			jedis = masterJedisPool.getResource();
			type = jedis.type(key);
		} catch (Throwable e) {
			throw e;
		} finally {
			closeConnection(jedis);
		}
		
		return type;
	}
	
	/**
	 * 为给定 key设置生存时间，当 key过期时(生存时间为 0)，它会被自动删除。
	 * @param key
	 * @param seconds
	 * @return 设置成功返回 1 。当 key不存在或者不能为 key设置生存时间时，返回 0。
	 * @throws Throwable
	 */
	public long expire(String key, int seconds) throws Throwable {
		long success = 0;
		Jedis jedis = null;
		try {
			jedis = masterJedisPool.getResource();
			if(jedis.ttl(key) > 0) {
				jedis.persist(key);
			}
			success = jedis.expire(key, seconds);
		} catch (Throwable e) {
			throw e;
		} finally {
			closeConnection(jedis);
		}
		
		return success;
	}
	
	/**
	 * 为给定 key设置生存时间（UNIX 时间戳），当 key过期时(生存时间为 0)，它会被自动删除。
	 * @param key
	 * @param seconds
	 * @return 设置成功返回 1 。当 key不存在或者不能为 key设置生存时间时，返回 0。
	 * @throws Throwable
	 */
	public long expireAt(String key, long timestamp) throws Throwable {
		long success = 0;
		Jedis jedis = null;
		try {
			jedis = masterJedisPool.getResource();
			if(jedis.ttl(key) > 0) {
				jedis.persist(key);
			}
			success = jedis.expireAt(key, timestamp);
		} catch (Throwable e) {
			throw e;
		} finally {
			closeConnection(jedis);
		}
		
		return success;
	}
	
	/**
	 * 移除给定 key的生存时间，将这个 key从『可挥发』的转换成『持久化』的。
	 * @param key
	 * @return 当生存时间移除成功时，返回 1。如果 key 不存在或 key 没有设置生存时间，返回 0 。
	 * @throws Throwable
	 */
	public long persist(String key) throws Throwable {
		long success = 0;
		Jedis jedis = null;
		try {
			jedis = masterJedisPool.getResource();
			success = jedis.persist(key);
		} catch (Throwable e) {
			throw e;
		} finally {
			closeConnection(jedis);
		}
		
		return success;
	}
	
	/**
	 * 返回或保存给定列表、集合、有序集合 key中经过排序的元素。排序默认以数字作为对象，值被解释为双精度浮点数，然后进行比较。
	 * @param key
	 * @param byPattern 排序表达式
	 * @param isAsc 是否正序排序
	 * @param isAlpha 当数据集中保存的是字符串值时，可以用 ALPHA修饰符进行排序。
	 * @param start 指定要跳过的元素数量
	 * @param count 指定跳过 start个指定的元素之后，要返回多少个对象。
	 * @param getPattern
	 * 
	 * @return 返回列表形式的排序结果。
	 * @throws Throwable
	 */
	public List<String> sort(String key, String byPattern, boolean isAsc, boolean isAlpha, int start, int count, 
			String... getPattern) throws Throwable {
		List<String> sorted = null;
		Jedis jedis = null;
		try {
			SortingParams params = new SortingParams();
			if (byPattern != null && !params.equals("")) {
				params.by(byPattern);
			}
			if (start >= 0 && count >= 0) {
				params.limit(start, count);
			}
			if (isAsc) {
				params.asc();
			} else {
				params.desc();
			}
			if (isAlpha) {
				params.alpha();
			}
			if (getPattern != null && getPattern.length > 0) {
				params.get(getPattern);
			}
			
			jedis = masterJedisPool.getResource();
			sorted = jedis.sort(key, params);
		} catch (Throwable e) {
			throw e;
		} finally {
			closeConnection(jedis);
		}
		
		return sorted;
	}
	
	/**
	 * 将字符串值 value关联到 key。如果 key已经持有其他值， SET就覆写旧值，无视类型。
	 * @param key
	 * @param value
	 * @throws Throwable
	 */
	public void set(String key, String value) throws Throwable {
		Jedis jedis = null;
		try {
			jedis = masterJedisPool.getResource();
			jedis.set(key, value);
		} catch (Throwable e) {
			throw e;
		} finally {
			closeConnection(jedis);
		}
	}
	
	/**
	 * 将 key的值设为 value，当且仅当 key不存在。若给定的 key已经存在，则 SETNX不做任何动作。
	 * @param key
	 * @param value
	 * @return 设置成功，返回 1 。设置失败，返回 0 。
	 * @throws Throwable
	 */
	public long setnx(String key, String value) throws Throwable {
		long success = 0;
		Jedis jedis = null;
		try {
			jedis = masterJedisPool.getResource();
			success = jedis.setnx(key, value);
		} catch (Throwable e) {
			throw e;
		} finally {
			closeConnection(jedis);
		}
		
		return success;
	}
	
	/**
	 * 将值 value关联到 key，并将 key的生存时间设为 seconds(以秒为单位)。如果 key已经存在， SETEX命令将覆写旧值。
	 * @param key
	 * @param seconds
	 * @param value
	 * @return
	 * @throws Throwable
	 */
	public String setex(String key, int seconds, String value) throws Throwable {
		String message = null;
		Jedis jedis = null;
		try {
			jedis = masterJedisPool.getResource();
			message = jedis.setex(key, seconds, value);
		} catch (Throwable e) {
			throw e;
		} finally {
			closeConnection(jedis);
		}
		
		return message;
	}
	
	/**
	 * 从偏移量 offset开始，用 value 参数覆写给定 key所储存的字符串值。不存在的 key 当作空白字符串处理。
	 * @param key
	 * @param offset
	 * @param value
	 * @return 被 SETRANGE修改之后，字符串的长度。
	 * @throws Throwable
	 */
	public long setrange(String key, long offset, String value) throws Throwable {
		long length = 0;
		Jedis jedis = null;
		try {
			jedis = masterJedisPool.getResource();
			length = jedis.setrange(key, offset, value);
		} catch (Throwable e) {
			throw e;
		} finally {
			closeConnection(jedis);
		}
		
		return length;
	}
	
	/**
	 * 同时设置一个或多个 key-value对。如果某个给定 key已经存在，那么 MSET会用新值覆盖原来的旧值。
	 * @param keyvalue key-value的二维数组
	 * @throws Throwable
	 */
	public void mset(String... keyvalue) throws Throwable {
		Jedis jedis = null;
		try {
			jedis = masterJedisPool.getResource();
			jedis.mset(keyvalue);
		} catch (Throwable e) {
			throw e;
		} finally {
			closeConnection(jedis);
		}
	}
	
	/**
	 * 同时设置一个或多个 key-value对，当且仅当所有给定 key都不存在。<br>
	 * 即使只有一个给定 key 已存在， MSETNX 也会拒绝执行所有给定 key 的设置操作。<br>
	 * MSETNX 是原子性的，所有字段要么全被设置，要么全不被设置。
	 * @param keyvalue
	 * @return 当所有 key都成功设置，返回 1 。如果所有给定 key都设置失败(至少有一个 key已经存在)，那么返回 0 。
	 * @throws Throwable
	 */
	public long msetnx(String... keyvalue) throws Throwable {
		long success = 0;
		Jedis jedis = null;
		try {
			jedis = masterJedisPool.getResource();
			success = jedis.msetnx(keyvalue);
		} catch (Throwable e) {
			throw e;
		} finally {
			closeConnection(jedis);
		}
		
		return success;
	}
	
	/**
	 * 如果 key已经存在并且是一个字符串， APPEND命令将 value追加到 key原来的值的末尾。<br>
	 * 如果 key不存在，APPEND就简单地将给定 key设为 value，就像执行 SET key value 一样。
	 * @param key
	 * @param value
	 * @return 追加 value 之后， key 中字符串的长度。
	 * @throws Throwable
	 */
	public long append(String key, String value) throws Throwable {
		long length = 0;
		Jedis jedis = null;
		try {
			jedis = masterJedisPool.getResource();
			length = jedis.append(key, value);
		} catch (Throwable e) {
			throw e;
		} finally {
			closeConnection(jedis);
		}
		
		return length;
	}
	
	/**
	 * 返回 key所关联的字符串值。如果 key不存在那么返回特殊值 nil。<br>
	 * 假如 key储存的值不是字符串类型，返回一个错误，因为 GET 只能用于处理字符串值。
	 * @param key
	 * @return 当 key不存在时，返回 nil，否则，返回 key的值。如果 key不是字符串类型，那么返回一个错误。
	 * @throws Throwable
	 */
	public String get(String key) throws Throwable {
		String value = null;
		Jedis jedis = null;
		try {
			jedis = masterJedisPool.getResource();
			value = jedis.get(key);
		} catch (Throwable e) {
			throw e;
		} finally {
			closeConnection(jedis);
		}
		
		return value;
	}
	
	/**
	 * 返回所有(一个或多个)给定 key的值。如果给定的 key里面，有某个 key不存在，那么这个 key返回特殊值 nil。
	 * @param keys
	 * @return 一个包含所有给定 key的值的列表。
	 * @throws Throwable
	 */
	public List<String> mget(String... keys) throws Throwable {
		List<String> values = null;
		Jedis jedis = null;
		try {
			jedis = masterJedisPool.getResource();
			values = jedis.mget(keys);
		} catch (Throwable e) {
			throw e;
		} finally {
			closeConnection(jedis);
		}
		
		return values;
	}
	
	/**
	 * 返回 key中字符串值的子字符串，字符串的截取范围由 start和 end两个偏移量决定(包括 start和 end在内)。<br>
	 * 负数偏移量表示从字符串最后开始计数， -1 表示最后一个字符， -2 表示倒数第二个，以此类推。
	 * @param key
	 * @param start
	 * @param end
	 * @return 截取得出的子字符串。
	 * @throws Throwable
	 */
	public String getrange(String key, long start, long end) throws Throwable {
		String value = null;
		Jedis jedis = null;
		try {
			jedis = masterJedisPool.getResource();
			value = jedis.getrange(key, start, end);
		} catch (Throwable e) {
			throw e;
		} finally {
			closeConnection(jedis);
		}
		
		return value;
	}
	
	/**
	 * 将给定 key的值设为 value ，并返回 key的旧值。当 key存在但不是字符串类型时，返回一个错误。
	 * @param key
	 * @param value
	 * @return 返回给定 key的旧值。当 key没有旧值时，返回 nil。
	 * @throws Throwable
	 */
	public String getset(String key, String value) throws Throwable {
		String oldValue = null;
		Jedis jedis = null;
		try {
			jedis = masterJedisPool.getResource();
			oldValue = jedis.getSet(key, value);
		} catch (Throwable e) {
			throw e;
		} finally {
			closeConnection(jedis);
		}
		
		return oldValue;
	}
	
	/**
	 * 返回 key 所储存的字符串值的长度。
	 * @param key
	 * @return 字符串值的长度。当 key不存在时，返回 0 。
	 * @throws Throwable
	 */
	public long strlen(String key) throws Throwable {
		long length = 0;
		Jedis jedis = null;
		try {
			jedis = masterJedisPool.getResource();
			length = jedis.strlen(key);
		} catch (Throwable e) {
			throw e;
		} finally {
			closeConnection(jedis);
		}
		
		return length;
	}
	
	/**
	 * 将 key所储存的值减去减量 decrement。如果 key不存在，那么 key的值会先被初始化为 0 ，然后再执行 DECRBY操作。
	 * @param key
	 * @param decrement
	 * @return 执行 DECR命令之后 key的值。
	 * @throws Throwable
	 */
	public long decrby(String key, long decrement) throws Throwable {
		long value = 0;
		Jedis jedis = null;
		try {
			jedis = masterJedisPool.getResource();
			value = jedis.decrBy(key, decrement);
		} catch (Throwable e) {
			throw e;
		} finally {
			closeConnection(jedis);
		}
		
		return value;
	}
	
	/**
	 * 将 key所储存的值加上增量 increment。如果 key不存在，那么 key的值会先被初始化为 0 ，然后再执行 INCRBY命令。
	 * @param key
	 * @param increment
	 * @return
	 * @throws Throwable
	 */
	public long incrby(String key, long increment) throws Throwable {
		long value = 0;
		Jedis jedis = null;
		try {
			jedis = masterJedisPool.getResource();
			value = jedis.incrBy(key, increment);
		} catch (Throwable e) {
			throw e;
		} finally {
			closeConnection(jedis);
		}
		
		return value;
	}
	
	/**
	 * 对 key所储存的字符串值，设置或清除指定偏移量上的位(bit)。位的设置或清除取决于 bool参数，可以是 false也可以是 true 。<br>
	 * 当 key不存在时，自动生成一个新的字符串值。
	 * @param key
	 * @param offset
	 * @param bool
	 * @return 指定偏移量原来储存的位。
	 * @throws Throwable
	 */
	public boolean setbit(String key, long offset, boolean bool) throws Throwable {
		boolean value = false;
		Jedis jedis = null;
		try {
			jedis = masterJedisPool.getResource();
			value = jedis.setbit(key, offset, bool);
		} catch (Throwable e) {
			throw e;
		} finally {
			closeConnection(jedis);
		}
		
		return value;
	}
	
	/**
	 * 对 key所储存的字符串值，获取指定偏移量上的位(bit)。当 offset比字符串值的长度大，或者 key不存在时，返回 0 。
	 * @param key
	 * @param offset
	 * @return 字符串值指定偏移量上的位(bit)。
	 * @throws Throwable
	 */
	public boolean getbit(String key, long offset) throws Throwable {
		boolean value = false;
		Jedis jedis = null;
		try {
			jedis = masterJedisPool.getResource();
			value = jedis.getbit(key, offset);
		} catch (Throwable e) {
			throw e;
		} finally {
			closeConnection(jedis);
		}
		
		return value;
	}
	
	/**
	 * 将哈希表 key中的域 field的值设为 value。<br>
	 * 如果 key不存在，一个新的哈希表被创建并进行 HSET 操作。如果域 field 已经存在于哈希表中，旧值将被覆盖。
	 * @param key
	 * @param field
	 * @param value
	 * @return 如果 field是哈希表中的一个新建域，并且值设置成功，返回 1 。如果哈希表中域 field已经存在且旧值已被新值覆盖，返回 0 。
	 * @throws Throwable
	 */
	public long hset(String key, String field, String value) throws Throwable {
		long success = 0;
		Jedis jedis = null;
		try {
			jedis = masterJedisPool.getResource();
			success = jedis.hset(key, field, value);
		} catch (Throwable e) {
			throw e;
		} finally {
			closeConnection(jedis);
		}
		
		return success;
	}
	
	/**
	 * 将哈希表 key中的域 field的值设置为 value ，当且仅当域 field不存在。若域 field 已经存在，该操作无效。<br>
	 * 如果 key 不存在，一个新哈希表被创建并执行 HSETNX 命令。
	 * @param key
	 * @param field
	 * @param value
	 * @return 设置成功，返回 1 。如果给定域已经存在且没有操作被执行，返回 0 。
	 * @throws Throwable
	 */
	public long hsetnx(String key, String field, String value) throws Throwable {
		long success = 0;
		Jedis jedis = null;
		try {
			jedis = masterJedisPool.getResource();
			success = jedis.hsetnx(key, field, value);
		} catch (Throwable e) {
			throw e;
		} finally {
			closeConnection(jedis);
		}
		
		return success;
	}
	
	/**
	 * 同时将多个 field-value对设置到哈希表 key中。此命令会覆盖哈希表中已存在的域。<br>
	 * 如果 key 不存在，一个空哈希表被创建并执行 HMSET 操作。
	 * @param key
	 * @param fieldValue
	 * @return 如果命令执行成功，返回 OK。当 key不是哈希表(hash)类型时，返回一个错误。
	 * @throws Throwable
	 */
	public String hmset(String key, Map<String, String> fieldValue) throws Throwable {
		String message = null;
		Jedis jedis = null;
		try {
			jedis = masterJedisPool.getResource();
			message = jedis.hmset(key, fieldValue);
		} catch (Throwable e) {
			throw e;
		} finally {
			closeConnection(jedis);
		}
		
		return message;
	}
	
	/**
	 * 返回哈希表 key 中给定域 field 的值。
	 * @param key
	 * @param field
	 * @return 给定域的值。当给定域不存在或是给定 key不存在时，返回 nil 。
	 * @throws Throwable
	 */
	public String hget(String key, String field) throws Throwable {
		String value = null;
		Jedis jedis = null;
		try {
			jedis = masterJedisPool.getResource();
			value = jedis.hget(key, field);
		} catch (Throwable e) {
			throw e;
		} finally {
			closeConnection(jedis);
		}
		
		return value;
	}
	
	/**
	 * 返回哈希表 key中，一个或多个给定域的值。如果给定的域不存在于哈希表，那么返回一个 nil值。
	 * @param key
	 * @param fields
	 * @return 一个包含多个给定域的关联值的表，表值的排列顺序和给定域参数的请求顺序一样。
	 * @throws Throwable
	 */
	public List<String> hmget(String key, String... fields) throws Throwable {
		List<String> values = null;
		Jedis jedis = null;
		try {
			jedis = masterJedisPool.getResource();
			values = jedis.hmget(key, fields);
		} catch (Throwable e) {
			throw e;
		} finally {
			closeConnection(jedis);
		}
		
		return values;
	}
	
	/**
	 * 返回哈希表 key 中，所有的域和值。
	 * @param key
	 * @return 以哈希表形式返回域和域的值。
	 * @throws Throwable
	 */
	public Map<String, String> hgetall(String key) throws Throwable {
		Map<String, String> fieldValue = null;
		Jedis jedis = null;
		try {
			jedis = masterJedisPool.getResource();
			fieldValue = jedis.hgetAll(key);
		} catch (Throwable e) {
			throw e;
		} finally {
			closeConnection(jedis);
		}
		
		return fieldValue;
	}
	
	/**
	 * 删除哈希表 key中的一个或多个指定域，不存在的域将被忽略。
	 * @param key
	 * @param fields
	 * @return 被成功移除的域的数量，不包括被忽略的域。
	 * @throws Throwable
	 */
	public long hdel(String key, String... fields) throws Throwable {
		long count = 0;
		Jedis jedis = null;
		try {
			jedis = masterJedisPool.getResource();
			count = jedis.hdel(key, fields);
		} catch (Throwable e) {
			throw e;
		} finally {
			closeConnection(jedis);
		}
		
		return count;
	}
	
	/**
	 * 返回哈希表 key 中域的数量。
	 * @param key
	 * @return 哈希表中域的数量。当 key不存在时，返回 0 。
	 * @throws Throwable
	 */
	public long hlen(String key) throws Throwable {
		long count = 0;
		Jedis jedis = null;
		try {
			jedis = masterJedisPool.getResource();
			count = jedis.hlen(key);
		} catch (Throwable e) {
			throw e;
		} finally {
			closeConnection(jedis);
		}
		
		return count;
	}
	
	/**
	 * 查看哈希表 key 中，给定域 field 是否存在。
	 * @param key
	 * @param field
	 * @return 如果哈希表含有给定域，返回 true。如果哈希表不含有给定域，或 key不存在，返回 false。
	 * @throws Throwable
	 */
	public boolean hexists(String key, String field) throws Throwable {
		boolean exist = false;
		Jedis jedis = null;
		try {
			jedis = masterJedisPool.getResource();
			exist = jedis.hexists(key, field);
		} catch (Throwable e) {
			throw e;
		} finally {
			closeConnection(jedis);
		}
		
		return exist;
	}
	
	/**
	 * 为哈希表 key 中的域 field 的值加上增量 increment。<br>
	 * 增量也可以为负数，相当于对给定域进行减法操作。<br>
	 * 如果 key不存在，一个新的哈希表被创建并执行 HINCRBY 命令。<br>
	 * 如果域 field不存在，那么在执行命令前，域的值被初始化为 0 。
	 * @param key
	 * @param field
	 * @param increment
	 * @return 执行 HINCRBY命令之后，哈希表 key中域 field的值。
	 * @throws Throwable
	 */
	public long hincrby(String key, String field, long increment) throws Throwable {
		long value = 0;
		Jedis jedis = null;
		try {
			jedis = masterJedisPool.getResource();
			value = jedis.hincrBy(key, field, increment);
		} catch (Throwable e) {
			throw e;
		} finally {
			closeConnection(jedis);
		}
		
		return value;
	}
	
	/**
	 * 返回哈希表 key 中的所有域。
	 * @param key
	 * @return 一个包含哈希表中所有域的表。当 key不存在时，返回一个空表。
	 * @throws Throwable
	 */
	public Set<String> hkeys(String key) throws Throwable {
		Set<String> fields = null;
		Jedis jedis = null;
		try {
			jedis = masterJedisPool.getResource();
			fields = jedis.hkeys(key);
		} catch (Throwable e) {
			throw e;
		} finally {
			closeConnection(jedis);
		}
		
		return fields;
	}
	
	/**
	 * 返回哈希表 key 中所有域的值。
	 * @param key
	 * @return 一个包含哈希表中所有值的表。当 key不存在时，返回一个空表。
	 * @throws Throwable
	 */
	public List<String> hvals(String key) throws Throwable {
		List<String> values = null;
		Jedis jedis = null;
		try {
			jedis = masterJedisPool.getResource();
			values = jedis.hvals(key);
		} catch (Throwable e) {
			throw e;
		} finally {
			closeConnection(jedis);
		}
		
		return values;
	}
	
	/**
	 * 将一个或多个值 value插入到列表 key的表头。如果有多个 value 值，那么各个 value值按从左到右的顺序依次插入到表头。<br>
	 * 如果 key不存在，一个空列表会被创建并执行 LPUSH 操作。
	 * @param key
	 * @param values
	 * @return 执行 LPUSH 命令后，列表的长度。
	 * @throws Throwable
	 */
	public long lpush(String key, String... values) throws Throwable {
		long count = 0;
		Jedis jedis = null;
		try {
			jedis = masterJedisPool.getResource();
			count = jedis.lpush(key, values);
		} catch (Throwable e) {
			throw e;
		} finally {
			closeConnection(jedis);
		}
		
		return count;
	}
	
	/**
	 * 将值 value插入到列表 key的表头，当且仅当 key存在并且是一个列表。<br>
	 * 当 key 不存在时， LPUSHX 命令什么也不做。
	 * @param key
	 * @param field
	 * @return LPUSHX 命令执行之后，表的长度。
	 * @throws Throwable
	 */
	public long lpushx(String key, String value) throws Throwable {
		long count = 0;
		Jedis jedis = null;
		try {
			jedis = masterJedisPool.getResource();
			count = jedis.lpushx(key, value);
		} catch (Throwable e) {
			throw e;
		} finally {
			closeConnection(jedis);
		}
		
		return count;
	}
	
	/**
	 * 将一个或多个值 value 插入到列表 key 的表尾。如果有多个 value 值，那么各个 value 值按从左到右的顺序依次插入到表尾。<br>
	 * 如果 key 不存在，一个空列表会被创建并执行 RPUSH 操作。
	 * @param key
	 * @param values
	 * @return 执行 RPUSH 操作后，表的长度。
	 * @throws Throwable
	 */
	public long rpush(String key, String... values) throws Throwable {
		long count = 0;
		Jedis jedis = null;
		try {
			jedis = masterJedisPool.getResource();
			count = jedis.lpush(key, values);
		} catch (Throwable e) {
			throw e;
		} finally {
			closeConnection(jedis);
		}
		
		return count;
	}
	
	/**
	 * 将值 value插入到列表 key的尾，当且仅当 key存在并且是一个列表。<br>
	 * 当 key 不存在时， RPUSHX 命令什么也不做。
	 * @param key
	 * @param field
	 * @return RPUSHX 命令执行之后，表的长度。
	 * @throws Throwable
	 */
	public long rpushx(String key, String value) throws Throwable {
		long count = 0;
		Jedis jedis = null;
		try {
			jedis = masterJedisPool.getResource();
			count = jedis.rpushx(key, value);
		} catch (Throwable e) {
			throw e;
		} finally {
			closeConnection(jedis);
		}
		
		return count;
	}
	
	/**
	 * 移除并返回列表 key的头元素。
	 * @param key
	 * @return 列表的头元素。当 key不存在时，返回 nil。
	 * @throws Throwable
	 */
	public String lpop(String key) throws Throwable {
		String value = null;
		Jedis jedis = null;
		try {
			jedis = masterJedisPool.getResource();
			value = jedis.lpop(key);
		} catch (Throwable e) {
			throw e;
		} finally {
			closeConnection(jedis);
		}
		
		return value;
	}
	
	/**
	 * 移除并返回列表 key的尾元素。
	 * @param key
	 * @return 列表的尾元素。当 key不存在时，返回 nil。
	 * @throws Throwable
	 */
	public String rpop(String key) throws Throwable {
		String value = null;
		Jedis jedis = null;
		try {
			jedis = masterJedisPool.getResource();
			value = jedis.rpop(key);
		} catch (Throwable e) {
			throw e;
		} finally {
			closeConnection(jedis);
		}
		
		return value;
	}
	
	/**
	 * 当给定列表内没有任何元素可供弹出的时候，连接将被 BLPOP 命令阻塞，直到等待超时或发现可弹出元素为止。<br>
	 * 当给定多个 key 参数时，按参数 key 的先后顺序依次检查各个列表，弹出第一个非空列表的头元素。
	 * @param key
	 * @return 如果列表为空，返回一个 nil。否则，返回一个含有两个元素的列表，第一个元素是被弹出元素所属的 key ，第二个元素是被弹出元素的值。
	 * @throws Throwable
	 */
	public List<String> blpop(int timeout, String... keys) throws Throwable {
		List<String> values = null;
		Jedis jedis = null;
		try {
			jedis = masterJedisPool.getResource();
			values = jedis.blpop(timeout, keys);
		} catch (Throwable e) {
			throw e;
		} finally {
			closeConnection(jedis);
		}
		
		return values;
	}
	
	/**
	 * 当给定列表内没有任何元素可供弹出的时候，连接将被 BRPOP 命令阻塞，直到等待超时或发现可弹出元素为止。<br>
	 * 当给定多个 key 参数时，按参数 key 的先后顺序依次检查各个列表，弹出第一个非空列表的头元素。
	 * @param key
	 * @return 如果列表为空，返回一个 nil。否则，返回一个含有两个元素的列表，第一个元素是被弹出元素所属的 key ，第二个元素是被弹出元素的值。
	 * @throws Throwable
	 */
	public List<String> brpop(int timeout, String... keys) throws Throwable {
		List<String> values = null;
		Jedis jedis = null;
		try {
			jedis = masterJedisPool.getResource();
			values = jedis.brpop(timeout, keys);
		} catch (Throwable e) {
			throw e;
		} finally {
			closeConnection(jedis);
		}
		
		return values;
	}
	
	/**
	 * 返回列表 key 的长度。如果 key 不存在，则 key 被解释为一个空列表，返回 0。
	 * @param key
	 * @return 列表 key 的长度。
	 * @throws Throwable
	 */
	public long llen(String key) throws Throwable {
		long length = 0;
		Jedis jedis = null;
		try {
			jedis = masterJedisPool.getResource();
			length = jedis.llen(key);
		} catch (Throwable e) {
			throw e;
		} finally {
			closeConnection(jedis);
		}
		
		return length;
	}
	
	/**
	 * 返回列表 key 中指定区间内的元素，区间以偏移量 start和 stop指定。以 -1 表示列表的最后一个元素， -2 表示列表的倒数第二个元素，以此类推。
	 * @param key
	 * @param start
	 * @param stop
	 * @return 一个列表，包含指定区间内的元素。
	 * @throws Throwable
	 */
	public List<String> lrange(String key, long start, long stop) throws Throwable {
		List<String> values = null;
		Jedis jedis = null;
		try {
			jedis = masterJedisPool.getResource();
			values = jedis.lrange(key, start, stop);
		} catch (Throwable e) {
			throw e;
		} finally {
			closeConnection(jedis);
		}
		
		return values;
	}
	
	/**
	 * 根据参数 count 的值，移除列表中与参数 value 相等的元素。count 的值可以是以下几种：<br>
	 * count > 0 : 从表头开始向表尾搜索，移除与 value 相等的元素，数量为 count 。<br>
	 * count < 0 : 从表尾开始向表头搜索，移除与 value 相等的元素，数量为 count 的绝对值。<br>
	 * count = 0 : 移除表中所有与 value 相等的值。
	 * @param key
	 * @param count
	 * @param value
	 * @return 被移除元素的数量。当 key 不存在时， LREM 命令总是返回 0 。
	 * @throws Throwable
	 */
	public long lrem(String key, long count, String value) throws Throwable {
		long rmCount = 0;
		Jedis jedis = null;
		try {
			jedis = masterJedisPool.getResource();
			rmCount = jedis.lrem(key, count, value);
		} catch (Throwable e) {
			throw e;
		} finally {
			closeConnection(jedis);
		}
		
		return rmCount;
	}
	
	/**
	 * 将列表 key 下标为 index 的元素的值设置为 value 。<br>
	 * 当 index 参数超出范围，或对一个空列表( key 不存在)进行 LSET 时，返回一个错误。
	 * @param key
	 * @param index
	 * @param value
	 * @return 操作成功返回 ok ，否则返回错误信息。
	 * @throws Throwable
	 */
	public String lset(String key, long index, String value) throws Throwable {
		String message = null;
		Jedis jedis = null;
		try {
			jedis = masterJedisPool.getResource();
			message = jedis.lset(key, index, value);
		} catch (Throwable e) {
			throw e;
		} finally {
			closeConnection(jedis);
		}
		
		return message;
	}
	
	/**
	 * 只保留指定区间内的元素，不在指定区间之内的元素都将被删除。
	 * @param key
	 * @param start
	 * @param stop
	 * @return 命令执行成功时，返回 ok 。
	 * @throws Throwable
	 */
	public String ltrim(String key, long start, long stop) throws Throwable {
		String message = null;
		Jedis jedis = null;
		try {
			jedis = masterJedisPool.getResource();
			message = jedis.ltrim(key, start, stop);
		} catch (Throwable e) {
			throw e;
		} finally {
			closeConnection(jedis);
		}
		
		return message;
	}
	
	/**
	 * 返回列表 key 中，下标为 index 的元素。
	 * @param key
	 * @param index
	 * @return 列表中下标为 index 的元素。如果 index 参数的值不在列表的区间范围内，返回 nil 。
	 * @throws Throwable
	 */
	public String lindex(String key, long index) throws Throwable {
		String value = null;
		Jedis jedis = null;
		try {
			jedis = masterJedisPool.getResource();
			value = jedis.lindex(key, index);
		} catch (Throwable e) {
			throw e;
		} finally {
			closeConnection(jedis);
		}
		
		return value;
	}
	
	/**
	 * 将值 value 插入到列表 key 当中，位于值 pivot 之前或之后。<br>
	 * 当 pivot 不存在于列表 key 时，不执行任何操作。<br>
	 * 当 key 不存在时， key 被视为空列表，不执行任何操作。
	 * @param key
	 * @param isAfter
	 * @param pivot
	 * @param value
	 * @return 如果命令执行成功，返回插入操作完成之后，列表的长度。如果没有找到 pivot ，返回 -1 。如果 key 不存在或为空列表，返回 0 。
	 * @throws Throwable
	 */
	public long linsert(String key, boolean isAfter, String pivot, String value) throws Throwable {
		long success = 0;
		Jedis jedis = null;
		try {
			jedis = masterJedisPool.getResource();
			if (isAfter) {
				success = jedis.linsert(key, LIST_POSITION.AFTER, pivot, value);
			} else {
				success = jedis.linsert(key, LIST_POSITION.BEFORE, pivot, value);
			}
		} catch (Throwable e) {
			throw e;
		} finally {
			closeConnection(jedis);
		}
		
		return success;
	}
	
	/**
	 * 在一个原子时间内:
	 * 将列表 source 中的最后一个元素(尾元素)弹出，并返回给客户端;
	 * 将 source 弹出的元素插入到列表 destination ，作为 destination 列表的的头元素。
	 * 如果 source 不存在，值 nil 被返回，并且不执行其他动作
	 * @param source
	 * @param target
	 * @return 被弹出的元素。
	 * @throws Throwable
	 */
	public String rpoplpush(String source, String target) throws Throwable {
		String value = null;
		Jedis jedis = null;
		try {
			jedis = masterJedisPool.getResource();
			value = jedis.rpoplpush(source, target);
		} catch (Throwable e) {
			throw e;
		} finally {
			closeConnection(jedis);
		}
		
		return value;
	}
	
	/**
	 * 当列表 source 为空时,命令将阻塞连接，直到等待超时，或有另一个客户端对 source 执行 LPUSH 或 RPUSH 命令为止。
	 * @param source
	 * @param target
	 * @param timeout
	 * @return 假如在指定时间内没有任何元素被弹出，则返回一个 nil。反之，返回被弹出元素的值。
	 * @throws Throwable
	 */
	public String brpoplpush(String source, String target, int timeout) throws Throwable {
		String value = null;
		Jedis jedis = null;
		try {
			jedis = masterJedisPool.getResource();
			value = jedis.brpoplpush(source, target, timeout);
		} catch (Throwable e) {
			throw e;
		} finally {
			closeConnection(jedis);
		}
		
		return value;
	}
	
	/**
	 * 将一个或多个 value元素加入到集合 key当中，已经存在于集合的 value元素将被忽略。
	 * 假如 key不存在，则创建一个只包含 value元素作成员的集合。
	 * @param key
	 * @param values
	 * @return 被添加到集合中的新元素的数量，不包括被忽略的元素。
	 * @throws Throwable
	 */
	public long sadd(String key, String... values) throws Throwable {
		long count = 0;
		Jedis jedis = null;
		try {
			jedis = masterJedisPool.getResource();
			count = jedis.sadd(key, values);
		} catch (Throwable e) {
			throw e;
		} finally {
			closeConnection(jedis);
		}
		
		return count;
	}
	
	/**
	 * 移除集合 key中的一个或多个 value元素，不存在的 value元素会被忽略。
	 * @param key
	 * @param values
	 * @return 被成功移除的元素的数量，不包括被忽略的元素。
	 * @throws Throwable
	 */
	public long srem(String key, String... values) throws Throwable {
		long count = 0;
		Jedis jedis = null;
		try {
			jedis = masterJedisPool.getResource();
			count = jedis.srem(key, values);
		} catch (Throwable e) {
			throw e;
		} finally {
			closeConnection(jedis);
		}
		
		return count;
	}
	
	/**
	 * 返回集合 key中的所有成员。不存在的 key被视为空集合。
	 * @param key
	 * @return 集合中的所有成员。
	 * @throws Throwable
	 */
	public Set<String> smembers(String key) throws Throwable {
		Set<String> values = null;
		Jedis jedis = null;
		try {
			jedis = masterJedisPool.getResource();
			values = jedis.smembers(key);
		} catch (Throwable e) {
			throw e;
		} finally {
			closeConnection(jedis);
		}
		
		return values;
	}
	
	/**
	 * 判断 value元素是否集合 key的成员。
	 * @param key
	 * @param value
	 * @return 如果 value元素是集合的成员，返回 true。如果 value元素不是集合的成员，或 key不存在，返回 false 。
	 * @throws Throwable
	 */
	public boolean sismember(String key, String value) throws Throwable {
		boolean isMember = false;
		Jedis jedis = null;
		try {
			jedis = masterJedisPool.getResource();
			isMember = jedis.sismember(key, value);
		} catch (Throwable e) {
			throw e;
		} finally {
			closeConnection(jedis);
		}
		
		return isMember;
	}
	
	/**
	 * 返回集合 key的基数(集合中元素的数量)。
	 * @param key
	 * @return 集合的基数。当 key不存在时，返回 0 。
	 * @throws Throwable
	 */
	public long scard(String key) throws Throwable {
		long count = 0;
		Jedis jedis = null;
		try {
			jedis = masterJedisPool.getResource();
			count = jedis.scard(key);
		} catch (Throwable e) {
			throw e;
		} finally {
			closeConnection(jedis);
		}
		
		return count;
	}
	
	/**
	 * 将 value元素从 source集合移动到 target集合。<br>
	 * 如果 source集合不存在或不包含指定的 value元素，则 SMOVE命令不执行任何操作，仅返回 0 。
	 * 否则， value元素从 source集合中被移除，并添加到 target集合中去。<br>
	 * 当 target集合已经包含 value元素时， SMOVE命令只是简单地将 source集合中的 value元素删除。
	 * @param source
	 * @param target
	 * @param value
	 * @return 如果 value元素被成功移除，返回 1 。如果 value元素不是 source集合的成员，并且没有任何操作对 target集合执行，那么返回 0 。
	 * @throws Throwable
	 */
	public long smove(String source, String target, String value) throws Throwable {
		long success = 0;
		Jedis jedis = null;
		try {
			jedis = masterJedisPool.getResource();
			success = jedis.smove(source, target, value);
		} catch (Throwable e) {
			throw e;
		} finally {
			closeConnection(jedis);
		}
		
		return success;
	}
	
	/**
	 * 移除并返回集合中的一个随机元素。
	 * @param key
	 * @return 被移除的随机元素。当 key不存在或 key是空集时，返回 nil。
	 * @throws Throwable
	 */
	public String spop(String key) throws Throwable {
		String value = null;
		Jedis jedis = null;
		try {
			jedis = masterJedisPool.getResource();
			value = jedis.spop(key);
		} catch (Throwable e) {
			throw e;
		} finally {
			closeConnection(jedis);
		}
		
		return value;
	}
	
	/**
	 * 返回集合中的一个随机元素。不对集合进行任何改动。
	 * @param key
	 * @return 集合中的一个随机元素。如果集合为空，返回 nil。
	 * @throws Throwable
	 */
	public String srandmember(String key) throws Throwable {
		String value = null;
		Jedis jedis = null;
		try {
			jedis = masterJedisPool.getResource();
			value = jedis.srandmember(key);
		} catch (Throwable e) {
			throw e;
		} finally {
			closeConnection(jedis);
		}
		
		return value;
	}
	
	/**
	 * 返回一个集合的全部成员，该集合是所有给定集合的交集。不存在的 key 被视为空集。
	 * @param keys
	 * @return 交集成员的列表。
	 * @throws Throwable
	 */
	public Set<String> sinter(String... keys) throws Throwable {
		Set<String> values = null;
		Jedis jedis = null;
		try {
			jedis = masterJedisPool.getResource();
			values = jedis.sinter(keys);
		} catch (Throwable e) {
			throw e;
		} finally {
			closeConnection(jedis);
		}
		
		return values;
	}
	
	/**
	 * 将所有给定集合的交集保存到 target集合。如果 target集合已经存在，则将其覆盖。target可以是 key本身。
	 * @param target
	 * @param keys
	 * @return 结果集中的成员数量。
	 * @throws Throwable
	 */
	public long sinterstore(String target, String... keys) throws Throwable {
		long count = 0;
		Jedis jedis = null;
		try {
			jedis = masterJedisPool.getResource();
			count = jedis.sinterstore(target, keys);
		} catch (Throwable e) {
			throw e;
		} finally {
			closeConnection(jedis);
		}
		
		return count;
	}
	
	/**
	 * 返回一个集合的全部成员，该集合是所有给定集合的并集。不存在的 key 被视为空集。
	 * @param keys
	 * @return 并集成员的列表。
	 * @throws Throwable
	 */
	public Set<String> sunion(String... keys) throws Throwable {
		Set<String> values = null;
		Jedis jedis = null;
		try {
			jedis = masterJedisPool.getResource();
			values = jedis.sunion(keys);
		} catch (Throwable e) {
			throw e;
		} finally {
			closeConnection(jedis);
		}
		
		return values;
	}
	
	/**
	 * 将所有给定集合的并集保存到 target集合。如果 target集合已经存在，则将其覆盖。target可以是 key本身。
	 * @param target
	 * @param keys
	 * @return 结果集中的成员数量。
	 * @throws Throwable
	 */
	public long sunionstore(String target, String... keys) throws Throwable {
		long count = 0;
		Jedis jedis = null;
		try {
			jedis = masterJedisPool.getResource();
			count = jedis.sunionstore(target, keys);
		} catch (Throwable e) {
			throw e;
		} finally {
			closeConnection(jedis);
		}
		
		return count;
	}
	
	/**
	 * 返回给定key集合中第一个key所代表的集合与后续其他key代表集合的差异集合。
	 * @param keys
	 * @return 给定key集合中第一个key所代表的集合与后续其他key代表集合的差异集合。
	 * @throws Throwable
	 */
	public Set<String> sdiff(String... keys) throws Throwable {
		Set<String> values = null;
		Jedis jedis = null;
		try {
			jedis = masterJedisPool.getResource();
			values = jedis.sdiff(keys);
		} catch (Throwable e) {
			throw e;
		} finally {
			closeConnection(jedis);
		}
		
		return values;
	}
	
	/**
	 * 将给定key集合中第一个key所代表的集合与后续其他key代表集合的差异集合保存到 target集合。
	 * 如果 target集合已经存在，则将其覆盖。target可以是 key本身。
	 * @param target
	 * @param keys
	 * @return 结果集中的元素数量。
	 * @throws Throwable
	 */
	public long sdiffstore(String target, String... keys) throws Throwable {
		long count = 0;
		Jedis jedis = null;
		try {
			jedis = masterJedisPool.getResource();
			count = jedis.sdiffstore(target, keys);
		} catch (Throwable e) {
			throw e;
		} finally {
			closeConnection(jedis);
		}
		
		return count;
	}
	
	/**
	 * 将一个或多个 member元素及其 score值加入到有序集 key当中。
	 * 如果某个 member已经是有序集的成员，那么更新这个 member的 score值，并通过重新插入这个 member元素，来保证该 member在正确的位置上。
	 * 如果 key 不存在，则创建一个空的有序集并执行 ZADD 操作。
	 * @param key
	 * @param scoreMember
	 * @return 被成功添加的新成员的数量，不包括那些被更新的、已经存在的成员。
	 * @throws Throwable
	 */
	public long zadd(String key, Map<Double, String> scoreMember) throws Throwable {
		long count = 0;
		Jedis jedis = null;
		try {
			jedis = masterJedisPool.getResource();
			count = jedis.zadd(key, scoreMember);
		} catch (Throwable e) {
			throw e;
		} finally {
			closeConnection(jedis);
		}
		
		return count;
	}
	
	/**
	 * 移除有序集 key中的一个或多个成员，不存在的成员将被忽略。
	 * @param key
	 * @param member
	 * @return 被成功移除的成员的数量，不包括被忽略的成员。
	 * @throws Throwable
	 */
	public long zrem(String key, String... member) throws Throwable {
		long count = 0;
		Jedis jedis = null;
		try {
			jedis = masterJedisPool.getResource();
			count = jedis.zrem(key, member);
		} catch (Throwable e) {
			throw e;
		} finally {
			closeConnection(jedis);
		}
		
		return count;
	}
	
	/**
	 * 返回有序集 key的基数。
	 * @param key
	 * @return 当 key存在且是有序集类型时，返回有序集的基数。当 key不存在时，返回 0 。
	 * @throws Throwable
	 */
	public long zcard(String key) throws Throwable {
		long count = 0;
		Jedis jedis = null;
		try {
			jedis = masterJedisPool.getResource();
			count = jedis.zcard(key);
		} catch (Throwable e) {
			throw e;
		} finally {
			closeConnection(jedis);
		}
		
		return count;
	}
	
	/**
	 * 返回有序集 key中， score值在 min和 max之间(默认包括 score值等于 min或 max)的成员的数量。
	 * @param key
	 * @param min
	 * @param max
	 * @return score值在 min和 max之间的成员的数量。
	 * @throws Throwable
	 */
	public long zcount(String key, long min, long max) throws Throwable {
		long count = 0;
		Jedis jedis = null;
		try {
			jedis = masterJedisPool.getResource();
			count = jedis.zcount(key, min, max);
		} catch (Throwable e) {
			throw e;
		} finally {
			closeConnection(jedis);
		}
		
		return count;
	}
	
	/**
	 * 返回有序集 key中，成员 member的 score值
	 * @param key
	 * @param member
	 * @return member成员的 score值
	 * @throws Throwable
	 */
	public double zscore(String key, String member) throws Throwable {
		double score = 0;
		Jedis jedis = null;
		try {
			jedis = masterJedisPool.getResource();
			score = jedis.zscore(key, member);
		} catch (Throwable e) {
			throw e;
		} finally {
			closeConnection(jedis);
		}
		
		return score;
	}
	
	/**
	 * 为有序集 key的成员 member的 score值加上增量 increment。可以通过传递一个负数值 increment，让 score减去相应的值。
	 * @param key
	 * @param member
	 * @param increment
	 * @return member成员的新 score值
	 * @throws Throwable
	 */
	public double zincrby(String key, String member, double increment) throws Throwable {
		double score = 0;
		Jedis jedis = null;
		try {
			jedis = masterJedisPool.getResource();
			score = jedis.zincrby(key, increment, member);
		} catch (Throwable e) {
			throw e;
		} finally {
			closeConnection(jedis);
		}
		
		return score;
	}
	
	/**
	 * 返回有序集 key中，指定区间内的成员。其中成员的位置按 score值递增(从小到大)来排序。
	 * @param key
	 * @param start
	 * @param stop
	 * @return 指定区间内，有序集成员的列表。
	 * @throws Throwable
	 */
	public Set<String> zrange(String key, long start, long stop) throws Throwable {
		Set<String> members = null;
		Jedis jedis = null;
		try {
			jedis = masterJedisPool.getResource();
			members = jedis.zrange(key, start, stop);
		} catch (Throwable e) {
			throw e;
		} finally {
			closeConnection(jedis);
		}
		
		return members;
	}
	
	/**
	 * 返回有序集 key中，指定区间内的成员。其中成员的位置按 score值递增(从小到大)来排序。
	 * @param key
	 * @param start
	 * @param stop
	 * @return 指定区间内，带有 score 值的有序集成员的列表。
	 * @throws Throwable
	 */
	public Set<Tuple> zrangeWithScores(String key, long start, long stop) throws Throwable {
		Set<Tuple> members = null;
		Jedis jedis = null;
		try {
			jedis = masterJedisPool.getResource();
			members = jedis.zrangeWithScores(key, start, stop);
		} catch (Throwable e) {
			throw e;
		} finally {
			closeConnection(jedis);
		}
		return members;
	}
	
	/**
	 * 返回有序集 key中，指定区间内的成员。其中成员的位置按 score值递减(从大到小)来排序。
	 * @param key
	 * @param start
	 * @param stop
	 * @return 指定区间内，有序集成员的列表。
	 * @throws Throwable
	 */
	public Set<String> zrevrange(String key, long start, long stop) throws Throwable {
		Set<String> members = null;
		Jedis jedis = null;
		try {
			jedis = masterJedisPool.getResource();
			members = jedis.zrevrange(key, start, stop);
		} catch (Throwable e) {
			throw e;
		} finally {
			closeConnection(jedis);
		}
		
		return members;
	}
	
	/**
	 * 返回有序集 key中，指定区间内的成员。其中成员的位置按 score值递减(从大到小)来排序。
	 * @param key
	 * @param start
	 * @param stop
	 * @return 指定区间内，带有 score 值的有序集成员的列表。
	 * @throws Throwable
	 */
	public Set<Tuple> zrevrangeWithScores(String key, long start, long stop) throws Throwable {
		Set<Tuple> members = null;
		Jedis jedis = null;
		try {
			jedis = masterJedisPool.getResource();
			members = jedis.zrevrangeWithScores(key, start, stop);
		} catch (Throwable e) {
			throw e;
		} finally {
			closeConnection(jedis);
		}
		return members;
	}
	
	/**
	 * 返回有序集 key 中，所有 score值介于 min和 max之间(包括等于 min或 max)的成员。有序集成员按 score值递增(从小到大)次序排列。
	 * @param key
	 * @param start
	 * @param stop
	 * @param offset
	 * @param count
	 * @return 指定区间内，有序集成员的列表。
	 * @throws Throwable
	 */
	public Set<String> zrangebyscore(String key, long min, long max, int offset, int count) throws Throwable {
		Set<String> members = null;
		Jedis jedis = null;
		try {
			jedis = masterJedisPool.getResource();
			if (offset >= 0) {
				members = jedis.zrangeByScore(key, min, max, offset, count);
			} else {
				members = jedis.zrangeByScore(key, min, max);
			}
		} catch (Throwable e) {
			throw e;
		} finally {
			closeConnection(jedis);
		}
		
		return members;
	}
	
	/**
	 * 返回有序集 key 中，所有 score值介于 min和 max之间(包括等于 min或 max)的成员。有序集成员按 score值递增(从小到大)次序排列。
	 * @param key
	 * @param min
	 * @param max
	 * @param offset
	 * @param count
	 * @return 指定区间内，带有 score值的有序集成员的列表。
	 * @throws Throwable
	 */
	public Set<Tuple> zrangebyscoreWithScores(String key, long min, long max, int offset, int count) throws Throwable {
		Set<Tuple> members = null;
		Jedis jedis = null;
		try {
			jedis = masterJedisPool.getResource();
			if (offset >= 0) {
				members = jedis.zrangeByScoreWithScores(key, min, max, offset, count);
			} else {
				members = jedis.zrangeByScoreWithScores(key, min, max);
			}
		} catch (Throwable e) {
			throw e;
		} finally {
			closeConnection(jedis);
		}
		
		return members;
	}
	
	/**
	 * 返回有序集 key中，所有 score值介于 min和 max之间(包括等于 min或 max)的成员。有序集成员按 score值递减(从大到小)次序排列。
	 * @param key
	 * @param start
	 * @param stop
	 * @param offset
	 * @param count
	 * @return 指定区间内，有序集成员的列表。
	 * @throws Throwable
	 */
	public Set<String> zrevrangebyscore(String key, long min, long max, int offset, int count) throws Throwable {
		Set<String> members = null;
		Jedis jedis = null;
		try {
			jedis = masterJedisPool.getResource();
			if (offset >= 0) {
				members = jedis.zrevrangeByScore(key, min, max, offset, count);
			} else {
				members = jedis.zrevrangeByScore(key, min, max);
			}
		} catch (Throwable e) {
			throw e;
		} finally {
			closeConnection(jedis);
		}
		
		return members;
	}
	
	/**
	 * 返回有序集 key 中，所有 score值介于 min和 max之间(包括等于 min或 max)的成员。有序集成员按 score值递减(从大到小)次序排列。
	 * @param key
	 * @param min
	 * @param max
	 * @param offset
	 * @param count
	 * @return 指定区间内，带有 score值的有序集成员的列表。
	 * @throws Throwable
	 */
	public Set<Tuple> zrevrangebyscoreWithScores(String key, long min, long max, int offset, int count) throws Throwable {
		Set<Tuple> members = null;
		Jedis jedis = null;
		try {
			jedis = masterJedisPool.getResource();
			if (offset >= 0) {
				members = jedis.zrevrangeByScoreWithScores(key, min, max, offset, count);
			} else {
				members = jedis.zrevrangeByScoreWithScores(key, min, max);
			}
		} catch (Throwable e) {
			throw e;
		} finally {
			closeConnection(jedis);
		}
		
		return members;
	}
	
	/**
	 * 返回有序集 key中成员 member的排名。其中有序集成员按 score值递增(从小到大)顺序排列。
	 * @param key
	 * @param member
	 * @return 如果 member是有序集 key的成员，返回 member的排名。
	 * @throws Throwable
	 */
	public long zrank(String key, String member) throws Throwable {
		long score = 0;
		Jedis jedis = null;
		try {
			jedis = masterJedisPool.getResource();
			score = jedis.zrank(key, member);
		} catch (Throwable e) {
			throw e;
		} finally {
			closeConnection(jedis);
		}
		
		return score;
	}
	
	/**
	 * 返回有序集 key中成员 member的排名。其中有序集成员按 score 值递减(从大到小)排序。
	 * @param key
	 * @param member
	 * @return 如果 member是有序集 key的成员，返回 member的排名。
	 * @throws Throwable
	 */
	public long zrevrank(String key, String member) throws Throwable {
		long score = 0;
		Jedis jedis = null;
		try {
			jedis = masterJedisPool.getResource();
			score = jedis.zrevrank(key, member);
		} catch (Throwable e) {
			throw e;
		} finally {
			closeConnection(jedis);
		}
		
		return score;
	}
	
	/**
	 * 移除有序集 key中，指定排名(rank)区间内的所有成员。
	 * @param key
	 * @param start
	 * @param stop
	 * @return 被移除成员的数量。
	 * @throws Throwable
	 */
	public long zremrangebyrank(String key, long start, long stop) throws Throwable {
		long count = 0;
		Jedis jedis = null;
		try {
			jedis = masterJedisPool.getResource();
			count = jedis.zremrangeByRank(key, start, stop);
		} catch (Throwable e) {
			throw e;
		} finally {
			closeConnection(jedis);
		}
		
		return count;
	}
	
	/**
	 * 移除有序集 key中，所有 score值介于 min和 max之间(包括等于 min或 max)的成员。
	 * @param key
	 * @param min
	 * @param max
	 * @return 被移除成员的数量。
	 * @throws Throwable
	 */
	public long zremrangebyscore(String key, double min, double max) throws Throwable {
		long count = 0;
		Jedis jedis = null;
		try {
			jedis = masterJedisPool.getResource();
			count = jedis.zremrangeByScore(key, min, max);
		} catch (Throwable e) {
			throw e;
		} finally {
			closeConnection(jedis);
		}
		
		return count;
	}
	
	public Jedis getConnection() {
		return masterJedisPool.getResource();
	}
	
	public void closeConnection(Jedis jedis) {
		if(jedis != null) {
			masterJedisPool.returnResource(jedis);
		}
	}
	
	public boolean isNull(String value) {
		return (value==null) || "nil".equals(value.trim());
	}
}
