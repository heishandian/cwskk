function chart10(){
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
                mychart10 = ec.init(document.getElementById('chart10')); 
                var option = {
			            	    tooltip : {
			            	        trigger: 'item'
			            	    },
							    title : {
							        text: 'COD当日曲线图'
							    },
							    legend: {
							        data:['COD']
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
							            name : 'COD',
							            boundaryGap: [0.2, 0.2]
							        }
							    ],
							    series : [
							        {
							            name:'COD',
							            type:'line',
							            xAxisIndex: 0,//非常重要
								        yAxisIndex: 0,
							            data:initxdata(),
							        }
							    ]
						};
                mychart10.setOption(option); 
            }
        );
}