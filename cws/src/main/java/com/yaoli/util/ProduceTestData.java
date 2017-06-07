package com.yaoli.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class ProduceTestData {
	
	
	public static void main(String[] args) throws IOException {
/*		for (int i = 1; i <= 14; i++) {
			System.out.println("+getAbnormalData()+\",\"+");
		}*/
/*        Date randomDate = randomDate("2014-06-01", "2014-06-02");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");*/
        //System.out.println(randomDate.toString());
/*        for (int i = 0; i < 200; i++) {
            Date randomDate = randomDate("2016-03-13", "2016-03-16");
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        	System.out.println(format.format(randomDate));
		}*/
/*		for (int i = 1; i <= 21; i++) {
			System.out.print("equipment"+i+"state,");
		}*/
		/*		for (int i = 0; i <240; i++) {
		int sewageid = 497;
		boolean isNormal = ptd.getProbilityForNormal(); 
        //Date randomDate = randomDate("2016-03-15 00:00:00", "2016-03-16 59:59:59");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("--"+isNormal+"--"+format.format(dates.get(i)));
		System.out.println(ptd.getDetection(sewageid, isNormal, format.format(dates.get(i))));
		System.out.println(ptd.getEquipmenSql(sewageid, isNormal, format.format(dates.get(i))));
	}*/
		File file =new File("sql1.sql");
		if(!file.exists()){
		     file.createNewFile();
		}
		FileWriter fileWritter = new FileWriter(file.getName(),true);
        BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
        
		File file2 =new File("sql2.sql");
		if(!file2.exists()){
		     file2.createNewFile();
		}
		FileWriter fileWritter2 = new FileWriter(file2.getName(),true);
        BufferedWriter bufferWritter2 = new BufferedWriter(fileWritter2);
        
        
		File file3 =new File("sql3.sql");
		if(!file3.exists()){
		     file3.createNewFile();
		}
		FileWriter fileWritter3 = new FileWriter(file3.getName(),true);
        BufferedWriter bufferWritter3 = new BufferedWriter(fileWritter3);
        
        
        
        
        
        
		ProduceTestData ptd = new ProduceTestData();
		
		List<Date> dates = new ArrayList<Date>();
		//24 * 50
		for (int i = 0; i < 1440; i++) {
			Date randomDate = randomDate("2017-01-18", "2017-01-19");
			dates.add(randomDate);
		}
		Collections.sort(dates);
		//每小时随机生成58 条数据，并且按照顺序从小到大排好
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String temp1;
		String temp2;
		int point = 0;
		
		
		for (int i = 20; i <= 20; i++) {
			for (int j = 0; j < 10; j++) {
				if(point < 20000){
					temp1 = ptd.getMianYuan(i, true, format.format(dates.get(j)));
					temp2 = ptd.getEquipmenSql(i, true, format.format(dates.get(j)));
					bufferWritter.write(temp1);
					bufferWritter.write(temp2);
				}else if(point >= 20000 && point < 40000){
					temp1 = ptd.getMianYuan(i, true, format.format(dates.get(j)));
					//temp2 = ptd.getEquipmenSql(i, true, format.format(dates.get(j)));
					bufferWritter2.write(temp1);
					//bufferWritter2.write(temp2);
				}else if(point >= 40000){
					temp1 = ptd.getMianYuan(i, true, format.format(dates.get(j)));
					//temp2 = ptd.getEquipmenSql(i, true, format.format(dates.get(j)));
					bufferWritter3.write(temp1);
					//bufferWritter3.write(temp2);
				}
				point = point + 1;
			}
		}
		System.out.println("close");
		bufferWritter.close();
		bufferWritter2.close();
		bufferWritter3.close();
	}
	/**
	 * 获取异常的概率
	 * @return 
	 * true 表示这条数据是正常的，
	 * false 表示这条数据是异常的。
	 */
	public boolean getProbilityForNormal(){
		double probility = Math.random();
		if(probility > 0.3){
			return true;
		}
		return false;
	}
	
	public double getNormalData(){
		java.text.DecimalFormat df = new java.text.DecimalFormat("#.00");  
		Random random = new Random();
		double i = random.nextInt(1000)+Double.valueOf(df.format(Math.random()/10));
		
		Random random2 = new Random();
		int j = random2.nextInt(10);
		if(j%2 == 0){
			i = i * -1;
		}
		return i;
	}
	
	public double getAbnormalData(){
		java.text.DecimalFormat df = new java.text.DecimalFormat("#.00");  
		Random random = new Random();
		double i = random.nextInt(1000)+1001+Double.valueOf(df.format(Math.random()/10));
		
		Random random2 = new Random();
		int j = random2.nextInt(10);
		if(j%2 == 0){
			i = i * -1;
		}
		return i;
	}
	
	public int getEquipmentAbnormalData(){
		Random random = new Random();
		int i = random.nextInt(2)+1;
		
		Random random2 = new Random();
		int j = random2.nextInt(10);
		if(j%2 == 0){
			i = 3;
		}
		return i;
	}
	
	public int getEquipmentNormalData(){
		Random random = new Random();
		int i = random.nextInt(2)+1;
		return i;
	}
	
	
	public int getNormalLsData(){
		//4 2
		Random random = new Random();
		int i = random.nextInt(2)+1;//[0,1] + 1 = [1,2]
		return i*2;
	}
	
	public int getAbnormalLsData(){
		//8 1
		Random random = new Random();
		int i = random.nextInt(2);//[0,1]
		if(i==0){
			i = i+1;
		}else {
			i = i+7;
		}
		return i;
	}
	
	
	/**
	 * 
	 */
	public static double getNormalTemp(){
		Random random = new Random();
		double temp = random.nextDouble();
		String i = new DecimalFormat("0.00").format(temp);//[0,1]
		return Double.valueOf(i)+21;
	}

	/**
	 * 
	 * @param sewgeid
	 * @return
	 */
	public static double getNormalPh(int sewgeid){
		String i = new DecimalFormat("0.00").format(7+sewgeid/80);//[0,1]
		return Double.valueOf(i);
	}
	
	public static double getNomralDO(){
		Random random = new Random();
		double temp = random.nextDouble()+4;
		String i = new DecimalFormat("0.00").format(temp);//[0,1]
		return Double.valueOf(i);
	}
	
	public static double getflow(){
		Random random = new Random();
		double temp = random.nextDouble();
		return temp;
	}
	
	public static double getNormalORP(){
		Random random = new Random();
		double temp = random.nextDouble()*(random.nextInt(2)+1)*100+450;
		String i = new DecimalFormat("0.00").format(temp);//[0,1]
		return Double.valueOf(i);
	}

	public static double getCOD(){
		Random random = new Random();
		double temp = random.nextDouble();
		return temp;
	}

	public static double getAN(){
		Random random = new Random();
		double temp = random.nextDouble();
		return temp;
	}


	public static double getFTU(){
		Random random = new Random();
		double temp = random.nextDouble();
		return temp;
	}

	public static double getTP(){
		Random random = new Random();
		double temp = random.nextDouble();
		return temp;
	}

	public static double getRH(){
		Random random = new Random();
		double temp = random.nextDouble();
		return temp;
	}
	
	/**
	 * 得到设备的sql
	 * @param isNormal
	 * @return
	 */
	public String getEquipmenSql(int sewageid,boolean isNormal,String testingtime){
		if(isNormal == true){
			String sql = "insert into run_data(sewageid,testingtime,equipment1state,equipment2state,equipment3state,equipment4state,equipment5state,equipment6state,equipment7state,equipment8state,equipment9state,equipment10state,equipment11state,equipment12state,equipment13state,equipment14state,equipment15state,equipment16state,equipment17state,equipment18state,equipment19state,equipment20state,equipment21state)"+
					"values ("
					+sewageid+","
					+"'"+testingtime+"',"
					+"1"+","
					+"3"+","
					+getEquipmentNormalData()+","+
					+getEquipmentNormalData()+","+
					+getEquipmentNormalData()+","+
					+getNormalLsData()+","+
					+getNormalLsData()+","+
					+getEquipmentNormalData()+","+
					+getEquipmentNormalData()+","+
					+getEquipmentNormalData()+","+
					+getEquipmentNormalData()+","+
					+getEquipmentNormalData()+","+
					+getEquipmentNormalData()+","+
					+getEquipmentNormalData()+","+
					+getEquipmentNormalData()+","+
					+getEquipmentNormalData()+","+
					+getEquipmentNormalData()+","+
					+getEquipmentNormalData()+","+
					+getEquipmentNormalData()+","+
					+getEquipmentNormalData()+","+
					+getEquipmentNormalData()+
					");\n";
			return sql;
		}else {
			String sql = "insert into run_data(sewageid,testingtime,equipment1state,equipment2state,equipment3state,equipment4state,equipment5state,equipment6state,equipment7state,equipment8state,equipment9state,equipment10state,equipment11state,equipment12state,equipment13state,equipment14state,equipment15state,equipment16state,equipment17state,equipment18state,equipment19state,equipment20state,equipment21state)"+
					"values ("
					+sewageid+","
					+"'"+testingtime+"',"
					+getEquipmentAbnormalData()+","+
					+getEquipmentAbnormalData()+","+
					+getEquipmentAbnormalData()+","+
					+getEquipmentAbnormalData()+","+
					+getEquipmentAbnormalData()+","+
					+getAbnormalLsData()+","+
					+getAbnormalLsData()+","+
					+getEquipmentAbnormalData()+","+
					+getEquipmentAbnormalData()+","+
					+getEquipmentAbnormalData()+","+
					+getEquipmentAbnormalData()+","+
					+getEquipmentAbnormalData()+","+
					+getEquipmentAbnormalData()+","+
					+getEquipmentAbnormalData()+","+
					+getEquipmentAbnormalData()+","+
					+getEquipmentAbnormalData()+","+
					+getEquipmentAbnormalData()+","+
					+getEquipmentAbnormalData()+","+
					+getEquipmentAbnormalData()+","+
					+getEquipmentAbnormalData()+","+
					+getEquipmentAbnormalData()+
					")";
			return sql;
		}
	}

	public double total = 4000;
	public String getDetection(int sewageid,boolean isNormal,String testingtime){
		if(isNormal == true){
			total = 1 + total + Math.random();

			String sql = "insert into detection_data(sewageid,testingtime,detection1,detection2,detection3,detection5,detection6,detection8,detection9)"+
					"values("
					+sewageid+","
					+"'"+testingtime+"',"+
					+getNormalTemp()+","+
					+getNormalPh(sewageid)+","+
					+getNormalORP()+","+
					"1"
					+getNomralDO()+","+
					+getflow()+
					",-999"+
					","+total+");\n";
			return sql;
		}else {
			String sql = "insert into detection_data(sewageid,testingtime,detection1,detection2,detection3,detection4,detection5,detection6,detection7,detection8,detection9,detection10,detection11,detection12,detection13,detection14)"+
					"values("
					+sewageid+","
					+"'"+testingtime+"',"+
					+getAbnormalData()+","+
					+getAbnormalData()+","+
					+getAbnormalData()+","+
					+getAbnormalData()+","+
					+getAbnormalData()+","+
					+getAbnormalData()+","+
					+getAbnormalData()+","+
					+getAbnormalData()+","+
					+getAbnormalData()+","+
					+getAbnormalData()+","+
					+getAbnormalData()+","+
					+getAbnormalData()+","+
					+getAbnormalData()+","+
					+getAbnormalData()+
					")";
			return sql;
		}
	}

	public String getMianYuan(int sewageid,boolean isNormal,String testingtime){
		if(isNormal == true){
			total = 1 + total + Math.random();

			String sql = "insert into detection_data(sewageid,testingtime,detection1,detection2,detection3,detection4,detection5,detection6,detection7,detection8,detection9,detection10,detection11,detection12,detection13,detection14)"+
					"values("
					+sewageid+","
					+"'"+testingtime+"',"+
					+getNormalTemp()+","+
					+getNormalPh(sewageid)+","+
					+getNormalORP()+","+
					"1,"
					+getNomralDO()+","+
					+getflow()+
					",null"+
					",null"+
					",null,"+
					getCOD()+","+
					getAN()+","+
					getFTU()+","+
					getTP()+","+
					getRH()+");\n";
			return sql;
		}else {
			String sql = "insert into detection_data(sewageid,testingtime,detection1,detection2,detection3,detection4,detection5,detection6,detection7,detection8,detection9,detection10,detection11,detection12,detection13,detection14)"+
					"values("
					+sewageid+","
					+"'"+testingtime+"',"+
					+getAbnormalData()+","+
					+getAbnormalData()+","+
					+getAbnormalData()+","+
					+getAbnormalData()+","+
					+getAbnormalData()+","+
					+getAbnormalData()+","+
					+getAbnormalData()+","+
					+getAbnormalData()+","+
					+getAbnormalData()+","+
					+getAbnormalData()+","+
					+getAbnormalData()+","+
					+getAbnormalData()+","+
					+getAbnormalData()+","+
					+getAbnormalData()+
					")";
			return sql;
		}
	}
	
    private static Date randomDate(String beginDate, String endDate) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date start = format.parse(beginDate);// 开始日期
            Date end = format.parse(endDate);// 结束日期
            if (start.getTime() >= end.getTime()) {
                return null;
            }
            long date = random(start.getTime(), end.getTime());
 
            return new Date(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
 
    private static long random(long begin, long end) {
        long rtnn = begin + (long) (Math.random() * (end - begin));
        if (rtnn == begin || rtnn == end) {
            return random(begin, end);
        }
        return rtnn;
    }

}

