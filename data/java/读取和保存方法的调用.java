 
 ͨ��uri��stream�ķ�ʽ ����Ƭ���ص�bitmap��  ���ؼ��ز����ź��λͼ
 1Ϊ��Ǳ��� 1��ʾ������Ҳ������ͼ��ҪĬ��ͼƬ����
 final Bitmap photo = ImagesUtil.loadBitmap(context, Uri.fromFile(tempFile), 
 400, 600,1);

  try {   //����ͼƬ�ı��湤�� ��û�м��õ�ͼƬ���浽SD����
            Uri uri=ImagesUtil.saveImage(photo);
            //uri.toString()���浽������  ���� uri=Uri.parse(uri.toString());��
			��ת��
            if (uri.toString()!=null){
				   //�����ݿⰲ�������  ����Ƭ�ĵ�ַ���浽���ݿ���
                    mSecurityStatusDB.insertZhaoPianDiZhi(uri.toString());
					
					  } catch (FileNotFoundException e) {
            e.printStackTrace();
            MyLog.ShowLog("FileNotFoundException e");
        }


		// ����һ���Ե�ǰʱ��Ϊ���Ƶ��ļ�
    private File tempFile = new 
	File(Environment.getExternalStorageDirectory(),getPhotoFileName());

// ʹ��ϵͳ��ǰ���ڼ��Ե�����Ϊ��Ƭ������
    public String getPhotoFileName() {
         /* �������ظ���ͼƬ�ļ�
      Date date = new1 Date(System.currentTimeMillis());
       SimpleDateFormat dateFormat = new1 
	   SimpleDateFormat("'IMG'_yyyyMMdd_HHmmss");
        return dateFormat.format(date) + ".jpg";*/
        return "tanyinqing.jpg";

    }