/**
 * Precompiled [de.Ste3et_C0st.furniture.java-conventions.gradle.kts][De_Ste3et_C0st_furniture_java_conventions_gradle] script plugin.
 *
 * @see De_Ste3et_C0st_furniture_java_conventions_gradle
 */
class De_Ste3et_C0st_furniture_javaConventionsPlugin : org.gradle.api.Plugin<org.gradle.api.Project> {
    override fun apply(target: org.gradle.api.Project) {
        try {
            Class
                .forName("De_Ste3et_C0st_furniture_java_conventions_gradle")
                .getDeclaredConstructor(org.gradle.api.Project::class.java, org.gradle.api.Project::class.java)
                .newInstance(target, target)
        } catch (e: java.lang.reflect.InvocationTargetException) {
            throw e.targetException
        }
    }
}
