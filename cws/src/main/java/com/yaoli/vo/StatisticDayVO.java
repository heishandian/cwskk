package com.yaoli.vo;

import java.util.Date;
import java.util.List;

public class StatisticDayVO {
    // NO59 月处理水量及年处理水量分析中增加一条平均值线（当前累计值除以天数）
    private double avgWater;

	private Double equipment1energycost;
	private Double equipment2energycost;
	private Double equipment3energycost;
	private Double equipment4energycost;
    private Double equipment5energycost;

	private List<StatisticDayVO> statisticDayVOLists;
	
	private Double tonnage;
	
	private Double water;
	
	private Integer month;
	/**
	 * 表示一个月中的哪一天
	 */
	private Integer day;
	
	private Integer areaid;
	
	private String sewagename;
	
	private String adminname;
	
    private Integer id;

    private Integer sewageid;

    private Date testingtime;

    private Integer equip1normaltime;

    private Integer equip1abnormaltime;

    private Integer equip2normaltime;

    private Integer equip2abnormaltime;

    private Integer equip3normaltime;

    private Integer equip3abnormaltime;

    private Integer equip4normaltime;

    private Integer equip4abnormaltime;

    private Integer equip5normaltime;

    private Integer equip5abnormaltime;

    private Double detection1avg;

    private Double detection1min;

    private Double detection1max;

    private Double detection2avg;

    private Double detection2min;

    private Double detection2max;

    private Double detection3avg;

    private Double detection3min;

    private Double detection3max;

    private Double detection4avg;

    private Double detection4min;

    private Double detection4max;

    private Double detection5avg;

    private Double detection5min;

    private Double detection5max;

    private Integer equip6normaltime;

    private Integer equip6abnormaltime;

    private Integer equip7normaltime;

    private Integer equip7abnormaltime;

    private Integer equip8normaltime;

    private Integer equip8abnormaltime;

    private Integer equip9normaltime;

    private Integer equip9abnormaltime;

    private Integer equip10normaltime;

    private Integer equip10abnormaltime;

    private Integer equip11normaltime;

    private Integer equip11abnormaltime;

    private Integer equip12normaltime;

    private Integer equip12abnormaltime;

    private Integer equip13normaltime;

    private Integer equip13abnormaltime;

    private Integer equip14normaltime;

    private Integer equip14abnormaltime;

    private Integer equip15normaltime;

    private Integer equip15abnormaltime;

    private Integer equip16normaltime;

    private Integer equip16abnormaltime;

    private Integer equip17normaltime;

    private Integer equip17abnormaltime;

    private Integer equip18normaltime;

    private Integer equip18abnormaltime;

    private Integer equip19normaltime;

    private Integer equip19abnormaltime;

    private Integer equip20normaltime;

    private Integer equip20abnormaltime;

    private Integer equip21normaltime;

    private Integer equip21abnormaltime;

    private Integer equip22normaltime;

    private Integer equip22abnormaltime;

    private Integer equip23normaltime;

    private Integer equip23abnormaltime;

    private Integer equip24normaltime;

    private Integer equip24abnormaltime;

    private Double detection6avg;

    private Double detection6min;

    private Double detection6max;

    private Double detection7avg;

    private Double detection7min;

    private Double detection7max;

    private Double detection8avg;

    private Double detection8min;

    private Double detection8max;

    private Double detection9avg;

    private Double detection9min;

    private Double detection9max;

    private Double detection10avg;

    private Double detection10min;

    private Double detection10max;

    private Double detection11avg;

    private Double detection11min;

    private Double detection11max;

    private Double detection12avg;

    private Double detection12min;

    private Double detection12max;

    private Double detection13avg;

    private Double detection13min;

    private Double detection13max;

    private Double detection14avg;

    private Double detection14min;

    private Double detection14max;

    private Double detection15avg;

    private Double detection15min;

    private Double detection15max;

    private Double detection16avg;

    private Double detection16min;

    private Double detection16max;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSewageid() {
        return sewageid;
    }

    public void setSewageid(Integer sewageid) {
        this.sewageid = sewageid;
    }

