package com.camark.dataanalysis;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DataAnalysis {
    private static final int WINS = 1;
    private static final int DRAWS = 0;
    private static final int LOSSES = -1;

    public static void main(String[] args) {

        //[0.40111420612813364, 0.2994428969359331, 0.2994428969359331]
        //[0.17432434691046447, 0.2465444334876569, 0.5791312196018786]

        //[0.3332140531890044, 0.32566958406019675, 0.341116362750799]
        //[0.6387058118633913, 0.2210904733373278, 0.140203714799281]

        //[0.5017301038062283, 0.2975778546712803, 0.20069204152249132]
        //[0.24287268770402612, 0.2781284004352557, 0.4789989118607182]

        analysisData();

        //String[] dataStrs = "123   a   321".split("a");

        //System.out.println(Arrays.asList(dataStrs));


    }

    public static Double[] multiple2probability(double... multiples) {
        Double[] probabilitys = new Double[multiples.length];
        double[] probabilityMultiples = new double[multiples.length];

        double probabilityMultipleSum = 0;

        for (int i = 0; i < multiples.length; i++) {
            probabilityMultiples[i] = 1 / multiples[i];
            probabilityMultipleSum += probabilityMultiples[i];
        }

        for (int i = 0; i < multiples.length; i++) {
            probabilitys[i] = probabilityMultiples[i] / probabilityMultipleSum;
        }

        return probabilitys;
    }

    public static void analysisData() {
        //5:0 $  -1 $    1.32,4.10,7.80  $  2.22,3.06,2.84

        List<Double> homeWinsAddList = new ArrayList<>();
        List<Double> homeWinsSubList = new ArrayList<>();

        List<Double> homeDrawsAddList = new ArrayList<>();
        List<Double> homeDrawsSubList = new ArrayList<>();

        List<Double> homeLossesAddList = new ArrayList<>();
        List<Double> homeLossesSubList = new ArrayList<>();

        List<String> homeWinsAddStrList = new ArrayList<>();
        List<String> homeWinsSubStrList = new ArrayList<>();

        List<String> homeDrawsAddStrList = new ArrayList<>();
        List<String> homeDrawsSubStrList = new ArrayList<>();

        List<String> homeLossesAddStrList = new ArrayList<>();
        List<String> homeLossesSubStrList = new ArrayList<>();

        BufferedReader bufr = null;
        String dataStrLine;
        try {
            bufr = new BufferedReader(new FileReader("C:\\Users\\Administrator\\Desktop\\数据2.txt"));

            while ((dataStrLine = bufr.readLine()) != null) {
                dataStrLine = dataStrLine.trim();

                if (!dataStrLine.isEmpty()) {
                    //dataStrLine = "5:0 $ -1  $  1.32,4.10,7.80   $    2.22,3.06,2.84";
                    String[] dataStrs = dataStrLine.split("H");

                    int result = judgeWinsDrawsLossesByResultStr(dataStrs[1].trim());
                    int rate = Integer.parseInt(dataStrs[2].trim());

                    String winLossesDataStr = dataStrs[3].trim();
                    String rateDataStr = dataStrs[4].trim();

                    dataStrs = winLossesDataStr.split(",");

                    Double[] winLossesDatas = multiple2probability(Double.parseDouble(dataStrs[0].trim()), Double.parseDouble(dataStrs[1].trim()), Double.parseDouble(dataStrs[2].trim()));

                    dataStrs = rateDataStr.split(",");

                    Double[] rateDatas = multiple2probability(Double.parseDouble(dataStrs[0].trim()), Double.parseDouble(dataStrs[1].trim()), Double.parseDouble(dataStrs[2].trim()));

                    switch(result){
                        case WINS:
                            if (rate == 1) {
                                homeWinsAddList.add(winLossesDatas[0] + winLossesDatas[1] - rateDatas[0]);
                                homeWinsAddStrList.add(dataStrLine + String.format("   %.3f",winLossesDatas[2] - rateDatas[1] - rateDatas[2]));
                            } else if (rate == -1) {
                                homeWinsSubList.add(winLossesDatas[1] + winLossesDatas[2] - rateDatas[2]);
                                homeWinsSubStrList.add(dataStrLine + String.format("   %.3f",winLossesDatas[0] - rateDatas[0] - rateDatas[1]));
                            }
                            break;
                        case DRAWS:
                            if (rate == 1) {
                                homeDrawsAddList.add(winLossesDatas[0] + winLossesDatas[1] - rateDatas[0]);
                                homeDrawsAddStrList.add(dataStrLine + String.format("   %.3f",winLossesDatas[2] - rateDatas[1] - rateDatas[2]));
                            } else  if (rate == -1) {
                                homeDrawsSubList.add(winLossesDatas[1] + winLossesDatas[2] - rateDatas[2]);
                                homeDrawsSubStrList.add(dataStrLine + String.format("   %.3f",winLossesDatas[0] - rateDatas[0] - rateDatas[1]));
                            }
                            break;

                        case LOSSES:
                            if (rate == 1) {
                                homeLossesAddList.add(winLossesDatas[0] + winLossesDatas[1] - rateDatas[0]);
                                homeLossesAddStrList.add(dataStrLine + String.format("   %.3f",winLossesDatas[2] - rateDatas[1] - rateDatas[2]));
                            } else  if (rate == -1) {
                                homeLossesSubList.add(winLossesDatas[1] + winLossesDatas[2] - rateDatas[2]);
                                homeLossesSubStrList.add(dataStrLine + String.format("   %.3f",winLossesDatas[0] - rateDatas[0] - rateDatas[1]));
                            }
                            break;

                    }

                }
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufr != null) {
                try {
                    bufr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }

        writeListToFile(homeWinsAddStrList,"C:\\Users\\Administrator\\Desktop\\测试结果\\W+1.txt");
        writeListToFile(homeWinsSubStrList,"C:\\Users\\Administrator\\Desktop\\测试结果\\W-1.txt");

        writeListToFile(homeDrawsAddStrList,"C:\\Users\\Administrator\\Desktop\\测试结果\\D+1.txt");
        writeListToFile(homeDrawsSubStrList,"C:\\Users\\Administrator\\Desktop\\测试结果\\D-1.txt");

        writeListToFile(homeLossesAddStrList,"C:\\Users\\Administrator\\Desktop\\测试结果\\L+1.txt");
        writeListToFile(homeLossesSubStrList,"C:\\Users\\Administrator\\Desktop\\测试结果\\L-1.txt");


    }

    public static int judgeWinsDrawsLossesByResultStr(String resultStr) {



        String[] dataStrs = resultStr.split(":");

        int home = Integer.parseInt(dataStrs[0]);
        int guest = Integer.parseInt(dataStrs[1]);
        int result = WINS;

        if (home > guest) {
            result = WINS;
        } else if (home == guest) {
            result = DRAWS;
        } else {
            result = LOSSES;
        }

        return result;
    }

    public static void writeListToFile(List<String> writeList,String fileStr) {
        BufferedWriter bufw = null;
        try {
            bufw = new BufferedWriter(new FileWriter(fileStr));


            for (String line:writeList ) {
                bufw.write(line);
                bufw.newLine();

            }




        } catch (IOException e) {
            System.out.println("catch:" + e.toString());
        } finally {
            try {
                if (bufw != null)
                    bufw.close();
            } catch (IOException e) {
                System.out.println(e.toString());
            }

        }
    }


}
