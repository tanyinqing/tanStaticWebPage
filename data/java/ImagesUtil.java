package com.gas.Securitycheck.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.content.CursorLoader;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.ImageView;

import com.gas.Securitycheck.Constant;
import com.gas.Securitycheck.R;
import com.gas.Securitycheck.ServiceApplication;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by xinliwei on 2015/7/2.
 */
public class ImagesUtil {
    public static final String TAG = "ImagesUtil.class";

    /**
     * �����ְ취����Ƭ���ص�bitmap�У�1.ͨ��uri��stream�ķ�ʽ
     * @param mContext Context
     * @param uri  ��Դuri
     * @param width ���غ����ŵ���Ŀ����
     * @param height ���غ����ŵ���Ŀ��߶�
     * @return ���ز����ź��λͼ
     */
    public static Bitmap loadBitmap(Context mContext, Uri uri, int width,int 
	height,int mark) {
        Bitmap mBitmap = null;
        try {
            ContentResolver resolver = mContext.getContentResolver();
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPreferredConfig = Bitmap.Config.RGB_565;  // FaceDetecor
			ֻ�ܶ�ȡRGB 565��ʽ��Bitmap
            Bitmap bitmap = 
			BitmapFactory.decodeStream(resolver.openInputStream(uri), null, 
			options);
            mBitmap = ThumbnailUtils.extractThumbnail(bitmap, width, height);  
			 // ����λͼ��ָ����С
        } catch (FileNotFoundException ex) {
            //Log.e(mContext.getPackageName(), "ͼ������쳣: " + 
			ex.getMessage());
            MyLog.ShowLog("ͼ������쳣: " + ex.getMessage());
            // ͼƬ�Ĵ�С��400 600
            if (mark==1){
                Bitmap 
				mBitmap1=BitmapFactory.decodeResource(ServiceApplication.getIns
				tance().getResources(), R.drawable.miss_photo);
                return ThumbnailUtils.extractThumbnail(mBitmap1, width, 
				height);   // ����λͼ��ָ����С
            }else if (mark==0){

            }
        }
        return mBitmap;
    }

    /**
     * �����ְ취����Ƭ���ص�bitmap�У�2.����Ƭ����ʵ·������
     * @param path  �����ص�ͼ���·��
     * @param width ���غ����ŵ���Ŀ����
     * @param height ���غ����ŵ���Ŀ��߶�
     * @return ���ز����ź��λͼ
     */
    public static Bitmap loadBitmap(String path, int width, int height) {
        Bitmap mBitmap = null;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;  // FaceDetecorֻ
		�ܶ�ȡRGB 565��ʽ��Bitmap
        Bitmap bitmap = BitmapFactory.decodeFile(path, options);
        mBitmap = ThumbnailUtils.extractThumbnail(bitmap, width, height);  // 
		����λͼ
        return mBitmap;
    }

    // �����Ƭ����ʵ·��
    public static String obtainFilePath(Context context, Uri uri){
        String filePath = null;
        String[] projection = { MediaStore.Images.Media.DATA };

        CursorLoader loader = new CursorLoader(context, uri, projection, null,
                null, null);
        Cursor cursor = loader.loadInBackground();

        if (cursor != null) {
            cursor.moveToFirst();
            filePath = cursor.getString(cursor.getColumnIndex(projection[0]));
            cursor.close();
        }
        return filePath;
    }

    // ��ͼƬ���浽SD����  Ϊʲô����������Ǳ���  ��Ϊû�м���д�ڴ濨��Ȩ��
    public static Uri saveImage(Bitmap bitmap) throws FileNotFoundException {
        //���ж��ֻ��Ƿ�װ��SD��,�����Խ��ж�д
        if(!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUN
		TED)){
//            Toast.makeText(getApplicationContext(),
//                    "û��SD��,�޷�����ͼ��.", Toast.LENGTH_SHORT).show();
            Log.e("ImagesUtil.java","û��SD��,�޷�����ͼ��.");
            return null;
        }

        //�ļ��ı���·��
        String[] file_path=new String[7];
        file_path[0]="ȼ������";
        file_path[1]= 
		ServiceApplication.getInstance().readUser().getU_RealName();
        file_path[2]= 
		ServiceApplication.mPrefUtil.getStrSetting(Constant.SP_Id)+"-"+ServiceA
		pplication.mPrefUtil.getStrSetting(Constant.AnJianCiShu);
        file_path[3]= 
		(ServiceApplication.mPrefUtil.getStrSetting(Constant.XiaoQuMingZi)).rep
		lace("#", "");
        //file_path[3]= 
		ServiceApplication.mPrefUtil.getStrSetting(Constant.YongMuMingZi);
       // file_path[4]= 
	   ServiceApplication.mPrefUtil.getStrSetting(Constant.LouHao);
        file_path[4]= 
		ServiceApplication.mPrefUtil.getStrSetting(Constant.DanYuanHao);
        file_path[5]= 
		ServiceApplication.mPrefUtil.getStrSetting(Constant.YongMuMingZi);
        file_path[6]= 
		ServiceApplication.mPrefUtil.getStrSetting("AnJianXiangId");
       /* file_path[1]="��������¶԰1��";
        file_path[2]="6��¥";
        file_path[3]="4��Ԫ";
        file_path[4]="301��";*/

        //�ļ�������ʱ��ɷ�
        Calendar calendar=Calendar.getInstance();
        Date data=calendar.getTime();
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time=format.format(data);

        // ����ⲿSD��,������Ӧ�ó���ı���Ŀ¼,������Ƭ
        File sdCard = Environment.getExternalStorageDirectory();
        // File photoDir = new1 File(sdCard.getAbsolutePath() + "/mycamera");
         File photoDir = new File(sdCard.getAbsolutePath() + 
		 "/"+file_path[0]+"/"+file_path[1]+"/"+file_path[2]+"/"+file_path[3]+"/
		 "+file_path[4]+"/"+file_path[5]+"/"+file_path[6]);
        MyLog.ShowLog("����ͼ���ַ"+photoDir.getAbsolutePath());
        LogTxt.writeLog(photoDir.getAbsolutePath(), "ͼƬ����ĵ�ַ");
        if(!photoDir.exists()){
            photoDir.mkdirs();
        }
       // File photo = new1 File(photoDir,(new1 Date()).getTime() + ".jpeg");
        File photo = new File(photoDir,"13 ȼ����ʴ�ϻ�"+time+".jpeg");
       MyLog.ShowLog("����photo�ļ�");
        FileOutputStream fOut = new FileOutputStream(photo); //������bug
        MyLog.ShowLog("������ļ��������");
        // ������д���ļ�.�����100������ʾ��ѹ��
        bitmap.compress(Bitmap.CompressFormat.JPEG, 80, fOut);

        // ����ͼƬ��Uri���������ݷ���ͼƬ
        return Uri.parse("file://" + photo.getAbsolutePath());
//        Toast.makeText(this,uri.toString(),Toast.LENGTH_LONG).show();
    }

    // ��ʾ�����ͼƬ(ʹ����SmartAndroid�е�
	com.tandong.sa.zUImageLoader.core.ImageLoader)
  /*  public void displayImage(String imageUrl, ImageView imageView){
        DisplayImageOptions options;
        options = new1 DisplayImageOptions.Builder()
                .showImageOnLoading(R.mipmap.ic_launcher)       // ����ͼƬ����
				���ڼ���ʾ��ͼƬ
                .showImageForEmptyUri(R.mipmap.ic_launcher)     // ����ͼƬUri
				Ϊ�ջ��Ǵ����ʱ����ʾ��ͼƬ
                .showImageOnFail(R.mipmap.ic_launcher)          // ����ͼƬ����
				/��������д���ʱ����ʾ��ͼƬ
                .cacheInMemory(true)        // �������ص�ͼƬ�Ƿ񻺴����ڴ���
                .cacheOnDisc(true)          // �������ص�ͼƬ�Ƿ񻺴���SD����
                .considerExifParams(true)   // �Ƿ���JPEGͼ��EXIF��������ת��
				��ת��
                .imageScaleType(ImageScaleType.EXACTLY_STRETCHED)   // ����ͼƬ
				����εı��뷽ʽ��ʾ
                .bitmapConfig(Bitmap.Config.RGB_565)                // ����ͼƬ
				�Ľ�������
//                .decodingOptions(BitmapFactory.Options decodingOptions)      
         //����ͼƬ�Ľ�������
                //.delayBeforeLoading(int delayInMillis)        //int 
				delayInMillisΪ�����õ�����ǰ���ӳ�ʱ��
                //.preProcessor(BitmapProcessor preProcessor)   //����ͼƬ����
				����ǰ����bitmap��������
                .resetViewBeforeLoading(true)                   //����ͼƬ����
				��ǰ�Ƿ����ã���λ
                .displayer(new1 RoundedBitmapDisplayer(20))      //�Ƿ�����ΪԲ
				�ǣ�����Ϊ����
                .displayer(new1 FadeInBitmapDisplayer(100))      //�Ƿ�ͼƬ����
				�ú���Ķ���ʱ��
                .build();                   // �������

        XlwApplication.getInstance().getImageLoader().displayImage(imageUrl, 
		imageView, options);
    }*/
}

