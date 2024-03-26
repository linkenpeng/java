package com.intecsec.java.basic.datatype;

import com.intecsec.java.util.DateUtils;
import com.intecsec.java.util.JsonUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JsonCompressTest {

	public static final String tagsSpe = "|";
	public static final String tagKvSpe = ":";

	public static String tempKeyMapStr;
	public static String tempWuidData;

	public static void main(String[] args) {
		String wuid = "";
		String wuidData = "{\"c1006006\":\"距今1年以上\",\"c1300050\":\"\",\"c1300097\":\"\",\"c1300145\":\"\",\"c1300146\":\"\",\"c1300147\":\"\",\"c1300148\":\"\",\"c1300149\":\"\",\"c1300150\":\"\",\"c1300151\":\"\",\"c1300152\":\"\",\"c1300156\":\"\",\"c1300157\":\"\",\"c1300158\":\"Y\",\"c1300159\":\"\",\"c1300160\":\"\",\"c1300161\":\"\",\"c1300162\":\"Y\",\"c1300163\":\"\",\"c1300166\":\"\",\"c1300167\":\"\",\"c1300411\":\"\",\"c1300415\":\"\",\"c1300416\":\"\",\"c1300417\":\"\",\"c1300418\":\"\",\"c1300421\":\"\",\"c1300423\":\"Y\",\"c0302004\":\"\",\"c0302005\":\"\",\"c0302006\":\"PotentialVIP\",\"c0302007\":\"低\",\"c0302008\":\"低\",\"c0302009\":\"\",\"c0302010\":\"\",\"c0302011\":\"\",\"c0302012\":\"\",\"c0302013\":\"\",\"c0302014\":\"\",\"c0302015\":\"\",\"c0302016\":\"\",\"c0302017\":\"\",\"c0302018\":\"\",\"c0302019\":\"\",\"c0302020\":\"\",\"c0302021\":\"\",\"c0302022\":\"\",\"c0302023\":\"\",\"c0302024\":\"\",\"c0302025\":\"\",\"c0302026\":\"\",\"c0302027\":\"\",\"c0302028\":\"\",\"c0302029\":\"\",\"c0302030\":\"\",\"c0302031\":\"Y\",\"c0302032\":\"Y\",\"c0302033\":\"\",\"c0302034\":\"\",\"c0302035\":\"\",\"c0302036\":\"\",\"c0302037\":\"\",\"c0302038\":\"\",\"c0302039\":\"\"}";
		writeDataToRedisWithVersion(wuid, wuidData);
	}

	public static void writeDataToRedisWithVersion(String wuid, String wuidData) {
		Map<String,String> map = JsonUtils.parseStringMap(wuidData, String.class);
		Map<String, Integer> keyMap = new HashMap<>();
		int i = 1;
		String columnStr = "";
		String columnVersion = "version_" + DateUtils.formatDate(new Date(), DateUtils.YYYYMMDD);
		String finalRedisValue = columnVersion + tagsSpe;
		for(Map.Entry<String, String> entry : map.entrySet()) {
			keyMap.put(entry.getKey(), i);
			String value = StringUtils.isNotBlank(entry.getValue()) ? entry.getValue() : "";
			columnStr += entry.getKey() + tagsSpe;
			finalRedisValue += value + tagsSpe;
			i++;
		}
		columnStr = StringUtils.stripEnd(columnStr, tagsSpe);
		finalRedisValue = StringUtils.stripEnd(finalRedisValue, tagsSpe);
		tempKeyMapStr = JsonUtils.toJson(keyMap);
		tempWuidData = finalRedisValue;
		System.out.println("OriginData: " + JsonUtils.toJson(map));
		System.out.println("columnStr: " + columnStr);
		System.out.println("writeToRedisData  : " + tempWuidData);
		// 7天
		// int expireTime = 604800;
		// redis.set("keyMap", tempKeyMapStr, expireTime);  // TODO 将keyMap写入Redis
		// redis.set(wuid, tempWuidData, expireTime);       // TODO 将wuid对应的标签数据写入Redis
	}

	public static void writeDataToRedis(String wuid, String wuidData) {
		Map<String,String> map = JsonUtils.parseStringMap(wuidData, String.class);
		Map<String, Integer> keyMap = new HashMap<>();
		int i = 1;
		String finalRedisValue = "";
		for(Map.Entry<String, String> entry : map.entrySet()) {
			keyMap.put(entry.getKey(), i);
			String value = StringUtils.isNotBlank(entry.getValue()) ? entry.getValue() : "";
			finalRedisValue += i + tagKvSpe + value + tagsSpe;
			i++;
		}
		finalRedisValue = StringUtils.stripEnd(finalRedisValue, tagsSpe);
		tempKeyMapStr = JsonUtils.toJson(keyMap);
		tempWuidData = finalRedisValue;
		System.out.println("writeToRedisKeyMap: " + tempKeyMapStr);
		System.out.println("writeToRedisData  : " + tempWuidData);
		// 7天
		// int expireTime = 604800;
		// redis.set("keyMap", tempKeyMapStr, expireTime);  // TODO 将keyMap写入Redis
		// redis.set(wuid, tempWuidData, expireTime);       // TODO 将wuid对应的标签数据写入Redis
	}

	public static Map<String,String> readDataFromRedis(String wuid) {
		String keyMapStr = tempKeyMapStr; // redis.get("keyMap"); // TODO 从redis获取keyMap
		String wuidData = tempWuidData;  //  redis.get(wuid);     // TODO 从redis获取wuid对应的标签数据
		Map<String, Integer> keyMapOrigin = JsonUtils.parseStringMap(keyMapStr, Integer.class);
		Map<Integer, String> keyMap = MapUtils.invertMap(keyMapOrigin);
		String[] tags = StringUtils.split(wuidData, tagsSpe);
		Map<String,String> finalResultMap = new HashMap<>();
		for(int i = 0; i < tags.length; i++) {
			String tag = tags[i];
			if(StringUtils.isEmpty(tag)) {
				continue;
			}
			String[] tagKV = StringUtils.split(tag, tagKvSpe);
			Integer key = Integer.valueOf(tagKV[0]);
			String val = tagKV.length > 1 ? tagKV[1] : "";
			finalResultMap.put(keyMap.get(key), val);
		}
		System.out.println("模拟从Redis获取数据，还原数据为  : " + JsonUtils.toJson(finalResultMap));
		return finalResultMap;
	}
}