    public Date getTestingtime() {
        return testingtime;
    }

    public void setTestingtime(Date testingtime) {
        this.testingtime = testingtime;
    }

    public Integer getEquip1normaltime() {
        return equip1normaltime;
    }

    public void setEquip1normaltime(Integer equip1normaltime) {
        this.equip1normaltime = equip1normaltime;
    }

    public Integer getEquip1abnormaltime() {
        return equip1abnormaltime;
    }

    public void setEquip1abnormaltime(Integer equip1abnormaltime) {
        this.equip1abnormaltime = equip1abnormaltime;
    }

    public Integer getEquip2normaltime() {
        return equip2normaltime;
    }

    public void setEquip2normaltime(Integer equip2normaltime) {
        this.equip2normaltime = equip2normaltime;
    }

    public Integer getEquip2abnormaltime() {
        return equip2abnormaltime;
    }

    public void setEquip2abnormaltime(Integer equip2abnormaltime) {
        this.equip2abnormaltime = equip2abnormaltime;
    }

    public Integer getEquip3normaltime() {
        return equip3normaltime;
    }

    public void setEquip3normaltime(Integer equip3normaltime) {
        this.equip3normaltime = equip3normaltime;
    }

    public Integer getEquip3abnormaltime() {
        return equip3abnormaltime;
    }

    public void setEquip3abnormaltime(Integer equip3abnormaltime) {
        this.equip3abnormaltime = equip3abnormaltime;
    }

    public Integer getEquip4normaltime() {
        return equip4normaltime;
    }

    public void setEquip4normaltime(Integer equip4normaltime) {
        this.equip4normaltime = equip4normaltime;
    }

    public Integer getEquip4abnormaltime() {
        return equip4abnormaltime;
    }

    public void setEquip4abnormaltime(Integer equip4abnormaltime) {
        this.equip4abnormaltime = equip4abnormaltime;
    }

    public Integer getEquip5normaltime() {
        return equip5normaltime;
    }

    public void setEquip5normaltime(Integer equip5normaltime) {
        this.equip5normaltime = equip5normaltime;
    }

    public Integer getEquip5abnormaltime() {
        return equip5abnormaltime;
    }

    public void setEquip5abnormaltime(Integer equip5abnormaltime) {
        this.equip5abnormaltime = equip5abnormaltime;
    }

    public Double getDetection1avg() {
        return detection1avg;
    }

    public void setDetection1avg(Double detection1avg) {
        this.detection1avg = detection1avg;
    }

    public Double getDetection1min() {
        return detection1min;
    }

    public void setDetection1min(Double detection1min) {
        this.detection1min = detection1min;
    }

    public Double getDetection1max() {
        return detection1max;
    }

    public void setDetection1max(Double detection1max) {
        this.detection1max = detection1max;
    }

    public Double getDetection2avg() {
        return detection2avg;
    }

    public void setDetection2avg(Double detection2avg) {
        this.detection2avg = detection2avg;
    }

    public Double getDetection2min() {
        return detection2min;
    }

    public void setDetection2min(Double detection2min) {
        this.detection2min = detection2min;
    }

    public Double getDetection2max() {
        return detection2max;
    }

    public void setDetection2max(Double detection2max) {
        this.detection2max = detection2max;
    }

    public Double getDetection3avg() {
        return detection3avg;
    }

    public void setDetection3avg(Double detection3avg) {
        this.detection3avg = detection3avg;
    }

    public Double getDetection3min() {
        return detection3min;
    }

    public void setDetection3min(Double detection3min) {
        this.detection3min = detection3min;
    }

    public Double getDetection3max() {
        return detection3max;
    }

    public void setDetection3max(Double detection3max) {
        this.detection3max = detection3max;
    }

    public Double getDetection4avg() {
        return detection4avg;
    }

    public void setDetection4avg(Double detection4avg) {
        this.detection4avg = detection4avg;
    }

    public Double getDetection4min() {
        return detection4min;
    }

    public void setDetection4min(Double detection4min) {
        this.detection4min = detection4min;
    }

