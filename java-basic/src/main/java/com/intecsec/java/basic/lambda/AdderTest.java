package com.intecsec.java.basic.lambda;

import java.util.HashMap;
import java.util.Map;

public class AdderTest {

	public static void main(String[] args) {
		Map<Integer, Integer> map = new HashMap<>();
		Map<Integer, Integer> mape = new HashMap<>();

		Map<Integer, Integer> bucket= new HashMap<>();
		bucket.put(30, 0);
		bucket.put(70, 0);

		for(int i = 100000000; i < 100000100; i++) {
			int hashCode = String.valueOf(i).hashCode();
			System.out.println(hashCode);
			if(hashCode % 100 < 30) {
				bucket.put(30, bucket.get(30) + 1);
			}
			if(hashCode % 100 >= 30) {
				bucket.put(70, bucket.get(70) + 1);
			}

			if(map.containsKey(hashCode)) {
				if(mape.containsKey(hashCode)) {
					mape.put(hashCode, mape.get(hashCode) + map.get(hashCode));
				} else {
					mape.put(hashCode, map.get(hashCode));
				}
			}

			map.put(hashCode, 1);
		}

		for(Map.Entry<Integer, Integer> entry : bucket.entrySet()) {
			System.out.println(entry.getKey() + ":" + entry.getValue());
		}

		System.out.println(mape);
	}

	public static void Adder() {
		Adder  c1 =
				x ->
				{
					x++;
					return x;
				};

		Adder  c2 =
				x ->
				{
					if(x>0)
						return x+1;
					return x;
				};
	}

}
