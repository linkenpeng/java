package com.intecsec.java.util;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.*;
import org.apache.commons.lang3.StringUtils;

import java.io.OutputStreamWriter;
import java.io.Reader;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * json工具类
 */
public class JsonUtils {

	private static final Gson gson;
	private static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd HH:mm:ss[.SSS]";
	private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DEFAULT_DATE_PATTERN);

	static {
		gson = createGson();
	}

	public static Gson createGson() {
		GsonBuilder gb = new GsonBuilder();
		// 禁止html转义
		gb.disableHtmlEscaping();
		// 处理下划线分割的json字符串（生成json字符串时候将驼峰转化为下划线，解析json时候将下划线转为驼峰）
		gb.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
		// 设置json时间格式
		// gb.setDateFormat("yyyy-MM-dd HH:mm:ss");

		// Date统一用long（毫秒）来表示 -- 序列化、反序列化都是
		gb.registerTypeAdapter(Date.class, new DateSerializer()).setDateFormat(DateFormat.LONG);
		gb.registerTypeAdapter(Date.class, new DateDeserializer()).setDateFormat(DateFormat.LONG);

		return gb.create();
	}

	/**
	 * 将对象转成json字符串
	 */
	public static final String toJson(Object src) {
		return gson.toJson(src);
	}

	/**
	 * json反序列化成指定类型的对象
	 */
	public static final <T> T parseJson(String jsonStr, Class<T> tClass) {
		T obj = gson.fromJson(jsonStr, tClass);
		return obj;
	}

	/**
	 * json反序列化成指定类型的List对象
	 */
	public static final <T> List<T> parseListJson(String jsonStr, Class<T> tClass) {
		List<T> list = gson.fromJson(jsonStr, new ArrayT<T>(tClass).getType());
		return list;
	}

	/**
	 * json反序列化成Map对象，key为String，value为指定类型
	 */
	public static final <T> Map<String, T> parseStringMap(String jsonStr, Class<T> tClass) {
		Map<String, T> map = gson.fromJson(jsonStr, new MapT<String, T>(String.class, tClass).getType());
		return map;
	}

	/**
	 * json反序列化成Map对象，key为Integer，value为指定类型
	 */
	public static final <T> Map<Integer, T> parseIntegerMap(String jsonStr, Class<T> tClass) {
		Map<Integer, T> map = gson.fromJson(jsonStr, new MapT<Integer, T>(Integer.class, tClass).getType());
		return map;
	}

	/**
	 * json反序列化成特定泛型的对象
	 * 
	 * @deprecated 请先确定其他指定Class的方法是否满足需求，例如：parseListJson(String, Class)
	 */
	public static final <T> T parseJson(String jsonStr, Type typeOfT) {
		return gson.fromJson(jsonStr, typeOfT);
	}

	// public static <T> T parseListJson(String listStr, Type type) {
	// T objList = gson.fromJson(listStr, type);
	// return objList;
	// }

	/**
	 * @deprecated 建议使用parseListJson(..)方法
	 */
	public static final JsonArray parseJsonArray(String jsonStr) {
		return new JsonParser().parse(jsonStr).getAsJsonArray();
	}

	/** @deprecated */
	public static final void toJson(Object t, Type typeOfSrc, OutputStreamWriter writer) {
		gson.toJson(t, typeOfSrc, writer);
	}

	/** @deprecated */
	public static final void toJson(Object t, OutputStreamWriter writer) {
		gson.toJson(t, writer);
	}

	/** @deprecated */
	public static final <T> T parseJson(Reader json, Class<T> typeOfT) {
		return gson.fromJson(json, typeOfT);
	}

	/**
	 * Gson序列化Date类型
	 */
	public static final class DateSerializer implements JsonSerializer<Date> {

		@Override
		public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext context) {
			return new JsonPrimitive(src.getTime());
		}

	}

	/**
	 * Gson反序列化Date类型
	 */
	public static final class DateDeserializer implements JsonDeserializer<Date> {

		@Override
		public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
				throws JsonParseException {
			JsonPrimitive jsonPrimitiveType = json.getAsJsonPrimitive();
			if (jsonPrimitiveType.isString()) {
				String jsonDataString = jsonPrimitiveType.getAsString();
				// 是一个数字，按照约定进行转换 long
				if (StringUtils.isNumeric(jsonDataString)) {
					return new Date(jsonPrimitiveType.getAsLong());
				}

				// 使用固定日期时间格式转换 string
				try {
					LocalDateTime localDateTime = LocalDateTime.parse(jsonDataString, DATE_TIME_FORMATTER);
					return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
				} catch (Exception e) {
					throw e;
				}
			}

			// 约定的转换模式: long
			return new Date(jsonPrimitiveType.getAsLong());
		}

	}

	/**
	 * ArrayList 泛型识别器
	 */
	private static final class ArrayT<T> {

		private Class<T> clz;

		public ArrayT(Class<T> clz) {
			this.clz = clz;
		}

		public Type getType() {
			return new ParameterizedType() {
				@Override
				public Type[] getActualTypeArguments() {
					return new Type[] { clz };
				}

				@Override
				public Type getRawType() {
					return ArrayList.class;
				}

				@Override
				public Type getOwnerType() {
					return null;
				}
			};
		}

	}

	/**
	 * Map 泛型识别器
	 */
	private static final class MapT<K, V> {

		private Class<K> clzK;
		private Class<V> clzV;

		public MapT(Class<K> clzK, Class<V> clzV) {
			this.clzK = clzK;
			this.clzV = clzV;
		}

		public Type getType() {
			return new ParameterizedType() {
				@Override
				public Type[] getActualTypeArguments() {
					return new Type[] { clzK, clzV };
				}

				@Override
				public Type getRawType() {
					return Map.class;
				}

				@Override
				public Type getOwnerType() {
					return null;
				}
			};
		}

	}

	// ####
	// ####
	// ####

	/**
	 * @deprecated 这个方法是为旧代码而保留的（fastjson）<br>
	 *             <span style="font-weight: bold;color: red;">除非</span>你确定<span
	 *             style="color: red;">依然需要</span>使用旧的fastjson序列化、反序列化<br>
	 *             <span style="font-weight: bold;color:
	 *             blue;">否则</span>请使用其他<span style="color:
	 *             blue;">未被标记不推荐使用</span>的方法（gson）
	 */
	public static final String toJSONString(Object object) {
		return JSONObject.toJSONString(object);
	}

	/**
	 * @deprecated 这个方法是为旧代码而保留的（fastjson）<br>
	 *             <span style="font-weight: bold;color: red;">除非</span>你确定<span
	 *             style="color: red;">依然需要</span>使用旧的fastjson序列化、反序列化<br>
	 *             <span style="font-weight: bold;color:
	 *             blue;">否则</span>请使用其他<span style="color:
	 *             blue;">未被标记不推荐使用</span>的方法（gson）
	 */
	public static <T> T parseObject(String text, Class<T> clazz) {
		return JSONObject.parseObject(text, clazz);
	}

	/**
	 * @deprecated 这个方法是为旧代码而保留的（fastjson）<br>
	 *             <span style="font-weight: bold;color: red;">除非</span>你确定<span
	 *             style="color: red;">依然需要</span>使用旧的fastjson序列化、反序列化<br>
	 *             <span style="font-weight: bold;color:
	 *             blue;">否则</span>请使用其他<span style="color:
	 *             blue;">未被标记不推荐使用</span>的方法（gson）
	 */
	public static <T> List<T> parseArray(String text, Class<T> clazz) {
		return JSONObject.parseArray(text, clazz);
	}

}
