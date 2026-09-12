Sequence Alignment Algorithm

Overview: This program is an implementation of the sequence alignment algorithm, which is used in determining the similarity between two DNA sequences (or sequences in general). The program uses dynamic programming to fill out a table that holds the highest possible alignment score at the last cell, which it then follows backwards to construct the sequences with the highest possible score. In creating the table, the program decides whether at the current step, a gap, match, or mismatch would produce the best result, and then follows the path given these results to find the highest possible alignment score. Adding a mismatch typically results in a large penalty, a gap a minor penalty, and a match a bonus to the score. Moving diagonal from a cell will add the Match/Mismatch penalty to that cell depending on if the current two letters match, while moving right or up adds the gap penalty. Taking the move with the most benefit at each step allows us to find the optimal score through this dynamic programming process, ending with the best score stored at the end of the route and the best alignment stored as the route itself.

Features: Computes the highest alignment score for two input DNA sequences, as well as the finalized sequences themselves after alignment

Input Format specifications:
Characters of first string, with no white space leading or trailing, all of the set {A, C, T, G}
Characters of second string, with no white space leading or trailing, all of the set {A, C, T, G}
The exact text "Match %d" where %d is an integer representing the score of a match bonus
The exact text "Mismatch %d" where %d is an integer representing the score of a mismatch penalty
The exact text "Gap %d" where %d is an integer representing the score of a gap penalty
