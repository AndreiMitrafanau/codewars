public class Kata009 {
    private final String[] words;

    public Kata009(String[] words) {
        this.words = words;
    }

    public String findMostSimilar(String S1) {
        int idx = Integer.MAX_VALUE;
        String result = "";
        for (String w : words) {
            int newIdx = editdist(S1, w);
            if (newIdx < idx) {
                idx = newIdx;
                result = w;
            }
        }
        return result;
    }

    private int editdist(String S1, String S2) {
        int m = S1.length();
        int n = S2.length();
        int[] D1;
        int[] D2 = new int[n + 1];

        for (int i = 0; i <= n; i++)
            D2[i] = i;

        for (int i = 1; i <= m; i++) {
            D1 = D2;
            D2 = new int[n + 1];
            for (int j = 0; j <= n; j++) {
                if (j == 0) D2[j] = i;
                else {
                    int cost = (S1.charAt(i - 1) != S2.charAt(j - 1)) ? 1 : 0;
                    if (D2[j - 1] < D1[j] && D2[j - 1] < D1[j - 1] + cost)
                        D2[j] = D2[j - 1] + 1;
                    else if (D1[j] < D1[j - 1] + cost)
                        D2[j] = D1[j] + 1;
                    else
                        D2[j] = D1[j - 1] + cost;
                }
            }
        }
        return D2[n];
    }
}
