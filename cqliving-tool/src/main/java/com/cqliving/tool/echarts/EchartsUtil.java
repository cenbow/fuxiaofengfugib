/**
 * Copyright (c) 2016 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.cqliving.tool.echarts;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.github.abel533.echarts.Legend;
import com.github.abel533.echarts.Toolbox;
import com.github.abel533.echarts.Tooltip;
import com.github.abel533.echarts.axis.Axis;
import com.github.abel533.echarts.axis.AxisLabel;
import com.github.abel533.echarts.axis.CategoryAxis;
import com.github.abel533.echarts.axis.ValueAxis;
import com.github.abel533.echarts.code.MarkType;
import com.github.abel533.echarts.code.Tool;
import com.github.abel533.echarts.code.Trigger;
import com.github.abel533.echarts.data.LegendData;
import com.github.abel533.echarts.data.PointData;
import com.github.abel533.echarts.json.GsonOption;
import com.github.abel533.echarts.option.NoDataLoadingOption;
import com.github.abel533.echarts.series.MarkLine;
import com.github.abel533.echarts.series.MarkPoint;
import com.github.abel533.echarts.series.Series;
import com.github.abel533.echarts.style.TextStyle;
import com.google.common.collect.Lists;

/**
 * Title:
 * <p>
 * Description:
 * </p>
 * Copyright (c) CQLIVING 2016
 * 
 * @author fuxiaofeng on 2016年11月8日
 */
public class EchartsUtil {

	public static final String SERIES_NAME = "name";
	public static final String SERIES_VALUE = "value";
	public static final String SERIES_COLOR = "lineColor";
	public static final String DEFAULT_NUM = "0";
	public static final String ICON = "diamond";
	/**
	 * Title:
	 * <p>Description:获取简单折线图图表配置option</p>
	 * @author fuxiaofeng on 2016年11月8日
	 * @param echartsName 图表名称
	 * @param yaxisUnit  y轴单位
	 * @param xaxisData x轴坐标数据
	 * @param seriesData  Map<String,List<Map<String,String>>   key : 每条折线图数据名称  value : 每条折线图数据,value的map值里面key必须有name和value
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static GsonOption getSimpleLineEcharts(String echartsName, String yaxisUnit, List<Map<Integer,String>> xaxisData,
			Map<String,List<Map<String,String>>> seriesData) {

		GsonOption option = EchartsUtil.getSimpleEchartsOption(echartsName, yaxisUnit, xaxisData,seriesData);
		//显示最大值和最小值
		MarkPoint markPoint = new MarkPoint();
		PointData markPointPd = new PointData();
		markPointPd.setType(MarkType.min);
		markPointPd.setName("最小值");
		PointData markPointPdMx = new PointData();
		markPointPdMx.setType(MarkType.max);
		markPointPdMx.setName("最大值");
		List<PointData> markPointPds = Lists.newArrayList();
		markPointPds.add(markPointPd);
		markPointPds.add(markPointPdMx);
		markPoint.setData(markPointPds);
		//显示平均值
		MarkLine markLine = new MarkLine();
		List<PointData> mlPds = Lists.newArrayList();
		PointData mlPd = new PointData();
		mlPd.setType(MarkType.average);
		mlPd.setName("平均值");
		mlPds.add(mlPd);
		markLine.setData(mlPds);
		
		if(null != seriesData && !seriesData.isEmpty()){
			List<Series> lineCharts = Lists.newArrayList();
			for(String key : seriesData.keySet()){
				List<Map<String,String>> data = seriesData.get(key);
				ExtLine lineSeries = new ExtLine();
				List<PointData> seriesDatas = Lists.newArrayList();
				//String color = "";
				for(Map<String,String> v : data){
					PointData pd = new PointData(v.get(SERIES_NAME),v.get(SERIES_VALUE));
					//pd.setSymbol(ICON);
					//color = v.get(SERIES_COLOR);
					seriesDatas.add(pd);
					lineSeries.setData(seriesDatas);
				}
				
				//设置线条样式
				/*LineStyle lineStyle = new LineStyle();
				Normal normal = new Normal();
				normal.setColor(color);
				lineStyle.setNormal(normal);*/
				//lineSeries.setLineStyle(lineStyle);
				lineSeries.setName(key.split("&")[0]);
				lineSeries.setMarkPoint(markPoint);
				//lineSeries.setMarkLine(markLine);
				lineCharts.add(lineSeries);
			}
			option.series(lineCharts);
		}
		return option;
	}

	@SuppressWarnings("rawtypes")
	public static GsonOption getSimpleEchartsOption(String echartsName, String yaxisUnit,List<Map<Integer,String>> xaxisData,Map<String,List<Map<String,String>>> seriesData) {
		GsonOption option = new GsonOption();
		CategoryAxis xAxis = new CategoryAxis();
		xAxis.setBoundaryGap(false);
		List<PointData> xdata = Lists.newArrayList();
		for(Map<Integer,String> map : xaxisData){
			for(Entry<Integer,String> et : map.entrySet()){
				PointData xpd = new PointData();
				xpd.setValue(et.getValue());
				xdata.add(xpd);
			}
		}
		xAxis.setData(xdata);
		ValueAxis yAxis = new ValueAxis();
		AxisLabel al = new AxisLabel();
		al.setFormatter(String.format("{%s}%s", yAxis.getType(), yaxisUnit));
		yAxis.setAxisLabel(al);
		Tooltip tooltip = new Tooltip();
		tooltip.setTrigger(Trigger.axis);
		Toolbox toolbox = new Toolbox();
		toolbox.setShow(true);
		toolbox.feature(Tool.saveAsImage, Tool.magicType);
		
		//设置横坐标
		List<Axis> xAxisList = Lists.newArrayList();
		xAxisList.add(xAxis);
		option.setxAxis(xAxisList);
		//设置纵坐标
		List<Axis> yAxisList = Lists.newArrayList();
		yAxisList.add(yAxis);
		option.setyAxis(yAxisList);
		//设置提示框
		option.setTooltip(tooltip);
		//设置工具箱
		option.setToolbox(toolbox);
		
		if(null == seriesData || seriesData.isEmpty()){
			//无数据时加载		
			NoDataLoadingOption no = new NoDataLoadingOption();
			option.setNoDataLoadingOption(no);
		}else{
			//设置图例
			Legend legend = new Legend();
			List<LegendData> legd = Lists.newArrayList();
			for(String key : seriesData.keySet()){
				String[] keyarr = key.split("&");
				if(keyarr.length>=2){
					TextStyle textStyle = new TextStyle();
					textStyle.setColor(keyarr[1]);
					LegendData legendData = new LegendData(keyarr[0],textStyle,ICON);
					legd.add(legendData);
				}else{
					LegendData legendData = new LegendData(key);
					legd.add(legendData);
				}
			}
			legend.setData(legd);
			option.setLegend(legend);
		}
		return option;
	}

}
