import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.LinkedList;
import java.util.Set;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Collections;
import java.util.Comparator;
import java.io.FileWriter;
import java.io.IOException;

public class WordCount {
	/*public static void main(String[] args) throws IOException{
        // TODO Auto-generated method stub
        FileWriter fw = new FileWriter("f://output.txt");
        FileReader fr = new FileReader("f://input.txt");
        int hasRead = 0;
        int charnum = 0;
        while((hasRead = fr.read())!=-1){
            //System.out.print((char)hasRead);
            fw.write((char)hasRead);
            charnum++;
        }
        fw.write("/n" + charnum);
        fr.close();
        fw.flush();
        fw.close();
    }*/
	
	public static void main(String[] args)  throws IOException{
		countChar();
		countWordsNum();
		countLine();
		countWordsSinglenum();
	}
	/*private static String[] insert(String[] arr, String... str) {
		int size = arr.length; // 获取原数组长度
		int newSize = size + str.length; // 原数组长度加上追加的数据的总长度
		
		// 新建临时字符串数组
		String[] tmp = new String[newSize]; 
		// 先遍历将原来的字符串数组数据添加到临时字符串数组
		for (int i = 0; i < size; i++) { 
			tmp[i] = arr[i];
		}
		// 在末尾添加上需要追加的数据
		for (int i = size; i < newSize; i++) {
			tmp[i] = str[i - size];
		} 
		return tmp; // 返回拼接完成的字符串数组
	}*/
	
	/*根据value排序取前10*/
	 public static Map sortMap(Map<String, Integer> map) {

		 //获取entrySet
		 Set<Map.Entry<String, Integer>> mapEntries = map.entrySet();

		 //使用链表来对集合进行排序，使用LinkedList，利于插入元素
		 List<Map.Entry<String, Integer>> result = new LinkedList<>(mapEntries);
		 //自定义比较器来比较链表中的元素
		 Collections.sort(result, new Comparator<Map.Entry<String, Integer>>() {
			 //基于entry的值（Entry.getValue()），来排序链表
			 @Override
			 public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
				 	return o2.getValue().compareTo(o1.getValue());
			 	}
		 	});

