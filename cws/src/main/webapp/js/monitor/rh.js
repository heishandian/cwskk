function chart14(){
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
                mychart14 = ec.init(document.getElementById('chart14')); 
                var option = {
			            	    tooltip : {
			            	        trigger: 'item'
			            	    },
							    title : {
							        text: 'RH当日曲线图'
							    },
							    legend: {
							        data:['RH']
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
							            name : 'RH',
							            boundaryGap: [0.2, 0.2]
							        }
							    ],
							    series : [
							        {
							            name:'RH',
							            type:'line',
							            xAxisIndex: 0,//非常重要
								        yAxisIndex: 0,
							            data:initxdata(),
							        }
							    ]
						};
                mychart14.setOption(option); 
            }
        );
}