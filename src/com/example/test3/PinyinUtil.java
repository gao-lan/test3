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
	
	
	//regex�ж��Ƿ��Ǻ���
	private static String regex = "[\\u4e00-\\u9fa5]";
	
	//regex�ж��Ƿ���Сд��ĸ
	private static String regex2 = "[a-z]";
	
	private static String regex3 = "[A-Z]";
	
	private static String regex4 = "[1-9]";
	
	private static String appName;
	
	/**   
     * ������ת��Ϊȫƴ
     *    
     * @param src   
     * @return String   
     */  
	public static String getPinYin(String src) {
        char[] t1 = null;  
        t1 = src.toCharArray();  
        String[] t2 = new String[t1.length];  
        // ���ú���ƴ������ĸ�ʽ     
        HanyuPinyinOutputFormat t3 = new HanyuPinyinOutputFormat();  
        t3.setCaseType(HanyuPinyinCaseType.LOWERCASE);  
        t3.setToneType(HanyuPinyinToneType.WITHOUT_TONE);  
        t3.setVCharType(HanyuPinyinVCharType.WITH_V);  
        String t4 = "";  
        int t0 = t1.length;  
        try {  
            for (int i = 0; i < t0; i++) {  
                // �ж��ܷ�Ϊ�����ַ�     
                if (Character.toString(t1[i]).matches("[\\u4E00-\\u9FA5]+")) {  
                    t2 = PinyinHelper.toHanyuPinyinStringArray(t1[i], t3);// �����ֵļ���ȫƴ���浽t2������     
                    t4 += t2[0];// ȡ���ú���ȫƴ�ĵ�һ�ֶ��������ӵ��ַ���t4��     
                } else {  
                    // ������Ǻ����ַ������ȡ���ַ������ӵ��ַ���t4��     
                    t4 += Character.toString(t1[i]);  
                }  
            }  
        } catch (BadHanyuPinyinOutputFormatCombination e) {  
            e.printStackTrace();  
        }  
        return t4;  
    }  
	
	/**   
     * ��ȡÿ�����ֵ�����ĸ   
     *    
     * @param str   
     * @return String   
     */  
    public static String getPinYinHeadChar(String str) {  
        String convert = "";  
        for (int j = 0; j < str.length(); j++) {  
            char word = str.charAt(j);  
            // ��ȡ���ֵ�����ĸ     
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
	 * @param list  ������APP������
	 * @return		Map<ÿ�����֡����֡�Ӣ�ĵ�����ĸ��APPName>
	 */
	public static Map<String,String> getEachInitial(ArrayList<String> list){
		//key����ĸ��value��APPName
		//��ŵ�һ����ĸ
		Map<String,String> shouZiMu = new HashMap<String, String>();
		int length;
		StringBuffer buf = new StringBuffer();
		Iterator<String> it = list.iterator();
		while(it.hasNext()){
			
			appName = it.next();
			length = appName.length();
		
			for(int i = 0;i<length;i++){
				char s = appName.charAt(i);
				//eachChar��s���ַ���
				String eachChar = String.valueOf(s);
				if(eachChar.matches(regex)){
					//�Ǻ���
					buf.append(getPinYinHeadChar(eachChar));
				}else{
					//���Ǻ���
					if(i==0){
						//�ǵ�һ��char,������Ӣ����ĸ���������֣���Ҫ�洢
						buf.append(eachChar.toLowerCase());
					}else{
						if(eachChar.matches(regex3)){
							//��д��ĸ
							buf.append(eachChar.toLowerCase());
						}else{
							if(eachChar.matches(regex4)){
								//����
								buf.append(eachChar);
							}
						}
					}
				}
			}
			//�洢APPname������ĸ��
			shouZiMu.put(buf.toString(), appName);
			//���buf
			buf.delete(0, buf.length());
		}
		return shouZiMu;
	}
	
}
