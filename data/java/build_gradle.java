studioʹ��
�Ҳ�����ʽ  Theme.Dialog
compileSdkVersion ��19���22�Ϳ����ˡ�

apply plugin: 'com.android.application'

android {
    compileSdkVersion 22
	buildToolsVersion "21.1.2"

CompileSdkVersion����SDK�İ汾�ţ�Ҳ����API Level������API-19��API-20��API-21��
�ȡ�   
buildeToolVersion���㹹�����ߵİ汾�����а����˴������aapt��dx�ȵȡ�������ߵ�
Ŀ¼λ��..your_sdk_path/build-tools/XX.XX.XX
����汾��һ����API-LEVEL.0.0�� ����I/O2014����Ϸ�����API20��Ӧ��build-tool�İ�
������20.0.0
����֮�������С�汾������20.0.1�ȵȡ�
	��ecplise��project.properties�п�������sdk.buildtools=17.0.0��Ҳ���Բ����ò�
	���õĻ�����ָ�����°汾������android studio���Ǳ�����build.gradle������

    defaultConfig {
        applicationId "com.linyou.lifeservice"
        minSdkVersion 14
        targetSdkVersion 18
    }

    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android.txt'), 
			'proguard-rules.txt'
        }
    }
}

dependencies {
    compile 'com.android.support:support-v4:18.0.0'
    compile files('libs/andbase.jar')
    compile files('libs/appcompat_v7.jar')
    compile files('libs/universal-image-loader-1.9.3.jar')
    compile files('libs/xUtils-2.6.14.jar')
    compile files('libs/alipaySdk-20160825.jar')
    compile files('libs/libammsdk.jar')
}


������ø߰汾��build-toolȥ����һ���Ͱ汾��sdk���̣�����build-tool�İ汾Ϊ20��
ȥ����һ��sdk�汾Ϊ18��
���磺compileSdkVersion 18  
buildToolsVersion "22.0.1"����Ҳ��OK�ġ�


���бȽ���Ҫ��Ŀ¼������build-tools����platforms����platform-tools����tools��
��build-tools�������ǲ�ͬ�汾(����21.1.1)��build���ߣ���Щ���߰�����aapt�������
��dx.bat��aidl.exe�ȵ�
��platform���Ǵ�Ų�ͬAPI-level�汾SDKĿ¼�ĵط�
��platform-tools����һЩandroidƽ̨��صĹ��ߣ�adb��fastboot��
��tools����ָ�İ�׿������صĹ��ߣ�����android.bat��ddms.bat(Dalvik debug 
 
