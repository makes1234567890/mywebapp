package test;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Test;

import system.distribution.contents.ModelRegisterContents;

/**
 * テストメソッド
 * @author matak
 *
 */
public class TestModelRegisterContents {

	final String LOCATION_ROOT_CONTENTSDIRECTORY = "C:\\Users\\matak\\Music\\recochoku";
	@Test
	public void testRegisterContents() {
		ModelRegisterContents mrc = new ModelRegisterContents(LOCATION_ROOT_CONTENTSDIRECTORY);
		Map<String, String> map = mrc.getMapRegistored();
		System.out.println("key\n::value");
		for(String key: map.keySet()) {
			System.out.println(	key +"\n::"+ map.get(key));
		}
	}

	@Test
	public void testSearchOnFilename001() {
		final String keyword = null;
		ModelRegisterContents mrc = new ModelRegisterContents(LOCATION_ROOT_CONTENTSDIRECTORY);
		Map<String, String> mapSearched = mrc.searchOnFilename(keyword);
		System.out.println("key\n::value");
		for(String key: mapSearched.keySet()) {
			System.out.println(	key +"\n::"+ mapSearched.get(key));
		}
		assertEquals(
				mrc.getMapRegistored(),
				mapSearched
				);
	}
	@Test
	public void testSearchOnFilename002() {
		final String keyword = "";
		ModelRegisterContents mrc = new ModelRegisterContents(LOCATION_ROOT_CONTENTSDIRECTORY);
		Map<String, String> mapSearched = mrc.searchOnFilename(keyword);
		System.out.println("key\n::value");
		for(String key: mapSearched.keySet()) {
			System.out.println(	key +"\n::"+ mapSearched.get(key));
		}
		assertEquals(
				mrc.getMapRegistored(),
				mapSearched
				);
		}
	@Test
	public void testSearchOnFilename003() {
		final String keyword = "a";
		ModelRegisterContents mrc = new ModelRegisterContents(LOCATION_ROOT_CONTENTSDIRECTORY);
		Map<String, String> mapSearched = mrc.searchOnFilename(keyword);
		System.out.println("key\n::value");
		for(String key: mapSearched.keySet()) {
			System.out.println(	key +"\n::"+ mapSearched.get(key));
		}
		assertNotEquals(
				mrc.getMapRegistored(),
				mapSearched
				);
	}
	@Test
	public void testSearchOnFilename004() {
		final String keyword = "笑";
		ModelRegisterContents mrc = new ModelRegisterContents(LOCATION_ROOT_CONTENTSDIRECTORY);
		Map<String, String> mapSearched = mrc.searchOnFilename(keyword);
		System.out.println("key\n::value");
		for(String key: mapSearched.keySet()) {
			System.out.println(	key +"\n::"+ mapSearched.get(key));
		}
		assertNotEquals(
				mrc.getMapRegistored(),
				mapSearched
				);
	}
	@Test
	public void testSearchOnFilename005() {
		final String keyword = "asdfghjk";
		ModelRegisterContents mrc = new ModelRegisterContents(LOCATION_ROOT_CONTENTSDIRECTORY);
		Map<String, String> mapSearched = mrc.searchOnFilename(keyword);
		System.out.println("key\n::value");
		for(String key: mapSearched.keySet()) {
			System.out.println(	key +"\n::"+ mapSearched.get(key));
		}
		assertNotEquals(
				mrc.getMapRegistored(),
				mapSearched
				);
	}

}
