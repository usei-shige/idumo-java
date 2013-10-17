package jp.idumo.java.template;

import java.io.File;
import java.io.IOException;

/**
 * @author Yusei SHIGENOBU
 * 
 * AndroidProviderモジュールを生成するプログラムです．
 * 
 * Providerモジュールに必要なProvider,Component,Modelファイルのテンプレートを
 * 指定のプロジェクト内に生成します．
 * 実行するとTemplateProvider.java,TemplateComponent.java,TemplateModel.java
 * という名前でファイルが作成されます．
 * 実行後に作成したいモジュール名に各ファイルをリネームしてください．
 * また，TemplateModel.javaに関してはモジュールの機能によって使用しない場合があるため，
 * 使用しない場合は各自で削除してください．
 */

public class CreateTemplateAndroidProvider {
	private static String Current_Path = new File("").getAbsolutePath();
	private static final String DELETE_STRING = "common/module";
	private static final String ADD_STRING = "platform/android/module/src/jp/idumo/java/android/";
	private static int index = Current_Path.indexOf(DELETE_STRING);
	private static String Root_Path = Current_Path.substring(0, index);
	private static String Copy_Path = Root_Path + ADD_STRING;
	
	public static void main(String[] args) {
		String Template_PPath = Current_Path + "/template/TemplateAndroidProvider.txt";
		String Template_CPath = Current_Path + "/template/TemplateComponent.txt";
		String Template_MPath = Current_Path + "/template/TemplateModel.txt";
		String Provider_Path  = Copy_Path + "parts/provider/TemplateProvider.java";
		String Component_Path = Copy_Path + "component/sensor/TemplateComponent.java";
		String Model_Path     = Copy_Path + "model/TemplateModel.java";
		
		try {
			new CopyTemplateFile(Template_PPath, Provider_Path);
		} catch (IOException e) {
	        e.printStackTrace();
		}
		
		try {
			new CopyTemplateFile(Template_CPath, Component_Path);
		} catch (IOException e) {
	        e.printStackTrace();
		}
		
		try {
			new CopyTemplateFile(Template_MPath, Model_Path);
		} catch (IOException e) {
	        e.printStackTrace();
		}
	}
}