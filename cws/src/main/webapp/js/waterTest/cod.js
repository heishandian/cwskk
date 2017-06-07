function chart1(){
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
                //'echarts/chart/bar', // 使用柱状图就加载bar模块，按需加载
                'echarts/chart/line'
            ],
            function (ec) {
                // 基于准备好的dom，初始化echarts图表
                mychart1 = ec.init(document.getElementById('chart1')); 
                
                var option = {
								    title : {
								        text: 'COD'//,,
								    },
								    legend: {
								        data:['进水水质COD','出水水质COD']
								    },
								    xAxis : [
								        {
								            type : 'category',
								            boundaryGap : false,
								            data :initxtoken(),
								        }
								    ],
								    yAxis : [
								        {
								            type : 'value',
								            scale: true,
								            //name : 'Flow',
								            boundaryGap: [0.2, 0.2]
								        }
								    ],
								    series : [
								        {
								            name:'进水水质COD',
								            type:'line',
								            xAxisIndex: 0,//非常重要 一定要注意注意
								            yAxisIndex: 0,
 								            data:initxdata()
								        },
								        {
								            name:'出水水质COD',
								            type:'line',
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