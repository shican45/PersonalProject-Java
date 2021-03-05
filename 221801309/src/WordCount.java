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
		int size = arr.length; // ��ȡԭ���鳤��
		int newSize = size + str.length; // ԭ���鳤�ȼ���׷�ӵ����ݵ��ܳ���
		
		// �½���ʱ�ַ�������
		String[] tmp = new String[newSize]; 
		// �ȱ�����ԭ�����ַ�������������ӵ���ʱ�ַ�������
		for (int i = 0; i < size; i++) { 
			tmp[i] = arr[i];
		}
		// ��ĩβ�������Ҫ׷�ӵ�����
		for (int i = size; i < newSize; i++) {
			tmp[i] = str[i - size];
		} 
		return tmp; // ����ƴ����ɵ��ַ�������
	}*/
	
	/*����value����ȡǰ10*/
	 public static Map sortMap(Map<String, Integer> map) {

		 //��ȡentrySet
		 Set<Map.Entry<String, Integer>> mapEntries = map.entrySet();

		 //ʹ���������Լ��Ͻ�������ʹ��LinkedList�����ڲ���Ԫ��
		 List<Map.Entry<String, Integer>> result = new LinkedList<>(mapEntries);
		 //�Զ���Ƚ������Ƚ������е�Ԫ��
		 Collections.sort(result, new Comparator<Map.Entry<String, Integer>>() {
			 //����entry��ֵ��Entry.getValue()��������������
			 @Override
			 public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
				 	return o2.getValue().compareTo(o1.getValue());
			 	}
		 	});

		 	//���ź���Ĵ��뵽LinkedHashMap(�ɱ���˳��)�У���Ҫ�洢����ֵ��Ϣ�Ե��µ�ӳ���С�
		 Integer sort = 1;
		 Map<String, Integer> linkMap = new LinkedHashMap<>();
	     for (Map.Entry<String, Integer> newEntry : result) {
	         // ȡ������ǰ5��ֵ
	         if (sort <= 10) {
	             linkMap.put(newEntry.getKey(), newEntry.getValue());
	             ++sort;
	         }
	     }
	     return linkMap;
	 }
	 
	/*1.ͳ���ļ����ַ�������Ӧ�����һ�У�*/
	 public static void countChar() throws IOException{
	    FileWriter fw = new FileWriter("f://output.txt");
		File file = new File("F:\\input.txt");
		try {
			FileReader fr = new FileReader(file);
			BufferedReader bfr = new BufferedReader(fr);
			char ch;
			int countc = 0;//�ַ���
			while((ch = (char) bfr.read()) != (char)-1)	{
				countc++;//�ۼ��ַ���
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
	 
	 /*2.ͳ���ļ��ĵ�����������Ӧ����ڶ��У�*/
	 public static void countWordsNum() throws IOException{
	        FileWriter fw = new FileWriter("f://output.txt",true);
			File file = new File("F:\\input.txt");
			
			try {
				FileReader fr = new FileReader(file);
				BufferedReader bfr = new BufferedReader(fr);
						
				char ch;//��ǰ�ַ�
				int count = 0;//��ĸ�����Ƿ񳬹�4��
				int t = 0 ;//�Ƿ��зָ������в������ӵ�����
				int endchar = 0;//ĩβ�޷ָ����ĵ���ҲҪ��һ
				int countw = 0;//Ӣ�ĵ�����
				//String[] strArray={};
				String str = "";
				HashMap<String, Integer> words = new HashMap<String, Integer>();
				while ((ch = (char) bfr.read()) != (char)-1)//���ַ���ȡ�ı�����&&((fch>='a'&&fch<='z')||(fch>='A'&&fch<='Z'))
				{
					if ((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z')) {
						str += ch;
						str = str.toLowerCase();
						count++;//�ۼ���ĸ
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
	 
	 /*3.ͳ���ļ�����Ч��������Ӧ��������У����κΰ����ǿհ��ַ����У�����Ҫͳ�ơ�*/
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
				i++;//�ۼ�����
				if (s1.equals("")) {
					i--;
					//�հ��в�ͳ��
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
	 
	 /*4.ͳ���ļ��и����ʵĳ��ִ�������Ӧ���������10�У�������ֻ���Ƶ����ߵ�10����*/
	 public static void countWordsSinglenum() throws IOException{
		FileWriter fw = new FileWriter("f://output.txt",true);
		File file = new File("F:\\input.txt");
		try {
			FileReader fr = new FileReader(file);
			BufferedReader bfr = new BufferedReader(fr);
					
			char ch;//��ǰ�ַ�
			int count = 0;//��ĸ�����Ƿ񳬹�4��
			int t = 0 ;//�Ƿ��зָ������в������ӵ�����
			int endchar = 0;//ĩβ�޷ָ����ĵ���ҲҪ��һ
			int countw = 0;//Ӣ�ĵ�����
			//String[] strArray={};
			String str = "";
			HashMap<String, Integer> words = new HashMap<String, Integer>();
			while ((ch = (char) bfr.read()) != (char)-1)//���ַ���ȡ�ı�����&&((fch>='a'&&fch<='z')||(fch>='A'&&fch<='Z'))
			{
				if ((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z')) {
					str += ch;
					str = str.toLowerCase();
					count++;//�ۼ���ĸ
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
		        
		        
		    //ͳ���ļ��и����ʵĳ��ִ�������Ӧ���������10�У�������ֻ���Ƶ����ߵ�10����
		    Collection<String> keyset= words.keySet();
		    List<String> list = new ArrayList<String>(keyset);
				 
			//��key��ֵ���ֵ���������
			Collections.sort(list);
			
			Map<String, Integer> newMap = new LinkedHashMap<>();//ȫ���ֵ��������
			for (int i = 0; i < list.size(); i++) {
				//System.out.println(list.get(i)+"��"+words.get(list.get(i)));
			    newMap.put(list.get(i).toString() , words.get(list.get(i).toString()));
			 }
			
			Map<String, Integer> newMap2 = new LinkedHashMap<>();//�ٸ���Ƶ�������ȡǰ10
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