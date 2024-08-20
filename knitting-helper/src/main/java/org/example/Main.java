package org.example;

import static java.lang.Integer.parseInt;

public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        System.out.println(main.knitAllKnitsPurlAllPurls("Row 35: k1, p3, k1"));
    }

    /*
    Knitting patterns often say for the second row "Knit all the knits and purl all the purls".
    While an advanced knitter can easily 'read' the knitting and figure out what to do, this function
    is meant to generate the wrong side row, given a right side row to a pattern.
    Inputs: A string that represents the row to be reversed. It should be in the form "Row n: k1, p3, k1"
    or "k1, p3, k1".
    Returns: A formatted next row of the pattern including row number, if it was provided.
     */
    public String knitAllKnitsPurlAllPurls(String row) {
        String rowInfo = "";
        String kPInfo = "";
        int rowNumber = 0;
        String newRow = "";
        if (row.indexOf(":") != -1) {
            rowInfo = row.split(":")[0];
            kPInfo = row.split(":")[1];
            rowNumber = parseInt(rowInfo.substring(4));
            newRow = "Row " + (rowNumber + 1) + ":";
        }
        else {
            kPInfo = row;
        }


        String[] rowArray = kPInfo.split(", ");
        for (int i = rowArray.length - 1; i >= 0; i--) {

            if (rowArray[i].charAt(0) == 'p') {
                newRow = newRow + " k" + rowArray[i].substring(1);
            }
            else {
                newRow = newRow + " p" + rowArray[i].substring(1);
            }
        }
        return  newRow.trim();



    }
}