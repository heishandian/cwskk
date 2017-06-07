package com.yaoli.util;

import com.yaoli.beans.RemoteControl;
import com.yaoli.config.SystemConfig;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;
import java.net.UnknownHostException;

import org.apache.log4j.Logger;

public class SendUpdateSewageIdTaskClient {

	private static Logger logger = Logger.getLogger(SendUpdateSewageIdTaskClient.class);

	public void startClient(){
		String host = "115.159.52.175";
		int port = 8990;
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		
		//semantic react
		try {
			Bootstrap b = new Bootstrap();
			b.group(workerGroup);
			b.channel(NioSocketChannel.class);
			b.option(ChannelOption.SO_KEEPALIVE, true);
			b.handler(new ChannelInitializer<Channel>() {
				@Override
				protected void initChannel(Channel ch) throws Exception {
					ch.pipeline().addLast(new SendUpdateSewageIdTask());
				}
			});
			
			ChannelFuture f = b.connect(host, port);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws IOException, InterruptedException {
		SendUpdateSewageIdTaskClient client = new SendUpdateSewageIdTaskClient();
		client.sendSewageid(130);
	}
	
	public static void sendSewageid(int sewageid) throws InterruptedException{
		try {
			//Map<String, String> map = CustomPropertyConfigurer.getProperties();
			String ip = SystemConfig.others.get("sendipconfig");
			String port = SystemConfig.others.get("sendipport");
			Socket socket =new Socket(ip,Integer.valueOf(port));
			OutputStream os=socket.getOutputStream();
			InputStream is=socket.getInputStream();

			byte[] req = new byte[3];
			int a = sewageid/127;
			int b = sewageid%127;
			req[0] = (byte)35;
			req[1] = (byte)a;
			req[2] = (byte)b;

			//接收服务器的相应
			byte [] reply = new byte[2];
			for (int j = 0; j < 2; j++) {
				os.write(req);
				os.flush();

				//读两次
				is.read(reply);
				Thread.sleep(100L);
				is.read(reply);
				if(reply[0] == 35 || reply[1] == 35){
					//重新再发一次，否则
					break;
				}else {
					Thread.sleep(10000L);
				}
			}
			socket.shutdownOutput();
			is.close();
			os.close();
			socket.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 无锡站点水泵开关
	 * @throws InterruptedException
     */
	public static void controlEquip(RemoteControl rc) throws InterruptedException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
		try {
			Class<?> remoteControlClass = Class.forName(rc.getClass().getName());

			StringBuilder sb = new StringBuilder("");

			for(int i = 5; i >= 1 ; i--){
				Method method = remoteControlClass.getDeclaredMethod("getEquip"+i);
				sb.append(method.invoke(rc).toString());
			}


			byte[] req = new byte[4];
			int a = rc.getSewageid()/127;
			int b = rc.getSewageid()%127;
			req[0] = (byte)35;
			req[1] = (byte)a;
			req[2] = (byte)b;
			req[3] = Byte.valueOf(sb.toString(),2);

			//Map<String, String> map = CustomPropertyConfigurer.getProperties();
			String ip = SystemConfig.others.get("sendipconfig_equip");
			String port = SystemConfig.others.get("sendipport_equip");
			//打印 ip 和 port
			logger.info(ip);
			logger.info(port);
			Socket socket =new Socket(ip,Integer.valueOf(port));
			OutputStream os=socket.getOutputStream();
			InputStream is=socket.getInputStream();

			//接收服务器的相应
			byte [] reply = new byte[2];
			for (int j = 0; j < 2; j++) {
				os.write(req);
				os.flush();

				//读两次
				is.read(reply);
				//Thread.sleep(100L);
				//is.read(reply);
				if(reply[0] == 35 || reply[1] == 35){
					//重新再发一次，否则
					break;
				}else {
					Thread.sleep(10000L);
				}
			}
			socket.shutdownOutput();
			is.close();
			os.close();
			socket.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
