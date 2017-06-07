package com.yaoli.util;

import java.util.Random;

public class ProduceSql {
	
/*	
	if(@detection1 < @detection1dl or @detection1 > @detection1ul)
		if not exists(select * from detection_data_abnormal where sewageID = @sewageID and detectionno = 1 and CONVERT(varchar(11),testingtime,111) = CONVERT(varchar(11),GETDATE() ,111) )
		begin
			insert into detection_data_abnormal(sewageID,testingtime,detectionnum,detectionno)
			values(@sewageid,@testtime,@detection1,1)
		end
		*/
	
	public static void main(String[] args) {
		for (int i = 18; i <= 31; i++) {
			//System.out.println("declare @detection"+i+"ul real,@detection"+i+"dl real");
			//System.out.print("@detection"+i+"=detection"+i+",");
			//System.out.println("@detection"+i+"ul=detection"+i+"UL,@detection"+i+"dl=detection"+i+"DL,");
			//System.out.println("insert into statistic_day(sewageid,testingtime,water) values(12,'2016-08-"+i+" 00:00:00',"+(new Random().nextDouble()*10+6)+")");
		}
		
		
/*		for (int i = 1; i <=5; i++) {
			System.out.println("	if (@detection"+i+" < @detection"+i+"dl or @detection"+i+" > @detection"+i+"ul)");
			System.out.println("	begin");
			System.out.println("		declare @tempdetectionid"+i+" bigint");
			System.out.println("		select @tempdetectionid"+i+"=detectionID from detection_data_abnormal where sewageID = @sewageID and detectionno = "+i+" and CONVERT(varchar(11),testingtime,111) = CONVERT(varchar(11),GETDATE() ,111) ");
			System.out.println("		if @tempdetectionid"+i+" is null");
			System.out.println("			begin");
			System.out.println("				insert into detection_data_abnormal(sewageID,testingtime,detectionnum,detectionno,isrepaired,lasttestingtime)");
			System.out.println("				values(@sewageid,@testtime,@detection"+i+","+i+",0,@testtime)");
			System.out.println("			end");
			System.out.println("		else");
			System.out.println("			begin");
			System.out.println("				update detection_data_abnormal set lasttestingtime = @testtime where detectionID = @tempdetectionid"+i+"");
			System.out.println("			end");
			System.out.println("	end");
			System.out.println("	---------------------------------------------------------------------------------");
		}
*/
/*		for (int i = 10; i <=14; i++) {
			System.out.println("	if (@detection"+i+" < @detection"+i+"dl or @detection"+i+" > @detection"+i+"ul)");
			System.out.println("	begin");
			System.out.println("		declare @tempdetectionid"+i+" bigint");
			System.out.println("		select @tempdetectionid"+i+"=detectionID from detection_data_abnormal where sewageID = @sewageID and detectionno = "+i+" and CONVERT(varchar(11),testingtime,111) = CONVERT(varchar(11),GETDATE() ,111) ");
			System.out.println("		if @tempdetectionid"+i+" is null");
			System.out.println("			begin");
			System.out.println("				insert into detection_data_abnormal(sewageID,testingtime,detectionnum,detectionno,isrepaired,lasttestingtime)");
			System.out.println("				values(@sewageid,@testtime,@detection"+i+","+i+",0,@testtime)");
			System.out.println("			end");
			System.out.println("		else");
			System.out.println("			begin");
			System.out.println("				update detection_data_abnormal set lasttestingtime = @testtime where detectionID = @tempdetectionid"+i+"");
			System.out.println("			end");
			System.out.println("	end");
			System.out.println("	---------------------------------------------------------------------------------");
		}*/
		
/*		for (int i = 1; i <=21; i++) {
			//System.out.print("@state"+i+"=equipment"+i+"state,");
			//sum(temp1.detection1) as detection1
			System.out.print("sum(temp1.detection"+i+") as detection"+i+",");
		}*/
/*		for (int i = 6; i <= 24; i++) {
			System.out.println("sum(equip"+i+"normaltime) as equip"+i+"normaltime,");
			System.out.println("sum(equip"+i+"abnormaltime) as equip"+i+"abnormaltime,");
		}*/
		
		//@detection1avg=detection1avg,@detection1min=detection1min,@detection1max=detection1max,
/*		for (int i = 6; i <= 14; i++) {
			System.out.println("detection"+i+"avg=@detection"+i+"avg,");
			System.out.println("detection"+i+"min=@detection"+i+"min,");
			System.out.println("detection"+i+"max=@detection"+i+"max,");
		}*/
/*		if @state1=3
				if not exists(select * from run_data_abnormal where sewageID = @sewageID and equipmentno = 1 and CONVERT(varchar(12),testingtime,111) = CONVERT(varchar(12),GETDATE() ,111) )
				begin
					insert into dbo.run_data_abnormal(sewageid,testingtime,equipmentstate,equipmentno,isrepaired) values(@sewageid,@testtime,@state1,1,0)
				end*/
/*		for (int i = 6; i <= 7; i++) {
			System.out.println("		if @state"+i+"=8");
			System.out.println("		begin");
			System.out.println("			declare @temprunid"+i+"low bigint");
			System.out.println("			select @temprunid"+i+"low=runid from run_data_abnormal where sewageID = @sewageID and equipmentno = "+i+" and CONVERT(varchar(12),testingtime,111) = CONVERT(varchar(12),GETDATE() ,111) ");
			System.out.println("			if @temprunid"+i+"low is null");
			System.out.println("				begin");
			System.out.println("					insert into dbo.run_data_abnormal(sewageid,testingtime,equipmentstate,equipmentno,isrepaired,lasttestingtime) values(@sewageid,@testtime,@state"+i+","+i+",0,@testtime)");
			System.out.println("				end");
			System.out.println("			else");
			System.out.println("				begin");
			System.out.println("					update dbo.run_data_abnormal set lasttestingtime = @testtime where runid = @temprunid"+i+"low");
			System.out.println("				end");
			System.out.println("		end");
			System.out.println("		--------------------------------------------------------------------------------");
			System.out.println("		if @state"+i+"=1");
			System.out.println("		begin");
			System.out.println("			declare @temprunid"+i+"high bigint");
			System.out.println("			select @temprunid"+i+"high=runid from run_data_abnormal where sewageID = @sewageID and equipmentno = "+i+" and CONVERT(varchar(12),testingtime,111) = CONVERT(varchar(12),GETDATE() ,111) ");
			System.out.println("			if @temprunid"+i+"high is null");
			System.out.println("				begin");
			System.out.println("					insert into dbo.run_data_abnormal(sewageid,testingtime,equipmentstate,equipmentno,isrepaired,lasttestingtime) values(@sewageid,@testtime,@state"+i+","+i+",0,@testtime)");
			System.out.println("				end");
			System.out.println("			else");
			System.out.println("				begin");
			System.out.println("					update dbo.run_data_abnormal set lasttestingtime = @testtime where runid = @temprunid"+i+"high");
			System.out.println("				end");
			System.out.println("		end");
			System.out.println("		--------------------------------------------------------------------------------");
		}*/
/*		for (int i = 8; i <= 18; i++) {
			System.out.println("		if @state"+i+"=3");
			System.out.println("		begin");
			System.out.println("			declare @temprunid"+i+" bigint");
			System.out.println("			select @temprunid"+i+"=runid from run_data_abnormal where sewageID = @sewageID and equipmentno = "+i+" and CONVERT(varchar(12),testingtime,111) = CONVERT(varchar(12),GETDATE() ,111) ");
			System.out.println("			if @temprunid"+i+" is null");
			System.out.println("				begin");
			System.out.println("					insert into dbo.run_data_abnormal(sewageid,testingtime,equipmentstate,equipmentno,isrepaired,lasttestingtime) values(@sewageid,@testtime,@state"+i+","+i+",0,@testtime)");
			System.out.println("				end");
			System.out.println("			else");
			System.out.println("				begin");
			System.out.println("					update dbo.run_data_abnormal set lasttestingtime = @testtime where runid = @temprunid"+i+"");
			System.out.println("				end");
			System.out.println("		end");
			System.out.println("		--------------------------------------------------------------------------------");
		}*/
/*		for (int i = 8; i <= 21; i++) {
			System.out.println("	if @state"+i+" = 1 ");
			System.out.println("		begin");
			System.out.println("			update dbo.statistic_day set equip"+i+"normaltime =equip"+i+"normaltime + @time where sewageID=@sewageid and convert(varchar(11),testingtime,120) = convert(varchar(11),@testtime,120)");
			System.out.println("		end");
			System.out.println("	if @state"+i+" = 3");
			System.out.println("		begin");
			System.out.println("			update dbo.statistic_day set equip"+i+"abnormaltime =equip"+i+"abnormaltime + @time where sewageID=@sewageid and convert(varchar(11),testingtime,120) = convert(varchar(11),@testtime,120)");
			System.out.println("		end");
		}*/
/*		for (int i = 6; i < 21; i++) {
			System.out.print("@state"+i+" int,");
		}*/
		
/*		for (int i = 10; i <= 14; i++) {
			System.out.println("			if @detection"+i+"avg is null");
			System.out.println("				begin");
			System.out.println("					set @detection"+i+"avg = @detection"+i);
			System.out.println("					set @detection"+i+"min = @detection"+i);
			System.out.println("					set @detection"+i+"max = @detection"+i);
			System.out.println("				end");
		}*/
/*		for (int i = 6; i <= 14; i++) {
			System.out.println("			if @detection"+i+" > @detection"+i+"max");
			System.out.println("				set @detection"+i+"max = @detection"+i);
		}*/
		
		//set @detection5avg = (@detection5avg*(@count-1)+@detection5)/@count
		for (int i = 10; i <= 14; i++) {
			//System.out.println("set @detection"+i+"avg = (@detection"+i+"avg*(@count-1)+@detection"+i+")/@count");
			//System.out.println("declare @detection"+i+"avg float,@detection"+i+"min float,@detection"+i+"max float");
/*			System.out.print("@detection"+i+"avg = detection"+i+"avg,");
			System.out.print("@detection"+i+"min = detection"+i+"min,");
			System.out.println("@detection"+i+"max = detection"+i+"max,");*/
			System.out.println("detection"+i+"avg = @detection"+i+"avg,");
			System.out.println("detection"+i+"min = @detection"+i+"min,");
			System.out.println("detection"+i+"max = @detection"+i+"max,");
		}
		
/*		for (int j = 10; j <= 14; j++) {
			//System.out.println("if @detection"+j+" > @detection"+j+"max");
			//System.out.println("	set @detection"+j+"max = @detection"+j+"");
			System.out.println("if @detection"+j+" < @detection"+j+"min");
			System.out.println("	set @detection"+j+"min = @detection"+j+"");
			
		}*/
	}
	/*System.out.print("@detection"+i+"avg,"+"@detection"+i+"min,"+"@detection"+i+"max,");*/
/*	System.out.println("detection"+i+"avg = @detection"+i+"avg,");
	System.out.println("detection"+i+"min = @detection"+i+"min,");
	System.out.println("detection"+i+"max = @detection"+i+"max,");*/
	/*System.out.println("declare @detection"+i+"avg float,@detection"+i+"min float,@detection"+i+"min float");*/
	
/*	System.out.println("alter table statistic_day add detection"+i+"avg float default 0.00");
	System.out.println("alter table statistic_day add detection"+i+"min float default 0.00");
	System.out.println("alter table statistic_day add detection"+i+"max float default 0.00");*/
}