		 	//将排好序的存入到LinkedHashMap(可保持顺序)中，需要存储键和值信息对到新的映射中。
		 Integer sort = 1;
		 Map<String, Integer> linkMap = new LinkedHashMap<>();
	     for (Map.Entry<String, Integer> newEntry : result) {
	         // 取出排名前5的值
	         if (sort <= 10) {
	             linkMap.put(newEntry.getKey(), newEntry.getValue());
	             ++sort;
	         }
	     }
	     return linkMap;
	 }
	 
	/*1.统计文件的字符数（对应输出第一行）*/
	 public static void countChar() throws IOException{
	    FileWriter fw = new FileWriter("f://output.txt");
		File file = new File("F:\\input.txt");
		try {
			FileReader fr = new FileReader(file);
			BufferedReader bfr = new BufferedReader(fr);
			char ch;
			int countc = 0;//字符数
			while((ch = (char) bfr.read()) != (char)-1)	{
				countc++;//累计字符数
				if(ch == '\n')
				{
					countc--;
				}
			}
			//System.out.println("Chars:"+countc);
	        fw.write("characters:"+ countc + "\n");
			bfr.close();
			fr.close();;
			fw.close();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
	}
	 
	 /*2.统计文件的单词总数（对应输出第二行）*/
	 public static void countWordsNum() throws IOException{
	        FileWriter fw = new FileWriter("f://output.txt",true);
			File file = new File("F:\\input.txt");
			
			try {
				FileReader fr = new FileReader(file);
				BufferedReader bfr = new BufferedReader(fr);
						
				char ch;//当前字符
				int count = 0;//字母数量是否超过4个
				int t = 0 ;//是否有分隔符，有才能增加单词数
				int endchar = 0;//末尾无分隔符的单词也要加一
				int countw = 0;//英文单词数
				//String[] strArray={};
				String str = "";
				HashMap<String, Integer> words = new HashMap<String, Integer>();
				while ((ch = (char) bfr.read()) != (char)-1)//按字符读取文本内容&&((fch>='a'&&fch<='z')||(fch>='A'&&fch<='Z'))
				{
					if ((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z')) {
						str += ch;
						str = str.toLowerCase();
						count++;//累计字母
						t = 0;
						if (count >= 4) {
							endchar = 1;
						}
						
					}else if ((ch < '0' || ch >'9')) {
						if (t == 0 && count >= 4) {
							countw++;
							if(words.containsKey(str)) {
								words.compute(str,(key, value) ->value + 1);
							}else {
								words.put(str, 1);
							}
						}
						str = "";
						t = 1;
						count = 0;
						endchar = 0;
					}else {
						str += ch;
					}
				}
				if (endchar == 1) {
					countw++;
					if(words.containsKey(str)) {
						words.compute(str,(key, value) ->value + 1);
					}else {
						words.put(str, 1);
					}
				}
				//System.out.println("WordsRepeatNum:  "+countw);
		        //System.out.println("Words:            "+words);
				
		        //System.out.println("WordsSingleNum:  "+words.size());
	        fw.write("words:  "+words.size() + "\n");
			bfr.close();
			fr.close();;
			fw.close();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	 }
	 
	 /*3.统计文件的有效行数（对应输出第三行）：任何包含非空白字符的行，都需要统计。*/
	 public static void countLine() throws IOException{
		 
	    FileWriter fw = new FileWriter("f://output.txt",true);
	    File file = new File("F:\\input.txt");
		try {
			FileReader fr = new FileReader(file);
			BufferedReader bufr = new BufferedReader(fr);
			int i = 0;
			String s1 = null;
			while ((s1 = bufr.readLine()) != null)
			{
				i++;//累计行数
				if (s1.equals("")) {
					i--;
					//空白行不统计
				}
			}
			//System.out.println("lines:"+i);
	        fw.write("lines:"+ i + "\n");
			bufr.close();
			fr.close();
			fw.close();
		}catch (Exception e)
		{
			e.printStackTrace();
		}
	 }
	 
	 /*4.统计文件中各单词的出现次数（对应输出接下来10行），最终只输出频率最高的10个。*/
	 public static void countWordsSinglenum() throws IOException{
		FileWriter fw = new FileWriter("f://output.txt",true);
		File file = new File("F:\\input.txt");
		try {
			FileReader fr = new FileReader(file);
			BufferedReader bfr = new BufferedReader(fr);
					
			char ch;//当前字符
			int count = 0;//字母数量是否超过4个
			int t = 0 ;//是否有分隔符，有才能增加单词数
			int endchar = 0;//末尾无分隔符的单词也要加一
			int countw = 0;//英文单词数
			//String[] strArray={};
			String str = "";
			HashMap<String, Integer> words = new HashMap<String, Integer>();
			while ((ch = (char) bfr.read()) != (char)-1)//按字符读取文本内容&&((fch>='a'&&fch<='z')||(fch>='A'&&fch<='Z'))
			{
				if ((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z')) {
					str += ch;
					str = str.toLowerCase();
					count++;//累计字母
					t = 0;
					if (count >= 4) {
						endchar = 1;
					}
					
				}else if ((ch < '0' || ch >'9')) {
					if (t == 0 && count >= 4) {
						countw++;
						if(words.containsKey(str)) {
							words.compute(str,(key, value) ->value + 1);
						}else {
							words.put(str, 1);
						}
						str = "";
					}
					t = 1;
					count = 0;
					endchar = 0;
				}else {
					str += ch;
				}
			}
			if (endchar == 1) {
				countw++;
				if(words.containsKey(str)) {
					words.compute(str,(key, value) ->value + 1);
				}else {
					words.put(str, 1);
				}
			}
			//System.out.println("WordsRepeatNum:  "+countw);
		    //System.out.println("Words:            "+words);
			
		    //System.out.println("WordsSingleNum:  "+words.size());
	        //fw.write("Words:  "+words.size() + "\n");
		        
		        
		    //统计文件中各单词的出现次数（对应输出接下来10行），最终只输出频率最高的10个。
		    Collection<String> keyset= words.keySet();
		    List<String> list = new ArrayList<String>(keyset);
				 
			//对key键值按字典升序排序
			Collections.sort(list);
			
			Map<String, Integer> newMap = new LinkedHashMap<>();//全部字典序排序后
			for (int i = 0; i < list.size(); i++) {
				//System.out.println(list.get(i)+"："+words.get(list.get(i)));
			    newMap.put(list.get(i).toString() , words.get(list.get(i).toString()));
			 }
			
			Map<String, Integer> newMap2 = new LinkedHashMap<>();//再根据频率排序后取前10
			newMap2 = sortMap(newMap);
		       //System.out.println("SortWordsrank5:  "+newMap2);
			for (String s : newMap2.keySet()) {
		           //System.out.println(s + ": " + newMap2.get(s));
		        fw.write(s + ": " + newMap2.get(s) + "\n");
		       }
			bfr.close();
			fr.close();
			fw.close();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	 }
}