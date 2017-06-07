package com.yaoli.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.security.NoSuchAlgorithmException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.SystemDefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONObject;
import com.yaoli.vo.VideoUrlTokenVO;


public class GetVideoUrlTokenUtil {
	 //61.160.70.100:10100
/*	 String postUrl = "http://61.160.70.100:9580/nmc/rest/realstream?sign=";
	 String uid = "admin";
	 String password = "admin";
	 String epid = "wxsd";
	 String puid = "201115200252708495";
	 String dateString =String.valueOf(System.currentTimeMillis()/1000L);//String.valueOf(new Date().getTime());
*/	 
	private VideoUrlTokenVO videoUrlTokenVO;
	
	public VideoUrlTokenVO getVideoUrlTokenVO() {
		return videoUrlTokenVO;
	}

	public void setVideoUrlTokenVO(VideoUrlTokenVO videoUrlTokenVO) {
		this.videoUrlTokenVO = videoUrlTokenVO;
	}

	public String getURL(){
		String auth = "";
		String sign = "";
		
		try {
			auth = getAuthorization();
			sign = getSign();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		String url = videoUrlTokenVO.getPostUrl()+sign;
		
		@SuppressWarnings("deprecation")
		HttpClient client = new SystemDefaultHttpClient(); 
		
		HttpPost post = new HttpPost(url);
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("puid", videoUrlTokenVO.getPuid());
		jsonObject.put("idx", "0");
		jsonObject.put("videoformat", "rtmp");
		jsonObject.put("stream", "0");
		
		JSONObject mainObject = new JSONObject();
		mainObject.put("request", jsonObject);
		
		HttpEntity entity = new StringEntity(mainObject.toJSONString(),"UTF-8");
		post.setEntity(entity);
		
		post.setHeader("Accept", "application/json");
		post.setHeader("Content-Type", "application/json;charset=utf-8");
		post.setHeader("Authorization", auth);
		
		String result = "";
		HttpResponse response = null;
		String responeseEntity = "";
		
		try {
			response = client.execute(post);
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				responeseEntity = EntityUtils.toString(response.getEntity(), "utf-8");
				
				JSONObject a = JSONObject.parseObject(responeseEntity);
				JSONObject b = a.getJSONObject("response");
				result = b.getString("url");
			}
			
			result = java.net.URLDecoder.decode(result,"utf-8");
		} catch (ConnectException e) {
			result = e.getMessage();
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			result = e.getMessage();
			e.printStackTrace();
		} catch (IOException e) {
			result = e.getMessage();
			e.printStackTrace();
		}

		return result;
	}
	
	private String getSign() throws NoSuchAlgorithmException, UnsupportedEncodingException{
		String pwdMd5 = CommonUtil.MD5(videoUrlTokenVO.getPassword());
		String data = CommonUtil.MD5(videoUrlTokenVO.getEpid()+videoUrlTokenVO.getUid()+pwdMd5+videoUrlTokenVO.getDateString());
        return data;
	}
	
	private String getAuthorization() throws Exception{
		String data = videoUrlTokenVO.getEpid()+"_"+videoUrlTokenVO.getUid()+"_"+videoUrlTokenVO.getDateString();
		String str = new String(CommonUtil.encodeBase64(data.getBytes()));
		return str;
	}
	
}
