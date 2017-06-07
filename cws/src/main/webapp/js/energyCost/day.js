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
                'echarts/chart/bar' // 使用柱状图就加载bar模块，按需加载
                //'echarts/chart/line'
            ],
            function (ec) {
                // 基于准备好的dom，初始化echarts图表
                mychart1 = ec.init(document.getElementById('chart1')); 
                
                var option = {
                					color:['#2894ff'],
								    title : {
								        text: '站点能耗'//,
								    },
								    legend: {
								        //data:['Water','Tonnage']
								    	data:['EnergyCost']
								    },
								    xAxis : [
								        {
								            type : 'category',
								            //boundaryGap : false, //柱状图 要注释掉
								            data :['曝气机','提升泵','回流泵','加药泵','排水泵']
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
								            name:'EnergyCost',
								            type:'bar',
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