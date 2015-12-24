package com.example.test3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

public class PinyinUtil {
	
	
	//regex判断是否是汉字
	private static String regex = "[\\u4e00-\\u9fa5]";
	
	//regex判断是否是小写字母
	private static String regex2 = "[a-z]";
	
	private static String regex3 = "[A-Z]";
	
	private static String regex4 = "[1-9]";
	
	private static String appName;
	
	/**   
     * 将汉字转换为全拼
     *    
     * @param src   
     * @return String   
     */  
	public static String getPinYin(String src) {
        char[] t1 = null;  
        t1 = src.toCharArray();  
        String[] t2 = new String[t1.length];  
        // 设置汉字拼音输出的格式     
        HanyuPinyinOutputFormat t3 = new HanyuPinyinOutputFormat();  
        t3.setCaseType(HanyuPinyinCaseType.LOWERCASE);  
        t3.setToneType(HanyuPinyinToneType.WITHOUT_TONE);  
        t3.setVCharType(HanyuPinyinVCharType.WITH_V);  
        String t4 = "";  
        int t0 = t1.length;  
        try {  
            for (int i = 0; i < t0; i++) {  
                // 判断能否为汉字字符     
                if (Character.toString(t1[i]).matches("[\\u4E00-\\u9FA5]+")) {  
                    t2 = PinyinHelper.toHanyuPinyinStringArray(t1[i], t3);// 将汉字的几种全拼都存到t2数组中     
                    t4 += t2[0];// 取出该汉字全拼的第一种读音并连接到字符串t4后     
                } else {  
                    // 如果不是汉字字符，间接取出字符并连接到字符串t4后     
                    t4 += Character.toString(t1[i]);  
                }  
            }  
        } catch (BadHanyuPinyinOutputFormatCombination e) {  
            e.printStackTrace();  
        }  
        return t4;  
    }  
	
	/**   
     * 提取每个汉字的首字母   
     *    
     * @param str   
     * @return String   
     */  
    public static String getPinYinHeadChar(String str) {  
        String convert = "";  
        for (int j = 0; j < str.length(); j++) {  
            char word = str.charAt(j);  
            // 提取汉字的首字母     
            String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word);  
            if (pinyinArray != null) {  
                convert += pinyinArray[0].charAt(0);  
            } else {  
                convert += word;  
            }  
        }  
        return convert;  
    }  
	/**
	 * 
	 * @param list  里面存放APP的名字
	 * @return		Map<每个汉字、数字、英文的首字母，APPName>
	 */
	public static Map<String,String> getEachInitial(ArrayList<String> list){
		//key是字母，value是APPName
		//存放第一个字母
		Map<String,String> shouZiMu = new HashMap<String, String>();
		int length;
		StringBuffer buf = new StringBuffer();
		Iterator<String> it = list.iterator();
		while(it.hasNext()){
			
			appName = it.next();
			length = appName.length();
		
			for(int i = 0;i<length;i++){
				char s = appName.charAt(i);
				//eachChar是s的字符型
				String eachChar = String.valueOf(s);
				if(eachChar.matches(regex)){
					//是汉字
					buf.append(getPinYinHeadChar(eachChar));
				}else{
					//不是汉字
					if(i==0){
						//是第一个char,可能是英文字母或者是数字，都要存储
						buf.append(eachChar.toLowerCase());
					}else{
						if(eachChar.matches(regex3)){
							//大写字母
							buf.append(eachChar.toLowerCase());
						}else{
							if(eachChar.matches(regex4)){
								//数字
								buf.append(eachChar);
							}
						}
					}
				}
			}
			//存储APPname的首字母集
			shouZiMu.put(buf.toString(), appName);
			//清空buf
			buf.delete(0, buf.length());
		}
		return shouZiMu;
	}
	
}
