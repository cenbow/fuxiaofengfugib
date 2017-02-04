package com.org.weixin.module.ahjy.common;

import java.util.List;
import java.util.Random;

import com.org.weixin.module.ahjy.domain.AhjyAward;

public class Probability {
	/***
	 * 根据中奖概率集合，获取中奖结果,
	 * 其中list中对象必须按getNum升序排序
	 * <p>Description:</p>
	 * @param list List<ProbaPercent>中奖概率集合
     * @return randomMax
	 * @return ProbaPercent对象的desc字段，如果没有中奖，则返回空
	 * @author YUWU on 2016-04-01
	 */
	public static AhjyAward probability(List<AhjyAward> list,int randomMax){
		int random = new Random().nextInt(randomMax);
		int count = 0;
		for(int i = 0 ; i < list.size() ; i++){
			if(random >= count && random < list.get(i).getNum() + count){
				return list.get(i);
			}else{
				count = count + list.get(i).getNum();
			}
		}
		return null;
	}
	
	/***
	 * 根据传入参数的命中率百分比，返回是否命中
	 * <p>Description:</p>
	 * @param percent 命中率的百分比,百分比取值范围只能在闭区间[1,100]之间
	 * @return {true:命中,false:没有命中}
	 * @author YUWU on 2016-04-01
	 */
	public static boolean probability(int percent){
		if(percent < 0 || percent > 100){
			throw new IllegalArgumentException("百分比取值范围只能在闭区间[1,100]之内");
		}
		int random = new Random().nextInt(100);
		if(random < percent){
			return true;
		}
		return false;
	}
	
	/**
	 * 生成一个区间段内的随机正整数
	 * <p>Description:</p>
	 * @param start 生成的随机数最大值
	 * @param end 生成的随机数最小值
	 * @return
	 * @author YUWU on 2016-04-01
	 */
	public static int getRandom(int start,int end){
		return new Random().nextInt(end - start) + start;
	}
}
