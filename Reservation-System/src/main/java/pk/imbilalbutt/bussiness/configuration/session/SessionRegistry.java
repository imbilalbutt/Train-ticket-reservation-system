//package pk.imbilalbutt.bussiness.configuration.session;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.core.ValueOperations;
//import org.springframework.stereotype.Component;
//
//import java.nio.charset.StandardCharsets;
//import java.util.Base64;
//import java.util.UUID;
//
//@Component
//public class SessionRegistry {
//
//    private static final Logger LOG = LoggerFactory.getLogger(SessionRegistry.class);
//
//    //private static final HashMap<String, String> SESSIONS = new HashMap<>();
//    private final ValueOperations<String, String> redisSessionStorage;
//
//    @Autowired
//    public SessionRegistry(final RedisTemplate<String, String> redisTemplate) {
//        redisSessionStorage = redisTemplate.opsForValue();
//    }
//
//    public String registerSession(final String username) {
//
//        LOG.info("SessionRegistry - registerSession() invoked.");
//
//        if (username == null) {
//            throw new RuntimeException("Username needs to be provided");
//        }
//
//        final String sessionId = generateSessionId();
//
//        try {
//            // This try block runs if redis server is up and running.
//            redisSessionStorage.set(sessionId, username);
//        } catch (final Exception e) {
//
//            LOG.error("SessionRegistry - registerSession() invoked - catch - Couldn't find the session in Redis.");
//
//            //SESSIONS.put(sessionId, username);
//        }
//
//        return sessionId;
//    }
//
//    public String getUsernameForSession(final String sessionId) {
//
//        LOG.info("SessionRegistry - getUsernameForSession() invoked - sessionId : {} ", sessionId);
//
//        try {
//
//            LOG.info("SessionRegistry - getUsernameForSession() invoked - redisSessionStorage.get(sessionId) : {} ", redisSessionStorage.get(sessionId));
//
//            return redisSessionStorage.get(sessionId);
//        } catch (final Exception e) {
//            LOG.error("Redis Server is down :( Reason Being - {} ", e.getMessage());
//            return null;
//            //return SESSIONS.get(sessionId);
//        }
//    }
//
//    private String generateSessionId() {
//
//        LOG.info("SessionRegistry - generateSessionId() invoked.");
//
//        return new String(
//                Base64.getEncoder().encode(
//                        UUID.randomUUID().toString().getBytes(StandardCharsets.UTF_8)
//                )
//        );
//    }
//}