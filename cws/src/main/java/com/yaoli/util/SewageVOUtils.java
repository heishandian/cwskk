package com.yaoli.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.yaoli.config.SystemConfig;
import com.yaoli.config.siteFunction.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import com.yaoli.beans.DetectionData;
import com.yaoli.beans.RunData;
import com.yaoli.beans.Sewage;
import com.yaoli.vo.SewageMonitorVO;

public class SewageVOUtils {
	/**
	 * 实时监控，弹出的信息框
	 * @param se SewageMonitorVO
	 * @param de DetectionData
	 * @param sewageDayTotalWaterFlow sewageDayTotalWaterFlow
	 * @return HTML
	 * @throws ClassNotFoundException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 */
	public static String getSewageHTML2(String contextPath,SewageMonitorVO se,DetectionData de,RunData runData,Sewage sewage,Double sewageDayTotalWaterFlow) throws ClassNotFoundException, SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException{
		StringBuilder sb = new StringBuilder();
		sb.append(getWapper("<a href='"+contextPath+"/monitor/sewagemonitor.do?sewageid="+se.getSewageid()+"'>"+se.getShortTitle()+"</a>"));


		Class<?> rundataClass = Class.forName("com.yaoli.beans.RunData");
		Class<?> sewageMonitorVoClass = Class.forName("com.yaoli.vo.SewageMonitorVO");
		Class<?> detectionDataClass = Class.forName("com.yaoli.beans.DetectionData");

		//添加 滚动条
		sb.append("<div style='width:260px;height:300px; overflow-y:scroll'>");

		//获取运营编号
		sb.append("运营编号："+se.getOperationnum()+"</br>");

		/**
		 * 以下 是设备信息参数 组合
		 */
		// 该站点拥有设备 1 - 5
		if(SystemConfig.site instanceof ImplEquip_1_to_5){

			String equipmentName;
			if(runData != null){
				for (int i = 1; i <= 5; i++) {
					Method method = rundataClass.getDeclaredMethod("getEquipment"+i+"state"); //getEquipment11state
					Object stateObject = method.invoke(runData);
					int state = (stateObject == null ? -1:Integer.valueOf(stateObject.toString()));
					sb.append(setEquipmentState(i,sewageMonitorVoClass,se,state));
				}
			}else{
				//如果该站点没有数据，那么 这个站点的所有设备显示为停止
				for (int i = 1; i <= 5; i++) {
					equipmentName = SystemConfig.equips.get("equipment"+i+"name");
					sb.append(getWapper(equipmentName+":停止"));
				}
			}
		} else if(SystemConfig.site instanceof ImplEquip_6_to_21){
			String equipmentName;
			if(runData != null){
				for (int i = 6; i <= 21; i++) {
					Method method = rundataClass.getDeclaredMethod("getEquipment"+i+"state"); //getEquipment11state
					Object stateObject = method.invoke(runData);
					int state = (stateObject == null ? -1:Integer.valueOf(stateObject.toString()));
					if(state != -1){//表示 state不为空，即有该数据;设置设备名称。
						//Method getRunDataState = sewageMonitorVoClass.getDeclaredMethod("getEquipment"+i+"Name");
						equipmentName = SystemConfig.equips.get("equipment"+i+"name");
						if (i == 6 || i == 7) {//两个液位计
							if(state == 8){
								sb.append(getWapper(getRedWapper(equipmentName+"：低低")));
							}else if(state == 1){
								sb.append(getWapper(getRedWapper(equipmentName+"：高高")));
							}else {
								if(state == 2){
									sb.append(getWapper(equipmentName+":高"));
								}else {//state == 4
									sb.append(getWapper(equipmentName+":低"));
								}
							}
						}else {//其他设备
							if(state == 3){
								sb.append(getWapper(getRedWapper(equipmentName+"：故障")));
							}else {
								if(state == 1){
									sb.append(getWapper(equipmentName+":运行"));
								}else {//state == 2
									sb.append(getWapper(equipmentName+":停止"));
								}
							}
						}
					}
				}
			}else{
				//如果该站点没有数据，那么 这个站点的所有设备显示为停止
				for (int i = 6; i <= 21; i++) {
					equipmentName = SystemConfig.equips.get("equipment"+i+"name");
					sb.append(getWapper(equipmentName+":停止"));
				}
			}

			//邗江 站点
		}else if(SystemConfig.site instanceof ImplEquip_1_to_21){

			String equipmentName;
			if(runData != null){
				for (int i = 1; i <= 21; i++) {
					Method method = rundataClass.getDeclaredMethod("getEquipment"+i+"state"); //getEquipment11state
					Object stateObject = method.invoke(runData);
					int state = (stateObject == null ? -1:Integer.valueOf(stateObject.toString()));
					sb.append(setEquipmentState(i,sewageMonitorVoClass,se,state));
				}
			}else{
				//如果该站点没有数据，那么 这个站点的所有设备显示为停止
				for (int i = 1; i <= 21; i++) {
					equipmentName = SystemConfig.equips.get("equipment"+i+"name");
					sb.append(getWapper(equipmentName+":停止"));
				}
			}
		}
		/**
		 * 设备信息组合 判断结束
		 */




		/**
		 * 以下是 水质参数信息 组合
		 */
		if(SystemConfig.site instanceof ImplDetect_1_to_5){
			String detectionName = null;
			if(de != null){
				for (int i = 1; i <= 5; i++) {
					//在这里添加不进行判断的地方
					if(i == 4){
						Method getDetection = detectionDataClass.getDeclaredMethod("getDetection"+i);
						Double detection = getDetection.invoke(de) == null?null:Double.valueOf(getDetection.invoke(de).toString());
						detectionName = SystemConfig.detections.get("detection"+i+"name");
						if(detection == 1){
							//fix 液位是没有异常的说法的 去掉警报说法
							sb.append(getWapper((detectionName+":高")));
						}else {
							sb.append(getWapper((detectionName+":低")));
						}

					}else {
						sb.append(getDetectionString(i, sewageMonitorVoClass, detectionDataClass, detectionName, se, de));
					}
				}
				//添加总累计水量
				Method getDetection = detectionDataClass.getDeclaredMethod("getDetection9");
				Double detection = getDetection.invoke(de) == null?null:Double.valueOf(getDetection.invoke(de).toString());
				detectionName = SystemConfig.detections.get("detection9name");
				sb.append(getWapper((detectionName+":"+detection)));
			}else {
				//如果这个站点没有水质信息，那么这个站点显示为 0
				for (int i = 1; i <= 5; i++) {
					if(i == 4){
						detectionName = SystemConfig.detections.get("detection"+i+"name");
						sb.append(getWapper(detectionName+":低 "));
					}else {
						detectionName = SystemConfig.detections.get("detection"+i+"name");
						sb.append(getWapper(detectionName+" : 0.00"));
					}
				}
				//添加总累计水量 NO:112
				detectionName = SystemConfig.detections.get("detection9name");
				sb.append(getWapper(detectionName+" : 0.00"));
			}
		} else if(SystemConfig.site instanceof ImplDetect_1_to_5_10_to_15){
			String detectionName = null;
			if(de != null){
				for (int i = 1; i <= 5; i++) {
					//在这里添加不进行判断的地方 江都系统detection4废弃
					if(i != 4){
						sb.append(getDetectionString(i, sewageMonitorVoClass, detectionDataClass, detectionName, se, de));
					}
				}
				for (int i = 10; i <= 15; i++) {
					//在这里添加不进行判断的地方 江都系统detection4废弃
					if(i != 4){
						sb.append(getDetectionString(i, sewageMonitorVoClass, detectionDataClass, detectionName, se, de));
					}
				}
			}else {
				//如果这个站点没有水质信息，那么这个站点显示为 0
				for (int i = 1; i <= 5; i++) {
					if(i != 4){
						detectionName = SystemConfig.detections.get("detection"+i+"name");
						sb.append(getWapper(detectionName+" : 0.00"));
					}else {
						detectionName = SystemConfig.detections.get("detection"+i+"name");
						sb.append(getWapper(detectionName+":低 "));
					}
				}
				for (int i = 10; i <= 15; i++) {
					detectionName = SystemConfig.detections.get("detection"+i+"name");
					sb.append(getWapper(detectionName+" : 0.00"));
				}
			}
		}
		/**
		 * 水质参数信息 组合结束
		 */


		if(de != null && sewageDayTotalWaterFlow != null){
			sb.append(getWapper("日处理水量："+new java.text.DecimalFormat("0.00").format(sewageDayTotalWaterFlow)+"立方米"));
			if(se.getReducecod() != null){
				sb.append(getWapper("日削减COD量："+new java.text.DecimalFormat("0.00").format(sewageDayTotalWaterFlow*se.getReducecod())+"克"));
			}
			if(se.getReducenh3n() != null){
				sb.append(getWapper("日削减NH3-N量："+new java.text.DecimalFormat("0.00").format(sewageDayTotalWaterFlow*se.getReducenh3n())+"克"));
			}
			if(se.getReducep() != null){
				sb.append(getWapper("日削减总TP量："+new java.text.DecimalFormat("0.00").format(se.getReducep()*sewageDayTotalWaterFlow)+"克"));
			}
		}else {//否则 全部设置为0
			sb.append(getWapper("日处理水量："+new java.text.DecimalFormat("0.00").format(0)+" 立方米"));
			sb.append(getWapper("日削减COD量："+new java.text.DecimalFormat("0.00").format(0)+" 克"));
			sb.append(getWapper("日削减NH3-N量："+new java.text.DecimalFormat("0.00").format(0)+" 克"));
			sb.append(getWapper("日削减总TP量："+new java.text.DecimalFormat("0.00").format(0)+" 克"));
		}





		Subject subject = SecurityUtils.getSubject();
		List<String> roleList = new ArrayList<String>();
		roleList.add("7");//拥有管理员
		roleList.add("8");//用用超级管理员角色
		if(subject.hasAllRoles(roleList)){

		}else {

		}
		//String autoflow = ;<div style="width:260px;height:120px; overflow-y:scroll; border:1px solid;"> 这里是你要显示的内容 </div>

		sb.append("</div>");
//		//如果是江都系统，需要添加滚动条
//		if(SystemConfig.site instanceof ImplEquip_6_to_21 && SystemConfig.site instanceof  ImplDetect_1_to_5){
//			sb.append("</div>");
//		}

		return sb.toString();
	}


