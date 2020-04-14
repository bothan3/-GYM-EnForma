package controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CacheController {
	
	@Autowired
	private CacheManager cacheManager;
	
	@RequestMapping(value="/cache/socio", method=RequestMethod.GET)
	public Map<Object, Object> getCacheContentSocio() {
		ConcurrentMapCacheManager cacheMgr = (ConcurrentMapCacheManager) cacheManager;
		ConcurrentMapCache cache = (ConcurrentMapCache) cacheMgr.getCache("socio");
		return cache.getNativeCache();
	}
	@RequestMapping(value="/cache/profesor", method=RequestMethod.GET)
	public Map<Object, Object> getCacheContentProfesor() {
		ConcurrentMapCacheManager cacheMgr = (ConcurrentMapCacheManager) cacheManager;
		ConcurrentMapCache cache = (ConcurrentMapCache) cacheMgr.getCache("profesor");
		return cache.getNativeCache();
	}
	@RequestMapping(value="/cache/clase", method=RequestMethod.GET)
	public Map<Object, Object> getCacheContentClase() {
		ConcurrentMapCacheManager cacheMgr = (ConcurrentMapCacheManager) cacheManager;
		ConcurrentMapCache cache = (ConcurrentMapCache) cacheMgr.getCache("clase");
		return cache.getNativeCache();
	}
}
