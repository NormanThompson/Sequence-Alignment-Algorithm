public class SequenceAlignment {

    public static String[] align(String s1, String s2, int match, int mismatch, int gap) {
        /*
        The next few lines just initiate the tables with the lengths of the strings as the length of the axis (+1).
        I wanted to remain consistent with the class examples, so I initiated this table with height+1 as the vertical height
        and length +1 as the horizontal length. The bottom row represents string 2, the left column represents string 1.
        The +1 is just there so that we can have [0][0] = 0 and [0][x] = x * gap and [x][0] = x * gap. This just fills
         out the sides of the table so that we can start making comparisons. I also make a second table to keep track of exactly
         where each cell came from. So later, I can refer to [i][j] in the second table to find where that score came from and
         backtrack to make the optimal alignment (at the end).
         */
        int height = s1.length(); // m height
        int length = s2.length(); //n length

        int[][] table = new int[height + 1][length + 1];
        char[][] backTrack = new char[height + 1][length + 1];

        table[0][0] = 0;

        for (int i = 1; i <= length; i++) {
            table[0][i] = i * gap;
            backTrack[0][i] = 'L'; //L for left
        }
        for (int i = 1; i <= height; i++) {
            table[i][0] = i * gap;
            backTrack[i][0] = 'U'; //U for under
        }
        /*
        This is the loop that actually fills out the bulk of the tables. This grows by row and then by column, so
        it starts on the second to bottom row (as the bottom is already filled out), and chooses the optimal direction
        for where to come from to get to that square. Then it goes to the next row, etc... until the end. The way that
        the optimal score is calculated is pretty simple. Then, I just have a consistent precedence
        for choosing which way to go, with diagonal being the most important, then left, then under. I don't know that this part
        actually matters, but I wanted things to be consistent and predictable for this.
         */
        for (int i = 1; i <= height; i++) {
            for (int j = 1; j <= length; j++) {
                int score;
                int diagScore = table[i - 1][j - 1];
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    diagScore += match;
                } else {
                    diagScore += mismatch;
                }
                int leftScore = table[i][j - 1] + gap;
                int underScore = table[i - 1][j] + gap;

                score = Math.max(leftScore, Math.max(underScore, diagScore));
                table[i][j] = score;
                if (score == diagScore) {
                    backTrack[i][j] = 'D';
                } else if (score == leftScore) {
                    backTrack[i][j] = 'L';
                } else {
                    backTrack[i][j] = 'U';
                }

            }
        }
        /*
        If we have not moved through either string entirely, we can check for diagonal
        in the current cell. If it is there, we append both characters in the cell and then move diagonally back down.
        If diagonal isn't there or one of the strings is empty, we can instead check for under. If It is there, then we
        just append the current character to the first string and a gap to the second and move down (since the first string
         is along the y-axis, we move down). Otherwise, it must be the case that the current backtrack is L, so it's not worth
         checking. The only check we have to do for the last case is to make sure that we aren't already at the leftmost cell,
         and then we move left and append a gap to the first string. Because We are building from the end and appending to the
         back of our resulting strings, the results are backwards, so we return the strings reversed.
         */
        StringBuilder ans1 = new StringBuilder();
        StringBuilder ans2 = new StringBuilder();
        while(height > 0 || length > 0){
            if(backTrack[height][length] == 'D'){
                if(length > 0 && height > 0){
                    ans1.append(s1.charAt(height - 1));
                    ans2.append(s2.charAt(length - 1));
                    length --;
                    height --;
                }
            }else if(backTrack[height][length] == 'L'){
                if(length > 0){
                    ans2.append(s2.charAt(length - 1));
                    ans1.append('-');
                    length --;

                }
            }else if(backTrack[height][length] == 'U'){
                if(height > 0){
                    ans1.append(s1.charAt(height - 1));
                    ans2.append('-');
                    height --;
                }
            }
        }
        String scoreString = table[s1.length()][s2.length()] + "";
        return new String[]{scoreString,ans1.reverse().toString(), ans2.reverse().toString()};

        }


    }