	private static String setEquipmentState(int i ,Class<?> sewageMonitorVoClass,SewageMonitorVO se,int state){

		if(state != -1) {//表示 state不为空，即有该数据;设置设备名称。
			try {
				Method getRunDataState = sewageMonitorVoClass.getDeclaredMethod("getEquipment"+i+"Name");
				String equipmentName = String.valueOf(getRunDataState.invoke(se));
				if(state == 3){
					return getWapper(getRedWapper(equipmentName+":故障"));
				}else {
					if(state == 1){
						return getWapper(equipmentName+":运行");
					}else {//state == 2
						return getWapper(equipmentName+":停止");
					}
				}
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 用于设置 水质值
     */
	public static String getDetectionString(int i,Class<?> sewageMonitorVoClass,Class<?> detectionDataClass,String detectionName,SewageMonitorVO se,DetectionData de){
		try {
			//设置 detection的下限 等
			Method getDetectiondl = sewageMonitorVoClass.getDeclaredMethod("getDetection"+i+"dl");
			Double detectiondlValue = getDetectiondl.invoke(se) == null?null:Double.valueOf(getDetectiondl.invoke(se).toString());


			//设置 detectiondl上限 等
			Method getDetectionul = sewageMonitorVoClass.getDeclaredMethod("getDetection"+i+"ul");
			Double detectionulValue = getDetectionul.invoke(se) == null?null:Double.valueOf(getDetectionul.invoke(se).toString());

			//获取 detection的值
			Method getDetection = detectionDataClass.getDeclaredMethod("getDetection"+i);
			Double detection = getDetection.invoke(de) == null?null:Double.valueOf(getDetection.invoke(de).toString());

			if (detection != null) {
				detectionName = SystemConfig.detections.get("detection"+i+"name");
				if(detectionulValue != null && detectiondlValue !=null){
					if (detectiondlValue > detection || detectionulValue < detection) {
						if(i != 6){//如果 i !=  6  流量按正常比较
							return getWapper(getOrangeWapper(detectionName+"："+new java.text.DecimalFormat("0.00").format(detection)));
						}else {//否则，无须发布
							return getWapper(detectionName+"："+new java.text.DecimalFormat("0.00").format(detection));
						}
					}else {
						return getWapper(detectionName+"："+new java.text.DecimalFormat("0.00").format(detection));
					}
				}else {
					return getWapper(detectionName+"："+new java.text.DecimalFormat("0.00").format(detection));
				}
			}
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

		return "";
	}




	public static String getWapper(String content){
		String wapper = "<font face='Arial'>"+content+"</font><br/>";
		return wapper;
	}
	public static String getRedWapper(String content){
		String wapper ="<font color='red'>"+content+"</font>";
		return wapper;
	}
	
	public static String getOrangeWapper(String content){
		String wapper ="<font color='orange'>"+content+"</font>";
		return wapper;
	}
	
	//------------------------------以下方法暂时用不到----------------------------//

	@Deprecated
	public static String getControlStrategy(String token,String machineToke){
		if(machineToke.equals("0") || machineToke.equals("3")){
			if (token.equals("1")) {
				return "手动控制";
			}else if(token.equals("2")){
				return "时间控制";
			}else if(token.equals("3")){
				return "溶解氧控制";
			}else {
				return "未知错误";
			}
		}else if(machineToke.equals("1") || machineToke.equals("2")){
			if (token.equals("1")) {
				return "手动控制";
			}else if(token.equals("2")){
				return "时间控制";
			}else {
				return "未知错误";
			}
		}else{
			return "未知错误";
		}
		
	}

	@Deprecated
	public static String getNowDate() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return formatter.format(new java.util.Date());
	}

	@Deprecated
	public static String getGaugeState(String name,float data,String token){
		NumberFormat nf = new DecimalFormat("0.00");
		if(token.equals("1")){
			return nf.format(data)+"(正常)";
		}else if(token.equals("2")){
			return nf.format(data)+"(警告:"+name+"高于设定值)";
		}else if(token.equals("3")){
			return nf.format(data)+"(警告:"+name+"低于设定值)";
		}else {
			return "仪表未知错误";
		}
	}

	@Deprecated
	public static String getEquipmentState(String stateToke){
		if (stateToke.equals("1")) {
			return "设备正常【运行】";
		}else if(stateToke.equals("2")){
			return "设备正常【运行】";
		}else if(stateToke.equals("3")){
			return "设备故障";
		}else if(stateToke.equals("4")){
			return "设备未安装";
		}else {
			return "设备未知问题";
		}
	}
	
	public static String getGaugeRange(float down,float up){
		return String.valueOf(down)+" ~ "+ String.valueOf(up);
	}
	
	
	/**
	 * 判断设备是否有故障
	 * @param runData
	 * @return
	 * @throws ClassNotFoundException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 * @throws NoSuchMethodException 
	 * @throws SecurityException 
	 */
	public static boolean rundataisAbnormal(RunData runData) throws ClassNotFoundException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, SecurityException, NoSuchMethodException{
		//Map<String, String> map = CustomPropertyConfigurer.getProperties();
		//String systemNO = map.get("systemno");
		//如果系统参数是0，表明是老系统

		Class<?> rundataClass = Class.forName("com.yaoli.beans.RunData");
		if(SystemConfig.site instanceof ImplEquip_1_to_5 ){
			if(runData!= null){
				for (int i = 1; i <= 5; i++) {
					Method method = rundataClass.getDeclaredMethod("getEquipment"+i+"state"); //getEquipment11state
					Object stateObject = method.invoke(runData);
					int state = (stateObject == null ? -1:Integer.valueOf(stateObject.toString()));
					if(state != -1){//表示 state不为空，即有该数据;设置设备名称。
						if(state == 3){
							return true;
						}
					}
				}
			}
		}else if(SystemConfig.site instanceof  ImplEquip_6_to_21){
			if(runData!= null){
				for (int i = 6; i <= 21; i++) {
					Method method = rundataClass.getDeclaredMethod("getEquipment"+i+"state"); //getEquipment11state
					Object stateObject = method.invoke(runData);
					int state = (stateObject == null ? -1:Integer.valueOf(stateObject.toString()));
					if(state != -1){//表示 state不为空，即有该数据;设置设备名称。
						if(i == 6 || i == 7){
							if(state == 1 || state == 8){
								return true;
							}
						}else {
							if(state == 3){
								return true;
							}
						}
					}
				}
			}
		}

//		if(SystemConfig.site instanceof ImplDetect_1_to_5){
//
//		}else if(SystemConfig.site instanceof ImplDetect_1_to_5_10_to_14){
//
//		}
//
//		if(SystemConfig.site instanceof ImplEquip_1_to_5 && SystemConfig.site instanceof ImplDetect_1_to_5){
//
//		}
//
//
//		//如果系统参数是1，表明是江都的系统
//		if(SystemConfig.site instanceof ImplEquip_6_to_21 && SystemConfig.site instanceof  ImplDetect_1_to_5){
//
//		}
		return false;
	}
	
	/**
	 * 判断水质是否有故障
	 * @return 是否有故障
	 * @throws ClassNotFoundException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 * @throws NumberFormatException 
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 */
	public static boolean detectiondataisAbnormal(Sewage sewage,DetectionData de) throws ClassNotFoundException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, SecurityException, NoSuchMethodException{
		Class<?> detectionDataClass = Class.forName("com.yaoli.beans.DetectionData");
		Class<?> sewageClass = Class.forName("com.yaoli.beans.Sewage");

		if(SystemConfig.site instanceof ImplDetect_1_to_5){
			if(de != null){
				for (int i = 1; i <= 5; i++) {
					if(i == 4){//老系统的液位
						//fix 去掉液位计为高的时候警报异常,去掉判断，去掉即可
/*						Method getDetection = detectionDataClass.getDeclaredMethod("getDetection"+i);
						Double detection = getDetection.invoke(de) == null?null:Double.valueOf(getDetection.invoke(de).toString());
						if(detection == 1){
							return true;
						}*/
					}else {
						if(judgeDetection(i,sewage,de,sewageClass,detectionDataClass)){
							return true;
						}
					}

				}
			}
		}else if(SystemConfig.site instanceof ImplDetect_1_to_5_10_to_15){
			if(de != null){
				for (int i = 1; i <= 5; i++) {
					if(i != 4){//老系统的液位
						if(judgeDetection(i,sewage,de,sewageClass,detectionDataClass)){
							return true;
						}
					}
				}
				for (int i = 10; i <= 15; i++) {
					if(judgeDetection(i,sewage,de,sewageClass,detectionDataClass)){
						return true;
					}
				}

			}
		}
		return false;
	}


	/**
	 * 判断 水质是否有问题
	 * @param i 水质序号
	 * @param sewage 站点信息，封装了水质的上下限
	 * @param de 水质信息
	 * @param sewageClass 站点类
	 * @param detectionDataClass 水质类
     * @return 是否问题
     */
	private static boolean judgeDetection(int i,Sewage sewage,DetectionData de,Class<?> sewageClass,Class<?> detectionDataClass)  {
		try {
			//设置 detection的下限 等
			Method getDetectiondl = sewageClass.getDeclaredMethod("getDetection"+i+"dl");
			Double detectiondlValue = getDetectiondl.invoke(sewage) == null?null:Double.valueOf(getDetectiondl.invoke(sewage).toString());


			//设置 detectiondl上限 等
			Method getDetectionul = sewageClass.getDeclaredMethod("getDetection"+i+"ul");
			Double detectionulValue = getDetectionul.invoke(sewage) == null?null:Double.valueOf(getDetectionul.invoke(sewage).toString());

			//获取 detection 的值
			Method getDetection = detectionDataClass.getDeclaredMethod("getDetection"+i);
			Double detection = getDetection.invoke(de) == null?null:Double.valueOf(getDetection.invoke(de).toString());

			//表明水质有问题
			if (detectionulValue != null && detectiondlValue !=null && detection != null) {
				if( i != 6 ){
					if (detectiondlValue > detection || detectionulValue < detection) {
						return true;
					}
				}
			}
		}catch (NoSuchMethodException e){
			e.printStackTrace();
		}catch (InvocationTargetException e){
			e.printStackTrace();
		}catch (IllegalAccessException e){
			e.printStackTrace();
		}

		return false;
	}
	
	/**
	 * 设置设备名称
	 * @param sewageMonitorVO
	 * @param runData
	 * @throws ClassNotFoundException
	 * @throws Exception
	 * @throws IllegalAccessException
	 */
	public static void setEquipmentName(SewageMonitorVO sewageMonitorVO,RunData runData) throws ClassNotFoundException, Exception, IllegalAccessException{
		Class<?> rundataClass = Class.forName("com.yaoli.beans.RunData");
		Class<?> sewageMonitorVoClass = Class.forName("com.yaoli.vo.SewageMonitorVO");

		if(SystemConfig.site instanceof ImplEquip_1_to_5 ){
			if(runData != null){
				for (int i = 1; i <= 5; i++) {
					setEquipName(i,rundataClass,sewageMonitorVoClass,sewageMonitorVO,runData);
				}
			}
		}else if(SystemConfig.site instanceof  ImplEquip_6_to_21){
			if(runData != null){
				for (int i = 6; i <= 21; i++) {
					setEquipName(i,rundataClass,sewageMonitorVoClass,sewageMonitorVO,runData);
				}
			}
		}else if(SystemConfig.site instanceof  ImplEquip_1_to_21){
			if(runData != null){
				for (int i = 1; i <= 21; i++) {
					setEquipName(i,rundataClass,sewageMonitorVoClass,sewageMonitorVO,runData);
				}
			}
		}
	}

	private static void setEquipName(int i ,Class<?> rundataClass,Class<?> sewageMonitorVoClass,SewageMonitorVO sewageMonitorVO,RunData runData){
		if(runData != null){
			try {
				Method method = rundataClass.getDeclaredMethod("getEquipment"+i+"state"); //getEquipment1state
				Object stateObject = method.invoke(runData);
				int state = (stateObject == null ? -1:Integer.valueOf(stateObject.toString()));
				if(state != -1){//表示 state不为空，即有该数据;设置设备名称。
					//设置设备名称
					Field field2 = sewageMonitorVoClass.getDeclaredField("equipment"+i+"Name");
					field2.setAccessible(true);
					field2.set(sewageMonitorVO,SystemConfig.equips.get("equipment"+i+"name"));
				}
			}catch (NoSuchMethodException e){
				e.printStackTrace();
			}catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (NoSuchFieldException e) {
				e.printStackTrace();
			}
        }

	}

	public static boolean isBlank(String text){
		if(text == null){
			return true;
		}else if(text.equals("")){
			return true;
		}
		return false;
	}

	public static int getMonthHasManyDays(int month){
		if(month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12){
			return 31;
		}else if(month == 4 || month == 6 || month == 9 || month == 11){
			return 30;
		}else {
			return 28;
		}
	}



	/*
    //获取角色值
    //Subject
    Subject subject = SecurityUtils.getSubject();
    List<String> roleList = new ArrayList<String>();
    roleList.add("7");//拥有管理员
    roleList.add("8");//用用超级管理员角色
    if(subject.hasAllRoles(roleList))
    {
        String ON = "<font face='Arial'>" + "运营编号：" + operationnum + "</font>" + "<br/>";
        html = "<b><font face='Arial'><a href='/CWS/pages/work/sewagecontrol2.faces?sewageid=" + id + "&sewagetitle=" + java.net.URLEncoder.encode(sewage.getShortTitle()) + "'>" + sewage.getName() + "</a></font></b>" + "<br/>"
               + ON
               + eqpstate1
               + eqpstate2
               + eqpstate3
               + detvalue1
               + detvalue2
               + detvalue3
               + detvalue4
               + detvalue5
               + detvalue6
               + str1 + str2 + str3 + str4
               + "<a href='/CWS/pages/work/equipmentlist.faces?sewageid=" + id + "'>" + "<font face='Arial'>设备运行记录</font></a>" + "<br/>"
               + "<a href='/CWS/pages/work/detectionlist.faces?sewageid=" + id + "'>" + "<font face='Arial'>数据检测记录</font></a>";
    }
    else
    {
        html = "<b><font face='Arial'>" + sewage.getName() + "</font></b><br/>"
               + eqpstate1
               + eqpstate2
               + eqpstate3
               + detvalue1
               + detvalue2
               + detvalue3
               + detvalue4
               + detvalue5
               + detvalue6
               + str1 + str2 + str3 + str4
               + "<a href='/CWS/pages/work/equipmentlist.faces?sewageid=" + id + "'><font face='Arial'>设备运行记录</font></a>" + "<br/>"
               + "<a href='/CWS/pages/work/detectionlist.faces?sewageid=" + id + "'><font face='Arial'>数据检测记录</font></a>";
    }*/

	/*		if(ru != null){
	if(ru.getEquipment1state() == 3){
		sb.append(getWapper(getRedWapper(se.getEquipment1Name()+"：故障")));
	}else {
		sb.append(getWapper(se.getEquipment1Name()+(ru.getEquipment1state() == 1?"：运行":"：停止")));
	}
	if(ru.getEquipment2state()== 3){
		sb.append(getWapper(getRedWapper(se.getEquipment2Name()+"：故障")));
	}else {
		sb.append(getWapper(se.getEquipment2Name()+(ru.getEquipment2state()== 1?"：运行":"：停止")));
	}
	if(ru.getEquipment3state()== 3){
		sb.append(getWapper(getRedWapper(se.getEquipment3Name()+"：故障")));
	}else {
		sb.append(getWapper(se.getEquipment3Name()+(ru.getEquipment3state()== 1?"：运行":"：停止")));
	}
	if(se.getControlmethod().equals("2")){
		if(se.getEquipment4state().equals("3")){
			sb.append(getWapper(getRedWapper(se.getEquipment4Name()+"：故障")));
		}else {
			sb.append(getWapper(se.getEquipment4Name()+(ru.getEquipment4state()== 1?"：运行":"：停止")));
		}
	}
}*/

/*	if(de != null){
		if(de.getDetection1() > se.getDetection1ul() || de.getDetection1() < se.getDetection1dl()){
			sb.append(getWapper(getOrangeWapper("温度："+new java.text.DecimalFormat("0.00").format(de.getDetection1()))));
		}else{
			sb.append(getWapper(("温度："+new java.text.DecimalFormat("0.00").format(de.getDetection1()))));
		}
		if(de.getDetection2() > se.getDetection2ul() || de.getDetection2() < se.getDetection2dl()){
			sb.append(getWapper(getOrangeWapper("PH："+new java.text.DecimalFormat("0.00").format(de.getDetection2()))));	
		}else {
			sb.append(getWapper(("PH："+new java.text.DecimalFormat("0.00").format(se.getDetection2()))));
		}
		if(de.getDetection3() > se.getDetection3ul() || de.getDetection3() < se.getDetection3dl()){
			sb.append(getWapper(getOrangeWapper("ORP："+new java.text.DecimalFormat("0.00").format(de.getDetection3()))));
		}else {
			sb.append(getWapper(("ORP："+new java.text.DecimalFormat("0.00").format(de.getDetection3()))));
		}
		
		if(de.getDetection4() == 1){
			sb.append(getWapper(getOrangeWapper("LS：高")));
		}else if (de.getDetection4() == 0) {
			sb.append(getWapper(("LS：低")));
		}else {
			sb.append(getWapper(getOrangeWapper("LS：异常")));
		}
		if(de.getDetection5() > se.getDetection5ul() || de.getDetection5() < se.getDetection5dl()){
			sb.append(getWapper(getOrangeWapper("DO："+new java.text.DecimalFormat("0.00").format(de.getDetection5()))));
		}else{
			sb.append(getWapper(("DO："+new java.text.DecimalFormat("0.00").format(de.getDetection5()))));
		}
		if(de.getDetection6() > se.getDetection6ul() || de.getDetection6() < se.getDetection6dl()){
			sb.append(getWapper(getOrangeWapper("流量："+new java.text.DecimalFormat("0.00").format(de.getDetection6()))));
		}else{
			sb.append(getWapper(("流量："+new java.text.DecimalFormat("0.00").format(de.getDetection6()))));
		}
	}*/

	/**
	 * 实时监控，弹出的信息框
	 //	 * @param se
	 //	 * @param de
	 //	 * @param sewageDayTotalWaterFlow
	 * @return
	 * @throws ClassNotFoundException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 */
	/*public static String getSewageHTML(String contextPath,SewageMonitorVO se,DetectionData de,RunData runData,Sewage sewage,Double sewageDayTotalWaterFlow) throws ClassNotFoundException, SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException{
		StringBuilder sb = new StringBuilder();
		sb.append(getWapper("<a href='"+contextPath+"/monitor/sewagemonitor.do?sewageid="+se.getSewageid()+"'>"+se.getShortTitle()+"</a>"));



		//Map<String, String> map = CustomPropertyConfigurer.getProperties();
		//String systemNO = map.get("systemno");

		//如果系统参数是0，表明是老系统
		//if(systemNO.equals("0")){
		if(SystemConfig.site instanceof ImplEquip_1_to_5 && SystemConfig.site instanceof ImplDetect_1_to_5){
			//添加 滚动条
			//sb.append("<div style='width:260px;height:300px; overflow-y:scroll'>");

			Class<?> rundataClass = Class.forName("com.yaoli.beans.RunData");
			Class<?> sewageMonitorVoClass = Class.forName("com.yaoli.vo.SewageMonitorVO");
			Class<?> detectionDataClass = Class.forName("com.yaoli.beans.DetectionData");

			//获取运营编号
			sb.append("运营编号："+se.getOperationnum()+"</br>");

			String equipmentName = "";
			if(runData != null){
				for (int i = 1; i <= 5; i++) {
					Method method = rundataClass.getDeclaredMethod("getEquipment"+i+"state"); //getEquipment11state
					Object stateObject = method.invoke(runData);
					int state = (stateObject == null ? -1:Integer.valueOf(stateObject.toString()));
					if(state != -1){//表示 state不为空，即有该数据;设置设备名称。
						Method getRunDataState = sewageMonitorVoClass.getDeclaredMethod("getEquipment"+i+"Name");
						equipmentName = String.valueOf(getRunDataState.invoke(se));
						if(state == 3){
							sb.append(getWapper(getRedWapper(equipmentName+":故障")));
						}else {
							if(state == 1){
								sb.append(getWapper(equipmentName+":运行"));
							}else {//state == 2
								sb.append(getWapper(equipmentName+":停止"));
							}
						}
					}
				}
			}else{
				//如果该站点没有数据，那么 这个站点的所有设备显示为停止
				for (int i = 1; i <= 5; i++) {
					equipmentName = SystemConfig.equips.get("equipment"+i+"name");
					sb.append(getWapper(equipmentName+":停止"));
				}
			}



			String detectionName;
			if(de != null){
				for (int i = 1; i <= 6; i++) {
					//在这里添加不进行判断的地方
					if(i == 4){
						Method getDetection = detectionDataClass.getDeclaredMethod("getDetection"+i);
						Double detection = getDetection.invoke(de) == null?null:Double.valueOf(getDetection.invoke(de).toString());
						detectionName = SystemConfig.detections.get("detection"+i+"name");
						if(detection == 1){
							//fix 液位是没有异常的说法的 去掉警报说法
							sb.append(getWapper((detectionName+":高")));
						}else {
							sb.append(getWapper((detectionName+":低")));
						}

					}else {
						//设置 detection的下限 等
						Method getDetectiondl = sewageMonitorVoClass.getDeclaredMethod("getDetection"+i+"dl");
						Double detectiondlValue = getDetectiondl.invoke(se) == null?null:Double.valueOf(getDetectiondl.invoke(se).toString());


						//设置 detectiondl上限 等
						Method getDetectionul = sewageMonitorVoClass.getDeclaredMethod("getDetection"+i+"ul");
						Double detectionulValue = getDetectionul.invoke(se) == null?null:Double.valueOf(getDetectionul.invoke(se).toString());

						Method getDetection = detectionDataClass.getDeclaredMethod("getDetection"+i);
						Double detection = getDetection.invoke(de) == null?null:Double.valueOf(getDetection.invoke(de).toString());

						if (detection != null) {
							detectionName = SystemConfig.detections.get("detection"+i+"name");
							if(detectionulValue != null && detectiondlValue !=null){
								if (detectiondlValue > detection || detectionulValue < detection) {
									if(i != 6){//如果 i !=  6  流量按正常比较
										sb.append(getWapper(getOrangeWapper(detectionName+"："+new java.text.DecimalFormat("0.00").format(detection))));
									}else {//否则，无须发布
										sb.append(getWapper(detectionName+"："+new java.text.DecimalFormat("0.00").format(detection)));
									}
								}else {
									sb.append(getWapper(detectionName+"："+new java.text.DecimalFormat("0.00").format(detection)));
								}
							}else {
								sb.append(getWapper(detectionName+"："+new java.text.DecimalFormat("0.00").format(detection)));
							}
						}
					}
				}
				//添加总累计水量
				Method getDetection = detectionDataClass.getDeclaredMethod("getDetection9");
				Double detection = getDetection.invoke(de) == null?null:Double.valueOf(getDetection.invoke(de).toString());
				detectionName = SystemConfig.detections.get("detection9name");
				sb.append(getWapper((detectionName+":"+detection)));
			}else {
				//如果这个站点没有水质信息，那么这个站点显示为 0
				for (int i = 1; i <= 6; i++) {
					if(i == 4){
						equipmentName = SystemConfig.detections.get("detection"+i+"name");
						sb.append(getWapper(equipmentName+":低 "));
					}else {
						equipmentName = SystemConfig.detections.get("detection"+i+"name");
						sb.append(getWapper(equipmentName+" : 0.00"));
					}
				}
				//添加总累计水量 NO:112
				equipmentName = SystemConfig.detections.get("detection9name");
				sb.append(getWapper(equipmentName+" : 0.00"));
			}
		}


		//如果系统参数是1，表明是江都的系统
		//if(systemNO.equals("1")){
		if(SystemConfig.site instanceof ImplEquip_6_to_21 && SystemConfig.site instanceof  ImplDetect_1_to_5){

			//添加 滚动条
			sb.append("<div style='width:260px;height:350px; overflow-y:scroll'>");

			Class<?> rundataClass = Class.forName("com.yaoli.beans.RunData");
			Class<?> sewageMonitorVoClass = Class.forName("com.yaoli.vo.SewageMonitorVO");
			Class<?> detectionDataClass = Class.forName("com.yaoli.beans.DetectionData");

			//获取运营编号
			sb.append("运营编号："+se.getOperationnum()+"</br>");

			String equipmentName = "";
			if(runData != null){
				for (int i = 6; i <= 21; i++) {
					Method method = rundataClass.getDeclaredMethod("getEquipment"+i+"state"); //getEquipment11state
					Object stateObject = method.invoke(runData);
					int state = (stateObject == null ? -1:Integer.valueOf(stateObject.toString()));
					if(state != -1){//表示 state不为空，即有该数据;设置设备名称。
						//Method getRunDataState = sewageMonitorVoClass.getDeclaredMethod("getEquipment"+i+"Name");
						equipmentName = SystemConfig.equips.get("equipment"+i+"name");
						if (i == 6 || i == 7) {//两个液位计
							if(state == 8){
								sb.append(getWapper(getRedWapper(equipmentName+"：低低")));
							}else if(state == 1){
								sb.append(getWapper(getRedWapper(equipmentName+"：高高")));
							}else {
								if(state == 2){
									sb.append(getWapper(equipmentName+":高"));
								}else {//state == 4
									sb.append(getWapper(equipmentName+":低"));
								}
							}
						}else {//其他设备
							if(state == 3){
								sb.append(getWapper(getRedWapper(equipmentName+"：故障")));
							}else {
								if(state == 1){
									sb.append(getWapper(equipmentName+":运行"));
								}else {//state == 2
									sb.append(getWapper(equipmentName+":停止"));
								}
							}
						}
					}
				}
			}else{
				//如果该站点没有数据，那么 这个站点的所有设备显示为停止
				for (int i = 1; i <= 21; i++) {
					if(i != 5){//5是备用
						equipmentName = SystemConfig.equips.get("equipment"+i+"name");
						sb.append(getWapper(equipmentName+":停止"));
					}
				}
			}



			String detectionName;
			if(de != null){
				for (int i = 1; i < 7; i++) {
					//在这里添加不进行判断的地方 江都系统detection4废弃
					if(i != 4){
						//设置 detection的下限 等
						Method getDetectiondl = sewageMonitorVoClass.getDeclaredMethod("getDetection"+i+"dl");
						Double detectiondlValue = getDetectiondl.invoke(se) == null?null:Double.valueOf(getDetectiondl.invoke(se).toString());


						//设置 detectiondl上限 等
						Method getDetectionul = sewageMonitorVoClass.getDeclaredMethod("getDetection"+i+"ul");
						Double detectionulValue = getDetectionul.invoke(se) == null?null:Double.valueOf(getDetectionul.invoke(se).toString());

						Method getDetection = detectionDataClass.getDeclaredMethod("getDetection"+i);
						Double detection = getDetection.invoke(de) == null?null:Double.valueOf(getDetection.invoke(de).toString());

						if (detection != null) {
							detectionName = SystemConfig.detections.get("detection"+i+"name");
							if(detectionulValue != null && detectiondlValue !=null){
								if (detectiondlValue > detection || detectionulValue < detection) {
									sb.append(getWapper(getOrangeWapper(detectionName+"："+new java.text.DecimalFormat("0.00").format(detection))));
								}else {
									sb.append(getWapper(detectionName+"："+new java.text.DecimalFormat("0.00").format(detection)));
								}
							}else {
								sb.append(getWapper(detectionName+"："+new java.text.DecimalFormat("0.00").format(detection)));
							}
						}
					}
					//注释 取消掉所有的 detection6判断
*//*					if(i == 6){// i == 6 的时候要特殊处理，因为如果 detection8 = -999表示就要用 detection6
						Method getDetection8 = detectionDataClass.getDeclaredMethod("getDetection8");
						Double detection8 = getDetection8.invoke(de) == null?null:Double.valueOf(getDetection8.invoke(de).toString());
						double waterflow = 0.00;
						if(detection8 != null ){
							if( detection8 == -999 ){
								Method getDetection6 = detectionDataClass.getDeclaredMethod("getDetection6");
								Double detection6 = getDetection6.invoke(de) == null?null:Double.valueOf(getDetection6.invoke(de).toString());
								waterflow = detection6;
							}
						}else {

							detection8 = 0.0;
						}
						waterflow = detection8;
						detectionName = map.get("detection"+i+"name");
						sb.append(getWapper(detectionName+"："+new java.text.DecimalFormat("0.00").format(waterflow)));
					}*//*
				}
			}else {
				//如果这个站点没有水质信息，那么这个站点显示为 0
				for (int i = 1; i <= 6; i++) {
					if(i == 4){
						equipmentName = SystemConfig.detections.get("detection"+i+"name");
						sb.append(getWapper(equipmentName+":低 "));
					}else {
						equipmentName = SystemConfig.detections.get("detection"+i+"name");
						sb.append(getWapper(equipmentName+" : 0.00"));
					}
				}
			}
		}

		//如果系统参数是1，表明是农博园的系统
		//if(systemNO.equals("1")){
		if(SystemConfig.site instanceof ImplEquip_1_to_5 && SystemConfig.site instanceof ImplDetect_1_to_5_10_to_14){

			//添加 滚动条
			sb.append("<div style='width:260px;height:350px; overflow-y:scroll'>");

			Class<?> rundataClass = Class.forName("com.yaoli.beans.RunData");
			Class<?> sewageMonitorVoClass = Class.forName("com.yaoli.vo.SewageMonitorVO");
			Class<?> detectionDataClass = Class.forName("com.yaoli.beans.DetectionData");

			//获取运营编号
			sb.append("运营编号："+se.getOperationnum()+"</br>");

			String equipmentName = "";
			if(runData != null){


				for (int i = 1; i <= 5; i++) {
					Method method = rundataClass.getDeclaredMethod("getEquipment"+i+"state"); //getEquipment11state
					Object stateObject = method.invoke(runData);
					int state = (stateObject == null ? -1:Integer.valueOf(stateObject.toString()));
					if(state != -1){//表示 state不为空，即有该数据;设置设备名称。
						Method getRunDataState = sewageMonitorVoClass.getDeclaredMethod("getEquipment"+i+"Name");
						equipmentName = String.valueOf(getRunDataState.invoke(se));
						if(state == 3){
							sb.append(getWapper(getRedWapper(equipmentName+":故障")));
						}else {
							if(state == 1){
								sb.append(getWapper(equipmentName+":运行"));
							}else {//state == 2
								sb.append(getWapper(equipmentName+":停止"));
							}
						}
					}
				}


			}else{
				//如果该站点没有数据，那么 这个站点的所有设备显示为停止
				for (int i = 1; i <= 5; i++) {
					if(i != 5){//5是备用
						equipmentName = SystemConfig.equips.get("equipment"+i+"name");
						sb.append(getWapper(equipmentName+":停止"));
					}
				}
			}



			String detectionName;
			if(de != null){
				for (int i = 1; i < 7; i++) {
					//在这里添加不进行判断的地方 江都系统detection4废弃
					if(i != 4){
						//设置 detection的下限 等
						Method getDetectiondl = sewageMonitorVoClass.getDeclaredMethod("getDetection"+i+"dl");
						Double detectiondlValue = getDetectiondl.invoke(se) == null?null:Double.valueOf(getDetectiondl.invoke(se).toString());


						//设置 detectiondl上限 等
						Method getDetectionul = sewageMonitorVoClass.getDeclaredMethod("getDetection"+i+"ul");
						Double detectionulValue = getDetectionul.invoke(se) == null?null:Double.valueOf(getDetectionul.invoke(se).toString());

						Method getDetection = detectionDataClass.getDeclaredMethod("getDetection"+i);
						Double detection = getDetection.invoke(de) == null?null:Double.valueOf(getDetection.invoke(de).toString());

						if (detection != null) {
							detectionName = SystemConfig.detections.get("detection"+i+"name");
							if(detectionulValue != null && detectiondlValue !=null){
								if (detectiondlValue > detection || detectionulValue < detection) {
									sb.append(getWapper(getOrangeWapper(detectionName+"："+new java.text.DecimalFormat("0.00").format(detection))));
								}else {
									sb.append(getWapper(detectionName+"："+new java.text.DecimalFormat("0.00").format(detection)));
								}
							}else {
								sb.append(getWapper(detectionName+"："+new java.text.DecimalFormat("0.00").format(detection)));
							}
						}
					}
				}
			}else {
				//如果这个站点没有水质信息，那么这个站点显示为 0
				for (int i = 1; i <= 5; i++) {
					if(i == 4){
						equipmentName = SystemConfig.detections.get("detection"+i+"name");
						sb.append(getWapper(equipmentName+":低 "));
					}else {
						equipmentName = SystemConfig.detections.get("detection"+i+"name");
						sb.append(getWapper(equipmentName+" : 0.00"));
					}
				}
				for (int i = 10; i <= 14; i++) {
					equipmentName = SystemConfig.detections.get("detection"+i+"name");
					sb.append(getWapper(equipmentName+" : 0.00"));
				}
			}
		}

		if(de != null && sewageDayTotalWaterFlow != null){
			sb.append(getWapper("日处理水量："+new java.text.DecimalFormat("0.00").format(sewageDayTotalWaterFlow)+"立方米"));
			if(se.getReducecod() != null){
				sb.append(getWapper("日削减COD量："+new java.text.DecimalFormat("0.00").format(sewageDayTotalWaterFlow*se.getReducecod())+"克"));
			}
			if(se.getReducenh3n() != null){
				sb.append(getWapper("日削减NH3-N量："+new java.text.DecimalFormat("0.00").format(sewageDayTotalWaterFlow*se.getReducenh3n())+"克"));
			}
			if(se.getReducep() != null){
				sb.append(getWapper("日削减总P量："+new java.text.DecimalFormat("0.00").format(se.getReducep()*sewageDayTotalWaterFlow)+"克"));
			}
		}else {//否则 全部设置为0
			sb.append(getWapper("日处理水量："+new java.text.DecimalFormat("0.00").format(0)+" 立方米"));
			sb.append(getWapper("日削减COD量："+new java.text.DecimalFormat("0.00").format(0)+" 克"));
			sb.append(getWapper("日削减NH3-N量："+new java.text.DecimalFormat("0.00").format(0)+" 克"));
			sb.append(getWapper("日削减总P量："+new java.text.DecimalFormat("0.00").format(0)+" 克"));
		}





        Subject subject = SecurityUtils.getSubject();
        List<String> roleList = new ArrayList<String>();
        roleList.add("7");//拥有管理员
        roleList.add("8");//用用超级管理员角色
        if(subject.hasAllRoles(roleList)){

        }else {

		}
		//String autoflow = ;<div style="width:260px;height:120px; overflow-y:scroll; border:1px solid;"> 这里是你要显示的内容 </div>

		//如果是江都系统，需要添加滚动条
		if(SystemConfig.site instanceof ImplEquip_6_to_21 && SystemConfig.site instanceof  ImplDetect_1_to_5){
			sb.append("</div>");
		}

		return sb.toString();
	}*/
}
