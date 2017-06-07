package com.yaoli.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * 前台控制方式
 * 0 表示没有选择
 * 1 一般控制
 * 2 无锡信捷XC1-16 PLC
 * 3 台达DVP PLC
 * @author will
 *
 */
public class ControlMethod {
	private int id;
	
	private String name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ControlMethod(int id,String name){
		this.id = id;
		this.name = name;
	}
	
	public static List<ControlMethod> getControlMethods(){
		List<ControlMethod> lists = new ArrayList<ControlMethod>();
		lists.add(new ControlMethod(1,"AOF工艺"));
		lists.add(new ControlMethod(2,"AO+人工湿地"));
		lists.add(new ControlMethod(3,"AO工艺"));
		lists.add(new ControlMethod(4,"MBR工艺"));
		lists.add(new ControlMethod(5,"SBR工艺"));
		lists.add(new ControlMethod(6,"跌水充氧接触氧化+人工湿地工艺"));
		lists.add(new ControlMethod(7,"复合生物滤床+人工湿地工艺"));
		lists.add(new ControlMethod(8,"复合塔式生态滤池+人工湿地工艺"));
		lists.add(new ControlMethod(9,"高负荷地下渗滤工艺"));
		lists.add(new ControlMethod(10,"海沃特工艺"));
		lists.add(new ControlMethod(11,"接触氧化+人工湿地）工艺"));
		lists.add(new ControlMethod(12,"脉冲多层复合滤池+人工湿地工艺"));
		lists.add(new ControlMethod(13,"厌氧滤池+好氧流化床工艺"));
		lists.add(new ControlMethod(14,"转笼式生物膜工艺"));
		return lists;
	}
}
