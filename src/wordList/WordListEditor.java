package wordList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Scanner;

public class WordListEditor {
	int totalWords = 0;
	int pluralVerbs = 0;
	int bigWords = 0;
	int smallWords = 0;
	int sizey = 0;
	HashMap<Integer,Integer> invalid = new HashMap<Integer,Integer>();
	HashMap<Integer, String> wordList = new HashMap<Integer, String>();

	public void loadMap() {
		try {
			Scanner sc = new Scanner(new File("wordsAlpha.txt"));
			int wordNum = 0;
			while(sc.hasNextLine()) {
				String temp = sc.nextLine();
				wordList.put(wordNum, temp);
				wordNum++;
			}
			System.out.println(wordNum);
			System.out.println(wordList.size());
			sc.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void validate() {
		sizey = wordList.size();
		for(int i = 0; i < sizey; i++) {
			String temp = wordList.get(i);
			String temp2 = "";
			boolean pluVerb = false;
			if(temp.charAt(temp.length() - 1) == 's') {
				temp2 = temp.substring(0, temp.length() - 1);
				if(wordList.containsValue(temp2)) {
					pluVerb = true;
				}
			}
			if(temp.length()>9 || temp.length()<4 || pluVerb) {
				wordList.remove(i);
			}
			System.out.println(i);
		}
		System.out.println(wordList.size());
	}
	
	public void makeNewList() {
		try {
			PrintStream ps = new PrintStream(new FileOutputStream("wordsValid2.txt", true));
			for(int i = 0; i < sizey; i++) {
				String temp = wordList.get(i);
				if(!(temp == null)) {
					ps.println(temp);
				}
			}
			ps.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void findWords(char central, char a, char b, char c, char d, char e, char f, char g, char h) {
		try {
			Scanner sc = new Scanner(new File("wordsValid2.txt"));
			String comp = Character.toString(central) + Character.toString(a) + Character.toString(b) + Character.toString(c) + Character.toString(d) + Character.toString(e) + Character.toString(f) + Character.toString(g) + Character.toString(h);
			int prelimNum = 0;
			while(sc.hasNextLine()) {
				String temp = sc.nextLine();
				boolean correctLetter = true;
				if(temp.indexOf(central)>=0) {
					String temp2 = comp;
					for(int i = 0; i < temp.length(); i++) {
						if(temp2.indexOf(temp.charAt(i))<0) {
							correctLetter = false;
							break;
						}
						if(temp2.indexOf(temp.charAt(i))>=0) {
							temp2 = temp2.replaceFirst(Character.toString(temp.charAt(i)), "!");
						}
					}
					if(correctLetter) {
						System.out.println(temp);
						prelimNum++;
					}
				}
			}
			System.out.println(prelimNum);
			sc.close();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public WordListEditor(){
//		loadMap();
//		validate();
//		makeNewList();
		findWords('p','e','i','c','t','e','i','n','r');
	}
	
	public static void main(String[] args) {
		new WordListEditor();
	}

}
