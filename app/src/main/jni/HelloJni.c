#include <jni.h>

//
// Created by JP on 16-3-24.
//package github.jp1017.hellojni;
//

/*********     静态注册方法 staticRegFromJni   ***********/
//方法名：Java_完整包名类名_方法名();
/**
 * env : 代表Java环境, 通过这个环境可以调用Java中的方法
 * obj : 代表调用JNI方法的对象, 即MainActivity对象
 */
JNIEXPORT jstring JNICALL Java_github_jp1017_hellojni_MainActivity_staticRegFromJni(JNIEnv * env, jobject obj)
{
    return (*env) -> NewStringUTF(env, "静态注册调用成功");
}


/*********     动态注册方法 dynamicRegFromJni   ***********/

static jstring nativeDynamicRegFromJni(JNIEnv *env, jobject obj)
{
    return (*env) -> NewStringUTF(env, "动态注册调用成功");
}

//方法数组，正是这个，可以动态调用任意 native 方法
JNINativeMethod nativeMethod[] = {{"dynamicRegFromJni", "()Ljava/lang/String;", (void*)nativeDynamicRegFromJni}};

//
JNIEXPORT jint JNICALL JNI_OnLoad(JavaVM *jvm, void *reserved)
{
    JNIEnv *env;
    if ((*jvm) -> GetEnv(jvm, (void**) &env, JNI_VERSION_1_4) != JNI_OK)
    {
        return -1;
    }

    jclass clz = (*env) -> FindClass(env, "github/jp1017/hellojni/MainActivity");

    (*env) -> RegisterNatives(env, clz, nativeMethod, sizeof(nativeMethod) / sizeof(nativeMethod[0]));

    return JNI_VERSION_1_4;
}