    public Double getDetection4max() {
        return detection4max;
    }

    public void setDetection4max(Double detection4max) {
        this.detection4max = detection4max;
    }

    public Double getDetection5avg() {
        return detection5avg;
    }

    public void setDetection5avg(Double detection5avg) {
        this.detection5avg = detection5avg;
    }

    public Double getDetection5min() {
        return detection5min;
    }

    public void setDetection5min(Double detection5min) {
        this.detection5min = detection5min;
    }

    public Double getDetection5max() {
        return detection5max;
    }

    public void setDetection5max(Double detection5max) {
        this.detection5max = detection5max;
    }

    public Integer getEquip6normaltime() {
        return equip6normaltime;
    }

    public void setEquip6normaltime(Integer equip6normaltime) {
        this.equip6normaltime = equip6normaltime;
    }

    public Integer getEquip6abnormaltime() {
        return equip6abnormaltime;
    }

    public void setEquip6abnormaltime(Integer equip6abnormaltime) {
        this.equip6abnormaltime = equip6abnormaltime;
    }

    public Integer getEquip7normaltime() {
        return equip7normaltime;
    }

    public void setEquip7normaltime(Integer equip7normaltime) {
        this.equip7normaltime = equip7normaltime;
    }

    public Integer getEquip7abnormaltime() {
        return equip7abnormaltime;
    }

    public void setEquip7abnormaltime(Integer equip7abnormaltime) {
        this.equip7abnormaltime = equip7abnormaltime;
    }

    public Integer getEquip8normaltime() {
        return equip8normaltime;
    }

    public void setEquip8normaltime(Integer equip8normaltime) {
        this.equip8normaltime = equip8normaltime;
    }

    public Integer getEquip8abnormaltime() {
        return equip8abnormaltime;
    }

    public void setEquip8abnormaltime(Integer equip8abnormaltime) {
        this.equip8abnormaltime = equip8abnormaltime;
    }

    public Integer getEquip9normaltime() {
        return equip9normaltime;
    }

    public void setEquip9normaltime(Integer equip9normaltime) {
        this.equip9normaltime = equip9normaltime;
    }

    public Integer getEquip9abnormaltime() {
        return equip9abnormaltime;
    }

    public void setEquip9abnormaltime(Integer equip9abnormaltime) {
        this.equip9abnormaltime = equip9abnormaltime;
    }

    public Integer getEquip10normaltime() {
        return equip10normaltime;
    }

    public void setEquip10normaltime(Integer equip10normaltime) {
        this.equip10normaltime = equip10normaltime;
    }

    public Integer getEquip10abnormaltime() {
        return equip10abnormaltime;
    }

    public void setEquip10abnormaltime(Integer equip10abnormaltime) {
        this.equip10abnormaltime = equip10abnormaltime;
    }

    public Integer getEquip11normaltime() {
        return equip11normaltime;
    }

    public void setEquip11normaltime(Integer equip11normaltime) {
        this.equip11normaltime = equip11normaltime;
    }

    public Integer getEquip11abnormaltime() {
        return equip11abnormaltime;
    }

    public void setEquip11abnormaltime(Integer equip11abnormaltime) {
        this.equip11abnormaltime = equip11abnormaltime;
    }

    public Integer getEquip12normaltime() {
        return equip12normaltime;
    }

    public void setEquip12normaltime(Integer equip12normaltime) {
        this.equip12normaltime = equip12normaltime;
    }

    public Integer getEquip12abnormaltime() {
        return equip12abnormaltime;
    }

    public void setEquip12abnormaltime(Integer equip12abnormaltime) {
        this.equip12abnormaltime = equip12abnormaltime;
    }

    public Integer getEquip13normaltime() {
        return equip13normaltime;
    }

    public void setEquip13normaltime(Integer equip13normaltime) {
        this.equip13normaltime = equip13normaltime;
    }

    public Integer getEquip13abnormaltime() {
        return equip13abnormaltime;
    }

    public void setEquip13abnormaltime(Integer equip13abnormaltime) {
        this.equip13abnormaltime = equip13abnormaltime;
    }

