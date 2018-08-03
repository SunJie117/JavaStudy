package com.camark.ioapistudy.util;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileUtils {
    private static final int BUFFERE_SIZE = 1024;
    public static void copyFile(File sourceFile, File destinationFile ) throws IOException {

        if (sourceFile.exists()) {
            if (sourceFile.isFile()) {
                BufferedInputStream sourceFileBis = new BufferedInputStream(new FileInputStream(sourceFile));
                BufferedOutputStream destinationFileBos =  new BufferedOutputStream(new FileOutputStream(destinationFile));


                byte[] bufferedBytes = new byte[BUFFERE_SIZE];
                int len;

                try {

                    while ((len = sourceFileBis.read(bufferedBytes)) != -1) {
                        destinationFileBos.write(bufferedBytes,0,len);
                    }
                } finally {
                    if (destinationFileBos != null) {
                        destinationFileBos.close();
                    }

                    if (sourceFileBis != null) {
                        sourceFileBis.close();
                    }


                }
            } else  {
                destinationFile.mkdirs();
                File[] files = sourceFile.listFiles();

                for (File file:files) {
                    copyFile(file, new File(destinationFile,file.getName()));
                }

            }

        }


    }

    public static void deleteFile(File file) {
        if (file.isFile()) {
            file.delete();
        } else {
            File[] files = file.listFiles();

            for (File inerFile:files) {
                deleteFile(inerFile);
            }

            file.delete();
        }
    }

    private static  void tiaoZhengWenJianXiuGaiShiJian() {
        Pattern p = Pattern.compile("\\d{4}-\\d{2}-\\d{2}");
        Matcher m;
        File xuBianDirectory = new File("C:\\Users\\Administrator\\Desktop\\工作\\徐变成果文件");

        File[] xuBianDatDirectorys = xuBianDirectory.listFiles();

        List<String> lineList = null;
        String xiuGaiTimeStr = "";
        String fileNameStr = "";
        int startIndex = 0;
        Date filetime;
        Date xiuGaiTime;
        //SimpleDateFormat format0 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
        Calendar xiuGaiDateCalendar = Calendar.getInstance();
        Calendar fileTimeCalendar = Calendar.getInstance();



        for (File xuBianDatDirectory : xuBianDatDirectorys) {
            if (xuBianDatDirectory.isDirectory()) {

                File[] xuBianDatFiles = xuBianDatDirectory.listFiles();

                for (File xuBianDatFile : xuBianDatFiles) {
                    if (xuBianDatFile.isFile()) {
                        fileNameStr = xuBianDatFile.getName().trim();

                        m = p.matcher(fileNameStr);
                        m.find();
                        xiuGaiTimeStr = m.group();
                        //startIndex = fileNameStr.length() - 14;
                        //xiuGaiTimeStr = fileNameStr.substring(startIndex,startIndex + 10);

                        try {
                            xiuGaiTime = formatDate.parse(xiuGaiTimeStr);
                        } catch (ParseException e) {
                            e.printStackTrace();
                            continue;
                        }

                        xiuGaiDateCalendar.setTime(xiuGaiTime);
                        xiuGaiDateCalendar.add(Calendar.DATE, 1);


                        fileTimeCalendar.setTime(new Date(xuBianDatFile.lastModified()));
                        fileTimeCalendar.set(Calendar.YEAR,xiuGaiDateCalendar.get(Calendar.YEAR));
                        fileTimeCalendar.set(Calendar.DAY_OF_YEAR,xiuGaiDateCalendar.get(Calendar.DAY_OF_YEAR));
                        //xiuGaiDateCalendar.set(Calendar.HOUR,fileTimeCalendar.get(Calendar.HOUR));
                        //xiuGaiDateCalendar.set(Calendar.MINUTE,fileTimeCalendar.get(Calendar.MINUTE));
                        //xiuGaiDateCalendar.set(Calendar.SECOND,fileTimeCalendar.get(Calendar.SECOND));




                        if (xuBianDatFile.setLastModified(fileTimeCalendar.getTimeInMillis())) {


                            fileNameStr = formatDate.format(new Date(xuBianDatFile.lastModified()));
                        } else {
                            fileNameStr = formatDate.format(new Date(xuBianDatFile.lastModified()));
                        }


                        xiuGaiTimeStr  = fileNameStr;

                    }

                }

            }
        }
        System.out.println("修改完成");


    }
}