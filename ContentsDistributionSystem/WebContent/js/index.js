/**
 * トップページのJS
 */
/**
 * 実験用関数
 */
function exam() {
	alert("wow!");
}
/**
 * フォーム
 * @returns
 */
function submitEmpty() {

}

function search() {
	var keyword = document.forms.keyword;
	var map = document.forms.mapFilenameDLPath;
	document.forms.mapFilenameDLPath = searchMap(keyword, map);
}

function searchMap(keyword, map) {
	console.log(map);
	var mapSearchResult;
	if(		keyword == null
		||	keyword.isEmpty()
	) {
		// キーワードがない場合 空マップを返す
		mapSearchResult = null;
	} else {
		// キーワードがある場合 部分一致検索する
		// マップからキーを取り出す
		var setKey = map.keys();
		// 検索用の正規表現オブジェクトを作る	i:大文字小文字の無視
		reg = new RegExp(keyword, 'i');
		for( var i=0; i<setKey.size(); i++ ) {
			if( setKey[i].match(reg) ) {
				mapSearchResult.set(key, map.get(key));
			}
		}
	}
	return mapSearchResult;
}