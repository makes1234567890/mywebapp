package system.distribution.contents;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
/**
 * コンテンツデータ保有クラス
 * @author matak
 *
 */
public class ModelRegisterContents {

	// 保有データ
	private Map<String, String> mapRegistored = new HashMap<String, String>();
	public Map<String, String> getMapRegistored() {
		return mapRegistored;
	}

	// 定数
    private final static String LOCATION_CONTENTS_ROOT =	// コンテンツディレクトリの物理パス
    		  "C:\\pleiades\\gitrepository\\ContentsDistributionSystem\\WebContent\\contents";
	private final static String CONTEXTPATH_CONTENTS_ROOT =	// コンテンツディレクトリのDLリンク
			"/ContentsDistributionSystem/contents";
	private final static String DELIMITER_CONTEXTPATH = "/";
	private final static String STR_EMPTY = "";
	// 変数
	String locationCurrentDirectory = "";	// 検索中のディレクトリ物理パス
	String contextPathCurrentDirectory = "";	// 検索中のディレクトリDLリンク

	// コンストラクタ
	public ModelRegisterContents() {
		this.mapRegistored.clear();
		this.registorContents();
	}
	/**
	 * コンテンツディレクトリにあるファイルをインスタンスに格納する
	 */
	public void registorContents() {
		File currentDirectory =	// カレントディレクトリ
				new File(
						  LOCATION_CONTENTS_ROOT
						+ locationCurrentDirectory
						);
		SystemLog.println(currentDirectory.getPath(), this);
		File files[] = currentDirectory.listFiles();	// カレントディレクトリにあるファイル

		for( int i=0; i<files.length; i++) {
			if( files[i].isFile() ) {
				// ファイルの時 ファイル名とDLリンクを登録
				mapRegistored.put(
						files[i].getName(),
						  CONTEXTPATH_CONTENTS_ROOT
						+ contextPathCurrentDirectory
						+ DELIMITER_CONTEXTPATH
						+ files[i].getName()
						);
			} else  if( files[i].isDirectory() ) {
				// ディレクトリの時 その階層へ移動
				locationCurrentDirectory =
						  locationCurrentDirectory
						+ File.separator
						+ files[i].getName();
				contextPathCurrentDirectory =
						  contextPathCurrentDirectory
						+ DELIMITER_CONTEXTPATH
						+ files[i].getName();
				// カレントディレクトリを移動して再度検索する
				registorContents();
				// ディレクトリの検索が終わったのでカレントディレクトリを元に戻す
				locationCurrentDirectory =
						locationCurrentDirectory.replace(
								  File.separator
								+ files[i].getName(),
								STR_EMPTY
								);
				contextPathCurrentDirectory =
						contextPathCurrentDirectory.replace(
								DELIMITER_CONTEXTPATH
								+ files[i].getName(),
								STR_EMPTY
								);
			}
		}
	}

	/**
	 * 登録済みコンテンツマップからファイル名（キー）で部分一致検索する
	 * @param filename 検索キーワード（ファイル名）
	 */
	public Map<String,String> searchOnFilename( String filename ) {
		Map<String,String> mapSearchResult = new HashMap<String, String>();
		mapSearchResult = searchOnKey(filename, mapRegistored);
		if( mapSearchResult == null ) {
			mapSearchResult = mapRegistored;
		}
		return mapSearchResult;
	}

	/**
	 * マップ<String, String>のキーを部分一致検索する
	 * @param keyword	検索キーワード
	 * @param map	検索対象マップ<String,String>
	 * @return	検索結果のマップ、キーワードが空・nullの場合nullを返す
	 */
	public static Map<String, String> searchOnKey(
			String keyword,
			Map<String, String> map) {
		Map<String,String> mapSearchResult = new HashMap<String, String>();
		if(		keyword == null
			||	keyword.isEmpty()
		) {
			// キーワードがない場合 空マップを返す
			mapSearchResult = null;
		} else {
			Set<String> setKey = map.keySet();
			for( String key: setKey ) {
				if( key.contains(keyword) ) {
					mapSearchResult.put(key, map.get(key));
				}
			}
		}
		return mapSearchResult;
	}
}