/*
����ͼƬ������
1.ֱ�Ӽ��أ�
imageLoader.displayImage(imageUrl, imageView); // imageUrl����ͼƬ��URL��ַ
��imageView�������ͼƬ��IMAGEVIEW�ؼ� ��

2.�����Զ������õ�һ��ͼƬ��
imageLoader.displayImage(imageUrl, imageView��options); // imageUrl����ͼƬ��
URL��ַ��imageView�������ͼƬ��IMAGEVIEW�ؼ� �� options����DisplayImageOptions
�����ļ�

3.ͼƬ����ʱ�����������ļ���
imageLoader.displayImage(imageUrl, imageView, options, new1 
ImageLoadingListener() {
    @Override
    public void onLoadingStarted() {
       //��ʼ���ص�ʱ��ִ��
    }
    @Override
    public void onLoadingFailed(FailReason failReason) {
       //����ʧ�ܵ�ʱ��ִ��
    }
    @Override
    public void onLoadingComplete(Bitmap loadedImage) {
       //���سɹ���ʱ��ִ��
    }
    @Override
    public void onLoadingCancelled() {
       //����ȡ����ʱ��ִ��

    }});

4.ͼƬ����ʱ�򣬴������ִ����ؽ����������

imageLoader.displayImage(imageUrl, imageView, options, new1 
ImageLoadingListener() {
    @Override
    public void onLoadingStarted() {
       //��ʼ���ص�ʱ��ִ��
    }
    @Override
    public void onLoadingFailed(FailReason failReason) {
       //����ʧ�ܵ�ʱ��ִ��
    }
    @Override
    public void onLoadingComplete(Bitmap loadedImage) {
       //���سɹ���ʱ��ִ��
    }
    @Override
    public void onLoadingCancelled() {
       //����ȡ����ʱ��ִ��
    },new1 ImageLoadingProgressListener() {
      @Override
      public void onProgressUpdate(String imageUri, View view, int current,int 
	  total) {
      //��������� ProgressBar�Ľ�����Ϣ
      }
    });
 */
/*
���ر���ͼƬ��
String imageUri = "http://site.com/image.png";      // from Web
String imageUri = "file:///mnt/sdcard/image.png";   // from SD card  ���������
�ܲ���
String imageUri = "content://media/external/audio/albumart/13";     // from 
content provider
String imageUri = "assets://image.png";             // from assets
String imageUri = "drawable://" + R.drawable.image; // from drawables (only 
images, non-9patch)
 */
