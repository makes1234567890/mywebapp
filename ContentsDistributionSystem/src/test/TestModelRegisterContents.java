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

	@Test
	public void testRegisterContents() {
		Map<String, String> map = ModelRegisterContents.registorContents();
		System.out.println("key\n::value");
		for(String key: map.keySet()) {
			System.out.println(	key +"\n::"+ map.get(key));
		}
	}

	@Test
	public void testSearchOnFilename001() {
		final String keyword = null;
		Map<String, String> mapContents = ModelRegisterContents.registorContents();
		Map<String, String> mapSearched = ModelRegisterContents.searchOnFilename(keyword, mapContents);
		System.out.println("key\n::value");
		for(String key: mapSearched.keySet()) {
			System.out.println(	key +"\n::"+ mapSearched.get(key));
		}
		assertEquals(
				mapContents,
				mapSearched
				);
	}
	@Test
	public void testSearchOnFilename002() {
		final String keyword = "";
		Map<String, String> mapContents = ModelRegisterContents.registorContents();
		Map<String, String> mapSearched = ModelRegisterContents.searchOnFilename(keyword, mapContents);
		System.out.println("key\n::value");
		for(String key: mapSearched.keySet()) {
			System.out.println(	key +"\n::"+ mapSearched.get(key));
		}
		assertEquals(
				mapContents,
				mapSearched
				);
		}
	@Test
	public void testSearchOnFilename003() {
		final String keyword = "a";
		Map<String, String> mapContents = ModelRegisterContents.registorContents();
		Map<String, String> mapSearched = ModelRegisterContents.searchOnFilename(keyword, mapContents);
		System.out.println("key\n::value");
		for(String key: mapSearched.keySet()) {
			System.out.println(	key +"\n::"+ mapSearched.get(key));
		}
		assertNotEquals(
				mapContents,
				mapSearched
				);
	}
	@Test
	public void testSearchOnFilename004() {
		final String keyword = "笑";
		Map<String, String> mapContents = ModelRegisterContents.registorContents();
		Map<String, String> mapSearched = ModelRegisterContents.searchOnFilename(keyword, mapContents);
		System.out.println("key\n::value");
		for(String key: mapSearched.keySet()) {
			System.out.println(	key +"\n::"+ mapSearched.get(key));
		}
		assertNotEquals(
				mapContents,
				mapSearched
				);
	}
	@Test
	public void testSearchOnFilename005() {
		final String keyword = "asdfghjk";
		Map<String, String> mapContents = ModelRegisterContents.registorContents();
		Map<String, String> mapSearched = ModelRegisterContents.searchOnFilename(keyword, mapContents);
		System.out.println("key\n::value");
		for(String key: mapSearched.keySet()) {
			System.out.println(	key +"\n::"+ mapSearched.get(key));
		}
		assertNotEquals(
				mapContents,
				mapSearched
				);
	}

}
