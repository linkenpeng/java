package com.intecsec.java.basic.datatype;

public class GenSqlTest {

	public static void main(String[] args) {
		delSql();
	}

	public static  void genSql() {
		for(int i = 0; i < 64; i++) {
			//String sql = "SELECT * FROM user_cart_new_" + i+ " WHERE delete_mark=0 AND item_type=21 AND (activity_id=0 OR activity_id is null)  ";
			// String sql = "ALTER TABLE `cart_db`.`user_cart_new_"+i+"` ADD COLUMN `key_channel` varchar(32) DEFAULT '' COMMENT '记录销售渠道，比如：小店主唯一标识' AFTER `activity_level`, ADD COLUMN `key_store` varchar(32) DEFAULT '' COMMENT '记录门店唯一标识' AFTER `key_channel`; ";
			String sql = "ALTER TABLE `user_cart_"+i+"` ADD COLUMN `activity_key` varchar(16) NULL DEFAULT '' COMMENT '活动key' AFTER `activity_level`,ADD COLUMN `activity_rule_code` varchar(32) NULL DEFAULT '' COMMENT '活动的规则类型编码' AFTER `activity_key`;";
			// String sql = "ALTER TABLE `cart_db`.`user_cart_new_"+i+"` CHANGE COLUMN `key_channel` `key_sales` varchar(32) DEFAULT '' COMMENT '记录分销商，比如：小店主唯一标识'; ";
			
			System.out.println(sql);
			//System.out.println("union all");
		}
	}

	public static  void delSql() {
		for(int i = 0; i < 64; i++) {
			String sql = "update user_cart_" + i+ " set delete_mark=1 WHERE key_sales is null; ";
			System.out.println(sql);
		}
	}

}
