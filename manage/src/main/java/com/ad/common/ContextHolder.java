package com.ad.common;

import com.ad.entity.project.Project;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xiang on 2017/5/8.
 */
@Component
public class ContextHolder {

    private static ThreadLocal<Project> currentProjectHolder = new ThreadLocal<>();
    private static ThreadLocal<HashMap<String, Object>> contextHolder = new ThreadLocal<>();

    public static void setCurrentProject(Project currentProject) {
        currentProjectHolder.set(currentProject);
    }

    public static Project getCurrentProject() {
        return currentProjectHolder.get();
    }

    public static void set(String key, Object o) {
        if (contextHolder.get() == null) {
            contextHolder.set(new HashMap<>());
        }
        contextHolder.get().put(key, o);
    }

    public static Object get(String key) {
        if (contextHolder.get() == null) {
            contextHolder.set(new HashMap<>());
        }
        return contextHolder.get().get(key);
    }
}