    public Integer getEquip14normaltime() {
        return equip14normaltime;
    }

    public void setEquip14normaltime(Integer equip14normaltime) {
        this.equip14normaltime = equip14normaltime;
    }

    public Integer getEquip14abnormaltime() {
        return equip14abnormaltime;
    }

    public void setEquip14abnormaltime(Integer equip14abnormaltime) {
        this.equip14abnormaltime = equip14abnormaltime;
    }

    public Integer getEquip15normaltime() {
        return equip15normaltime;
    }

    public void setEquip15normaltime(Integer equip15normaltime) {
        this.equip15normaltime = equip15normaltime;
    }

    public Integer getEquip15abnormaltime() {
        return equip15abnormaltime;
    }

    public void setEquip15abnormaltime(Integer equip15abnormaltime) {
        this.equip15abnormaltime = equip15abnormaltime;
    }

    public Integer getEquip16normaltime() {
        return equip16normaltime;
    }

    public void setEquip16normaltime(Integer equip16normaltime) {
        this.equip16normaltime = equip16normaltime;
    }

    public Integer getEquip16abnormaltime() {
        return equip16abnormaltime;
    }

    public void setEquip16abnormaltime(Integer equip16abnormaltime) {
        this.equip16abnormaltime = equip16abnormaltime;
    }

    public Integer getEquip17normaltime() {
        return equip17normaltime;
    }

    public void setEquip17normaltime(Integer equip17normaltime) {
        this.equip17normaltime = equip17normaltime;
    }

    public Integer getEquip17abnormaltime() {
        return equip17abnormaltime;
    }

    public void setEquip17abnormaltime(Integer equip17abnormaltime) {
        this.equip17abnormaltime = equip17abnormaltime;
    }

    public Integer getEquip18normaltime() {
        return equip18normaltime;
    }

    public void setEquip18normaltime(Integer equip18normaltime) {
        this.equip18normaltime = equip18normaltime;
    }

    public Integer getEquip18abnormaltime() {
        return equip18abnormaltime;
    }

    public void setEquip18abnormaltime(Integer equip18abnormaltime) {
        this.equip18abnormaltime = equip18abnormaltime;
    }

    public Integer getEquip19normaltime() {
        return equip19normaltime;
    }

    public void setEquip19normaltime(Integer equip19normaltime) {
        this.equip19normaltime = equip19normaltime;
    }

    public Integer getEquip19abnormaltime() {
        return equip19abnormaltime;
    }

    public void setEquip19abnormaltime(Integer equip19abnormaltime) {
        this.equip19abnormaltime = equip19abnormaltime;
    }

    public Integer getEquip20normaltime() {
        return equip20normaltime;
    }

    public void setEquip20normaltime(Integer equip20normaltime) {
        this.equip20normaltime = equip20normaltime;
    }

    public Integer getEquip20abnormaltime() {
        return equip20abnormaltime;
    }

    public void setEquip20abnormaltime(Integer equip20abnormaltime) {
        this.equip20abnormaltime = equip20abnormaltime;
    }

    public Integer getEquip21normaltime() {
        return equip21normaltime;
    }

    public void setEquip21normaltime(Integer equip21normaltime) {
        this.equip21normaltime = equip21normaltime;
    }

    public Integer getEquip21abnormaltime() {
        return equip21abnormaltime;
    }

    public void setEquip21abnormaltime(Integer equip21abnormaltime) {
        this.equip21abnormaltime = equip21abnormaltime;
    }

    public Integer getEquip22normaltime() {
        return equip22normaltime;
    }

    public void setEquip22normaltime(Integer equip22normaltime) {
        this.equip22normaltime = equip22normaltime;
    }

    public Integer getEquip22abnormaltime() {
        return equip22abnormaltime;
    }

    public void setEquip22abnormaltime(Integer equip22abnormaltime) {
        this.equip22abnormaltime = equip22abnormaltime;
    }

    public Integer getEquip23normaltime() {
        return equip23normaltime;
    }

