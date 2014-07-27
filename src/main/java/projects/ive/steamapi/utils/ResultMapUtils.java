package projects.ive.steamapi.utils;

import java.util.Map;
import java.util.Set;

public class ResultMapUtils {

    public static boolean isSuccessfullyRetrieved(Map<Object, Object> resultMap) {
        Long appId = extractAppId(resultMap);

        @SuppressWarnings("unchecked")
        Map<Object, Object> innerMap = (Map<Object, Object>)resultMap.get(appId + "");
        return (Boolean)innerMap.get("success");
    }

    public static Long extractAppId(Map<Object, Object> resultMap) {
        long appId = 0;

        Set<Object> keySet = resultMap.keySet();
        for (Object object : keySet) {
            appId = Long.parseLong((String)object);
        }

        return appId;
    }

}
