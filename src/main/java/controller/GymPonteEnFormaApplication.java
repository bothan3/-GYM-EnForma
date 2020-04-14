package controller;

import java.util.Collections;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.session.hazelcast.config.annotation.web.http.EnableHazelcastHttpSession;

import com.hazelcast.config.Config;
import com.hazelcast.config.JoinConfig;


@SpringBootApplication
@EnableHazelcastHttpSession
@EnableCaching
public class GymPonteEnFormaApplication {

	private static final Log LOG = LogFactory.getLog(GymPonteEnFormaApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(GymPonteEnFormaApplication.class, args);
	}
	
	//Configuración de la cache de consultas para reducir el numero de accesos bbdd
    @Bean
    public CacheManager cacheManager() {
    	LOG.info("Activating cache...");
    	return new ConcurrentMapCacheManager("socio","profesor","clase");
    }

	//Para que Hazelcast pueda conocer el resto de instancias en la misma subred 
	@Bean
	public Config config() {
		Config config = new Config();
		JoinConfig joinConfig = config.getNetworkConfig().getJoin();
		joinConfig.getMulticastConfig().setEnabled(false);
		//Se añade la instancia en el sesion distribuida
		joinConfig.getTcpIpConfig().setEnabled(true).setMembers(Collections.singletonList("127.0.0.1"));
		
		return config;
	}

}
