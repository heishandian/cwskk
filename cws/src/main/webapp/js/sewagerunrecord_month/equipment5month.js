function chart9(){
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
                mychart9 = ec.init(document.getElementById('chart9'));
                
                var option = {
			                	    tooltip : {
			                	        trigger: 'item'
			                	    },
								    title : {
								        text: '加药泵运行/故障时间月报'//,
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
								            data :initxtoken()
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
 								            data:initxdata()
								        },
								        {
								            name:'故障时间',
								            type:'line',
								            xAxisIndex: 0,//非常重要 一定要注意注意
								            yAxisIndex: 0,
 								            data:initxdata()
								        }
								    ]
						};
                // 为echarts对象加载数据 
				mychart9.setOption(option);
            }
        );
 }  