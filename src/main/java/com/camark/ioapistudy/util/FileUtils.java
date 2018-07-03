package com.camark.ioapistudy.util;

import java.io.*;

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
}