    public void setEquip23normaltime(Integer equip23normaltime) {
        this.equip23normaltime = equip23normaltime;
    }

    public Integer getEquip23abnormaltime() {
        return equip23abnormaltime;
    }

    public void setEquip23abnormaltime(Integer equip23abnormaltime) {
        this.equip23abnormaltime = equip23abnormaltime;
    }

    public Integer getEquip24normaltime() {
        return equip24normaltime;
    }

    public void setEquip24normaltime(Integer equip24normaltime) {
        this.equip24normaltime = equip24normaltime;
    }

    public Integer getEquip24abnormaltime() {
        return equip24abnormaltime;
    }

    public void setEquip24abnormaltime(Integer equip24abnormaltime) {
        this.equip24abnormaltime = equip24abnormaltime;
    }

    public Double getDetection6avg() {
        return detection6avg;
    }

    public void setDetection6avg(Double detection6avg) {
        this.detection6avg = detection6avg;
    }

    public Double getDetection6min() {
        return detection6min;
    }

    public void setDetection6min(Double detection6min) {
        this.detection6min = detection6min;
    }

    public Double getDetection6max() {
        return detection6max;
    }

    public void setDetection6max(Double detection6max) {
        this.detection6max = detection6max;
    }

    public Double getDetection7avg() {
        return detection7avg;
    }

    public void setDetection7avg(Double detection7avg) {
        this.detection7avg = detection7avg;
    }

    public Double getDetection7min() {
        return detection7min;
    }

    public void setDetection7min(Double detection7min) {
        this.detection7min = detection7min;
    }

    public Double getDetection7max() {
        return detection7max;
    }

    public void setDetection7max(Double detection7max) {
        this.detection7max = detection7max;
    }

    public Double getDetection8avg() {
        return detection8avg;
    }

    public void setDetection8avg(Double detection8avg) {
        this.detection8avg = detection8avg;
    }

    public Double getDetection8min() {
        return detection8min;
    }

    public void setDetection8min(Double detection8min) {
        this.detection8min = detection8min;
    }

    public Double getDetection8max() {
        return detection8max;
    }

    public void setDetection8max(Double detection8max) {
        this.detection8max = detection8max;
    }

    public Double getDetection9avg() {
        return detection9avg;
    }

    public void setDetection9avg(Double detection9avg) {
        this.detection9avg = detection9avg;
    }

    public Double getDetection9min() {
        return detection9min;
    }

    public void setDetection9min(Double detection9min) {
        this.detection9min = detection9min;
    }

    public Double getDetection9max() {
        return detection9max;
    }

    public void setDetection9max(Double detection9max) {
        this.detection9max = detection9max;
    }

    public Double getDetection10avg() {
        return detection10avg;
    }

    public void setDetection10avg(Double detection10avg) {
        this.detection10avg = detection10avg;
    }

    public Double getDetection10min() {
        return detection10min;
    }

    public void setDetection10min(Double detection10min) {
        this.detection10min = detection10min;
    }

    public Double getDetection10max() {
        return detection10max;
    }

    public void setDetection10max(Double detection10max) {
        this.detection10max = detection10max;
    }

    public Double getDetection11avg() {
        return detection11avg;
    }

    public void setDetection11avg(Double detection11avg) {
        this.detection11avg = detection11avg;
    }

    public Double getDetection11min() {
        return detection11min;
    }

    public void setDetection11min(Double detection11min) {
        this.detection11min = detection11min;
    }

    public Double getDetection11max() {
        return detection11max;
    }

    public void setDetection11max(Double detection11max) {
        this.detection11max = detection11max;
    }

    public Double getDetection12avg() {
        return detection12avg;
    }

    public void setDetection12avg(Double detection12avg) {
        this.detection12avg = detection12avg;
    }

    public Double getDetection12min() {
        return detection12min;
    }

    public void setDetection12min(Double detection12min) {
        this.detection12min = detection12min;
    }

    public Double getDetection12max() {
        return detection12max;
    }

    public void setDetection12max(Double detection12max) {
        this.detection12max = detection12max;
    }

