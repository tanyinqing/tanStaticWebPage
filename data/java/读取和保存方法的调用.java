 
 通过uri用stream的方式 将照片加载到bitmap中  返回加载并缩放后的位图
 1为标记变量 1表示，如果找不到这个图就要默认图片代替
 final Bitmap photo = ImagesUtil.loadBitmap(context, Uri.fromFile(tempFile), 
 400, 600,1);

  try {   //利用图片的保存工具 将没有剪裁的图片保存到SD卡内
            Uri uri=ImagesUtil.saveImage(photo);
            //uri.toString()保存到数库内  利用 uri=Uri.parse(uri.toString());进
			行转化
            if (uri.toString()!=null){
				   //在数据库安检项表中  将照片的地址保存到数据库中
                    mSecurityStatusDB.insertZhaoPianDiZhi(uri.toString());
					
					  } catch (FileNotFoundException e) {
            e.printStackTrace();
            MyLog.ShowLog("FileNotFoundException e");
        }


		// 创建一个以当前时间为名称的文件
    private File tempFile = new 
	File(Environment.getExternalStorageDirectory(),getPhotoFileName());

// 使用系统当前日期加以调整作为照片的名称
    public String getPhotoFileName() {
         /* 建立不重复的图片文件
      Date date = new1 Date(System.currentTimeMillis());
       SimpleDateFormat dateFormat = new1 
	   SimpleDateFormat("'IMG'_yyyyMMdd_HHmmss");
        return dateFormat.format(date) + ".jpg";*/
        return "tanyinqing.jpg";

    }