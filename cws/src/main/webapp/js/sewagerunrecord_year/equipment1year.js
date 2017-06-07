function chart11(){
        // 路径配置
        require.config({
            paths: {
                echarts: getContextPath()+'/js/echarts'
            }
        });
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
								        text: '曝气机运行/故障时间年报'//,
								        //subtext: '纯属虚构'
								    },
								    legend: {
								        data:['运行时间','故障时间']
								    },
								    //calculable : true,
								    xAxis : [
								        {
								            type : 'category',
								            boundaryGap : false,
								            data :initxtokenyear()
								        }
								    ],
								    yAxis : [
								        {
								            type : 'value',
								            scale: true
								            //name : 'T',
								            //boundaryGap: [0.2, 0.2]/*, 这里暂时不设置
/*								            min:10,
								            max:40,
								            splitNumber:10*/
								        }
								    ],
								    series : [
								        {
								            name:'运行时间',
								            type:'line',
								            xAxisIndex: 0,//非常重要 一定要注意注意
								            yAxisIndex: 0,
 								            data:initxdatayear()
								        },
								        {
								            name:'故障时间',
								            type:'line',
								            xAxisIndex: 0,//非常重要 一定要注意注意
								            yAxisIndex: 0,
 								            data:initxdatayear()
								        }
								    ]
						};
                // 为echarts对象加载数据 
                mychart11.setOption(option); 
            }
        );
 }  