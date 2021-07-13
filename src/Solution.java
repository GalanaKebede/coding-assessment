import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

//time complexity O(N * L^2)
//Space complexity O(N * L^2)

class Solution {
	String getStarWord(String word, int i) {
		return word.substring(0, i) + "*" + word.substring(i + 1);
	}

	public int dictionaryWalk(String beginWord, String endWord, Set<String> wordList) {
		Map<String, List<String>> graph = new HashMap<>();
		
		//Iterate over the value of words
		for (String word : wordList) {
			
			for (int i = 0; i < word.length(); ++i) {
				String starWord = getStarWord(word, i);
				if (!graph.containsKey(starWord)) {
					graph.put(starWord, new ArrayList<>());
				}
				graph.get(starWord).add(word);
			}
		}
		Deque<String> queue = new ArrayDeque<>();
		Map<String, Integer> minDist = new HashMap<>();
		queue.add(beginWord);
		minDist.put(beginWord, 1);
		while (!queue.isEmpty()) {
			String word = queue.poll();
			if (endWord.equals(word)) {
				break;
			}
			for (int i = 0; i < word.length(); ++i) {
				String starWord = getStarWord(word, i);
				for (String nextWord : graph.getOrDefault(starWord, new ArrayList<>())) {
					if (!minDist.containsKey(nextWord)) {
						minDist.put(nextWord, minDist.get(word) + 1);
						queue.add(nextWord);
					}
				}
			}
		}
		return minDist.getOrDefault(endWord, 0);
	}

	public static void main(String[] args) {
		Solution sol = new Solution();
		Set<String> wordList = new HashSet<String>();
//		wordList.add("dogs");
//		wordList.add("does");
//		wordList.add("doles");
//		wordList.add("soles");
//		wordList.add("solves");
//		wordList.add("wolves");
//
//		String start = "dogs";
//		String target = "wolves"; -
		//Explanation -> return 0, the start and the end are not the same length
		//
		//hate → love: hate, have, hove, love
		wordList.add("hate");
		wordList.add("have");
		wordList.add("hove");
		wordList.add("love");
		
		String start ="hate";
		String target= "love";
		//as one shortest path is hate->have->hove->love , return its length 4
		System.out.println(sol.dictionaryWalk(start, target, wordList));

	}
}


