package com.mar.algotools.crypto;

public class CaesarSolver {

    private int offset = 0;

    private String cipherText;

    private String plainText;

    private String word = "the";

    public CaesarSolver() {

    }

    public static int getCount(String pString, String pWord) {
        int wordLength = pWord.length();
        int cpt = 0;
        for (int i = 0; i < pString.length() - wordLength + 1; ++i) {
            if (pString.substring(i, i + wordLength).equals(pWord)) {
                cpt++;
            }
        }
        return cpt;
    }

    public String getPlainText() {
        return plainText;
    }

    public void solve(String pText) {
        cipherText = pText;

        int maxCount = 0;
        int maxOffset = -129;
        for (int i = -128; i <= 128; ++i) {
            String test = offset(pText, i);
            int count = getCount(test, word);
            if (count > maxCount) {
                maxCount = count;
                maxOffset = i;
            }
        }

        if (maxOffset != -129) {
            offset = maxOffset;
            plainText = offset(pText, maxOffset);
            System.out.println("Solution = " + plainText.substring(0, 1000));
        }
        else {
            System.out.println("No solution found.");
        }
    }

    private String offset(String pString, int pOffset) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < pString.length(); ++i) {
            char newChar = (char) (pString.charAt(i) + pOffset);
            sb.append(newChar);
        }
        return sb.toString();
    }

}