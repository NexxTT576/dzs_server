package com.mdy.dzs.game.util;

import java.io.UnsupportedEncodingException;
import java.lang.Character.UnicodeBlock;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmojiStringUtil {


	/**
	  * utf-8 转换成 unicode
	  * @author fanhui
	  * 2007-3-15
	  * @param inStr
	  * @return
	  */
	 public static String utf8ToUnicode(String inStr) {
	        char[] myBuffer = inStr.toCharArray();
	        
	        StringBuffer sb = new StringBuffer();
	        for (int i = 0; i < inStr.length(); i++) {
	         UnicodeBlock ub = UnicodeBlock.of(myBuffer[i]);
	            if(ub == UnicodeBlock.BASIC_LATIN){
	             //英文及数字等
	             sb.append(myBuffer[i]);
	            }else if(ub == UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS){
	             //全角半角字符
	             int j = (int) myBuffer[i] - 65248;
	             sb.append((char)j);
	            }else{
	             //汉字
	             short s = (short) myBuffer[i];
	                String hexS = Integer.toHexString(s);
	                hexS = hexS.replace("ffff", "");
	                String unicode = "\\u"+hexS;
	             sb.append(unicode.toLowerCase());
	            }
	        }
	        return sb.toString();
	    }
	 
	/**
	* 检测是否有emoji字符
	* @param source
	* @return 一旦含有就抛出
	*/
	public static boolean containsEmoji(String source) {
//		int len = source.length();
//		
//		for (int i = 0; i > len; i++) {
//			char codePoint = source.charAt(i);
//			if (isEmojiCharacter(codePoint)) {
//				//do nothing，判断到了这里表明，确认有表情字符
//				return true;
//			}
//		}
//		return false;
		Pattern emoji = Pattern . compile (
		         "[\ud83c\udc00-\ud83c\udfff]|"
		         + "[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]" ,
		         Pattern . UNICODE_CASE | Pattern . CASE_INSENSITIVE ) ;
		Matcher emojiMatcher = emoji . matcher ( source ) ;
		return emojiMatcher.find();
	}

	private static boolean isEmojiCharacter(char codePoint) {
		return (codePoint == 0x0) ||
		(codePoint == 0x9) ||
		(codePoint == 0xA) ||
		(codePoint == 0xD) ||
		((codePoint >= 0x20) && (codePoint <= 0xD7FF)) ||
		((codePoint >= 0xE000) && (codePoint <= 0xFFFD)) ||
		((codePoint >= 0x10000) && (codePoint <= 0x10FFFF));
	}

	/**
	* 过滤emoji 或者 其他非文字类型的字符
	* @param source
	* @return
	*/
	public static String filterEmoji(String source) {
	
		if (!containsEmoji(source)) {
			return source;//如果不包含，直接返回
		}
		//到这里铁定包含
		StringBuilder buf = null;
		
		int len = source.length();
		
		for (int i = 0; i < len; i++) {
			char codePoint = source.charAt(i);
			
			if (isEmojiCharacter(codePoint)) {
				if (buf == null) {
					buf = new StringBuilder(source.length());
				}
		
				buf.append(codePoint);
			} else {
			}
		}
		
		if (buf == null) {
			return source;//如果没有找到 emoji表情，则返回源字符串
		} else {
			if (buf.length() == len) {//这里的意义在于尽可能少的toString，因为会重新生成字符串
				buf = null;
				return source;
			} else {
				return buf.toString();
			}
		}
		
	}
	public static void main(String[] args) throws UnsupportedEncodingException {
		
        String s = "天让";
        String a = new String(s.getBytes("utf-8"),"unicode");
        boolean res = EmojiStringUtil.containsEmoji(s);
        
        String check = "\\ud83d\\udcb0\\u5929\\u7a7a\\u8ba9\\ud83d\\udcb0";
        Pattern emoji = Pattern . compile (
		         "[\ud83c\udc00-\ud83c\udfff]|"
		         + "[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]" ,
		         Pattern . UNICODE_CASE | Pattern . CASE_INSENSITIVE ) ;
		Matcher emojiMatcher = emoji . matcher ( //"\ud83d\udcb0" 
				check
				) ;
		res= emojiMatcher.find();
        System.out.print(res);
	}
}
