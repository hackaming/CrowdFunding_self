package NetworkRelated;

import java.net.UnknownHostException;
import java.util.HashMap;

import org.apache.log4j.Logger;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;

public class MongoDBTest {
	public void insert() {
		Logger logger = Logger.getLogger(MongoDBTest.class);
		Logger logger1 = Logger.getLogger("MongoDB");
		while (true) {
			logger.debug("debug");
			logger.info("info");
			logger.warn("warn");
			logger.error("error");
			logger1.error("MongoDB Error");
			logger1.error("ss:ss");
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void main(String argv[]) throws UnknownHostException {
		get();
	}

	public static void get() throws UnknownHostException {

		Mongo mongo = new Mongo("10.62.150.33"); // 数据库地址

		DB db = mongo.getDB("logs"); // 数据库名

		DBCollection dbc = db.getCollection("log"); // col名，可以理解为表名
		DBCursor dbcursor = dbc.find();// find可以加条件，不加条件就检索所有记录
		while (dbcursor.hasNext()) {
			DBObject obj = dbcursor.next();// 遍历每一条数据
			HashMap h =(HashMap) obj.toMap();// 变成了java的map对象，随便操作吧。 
			for (Object s:h.keySet()){
				System.out.println(h.get(s));
			}
			for (Object s:h.values()){
				System.out.println(s);
			}
			
			System.out.println("new records");
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}
