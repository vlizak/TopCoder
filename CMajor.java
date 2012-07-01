/**
 * SRM 289 DIV 1
 */
public class CMajor {
    public static final char[] WHITE_KEYS = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G'};
    public static final boolean[] ALL_KEYS = {
            true/*A*/,
            false/*black*/,
            true/*B*/,
            true/*C*/,
            false/*black*/,
            true/*D*/,
            false/*black*/,
            true/*E*/,
            true/*F*/,
            false/*black*/,
            true/*G*/,
            false/*black*/};

    int[] keyIndex = {
            0, 2, 3, 5, 6, 7, 9
    };


    public int getLongest(String[] fragments) {
        final int[][] DP = new int[fragments.length][WHITE_KEYS.length];

        for (int i = 0; i < fragments.length; i++) {
            int[] lengthsForWhite = new int[WHITE_KEYS.length];
            for (int j = 0; j < WHITE_KEYS.length; j++) {
                lengthsForWhite[j] = isValidStart(WHITE_KEYS[j], fragments[i]) ? fragments[i].length() : 0;
            }
            DP[i] = lengthsForWhite;
        }

        for (int i = 1; i < DP.length; i++) {
            for (int j = 0; j < DP[i].length; j++) {
                int prevFragmentLength = DP[i - 1][j];
                DP[i][j] = (prevFragmentLength == 0) ? 0 : prevFragmentLength + DP[i][j];
            }
        }

        return maxElement(DP[DP.length - 1]);


    }

    private int maxElement(int[] array) {
        int max = array[0];
        for (int n : array) {
            if (n > max) max = n;
        }
        return max;
    }

    private boolean isValidStart(char c, String fragment) {

        int start = keyIndex[c - 'A'];
        final String[] jumps = fragment.split(" ");
        for (String j : jumps) {
            int jump = Integer.parseInt(j);
            int next = (Math.abs(start + jump)) % ALL_KEYS.length;
            if (!ALL_KEYS[next]) return false;
            start = next;
        }
        return true;

    }
}