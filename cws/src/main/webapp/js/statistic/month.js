function chart1(){
        // 路径配置
        require.config({
            paths: {
                echarts: 'http://echarts.baidu.com/build/dist'
            }
        });
        // 使用
        	require(
            [
                'echarts',
                //'echarts/chart/bar', // 使用柱状图就加载bar模块，按需加载
                'echarts/chart/line'
            ],
            function (ec) {
                // 基于准备好的dom，初始化echarts图表
                mychart1 = ec.init(document.getElementById('chart1')); 
                
                var option = {
                					color:['#1E90FF','#CD0000','#EEEE00'], //蓝色，红色，黄色
								    title : {
								        text: '月处理水量（吨）'//,
								    },
								    legend: {
								        data:['水量值','设计值','平均值']
								    },
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
								        }
								    ],
								    series : [
								        {
								            name:'水量值',
								            type:'line',
								            scale: true,
								            xAxisIndex: 0,//非常重要 一定要注意注意
								            yAxisIndex: 0,
 								            data:initxdata()
								        },
								        {
								            name:'设计值',
											type:'line',
											scale: true,
											xAxisIndex: 0,//非常重要 一定要注意注意
											yAxisIndex: 0,
											data:initxdata()
								        },
										{
											name:'平均值',
											type:'line',
											scale: true,
											xAxisIndex: 0,//非常重要 一定要注意注意
											yAxisIndex: 0,
											data:initxdata()
										}
								    ]
						};
                // 为echarts对象加载数据 
                mychart1.setOption(option); 
            }
        );
 }  