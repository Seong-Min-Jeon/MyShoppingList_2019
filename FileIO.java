package javaTermProjectPackage;
import java.io.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class FileIO {
	//파일을 쓰는 함수입니다.(한 리스트)
	public void Input(String filename, ArrayList ary) throws IOException {
		File fn  = new File(filename);
		try {
			FileWriter fw = new FileWriter(fn);
			Writer output = new BufferedWriter(fw);
			int size = ary.size();
			for (int i = 0 ; i < size ; i++) {
				output.write(ary.get(i).toString() + "\n");
			}
			output.close();
		} catch (Exception e) {
			System.err.println(e);
		}
	}
	//파일을 쓰는 함수입니다.(한 아이템)
	public void Input2(String filename, Item item) throws IOException {
		File fn  = new File(filename);
		try {
			FileWriter fw = new FileWriter(fn,true);
			Writer output = new BufferedWriter(fw);
			output.write(item.toString() + "\n");
			output.close();
		} catch (Exception e1) {
			try {
				FileWriter fw = new FileWriter(fn);
				Writer output = new BufferedWriter(fw);
				output.write(item.toString());
			} catch (Exception e2) {
				System.err.println(e2);
			}
		}
	}
	//파일을 읽는 함수입니다.(이전 등록 목록)
	public ArrayList<Item> Output(String filename) throws IOException {
		String line;
		ArrayList<Item> ary = new ArrayList<>();
		try {
			BufferedReader input = new BufferedReader(new FileReader(filename));
			if (!input.ready()) 
				throw new IOException();
			while ((line = input.readLine()) != null) {
	            String name =  line.split("\\(")[0];
	            String remainder = line.split("\\(")[1];
	            String num = remainder.split("\\)")[0];
	            Item i = new Item(name, Integer.parseInt(num));
	            ary.add(i);
			}
			input.close();
		} catch (Exception e) {
			File fn  = new File("Last List.txt");
		}
		return ary;
	}
	//파일을 읽는 함수입니다.(전체 리스트)
	public ArrayList<List> OutputFinal(String filename) throws IOException {
		String line;
		ArrayList<List> ary = new ArrayList<>();
		try {
			BufferedReader input = new BufferedReader(new FileReader(filename));
			if (!input.ready()) 
				throw new IOException();
			while ((line = input.readLine()) != null) {	 
				ArrayList<Item> group = new ArrayList<>();
				String Listname =  line.split("\\[")[0];
		        String remainder = line.split("\\[")[1];
		        remainder = remainder.split("\\]")[0];
		        String[] splitStr = remainder.split(", ");
		        for(int i=0; i<splitStr.length; i++){
		            //System.out.println(splitStr[i]);
		            String name =  splitStr[i].split("\\(")[0];
			        String subRemainder = splitStr[i].split("\\(")[1];
			        String num = subRemainder.split("\\)")[0];
			        Item item = new Item(name, Integer.parseInt(num));
			        group.add(item);
		        }
		        List list = new List(Listname,group);
		        ary.add(list);
			}
			input.close();
		} catch (Exception e) {
			File fn  = new File("List.txt");
			ArrayList<Item> a = new ArrayList<>();
			ArrayList<Item> b = new ArrayList<>();
			Item i = new Item("Book Mark");
			Item i2 = new Item("TodayList");
			a.add(i);
			b.add(i2);
			List l1 = new List("Book Mark", a);
			List l2 = new List("Today List", b);
			ary.add(l1);
			ary.add(l2);
		}
		return ary;
	}
}
