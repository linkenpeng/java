package com.intecsec.java.basic.zookeeper;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.CloseableUtils;
import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

import java.util.List;

/**
 * @author Peter.Peng
 * @date 2021/2/7
 */
public class ZooKeeperTest {

	public static final String ZK_ADDRESS = "127.0.0.1:2181";

	public static void main(String[] args) {
		//updateNode();
		updateNodeAsync();
		readNode();
	}

	public static void deleteNode() {
		CuratorFramework client = getClient();
		try {
			client.start();
			String data = "hello world";
			byte[] payload = data.getBytes("UTF-8");
			String zkPath = "/test/CRUD/remoteNode-1";
			client.delete().forPath(zkPath);

			String parentPath = "/test";
			List<String> childrens = client.getChildren().forPath(parentPath);
			for(String children : childrens) {
				System.out.println(children);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseableUtils.closeQuietly(client);
		}
	}

	public static void updateNodeAsync() {
		CuratorFramework client = getClient();
		try {
			client.start();
			String data = "hello world async";
			byte[] payload = data.getBytes("UTF-8");
			String zkPath = "/test/CRUD/remoteNode-1";

			AsyncCallback.StringCallback callback = (i, s, o, s1) -> {
				System.out.println(i);
				System.out.println(s);
				System.out.println(o);
				System.out.println(s1);
			};

			client.setData().inBackground(callback).forPath(zkPath, payload);

			Thread.sleep(10000);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseableUtils.closeQuietly(client);
		}
	}

	public static void updateNode() {
		CuratorFramework client = getClient();
		try {
			client.start();
			String data = "hello world";
			byte[] payload = data.getBytes("UTF-8");
			String zkPath = "/test/CRUD/node-1";
			client.setData()
					.forPath(zkPath, payload);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseableUtils.closeQuietly(client);
		}
	}

	public static void createNode() {
		CuratorFramework client = getClient();
		client.start();
		String data = "hello";
		try {
			byte[] payload = data.getBytes("UTF-8");
			String zkPath = "/test/CRUD/node-1";
			client.create().creatingParentContainersIfNeeded()
					.withMode(CreateMode.PERSISTENT)
					.forPath(zkPath, payload);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseableUtils.closeQuietly(client);
		}
	}

	public static void readNode() {
		CuratorFramework client = getClient();
		try {
			client.start();
			String zkPath = "/test/CRUD/node-1";
			Stat stat = client.checkExists().forPath(zkPath);
			if(null != stat) {
				byte[] payload = client.getData().forPath(zkPath);
				String data = new String(payload, "UTF-8");
				System.out.println(data);
				String parentPath = "/test";
				List<String> childrens = client.getChildren().forPath(parentPath);
				for(String children : childrens) {
					System.out.println(children);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseableUtils.closeQuietly(client);
		}

	}

	public static CuratorFramework getClient() {
		CuratorFramework client = CuratorFrameworkFactory.newClient(ZK_ADDRESS, new ExponentialBackoffRetry(1000, 3));
		return client;
	}

	public static CuratorFramework getClient(int sessionTimeOut, int connectionTimeOut) {
		CuratorFramework client = CuratorFrameworkFactory.newClient(ZK_ADDRESS, sessionTimeOut, connectionTimeOut, new ExponentialBackoffRetry(1000, 3));
		return client;
	}
}
