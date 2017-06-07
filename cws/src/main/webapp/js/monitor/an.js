function chart11(){
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
                mychart11 = ec.init(document.getElementById('chart11')); 
                var option = {
			            	    tooltip : {
			            	        trigger: 'item'
			            	    },
							    title : {
							        text: 'AN当日曲线图'
							    },
							    legend: {
							        data:['AN']
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
							            scale: true,
							            name : 'AN',
							            boundaryGap: [0.2, 0.2]
							        }
							    ],
							    series : [
							        {
							            name:'AN',
							            type:'line',
							            xAxisIndex: 0,//非常重要
								        yAxisIndex: 0,
							            data:initxdata(),
							        }
							    ]
						};
                mychart11.setOption(option); 
            }
        );
}