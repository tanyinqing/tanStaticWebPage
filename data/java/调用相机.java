调用拍照的方法
  mHandler.sendEmptyMessage(1);
 
 private Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
	            	// 调用系统的拍照功能
                    Intent intent2 = new
					Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				// 指定调用相机拍照后照片的储存路径  完成保存的过程
                    intent2.putExtra(MediaStore.EXTRA_OUTPUT,
					Uri.fromFile(tempFile));
		        //PHOTO_REQUEST_TAKEPHOTO   这个是requestcode参数  本Activity得到图片
                    startActivityForResult(intent2, PHOTO_REQUEST_TAKEPHOTO);
                    break;
            }
        }
    };


	@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent 
	data) {
        // TODO Auto-generated method stub

        switch (requestCode) {
		//从本地得到图片的Uri 并进行剪切
            case PHOTO_REQUEST_TAKEPHOTO:
                if (resultCode== RESULT_OK) {
		    //保存拒签图片的地址
                    saveImage();                 


                    Toast.makeText(this, "保存成功",
					Toast.LENGTH_SHORT).show();
                  
                    finish();
                }else {
                    Toast.makeText(this, "保存失败",
					Toast.LENGTH_SHORT).show();
                }


                //MyLog.ShowLog(" saveImage();  ");
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }



// 创建一个以当前时间为名称的文件
    private File tempFile = new 
	File(Environment.getExternalStorageDirectory(),getPhotoFileName());

    private static final int PHOTO_REQUEST_TAKEPHOTO = 1;// ????


// 使用系统当前日期加以调整作为照片的名称
    public String getPhotoFileName() {
         /*
      Date date = new1 Date(System.currentTimeMillis());
       SimpleDateFormat dateFormat = new1 
	   SimpleDateFormat("'IMG'_yyyyMMdd_HHmmss");
        return dateFormat.format(date) + ".jpg";*/
        return "tanyinqing.jpg";

    }


	 private void saveImage() {
        final Bitmap photo = ImagesUtil.loadBitmap(this, Uri.fromFile(tempFile), 
		400, 600,1);

        try {  //利用图片的保存工具 将没有剪裁的图片保存到SD卡内
            Uri uri=ImagesUtil.saveImage(photo);
		//uri.toString()保存到数库内  利用 uri=Uri.parse(uri.toString());进行转化
			?????
            if (uri.toString() !=null){

		弹出提示保存成功

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            MyLog.ShowLog("FileNotFoundException e");
        }
    }