    public Double getDetection13avg() {
        return detection13avg;
    }

    public void setDetection13avg(Double detection13avg) {
        this.detection13avg = detection13avg;
    }

    public Double getDetection13min() {
        return detection13min;
    }

    public void setDetection13min(Double detection13min) {
        this.detection13min = detection13min;
    }

    public Double getDetection13max() {
        return detection13max;
    }

    public void setDetection13max(Double detection13max) {
        this.detection13max = detection13max;
    }

    public Double getDetection14avg() {
        return detection14avg;
    }

    public void setDetection14avg(Double detection14avg) {
        this.detection14avg = detection14avg;
    }

    public Double getDetection14min() {
        return detection14min;
    }

    public void setDetection14min(Double detection14min) {
        this.detection14min = detection14min;
    }

    public Double getDetection14max() {
        return detection14max;
    }

    public void setDetection14max(Double detection14max) {
        this.detection14max = detection14max;
    }

    public Double getDetection15avg() {
        return detection15avg;
    }

    public void setDetection15avg(Double detection15avg) {
        this.detection15avg = detection15avg;
    }

    public Double getDetection15min() {
        return detection15min;
    }

    public void setDetection15min(Double detection15min) {
        this.detection15min = detection15min;
    }

    public Double getDetection15max() {
        return detection15max;
    }

    public void setDetection15max(Double detection15max) {
        this.detection15max = detection15max;
    }

    public Double getDetection16avg() {
        return detection16avg;
    }

    public void setDetection16avg(Double detection16avg) {
        this.detection16avg = detection16avg;
    }

    public Double getDetection16min() {
        return detection16min;
    }

    public void setDetection16min(Double detection16min) {
        this.detection16min = detection16min;
    }

    public Double getDetection16max() {
        return detection16max;
    }

    public void setDetection16max(Double detection16max) {
        this.detection16max = detection16max;
    }

	public String getSewagename() {
		return sewagename;
	}

	public void setSewagename(String sewagename) {
		this.sewagename = sewagename;
	}

	public String getAdminname() {
		return adminname;
	}

	public void setAdminname(String adminname) {
		this.adminname = adminname;
	}

	public Integer getAreaid() {
		return areaid;
	}

	public void setAreaid(Integer areaid) {
		this.areaid = areaid;
	}

	public Integer getDay() {
		return day;
	}

	public void setDay(Integer day) {
		this.day = day;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public Double getWater() {
		return water;
	}

	public void setWater(Double water) {
		this.water = water;
	}

	public List<StatisticDayVO> getStatisticDayVOLists() {
		return statisticDayVOLists;
	}

	public void setStatisticDayVOLists(List<StatisticDayVO> statisticDayVOLists) {
		this.statisticDayVOLists = statisticDayVOLists;
	}

	public Double getTonnage() {
		return tonnage;
	}

	public void setTonnage(Double tonnage) {
		this.tonnage = tonnage;
	}

	public Double getEquipment1energycost() {
		return equipment1energycost;
	}

	public void setEquipment1energycost(Double equipment1energycost) {
		this.equipment1energycost = equipment1energycost;
	}

	public Double getEquipment2energycost() {
		return equipment2energycost;
	}

	public void setEquipment2energycost(Double equipment2energycost) {
		this.equipment2energycost = equipment2energycost;
	}

	public Double getEquipment3energycost() {
		return equipment3energycost;
	}

	public void setEquipment3energycost(Double equipment3energycost) {
		this.equipment3energycost = equipment3energycost;
	}

	public Double getEquipment4energycost() {
		return equipment4energycost;
	}

	public void setEquipment4energycost(Double equipment4energycost) {
		this.equipment4energycost = equipment4energycost;
	}

    public double getAvgWater() {
        return avgWater;
    }

    public void setAvgWater(double avgWater) {
        this.avgWater = avgWater;
    }

    public Double getEquipment5energycost() {
        return equipment5energycost;
    }

    public void setEquipment5energycost(Double equipment5energycost) {
        this.equipment5energycost = equipment5energycost;
    }
}