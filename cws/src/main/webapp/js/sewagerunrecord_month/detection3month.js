function chart7(){
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
                mychart7 = ec.init(document.getElementById('chart7')); 
                
                var option = {
			                	    tooltip : {
			                	        trigger: 'item'
			                	    },
								    title : {
								        text: 'ORP月报'//,
								        //subtext: '纯属虚构'
								    },
								    legend: {
								        data:['平均值','最大值','最小值']
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
								            name:'平均值',
								            type:'line',
								            xAxisIndex: 0,//非常重要 一定要注意注意
								            yAxisIndex: 0,
 								            data:initxdata()
								        },
								        {
								            name:'最大值',
								            type:'line',
								            xAxisIndex: 0,//非常重要 一定要注意注意
								            yAxisIndex: 0,
 								            data:initxdata()
								        },
								        {
								            name:'最小值',
								            type:'line',
								            xAxisIndex: 0,//非常重要 一定要注意注意
								            yAxisIndex: 0,
 								            data:initxdata()
								        }
								    ]
						};
                // 为echarts对象加载数据 
                mychart7.setOption(option); 
            }
        );
 }  