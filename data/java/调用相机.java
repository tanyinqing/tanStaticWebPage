�������յķ���
  mHandler.sendEmptyMessage(1);
 
 private Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
	            	// ����ϵͳ�����չ���
                    Intent intent2 = new
					Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				// ָ������������պ���Ƭ�Ĵ���·��  ��ɱ���Ĺ���
                    intent2.putExtra(MediaStore.EXTRA_OUTPUT,
					Uri.fromFile(tempFile));
		        //PHOTO_REQUEST_TAKEPHOTO   �����requestcode����  ��Activity�õ�ͼƬ
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
		//�ӱ��صõ�ͼƬ��Uri �����м���
            case PHOTO_REQUEST_TAKEPHOTO:
                if (resultCode== RESULT_OK) {
		    //�����ǩͼƬ�ĵ�ַ
                    saveImage();                 


                    Toast.makeText(this, "����ɹ�",
					Toast.LENGTH_SHORT).show();
                  
                    finish();
                }else {
                    Toast.makeText(this, "����ʧ��",
					Toast.LENGTH_SHORT).show();
                }


                //MyLog.ShowLog(" saveImage();  ");
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }



// ����һ���Ե�ǰʱ��Ϊ���Ƶ��ļ�
    private File tempFile = new 
	File(Environment.getExternalStorageDirectory(),getPhotoFileName());

    private static final int PHOTO_REQUEST_TAKEPHOTO = 1;// ????


// ʹ��ϵͳ��ǰ���ڼ��Ե�����Ϊ��Ƭ������
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

        try {  //����ͼƬ�ı��湤�� ��û�м��õ�ͼƬ���浽SD����
            Uri uri=ImagesUtil.saveImage(photo);
		//uri.toString()���浽������  ���� uri=Uri.parse(uri.toString());����ת��
			?????
            if (uri.toString() !=null){

		������ʾ����ɹ�

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            MyLog.ShowLog("FileNotFoundException e");
        }
    }