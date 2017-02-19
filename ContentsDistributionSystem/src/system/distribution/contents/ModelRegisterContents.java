package system.distribution.contents;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
/**
 *
 * @author matak
 *
 */
public class ModelRegisterContents {
    final static String LOCATION_CONTENTS_ROOT =	// コンテンツディレクトリの物理パス
    		  "C:\\pleiades\\workspace\\ContentsDistributionSystem"	// プロジェクトの物理パス
    		+ "\\WebContent\\recochoku";
	static String CONTEXTPATH_CONTENTS_ROOT =	// コンテンツディレクトリのDLリンク
			"/ContentsDistributionSystem/recochoku";
	final static String SEPARATOR_LOCATION = "\\";
	final static String SEPARATOR_CONTEXTPATH = "/";
	final static String STR_EMPTY = "";

	static String locationCurrentDirectory = "";	// 検索中のディレクトリ物理パス
	static String contextPathCurrentDirectory = "";	// 検索中のディレクトリDLリンク

	/**
	 * コンテンツディレクトリにあるファイル名とダウンロードリンクのMapを返す
	 * ファイルがない場合はnullを返す
	 * @return Map<ファイル名,DLリンク>
	 */
	public static Map<String,String> registorContents() {
		Map<String,String> map = new HashMap<String, String>();
		File currentDirectory =	// カレントディレクトリ
				new File(
						  LOCATION_CONTENTS_ROOT
						+ locationCurrentDirectory
						);
		System.out.println(currentDirectory);
		File files[] = currentDirectory.listFiles();	// カレントディレクトリにあるファイル

		for( int i=0; i<files.length; i++) {
			if( files[i].isFile() ) {
				// ファイルの時 ファイル名とDLリンクを登録
				map.put(
						files[i].getName(),
						  CONTEXTPATH_CONTENTS_ROOT
						+ contextPathCurrentDirectory
						+ SEPARATOR_CONTEXTPATH
						+ files[i].getName()
						);
			} else  if( files[i].isDirectory() ) {
				// ディレクトリの時 その階層へ移動
				locationCurrentDirectory =
						  locationCurrentDirectory
						+ SEPARATOR_LOCATION
						+ files[i].getName();
				contextPathCurrentDirectory =
						  contextPathCurrentDirectory
						+ SEPARATOR_CONTEXTPATH
						+ files[i].getName();
				// カレントディレクトリを移動して再度検索する
				map.putAll(registorContents());
				// ディレクトリの検索が終わったのでカレントディレクトリを元に戻す
				locationCurrentDirectory =
						locationCurrentDirectory.replace(
								  SEPARATOR_LOCATION
								+ files[i].getName(),
								STR_EMPTY);
				contextPathCurrentDirectory =
						contextPathCurrentDirectory.replace(
								SEPARATOR_CONTEXTPATH
								+ files[i].getName(),
								STR_EMPTY);
			}
		}
		return map;
	}

	public static Map<String,String> searchOnFilename(
			String keyword,
			Map<String, String> mapContents) {
		Map<String,String> mapSearchResult = new HashMap<String, String>();
		if(		keyword == null
			||	"".equals(keyword)
		) {
			// キーワードがない場合 リセットする
			mapSearchResult = mapContents;
			registorContents();
		} else {
			mapSearchResult.clear();
			Set<String> setFilename = mapContents.keySet();
			for(String filename: setFilename) {
				if( filename.contains(keyword) ) {
					mapSearchResult.put(filename, mapContents.get(filename));
				}
			}
		}
		return mapSearchResult;
	}
}
