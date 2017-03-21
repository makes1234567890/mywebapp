/**
 * トップページのJS
 */
var mapContentsRegistered = {};
var mapSearchResult = {};

function setMapContentsRegistered(mapStrStr) {
//	mapContentsRegistered = mapStrStr;
	console.log(mapStrStr);
}

function addContentsToMap(key, value) {
	mapContentsRegistered[key] = value;
}

function isThereContents() {
	var flagExist = false;
	if( mapContentsRegistered != null ) {
		flagExist = true;
	}
	return flagExist;
}

function search() {
	var keyword = document.forms.keyword;
	var map = document.forms.mapFilenameDLPath;
	document.forms.mapFilenameDLPath = searchMap(keyword, map);
}

function searchMap(keyword, map) {
	console.log(map);
	var mapSearchResult = {};
	if(		keyword == null
		||	keyword.length == 0
	) {
		// キーワードがない場合 空マップを返す
		mapSearchResult = null;
	} else {
		// キーワードがある場合 部分一致検索する
		// 検索用の正規表現オブジェクトを作る	i:大文字小文字の無視
		reg = new RegExp(keyword, "i");
		for( var key in mapContentsRegistered ) {
			if( key.match(reg) ) {
				mapSearchResult[key] = mapContentsRegistered[key];
			}
		}
	}
	return mapSearchResult;
}

function searchContents() {
	var keyword = document.search.keyword.value;
	var mapSearchResult = searchMap(keyword, mapContentsRegistered);
	if(mapSearchResult == null ) {
		mapSearchResult = mapContentsRegistered;
	}
}
