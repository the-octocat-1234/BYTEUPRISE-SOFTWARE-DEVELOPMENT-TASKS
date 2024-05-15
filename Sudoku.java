import java.util.*;

public class Sudoku {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Auto Anagram Solver");
        System.out.print("Enter the jumbled letters: ");
        String jumbledLetters = scanner.nextLine().toLowerCase();

        List<String> anagrams = findAnagrams(jumbledLetters);
        if (anagrams.isEmpty()) {
            System.out.println("No valid anagrams found.");
        } else {
            System.out.println("Unscrambled word(s):");
            for (String anagram : anagrams) {
                System.out.println(anagram);
            }
        }
    }

    public static List<String> findAnagrams(String jumbledLetters) {
        List<String> anagrams = new ArrayList<>();
        char[] letters = jumbledLetters.toCharArray();
        Arrays.sort(letters); // Sort letters for easier comparison
        boolean[] visited = new boolean[letters.length];
        backtrack("", letters, visited, anagrams);
        return anagrams;
    }

    private static void backtrack(String currentWord, char[] letters, boolean[] visited, List<String> anagrams) {
        if (currentWord.length() == letters.length) {
            anagrams.add(currentWord);
            return;
        }

        for (int i = 0; i < letters.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                backtrack(currentWord + letters[i], letters, visited, anagrams);
                visited[i] = false;
                // Skip duplicates to avoid redundant permutations
                while (i + 1 < letters.length && letters[i] == letters[i + 1]) {
                    i++;
                }
            }
        }
    }
}

