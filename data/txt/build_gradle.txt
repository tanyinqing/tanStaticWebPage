studio使用
		找不到样式  Theme.Dialog
		compileSdkVersion 从19变成22就可以了。

		apply plugin: 'com.android.application'

		android {
		compileSdkVersion 22
		buildToolsVersion "21.1.2"

		CompileSdkVersion是你SDK的版本号，也就是API Level，例如API-19、API-20、API-21等
		等。
		buildeToolVersion是你构建工具的版本，其中包括了打包工具aapt、dx等等。这个工具的
		目录位于..your_sdk_path/build-tools/XX.XX.XX
		这个版本号一般是API-LEVEL.0.0。 例如I/O2014大会上发布了API20对应的build-tool的版
		本就是20.0.0
		在这之间可能有小版本，例如20.0.1等等。
		在ecplise的project.properties中可以设置sdk.buildtools=17.0.0。也可以不设置不
		设置的话就是指定最新版本。而在android studio中是必须在build.gradle中设置

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


		你可以用高版本的build-tool去构建一个低版本的sdk工程，例如build-tool的版本为20，
		去构建一个sdk版本为18的
		例如：compileSdkVersion 18
		buildToolsVersion "22.0.1"这样也是OK的。


		其中比较重要的目录包括【build-tools】【platforms】【platform-tools】【tools】
		【build-tools】里面是不同版本(例如21.1.1)的build工具，这些工具包括了aapt打包工具
		、dx.bat、aidl.exe等等
		【platform】是存放不同API-level版本SDK目录的地方
		【platform-tools】是一些android平台相关的工具，adb、fastboot等
		【tools】是指的安卓开发相关的工具，例如android.bat、ddms.bat(Dalvik debug
