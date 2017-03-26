package system.distribution.contents;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
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
    private final String LOCATION_ROOT_CONTENTS_DIRECTORY;	// コンテンツディレクトリの物理パスのルート
	private final String CONTEXTPATH_ROOT_CONTENTS =	// コンテンツディレクトリのDLリンクのルート
			"/ContentsDistributionSystem/contents";
	private final static String DELIMITER_CONTEXTPATH = "/";
	private final static String STR_EMPTY = "";
	// 変数
	String locationCurrent = "";	// 検索中のディレクトリ物理パス
	String contextPathCurrentDirectory = "";	// 検索中のディレクトリDLリンク

	/**
	 * コンストラクタ
	 * @param pathContext コンテンツディレクトリのルート
	 */
	public ModelRegisterContents(String pathContext) {
		this.LOCATION_ROOT_CONTENTS_DIRECTORY = pathContext;
		SystemLog.println("locationRootContentsDirectory:"+ this.LOCATION_ROOT_CONTENTS_DIRECTORY, this);
		this.mapRegistored.clear();
		this.registorContents();
	}
	/**
	 * コンテンツディレクトリにあるファイルをインスタンスに格納する
	 */
	public void registorContents() {
		File currentDirectory =	// カレントディレクトリ
				new File(
						LOCATION_ROOT_CONTENTS_DIRECTORY
						+ locationCurrent
						);
		File files[] = currentDirectory.listFiles();	// カレントディレクトリにあるファイル

		for( int i=0; i<files.length; i++) {
			if( files[i].isFile() ) {
				// ファイルの時 ファイル名とDLリンクを登録
				mapRegistored.put(
						files[i].getName(),
						  CONTEXTPATH_ROOT_CONTENTS
						+ contextPathCurrentDirectory
						+ DELIMITER_CONTEXTPATH
						+ files[i].getName()
						);
				SystemLog.println("filename:"+ files[i].getName(), this);
			} else  if( files[i].isDirectory() ) {
				// ディレクトリの時 その階層へ移動
				locationCurrent =
						  locationCurrent
						+ File.separator
						+ files[i].getName();
				contextPathCurrentDirectory =
						  contextPathCurrentDirectory
						+ DELIMITER_CONTEXTPATH
						+ files[i].getName();
				// カレントディレクトリを移動して再度検索する
				registorContents();
				// ディレクトリの検索が終わったのでカレントディレクトリを元に戻す
				locationCurrent =
						locationCurrent.replace(
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
			for( Map.Entry<String, String> me : map.entrySet() ){
				// 検索対象マップから1組のキー（ファイル名）とバリュー（DLパス）を取得
				if( isMatchedPartially(keyword, me.getKey()) )
					// 検索キーがキー（ファイル名）に部分一致　検索結果マップに登録
					mapSearchResult.put(me.getKey(), me.getValue());
			}
		}
		return mapSearchResult;
	}
	private static boolean isMatchedPartially( String key, String target ) {
		if(		key == null
			||	target == null
			||	key.isEmpty()
			||	target.isEmpty()
		) {
			// 検索キー、検索対象なし
			return false;
		}
		String keyLowered		= key.toLowerCase();
		String targetLowered	= target.toLowerCase();

		return targetLowered.contains(keyLowered);
	}
}
