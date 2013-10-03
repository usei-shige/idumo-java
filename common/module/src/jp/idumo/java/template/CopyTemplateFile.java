package jp.idumo.java.template;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * @author Yusei SHIGENOBU
 * 
 * モジュールテンプレートを作成するためのファイル
 * 
 * モジュールテンプレートを作成する際に呼び出されます．
 * 与えられた引数に該当するパスに指定のファイルをコピーする．
 *
 */

public class CopyTemplateFile {
	public CopyTemplateFile(String currentPath, String copyPath) throws IOException {
		
		FileChannel currentChannel = new FileInputStream(currentPath).getChannel();
	    FileChannel copyChannel = new FileOutputStream(copyPath).getChannel();
	    try {
	        currentChannel.transferTo(0, currentChannel.size(), copyChannel);
	    } finally {
	        currentChannel.close();
	        copyChannel.close();
	    }
	}
}
