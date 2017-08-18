package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

import entity.Goods;
import pojo.News;

public class Tool {
	
	public static boolean isArrayContainsElem(Object[] objs, Object elem){
		return Arrays.asList(objs).contains(elem);
	}
	
//	public static boolean isArrayContainsElem(String[] strArray, String str){
//		return Arrays.asList(strArray).contains(str);
//	}
	
	public static String subStringOfNChar(String str, int length){
		String subStr = null;
		try {
			subStr = str.substring(0, length);
			subStr = subStr + " ...";
		} catch (Exception e) {
			return str;
		}
		return subStr;
	}
	
	public static String subStringOf20(String str){
		return subStringOfNChar(str, 20);
	}
	
	public static List<Goods> goodsListToRandom(List<Goods> list){
		List<Goods> goodsList = new ArrayList<Goods>();
		List<Integer> nums = new ArrayList<Integer>();
		Random random = new Random();
		for (int i = 0; i < list.size(); i++) {
			int index = random.nextInt(list.size());
			if(nums.contains(index)){
				i--;
				continue;
			}
			nums.add(index);
			goodsList.add(list.get(index));
		}
		return goodsList;
	}
	
	public static List<News> newsListToRandom(List<News> list){
		List<News> newList = new ArrayList<News>();
		List<Integer> nums = new ArrayList<Integer>();
		Random random = new Random();
		for (int i = 0; i < list.size(); i++) {
			int index = random.nextInt(list.size());
			if(nums.contains(index)){
				i--;
				continue;
			}
			nums.add(index);
			newList.add(list.get(index));
		}
		return newList;
	}
	
	public static String dateToString(Date date, String pattern){
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
	}
	
	public static Date stringToDate(String dateString, String pattern){
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		Date date = null;
		try {
			date =  sdf.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
//	public static void main(String[] args) {
//		String[] strs = {"1","2","3"};
//		System.out.println(Tool.isArrayContainsElem(strs, "3"));
//		System.out.println(Tool.isArrayContainsElem(strs, "4"));
//		Integer[] integers = {1,2,3};
//		System.out.println(Tool.isArrayContainsElem(integers, 3));
//		System.out.println(Tool.isArrayContainsElem(integers, 4));
//		
//		System.out.println("aa".substring(0,16));
//	}
	
//	public static void main(String[] args) {
//		List<Goods> list = new GoodsDaoImpl().getTop8OffGoods();
//		System.out.println("随机排序前：");
//		for(Goods goods: list){
//			System.out.println(goods);
//		}
//		System.out.println("随机排序后：");
//		List<Goods> list2 = (List<Goods>) Tool.listToRandom(list);
//		for(Goods goods: list2){
//			System.out.println(goods);
//		}
//	}

	public static void main(String[] args) {
		System.out.println(dateToString(new Date(), "yyyy/MM/dd"));
		System.out.println(stringToDate("2017-03-25 14:08:12", "yyyy-MM-dd HH:mm:ss"));
	}
	
}
