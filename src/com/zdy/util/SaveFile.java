package com.zdy.util;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Created by zdy on 2017/1/22.
 */
public class SaveFile {
    public static void SavePNG(String tempFile ,String saveFilePath) throws IOException {
        RandomAccessFile randomAccessFile_temp=new RandomAccessFile(tempFile,"r");
        File saveFile=new File(saveFilePath);
        RandomAccessFile randomAccessFile_save=new RandomAccessFile(saveFile,"rw");
        randomAccessFile_temp.seek(0);
        System.out.println(randomAccessFile_temp.getFilePointer());
        int b;
        while  ((b=randomAccessFile_temp.readByte())!=-1){
            //randomAccessFile_save.write(b);
            System.out.print(b);
        }
        randomAccessFile_temp.close();
        randomAccessFile_save.close();
    }
}
