/**
 *
 * @author datakent.com
 */
public class NumberReader {

    //Using: 
    //String _rs1 = Number2TextConverter(123); // YüzYirmiÜç
    //String _rs2 = Number2TL_Converter(123.45); // YüzYirmiÜç TL KýrkBeþ Krþ
    
    private final String krs_ = " Krþ";
    private final String tl_ = " TL";

    //String[10][2]
    private final String[][] Text1 = new String[][]{
        {"", ""},
        {"Bir", "On"},
        {"Ýki", "Yirmi"},
        {"Üç", "Otuz"},
        {"Dört", "Kýrk"},
        {"Beþ", "Elli"},
        {"Altý", "Altmýþ"},
        {"Yedi", "Yetmiþ"},
        {"Sekiz", "Seksen"},
        {"Dokuz", "Doksan"}
    };
    
    private final String[] Text2 = new String[] {"Bin", "Milyon", "Milyar", "Trilyon"};

    
    public NumberReader() {
    }

    private String ireader(String value) {
        String part1 = "", part2 = "", part3;
        Integer P = 0;

        try {
            //for debug
            //System.out.println("--- ireader ---");
            //System.out.println(Deg.substring(2, 3));
            //System.out.println(Deg.substring(1, 2));
            //System.out.println(Deg.substring(0, 1));

            part1 = Text1[Integer.valueOf(value.substring(2, 3))][0];//1char
            part2 = Text1[Integer.valueOf(value.substring(1, 2))][1];//1char
            P = Integer.valueOf(value.substring(0, 1));//1char
        } catch (Exception ex) {
        }

        if (P > 1) {
            part3 = Text1[P][0] + "Yüz";
        } else if (P == 1) {
            part3 = "Yüz";
        } else {
            part3 = "";
        }

        return part3 + part2 + part1;
    }

    private String zeroMaker(int lenx) {
        StringBuilder sb = new StringBuilder();

        if (lenx > 0) {
            for (int i = 0; i < lenx; i++) {
                sb.append("0");
            }
        }

        return sb.toString();
    }

    public String Number2TextConverter(long Number) {
        String syt, strNum;

        if (Number <= 0) {
            return "";
        }

        strNum = String.valueOf(Number);
        strNum = zeroMaker(15 - strNum.length()) + strNum;//max/min Len=15

        //for debug
        //System.out.println("--- Number2TextConverter ---");
        //System.out.println(strNum.substring(12, 12+3));
        //System.out.println(strNum.substring(9, 9+3));
        //System.out.println(strNum.substring(6, 6+3));
        //System.out.println(strNum.substring(3, 3+3));
        //System.out.println(strNum.substring(0, 0+3));
        
        syt = ireader(strNum.substring(12, 12 + 3));

        if (!ireader(strNum.substring(9, 9 + 3)).equals("")) {
            if (Integer.valueOf(strNum.substring(9, 9 + 3)) == 1) {
                syt = Text2[0] + syt;
            } else {
                syt = ireader(strNum.substring(9, 9 + 3)) + Text2[0] + syt;
            }
        }

        if (!ireader(strNum.substring(6, 6 + 3)).equals("")) {
            syt = ireader(strNum.substring(6, 6 + 3)) + Text2[1] + syt;
        }

        if (!ireader(strNum.substring(3, 3 + 3)).equals("")) {
            syt = ireader(strNum.substring(3, 3 + 3)) + Text2[2] + syt;
        }

        if (!ireader(strNum.substring(0, 0 + 3)).equals("")) {
            syt = ireader(strNum.substring(0, 0 + 3)) + Text2[3] + syt;
        }

        return syt;
    }

    public String Number2TL_Converter(Double value) {
        String result = "";
        String strValue = String.valueOf(value);
        String[] parts = strValue.split("\\.");

        if (parts != null && parts.length == 2) {
            if (parts[1].length() == 1) {
                parts[1] += "0";
            }

            Integer part0 = Integer.valueOf(parts[0]);
            Integer part1 = Integer.valueOf(parts[1]);

            if (part0 == 0 && part1 > 0) {
                result = Number2TextConverter(part1.longValue()) + krs_;
            } else if (part0 > 0 && part1 == 0) {
                result = Number2TextConverter(part0.longValue()) + tl_;
            } else if (part0 > 0 && part1 > 0) {
                result = Number2TextConverter(part0.longValue()) + tl_ + 
                        " " + Number2TextConverter(part1.longValue()) + krs_;
            }
        }

        return result;
    }

}
