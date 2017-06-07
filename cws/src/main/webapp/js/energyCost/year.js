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
                					color:['#2894ff','#019858','#7CFC00','00EE00'],
								    title : {
								        text: '站点能耗年记录'//,
								    },
								    legend: {
								        data:['Equipment1','Equipment2','Equipment3','Equipment4']
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
								            name:'Equipment1',
								            type:'line',
								            scale: true,
								            xAxisIndex: 0,//非常重要 一定要注意注意
								            yAxisIndex: 0,
 								            data:initxdata()
								        },
								        {
								            name:'Equipment2',
								            type:'line',
								            scale: true,
/*								            itemStyle: {
								                normal: {
								                   lineStyle:{
								                	   color:'#019858'
								                   }
								                }
								            },*/
								            xAxisIndex: 0,//非常重要 一定要注意注意
								            yAxisIndex: 0,
								            data:initxdata()
								        },
								        {
								            name:'Equipment3',
								            type:'line',
								            scale: true,
								            xAxisIndex: 0,//非常重要 一定要注意注意
								            yAxisIndex: 0,
 								            data:initxdata()
								        },
								        {
								            name:'Equipment4',
								            type:'line',
								            scale: true,
								            xAxisIndex: 0,//非常重要 一定要注意注意
								            yAxisIndex: 0,
 								            data:initxdata()
								        },
								    ]
						};
                // 为echarts对象加载数据 
                mychart1.setOption(option); 
            }
        );
 }  