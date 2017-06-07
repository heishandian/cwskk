function chart2(){
        // 路径配置
	    require.config({
	        paths: {
	            echarts: getContextPath()+'/js/echarts'
	        }
	    });
	    // 使用
	    	require(
	        [
	            'echarts',
	            'echarts/chart/line'
	        ],
            function (ec) {
                // 基于准备好的dom，初始化echarts图表
                mychart2 = ec.init(document.getElementById('chart2')); 
                var option = {
		                	    tooltip : {
		                	        trigger: 'item'
		                	    },
							    title : {
							        text: 'PH当日曲线图'
							    },
							    legend: {
							        data:['PH']
							    },
							    xAxis : [
							        {
							            type : 'category',
							            boundaryGap : false,
							            data : initxtoken(),
							        }
							    ],
							    
							    yAxis : [
							        {
							            type : 'value',
							            name : 'PH',
							            scale:true,
							            boundaryGap: [0.2, 0.2]
/*							            min:3,
							            max:14,
							            splitNumber:11*/
							        }
							    ],
							    series : [
							        {
							            name:'PH',
							            type:'line',
							            xAxisIndex: 0,//非常重要
								        yAxisIndex: 0,
							            data:initxdata(),
							        }
							    ]
						};
                mychart2.setOption(option); 
            }
        );
}