

function chart6(){
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
			'echarts/chart/bar' // 使用柱状图就加载bar模块，按需加载
			//'echarts/chart/line'
		],
		function (ec) {
			// 基于准备好的dom，初始化echarts图表
			mychart6 = ec.init(document.getElementById('chart6'));

			var option = {
				color:['#2894ff'],
				title : {
					text: '流量当日曲线图'//,
				},
				legend: {
					data:['流量']
				},
				xAxis : [
					{
						type : 'category',
						boundaryGap : false,
						data :initxtoken_Jiangdu_flow()
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
						name:'流量',
						type:'bar',
						scale: true,
						xAxisIndex: 0,//非常重要 一定要注意注意
						yAxisIndex: 0,
						data:initxdata_Jiangdu_flow()
					}
				]
			};
			// 为echarts对象加载数据
			mychart6.setOption(option);
		}
	);
}