import java.util.*;

public class l001 {

    public static void main(String[] args) {
        // int[] arr={2,3,5,7};
        // boolean[] vis=new boolean[arr.length]; //default false.
        // int tar=10;
        // System.out.println(coinCombiInfi(arr,0,tar,""));

        // boolean[][] box = new boolean[10][10];
        // System.out.println(queen2DCombi(box, 0, box.length, ""));
        // System.out.println(queen2DCombi_02(box, 0, 0, 0, 0, box.length, ""));
        // System.out.println(calls);

        basicQuest();

    }

    public static int coinCombiInfi(int[] arr, int idx, int tar, String ans) {
        if (idx == arr.length || tar == 0) {
            if (tar == 0) {
                System.out.println(ans);
                return 1;
            }
            return 0;

        }

        int count = 0;
        if (tar - arr[idx] >= 0)
            count += coinCombiInfi(arr, 0, tar - arr[idx], ans + arr[idx]);
        count += coinCombiInfi(arr, idx + 1, tar, ans);
        return count;
    }

    public static boolean isSafeToPlaceQueen(boolean[][] box, int x, int y) {
        int[][] dir = { { 0, -1 }, { -1, -1 }, { -1, 0 }, { -1, 1 }, { 0, 1 }, { 1, 1 }, { 1, 0 }, { 1, -1 } };
        for (int d = 0; d < dir.length; d++) {
            for (int rad = 1; rad <= Math.max(box.length, box[0].length); rad++) {
                int r = x + rad * dir[d][0];
                int c = y + rad * dir[d][1];
                if (r >= 0 && c >= 0 && r < box.length && c < box[0].length) {
                    if (box[r][c])
                        return false;
                } else {
                    break;
                }
            }

        }

        return true;

    }

    public static int queen2DCombi(boolean[][] box, int lqpl, int tnq, String ans) {
        if (tnq == 0) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        calls++;
        for (int i = lqpl; i < box.length * box[0].length; i++) {
            int x = i / box[0].length;
            int y = i % box[0].length;
            if (!box[x][y] && isSafeToPlaceQueen(box, x, y)) {
                box[x][y] = true;
                count += queen2DCombi(box, i + 1, tnq - 1, ans + "(" + x + ", " + y + ") ");
                box[x][y] = false;
            }
        }
        return count;

    }

    static int calls = 0;

    public static int queen2DCombi_02(boolean[][] box, int row, int bitc, int bitd, int bitad, int tnq, String ans) {
        if (row == box.length || tnq == 0) {
            if (tnq == 0) {
                System.out.println(ans);
                return 1;
            }
            return 0;
        }

        int count = 0;
        for (int col = 0; col < box[0].length; col++) {
            int a = (1 << col);
            int b = (1 << (row + col));
            int c = (1 << (row - col + box[0].length - 1));
            if ((bitc & a) == 0 && (bitd & b) == 0 && (bitad & c) == 0) {
                box[row][col] = true;
                bitc ^= a;// bitc[a]=true;
                bitd ^= b; // bitd[b]=true;
                bitad ^= c; // bitad[c]=true;

                count += queen2DCombi_02(box, row + 1, bitc, bitd, bitad, tnq - 1, ans + "(" + row + ", " + col + ") ");

                box[row][col] = false;
                bitc ^= a;// bitc[a]=false;
                bitd ^= b; // bitd[b]=false;
                bitad ^= c; // bitad[c]=false;

            }
        }
        return count;
    }

    public static void basicQuest() {
        // System.out.println(permu("aba", ""));
        int[] arr = { 10, 20, 30, 40, 50, 60, 70, 80 };
        System.out.println(eqiSet(arr, 1, arr[0], 0, arr[0] + "", ""));
    }

    public static int permu(String str, String ans) {
        if (str.length() == 0) {
            System.out.println(ans);
            return 1;
        }
        int count = 0;
        int seen = 0;

        for (int i = 0; i < str.length(); i++) {
            int k = str.charAt(i) - 'a';
            int mask = 1 << k;
            if ((seen & mask) == 0) {
                seen |= mask;
                String nstr = str.substring(0, i) + str.substring(i + 1);
                count += permu(nstr, ans + str.charAt(i)); // str[i]
            }
        }

        return count;
    }

    public static int eqiSet(int[] arr, int idx, int sum1, int sum2, String set1, String set2) {
        if (idx == arr.length) {
            if (sum1 == sum2) {
                System.out.println(set1 + " = " + set2);
                return 1;
            }
            return 0;
        }

        int count = 0;
        count+=eqiSet(arr,idx+1,sum1+arr[idx],sum2,set1+arr[idx]+" ",set2);
        count+=eqiSet(arr,idx+1,sum1,sum2+arr[idx],set1,set2+arr[idx]+" ");
        return count;
    }

}